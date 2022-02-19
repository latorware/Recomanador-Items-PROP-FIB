package domini.controladors;

import domini.*;
import utils.Pair;

import java.util.ArrayList;
import java.util.Map;

public class CtrlDominiValoracio {
    
    /**Mapa de les valoracions del domini*/
    private Map<Pair<Item, Usuari>, Valoracio> valoracions;
    
    /**
     * Instancia de la propia classe. Propietat singleton.
     */
    private static CtrlDominiValoracio instance;


    public CtrlDominiValoracio() {
    }
    
    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlDominiValoracio getInstance() {
        if (instance == null) {
            instance = new CtrlDominiValoracio();
        }
        return instance;
    }

    /**
     * Inicialitza el controlador amb les valoracions del domini. 
     */
    public void inicialitzarControlador(Map<Pair<Item, Usuari>, Valoracio> valoracions) {
        this.valoracions = valoracions;
    }
    
    
    /**
     * Afegeix una valoració al mapa de valoracions de la classe. Rep un usuari actiu, una puntuació, un comentari i un ítem. 
     * @param usuariActiu, UActiu
     * @param puntuacio, Int
     * @param comentaris, String
     * @param item, Item
     * @return 
     */
    public void afegirValoracio(UActiu usuariActiu, Integer puntuacio, String comentaris, Item item) throws Excepcions.ItemNul, Excepcions.InvalidPuntuacioException, Excepcions.UsuariJaTeValoracioAItem {
        Pair<Item, Usuari> key = new Pair<>(item, usuariActiu);
        if (!valoracions.containsKey(key)) valoracions.put(key, new Valoracio(usuariActiu, puntuacio, comentaris, item));
        else throw new Excepcions.UsuariJaTeValoracioAItem();

    }

    /**
     * Elimina una valoració del mapa de valoracions de la classe. Rep el usuari actiu i l'item que es vol eliminar.  
     * @param usuariActiu, UActiu
     * @param item, Item
     * @return 
     */
    public void eliminarValoracio(Usuari usuariActiu, Item item) {
        valoracions.remove(new Pair<>(item, usuariActiu));
    }

    /**
     * Elimina totes les valoracions d'un usuari actiu del mapa de valoracions de la classe. Rep el usuari actiu. 
     * @param usuariActiu, UActiu
     * @return 
     */
    public void eliminarUA(UActiu usuariActiu) {
        ArrayList<Pair<Item, Usuari>> keysAEliminar = new ArrayList<>();
        for (Pair<Item, Usuari> key: valoracions.keySet()) {
            if (key.second().getId() == usuariActiu.getId()) keysAEliminar.add(key);
        }
        for (Pair<Item, Usuari> k: keysAEliminar) {
            valoracions.remove(k);
        }
    }

    /**
     * Elimina totes les valoracions d'un ítem del mapa de valoracions de la classe. Rep l'item que es vol eliminar.  
     * @param item, Item
     * @return 
     */
    public void eliminarItem(Item item) {
        ArrayList<Pair<Item, Usuari>> keysAEliminar = new ArrayList<>();
        for (Pair<Item, Usuari> key: valoracions.keySet()) {
            if (key.first().getID() == item.getID()) keysAEliminar.add(key);
        }
        for (Pair<Item, Usuari> k: keysAEliminar) {
            valoracions.remove(k);
        }
    }
}
