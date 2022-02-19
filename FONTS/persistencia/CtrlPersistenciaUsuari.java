package persistencia;

import java.io.*;
import java.util.Set;

public class CtrlPersistenciaUsuari {

    /**
     * Instancia de la propia classe. Propietat singleton.
     */
    private static CtrlPersistenciaUsuari instance;

    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlPersistenciaUsuari getInstance() {
        if (instance == null) {
            instance = new CtrlPersistenciaUsuari();
        }
        return instance;
    }

    /**
     * Inicialitza el controlador.
     * Crea el fitxer usuarisActius.csv.
     */
    public void inicialitzarControlador(String path) {
        crearFitxerUsuarisActius(path);
    }

    /**
     * Crea el fitxer usuarisActius.csv.
     * Inserta a la primera linia de l'arxiu usuarisActius.csv "idUsuari,nom,contrasenya,itemsCreats".
     */
    private void crearFitxerUsuarisActius(String path) {
        FileWriter fitxer = null;
        PrintWriter pw;
        try {
            fitxer = new FileWriter(path);
            pw = new PrintWriter(fitxer);

            pw.println("idUsuari,nom,contrasenya,itemsCreats");

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
     * Afegeix l'usuari actiu amb id = idUsuariA, nom = nom, contrasenya = contrasenya a l'arxiu usuarisActius.csv.
     */
    public void afegirUsuariActiu(String path, Integer idUsuariA, String nom, String contrasenya) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(path, true));
        output.write(idUsuariA + "," + nom + "," + contrasenya + "," + "\n");
        output.close();
    }

    /**
     * Elimina l'usuari actiu amb id = idUsuariA de l'arxiu usuarisActius.csv.
     */
    public void eliminarUsuariActiu(String path, Integer idUsuariA) {
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
                if (liniaActual > 0 && Integer.parseInt(liniaPartida[0]) != idUsuariA || liniaActual == 0) {//si es l'element a borrar no l'escribim
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
     * Afegeix l'item creat ,per l'usuari actiu amb id = idUsuari, al conjunt d'items creats del propi usuari de l'arxiu usuarisActius.csv.
     * @param numItemsCreats conjunt d'id dels items creats per l'usuari.
     */
    public void afegirItem(int idUsuari, Set<Integer> numItemsCreats, String path) {
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
                    pw.print(linia);
                    pw.flush();
                }
                else if (liniaActual > 0 && Integer.parseInt(liniaPartida[0]) == idUsuari) {
                    String[] paraules = linia.split(",");
                    pw.print(paraules[0] + "," + paraules[1] + "," + paraules[2] + ",");
                    int i = 0;
                    for (Integer id : numItemsCreats) {
                        if (i == 0) pw.print(id);
                        else pw.print(";" + id);
                        ++i;
                    }
                }
                pw.print("\n");
                ++liniaActual;
            }
            pw.close();
            br.close();
            //Eliminar l'arxiu original
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            //Rename del nou fitxer
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename file");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Elimina l'item creat, per l'usuari actiu amb id = idUsuari, al conjunt d'items creats del propi usuari de l'arxiu usuarisActius.csv.
     * @param numItemsCreats conjunt d'id dels items creats per l'usuari.
     */
    public void eliminarItem(int idUsuari, Set<Integer> numItemsCreats, int idItem, String path) {
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
                    pw.print(linia);
                    pw.flush();
                }
                else if (liniaActual > 0 && Integer.parseInt(liniaPartida[0]) == idUsuari) {
                    pw.print(linia);
                    int i = 0;
                    for (Integer id : numItemsCreats) {
                        if (id != idItem) {
                            if (i == 0) pw.print(id);
                            else pw.print(";" + id);
                            ++i;
                        }
                    }
                }
                pw.print("\n");
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
