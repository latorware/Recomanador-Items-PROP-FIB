package domini.controladors;

import domini.*;
import utils.Pair;

import java.util.*;

public class CtrlDominiConfiguracio {

    private String nomId;
    private String nomTitol;
    private String path;
    private Map<Pair<Integer, String>, TipusAtribut> tipusAtributs = new TreeMap<>(new PairComparator());
    private static CtrlDominiConfiguracio instance;


    /**
     * Necesari ja que CtrlPresentacio és singleton
     */
    public static CtrlDominiConfiguracio getInstance() {
        if (instance == null) {
            instance = new CtrlDominiConfiguracio();
        }
        return instance;
    }

    /**
     * inicialitza la classe, és a dir agafa la configuració dels tipusAtributs rebuda i l'enmagatzema com a atribut privat
     * @param tipusAtributs la configuració dels TipusAtributs de la base de dades que representa a la que aquesta classe representa la configuració 
     */
    public void inicialitzarControlador(Map<Pair<Integer, String>, TipusAtribut> tipusAtributs) {
        this.tipusAtributs = tipusAtributs;
    }

    /**
     * @return Retorna el path on es troba la base de dades en la que aquesta classe hi representa la configuració
     */
    public String getPath() {
        return path;
    }

    /**
     * @return Retorna la configuració dels TipusAtributs que aquesta classe té guardats. és a dir, el nom amb que s'identifica cada TipusAtribut, i si es calculable o no el TipusAtribut
     */
    public Map<Pair<Integer, String>, TipusAtribut> getTipusAtributs() {
        return tipusAtributs;
    }

    /**
     * @return Retorna el nom del TipusAtribut que representa els atributs que fan referència als titols dels items
     */
    public String getNomTitol() {
        return nomTitol;
    }

    /**
     * @return Retorna el nom del TipusAtribut que representa els atributs que fan referència als id dels items
     */
    public String getNomId() {
        return nomId;
    }

    /**
     * Assigna el TipusAtribut que representa els atributs que fan referència als titols dels items com a tal
     * @param titol és el nom del TipusAtribut que s'assignarà com a TipusAtribut que representa títols. 
     * @throws Excepcions.titolHaDeSerString El TipusAtribut que faci referència als títols dels items ha de ser del tipus de String
     */
    public void assignarTitol(String titol) throws Excepcions.titolHaDeSerString {
        for(Pair<Integer, String> key: tipusAtributs.keySet()) {
            if (key.second().equals(titol) && !tipusAtributs.get(key).getTipusDada().equals("String")) throw new Excepcions.titolHaDeSerString();
        }
        this.nomTitol = titol;
    }

    /**
     * Assigna el TipusAtribut que representa els atributs que fan referència als id dels items com a tal
     * @param nomTipusAtributID és el nom del TipusAtribut que s'assignarà com a TipusAtribut que representa ids. 
     * @throws Excepcions.idHaDeSerInteger El TipusAtribut que faci referència als ids dels items ha de ser del tipus de Integer
     */
    public void setTipusAtributId(String nomTipusAtributID) throws Excepcions.idHaDeSerInteger {
        for(Pair<Integer, String> key: tipusAtributs.keySet()) {
            if (key.second().equals(nomTipusAtributID) && !tipusAtributs.get(key).getTipusDada().equals("Int")) throw new Excepcions.idHaDeSerInteger();
        }
        this.nomId = nomTipusAtributID;
    }

    /**
     * Assigna el path on es troba la base de dades en que aquesta classe té la responsabilitat de fer-ne de configuració de els TipusAtributs 
     * @param path és el path
     */
    public void assignarPath(String path) {
        this.path = path;
    }

    /**
     * @return Retorna la configuració dels TipusAtribut que conté aquesta classe. (nom de TipusAtributs, i tipus i si son o no ho són de calculables)
     */
    public Map<String, Pair<String, Boolean>> getDadesPerFitxer() {
        Map<String, Pair<String, Boolean>> dadesPerFitxer = new TreeMap<>();
        for(Pair<Integer, String> key: tipusAtributs.keySet()) {
            dadesPerFitxer.put(key.second(), new Pair<>(tipusAtributs.get(key).getTipusDada(), tipusAtributs.get(key).getCalculable()));
        }
        return dadesPerFitxer;
    }

    /**
     * @return Retorna la configuració dels TipusAtribut que conté aquesta classe. (nom de TipusAtributs, i tipus i si son o no ho són de calculables)
     */
    public HashMap<String, String> getTipusTipusAtributs() {
        HashMap<String, String> tipus = new HashMap<>();
        for(Pair<Integer, String> key: tipusAtributs.keySet()) {
            tipus.put(key.second(), tipusAtributs.get(key).getTipusDada());
        }
        return tipus;
    }

    /**
     * Afegeix els TipusAtributs que té definits un ítem (és a dir dels quals un item té atributs) a la configuració
     * @param item és l'ítem
     * @throws Excepcions.AtributNul si l'atribut és nul lògicament hi haurà una exepcio
     */
    public void afegirAtributsItem(Item item) throws Excepcions.AtributNul {
        for (Atribut atr: item.getAtributs()) {
            String nomTipus = atr.getTipusAtribut().getNom();
            for (Pair<Integer, String> key: tipusAtributs.keySet()) {
                if (tipusAtributs.get(key).getNom().equals(nomTipus)) {
                    tipusAtributs.get(key).afegirAtribut(atr);
                }
                break;
            }
        }
    }

    /**
     * Elimina els TipusAtributs que té definits un ítem (és a dir dels quals un item té atributs) a la configuració
     * @param item és l'ítem
     * @throws Excepcions.AtributNul si l'atribut és nul lògicament hi haurà una exepcio
     */
    public void eliminarAtributsItem(Item item) throws Excepcions.AtributNul {
        for (Atribut atr: item.getAtributs()) {
            String nomTipus = atr.getTipusAtribut().getNom();
            for (Pair<Integer, String> key: tipusAtributs.keySet()) {
                if (tipusAtributs.get(key).getNom().equals(nomTipus)) {
                    tipusAtributs.get(key).eliminaAtribut(atr);
                }
                break;
            }
        }
    }
}
