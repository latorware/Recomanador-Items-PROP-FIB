package presentacio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import domini.controladors.CtrlDomini;
import utils.Pair;


public class CtrlPresentacio {

    /**
     * Instancia de CtrlDomini amb la qual es comunica CtrlPresentacio
     */
    private CtrlDomini ctrlDomini; 

    /**
     * Representa la instancia de la classe VistaPrincipal de la interficie grafica
     */
    private VistaPrincipal vistaPrincipal;

    /**
     * Necesari ja que CtrlPresentacio és singleton
     */
    private static CtrlPresentacio instance;

    /**
     * Necessaria ja que CtrlPresentacio és singleton
     * @return retorna la instància de CtrlPresentacio
     */
    public static CtrlPresentacio getInstance() {
        if (instance == null) {
            instance = new CtrlPresentacio();
        }
        return instance;
    }


    /**
     * Constructora de la classe. 
     */
    public CtrlPresentacio() {
        ctrlDomini = CtrlDomini.getInstance();
        vistaPrincipal = new VistaPrincipal(this); 
    }

    /**
     * Pregunta a CtrlDomini el nom de l'usuari que te la sessió iniciada
     * @return el nom de l'usuari que té la sessió iniciada. 
     */
    public String consultaNomUsuari (){//FET
        //CRIDA A CTRLDOMINI consultaNomUsuari
        //LUNIC QUE HA DE FER CTRLDOMINI ES TORNAR EL NOM DUSUARI DE LUSUARI QUE TE LA SESIO INICIADA. NO CAL THROW PERQUE AQUESTA SEMPRE SERA CRIDADA QUAN LA SESIO ESTIGUI INICIADA
        return ctrlDomini.consultaNomUsuari();
    }


    /**
     * Diu a CtrlDomini que faci totes les operacions d'inicialització
     */
    public void inicialitzarPresentacio() throws Exception {
        vistaPrincipal.ferVisible();
    }

    /**
     * Diu a CtrlDomini quin es el TipusAtribut que conté atributs que fan referència al ID dels items
     * @param nomTipusAtributID és el nom que identifica aquest TipusAtribut
     */
    public void setTipusAtributId(String nomTipusAtributID) throws Exception {//FET
        //CRIDA A CTRLDOMINI setTipusAtributId
        ctrlDomini.setTipusAtributId(nomTipusAtributID);
        //EL QUE HA DE FER CTRLDOMINI es setejar a la configuracio dels TipusAtributs el TipusAtribut que fa referencia al ID. Aquest TipusAtribut es el tipusAtribut que te el nom que sa passat per parametre
    }

    /**
     * Diu a CtrlDomini el path on es troba la base de dades amb la que l'usuari vol treballar. 
     * @param path és el path
     */
    public void especificaPath(String path) throws Exception {//FET
        //CRIDA A CTRLDOMINI especifica path
        ctrlDomini.validarPath(path);
        //SESPECIFICARA EL PATH ES A DIR CARPETA ON ESTAN ELS ARXIUS EXTERNS, ES A DIR EL ITEMS, RATINGS... CAS QUE PATH SIGUI INCORRECTE... es fara throw
    }


    /**
     * Diu a CtrlDomini que registri un usuari actiu nou. Si el registre té èxit, CtrlDomini també farà el inici de sessió
     * @param nom és el nom del nou usuari. 
     * @param contrasenya és la contrassenya del nou usuari. 
     */
    public void registraUsuari (String nom, String contrasenya) throws Exception {//FET
        ctrlDomini.registraUsuari(nom, contrasenya);  //comento perque ctrldomini encara no esta acabat

        //EL QUE HA DE FER CTRLDOMINI es registrar usuariactiu nou amb el nom i contrasenya donats. Si es produeix algun error (ex: ja existeix usuari amb mateix
        //nom i contrasenya) es fara throw. A MES, SI ES POT REGISTRAR, TAMBE INICIARA SESIO AUTOMATICAMENT AMB AQUEST. 
    }

    /**
     * Diu a CtrlDomini que inici sessió amb els parametres de l'usuari que s'especifiquen. 
     * @param nom És el nom de l'usuari amb el qual es vol iniciar la sessió. 
     * @param contrasenya És la contrassenya de l'usuari amb el que es vol iniciar la sessió 
     */
    public void iniciaSesio (String nom, String contrasenya) throws Exception {//FET
        ctrlDomini.iniciarSessio(nom, contrasenya);   //comento perque ctrldomini encara no esta acabat

        //EL QUE HA DE FER CTRLDOMINI es iniciar sesio amb el usuari que te el nom i la contrasenya donats. En cas que es prordueixi algun error es tirara throw. 
    }

