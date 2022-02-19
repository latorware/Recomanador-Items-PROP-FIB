package persistencia;

import domini.TipusAtribut;
import domini.UActiu;
import utils.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CtrlPersistencia {

    /**
     * Instancia de la propia classe. Propietat singleton.
     */
    private static CtrlPersistencia instance;

    /**
     * Referencia al ctrlPItem.
     */
    private CtrlPersistenciaItem ctrlPItem;

    /**
     * Referencia al ctrlPRecomanacio.
     */
    private CtrlPersistenciaRecomanacio ctrlPRecomanacio;

    /**
     * Referencia al ctrlPConfiguracio.
     */
    private CtrlPersistenciaConfiguracio ctrlPConfiguracio;

    /**
     * Referencia al ctrlPUsuari.
     */
    private CtrlPersistenciaUsuari ctrlPUsuari;

    /**
     * Referencia al ctrlPValoracio.
     */
    private CtrlPersistenciaValoracio ctrlPValoracio;

    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlPersistencia getInstance() {
        if (instance == null) {
            instance = new CtrlPersistencia();
        }
        return instance;
    }

    /**
     * Constructora.
     */
    public CtrlPersistencia() {
        incialitzarCtrlPersistencia();
    }

    /**
     * Inicialitza tots els controladors adjacents.
     */
    public void incialitzarCtrlPersistencia() {
        ctrlPItem = CtrlPersistenciaItem.getInstance();
        ctrlPRecomanacio = CtrlPersistenciaRecomanacio.getInstance();
        ctrlPConfiguracio = CtrlPersistenciaConfiguracio.getInstance();
        ctrlPUsuari = CtrlPersistenciaUsuari.getInstance();
        ctrlPValoracio = CtrlPersistenciaValoracio.getInstance();
    }

    /**
     * Crida al metode comprovacioFitxers del ctrlPersistenciaConfiguracio.
     *
     * @param path direccio de l'arxiu configuracio.txt.
     */
    public void validarPath(String path) throws Exception {
        ctrlPConfiguracio.comprovacioFitxers(path);
    }

    /**
     * Inicialitza tots els controladors adjacents.
     *
     * @param path           direccio de la carpeta.
     * @param nomTitol       representa el nom pel qual es fa referencia als titols dels items.
     * @param nomId          representa el nom pel qual es fa referencia als ids dels items.
     * @param dadesPerFitxer dades de tots els TipusAtributs (nom,tipusDada,calculable)
     */
    public void inicialitzarControladors(String path, String nomTitol, String nomId, Map<String, Pair<String, Boolean>> dadesPerFitxer) {
        ctrlPRecomanacio.inicialitzarControlador(path + "recomanacions.csv");
        ctrlPUsuari.inicialitzarControlador(path + "usuarisActius.csv");
        ctrlPValoracio.inicialitzarControlador(path);
        ctrlPConfiguracio.inicialitzarControlador(path + "configuracio.txt", nomTitol, nomId, dadesPerFitxer);
    }

    /**
     * Crida al metode comprovarCanvisItems de ctrlPersistenciaConfiguracio.
     * @return boolea indicant si hi ha hagut un canvi entre l'arxiu d'items i l'arxiu de configuracio
     */
    public boolean canvisTipusAtributsArxiuItems(String path) {
        return ctrlPConfiguracio.comprovarCanvisItems(path, llegeixLiniesArxiu(path + "items.csv", 1));
    }

    /**
     * Retorna les 3 primeres linies de l'arxiu amb path = path, per identificar les paraules en una linia s'utilitza la funcio split() que separa tots els elements entre el SEPARARDOR
     * @return ArrayList de tamany = 3, on cada element es un ArrayList de Strings
     */
    public ArrayList<ArrayList<String>> llegeixLiniesArxiu(String path, int num) {
        ArrayList<ArrayList<String>> linies = new ArrayList<>();
        BufferedReader bufferLectura = null;
        try {
            // Obre el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader(path));
            // Llegeix una linia de l'arxiu
            String linia = bufferLectura.readLine();
            int i = 0;
            while (i < num) {
                // Sepapar la linia llegida amb el separador definit previament
                String[] paraules = linia.split(",");
                ArrayList<String> l = new ArrayList<>(Arrays.asList(paraules));
                linies.add(l);
                // Tornar a llegir una altra linia del fitxer
                linia = bufferLectura.readLine();
                ++i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Tancar el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return linies;
    }

    /**
     * Retorna totes les linies de l'arxiu amb path = path, per identificar les paraules en una linia s'utilitza la funcio split() que separa tots els elements entre el SEPARARDOR
     * @return ArrayList de tamany = linies que te l'arxiu, on cada element es un ArrayList de Strings
     */
    public ArrayList<ArrayList<String>> llegeixArxiu(String path) {
        BufferedReader bufferLectura = null;
        ArrayList<ArrayList<String>> linies = new ArrayList<>();
        try {
            // Obrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader(String.valueOf(path)));
            // Llegir una linia de l'arxiu
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Separar la linia llegida amb el separador definit previament
                String[] campos = linea.split(",");
                ArrayList<String> l = new ArrayList<>(Arrays.asList(campos));
                linies.add(l);
                // Tornar a llegir una altra linia del fitxer
                linea = bufferLectura.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Tancar el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return linies;
    }

    /**
     * Comunica al CtrlPersistenciaValoracio perque afegeixi una valoracio de l'usuari actiu al fitxer de valoracions.
     */
    public void afegirValoracio(String path, int idUsuariA, Integer puntuacio, String comentaris, int idItem) throws IOException {
        ctrlPValoracio.afegirValoracio(path, idUsuariA, puntuacio, comentaris, idItem);
    }

    /**
     * Comunica al CtrlPersistenciaUsuari perque afegeixi l'usuari actiu al fitxer d'usuaris.
     */
    public void afegirUsuariActiu(String path, UActiu usuariActiu) throws IOException {
        ctrlPUsuari.afegirUsuariActiu(path, usuariActiu.getId(), usuariActiu.getNom(), usuariActiu.getContrasenya());
    }

    /**
     * Comunica al CtrlPersistenciaValoracio perque elimini les valoracions de l'usuari actiu o de l'item al fitxer de valoracions.
     */
    public void eliminarValoracio(String path, int idUser, int idItem) {
        ctrlPValoracio.eliminarValoracio(path, "valoracio", idUser, idItem);
    }

    /**
     * Comunica als CtrlPersistenciaUsuari, CtrlPersistenciaRecomanacio I CtrlPersistenciaValoracio perque eliminin tot tipus de dada on estigui relacionat l'usuari actiu.
     */
    public void eliminarUA(String path, int idUser) {
        ctrlPUsuari.eliminarUsuariActiu(path + "usuarisActius.csv", idUser);
        ctrlPRecomanacio.eliminarRecomanacionsUsuari(path + "recomanacions.csv", idUser);
        ctrlPValoracio.eliminarValoracio(path + "valoracionsUA.csv", "userId", idUser, idUser);
    }

    /**
     * Comunica als CtrlPersistenciaItem, CtrlPersistenciaRecomanacio, CtrlPersistenciaValoracio, CtrlPersistenciaUsuari perque eliminin tot tipus de dada on estigui relacionat l'item.
     */
    public void eliminarItem(int idUsuari, Set<Integer> numItemsCreats, String path, int idItem, String nomId) throws IOException {
        ctrlPItem.eliminarItem(path + "items.csv", idItem, nomId, llegeixLiniesArxiu(path + "items.csv", 1));
        //ctrlPRecomanacio.eliminarRecomanacioItem(path + "\\recomanacions.csv", idItem);
        ctrlPValoracio.eliminarValoracio(path + "valoracionsUA.csv", "itemId", 0, idItem);
        ctrlPUsuari.eliminarItem(idUsuari, numItemsCreats, idItem, path + "usuarisActius.csv");
    }

    /**
     * Comunica als CtrlPersistenciaItem i CtrlPersistenciaUsuari perque afegeixin l'item a les seves bases de dades.
     */
    public void afegirItem(int idUsuari, Set<Integer> numItemsCreats, int idItem, HashMap<String, String> atributs, Map<Pair<Integer, String>, TipusAtribut> tipusAtributs, String path, String nomId) throws IOException {
        ctrlPItem.afegirItem(idItem, atributs, tipusAtributs, path + "items.csv", llegeixLiniesArxiu(path + "items.csv", 1), nomId);
        ctrlPUsuari.afegirItem(idUsuari, numItemsCreats, path + "usuarisActius.csv");
    }

    /**
     * Comunica al CtrlPersistenciaRecomanacio que guardi els items recomanats, per cada algorisme, per l'usuari actiu, a les seves bases de dades.
     */
    public void guardarRecomanacioUsuariActiu(int idUsuari, ArrayList<Integer> recomanacionsCOL, ArrayList<Integer> recomanacionsCB, ArrayList<Integer> recomanacionsHybrid, String path) throws IOException {
        ctrlPRecomanacio.afegirRecomanacionsUsuari(path, idUsuari, recomanacionsCOL, recomanacionsCB, recomanacionsHybrid);
    }

    /**
     * Comunica al CtrlPersistenciaValoracio perque detecti la primera linia de l'arxiu ratings.db.csv.
     */
    public void setHeaderVal(String path) {
        ctrlPValoracio.detectarHeader(path);
    }

    /**
     * Retorna els tipusAtributs guardats a l'arxiu configuracio.txt.
     * @return Map<nomTipusAtribut, Pair<tipusDada, calculable>>.
     */
    public Map<String, Pair<String, Boolean>> getTipusAtribut(String path) {
        return ctrlPConfiguracio.getTipusAtributs(path);
    }

    /**
     * Retorna el nom de la columna d'items.csv que identifica els id dels items.
     * @return nomId.
     */
    public String getNomId(String path) {
        return ctrlPConfiguracio.getNomId(path);
    }

    /**
     * Retorna el nom de la columna d'items.csv que identifica els titol dels items.
     * @return nomTitol.
     */
    public String getNomTitol(String path) {
        return ctrlPConfiguracio.getNomTitol(path);
    }
}
