package domini.controladors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import domini.CBFiltering;
import domini.COLFiltering;
import domini.DistItem;
import domini.Hybrid;
import domini.Item;
import domini.Usuari;
import domini.Excepcions.CapClusterExcepcio;
import domini.Excepcions.InsuficientsItemsExcepcio;
import domini.Excepcions.InsuficientsItemsNoValoratsExcepcio;
import domini.Excepcions.InsuficientsValoracionsExcepcio;
import domini.Excepcions.ItemNoAfegit;
import domini.Excepcions.ItemNul;
import domini.Excepcions.MassaClustersExcepcio;
import domini.Excepcions.SetItemsNul;
import domini.Excepcions.SetUsuarisNul;
import domini.Excepcions.UsuariAAfegirEsAnalitzat;
import domini.Excepcions.UsuariJaAfegit;
import domini.Excepcions.UsuariNul;
import domini.Excepcions.UsuarisNoCoincidents;

public class CtrlDominiRecomanacio {

    /**Instancia de control domini*/
    private CtrlDomini instanciaCtrlDomini; 
    /**Llista de recomanacions fetes per Content Based Filtering*/
    private ArrayList<Item> recomanacioCB = new ArrayList<>();
    /**Llista de recomanacions fetes per Collaborative Filtering*/
    private ArrayList<Item> recomanacioCOL = new ArrayList<>();
    /**Llista de recomanacions fetes per Hibrid Filtering*/
    private ArrayList<Item> recomanacioHibrid = new ArrayList<>();

    /**Instància de classe distància item*/
    private DistItem distItem = null; 
    /**Instància de classe Content Based Filtering*/
    private CBFiltering cbFiltering; 
    /**Instància de classe Collaborative Filtering*/
    private COLFiltering colFiltering; 
    /**Instància de classe Hybrid Filtering*/
    private Hybrid hybrid; 

    /**Boolean que diu si cal recalcular les recomanacions, o es poden reaprofitar*/
    private boolean aprofitaRecomanacions = true; //abans estava a false

    /**Usuari que té la sessió iniciada en aquell moment*/
    private Usuari usuariSessioIniciada; 
    /**Hash Map dels usuaris que hi ha guardats*/
    private HashMap<Integer, Usuari> totsUsuaris; 

    /**
     * Instancia de la propia classe. Propietat singleton.
     */
    private static CtrlDominiRecomanacio instance;

    private static final int k = 34; 
    
    /**
     * Propietat singleton.
     * Comprova si ja existeix una instancia de la classe.
     * En cas de que no existeixi crida a la constructora abans.
     *
     * @return instancia de la clase.
     */
    public static CtrlDominiRecomanacio getInstance() {
        if (instance == null) {
            instance = new CtrlDominiRecomanacio();
        }
        return instance;
    }

    public CtrlDominiRecomanacio(){}

        //INICIALITZADORS

    /**
    * Funció per inicialitzar el controlador, 
    * @param recomanacionsGuardadesCB, ArrayList<Item>
    * @param recomanacionsGuardadesCOL, ArrayList<Item>
    * @param recomanacionsGuardadesHibrid, ArrayList<Item>
    * @returns
    */
    public void inicialitzarControlador(ArrayList<Item> recomanacionsGuardadesCB, ArrayList<Item> recomanacionsGuardadesCOL, ArrayList<Item> recomanacionsGuardadesHibrid) {
        if (recomanacionsGuardadesCB.isEmpty() || recomanacionsGuardadesCOL.isEmpty() || recomanacionsGuardadesHibrid.isEmpty()) {
            //aprofitaRecomanacions = false;
        }
        else {
            this.recomanacioCB = recomanacionsGuardadesCB;
            this.recomanacioHibrid = recomanacionsGuardadesHibrid;
            this.recomanacioCOL = recomanacionsGuardadesCOL;
            //aprofitaRecomanacions = true;
        }
    }

    //Sa de cridar quan no hi hagi canvis pero lusuari vulgui especificar la configuracio
    
    /**Setter que posa el booleà aprofitaRecomanacions a false*/
    public void setNoAprofitaRecomanacions () {
        aprofitaRecomanacions = false; 
    }