    /**
     * Consulta a CtrlDomini si l'usuari que té la sessió iniciada té almenys una valoracio feta. 
     * @return si té almenys una valoració feta
     */
    public boolean almenysUnaValoracioFeta () throws Exception {//FET
        //return  CIRDA A CTRLDOMINI almenysUnaValoracioFeta 
        return ctrlDomini.almenysUnaValoracioFeta();
        //EL QUE HA DE FER CTRLDOMINI es retornar si lusuari amb la sesio iniciada ha fet almenys una valoracio. En cas que es produeixi algun error es tirara throw.
    }

    /**
     * Consulta a CtrlDomini els noms dels items que s'han recomanat a l'usuari que té la sessió iniciada per l'algorisme CBFiltering
     * @return El nom d'aquests items
     */
    public ArrayList<String> nomsItemsRecomanatsCB () throws Exception {
        //return CRIDA A CTRLDOMINI nomsItemsRecomanats
        return ctrlDomini.nomsItemsRecomanatsCB(); 
        //EL QUE HA DE FER CTRLDOMINI es retornar els noms dels items que es recomanen a lusuari que te iniciada la sesio
        /*
        HashSet<String> test = new HashSet<String>(); //per provar interficie
        test.add("nom de algun item"); //per provar interficie
        test.add("nom de algun altre item"); //per provar interficie
        return test; //per provar interficie
         */
    }

    /**
     * Consulta a CtrlDomini els noms dels items que s'han recomanat a l'usuari que té la sessió iniciada per l'algorisme COLFiltering
     * @return El nom d'aquests items 
     */
    public ArrayList<String> nomsItemsRecomanatsCOL () throws Exception {
        //return CRIDA A CTRLDOMINI nomsItemsRecomanats
        return ctrlDomini.nomsItemsRecomanatsCOL(); 
        //EL QUE HA DE FER CTRLDOMINI es retornar els noms dels items que es recomanen a lusuari que te iniciada la sesio
        /*
        HashSet<String> test = new HashSet<String>(); //per provar interficie
        test.add("nom de algun item"); //per provar interficie
        test.add("nom de algun altre item"); //per provar interficie
        return test; //per provar interficie
         */
    }

    /**
     * Consulta a CtrlDomini els noms dels items que s'han recomanat a l'usuari que té la sessió iniciada per l'algorisme HYBRID
     * @return El nom d'aquests items 
     */
    public ArrayList<String> nomsItemsRecomanatsHYBRID () throws Exception {
        //return CRIDA A CTRLDOMINI nomsItemsRecomanats
        return ctrlDomini.nomsItemsRecomanatsHYBRID(); 
        //EL QUE HA DE FER CTRLDOMINI es retornar els noms dels items que es recomanen a lusuari que te iniciada la sesio
        /*
        HashSet<String> test = new HashSet<String>(); //per provar interficie
        test.add("nom de algun item"); //per provar interficie
        test.add("nom de algun altre item"); //per provar interficie
        return test; //per provar interficie
         */
    }


    /**
     * Consulta a CtrlDomini si s'han fet canvis en el arxiu que conté informació sobre TipusAtributs d'items que sigui incompatible amb la configuració interna de la base de dades que l'usuari vol iniciar treballar. 
     * @return Si s'han fet canvis
     */
    public boolean canvisTipusAtributsArxiuItems() throws Exception {//FET
        //return  CRIDA A CTRLDOMINI canvisTipusAtributsArxiuItems
        return ctrlDomini.canvisTipusAtributsArxiuItems();
        //EL QUE HA DE FER CTRLDOMINI es detectar si s'han produit canvis en l'arxiu de items pel que fa als TipusAtributs (s'han afegit tipusAtributs, s'han eliminat respecte l'última vegada, o directament l'arxiu Items es nou)
        //CTRLDOMINI per tant retornara true si hi hi ha canvis, i false sinó. Compte, només ens cal saber si hi ha canvis en TipusAtributs, si per exemple s'han afegit items, eliminat, o canviat valor atributs de items no ens importa aixo

        //return false; //per provar interficie
    }


