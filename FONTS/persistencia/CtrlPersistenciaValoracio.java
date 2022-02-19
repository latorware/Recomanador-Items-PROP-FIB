package persistencia;

import java.io.*;
import java.util.*;

public class CtrlPersistenciaValoracio {

    /**
     * Equival a la primera linia de l'arxiu ratings.db.csv.
     * ex: (0, userId), (1, itemId), (2, rating), (3, comentari)
     * Map<posicioColumna, nomColumna>.
     */
    private final Map<Integer, String> header = new TreeMap<>();

    /**
     * Instancia de la propia classe. Propietat singleton.
     */
    private static CtrlPersistenciaValoracio instance;

    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlPersistenciaValoracio getInstance() {
        if (instance == null) {
            instance = new CtrlPersistenciaValoracio();
        }
        return instance;
    }

    /**
     * Inicialitza el controlador.
     * Llegeix la primera linia de l'arxiu ratings.db.csv i inicialitza header.
     * Crea el fitxer valoracionsUA.csv.
     */
    public void inicialitzarControlador(String path) {
        detectarHeader(path + "ratings.db.csv");
        crearFitxerValoracionsUA(path + "valoracionsUA.csv");
    }

    /**
     * Crea el fitxer valoracions.csv.
     * Inserta a la primera linia "header.get(0) + "," + header.get(1) + "," + header.get(2) + "," + header.get(3)".
     */
    private void crearFitxerValoracionsUA(String path) {
        FileWriter fitxer = null;
        PrintWriter pw;
        try {
            fitxer = new FileWriter(path);
            pw = new PrintWriter(fitxer);

            pw.println(header.get(0) + "," + header.get(1) + "," + header.get(2) + "," + header.get(3));

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
     * Llegeix la primera linia de l'arxiu ratings.db.csv i inicialitza header.
     */
    public void detectarHeader(String path) {
        BufferedReader bufferLectura = null;
        try {
            bufferLectura = new BufferedReader(new FileReader(path));
            String linia = bufferLectura.readLine();
            String[] paraules = linia.split(",");
            for (int i = 0; i < 3; ++i) {
                header.put(i, paraules[i]);
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
        header.put(3, "comentari");
    }

    /**
     * Afegeix la valoracio de l'usuari amb id = userId de l'item amb id = itemId a l'arxiu valoracionsUA.csv.
     */
    public void afegirValoracio(String path, Integer userId, Integer rating, String comentari, Integer itemId) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(path, true));
        int i = 0;
        for (Integer key : header.keySet()) {
            switch (header.get(key)) {
                case "userId":
                    if (i == 0) output.write(userId.toString());
                    else output.write("," + userId.toString());
                    break;
                case "itemId":
                    if (i == 0) output.write(itemId.toString());
                    else output.write("," + itemId.toString());
                    break;
                case "rating":
                    if (i == 0) output.write(rating.toString());
                    else output.write("," + rating.toString());
                    break;
                case "comentari":
                    if (comentari != null) output.write("," + comentari);
            }
            ++i;
        }
        output.write("\n");
        output.close();
    }

    /**
     * Elimina les valoracions, de l'arxiu valoracionsUA.csv, segons tipus:
     * Si tipus = "userId", elimina totes les valoracions relacionades amb l'usuari id = idUser.
     * Si tipus = "itemId", elimina totes les valoracions relacionades amb l'item id = idItem.
     * Si tipus = "valoracio", elimina la valoracio relacionada amb l'usuari id = idUser i item id = idItem.
     */
    public void eliminarValoracio(String path, String tipus, int idUser, Integer idItem) {
        try {
            File inFile = new File(path);
            //Construccio del nou fitxer que es fara rename a l'original
            File tempFile = new File(inFile.getAbsolutePath() + ".temp");
            BufferedReader br = new BufferedReader(new FileReader(path));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            int columna = 0;
            switch (tipus) {
                case "userId":
                    for (Integer key : header.keySet()) {
                        if (header.get(key).equals("userId")) columna = key;
                    }
                    actualitzarFitxerPerItemUsuari(inFile, tempFile, br, pw, columna, idUser);
                    break;
                case "itemId":
                    for (Integer key : header.keySet()) {
                        if (header.get(key).equals("itemId")) columna = key;
                    }
                    actualitzarFitxerPerItemUsuari(inFile, tempFile, br, pw, columna, idItem);
                    break;
                case "valoracio":
                    int columnaU = 0;
                    int columnaI = 0;
                    for (Integer key : header.keySet()) {
                        if (header.get(key).equals("itemId")) columnaI = key;
                        if (header.get(key).equals("userId")) columnaU = key;
                    }
                    actualitzarFitxerPerValoracio(idUser, idItem, inFile, tempFile, br, pw, columnaI, columnaU);
                    break;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Elimina la valoracio feta per l'usuari amb id = idUser a l'item amb id = idItem de l'arxiu valoracionsUA.csv.
     * @param columnaI representa la posicio de la columna on estan els id dels items.
     * @param columnaU representa la posicio de la columna on estan els id dels usuaris.
     */
    private void actualitzarFitxerPerValoracio(int idUser, Integer idItem, File inFile, File tempFile, BufferedReader br, PrintWriter pw, int columnaI, int columnaU) throws IOException {
        String linia;
        //Llegir des de l'original i escriure al nou
        int liniaActual = 0;
        while ((linia = br.readLine()) != null) {
            String[] liniaPartida = linia.split(",");
            if (liniaActual > 0 && Integer.parseInt(liniaPartida[columnaU]) != idUser && Integer.parseInt(liniaPartida[columnaI]) != idItem || liniaActual == 0) {//si es l'element a borrar no l'escribim
                pw.println(linia);
                pw.flush();
            }
            ++liniaActual;
        }
        pw.close();
        br.close();
        //Eliminar l'arxiu original
        if (!inFile.delete()) {
            System.out.println("No s'ha pogut eliminar el fitxer");
            return;
        }
        //Rename del nou fitxer
        if (!tempFile.renameTo(inFile)) {
            System.out.println("No s'ha pogut renombrar el fitxer");
        }
    }

    /**
     * Elimina les valoracions que tinguin relacio amb el id = id de l'arxiu valoracionsUA.csv.
     * @param columna representa la posicio de la columna on esta el id.
     */
    private void actualitzarFitxerPerItemUsuari(File inFile, File tempFile, BufferedReader br, PrintWriter pw, int columna, int id) throws IOException {
        String linia;
        //Llegir des de l'original i escriure al nou
        int liniaActual = 0;
        while ((linia = br.readLine()) != null) {
            String[] liniaPartida = linia.split(",");
            if (liniaActual > 0 && Integer.parseInt(liniaPartida[columna]) != id || liniaActual == 0) {//si es l'element a borrar no l'escribim
                pw.println(linia);
                pw.flush();
            }
            ++liniaActual;
        }
        pw.close();
        br.close();
        //Eliminar l'arxiu original
        if (!inFile.delete()) {
            System.out.println("No s'ha pogut eliminar el fitxer");
            return;
        }
        //Rename del nou fitxer
        if (!tempFile.renameTo(inFile)) {
            System.out.println("No s'ha pogut renombrar el fitxer");
        }
    }
}