    /**
    * Funció per acutalitzar els items, usuaris i usuari actiu als seus algorismes corresponents. 
    * @param instanciaCtrlDomini, CtrlDomini
    * @param usuariSessioIniciada, Usuari 
    * @param totsItem, Set<Item>
    * @param totsUsuari,Map<Integer, Usuari> 
    */
    public void passaTotsUsuarisItemsIUsuariIniciat(CtrlDomini instanciaCtrlDomini, Usuari usuariSessioIniciada, Set<Item> totsItem, Map<Integer, Usuari> totsUsuari) throws SetItemsNul, ItemNul, InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio, UsuariAAfegirEsAnalitzat, UsuariNul, UsuariJaAfegit, SetUsuarisNul, MassaClustersExcepcio, CapClusterExcepcio, UsuarisNoCoincidents {
        this.instanciaCtrlDomini = instanciaCtrlDomini; 
        this.usuariSessioIniciada = usuariSessioIniciada;
        distItem = new DistItem(totsItem); 
        this.totsUsuaris = (HashMap<Integer, Usuari>) totsUsuari;

        if (!aprofitaRecomanacions) {
            if (instanciaCtrlDomini.almenysUnaValoracioFeta()) {
                cbFiltering = new CBFiltering(usuariSessioIniciada, distItem.getTotesDistancies(), k); 
                colFiltering = new COLFiltering((HashMap<Integer, Usuari>) totsUsuari, k, usuariSessioIniciada);
                hybrid = new Hybrid(cbFiltering, colFiltering, k); 
            }

        }
        //Si aprofitaRecomanacions es TRUEEE llavors no cal inicialitzar cbFiltering i colFiltering
    }



    /**
    * Getter de les recomanacions calculades per Collaborative Filtering
    * @returns recomanacioCOL, ArrayList<Item>
    */
    public ArrayList<Item> getRecomanacionsCOL () {
        return recomanacioCOL; 
    }
    
    /**
    * Getter de les recomanacions calculades per Content Based Filtering
    * @returns recomanacioCB, ArrayList<Item>
    */

    public ArrayList<Item> getRecomanacionsCB () {
        return recomanacioCB; 
    }
    
    /**
    * Getter de les recomanacions calculades per Hybrid Filtering
    * @returns recomanacioHibridL, ArrayList<Item>
    */
    public ArrayList<Item> getRecomanacionsHybrid () {
        return recomanacioHibrid; 
    }
    
    /**
    * Getter de les del booleà que diu si s'aprofiten les recomanacions
    * @returns aprofitaRecomanacions, boolean
    */
    public boolean isAprofitaRecomanacions() {
        return aprofitaRecomanacions;
    }
//MODIFICADORS

    /**
    * Mètode que refresca totes les recomanacions quan s'afegeix una nova valoracio
    * @returns 
    */
    public void refrescaPerValoracioAfegida() throws InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio, UsuariAAfegirEsAnalitzat, UsuariNul, UsuariJaAfegit, SetUsuarisNul, MassaClustersExcepcio, CapClusterExcepcio, UsuarisNoCoincidents {
        if (instanciaCtrlDomini.almenysUnaValoracioFeta()) {
            if (cbFiltering == null) { //llavors els cb i co son son null
                cbFiltering = new CBFiltering(usuariSessioIniciada, distItem.getTotesDistancies(), k);
                colFiltering = new COLFiltering(totsUsuaris, k, usuariSessioIniciada);
                hybrid = new Hybrid(cbFiltering, colFiltering, k);//aixo no estava
                recomanacioCB = cbFiltering.getRecomanacions(); 
                recomanacioCOL = colFiltering.getRecomanacions(); 
                recomanacioHibrid = hybrid.getRecomanacions(); 
            }
            else {
                cbFiltering.recalculaRecomanacions();
                colFiltering = new COLFiltering(totsUsuaris, k, usuariSessioIniciada);
                hybrid = new Hybrid(cbFiltering, colFiltering, k);//aixo no estava
                recomanacioCB = cbFiltering.getRecomanacions(); 
                recomanacioCOL = colFiltering.getRecomanacions(); 
                recomanacioHibrid = hybrid.calculRecomanacions(); 
            }
        }



    }

