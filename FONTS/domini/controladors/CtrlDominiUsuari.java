package domini.controladors;

import domini.Usuari;
import domini.UActiu;
import domini.Valoracio;
import domini.Excepcions;
import domini.Item;

import java.util.*;

public class CtrlDominiUsuari {

    /**Mapa d'usuaris del domini*/
    private final Map<Integer, Usuari> usuaris = new HashMap<>();
    
    /**Integer per saber quin és l'últim id d'usuari que s'ha assignat*/
    private int idMax = 0;
    /**Instancia de la propia classe. Propietat singleton. */
    private static CtrlDominiUsuari instance;

    public CtrlDominiUsuari() {
    }
    
    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlDominiUsuari getInstance() {
        if (instance == null) {
            instance = new CtrlDominiUsuari();
        }
        return instance;
    }

    /**
    * Afegeix un conjunt d'usuaris al mapa d'usuaris, també actualitza idMax.
    * @param usuaris, Map<Integer, Usuari>
    * @return
    */
    public void afegirCjtUsuaris(Map<Integer, Usuari> usuaris) {
        this.usuaris.putAll(usuaris);
        for(int id: usuaris.keySet()) {
            if (id > idMax) idMax = id;
        }
    }

    /**
    * Registra un nou usuari, afegint-lo al mapa d'usuaris i actualitza iMax. 
    * @param nom, String
    * @param contrasenya, String
    * @return
    */
    public void registraUsuari(String nom, String contrasenya) throws Exception {
        for(Usuari usuari: usuaris.values()) {
            if (usuari.isActiu() && ((UActiu)usuari).getContrasenya().equals(contrasenya) && ((UActiu)usuari).getNom().equals(nom)) throw new Excepcions.UsuariJaExisteix();
        }
        usuaris.put(idMax+1, new UActiu(idMax+1, nom, contrasenya));
        ++idMax;
    }
    
    /**
    * Inicia sessió d'un usuari. 
    * @param nom, String
    * @param contrasenya, String
    * @return
    */
    public UActiu iniciarSesio(String nom, String contrasenya) throws Exception {
        for(Usuari u: usuaris.values()) {
            if (u.isActiu() && ((UActiu)u).getNom().equals(nom) && ((UActiu)u).getContrasenya().equals(contrasenya)) return (UActiu) u;
        }
        throw new Excepcions.UsuariAmbNomIContrasenyaNoExisteix();
    }
    
    
    /**
    * Afegeix una nova valoració a un usuari del domini.  
    * @param usuariActiu, UActiu
    * @param puntuacio, Integer
    * @param comentaris, String
    * @param item, Item 
    * @return
    */
    public void afegirValoracio(UActiu usuariActiu, Integer puntuacio, String comentaris, Item item) throws Excepcions.ItemNul, Excepcions.InvalidPuntuacioException, Excepcions.InvalidValoracioException {
        usuaris.get(usuariActiu.getId()).addItemValoracio(new Valoracio(usuariActiu, puntuacio, comentaris, item));
    }

    /**
    * Elimina una  valoració a un usuari del domini.  
    * @param usuariActiu, UActiu
    * @param item, Item 
    * @return
    */
    public void eliminarValoracio(UActiu usuariActiu, Item item) {
        usuaris.get(usuariActiu.getId()).eliminarValoracio(item);
    }
    
    /**
    * Elimina un usuari actiu del mapa d'usuaris del domini.  
    * @param usuariActiu, UActiu
    * @return
    */
    public void eliminarUA(int id) {
        usuaris.remove(id);
    }

    /**
    * Comproba si el creador d'un ítem és el mateix que l'usuari actiu, retorna true si és així, false si no.    
    * @param id, Integer
    * @param item, Item
    * @return Boolean, true si és cert, false si no
    */
    public boolean checkCreador(int id, Item item) {
        return ((UActiu)usuaris.get(id)).getItemsCreats().contains(item);
    }
    

    /**
    * Elimina totes les valoracions que siguin fetes a un cert ítem dels usuaris i l'item creat. 
    * @param id, Integer
    * @param item, Item
    * @return Boolean, true si és cert, false si no
    */
    public void eliminarItem(int id, Item item) {
        ((UActiu)usuaris.get(id)).eliminarItemCreat(item);
        for (Usuari usuari: usuaris.values()) {
            usuari.eliminarValoracio(item);
        }
        //usuaris.get(id).eliminarValoracio(item);
    }

    /**
    * Crea un nou ítem, fet per l'usuari actiu. 
    * @param id, Integer
    * @param item, Item
    * @return Boolean, true si és cert, false si no
    */
    public void crearItem(UActiu usuariActiu, Item item) {
        ((UActiu)usuaris.get(usuariActiu.getId())).afegirItemCreat(item);
    }
    
    
    /**
    * Getter del mapa d'usuaris. 

    * @return usuaris,  Map<Integer, Usuari>
    */
    public Map<Integer, Usuari> getUsuaris() {
        return usuaris;
    }
}