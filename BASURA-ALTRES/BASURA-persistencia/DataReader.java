package persistencia;

import domini.*;
import utils.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.lang.Double;

public class DataReader
{
    public static final String SEPARADOR = ",";

    /** DOMINI
     * Mostra si un String té un valor negatiu
     * @return false en cas de que no tingui un valor negatiu, true en cas de que si tingui un valor negatiu
     */
    public static boolean Negative(String paraula) {
        if (!Objects.equals(paraula, "")) {
            return paraula.charAt(0) == '-';
        }
        return false;
    }

    /** DOMINI
     * Mostra si un String té un valor enter
     * @return false en cas de que no tingui un valor enter, true en cas de que si tingui un valor enter
     */
    public static boolean isInteger(String paraula) {
        if (!Objects.equals(paraula, "")) {
            for(int i = 0; i < paraula.length(); i++) {
                if(i == 0 && paraula.charAt(i) == '-') {
                    if(paraula.length() == 1) return false;
                    else continue;
                }
                if(paraula.charAt(i) > 57 || paraula.charAt(i) < 48) return false;
            }
            return true;
        }
        else return false;
    }

    /** DOMINI
     * Mostra si un String té un valor double
     * @return false en cas de que no tingui un valor double, true en cas de que si tingui un valor double
     */
    public static boolean isDouble(String paraula) {
        if (!Objects.equals(paraula, "")) {
            for(int i = 0; i < paraula.length(); i++) {
                if(i == 0 && paraula.charAt(i) == '-') {
                    if(paraula.length() == 1) return false;
                    else continue;
                }
                if (paraula.charAt(i) != 46) {
                    if (paraula.charAt(i) > 57 || paraula.charAt(i) < 48) return false;
                }
            }
            return true;
        }
        else return false;
    }

    /** DOMINI
     * Mostra si un String té un valor boolea
     * @return false en cas de que no tingui un valor boolea, true en cas de que si tingui un valor boolea
     */
    public static boolean isBoolean(String paraula) {
        return paraula.equals("False") || paraula.equals("false") || paraula.equals("True") || paraula.equals("true");
    }

    /** DOMINI
     * Retorna el valor boolea que conte el String
     * @return el valor del boolea dins del String
     */
    public static boolean transformarBoolean(String paraula) {
        return !paraula.equals("False") && !paraula.equals("false");
    }

    /** DOMINI
     * Retorna el numero de vegades que dins del string apareix el caracter \"
     * @return numero de vegades que apareix el caracter \"
     */
    public static int comptarQuotes(String paraula) {
        int quotesCount = 0;
        char temp;
        for (int i = 0; i < paraula.length(); i++) {
            temp = paraula.charAt(i);
            if (temp == '"')
                quotesCount++;
        }
        return quotesCount;
    }

