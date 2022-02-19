/**
 CBFiltering.Java
 especificació de classe CBFiltering
 */
package domini;

import java.util.ArrayList;
import java.util.HashMap;


import domini.Excepcions.InsuficientsValoracionsExcepcio;
import utils.Pair;
import domini.Excepcions.InsuficientsItemsNoValoratsExcepcio;
import domini.Excepcions.InsuficientsItemsExcepcio;;


/**
 CBFiltering
 classe que calcula les recomanacions en funció de l'algorisme knn

 Conté un usuari, unes recomanacions, les distàncies entre ítems, un booleà que diu si les recomanacions són bones i k, el número de recomanacions que es volen obtenir.
 * */

public class CBFiltering extends Algorisme{


    /** Usuari sobre el que funciona l'algorisme*/
    private Usuari usuari; 
    
    /** Llista en la que es guardaran les recomanacions*/
    private ArrayList<Item> recomanacions; 

    /** Llista amb totes les distàncies entre ítems*/
    private HashMap<Item, HashMap<Item, Double>> totesDistancies;
    
    
    /** Booleà que diu si hi ha recomanacions basades en bons reviews*/
    private Boolean bonesRecomanacions; 

    /** Integer que diu el nombre de recomanacions que volem crear*/
    private int k; 
    
    /**CONSTRUCTORS*/

    public CBFiltering(Usuari usuari, HashMap<Item, HashMap<Item, Double>> totesDistancies, int k) throws InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio{
        this.usuari = usuari;
        this.totesDistancies = totesDistancies;
        this.recomanacions = new ArrayList<>(); 
        this.bonesRecomanacions = false;
        this.k = k;
        knn();
    }

    /**CONSULTORS */

    
    /**
     * @brief Getter de l'ArrayList de recomanacions guardades
     * @param <true>
     * @return Retorna un ArrayList amb les recomanacions guardades
    */
    public ArrayList<Item> getRecomanacions(){
        return recomanacions;
    }

    
    /**
     * @brief Getter del booleà que diu si hi ha bones recomanacions
     * @param <true>
     * @return Un booleà que diu si hi ha bones recomanacions
    */
    public Boolean getQualitatRecomanacions(){
        return bonesRecomanacions;
    }
    
    /**
     * @brief Getter de k, el nombre de recomanacions que volem obtenir
     * @param <true>
     * @return Un integer, el nombre de recomanacions que volem obtenir
    */
    public Integer getUsuariID(){
        return usuari.getId();
    }
    
    /**
     * @brief Getter de k, el nombre de recomanacions que volem obtenir
     * @param <true>
     * @return Un integer, el nombre de recomanacions que volem obtenir
    */
    public Integer getK(){
        return k;
    }


    /**MODIFICADORS*/

    
    /**
     * @brief Funció que recalcula les recomanacions
     * @param <true>
     * @return 
    */
    public void recalculaRecomanacions() throws InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio{
        this.recomanacions = new ArrayList<>(); 
        knn();
    }
    
    /**
     * @brief Funció que edita k i recalcula les recomanacions
     * @param k
     * @return 
    */
    public void editaK(int k) throws InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio{
        this.k = k; 
        recalculaRecomanacions();
    }

    /**
     * @brief Funció que edita les distàncies i recalcula les recomanacions
     * @param <true>
     * @return 
    */
    public void editaDistancies(HashMap<Item, HashMap<Item, Double>> totesDistancies) throws InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio{
        this.totesDistancies = totesDistancies;
        recalculaRecomanacions();
    }


    /**METODES*/

    /**
     * @brief Funció que obté les valoracions de l'usuari que vol recomanacions. Itera sobre aquesta llista no ordenada 
     * i guardem Item i la seva puntuació en un ArrayList.
     * A més, mira si hi ha alguna valoració amb puntuació >= 2.5, que per nosaltres significa
     * que a l'usuari li "agrada". Paralelament es comptarà a quina posició hi ha el 2.5 més baix.    
     * @param valoradesBones és l'ArrayList d'entrada que té els ítems i la seva puntuació 
     * @return es retonra un int, si aquest és -1 significa que no hi ha cap review "bó" (>=2.5). Sinó és l'última posició del review bó.
    */

