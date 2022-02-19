package domini.controladors;

import domini.*;
import persistencia.CtrlPersistencia;
import utils.Pair;

import java.io.IOException;
import java.util.*;

public class CtrlDomini {

    /**
     * Instancia de l'usuari actiu que ha iniciat sessio al sistema.
     */
    private UActiu usuariActiu;

    /**
     * Referencia al CtrlDominiRecomanacio.
     */
    private CtrlDominiRecomanacio ctrlDRecomanacio;

    /**
     * Referencia al CtrlDominiConfiguracio.
     */
    private CtrlDominiConfiguracio ctrlDConfiguracio;

    /**
     * Referencia al CtrlDominiUsuari.
     */
    private CtrlDominiUsuari ctrlDUsuari;

    /**
     * Referencia al CtrlDominiItems.
     */
    private CtrlDominiItem ctrlDItems;

    /**
     * Referencia al CtrlDominiValoracio.
     */
    private CtrlDominiValoracio ctrlDValoracio;

    /**
     * Referencia al CtrlPersistencia.
     */
    private CtrlPersistencia ctrlPersistencia;

    /**
     * Instancia de la propia classe. Propietat singleton.
     */
    private static CtrlDomini instance;

    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlDomini getInstance() {
        if (instance == null) {
            instance = new CtrlDomini();
        }
        return instance;
    }

    /**
     * Constructora.
     */
    public CtrlDomini() {
        inicialitzarCtrlDomini();
    }

    /**
     * Inicialitza tots els controladors adjacents.
     */
    public void inicialitzarCtrlDomini() {
        ctrlDUsuari = CtrlDominiUsuari.getInstance();
        ctrlDItems = CtrlDominiItem.getInstance();
        ctrlDValoracio = CtrlDominiValoracio.getInstance();
        ctrlDConfiguracio = CtrlDominiConfiguracio.getInstance();
        ctrlDRecomanacio = CtrlDominiRecomanacio.getInstance();
        ctrlPersistencia = CtrlPersistencia.getInstance();
    }

    /**
     * Valida que el path passat com a parametre sigui valid.
     * En cas de que el path sigui valid, comunica al CtrlDominiConfiguracio l'assignacio del path.
     */
    public void validarPath(String path) throws Exception {
        ctrlPersistencia.validarPath(path);
        ctrlDConfiguracio.assignarPath(path);
    }

    /**
     * Carrega els TipusAtribut segons la configuracio de l'usuari.
     * Carrega tota la base de dades obtinguda del fitxers items.csv i ratings.db.csv.
     * Envia usuaris a CtrlDominiUsuari.
     * Envia valoracions a CtrlDominiValoracio.
     * Envia tipusAtribut a CtrlDominiConfiguracio.
     * @param configUser representa l'eleccio de l'usuari sobre cada TipusAtribut (tipusDada, calculable).
     */
    public void setTipusAtributs(Map<String, Pair<String, Boolean>> configUser) throws Excepcions.NomNul, Excepcions.TipusDadaNul, Excepcions.IncompatibleTipusAtribut, Excepcions.ValorAtributNul, Excepcions.TipusAtributNul, Excepcions.AtributNul, Excepcions.UnexistingAtributsException, Excepcions.SetAtributsNul, Excepcions.InvalidValoracioException, Excepcions.ItemNul, Excepcions.InvalidPuntuacioException, Excepcions.UsuariNul, Excepcions.ValoracioNul, Excepcions.NoTipusAtributCalculable {
        boolean calculableDetectat = false;
        for (Pair<String, Boolean> calc: configUser.values()) {
            if (calc.second()) calculableDetectat = true;
        }
        if (!calculableDetectat) throw new Excepcions.NoTipusAtributCalculable();
        Map<Pair<Integer, String>, TipusAtribut> tipusAtribut = new HashMap<>();
        lecturaHeader(ctrlPersistencia.llegeixLiniesArxiu(ctrlDConfiguracio.getPath() + "items.csv", 1), tipusAtribut, configUser, ctrlDConfiguracio.getNomId());
        //map tipusAtribut ja esta actualitzat amb la config que ha dit l'usuari
        int posicioId = getPosicioId(ctrlPersistencia.llegeixLiniesArxiu(ctrlDConfiguracio.getPath() + "items.csv", 1), ctrlDConfiguracio.getNomId());

        Map<Integer, Item> items = new HashMap<>();
        ArrayList<Integer> ids = new ArrayList<>();
        inicialitzarItems(tipusAtribut, items, ids, posicioId, ctrlPersistencia.llegeixArxiu(ctrlDConfiguracio.getPath() + "items.csv"));
        //map items carregat
        ctrlDItems.inicialitzarControlador(items); //items carregat al ctrl
        ctrlDConfiguracio.inicialitzarControlador(tipusAtribut); //tipusAtribut carregat al ctrl

        //torn del map usuaris, valoracions, recomanacions
        ArrayList<ArrayList<String>> dataRatings = ctrlPersistencia.llegeixArxiu(ctrlDConfiguracio.getPath() + "ratings.db.csv");
        Map<Integer, String> mapColumnes = new HashMap<>();
        double ratingMax = mapeigRatings(dataRatings, mapColumnes);
        //carregar map usuaris + afegir data a larxiu usuaris + carregar map valoracions
        Map<Integer, Usuari> usuaris = new HashMap<>();
        Map<Pair<Item, Usuari>, Valoracio> valoracions = new HashMap<>();

        inicialitzarUsuarisValoracions(dataRatings, items, usuaris, valoracions, ratingMax, mapColumnes);
        ctrlDUsuari.afegirCjtUsuaris(usuaris);//usuaris carregat a ctrl

        ctrlDValoracio.inicialitzarControlador(valoracions); //carregar valoracions a ctrl
        ctrlPersistencia.inicialitzarControladors(ctrlDConfiguracio.getPath(), ctrlDConfiguracio.getNomTitol(), ctrlDConfiguracio.getNomId(), ctrlDConfiguracio.getDadesPerFitxer());

        ctrlDRecomanacio.setNoAprofitaRecomanacions();//aixo a danar abans que passaTotsUsuarisItemsIUsuariIniciat
    }

