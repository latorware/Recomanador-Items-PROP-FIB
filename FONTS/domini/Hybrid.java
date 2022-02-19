/**
 Hybrid.Java
 especificació de classe Hybrid
 */
package domini;

import java.util.ArrayList;

import domini.Excepcions.CapClusterExcepcio;
import domini.Excepcions.InsuficientsItemsExcepcio;
import domini.Excepcions.InsuficientsItemsNoValoratsExcepcio;
import domini.Excepcions.InsuficientsValoracionsExcepcio;
import domini.Excepcions.MassaClustersExcepcio;
import domini.Excepcions.UsuarisNoCoincidents;

/**
 Hybrid

classe que calcula les recomanacions en funció de les classes colaborative filtering i content based filtering

 Conté un CBFiltering , un COLFiltering, k (el nombre de recomanacions que volem) i un ArrayList de les últimes recomanacions calculades.

 * */

public class Hybrid extends Algorisme{
    
    /** Coontent Based filtering per crear recomanacions */
    private CBFiltering cbf; 

    /** Collaborative filtering per crear recomanacions */
    private COLFiltering colf;

    /** Integer que diu el nombre de recomanacions que volem crear*/
    private int k;

    /** Llista en la que es guardaran les recomanacions*/
    private ArrayList<Item> recomanacions; 


    
    /**CONSTRUCTORS*/
    public Hybrid(CBFiltering cbf, COLFiltering colf, int k)throws UsuarisNoCoincidents, InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio, MassaClustersExcepcio, CapClusterExcepcio{
        this.cbf = cbf;
        this.colf = colf;
        if(!cbf.getUsuariID().equals(colf.getUsuariID())) throw new UsuarisNoCoincidents();
        this.k = k;
        this.recomanacions = new ArrayList<>();
        if(cbf.getK()!=k) cbf.editaK(k);
        if(colf.getK()!=k) colf.editaK(k);
        calculRecomanacions(); 
    }
    
    /**MODIFICADORS*/

    /**
     * @brief Getter de l'ArrayList de recomanacions guardades
     * @param <true>
     * @return Retorna un ArrayList amb les recomanacions guardades
    */
    public ArrayList<Item> getRecomanacions(){
        return this.recomanacions;
    }


    /**METODES*/

    /**
     * @brief Mètode que troba repeticions entre dues ArrayLists
     * @param recomanacionsA Llista que té preferència a l'hora de ser recomanada
     * @param recomanacionsB Llista que no té preferència a l'hora de ser recomanda
     * @return Retorna un ArrayList d'ítems amb les repeticions entre llistes
    */
    private ArrayList<Item> trobaRepeticions(ArrayList<Item> recomanacionsA, ArrayList<Item> recomanacionsB){
        ArrayList<Item> repeticions = new ArrayList<>();
        for(Item iA : recomanacionsA){
            for(Item iB : recomanacionsB){
                if( iA.getID() == iB.getID() ){
                    repeticions.add(iA);
                }
            }
        }

        for(Item i: repeticions){
            recomanacionsA.remove(i);
            recomanacionsB.remove(i);
        }

            
        return repeticions; 
    }

    
    /**
     * @brief Mètode que uneix les dues llistes de recomanacions. En el cas que una llista tingui males recomanacions, per una recomanació d'ella l'altre en tindrà el doble
     * @param recomanacionsCBF Llista de recomanacions creades per collaborative filtering
     * @param recomanacionsCOL Llista de recomanacions creades per content-based filtering
     * @return Retorna un ArrayList de k ítems amb les millors recomanacions de les dues llistes
    */
    private void unirRecomanacions(ArrayList<Item> recomanacionsCBF, ArrayList<Item> recomanacionsCOL, Boolean malsReviews){
        int totalSizes = recomanacionsCOL.size() + recomanacionsCBF.size();
        int maxCOL= 1; 
        int maxCBF= 1; 
        if (malsReviews)maxCOL = 2; 
        int comptadorCOL = maxCOL; 
        int comptadorCBF = maxCBF; 

        while(recomanacions.size() < k  &&  totalSizes > 0){
            if(comptadorCOL > 0 && !recomanacionsCOL.isEmpty()){
                recomanacions.add(recomanacionsCOL.get(0));
                recomanacionsCOL.remove(0);
                --comptadorCOL;
            }else if(comptadorCBF > 0 && !recomanacionsCBF.isEmpty()){
                recomanacions.add(recomanacionsCBF.get(0));
                recomanacionsCBF.remove(0);
                --comptadorCBF;
            }else{
                comptadorCBF = maxCBF;
                comptadorCOL = maxCOL;
            }

            totalSizes = recomanacionsCOL.size() + recomanacionsCBF.size(); 
        }
    }

    
    /**
     * @brief Mètode que calcula les recomanacions. 
     * @param <true>
     * @return Retorna un ArrayList de k ítems amb les millors recomanacions de les dues llistes
    */
    public ArrayList<Item> calculRecomanacions(){
        this.recomanacions = new ArrayList<>(); 
        boolean malsReviews = false; 

        //El percentatge existeix si hi ha mals reviews de cbf
        if(!cbf.getQualitatRecomanacions()) malsReviews = true; 
        //En el cas de colf passa una cosa semblant si no hi ha suficients usuaris

        ArrayList<Item> recomanacionsCOL = new ArrayList<>(colf.getRecomanacions());
        ArrayList<Item> recomanacionsCBF = new ArrayList<>(cbf.getRecomanacions());
        if(malsReviews) recomanacions = trobaRepeticions(recomanacionsCOL,recomanacionsCBF);
        else recomanacions = trobaRepeticions(recomanacionsCBF,recomanacionsCOL);
        
        unirRecomanacions(recomanacionsCBF, recomanacionsCOL, malsReviews);

        return recomanacions;
    }
    



}