    /**
     * Consulta a CtrlDomini els noms amb els que s'identifiquen tots els TipusAtributs de la base de dades amb que l'usuari vol treballar
     * @return els noms
     */
    public HashSet<String> getNomTipusAtributs () throws Exception {//FET
        return ctrlDomini.getNomTipusAtributs();      //comento perque ctrldomini encara no esta acabat

        //EL QUE HA DE FER CTRLDOMINI es retornar el nom de tots els TipusAtributs (ex: Genere, Any)

        //return new HashSet<String>(); //per provar interficie
    }

    /**
     * Diu a CtrlDomini el que l'usuari a decidit sobre la configuracio interna, és a dir la configuració dels TipusAtributs de la base de dades que vol treballar. Aquesta configuracio es tracta de: quins TipusAtributs son calculables i quins no, i quins TipusAtributs son d'un tipus, i quins són d'un altre tipus
     * @param tipus la configuracio dels TipusAtributs
     */
    public void setTipusATipusAtributs (HashMap<String, Pair<String, Boolean> > tipus) throws Exception { //FET   //key: nom TipusAtribut       valor: Pair.first: tipusDada      Pair.second: si es o no calculable
        //CRIDA A CTRLDOMINI setTipusATipusAtributs
        ctrlDomini.setTipusAtributs(tipus);
        //EL QUE HA DE FER CTRLDOMINI es setejar la configuracio dels TipusAtributs: el tipus dels TipusAtributs, i si son o no calculables segons com li diu el HashMap de parametre
        //key: nom TipusAtribut       valor: Pair.first: TipusdeTipusAtribut (Int, String ......)      Pair.second: si es o no calculable

        //AL TANTO!!! aqui el ctrlDomini a danar amb cuidado ja que quan es cridi aquesta funcio al ctrldomini, aquest automaticament haura de carregar tots els items i els atributs
        // correctament automaticament i lo demes. PER TANT, tornar a inicialitzarse correctament
    }

    /**
     * Diu a CtrlDomini quin és el TipusAtribut que conté els atributs que fan referència als titols dels items 
     * @param nomTipusAtributTitol el nom que identifica aquest TipusAtribut
     */
    public void setTipusAtributTitol (String nomTipusAtributTitol) throws Exception {//FET
        //CRIDA A CTRLDOMINI setTipusAtributTitol
        ctrlDomini.setTipusAtributTitol(nomTipusAtributTitol);
        //EL QUE HA DE FER CTRLDOMINI es setejar a la configuracio dels TipusAtributs el TipusAtribut que fa referencia al titol. Aquest TipusAtribut es el tipusAtribut que te el nom que sa passat per parametre
    }

    /**
     * Consulta a CtrlDomini si existeix un item o no
     * @param nom És el nom de l'ítem que es vol consultar l'existència
     * @return si existeix l'ítem
     */
    public boolean existeixItem (String nom) throws Exception {//FET
        //CRIDA A CTRLDOMINI existeixItem
        return ctrlDomini.existeixItem(nom);
        //EL QUE HA DE FER CTRLDOMINI es retornar si no existeix o existeix el item amb el nom (titol) que es passa per parametre

        //return false; //per provar interficie
        //return true; //per provar interficie

    }

    /**
     * Consulta a CtrlDomini el id d'un Ítem
     * @param titol és el nom de l'ítem que es vol consultar el id
     * @return és id de l'ítem
     */
    public int idItem (String titol) throws Exception {//FET
        //CRIDA A CTRLDOMINI idItem
        return ctrlDomini.getIdItem(titol);
        //EL QUE HA DE FER CTRLDOMINI es retornar el id del item que te com a nom (es a dir titol) el que es passa per parametre

        //return 1; //per provar la interficie
    }