    /**
     * Comprova que no hi hagin hagut canvis entre l'arxiu items.csv i configuracio.txt.
     * Carrega els TipusAtribut segons la configuracio guaradada a l'arxiu configuracio.txt.
     * Carrega tota la base de dades obtinguda del fitxers items.csv, ratings.db.csv, valoracionsUA, i usuarisActius.csv.
     * Envia usuaris a CtrlDominiUsuari.
     * Envia valoracions a CtrlDominiValoracio.
     * Envia tipusAtribut a CtrlDominiConfiguracio.
     * @return false en cas de que no hi hagin canvis, true en cas contrari.
     */
    public boolean canvisTipusAtributsArxiuItems() throws Exception {

        if (!ctrlPersistencia.canvisTipusAtributsArxiuItems(ctrlDConfiguracio.getPath())) {

            Map<String, Pair<String, Boolean>> config = ctrlPersistencia.getTipusAtribut(ctrlDConfiguracio.getPath() + "configuracio.txt");
            ctrlDConfiguracio.setTipusAtributId(ctrlPersistencia.getNomId(ctrlDConfiguracio.getPath() + "configuracio.txt"));
            ctrlDConfiguracio.assignarTitol(ctrlPersistencia.getNomTitol(ctrlDConfiguracio.getPath() + "configuracio.txt"));
            ctrlPersistencia.setHeaderVal(ctrlDConfiguracio.getPath() + "valoracionsUA.csv");
            //---------------

            Map<Pair<Integer, String>, TipusAtribut> tipusAtribut = new HashMap<>();
            lecturaHeader(ctrlPersistencia.llegeixLiniesArxiu(ctrlDConfiguracio.getPath() + "items.csv", 1), tipusAtribut, config, ctrlDConfiguracio.getNomId());

            //map tipusAtribut ja esta actualitzat amb la config que ha dit l'usuari
            int posicioId = getPosicioId(ctrlPersistencia.llegeixLiniesArxiu(ctrlDConfiguracio.getPath() + "items.csv", 1), ctrlDConfiguracio.getNomId());

            Map<Integer, Item> items = new HashMap<>();
            ArrayList<Integer> ids = new ArrayList<>();
            inicialitzarItems(tipusAtribut, items, ids, posicioId, ctrlPersistencia.llegeixArxiu(ctrlDConfiguracio.getPath() + "items.csv"));
            //map items carregat
            ctrlDItems.inicialitzarControlador(items); //items carregat al ctrl            
            ctrlDConfiguracio.inicialitzarControlador(tipusAtribut); //tipusAtribut carregat al ctrl

            //torn del map usuaris, valoracions, recomanacions
            ArrayList<ArrayList<String>> dataRatings = ctrlPersistencia.llegeixArxiu(ctrlDConfiguracio.getPath() + "ratings.db.csv");
            Map<Integer, String> mapColumnes = new HashMap<>();
            double ratingMax = mapeigRatings(dataRatings, mapColumnes);
            //carregar map usuaris + afegir data a larxiu usuaris + carregar map valoracions
            Map<Integer, Usuari> usuaris = new HashMap<>();
            Map<Pair<Item, Usuari>, Valoracio> valoracions = new HashMap<>();

            inicialitzarUsuarisValoracions(dataRatings, items, usuaris, valoracions, ratingMax, mapColumnes);
            ctrlDUsuari.afegirCjtUsuaris(usuaris);//usuaris carregat a ctrl
            //encara falta llegir arxius: usuarisActius.csv i valoracionsUA.csv <-------- IMPORTANT, AIXO PASA EN CAS DE Q EXISTEIXIN <--- SHA DE COMPROVAR

            //ABANS OMPLIR USUARISACTIUS
            //Map<Integer, Usuari> usuarisActius = new HashMap<>();
            ArrayList<ArrayList<String>> dataUsuarisActius = ctrlPersistencia.llegeixArxiu(ctrlDConfiguracio.getPath() + "usuarisActius.csv");
            carregarUsuarisActius(dataUsuarisActius, usuaris);
            //Afegir valoracions
            ArrayList<ArrayList<String>> dataRatingsUA = ctrlPersistencia.llegeixArxiu(ctrlDConfiguracio.getPath() + "valoracionsUA.csv");
            afegirValoracionsUA(dataRatingsUA, valoracions, usuaris, items, mapColumnes);

            ctrlDUsuari.afegirCjtUsuaris(usuaris); //carregar usuarisActius a ctrl
            ctrlDValoracio.inicialitzarControlador(valoracions); //carregar valoracions a ctrl
            return false;
        }
        return true;
    }