    /**
    * Mètode que refresca totes les recomanacions quan s'elimina una valoracio
    * @returns 
    */
    public void refrescaPerValoracioEliminada() throws InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio, UsuariAAfegirEsAnalitzat, UsuariNul, UsuariJaAfegit, SetUsuarisNul, MassaClustersExcepcio, CapClusterExcepcio, UsuarisNoCoincidents {
        if (instanciaCtrlDomini.almenysUnaValoracioFeta()) {
            if (cbFiltering == null) { //llavors els cb i co son son null
                cbFiltering = new CBFiltering(usuariSessioIniciada, distItem.getTotesDistancies(), k); 
                colFiltering = new COLFiltering(totsUsuaris, k, usuariSessioIniciada);
                hybrid = new Hybrid(cbFiltering, colFiltering, k);//aixo no estava
                recomanacioCB = cbFiltering.getRecomanacions(); 
                recomanacioCOL = colFiltering.getRecomanacions(); 
                recomanacioHibrid = hybrid.getRecomanacions(); 
            }
            else {
                cbFiltering.recalculaRecomanacions();
                colFiltering = new COLFiltering(totsUsuaris, k, usuariSessioIniciada);
                hybrid = new Hybrid(cbFiltering, colFiltering, k);//aixo no estava
                recomanacioCB = cbFiltering.getRecomanacions(); 
                recomanacioCOL = colFiltering.getRecomanacions(); 
                recomanacioHibrid = hybrid.calculRecomanacions(); 
            }
            
        }
        else {
            cbFiltering = null; 
            colFiltering = null; 
            hybrid = null; 
        }

    }

    /**
    * Getter l'usuari que té la sessió iniciada
    * @returns usuariSessioIniciada, Usuari
    */
    public Usuari getUsuariSessioIniciada() {
        return usuariSessioIniciada;
    }

    /**
    * Getter del mapa de tots els usuaris
    * @returns totsUsuaris, HashMap<Integer, Usuari>
    */
    public HashMap<Integer, Usuari> getTotsUsuaris() {
        return totsUsuaris;
    }

    /**
    * Mètode que refresca totes les recomanacions quan s'afegeix un item
    * @param itemAfegit, Item
    * @returns 
    */ 
    public void refrescaPerItemAfegit(Item itemAfegit) throws ItemNul, InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio, UsuariAAfegirEsAnalitzat, UsuariNul, UsuariJaAfegit, SetUsuarisNul, MassaClustersExcepcio, CapClusterExcepcio, UsuarisNoCoincidents {
        distItem.afegirItem(itemAfegit);
        if (instanciaCtrlDomini.almenysUnaValoracioFeta()) {
            if (cbFiltering == null) { //llavors els cb i co son son null
                cbFiltering = new CBFiltering(usuariSessioIniciada, distItem.getTotesDistancies(), k); 
                colFiltering = new COLFiltering(totsUsuaris, k, usuariSessioIniciada);
                hybrid = new Hybrid(cbFiltering, colFiltering, k);//aixo no estava
                recomanacioCB = cbFiltering.getRecomanacions(); 
                recomanacioCOL = colFiltering.getRecomanacions(); 
                recomanacioHibrid = hybrid.getRecomanacions(); 
            }
            else {
                cbFiltering.editaDistancies(distItem.getTotesDistancies());
                colFiltering = new COLFiltering(totsUsuaris, k, usuariSessioIniciada);
                hybrid = new Hybrid(cbFiltering, colFiltering, k);//aixo no estava
                recomanacioCB = cbFiltering.getRecomanacions(); 
                recomanacioCOL = colFiltering.getRecomanacions(); 
                recomanacioHibrid = hybrid.calculRecomanacions(); 
            }
        }

    }

    /**
    * Mètode que refresca totes les recomanacions quan s'elimina un item
    * @param itemEliminat, Item
    * @returns 
    */ 
    public void refrescaPerItemEliminat(Item itemEliminat) throws ItemNoAfegit, InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio, UsuariAAfegirEsAnalitzat, UsuariNul, UsuariJaAfegit, SetUsuarisNul, MassaClustersExcepcio, CapClusterExcepcio, UsuarisNoCoincidents {
        distItem.eliminarItem(itemEliminat);
        if (instanciaCtrlDomini.almenysUnaValoracioFeta()) {
            if (cbFiltering == null) { //llavors els cb i co son son null
                cbFiltering = new CBFiltering(usuariSessioIniciada, distItem.getTotesDistancies(), k); 
                colFiltering = new COLFiltering(totsUsuaris, k, usuariSessioIniciada);
                hybrid = new Hybrid(cbFiltering, colFiltering, k);//aixo no estava
                recomanacioCB = cbFiltering.getRecomanacions(); 
                recomanacioCOL = colFiltering.getRecomanacions(); 
                recomanacioHibrid = hybrid.getRecomanacions(); 
            }
            else {
                cbFiltering.editaDistancies(distItem.getTotesDistancies());
                colFiltering = new COLFiltering(totsUsuaris, k, usuariSessioIniciada);
                hybrid = new Hybrid(cbFiltering, colFiltering, k);//aixo no estava
                recomanacioCB = cbFiltering.getRecomanacions(); 
                recomanacioCOL = colFiltering.getRecomanacions(); 
                recomanacioHibrid = hybrid.calculRecomanacions(); 
            }
        }

    }
}