    /** PERSISTENCIA
     * Retorna les 3 primeres linies de l'arxiu amb path = direccioArxiu, per identificar les paraules en una linia s'utilitza la funcio split() que separa tots els elements entre el SEPARARDOR
     * @return ArrayList de tamany = 3, on cada element es un ArrayList de Strings
     */
    public static ArrayList<ArrayList<String>> llegeixHeader(StringBuilder direccioArxiu) {
        ArrayList<ArrayList<String>> linies = new ArrayList<>();
        BufferedReader bufferLectura = null;
        try {
            // Obre el .csv en buffer de lectura
            String path = direccioArxiu.toString();
            bufferLectura = new BufferedReader(new FileReader(path));
            // Llegeix una linia de l'arxiu
            String linia = bufferLectura.readLine();
            int i = 0;
            while (i < 3) {
                // Sepapar la linia llegida amb el separador definit previament
                String[] paraules = linia.split(SEPARADOR);
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
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return linies;
    }

    /** PERSISTENCIA
     * Retorna totes les linies de l'arxiu amb path = direccioArxiu, per identificar les paraules en una linia s'utilitza la funcio split() que separa tots els elements entre el SEPARARDOR
     * @return ArrayList de tamany = linies que te l'arxiu, on cada element es un ArrayList de Strings
     * @param direccioArxiu
     */
    public static ArrayList<ArrayList<String>> llegeixArxiu(String direccioArxiu) {
        BufferedReader bufferLectura = null;
        ArrayList<ArrayList<String>> linies = new ArrayList<>();
        try {
            // Obrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader(String.valueOf(direccioArxiu)));
            // Llegir una linia de l'arxiu
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Separar la linia llegida amb el separador definit previament
                String[] campos = linea.split(SEPARADOR);
                ArrayList<String> l = new ArrayList<>(Arrays.asList(campos));
                linies.add(l);
                // Tornar a llegir una altra linia del fitxer
                linea = bufferLectura.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Tancar el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return linies;
    }

    /** DOMINI
     * Guarda els paths de cada arxiu als parametres: pathItems = path(items.csv), pathUsuaris = path(ratings.test.known.csv), i pathUsuarisUnknown = path(rating.test.unknown.csv)
     */
    public static void inicialitzarPaths(StringBuilder pathItems, StringBuilder pathUsuaris, StringBuilder pathUsuarisUnknown) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("Programa en execucio.\n" +
                           "Analitzant csv.\n" +
                           "Vols indicar el path dels arxius CSV? En cas de que no, utilitzarem els CSV que te el directori \"data\".\n" +
                           "Escriu \"Si\" o \"No\":");
        while (!input.equalsIgnoreCase("si") && !input.equalsIgnoreCase("no")) {
            input = scanner.nextLine();
        }
        if (input.equalsIgnoreCase("si")) {
            scanner = new Scanner(System.in);
            input = "";
            boolean correcteI = false;
            boolean correcteU = false;
            boolean correcteUnknown = false;
            System.out.println("Escriu el path del directori:");
            while (!correcteI && !correcteU && !correcteUnknown) {
                input = scanner.nextLine();
                File arxiuI = new File(input + "items.csv");
                File arxiuU = new File(input + "ratings.test.known.csv");
                File arxiuUnknown = new File(input + "ratings.test.unknown.csv");
                correcteI = arxiuI.exists();
                correcteU = arxiuU.exists();
                correcteUnknown = arxiuUnknown.exists();
                if (!correcteI) System.out.println("L'arxiu \"items.csv\" no existeix al path indicat.");
                if (!correcteU) System.out.println("L'arxiu \"ratings.test.know.csv\" no existeix al path indicat.");
                if (!correcteUnknown)
                    System.out.println("L'arxiu \"ratings.test.unknown.csv\" no existeix al path indicat.");
            }
            pathItems.append(input).append("items.csv");
            pathUsuaris.append(input).append("ratings.test.known.csv");
            pathUsuarisUnknown.append(input).append("ratings.test.unknown.csv");

        }
        else {
            pathItems.append("C:\\Users\\hpuca\\IdeaProjects\\subgrup-prop7-2\\FONTS\\data\\movies.sample\\250\\items.csv");
            pathUsuaris.append("C:\\Users\\hpuca\\IdeaProjects\\subgrup-prop7-2\\FONTS\\data\\movies.sample\\250\\ratings.test.known.csv");
            pathUsuarisUnknown.append("C:\\Users\\hpuca\\IdeaProjects\\subgrup-prop7-2\\FONTS\\data\\movies.sample\\250\\ratings.test.unknown.csv");
        }
    }

    /** DOMINI
     * Determina tots els tipusAtribut que conte el header del csv i omple el map tipusAtribut
     * Detecta la columna on estan els ids dels items
     * @return posicio de la columna on estan els ids dels items
     * @throws Excepcions.SaltDeLiniaExcepcio S'ha detectat un salt de linia a la segona linia del csv que impedeix la deduccio del tipus d'atribut
     */
    private static int lecturaHeader(Map<Pair<Integer, String>, TipusAtribut> tipusAtribut, StringBuilder pathItems) throws Excepcions.SaltDeLiniaExcepcio, Excepcions.NomNul, Excepcions.TipusDadaNul, Excepcions.IncompatibleTipusAtribut {
        int posicioId = 0;
        ArrayList<ArrayList<String>> header = llegeixHeader(pathItems);
        Iterator<ArrayList<String>> itLinies = header.listIterator();
        int columna = 1;
        int linia = 0;
        boolean quotesLinia = false;
        boolean acabat = false;
        boolean correcte = true;
        while (itLinies.hasNext()) {
            Iterator<String> itParaules = itLinies.next().listIterator();
            boolean quotes = false;
            while (itParaules.hasNext() && !acabat) {
                String paraula = itParaules.next();
                Iterator<ArrayList<String>> it = header.iterator();
                ArrayList<String> paraules = it.next();
                if (linia == 1) {
                    if (isBoolean(paraula)) {
                        Pair<Integer, String> index = new Pair<>(columna, paraules.get(columna));
                        tipusAtribut.put(index, new TipusAtribut(paraules.get(columna), "Bool"));
                        ++columna;
                    }
                    else if (isInteger(paraula) && !Negative(paraula)) {
                        if (!paraules.get(columna).equals("id")) {
                            Pair<Integer, String> index = new Pair<>(columna, paraules.get(columna));
                            tipusAtribut.put(index, new TipusAtribut(paraules.get(columna), "Int"));
                        }
                        else posicioId = columna;
                        ++columna;
                    }
                    else if (isDouble(paraula) && !Negative(paraula)) {
                        Pair<Integer, String> index = new Pair<>(columna, paraules.get(columna));
                        tipusAtribut.put(index, new TipusAtribut(paraules.get(columna), "Double"));
                        ++columna;
                    }
                    else if (!paraula.equals("") && !Negative(paraula)) {
                        char ultimCaracter = paraula.charAt(paraula.length() - 1);
                        char primerCaracter = paraula.charAt(0);
                        int charQuotes = comptarQuotes(paraula);
                        if ((primerCaracter != ultimCaracter && primerCaracter == '"' && !quotes) || (primerCaracter == ultimCaracter && primerCaracter == '"' && charQuotes % 2 == 1 && !quotes)) {//Primer amb " i ultim char sense "
                            quotes = true;
                            quotesLinia = true;
                        }
                        else if (paraula.contains("\"") && quotes) {
                            if (charQuotes % 2 == 1) {
                                quotes = false;
                                quotesLinia = false;
                                Pair<Integer, String> index = new Pair<>(columna, paraules.get(columna));
                                tipusAtribut.put(index, new TipusAtribut(paraules.get(columna), "String"));
                                ++columna;
                            }
                        }
                        else if (!(!paraula.contains("\"") && quotes)) {
                            Pair<Integer, String> index = new Pair<>(columna, paraules.get(columna));
                            tipusAtribut.put(index, new TipusAtribut(paraules.get(columna), "String"));
                            ++columna;
                        }
                    }
                    else {
                        Scanner scan = new Scanner(System.in);
                        String inp = "";
                        System.out.println("\nNo hem pogut deduir de quin tipus es " + paraules.get(columna) + ". Digues si es un \"Bool\", \"Int\", \"Double\" o \"String\":");
                        while (!inp.equalsIgnoreCase("Bool") && !inp.equalsIgnoreCase("Int") && !inp.equalsIgnoreCase("Double") && !inp.equalsIgnoreCase("String")) {
                            inp = scan.nextLine();
                        }
                        //tipusAtributArray.add(new TipusAtribut(paraules.get(columna), inp));
                        Pair<Integer, String> index = new Pair<>(columna, paraules.get(columna));
                        if (inp.equalsIgnoreCase("Bool")) tipusAtribut.put(index, new TipusAtribut(paraules.get(columna), "Bool"));
                        else if (inp.equalsIgnoreCase("Int")) tipusAtribut.put(index, new TipusAtribut(paraules.get(columna), "Int"));
                        else if (inp.equalsIgnoreCase("Double")) tipusAtribut.put(index, new TipusAtribut(paraules.get(columna), "Double"));
                        else tipusAtribut.put(index, new TipusAtribut(paraules.get(columna), "String"));
                        ++columna;
                    }
                }
                else if (linia == 2) {
                    if (quotesLinia) correcte = false;
                    acabat = true;
                }
            }
            columna = 0;
            ++linia;
        }

        if (!correcte) throw new Excepcions.SaltDeLiniaExcepcio();
        else System.out.println();

        return posicioId;
    }

    /** DOMINI
     * Llegeix tot el csv amb paht = pathItems
     * Per cada linia assignada a cada item els seus atributs, a mes a cada atribut li assigna el seu tipus
     * Omple el map items
     */
    private static void inicialitzarItems(Map<Pair<Integer, String>, TipusAtribut> tipusAtribut, Map<Integer, Item> items, StringBuilder pathItems, ArrayList<Integer> ids, int posicioId) throws Excepcions.TipusAtributNul, Excepcions.AtributNul, Excepcions.ValorAtributNul, Excepcions.UnexistingAtributsException, Excepcions.SetAtributsNul {
        ArrayList<ArrayList<String>> atributsItems = DataReader.llegeixArxiu(pathItems);
        Iterator<ArrayList<String>> linias = atributsItems.listIterator();
        int columna = 0;
        int linia = 0;
        int saveLinia = 0;
        String paraulaAAjuntar = "";
        boolean quotesLinia = false;
        ArrayList<String> arrayHeader = new ArrayList<>();
        while (linias.hasNext()) {
            Iterator<String> paraules = linias.next().listIterator();
            Set<Atribut> atributsItem = new HashSet<>();
            boolean quotes = false;
            boolean trobat = false;
            while (paraules.hasNext()) {
                String paraula = paraules.next();
                if (columna == posicioId) trobat = true;
                if (linia > 0) {
                    if (!Objects.equals(paraula, "") && columna <= tipusAtribut.size() && !trobat) {
                        Pair<Integer, String> key = new Pair<>(columna, arrayHeader.get(columna));
                        String tipus = tipusAtribut.get(key).getTipusDada();
                        /*
                        AQUI S'APLICA EL PATRO ESTRATEGIA --> HERENCIA A TIPUSATRIBUT
                         */
                        if (!Objects.equals(tipus, "String")) tipusAtribut.get(key).crearAtribut(paraula, tipusAtribut, key, atributsItem);
                        else {
                            char lastCharacter = paraula.charAt(paraula.length() - 1);
                            char firstChar = paraula.charAt(0);
                            int charQuotes = comptarQuotes(paraula);
                            if ((firstChar != lastCharacter && firstChar == '"' && !quotes) || (firstChar == lastCharacter && firstChar == '"' && charQuotes%2==1 && !quotes)) {//Primer amb " i ultim char sense "
                                quotes = true;
                                quotesLinia = true;
                                saveLinia = linia;
                                paraulaAAjuntar = paraula;
                            }
                            else if (!paraula.contains("\"") && quotes && quotesLinia || !paraula.contains("\"") && !quotes && quotesLinia) {//Paraules entre " "
                                paraulaAAjuntar = paraulaAAjuntar.concat("," + paraula);
                            }
                            else if (paraula.contains("\"") && quotes || paraula.contains("\"") && !quotes && quotesLinia) {//Paurala entre " " amb "
                                if (charQuotes%2==1) {
                                    quotes = false;
                                    quotesLinia = false;
                                    linia = saveLinia;
                                    paraulaAAjuntar = paraulaAAjuntar.concat("," + paraula);
                                    tipusAtribut.get(key).crearAtribut(paraulaAAjuntar, tipusAtribut, key, atributsItem);
                                    ++columna;
                                }
                                else {
                                    paraulaAAjuntar = paraulaAAjuntar.concat(paraula);
                                }
                            }
                            else {// ";"
                                String[] words = paraula.split(";");
                                ArrayList<String> llistaParaules = new ArrayList<>(Arrays.asList(words));
                                for (String p : llistaParaules) {
                                    tipusAtribut.get(key).crearAtribut(p, tipusAtribut, key, atributsItem);
                                }
                                ++columna;
                            }
                        }
                        /* Abans
                        if (Objects.equals(tipus, "Bool") && !quotesLinia) {
                            boolean b = transformarBoolean(paraula);
                            AtrBool nou = new AtrBool(tipusAtribut.get(key), b);
                            tipusAtribut.get(key).afegirAtribut(nou);//Afegir Atribut al Set<Atribut> del TIPUS
                            atributsItem.add(nou);
                            ++columna;
                        }
                        else if (Objects.equals(tipus, "Int") && !quotesLinia) {
                            int num = 0;
                            if (!Negative(paraula)) num = Integer.parseInt(paraula);
                            AtrInt nou = new AtrInt(tipusAtribut.get(key), num);
                            tipusAtribut.get(key).afegirAtribut(nou);
                            atributsItem.add(nou);
                            ++columna;
                        }
                        else if (Objects.equals(tipus, "Double") && !quotesLinia) {
                            double num = 0.0;
                            if (!Negative(paraula)) num = Double.parseDouble(paraula);
                            AtrDouble nou = new AtrDouble(tipusAtribut.get(key), num);
                            tipusAtribut.get(key).afegirAtribut(nou);
                            atributsItem.add(nou);
                            ++columna;
                        }
                        else {//és String
                            char lastCharacter = paraula.charAt(paraula.length() - 1);
                            char firstChar = paraula.charAt(0);
                            int charQuotes = comptarQuotes(paraula);
                            if ((firstChar != lastCharacter && firstChar == '"' && !quotes) || (firstChar == lastCharacter && firstChar == '"' && charQuotes%2==1 && !quotes)) {//Primer amb " i ultim char sense "
                                quotes = true;
                                quotesLinia = true;
                                saveLinia = linia;
                                paraulaAAjuntar = paraula;
                            }
                            else if (!paraula.contains("\"") && quotes && quotesLinia || !paraula.contains("\"") && !quotes && quotesLinia) {//Paraules entre " "
                                paraulaAAjuntar = paraulaAAjuntar.concat("," + paraula);
                            }
                            else if (paraula.contains("\"") && quotes || paraula.contains("\"") && !quotes && quotesLinia) {//Paurala entre " " amb "
                                if (charQuotes%2==1) {
                                    quotes = false;
                                    quotesLinia = false;
                                    linia = saveLinia;
                                    paraulaAAjuntar = paraulaAAjuntar.concat("," + paraula);
                                    AtrString nou = new AtrString(tipusAtribut.get(key), paraulaAAjuntar);
                                    tipusAtribut.get(key).afegirAtribut(nou);
                                    atributsItem.add(nou);
                                    ++columna;
                                }
                                else {
                                    paraulaAAjuntar = paraulaAAjuntar.concat(paraula);
                                }
                            }
                            else {// ";"
                                String[] words = paraula.split(";");
                                ArrayList<String> llistaParaules = new ArrayList<>(Arrays.asList(words));
                                for (String p : llistaParaules) {
                                    AtrString nou = new AtrString(tipusAtribut.get(key), p);
                                    tipusAtribut.get(key).afegirAtribut(nou);
                                    atributsItem.add(nou);
                                }
                                ++columna;
                            }
                        }
                         */
                    }
                    else if (trobat) {
                        int num = Integer.parseInt(paraula);
                        ids.add(num);
                        ++columna;
                        trobat=false;
                    }
                    else ++columna;
                }
                else if (linia == 0) arrayHeader.add(paraula);
            }
            //Creem l'item amb les relacions d'atributs
            if (linia > 0 && !quotesLinia) {
                int id = ids.get(linia - 1);
                Item nou = new Item(id, atributsItem);
                items.put(ids.get(linia - 1), nou);
            }
            ++linia;
            if (!quotesLinia) columna = 0;
        }
    }

    /**
     * Indica el rating mes gran
     * Omple el Map amb el header de l'arxiu
     * @return el rating mes gran de la llista de l'arxiu amb path = pathUsuaris
     */
    /*
    private static double getRatingMax(StringBuilder pathUsuaris, double ratingMax, Map<Integer, String> map) {
        ArrayList<ArrayList<String>> header = llegeixArxiu(pathUsuaris);
        Iterator<ArrayList<String>> iteratorLinies = header.listIterator();
        int linea = 0;
        while (iteratorLinies.hasNext()) {
            Iterator<String> itParaules = iteratorLinies.next().listIterator();
            double rating;
            int columna = 0;
            while (itParaules.hasNext()) {
                String word = itParaules.next();
                if (linea == 0) {
                    map.put(columna, word);
                }
                else {
                    if (map.get(columna).equals("rating")) {
                        rating = Double.parseDouble(word);
                        if (rating > ratingMax) ratingMax = rating;
                    }
                }
                ++columna;
            }
            ++linea;
        }
        return ratingMax;
    }
     */

    /**
     * Omple el HashMap unknown
     * Recalcula tots els ratings en base 5
     */
    /*
    private static void inicialitzarRatingsUnknown(HashMap<Integer, HashMap<Integer, Double>> unknown, StringBuilder pathUsuarisUnknown, double ratingMax, Map<Integer, String> HeaderUnknown) {
        ArrayList<ArrayList<String>> linies = llegeixArxiu(pathUsuarisUnknown);
        Iterator<ArrayList<String>> iteratorLinies = linies.listIterator();
        int linea = 0;
        while (iteratorLinies.hasNext()) {
            Iterator<String> itParaules = iteratorLinies.next().listIterator();
            int idUsuari = 0;
            int idItem = 0;
            double rating = 0;
            int columna = 0;
            while (itParaules.hasNext()) {
                String paraula = itParaules.next();
                if (linea != 0) {
                    if (HeaderUnknown.get(columna).equals("userId")) idUsuari = Integer.parseInt(paraula);
                    else if (HeaderUnknown.get(columna).equals("itemId")) idItem = Integer.parseInt((paraula));
                    else rating = Double.parseDouble(paraula);
                }
                ++columna;
            }
            //Afegim Usuari amb l'associació amb Valoració
            if (linea > 0) {
                if (!unknown.containsKey(idUsuari)) unknown.put(idUsuari, new HashMap<>());
                rating = (rating*5)/ratingMax;
                unknown.get(idUsuari).put(idItem, rating);
            }
            ++linea;
        }
    }

     */

    /**
     * Omple el Map usuaris, afegint a cada usuari les seves valoracions
     * Omple el Map valoracions
     * Recalcula tots els ratings en base 5
     */
    /*
    private static void inicialitzarRatingsKnown(Map<Integer, Item> items, Map<Integer, Usuari> usuaris, Map<Pair<Item, Usuari>, Valoracio> valoracions, StringBuilder pathUsuaris, double ratingMax, Map<Integer, String> mapCap) throws Excepcions.InvalidPuntuacioException, Excepcions.ItemNul, Excepcions.InvalidValoracioException {
        ArrayList<ArrayList<String>> header = llegeixArxiu(pathUsuaris);
        Iterator<ArrayList<String>> iteratorLinies = header.listIterator();
        int linea = 0;
        while (iteratorLinies.hasNext()) {
            Iterator<String> itParaules = iteratorLinies.next().listIterator();
            int idUsuari = 0;
            int idItem = 0;
            int columna = 0;
            double rating = 0;
            while (itParaules.hasNext()) {
                String paraula = itParaules.next();
                if (linea != 0) {
                    if (mapCap.get(columna).equals("userId")) idUsuari = Integer.parseInt(paraula);
                    else if (mapCap.get(columna).equals("itemId")) idItem = Integer.parseInt((paraula));
                    else rating = Double.parseDouble(paraula);
                }
                ++columna;
            }
            //Afegim Usuari amb l'associació amb Valoració
            if (linea > 0) {
                if (!usuaris.containsKey(idUsuari)) usuaris.put(idUsuari, new Usuari(idUsuari));
                rating = (rating*5) / ratingMax;
                Valoracio nova = new Valoracio(usuaris.get(idUsuari), rating, "", items.get(idItem));
                Pair<Item, Usuari> key = new Pair<>(items.get(idItem), usuaris.get(idUsuari));
                valoracions.put(key, nova);
                usuaris.get(idUsuari).addItemValoracio(nova);
            }
            ++linea;
        }
    }

     */

    /**
     * Carrega totes les dades llegides dels arxius de dades a totes les estructures passades per parametres
     */
    public static void carregaDades(Map<Pair<Integer, String>, TipusAtribut> tipusAtribut, Map<Integer, Item> items, Map<Integer, Usuari> usuaris, Map<Pair<Item, Usuari>, Valoracio> valoracions, HashMap<Integer, HashMap<Integer, Double>> unknown) throws Exception {
        /*
        StringBuilder pathItems = new StringBuilder();
        StringBuilder pathUsuaris = new StringBuilder();
        StringBuilder pathUsuarisUnknown = new StringBuilder();
        inicialitzarPaths(pathItems, pathUsuaris, pathUsuarisUnknown);
         */


        int posicioId = lecturaHeader(tipusAtribut, pathItems);
        ArrayList<Integer> ids = new ArrayList<>();
        inicialitzarItems(tipusAtribut, items, pathItems, ids, posicioId);

        /*
        double ratingMaxK = 0;
        double ratingMaxU = 0;
        Map<Integer, String> HeaderKnown = new HashMap<>();
        Map<Integer, String> HeaderUnknown = new HashMap<>();
        ratingMaxK = getRatingMax(pathUsuaris, ratingMaxK, HeaderKnown);
        ratingMaxU = getRatingMax(pathUsuarisUnknown, ratingMaxU, HeaderUnknown);

        inicialitzarRatingsKnown(items, usuaris, valoracions, pathUsuaris, Double.max(ratingMaxU, ratingMaxK), HeaderKnown);
        inicialitzarRatingsUnknown(unknown, pathUsuarisUnknown, Double.max(ratingMaxU, ratingMaxK), HeaderUnknown);
         */
    }
}

/*PREGUNTES:
    1. Com indicar com implemetem les llistes de strings
    2. Hi ha algun atribut que sempre el tindran tots els items?
        2.1 Si es no -> fins que no indiqui minim un, preguntarem again
        2.2 En cas de si -> pel moment nomes esta keywords, canviar si es un altre
 */