package persistencia;

import domini.Excepcions;
import utils.Pair;

import java.io.*;
import java.util.*;

public class CtrlPersistenciaConfiguracio {

    /**
     * Instancia de la propia classe. Propietat singleton.
     */
    private static CtrlPersistenciaConfiguracio instance;

    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlPersistenciaConfiguracio getInstance() {
        if (instance == null) {
            instance = new CtrlPersistenciaConfiguracio();
        }
        return instance;
    }

    /**
     * Inicialitza el controlador.
     * Crea el fitxer configuracio.txt.
     */
    public void inicialitzarControlador(String path, String nomTitol, String nomId, Map<String, Pair<String, Boolean>> tipusAtributs) {
        crearFitxerConfig(path, nomTitol, nomId, tipusAtributs);
    }

    /**
     * Comprova si hi ha hagut canvis entre el header de l'arxiu items.csv i els tipusAtributs guardats a l'arxiu configuracio.txt.
     * @return false en cas de que no hi hagi hagut canvis, true en cas contrari.
     */
    public boolean comprovarCanvisItems(String path, ArrayList<ArrayList<String>> nomTipusAtributs) {
        File arxiuConfig = new File(path + "configuracio.txt");
        if (!arxiuConfig.exists()) return true;
        String nomId = getNomId(path + "configuracio.txt");
        Map<String, Pair<String, Boolean>> mapTipusAtribut = new TreeMap<>(getTipusAtributs(path + "configuracio.txt"));
        for (String nom : nomTipusAtributs.get(0)) {
            if (!mapTipusAtribut.containsKey(nom) && !nom.equals(nomId)) return true;
        }
        return mapTipusAtribut.size() > nomTipusAtributs.get(0).size();
    }

    /**
     * Comprova que existeixin els arxius items.csv i ratings.db.csv al directori = path.
     */
    public void comprovacioFitxers(String path) throws Excepcions.ArxiuRatingsDBNoExisteix, Excepcions.BaseDeDadesNoExisteix, Excepcions.ArxiuItemsNoExisteix {
        File arxiuI = new File(path + "items.csv");
        File arxiuR = new File(path + "ratings.db.csv");
        if (!arxiuI.exists() && !arxiuR.exists()) throw new Excepcions.BaseDeDadesNoExisteix();
        else if (!arxiuI.exists()) throw new Excepcions.ArxiuItemsNoExisteix();
        else if (!arxiuR.exists()) throw new Excepcions.ArxiuRatingsDBNoExisteix();
    }

    /**
     * Crea l'arxiu configuracio.txt.
     * Inserta a la primera linia de l'arxiu configuracio.txt "nom,tipusDada,calculable".
     */
    public void crearFitxerConfig(String path, String nomTitol, String nomId, Map<String, Pair<String, Boolean>> tipusAtributs) {
        FileWriter fitxer = null;
        PrintWriter pw;
        try {
            fitxer = new FileWriter(path);
            pw = new PrintWriter(fitxer);
            pw.println(nomId);
            pw.println(nomTitol);
            pw.println("nom,tipusDada,calculable");
            for (String key : tipusAtributs.keySet()) {
                pw.write(key + "," + tipusAtributs.get(key).first() + "," + tipusAtributs.get(key).second() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fitxer)
                    fitxer.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Retorna els tipusAtribut guardats a l'arxiu configuracio.txt.
     * @return Map<nomTipus, Pair<tipusDada, calculable>>.
     */
    public Map<String, Pair<String, Boolean>> getTipusAtributs(String path) {
        BufferedReader bufferLectura = null;
        Map<String, Pair<String, Boolean>> tipusAtributs = new HashMap<>();
        try {
            bufferLectura = new BufferedReader(new FileReader(path));
            String linia = bufferLectura.readLine();
            int liniaActual = 0;
            while (linia != null) {
                if (liniaActual > 2) {
                    String[] paraules = linia.split(",");
                    tipusAtributs.put(paraules[0], new Pair<>(paraules[1], Boolean.valueOf(paraules[2])));
                }
                linia = bufferLectura.readLine();
                ++liniaActual;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tipusAtributs;
    }

    /**
     * Retorna el nom de la columna d'items.csv que identifica els titol dels items.
     * @return nomTitol.
     */
    public String getNomTitol(String path) {
        BufferedReader bufferLectura = null;
        String nomTitol = "";
        try {
            bufferLectura = new BufferedReader(new FileReader(path));
            nomTitol = bufferLectura.readLine();
            nomTitol = bufferLectura.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nomTitol;
    }

    /**
     * Retorna el nom de la columna d'items.csv que identifica els id dels items.
     * @return nomId.
     */
    public String getNomId(String path) {
        BufferedReader bufferLectura = null;
        String nomId = "";
        try {
            bufferLectura = new BufferedReader(new FileReader(path));
            nomId = bufferLectura.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nomId;
    }
}
