package domini;

import domini.Excepcions.*;

import java.util.HashSet;
import java.util.Set;

public class UActiu extends Usuari {

    //ATRIBUTS

    /**
     * El nom de l'usuari actiu. 
     */
    private String nom; 

    /**
     * La constrassenya d'accés de l'usuari actiu.  
     */
    private String password;

    private Set<Item> itemsCreats = new HashSet<>();

    //CONSTRUCTORES

    /**
     * Crea un usuari ACTIU, amb l'id, nom i password donats
     * @param id serà l'id de l'usari anem a crear.  
     * @param nom serà el nom de l'usuari que anem a crear
     * @param password serà el password de l'usuari que anem a crear
     */
    public UActiu (int id, String nom, String password) throws NomNul, PasswordNul {
        super(id);
        if (nom == null) throw new NomNul (); 
        if (password == null) throw new PasswordNul(); 
        
        this.nom = nom; 
        this.password = password; 
    }


    //GETTERS

    /**
     * Comprova si l'usuari és actiu o no
     * @return retorna si l'usuari és un usuari actiu o no
     */
    @Override
    public boolean isActiu () {
        return true; 
    }

    public String getNom () {
        return nom; 
    }

    public String getContrasenya () {
        return password; 
    }


    //STTERS

    /**
     * Canvia la contrassenya d'accés de l'usuari per una de nova
     * @param pasword és la nova contrassenya d'accés que tindrà l'usuari
     */
    public void canviaPassword(String passwordVella, String passwordNova) throws Excepcions.IncorrectPassword_CANTCHANGE, PasswordVellaNul, PasswordNovaNul {
        if (passwordNova == null) throw new PasswordNovaNul (); 
        if (passwordVella == null) throw new PasswordVellaNul ();  
        if (passwordVella.equals(password)) {
            this.password = passwordNova; 
        }
        else {
            throw new IncorrectPassword_CANTCHANGE(); 
        }
        
    }

    public Set<Item> getItemsCreats() {
        return itemsCreats;
    }

    public void eliminarItemCreat(Item item) {
        itemsCreats.remove(item);
    }

    public void afegirItemCreat(Item item) {
        itemsCreats.add(item);
    }
}