package domini;

import java.util.HashMap;

import domini.Excepcions.*;

public class Usuari {

    //ATRIBUTS

     /**
      * id del usuari. 
      */
    private int id; 

    /**
     * Map que conté com a clau tots aquells ids de ítems que l'usuari amb identificador id a valorat, i com a valor d'aquestes claus, el id de la valoració que correspon. 
     */
    private HashMap<Item, Valoracio> itemsValoracions; 

    //CONSTRUCTORES

    /**
     * Crea un usuari amb el id 'id', el qual NO és un usuari actiu. 
     * @param id serà l'id de l'usuari nou. 
     */
    public Usuari (int id) {
        this.id = id; 
        itemsValoracions = new HashMap<Item, Valoracio> (); 
    }


    //GETTERS, SETTERS, OPERACIONS
    
    /**
     * Comprova si l'usuari amb identificador id és actiu o no. 
     * @return retorna si l'usuari és un usuari actiu o no. 
     */
    public boolean isActiu () {
        return false; //el valor retornat serà overriden per la subclasse UActiu, si es dona el cas. 
    }

    /**
     * Retorna la id de l'usuari
     * @return és l'id
     */
    public int getId () {
        return id; 
    }

    /**
     * Retorna la valoracio de l'usuari
     * @return és l'id
     */
    public Valoracio getValoracio(Item item) {
        return itemsValoracions.get(item);
    }

    
    /**
     * Retorna un booleà si l'usuari ha valorat un item
     * @return és l'id
     */
    public boolean haValorat(Item item){
        if (itemsValoracions.get(item)!=null) return true;
        else return false; 
    }

    /**
     * Informa dels ítems que ha valorat l'usuari amb identificador id, i de les valoracions d'aquests ítems. 
     * @return retorna totes aquestes instàncies d'ítems i valoracions. 
     */
    public HashMap<Item, Valoracio> getItemsValoracions () {
        return itemsValoracions; 
    }


    /**
     * S'afegeix com a propietat de l'usuari una valoració. És a dir, que l'usuari crea una valoració
     * @param valoracio És la valoració nova que crea l'usuari
     * @throws Excepcions.InvalidValoracioException en cas que ja s'hagi valorat l'ítem de la nova valoració per a l'usuari de la classe, saltarà una excepció, ja que això no es pot produir
     */
    public void addItemValoracio (Valoracio valoracio) throws Excepcions.InvalidValoracioException{

        if (itemsValoracions.containsKey(valoracio.getItem())) {
            throw new InvalidValoracioException (); 
        }

        itemsValoracions.put(valoracio.getItem(), valoracio); 
    }

    public void eliminarValoracio(Item item) {
        itemsValoracions.remove(item);
    }
}