    /**
     * Carrega les recomanacions dels diferents algorismes, guardades a l'arxiu recomanacions.csv, als ArrayList<Integer> corresponents.
     */
    private void carregarRecomanacions(ArrayList<Item> recomanacionsGuardadesCB, ArrayList<Item> recomanacionsGuardadesCOL, ArrayList<Item> recomanacionsGuardadesHibrid) {
        ArrayList<ArrayList<String>> dataRecomanacions = ctrlPersistencia.llegeixArxiu(ctrlDConfiguracio.getPath() + "recomanacions.csv");
        int l = 0;
        for (ArrayList<String> linia : dataRecomanacions) {
            if (l != 0) {
                if (Integer.parseInt(linia.get(0)) == usuariActiu.getId()) {
                    switch (linia.get(1)) {
                        case "COL":
                            arrayStringToItem(recomanacionsGuardadesCOL, linia);
                            break;
                        case "CB":
                            arrayStringToItem(recomanacionsGuardadesCB, linia);
                            break;
                        case "Hybrid":
                            arrayStringToItem(recomanacionsGuardadesHibrid, linia);
                            break;
                    }
                }
            }
            ++l;
        }
    }

    /**
     * Conversio ArrayList<String> (String = idItem) a ArrayList<Item>.
     */
    private void arrayStringToItem(ArrayList<Item> recomanacionsGuardades, ArrayList<String> linia) {
        String[] idsI = linia.get(2).split(";");
        for (String id : idsI) {
            recomanacionsGuardades.add(ctrlDItems.getItem(Integer.parseInt(id)));
        }
    }

