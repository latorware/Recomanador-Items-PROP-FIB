package persistencia;


import domini.TipusAtribut;
import utils.Pair;

import java.io.*;
import java.util.*;

public class CtrlPersistenciaItem {

    /**
     * Instancia de la propia classe. Propietat singleton.
     */
    private static CtrlPersistenciaItem instance;

    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlPersistenciaItem getInstance() {
        if (instance == null) {
            instance = new CtrlPersistenciaItem();
        }
        return instance;
    }

    /**
     * Elimina l'item amb id = idItem de l'arxiu items.csv.
     * @param header equival a la primera linia de l'arxiu items.csv. S'utilitza per saber on es situa la columna amb nomId.
     */
    public void eliminarItem(String path, int idItem, String nomId, ArrayList<ArrayList<String>> header) throws IOException {
        File inFile = new File(path);
        File tempFile = new File(inFile.getAbsolutePath() + ".temp");
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
        BufferedReader br;
        int posicioId = getPosicioId(header, nomId);
        try {
            // Obrir el .csv en buffer de lectura
            br = new BufferedReader(new FileReader(path));
            // Llegir una linia de l'arxiu
            String linia = br.readLine();
            boolean quotesLinia = false;
            int columna = 0;
            int numLinia = 0;
            while (linia != null) {
                boolean eliminar = false;
                if (numLinia > 0) {
                    String[] dades = linia.split(",");// Separar la linia llegida amb el separador definit previament
                    ArrayList<String> l = new ArrayList<>(Arrays.asList(dades));
                    boolean quotes = false;
                    for (String paraula : l) {
                        //System.out.println(paraula);
                        if (columna == posicioId) {
                            if (paraula.equals(Integer.toString(idItem))) {
                                eliminar = true;
                            }
                        }
                        else if (!paraula.equals("")) {
                            char lastCharacter = paraula.charAt(paraula.length() - 1);
                            char firstChar = paraula.charAt(0);
                            int charQuotes = comptarQuotes(paraula);
                            if ((firstChar != lastCharacter && firstChar == '"' && !quotes) || (firstChar == lastCharacter && firstChar == '"' && charQuotes % 2 == 1 && !quotes)) {
                                quotes = true;
                                quotesLinia = true;
                            }
                            else if (paraula.contains("\"") && quotes || paraula.contains("\"") && !quotes && quotesLinia) {//Paurala entre " " amb "
                                if (charQuotes % 2 == 1) {
                                    quotes = false;
                                    quotesLinia = false;
                                    ++columna;
                                }
                            }
                            else if (!paraula.contains("\"") && quotes || !paraula.contains("\"") && !quotes && quotesLinia) {
                                pw.println(linia);
                                pw.flush();
                            }
                            else ++columna;

                        }
                        else ++columna;
                    }
                    if (!eliminar) {
                        pw.println(linia);
                        pw.flush();
                    }
                    if (!quotesLinia) {
                        columna = 0;
                    }
                    // Tornar a llegir una altra linia del fitxer
                }
                else {
                    pw.println(linia);
                    pw.flush();
                }
                ++numLinia;
                linia = br.readLine();
            }
            pw.close();
            br.close();

            if (!inFile.delete()) {
                System.out.println("No s'ha pogut eliminar el fitxer");
                return;
            }
            //Rename del nou fitxer
            if (!tempFile.renameTo(inFile)) {
                System.out.println("No s'ha pogut renombrar el fitxer");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Calcula les repeticions del caracter "\"" dins d'un string.
     * @return numero de repeticions del caracter "\"".
     */
    private static int comptarQuotes(String paraula) {
        int quotesCount = 0;
        char temp;
        for (int i = 0; i < paraula.length(); i++) {
            temp = paraula.charAt(i);
            if (temp == '"')
                quotesCount++;
        }
        return quotesCount;
    }

    /**
     * Retorna la posicio de la columna, del header, amb nom = id.
     * @param header equival a la primera linia de l'arxiu items.csv. S'utilitza per saber on es situa la columna amb nomId.
     * @return posicio de id en l'ArrayList<ArrayList<String>> header.
     */
    private int getPosicioId(ArrayList<ArrayList<String>> header, String id) {
        int posicioId = 0;
        int columna = 0;
        for (ArrayList<String> strings : header) {
            for (String paraula : strings) {
                if (paraula.equals(id)) {
                    posicioId = columna;
                    break;
                }
                ++columna;
            }
        }
        return posicioId;
    }

    /**
     * Afegeix l'item amb id = idItem de l'arxiu items.csv.
     * @param atributs atributs de l'item. HashMap<nomTipusAtribut, dadaAtribut>.
     * @param header equival a la primera linia de l'arxiu items.csv. S'utilitza per saber on es situa la columna amb nomId.
     * @param nomId nom columna ids.
     */
    public void afegirItem(int idItem, HashMap<String, String> atributs, Map<Pair<Integer, String>, TipusAtribut> tipusAtributs, String path, ArrayList<ArrayList<String>> header, String nomId) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(path, true));
        int columna = 0;
        int posicioId = getPosicioId(header, nomId);
        for (; columna <= tipusAtributs.size(); ++columna) {
            for (Pair<Integer, String> key : tipusAtributs.keySet()) {
                if (columna == key.first() && columna != posicioId) {
                    if (atributs.containsKey(key.second())) {
                        if (columna > 0) {
                            output.write("," + atributs.get(key.second()));
                        }
                        else output.write(atributs.get(key.second()));
                    }
                    else {
                        if (columna > 0) output.write(",");
                    }
                }
            }
            if (columna == posicioId) {
                if (columna > 0) {
                    output.write("," + idItem);
                }
                else {
                    output.write(Integer.toString(idItem));
                }
            }
        }
        output.write("\n");
        output.close();
    }
}
