package domini.controladors;

import domini.*;
import utils.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CtrlDominiItem {

    /**Integer per saber quin és l'últim id d'item que s'ha assignat*/
    private int idMax = 0;
     /**Mapa d'items del domini*/
    private Map<Integer, Item> items;
    /**Instancia de la propia classe. Propietat singleton. */
    private static CtrlDominiItem instance;

    public CtrlDominiItem() {
    }

    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */

    public static CtrlDominiItem getInstance() {
        if (instance == null) {
            instance = new CtrlDominiItem();
        }
        return instance;
    }
    
    /**
    * Inicialitza el controlador afegint un conjunt d'items al mapa d'items, també actualitza idMax.
    * @param items, Map<Integer, Item>
    * @return
    */
    public void inicialitzarControlador(Map<Integer, Item> items) {
        this.items = items;
        for (Integer key: items.keySet()) {
            if (key > idMax) idMax = key;
        }
    }

    /**
    * Getter dels títols dels ítems. 
    * @param itemsRecomanats, HashSet<Integer>
    * @param nomTitol, String
    * @return HashSet<String>
    */
    public HashSet<String> getTitolsItems(HashSet<Integer> itemsRecomanats, String nomTitol) {
        HashSet<String> titolsItems = new HashSet<>();
        for(Integer key: itemsRecomanats) {
            titolsItems.add(getTitol(key, nomTitol));
        }
        return titolsItems;
    }

    /**
    * Funció que retorna un booleà en funció de si existeix o no un cert ítem. 
    * @param nom, String
    * @param nomTitol, String
    * @return boolean
    */
    public boolean existeixItem(String nom, String nomTitol) {
        for(Integer key: items.keySet()) {
            for (Atribut atr: items.get(key).getAtributs()) {
                if (atr.getTipusAtribut().getNom().equals(nomTitol) && ((AtrString)atr).getAtributString().equalsIgnoreCase(nom)) return true;
            }
        }
        return false;
    }

    /**
    * Getter del id d'un cert ítem. 
    * @param titol, String
    * @param nomTitol, String
    * @return Integer, id del títol
    */
    public int getIdItem(String titol, String nomTitol) throws Excepcions.ItemNoExisteix {
        for(Integer key: items.keySet()) {
            for (Atribut atr: items.get(key).getAtributs()) {
                if (atr.getTipusAtribut().getNom().equals(nomTitol) && ((AtrString)atr).getAtributString().equalsIgnoreCase(titol)) return key;
            }
        }
        throw new Excepcions.ItemNoExisteix();
    }

    /**
    * Getter dels valors TipusAtribut d'un item. 
    * @param titol, String
    * @param nomTitol, String
    * @return  HashMap<String, String>, amb el valor dels tipus d'atribut
    */    
    public HashMap<String, String> getValorsTipusAtributDeItem(String titol, String nomTitol) throws Excepcions.ItemNoExisteix {
        if (!items.containsKey(getIdItem(titol, nomTitol))) throw new Excepcions.ItemNoExisteix();
        HashMap<String, String> valorsAtributs = new HashMap<>();
        for(Integer key: items.keySet()) {
            for (Atribut atr: items.get(key).getAtributs()) {
                if (atr.getTipusAtribut().getNom().equals(nomTitol) && ((AtrString)atr).getAtributString().equalsIgnoreCase(titol)) {
                    for (Atribut atr2: items.get(key).getAtributs()) {
                        String dada = "";
                        String nomTipusAtribut = atr2.getTipusAtribut().getNom();
                        if (!atr2.getTipusAtribut().getTipusDada().equals("String")) dada = atr2.getDadaEnString();
                        else {
                            dada = atr2.getDadaEnString();
                            for(Atribut atrStr: items.get(key).getAtributs()) {
                                if (atrStr != atr2 && atrStr.getTipusAtribut().getNom().equalsIgnoreCase(atr2.getTipusAtribut().getNom())) dada = dada.concat(", " + atrStr.getDadaEnString());
                            }
                        }
                        valorsAtributs.put(nomTipusAtribut,  dada);
                    }
                    break;
                }
            }
        }
        return valorsAtributs;
    }

 
    /**
    * Getter d'un item. 
    * @param titol, String
    * @param nomTitol, String
    * @return Item
    */   
    public Item getItem(String item, String titolItem) throws Excepcions.ItemNoExisteix {
        return items.get(getIdItem(item, titolItem));
    }

    /**Getter del titol d'un item per id
    * @param id, Integer
    * @param nomTitol, String
    * @returns String, titol en questió
    */
    public String getTitol(int id, String nomTitol) {
        String titol = "";
        for (Atribut atr: items.get(id).getAtributs()) {
            if (atr.getTipusAtribut().getNom().equals(nomTitol)) titol = ((AtrString)atr).getAtributString();
        }
        return titol;
    }

    /**Elimina un item del mapa d'items
    * @param item Item
    * @returns 
    */
    public void eliminarItem(Item item) {
        items.remove(item.getID());
    }

    /**Afegeix un item al mapa d'items
    * @param atributsAfegits, HashMap<String, String>
    * @param tipusAtributs, Map<Pair<Integer, String>, TipusAtribut>
    * @param nomTitol
     * @returns Integer, retorna idMax
    */
    public Integer afegirItem(HashMap<String, String> atributsAfegits, Map<Pair<Integer, String>, TipusAtribut> tipusAtributs, String nomTitol) throws Excepcions.ValorAtributNul, Excepcions.AtributNul, Excepcions.TipusAtributNul, Excepcions.UnexistingAtributsException, Excepcions.SetAtributsNul, Excepcions.ItemNoTeTitol, Excepcions.ItemNoTeTitol, Excepcions.ItemAmbMateixTitolJaExisteix {
        boolean TitolDetectat = false;
        HashSet<Atribut> atributsItem = new HashSet<>();
        for (String tipus: atributsAfegits.keySet()) {
            for (Pair<Integer, String> key: tipusAtributs.keySet()) {
                if (key.second().equals(tipus)) {
                    if (tipus.equals(nomTitol)) {
                        for (Item item: items.values()) {
                            for (Atribut a: item.getAtributs()) {
                                if (a.getTipusAtribut().getNom().equals(nomTitol) && ((AtrString)a).getAtributString().equals(atributsAfegits.get(tipus))) throw new Excepcions.ItemAmbMateixTitolJaExisteix();
                            }
                        }
                        TitolDetectat = true;
                    }
                    String dada = atributsAfegits.get(tipus);
                    tipusAtributs.get(key).crearAtribut(dada, tipusAtributs, key, atributsItem);
                }
            }
        }
        if (!TitolDetectat) throw new Excepcions.ItemNoTeTitol();
        else {
            ++idMax;

            items.put(idMax, new Item(idMax, atributsItem));
        }
        return idMax;
    }


    /**Getter d'un item per id
    * @param id, Integer
    * @returns String, titol en questió
    */
    public Item getItem(int idItem) {
        return items.get(idItem);
    }
    
    /**Getter del mapa de tots els items
    * @param 
    * @returns Map<Integer, Item>
    */

    public Map<Integer, Item> getItems() {
        return items;
    }
}