    /**
     * Carrega els usuarisActius obtinguts de dataUsuarisActius a usuarisActius.
     * @param dataUsuarisActius data extreta de l'arxiu usuarisActius.csv.
     */
    private void carregarUsuarisActius(ArrayList<ArrayList<String>> dataUsuarisActius, Map<Integer, Usuari> usuarisActius) throws Excepcions.NomNul, Excepcions.PasswordNul {
        //"idUsuari,nom,contrasenya"
        Iterator<ArrayList<String>> iteratorLinies = dataUsuarisActius.listIterator();
        int linea = 0;
        while (iteratorLinies.hasNext()) {
            Iterator<String> itParaules = iteratorLinies.next().listIterator();
            int columna = 0;
            int idUsuari = 0;
            String nom = "";
            String contrasenya = "";
            Set<Item> itemsCreats = new HashSet<>();
            while (itParaules.hasNext()) {
                String paraula = itParaules.next();
                if (linea != 0) {
                    switch (columna) {
                        case 0:
                            idUsuari = Integer.parseInt(paraula);
                            break;
                        case 1:
                            nom = paraula;
                            break;
                        case 2:
                            contrasenya = paraula;
                            break;
                        case 3:
                            String[] idsI = paraula.split(";");
                            for (String idI : idsI) {
                                itemsCreats.add(ctrlDItems.getItem(Integer.parseInt(idI)));
                            }
                            break;
                    }
                }
                ++columna;
            }
            //Afegim Usuari amb l'associació amb Valoració
            if (linea > 0) {
                UActiu uActiu = new UActiu(idUsuari, nom, contrasenya);
                for (Item item : itemsCreats) {
                    uActiu.afegirItemCreat(item);
                }
                usuarisActius.put(idUsuari, uActiu);
            }
            ++linea;
        }
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
     * Carrega les valoracions obtingudes de dataRatingsUA a valoracions.
     * @param dataRatingsUA data extreta de l'arxiu valoracionsUA.csv.
     * @param mapCap representa la primera linia de l'arxiu ratings.db.csv (header).
     */
    private void afegirValoracionsUA(ArrayList<ArrayList<String>> dataRatingsUA, Map<Pair<Item, Usuari>, Valoracio> valoracions, Map<Integer, Usuari> usuarisActius, Map<Integer, Item> items, Map<Integer, String> mapCap) throws Excepcions.ItemNul, Excepcions.InvalidPuntuacioException, Excepcions.InvalidValoracioException {
        Iterator<ArrayList<String>> iteratorLinies = dataRatingsUA.listIterator();
        int linea = 0;
        while (iteratorLinies.hasNext()) {
            Iterator<String> itParaules = iteratorLinies.next().listIterator();
            int idUsuari = 0;
            int idItem = 0;
            int columna = 0;
            double rating = 0;
            String comentari = "";
            while (itParaules.hasNext()) {
                String paraula = itParaules.next();
                if (linea != 0 && columna < 3) {
                    if (mapCap.get(columna).equals("userId")) idUsuari = Integer.parseInt(paraula);
                    else if (mapCap.get(columna).equals("itemId")) idItem = Integer.parseInt((paraula));
                    else rating = Double.parseDouble(paraula);
                }
                else if (linea != 0) {
                    if (columna == 3) comentari = paraula;
                    else comentari = comentari.concat("," + paraula);
                }
                ++columna;
            }

            if (linea > 0) {
                Valoracio nova = new Valoracio(usuarisActius.get(idUsuari), rating, comentari, items.get(idItem));
                Pair<Item, Usuari> key = new Pair<>(items.get(idItem), usuarisActius.get(idUsuari));
                valoracions.put(key, nova);
                usuarisActius.get(idUsuari).addItemValoracio(nova);
            }
            ++linea;
        }
    }

    /**
     * Registra l'usuari amb nom = nom i contransenya = contrasenya al sistema.
     * Inicia sessio.
     */
    public void registraUsuari(String nom, String contrasenya) throws Exception {
        ctrlDUsuari.registraUsuari(nom, contrasenya);
        iniciarSessio(nom, contrasenya);
        ctrlPersistencia.afegirUsuariActiu(ctrlDConfiguracio.getPath() + "usuarisActius.csv", usuariActiu);
    }

    /**
     * Conversio ArrayList<Item> a ArrayList<Integer> (Integer = idItem).
     */
    private void arrayItemToInt(ArrayList<Integer> recomanacionsGuardades, ArrayList<Item> recomanacions) {
        for (Item item : recomanacions) {
            recomanacionsGuardades.add(item.getID());
        }
    }

    /**
     * Assigna usuariActiu a l'usuari que ha iniciat sessio.
     * Comunica al CtrlDominiRecomanacio que un usuari ha iniciat sessio, perque faci les operacions necessaries.
     */
    public void iniciarSessio(String nom, String contrasenya) throws Exception {
        usuariActiu = ctrlDUsuari.iniciarSesio(nom, contrasenya);
        ArrayList<Item> recomanacionsGuardadesCB = new ArrayList<>();
        ArrayList<Item> recomanacionsGuardadesCOL = new ArrayList<>();
        ArrayList<Item> recomanacionsGuardadesHybrid = new ArrayList<>();
        carregarRecomanacions(recomanacionsGuardadesCB, recomanacionsGuardadesCOL, recomanacionsGuardadesHybrid);
        ctrlDRecomanacio.inicialitzarControlador(recomanacionsGuardadesCB, recomanacionsGuardadesCOL, recomanacionsGuardadesHybrid);
        ctrlDRecomanacio.passaTotsUsuarisItemsIUsuariIniciat(this, usuariActiu, new HashSet<>(ctrlDItems.getItems().values()), ctrlDUsuari.getUsuaris());
    }

    /**
     * Retorna els noms dels TipusAtributs de la primera linia de l'arxiu items.csv (header).
     * @return HashSet<nomTipusAtribut>
     */
    public HashSet<String> getNomTipusAtributs() {
        ArrayList<ArrayList<String>> headerItems = ctrlPersistencia.llegeixLiniesArxiu(ctrlDConfiguracio.getPath() + "items.csv", 1);
        return new HashSet<>(headerItems.get(0));
    }

    /**
     * Retorna els TipusAtributs provinents del CtrlDominiConfiguracio.
     * @return Map<Pair<Integer, String>, TipusAtribut>
     */
    public Map<Pair<Integer, String>, TipusAtribut> getTipusAtributs() {
        return ctrlDConfiguracio.getTipusAtributs();
    }

    /**
     * Comunica l'assignacio del nom del TipusAtribut que representa el id d'un item.
     */
    public void setTipusAtributId(String nomTipusAtributID) throws Excepcions.idHaDeSerInteger {
        ctrlDConfiguracio.setTipusAtributId(nomTipusAtributID);
    }

    /**
     * Retorna si l'usuari que ha iniciat sessio te almenys una valoracio feta.
     * @return false en cas de que no tingui cap, true en cas contrari.
     */
    public boolean almenysUnaValoracioFeta() {
        return !usuariActiu.getItemsValoracions().isEmpty();
    }

    /**
     * Comunica l'assignacio del nom del TipusAtribut que representa el titol d'un item.
     */
    public void setTipusAtributTitol(String nomTipusAtributTitol) throws Excepcions.titolHaDeSerString {
        ctrlDConfiguracio.assignarTitol(nomTipusAtributTitol);
    }

    /**
     * Retorna si existeix l'item amb titol = nom.
     * @return false en cas de que no existeixi l'item a la base de dades del CtrlDominiItems, true en cas contrari.
     */
    public boolean existeixItem(String nom) {
        return ctrlDItems.existeixItem(nom, ctrlDConfiguracio.getNomTitol());
    }

    /**
     * Retorna el id de l'item amb titol = titol.
     * @return idItem.
     */
    public int getIdItem(String titol) throws Excepcions.ItemNoExisteix {
        return ctrlDItems.getIdItem(titol, ctrlDConfiguracio.getNomTitol());
    }

    /**
     * Retorna els Atributs de l'item amb titol = titol.
     * @return HashMap<nomTipusAtribut, dadaAtribut>.
     */
    public HashMap<String, String> getValorsTipusAtributDeItem(String titol) throws Excepcions.ItemNoExisteix {
        return ctrlDItems.getValorsTipusAtributDeItem(titol, ctrlDConfiguracio.getNomTitol());
    }

    /**
     * Carrega tipusAtribut a partir del header i configUser.
     * @param header primera linia de l'arxiu items.csv
     * @param configUser configuracio dels TipusAtribut feta per l'usuari.
     * @param nomId columna que representa els id dels items a l'arxiu items.csv.
     */
    private void lecturaHeader(ArrayList<ArrayList<String>> header, Map<Pair<Integer, String>, TipusAtribut> tipusAtribut, Map<String, Pair<String, Boolean>> configUser, String nomId) throws Excepcions.NomNul, Excepcions.TipusDadaNul, Excepcions.IncompatibleTipusAtribut {
        int columna = 0;
        for (ArrayList<String> strings : header) {
            for (String paraula : strings) {
                if (!paraula.equals(nomId)) {
                    Pair<Integer, String> key = new Pair<>(columna, paraula);
                    tipusAtribut.put(key, new TipusAtribut(paraula, configUser.get(paraula).first(), configUser.get(paraula).second()));
                }
                ++columna;
            }
        }
    }

    /**
     * Carrega items a partir d'atributsItems.
     * Associa cada item amb els seus Atributs.
     * Afegeix cada Atribut al conjunt d'Atributs de cada TipusAtribut corresponent.
     * @param atributsItems data extreta de l'arxiu items.csv.
     */
    private void inicialitzarItems(Map<Pair<Integer, String>, TipusAtribut> tipusAtribut, Map<Integer, Item> items, ArrayList<Integer> ids, int posicioId, ArrayList<ArrayList<String>> atributsItems) throws Excepcions.TipusAtributNul, Excepcions.AtributNul, Excepcions.ValorAtributNul, Excepcions.UnexistingAtributsException, Excepcions.SetAtributsNul {
        Iterator<ArrayList<String>> linias = atributsItems.listIterator();
        int columna = 0;
        int linia = 0;
        int saveLinia = 0;
        String paraulaAAjuntar = "";
        boolean quotesLinia = false;
        while (linias.hasNext()) {
            Iterator<String> paraules = linias.next().listIterator();
            Set<Atribut> atributsItem = new HashSet<>();
            boolean quotes = false;
            while (paraules.hasNext()) {
                String paraula = paraules.next();
                if (linia > 0) {
                    if (columna == posicioId) {
                        int num = Integer.parseInt(paraula);
                        ids.add(num);
                        ++columna;
                    }
                    else if (!paraula.equals("") && columna <= tipusAtribut.size()) {
                        for (Pair<Integer, String> key : tipusAtribut.keySet()) {
                            if (key.first() == columna) {
                                String tipus = tipusAtribut.get(key).getTipusDada();
                                if (!Objects.equals(tipus, "String")) {
                                    tipusAtribut.get(key).crearAtribut(paraula, tipusAtribut, key, atributsItem);
                                    ++columna;
                                }
                                else {
                                    char lastCharacter = paraula.charAt(paraula.length() - 1);
                                    char firstChar = paraula.charAt(0);
                                    int charQuotes = comptarQuotes(paraula);
                                    if ((firstChar != lastCharacter && firstChar == '"' && !quotes) || (firstChar == lastCharacter && firstChar == '"' && charQuotes % 2 == 1 && !quotes)) {//Primer amb " i ultim char sense "
                                        quotes = true;
                                        quotesLinia = true;
                                        saveLinia = linia;
                                        paraulaAAjuntar = paraula;
                                    }
                                    else if (!paraula.contains("\"") && quotes && quotesLinia || !paraula.contains("\"") && !quotes && quotesLinia) {//Paraules entre " "
                                        paraulaAAjuntar = paraulaAAjuntar.concat("," + paraula);
                                    }
                                    else if (paraula.contains("\"") && quotes || paraula.contains("\"") && !quotes && quotesLinia) {//Paurala entre " " amb "
                                        if (charQuotes % 2 == 1) {
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
                                    else {
                                        tipusAtribut.get(key).crearAtribut(paraula, tipusAtribut, key, atributsItem);
                                        ++columna;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    else ++columna;
                }
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
     * Carrega mapColumnes a partir de dadesArxiu.
     * @param dadesArxiu data extreta de l'arxiu ratings.db.csv.
     * @param mapColumnes representa el header de l'arxiu ratings.db.csv (Map<columna, nomColumna>)
     * @return ratingMax = la puntuacio mes alta de tot dadesArxius.
     */
    private double mapeigRatings(ArrayList<ArrayList<String>> dadesArxiu, Map<Integer, String> mapColumnes) {
        Iterator<ArrayList<String>> iteratorLinies = dadesArxiu.listIterator();
        int linea = 0;
        double ratingMax = 0.0;
        while (iteratorLinies.hasNext()) {
            Iterator<String> itParaules = iteratorLinies.next().listIterator();
            double rating;
            int columna = 0;
            while (itParaules.hasNext()) {
                String word = itParaules.next();
                if (linea == 0) {
                    mapColumnes.put(columna, word);
                }
                else {
                    if (mapColumnes.get(columna).equals("rating")) {
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

    /**
     * Carrega usuaris i valoracions a partir de dataArxiu.
     * @param dataArxiu data extreta de ratings.db.csv.
     * @param ratingMax puntuacio mes alta de l'arxiu ratings.db.csv.
     * @param mapCap representa la primera linia de l'arxiu ratings.db.csv (header).
     */
    private void inicialitzarUsuarisValoracions(ArrayList<ArrayList<String>> dataArxiu, Map<Integer, Item> items, Map<Integer, Usuari> usuaris, Map<Pair<Item, Usuari>, Valoracio> valoracions, double ratingMax, Map<Integer, String> mapCap) throws Excepcions.InvalidPuntuacioException, Excepcions.ItemNul, Excepcions.InvalidValoracioException, Excepcions.UsuariNul, Excepcions.ValoracioNul {
        Iterator<ArrayList<String>> iteratorLinies = dataArxiu.listIterator();
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
                rating = (rating * 5) / ratingMax;
                Valoracio nova = new Valoracio(usuaris.get(idUsuari), rating, "", items.get(idItem));
                Pair<Item, Usuari> key = new Pair<>(items.get(idItem), usuaris.get(idUsuari));
                valoracions.put(key, nova);
                usuaris.get(idUsuari).addItemValoracio(nova);
                items.get(idItem).addValoracio(nova, usuaris.get(idUsuari));
            }
            ++linea;
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
     * Retorna el nom de l'usuari que ha iniciat sessio.
     * @return nomUsuari.
     */
    public String consultaNomUsuari() {
        return usuariActiu.getNom();
    }

    /**
     * Retorna els TipusAtributs.
     * @return HashMap<nomTipusAtribut, tipusDada>.
     */
    public HashMap<String, String> consultaTipusTipusAtributs() {
        return ctrlDConfiguracio.getTipusTipusAtributs();
    }

    /**
     * Retorna les valoracions fetes per l'usuari que ha iniciat sessio.
     * @return HashMap<String, Pair<Integer, String>>.
     */
    public HashMap<String, Pair<Integer, String>> consultaValoracionsUsuari() {
        HashMap<String, Pair<Integer, String>> valUsuari = new HashMap<>();
        HashMap<Item, Valoracio> itemsVal = usuariActiu.getItemsValoracions();
        for (Item item : itemsVal.keySet()) {
            String titol = ctrlDItems.getTitol(item.getID(), ctrlDConfiguracio.getNomTitol());
            valUsuari.put(titol, new Pair<>((int) itemsVal.get(item).getPuntuacio(), itemsVal.get(item).getDescripcio()));
        }
        return valUsuari;
    }

    /**
     * Comunica als CtrlDominiValoracio, CtrlDominiUsuari, i CtrlPersistencia perque afegeixin la valoracio de l'item amb titol = titol de les seves bases de dades.
     * Recalcula les recomanacions amb la valoracio afegida i comunica al CtrlPersistencia perque les guardi.
     */
    public void creaValoracio(String titolItem, Integer puntuacio, String comentaris) throws Excepcions.ItemNoExisteix, Excepcions.ItemNul, Excepcions.InvalidPuntuacioException, Excepcions.UsuariJaTeValoracioAItem, IOException, Excepcions.InvalidValoracioException, Excepcions.InsuficientsItemsNoValoratsExcepcio, Excepcions.UsuariAAfegirEsAnalitzat, Excepcions.UsuariNul, Excepcions.MassaClustersExcepcio, Excepcions.UsuariJaAfegit, Excepcions.InsuficientsValoracionsExcepcio, Excepcions.InsuficientsItemsExcepcio, Excepcions.SetUsuarisNul, Excepcions.CapClusterExcepcio, Excepcions.UsuarisNoCoincidents {
        ctrlDValoracio.afegirValoracio(usuariActiu, puntuacio, comentaris, ctrlDItems.getItem(titolItem, ctrlDConfiguracio.getNomTitol()));
        ctrlDUsuari.afegirValoracio(usuariActiu, puntuacio, comentaris, ctrlDItems.getItem(titolItem, ctrlDConfiguracio.getNomTitol()));
        ctrlPersistencia.afegirValoracio(ctrlDConfiguracio.getPath() + "valoracionsUA.csv", usuariActiu.getId(), puntuacio, comentaris, ctrlDItems.getItem(titolItem, ctrlDConfiguracio.getNomTitol()).getID());

        ctrlDRecomanacio.refrescaPerValoracioAfegida();
        guardarRecomanacions();
    }

    /**
     * Comunica als CtrlDominiValoracio, CtrlDominiUsuari, i CtrlPersistencia perque eliminin totes les relacions de la valoracio de l'item amb titol = titol de les seves bases de dades.
     * Recalcula les recomanacions amb la valoracio afegida i comunica al CtrlPersistencia perque les guardi.
     */
    public void eliminaValoracio(String titol) throws Excepcions.ItemNoExisteix, Excepcions.InsuficientsItemsNoValoratsExcepcio, Excepcions.UsuariAAfegirEsAnalitzat, Excepcions.UsuariNul, Excepcions.MassaClustersExcepcio, Excepcions.UsuariJaAfegit, Excepcions.InsuficientsValoracionsExcepcio, Excepcions.InsuficientsItemsExcepcio, Excepcions.SetUsuarisNul, Excepcions.CapClusterExcepcio, Excepcions.UsuarisNoCoincidents, IOException {
        ctrlDValoracio.eliminarValoracio(usuariActiu, ctrlDItems.getItem(titol, ctrlDConfiguracio.getNomTitol()));
        ctrlDUsuari.eliminarValoracio(usuariActiu, ctrlDItems.getItem(titol, ctrlDConfiguracio.getNomTitol()));
        ctrlPersistencia.eliminarValoracio(ctrlDConfiguracio.getPath() + "valoracionsUA.csv", usuariActiu.getId(), ctrlDItems.getItem(titol, ctrlDConfiguracio.getNomTitol()).getID());
        ctrlDRecomanacio.refrescaPerValoracioEliminada();
        guardarRecomanacions();
    }

    /**
     * Envia al CtrlPersistencia totes les recomanacions, de l'usuari que ha iniciat sessio, generades pels algorismes, perque les guardi al fitxer recomanacions.csv.
     */
    private void guardarRecomanacions() throws IOException {
        ArrayList<Integer> COL = new ArrayList<>();
        ArrayList<Integer> CB = new ArrayList<>();
        ArrayList<Integer> Hybrid = new ArrayList<>();
        arrayItemToInt(COL, ctrlDRecomanacio.getRecomanacionsCOL());
        arrayItemToInt(CB, ctrlDRecomanacio.getRecomanacionsCB());
        arrayItemToInt(Hybrid, ctrlDRecomanacio.getRecomanacionsHybrid());
        ctrlPersistencia.guardarRecomanacioUsuariActiu(usuariActiu.getId(), COL, CB, Hybrid, ctrlDConfiguracio.getPath() + "recomanacions.csv");
    }

    /**
     * Comunica als CtrlDominiValoracio, CtrlDominiUsuari, CtrlPersistencia perque eliminin totes les relacions de l'usuari, que ha iniciat sessio, de les seves bases de dades.
     */
    public void eliminarUsuariActiu() {
        ctrlDUsuari.eliminarUA(usuariActiu.getId());
        ctrlDValoracio.eliminarUA(usuariActiu);
        ctrlPersistencia.eliminarUA(ctrlDConfiguracio.getPath(), usuariActiu.getId());
    }

    /**
     * Comunica als CtrlDominiItem, CtrlDominiUsuari, CtrlDominiRecomanacio, CtrlDominiConfiguracio, i CtrlPersistencia perque eliminin totes les relacions de l'item amb titol = titol de les seves bases de dades.
     */
    public void eliminaItem(String titol) throws Excepcions.ItemNoExisteix, Excepcions.UsuariNoEsCreadorDelItem, IOException, Excepcions.InsuficientsItemsNoValoratsExcepcio, Excepcions.UsuariAAfegirEsAnalitzat, Excepcions.UsuariNul, Excepcions.MassaClustersExcepcio, Excepcions.UsuariJaAfegit, Excepcions.InsuficientsValoracionsExcepcio, Excepcions.ItemNoAfegit, Excepcions.InsuficientsItemsExcepcio, Excepcions.SetUsuarisNul, Excepcions.CapClusterExcepcio, Excepcions.AtributNul, Excepcions.UsuarisNoCoincidents {
        Item item = ctrlDItems.getItem(titol, ctrlDConfiguracio.getNomTitol());
        if (!ctrlDUsuari.checkCreador(usuariActiu.getId(), item)) throw new Excepcions.UsuariNoEsCreadorDelItem();
        else {
            ctrlDUsuari.eliminarItem(usuariActiu.getId(), item);
            ctrlDItems.eliminarItem(item);
            ctrlDValoracio.eliminarItem(item);
            ctrlDRecomanacio.refrescaPerItemEliminat(item);
            guardarRecomanacions();
            ctrlDConfiguracio.eliminarAtributsItem(item);
            Set<Integer> idsItemsCreats = new HashSet<>();
            for (Item items : usuariActiu.getItemsCreats()) {
                idsItemsCreats.add(items.getID());
            }
            ctrlPersistencia.eliminarItem(usuariActiu.getId(), idsItemsCreats, ctrlDConfiguracio.getPath(), item.getID(), ctrlDConfiguracio.getNomId());
        }
    }

    /**
     * Comunica als CtrlDominiItem, CtrlDominiUsuari, CtrlDominiRecomanacio, CtrlDominiConfiguracio, i CtrlPersistencia perque afegeixin l'item amb atributs a les seves bases de dades.
     */
    public void creaItem(HashMap<String, String> atributs) throws Excepcions.ValorAtributNul, Excepcions.AtributNul, Excepcions.TipusAtributNul, Excepcions.UnexistingAtributsException, Excepcions.SetAtributsNul, IOException, Excepcions.InsuficientsItemsNoValoratsExcepcio, Excepcions.UsuariAAfegirEsAnalitzat, Excepcions.UsuariNul, Excepcions.MassaClustersExcepcio, Excepcions.UsuariJaAfegit, Excepcions.InsuficientsValoracionsExcepcio, Excepcions.ItemNul, Excepcions.InsuficientsItemsExcepcio, Excepcions.SetUsuarisNul, Excepcions.CapClusterExcepcio, Excepcions.UsuarisNoCoincidents, Excepcions.ItemNoTeTitol, Excepcions.ItemAmbMateixTitolJaExisteix {
        int idItem = ctrlDItems.afegirItem(atributs, ctrlDConfiguracio.getTipusAtributs(), ctrlDConfiguracio.getNomTitol());
        ctrlDUsuari.crearItem(usuariActiu, ctrlDItems.getItem(idItem));
        ctrlDRecomanacio.refrescaPerItemAfegit(ctrlDItems.getItem(idItem));
        guardarRecomanacions();
        ctrlDConfiguracio.afegirAtributsItem(ctrlDItems.getItem(idItem));
        Set<Integer> idsItemsCreats = new HashSet<>();
        for (Item item : usuariActiu.getItemsCreats()) {
            idsItemsCreats.add(item.getID());
        }
        ctrlPersistencia.afegirItem(usuariActiu.getId(), idsItemsCreats, idItem, atributs, ctrlDConfiguracio.getTipusAtributs(), ctrlDConfiguracio.getPath(), ctrlDConfiguracio.getNomId());
    }

    /**
     * Retorna els titols de tots els items recomanats per l'algorisme CBFiltering.
     * @return ArrayList<titolItem>.
     */
    public ArrayList<String> nomsItemsRecomanatsCB() {
        return getNomsItemsRecomanats(ctrlDRecomanacio.getRecomanacionsCB());
    }

    /**
     * Retorna els titols de tots els items recomanats per l'algorisme COLFiltering.
     * @return ArrayList<titolItem>.
     */
    public ArrayList<String> nomsItemsRecomanatsCOL() {
        return getNomsItemsRecomanats(ctrlDRecomanacio.getRecomanacionsCOL());
    }

    /**
     * Retorna els titols de tots els items recomanats per l'algorisme Hybrid.
     * @return ArrayList<titolItem>.
     */
    public ArrayList<String> nomsItemsRecomanatsHYBRID() {
        return getNomsItemsRecomanats(ctrlDRecomanacio.getRecomanacionsHybrid());
    }

    /**
     * Retorna els titols de tots els items recomanats passats com a parametres.
     * @return ArrayList<titolItem>.
     */
    private ArrayList<String> getNomsItemsRecomanats(ArrayList<Item> recomanacions) {
        ArrayList<String> nomsItems = new ArrayList<>();
        for (Item item : recomanacions) {
            nomsItems.add(ctrlDItems.getTitol(item.getID(), ctrlDConfiguracio.getNomTitol()));
        }
        return nomsItems;
    }
}

/*
implementar throw per:
    - usuari crea un item sense titol
    - el titol ingresat ha de ser diferent a tots els altres (unic)
 */