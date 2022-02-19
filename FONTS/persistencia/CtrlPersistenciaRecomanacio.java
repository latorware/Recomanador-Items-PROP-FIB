package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CtrlPersistenciaRecomanacio {

    /**
     * Instancia de la propia classe. Propietat singleton.
     */
    private static CtrlPersistenciaRecomanacio instance;

    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlPersistenciaRecomanacio getInstance() {
        if (instance == null) {
            instance = new CtrlPersistenciaRecomanacio();
        }
        return instance;
    }

    /**
     * Inicialitza el controlador.
     * Crea el fitxer recomanacions.csv.
     */
    public void inicialitzarControlador(String path) {
        crearFitxerRecomanacions(path);
    }

    /**
     * Crea el fitxer recomanacions.csv.
     * Inserta a la primera linia "idUsuari,algorisme,idItems".
     */
    public void crearFitxerRecomanacions(String path) {
        FileWriter fitxer = null;
        PrintWriter pw;
        try {
            fitxer = new FileWriter(path);
            pw = new PrintWriter(fitxer);

            pw.println("idUsuari,algorisme,idItems");

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
     * Elimina totes les recomanacions de l'usuari amb id = idUsuari de l'arxiu recomanacions.csv.
     */
    public void eliminarRecomanacionsUsuari(String path, Integer idUsuari) {//canviar a path default!!!!!!!
        try {
            File inFile = new File(path);
            //Construccio del nou fitxer que es fara rename a l'original
            File tempFile = new File(inFile.getAbsolutePath() + ".temp");
            BufferedReader br = new BufferedReader(new FileReader(path));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String linia;
            //Llegir des de l'original i escriure al nou
            int liniaActual = 0;
            while ((linia = br.readLine()) != null) {
                String[] liniaPartida = linia.split(",");
                if (liniaActual > 0 && Integer.parseInt(liniaPartida[0]) != idUsuari || liniaActual == 0) {//si es l'element a borrar no l'escribim
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Afegeix les recomanacions generades pels algorismes a l'usuari actiu amb id = idUsuari de l'arxiu recomanacions.csv.
     * @param recomanacionsCOL recomanacions generades per l'algorisme COLFiltering.
     * @param recomanacionsCB recomanacions generades per l'algorisme CBFiltering.
     * @param recomanacionsHybrid recomanacions generades per l'algorisme Hybrid.
     */
    public void afegirRecomanacionsUsuari(String path, int idUsuari, ArrayList<Integer> recomanacionsCOL, ArrayList<Integer> recomanacionsCB, ArrayList<Integer> recomanacionsHybrid) throws IOException {
        eliminarRecomanacionsUsuari(path, idUsuari);
        Writer output;
        output = new BufferedWriter(new FileWriter(path, true));
        for (int i = 0; i < 3; ++i) {
            output.write(idUsuari + ",");
            switch (i) {
                case 0:
                    output.write("COL" + ",");
                    escriureItems(output, recomanacionsCOL);
                    break;
                case 1:
                    output.write("CB" + ",");
                    escriureItems(output, recomanacionsCB);
                    break;
                case 2:
                    output.write("Hybrid" + ",");
                    escriureItems(output, recomanacionsHybrid);
                    break;
            }
            output.write("\n");
        }
        output.close();
    }

    /**
     * Donat un writer escriu totes les recomanacions(id item) separats per ";" a l'arxiu recomanacions.csv.
     */
    private void escriureItems(Writer output, ArrayList<Integer> recomanacions) throws IOException {
        int it = 0;
        for (Integer i : recomanacions) {
            if (it == 0) output.write(Integer.toString(i));
            else output.write(";" + i);
            ++it;
        }
    }

    /**
     * Elimina de totes les recomanacions l'item amb id = idItem de l'arxiu recomanacions.csv.
     */
    public void eliminarRecomanacioItem(String path, int idItem) {
        try {
            File inFile = new File(path);
            //Construccio del nou fitxer que es fara rename a l'original
            File tempFile = new File(inFile.getAbsolutePath() + ".temp");
            BufferedReader br = new BufferedReader(new FileReader(path));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String linia;
            //Llegir des de l'original i escriure al nou
            int liniaActual = 0;
            while ((linia = br.readLine()) != null) {
                String[] liniaPartida = linia.split(",");
                if (liniaActual > 0) {//si es l'element a borrar no l'escribim
                    ArrayList<String> idsItems = new ArrayList<>(List.of(liniaPartida[2].split(";")));
                    if (idsItems.contains(Integer.toString(idItem))) {
                        idsItems.remove(Integer.toString(idItem));
                        ArrayList<Integer> idsI = new ArrayList<>();
                        for (String item : idsItems) {
                            idsI.add(Integer.parseInt(item));
                        }
                        pw.print(liniaPartida[0] + "," + liniaPartida[1] + ",");
                        int it = 0;
                        for (Integer i : idsI) {
                            if (it == 0) pw.print(i);
                            else pw.print(";" + i);
                            ++it;
                        }
                        pw.print("\n");
                    }
                    else {
                        pw.println(linia);
                    }
                }
                else {
                    pw.println(linia);
                }
                pw.flush();
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
