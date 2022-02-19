/**
 Valoracio.Java
 especificació de classe Valoracio
 */

package domini;


import domini.Excepcions.InvalidPuntuacioException;
import domini.Excepcions.NotAutorException;
import domini.Excepcions.ItemNul;;

/**
 Valoracio
 representa una valoració en el nostre sistema

 Classe que representa una valoració. Conté l'id d'un autor, un títol i una descripció.
 **/



public class Valoracio {
    /** Usuari que ha escrit la valoracio*/
    private Usuari usuari;
    /** Puntuació de la Valoracio*/
    private double puntuacio ;
    /** Descripció de la Valoracio*/
    private String descripcio;
    /** Item al que correspon la Valoracio*/
    private Item item;

    /**CONSTRUCTORS*/
    /*Mirar tema controladors: Per crear cal autor*/
    /*Poden haver valoracions sense autor??*/
    public Valoracio(Usuari usuari, double puntuacio, String descripcio, Item item) throws InvalidPuntuacioException, ItemNul{
        this.usuari = usuari; //L'autor hauria d'existir
        if(puntuacio < 0 || puntuacio > 5) throw new InvalidPuntuacioException();
        this.puntuacio = puntuacio;
        this.descripcio = descripcio;
        if(item.getID() == -1) throw new ItemNul();
        this.item = item;  
    }

    /**CONSULTORS*/

    /** Consultor que retorna el paràmetre implicit del usuari que ha escrit la valoració.
     El paràmetre implicit del usuari és retornat.
     */
    public Usuari getAutor() {
        return this.usuari;
    }

    /**Consultor que retorna el paràmetre implicit de la puntuació.
    El paràmetre implicit de la puntuació és retornat.
     */
    public double getPuntuacio() {
        return this.puntuacio;
    }

    /**Consultor que retorna el paràmetre implicit de la descripció.
     El paràmetre implicit de la descripció és retornat.
     */
    public String getDescripcio() {
        return this.descripcio;
    }

    /**Consultor que retorna el paràmetre implicit de l'ítem.
     El paràmetre implicit de l'ítem és retornat.
     */
    public Item getItem() {
        return this.item;
    }


    /**MODIFICADORS*/

    /**Modificador que canvia el paràmetre implitic descripcio per 'descripcio' si 'usuari' és el mateix que autor.
     El paràmetre implícit de descrition s'ha canviat.
     * */

    public void setDescripcio(String descripcio, Usuari usuari) throws NotAutorException{
        if(usuari != this.usuari) throw new NotAutorException();
            this.descripcio = descripcio;
    }

    /**Modificador que canvia el paràmetre implitic puntuació per 'puntuació'
     * El paràmetre implícit de puntuació s'ha canviat.
     * */
    public void setPuntuacio(int puntuacio, Usuari usuari) throws InvalidPuntuacioException, NotAutorException{
        if(usuari != this.usuari) throw new NotAutorException();
        if(puntuacio < 0 || puntuacio > 5) throw new InvalidPuntuacioException();
        this.puntuacio = puntuacio;
    }





}