    private Integer initializeReviews(ArrayList<Pair<Item,Double>> valoradesBones){
        Integer position = -1; 
        bonesRecomanacions = false; 

        for (HashMap.Entry<Item, Valoracio> entry : usuari.getItemsValoracions().entrySet()) {
            Item item = entry.getKey();
            Valoracio val = entry.getValue();
            Pair<Item,Double> parella = new Pair<>(item,val.getPuntuacio());
            Boolean inserted = false;

            for(int i = 0  ; i<valoradesBones.size() && ! inserted ; i++){
                if( val.getPuntuacio() > valoradesBones.get(i).second()){
                    valoradesBones.add(i,parella);
                    if(val.getPuntuacio() >= 2.5) position = i;
                    inserted = true;
                }
            }
            if(!inserted){
                valoradesBones.add(parella);
                if(val.getPuntuacio() >= 2.5) position = valoradesBones.size();
            }
        }

        if(position >=0) bonesRecomanacions=true;
        return position;
    }

    /**
     * @brief Funicó que retrona els ítems més semblants al d'entrada.  
     * @param item és l'ítem al qual volem obtenir els seus ítems semblants. 
     * @return ArrayList<Item> una llista amb els k ítems semblants
    */

    private ArrayList<Item> knearestToSingleItem ( Item item){
        //Aquí entrarem ja suposant que té suficient recomanacións

        ArrayList<Item> kNearestItems = new ArrayList<>();
        HashMap<Item, Double> distanciesAItem = totesDistancies.get(item);
        Boolean inserted = false;

        for (HashMap.Entry<Item, Double> entry : distanciesAItem.entrySet()) {
            Item itemRecomanat = entry.getKey();

            //Si no és la mateixa i no està valorada busca la distància
            if (item.getID() != itemRecomanat.getID() && !usuari.getItemsValoracions().containsKey(itemRecomanat)) {
                double dist = entry.getValue();

                //System.out.println("Distancia a item: "+ itemRecomanat.getID() +" valor "+dist);


                if(kNearestItems.size() == 0){
                     kNearestItems.add(itemRecomanat);
                     
                    // System.out.println("Afegeixo al principi: "+itemRecomanat.getID());
                }else{
                    inserted = false; 
                    for( int i = kNearestItems.size()-1; i >= 0 && !inserted ; i--){
                        if(dist >= distanciesAItem.get(kNearestItems.get(i)) && i==(k-1)) inserted=true;
                        else if(dist >= distanciesAItem.get(kNearestItems.get(i))){
                            kNearestItems.add(i+1,itemRecomanat);
                            inserted = true;
                        }else if(i == 0 && dist < distanciesAItem.get(kNearestItems.get(i))){
                            kNearestItems.add(0,itemRecomanat);
                            inserted = true;
                            //System.out.println("he trobat un valor millor "+itemRecomanat.getID());
                        }
                    }
                    
                }

            }
        }

        ArrayList<Item> aux = new ArrayList<>();
        for(int i = 0; i < kNearestItems.size() && i < k; i++){
            aux.add(kNearestItems.get(i));
        }

        //for(Item i:aux) System.out.println(i.getID());
       
        return aux;
    }
    
    
    /**
     * @brief Funicó que tria les millors valoracions en les que ha treballat. 
     * @param valoradesBones és la llista que té totes les valoracions, ordenades de més bones a més dolentes
     * @param position és la última posició en la que hi ha una valoració bona, si no n'hi ha position és ignorat
     * @return ArrayList<Pair<Item,Double>> una llista amb els reviews que s'utilitzaran per buscar la recomanació
    */
    private ArrayList<Pair<Item,Double>> chooseReviewsWorkedOn( ArrayList<Pair<Item,Double>> valoradesBones, int position){
        if(position >= 0){
            while(valoradesBones.size() > (position+1) )valoradesBones.remove(valoradesBones.size()-1);
        }else { 
            ArrayList<Pair<Item,Double>> aux = new ArrayList<>();  
            aux.add(valoradesBones.get(0));
            valoradesBones.clear();
            valoradesBones = aux; 
        } 
        return valoradesBones;
    }

    
    /**
     * @brief Funicó que retorna una puntuació dels items recomanats en funció de a quina distància es troba del ítem al que està relacionat i la valoració que té l'item valorat. 
     * @param valoradesBones és la llista que té totes les valoracions sobre les que es treballarà
     * @return HashMap<Item,Double> una llista d'items 
    */
    private HashMap<Item,Double> findDistances(ArrayList<Pair<Item,Double>> valoradesBones){
        HashMap<Item,Double> posicions = new HashMap<>();
        for (Pair<Item,Double> parella : valoradesBones) {
            Item itemValorat = parella.first();
            Double punt = parella.second();
            ArrayList<Item> n = knearestToSingleItem(itemValorat);
            Double divisor = (double) n.size();    
            for(int i = 0 ; i < n.size() ; i++){
                Double aSumar = punt + (divisor-i)/divisor;
                if(posicions.containsKey(n.get(i))){
                    Double valor = posicions.get(n.get(i));
                    valor += aSumar; 
                    posicions.put(n.get(i),valor);
                } else posicions.put(n.get(i),aSumar);
            }
        }
        return posicions; 
    }


    
    /**
     * @brief Funció agafa els k ítems amb puntuació més alta i extreu els que sobren. Finalment retorna un ArrayList d'ítems.  
     * @param posicions és la llista no ordenada de ítems que poden ser recomanats
    */
    private void orderAndRemove(HashMap<Item,Double> posicions){
        double max = 0;
        Item iaux = new Item();

        for(int i = 0; i < k; i++){
            for(HashMap.Entry<Item, Double> entry : posicions.entrySet()) {
                if(entry.getValue() > max){
                    max = entry.getValue();
                    iaux = entry.getKey();
                }
            }
            if(max != 0 && (iaux.getID() != -1)){
                max = 0;
                recomanacions.add(iaux);
                posicions.remove(iaux);
            }
            else i = k;
        }
        
    }