    /**
     * consulta a CtrlDomini els atributs per a cada TipusAtribut de la base de dades, per a un ítem
     * @param titol el titol de l'ítem
     * @return els atributs de l'ítem, organitzats pels TipusAtributs en els que aquests es troben
     */
    public HashMap<String, String> getValorsTipusAtributDeItem (String titol) throws Exception {//FET
        //CRIDA A CTRLDOMINI getValorsTipusAtributDeItem
        return ctrlDomini.getValorsTipusAtributDeItem(titol);
        //EL QUE HA DE FER CTRLDOMINI es: 
        //          - Retornar un HashMap en que les keys son el nom de cada tipusAtribut que te definit el item amb el titol que sa passat com a parametre
        //          - El valor de les keys sera el atribut/atributs que te el item per cada tipusAtribut Pero com que el valor del map es String (ja que s'haura dimprimir), i atributs no tenen perque ser String, o poden ser mes de un String, llavors: 
        //              -- En cas que el atribut sigui int: Es converteix int a string
        //              -- En cas que el atribut sigui double: Es converteix double a string
        //              -- En cas que el atribut sigui String: Com que pot haver-hi diversos atributs (item per genere pot tenir drama, misteri etc) llavors tots aquest atributs string sencadenen en un sol String, separat per comes i espais, de forma que estigui ready per imrpimir
        //              -- En cas que el atribut sigui boolean: Es converteix bool a string ("TRUE", "FALSE") EXACTAMENT AIXI

        //NOMES SAN DE PASAR ELS TIPUSATRIBUTS DEFINITS. SI HI HA ALGUN QUE TE VALOR = NULL, AQUEST NO ES PASA AL MAP
        /*
        HashMap<String, String> test = new HashMap<String, String>(); //per provar interficie
        test.put("Any", "2021"); //per provar interficie
        test.put("Budget", "1"); //per provar interficie
        test.put("Popularity", "0.00001"); //per provar interficie
        test.put("Adult", "False"); //per provar interficie
        test.put("Genere", "Drama, Accio"); //per provar interficie
        return test; //per provar interficie
         */
    }

    /**
     * diu a CtrlDomini que l'usuari vol eliminar un item
     * @param titol es el titol de l'ítem que es vol eliminar
     */
    public void eliminaItem(String titol) throws Exception {//FET
        //CRIDA A CTRLDOMINI eliminaItem
        ctrlDomini.eliminaItem(titol);
        //EL QUE HA DE FER CTRLDOMINI es eliminar el item amb el titol que sa especificat
    }

    /**
     * diu a CtrlDomini que l'usuari vol crear un item
     * @param atributs els atributs de l'ítem
     */
    public void creaItem(HashMap<String, String> atributs) throws Exception {
        //CRIDA A CTRLDOMINI creaItem
        ctrlDomini.creaItem(atributs);
        //EL QUE HA DE FER CTRLDOMINI es crear un item nou, amb els atributs especificats. La forma en que es pasen els atributs com a paràmetre és la mateixa que
        // la que s'explica a la funció getValorsTipusAtributDeItem, però aqui CtrlDomini no els passa sinó que els rep. 
        // SA DANAR AMB COMPTE amb traduir totes els values de String al corresponent atr (int, double...).  ls de tipus Bool, s'especificarana amb string "TRUE" o amb la string "FALSE" DE FORMA EXACTA A AIXI
        //I TAMBE SA DANAT MOLT EN COMPTE amb els que siguin atrString, ja que si hi ha diferents atributs en el value del map per a un TipusAtribut de tipus String (per exemple: "Accio;Drama"), el que haurà de fer
        //CTRLOMINI es separar aquesta String en els diversos atributs que hi ha, en que el usuari ha dit que els introdueixi separantlos per un ";" tal com a l'arxiu de items
        //SI LUSUARI A DECIDIT NO ESPECIFICAR EL ATRIBUT PER A UN TIPUSATRIBUT, AQUEST DIRECTAMENT NO ES PASARA EN EL MAP
    }

    /**
     * diu a CtrlDomini que l'usuari vol crear una valoració d'un item
     * @param titolItem El titol de l'ítem del qual es vol crear la valoracio
     * @param puntuacio La puntuacií de la valoració. 
     * @param comentaris Els comentaris opcionals de la valoració
     */
    public void creaValoracio(String titolItem, Integer puntuacio, String comentaris) throws Exception {//FET
        //CRIDA A CTRLDOMINI creaValoracio
        ctrlDomini.creaValoracio(titolItem, puntuacio, comentaris);
        //EL QUE HA DE FER CTRLDOMINI es crear una valoracio de lusuari que te la sesio iniciada. 
        //Aquesta Valoracio serà de el item amb titol titolItem, la puntuacio sera un valor entre 0 i 5, i els comentaris poden ser nuls. 
        //anar en compte aqui amb tirar tots els throws; ex: quan litem amb el titol de parametre no existeixi, quan el usuari ja tingui una valoracio amb el item que te el titol especificat al parametre
    }

