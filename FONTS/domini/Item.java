/**
 Item.Java
 especificació de classe Item
 */

package domini;


//import java.lang.*;
import java.util.*;
import domini.Excepcions.*; 
//import test.drivers.DriverItem;


/**
 Item
 representa un item en el nostre sistema

 Classe que representa un Item. Conté un id, un títol i una descripció.
 * */


public class Item {
    
    /** ID del Ítem*/
    private int id;
    
    /** Valoracions del Ítem on la key és l'autor de la valoració, i el valor es l'id de la valoracio*/
    private HashMap<Usuari,Valoracio> valoracions;

    /** Atributs que corresponen al Ítem*/
    private Set<Atribut> atributs;

    /**CONSTRUCTORS*/

    public Item( int id, HashMap<Usuari,Valoracio> valoracions, Set<Atribut> atributs) throws UnexistingAtributsException, MapValoracionsNul, SetAtributsNul {
        if (valoracions == null) throw new MapValoracionsNul (); 
        if (atributs == null) throw new SetAtributsNul (); 
        //if(atributs.isEmpty()) throw new UnexistingAtributsException();

        this.id = id; 
        this.atributs = atributs;

        this.valoracions = valoracions;
    }

    public Item(int id, Set<Atribut> atributs) throws UnexistingAtributsException, SetAtributsNul {
        if (atributs == null) throw new SetAtributsNul ();
        if(atributs.isEmpty()) throw new UnexistingAtributsException();
        this.id = id;
        this.atributs = atributs;
        this.valoracions = new HashMap<>();
    }
    
    /** Constructor buit per utilitzar de auxiliar*/

   /** 
   * @brief Getter d'Item
   * @param
   * @return Item
   */
    public Item() {
        this.id = -1;
    }



    /**CONSULTORS*/

    /**  Consultor que retorna el paràmetre implicit del id.
     El paràmetre implicit del id és retornat.
     */
    public int getID() {
        return this.id;
    }

   /** 
   * @brief Getter dels atributs
   * @param
   * @return Set<Atribut>
   */
    public Set<Atribut> getAtributs(){
        return this.atributs;
    }


   /** 
   * @brief Getter dels les valoracions per usuari
   * @param
   * @return HashMap<Usuari,Valoracio>
   */
    public HashMap<Usuari,Valoracio> getValoracions () {
        return valoracions; 
    }



    /**MODIFICADORS*/


    //El control de add valoracio hauria de ser al moment de crear la valoracio,
    // pero un objecte no sap si exiteixen altres objectes de la mateixa classe,
    // Ho poso aquí però va a un controlador
    // De moment les afegeixo per ordre que van apareixent, no les ordeno per nom ni na
    
    /** 
   * @brief Funció que afegeix una valoració nova
   * @param valoracio, una valoració nova
   * @param autor, autor de la nova valoracio
   * @return 
   */
    public void addValoracio(Valoracio valoracio, Usuari autor) throws InvalidValoracioException, UsuariNul, ValoracioNul {
        if (autor == null) throw new UsuariNul(); 
        if (valoracio == null) throw new ValoracioNul (); 
        if (valoracions.containsKey(autor)) throw new InvalidValoracioException();
        this.valoracions.put(autor, valoracio);
    }
    
    /** 
   * @brief Funció que elimina una valoració 
   * @param autor, autor de la valoracio
   * @return 
   */
    public void deleteValoracio(Usuari autor) throws InvalidAutorInItem, UsuariNul {
        if (autor == null) throw new UsuariNul (); 
        if (!valoracions.containsKey(autor)) throw new InvalidAutorInItem(); 
        valoracions.remove(autor); 
    }
}