    /**
     * @brief Algorisme de recomanació basat en knn, ens retorna els k ítems més semblants als ítems que ens han agradat. 
     * @return ArrayList<Item> recomanacions, una llista de k recomanacions
    */

    private ArrayList<Item> knn () throws InsuficientsValoracionsExcepcio, InsuficientsItemsExcepcio, InsuficientsItemsNoValoratsExcepcio{
        if (usuari.getItemsValoracions().isEmpty()) throw new InsuficientsValoracionsExcepcio();
        if(totesDistancies.isEmpty()) throw new InsuficientsItemsExcepcio();
        
        ArrayList<Pair<Item,Double>> valoradesBones = new ArrayList<>(); 
        

        /*
        * Obtenim les valoracions de l'usuari que vol recomanacions, iterem sobre aquesta llista no 
        * ordenada i guardem Item i la seva puntuació en un ArrayList, ja que aquest ens assegura que 
        * es mantindrà l'ordre, mentre que el HashMap no. 
        * A més, mirarem si hi ha alguna valoració amb puntuació >= 2.5, que per nosaltres significa
        * que a l'usuari li "agrada". Si hi ha una valoració així posarem a true el booleà 
        * "thereAreGoodReviews". Paralelament es comptarà a quina posició hi ha el 2.5 més baix.  
        */
        Integer position = initializeReviews(valoradesBones);

        /*
        * De la llista de reviews ordenada agafa només les que ens interessa.
        * En el cas que hi hagi valoracions >=2.5 (és a dir,  "bones") agafa les k posicions millor valorades.
        * En el cas que totes les valoracions <2.5 només s'agafarà la primera.
        * Creiem que si totes les valoracions són dolentes millor no agafar-les totes, sinó només la que li ha 
        * agradat més d'aquestes. 
        */
        valoradesBones = chooseReviewsWorkedOn(valoradesBones, position);
        
        
        /*
        * En funció a les distàncies dels items valorats a tots els que existeixen es donen unes puntuacions. 
        * Aquetes es guarden a la llista posicions. En el cas que posicions es retorni buit llavors saltarà error. 
        */
        HashMap<Item,Double> posicions = findDistances(valoradesBones);
        if(posicions.isEmpty()) throw new InsuficientsItemsNoValoratsExcepcio();

        /* 
        * Finalment s'hagafen les k posicions més altes i es guarden a recomanacions, s'eliminen les recomanacions extres. 
        */
        orderAndRemove(posicions);

        return recomanacions;
    }
}