    /**
     * consulta a CtrlDomini tots els TipusAtributs que té una base de dades. D'aquests es consulta el nom d'aquests, i el seu tipus. 
     * @return la consulta
     */
    public HashMap<String, String> consultaTipusTipusAtributs () throws Exception {//FET
        //CRIDA A CTRLDOMINI consultaTipusTipusAtributs
        return ctrlDomini.consultaTipusTipusAtributs();
        //EL QUE HA DE FER CTRLDOMINI es retornar un map, en que els keys son els noms de tots els TipusAtribut
        //i values de les keys son un String que informa del tipus del TipusAtribut, es a dir un String que sera: "Int" o "Double" o "Bool" o "String"

        /*
        HashMap<String, String> test = new HashMap<String, String>(); //per provar interficie
        test.put("Any", "Int"); //per provar interficie
        test.put("Budget", "Int"); //per provar interficie
        test.put("Popularity", "Double"); //per provar interficie
        test.put("Adult", "Bool"); //per provar interficie
        test.put("Genere", "String"); //per provar interficie
        return test;
         */
    }

    /**
     * diu a CtrlDomini que l'usuari que té la sessió iniciada vol eliminar el seu usuari
     */
    public void eliminaUsuari () {//FET    //NO POT TIRAR EXCEPCIO SINO SERIA FATAL ERROR
        //CRIDA A CTRLDOMINI eliminaUsuari
        ctrlDomini.eliminarUsuariActiu();
        //EL QUE HA DE FER CTRLDOMINI es eliminar l'usuari que te sesio iniciada. Això implica eliminarlo de tot arreu, inclos tots els arxius, i tot el que sagi de eliminar sobre aquest usuari. No ens de preocupar de res mes, ja que quan es cridi
        //a la funcio eliminaUsuari, llavors el programa es tancara ja que evidentment ja no hi haura sesio iniciada
    }

    /**
     * consulta a CtrlDomini les valoracions de l'usuari que té la sessió iniciada. d'aquestes es retorna el nom de l'ítem al que fa referència la valoració, la puntucacio, i els comentaris
     * @return la consulta
     */
    public HashMap<String, Pair<Integer, String> > consultaValoracionsUsuari () throws Exception {//FET
        //CRIDA A CTRLDOMINI consultaValoracionsUsuari
        return ctrlDomini.consultaValoracionsUsuari();
        //EL QUE HA DE FER CTRLDOMINI es retornar les valoracions de l'usuari que te la sesio iniciada. Coses a tenir en compte:
        //      La forma en que aixo es retorna es en forma de map. Les keys son els titols dels litems de les valoracions de lusuari
        //          Els values de les keys, son un Pair en que el pair.first es la puntuacio, i second es el comentari (que es opcional), per tant pair.second podria ser nul

        /*
        HashMap<String, Pair<Integer, String> > test = new HashMap<String, Pair<Integer, String> >(); //per provar interficie
        Pair<Integer, String> one = new Pair<Integer, String>(5, null); //per provar interficie
        Pair<Integer, String> two = new Pair<Integer, String>(4, "comentari2"); //per provar interficie
        Pair<Integer, String> three = new Pair<Integer, String>(3, null); //per provar interficie
        Pair<Integer, String> four = new Pair<Integer, String>(5, "comentari4"); //per provar interficie
        Pair<Integer, String> five = new Pair<Integer, String>(2, "comentari5"); //per provar interficie
        test.put("pelicula1", one); //per provar interficie
        test.put("pelicula2", two); //per provar interficie
        test.put("pelicula3", three); //per provar interficie
        test.put("pelicula4", four); //per provar interficie
        test.put("pelicula5", five); //per provar interficie
        return test; //per provar interficie
         */
    }

    /**
     * diu a CtrlDomini que l'usuari vol eliminar una valoració que ell a fet
     * @param titol El titol de l'ítem que fa referència la valoració
     */
    public void eliminaValoracio (String titol) throws Exception {//FET
        //CRIDA A CTRLDOMINI eliminaValoracio
        ctrlDomini.eliminaValoracio(titol);
        //EL QUE HA DE FER CTRLDOMINI es eliminar la valoracio de lusuari actiu que com a titol la cosa que es pasa al parametre de la funcio
    }


}