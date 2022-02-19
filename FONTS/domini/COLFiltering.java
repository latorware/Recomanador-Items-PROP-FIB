package domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import domini.Excepcions.CapClusterExcepcio;
import domini.Excepcions.MassaClustersExcepcio;
import domini.Excepcions.SetUsuarisNul;
import domini.Excepcions.UsuariAAfegirEsAnalitzat;
import domini.Excepcions.UsuariJaAfegit;
import domini.Excepcions.UsuariNul;

public class COLFiltering {
    
    
    /**Usuari pel que es crearan les recomanacions*/
    private Usuari usuari; 
    /**Mapa d'usuaris identificats per id*/
    private HashMap<Integer,Usuari> usuaris;
    /**Mapa de distàncies entre dos usuaris*/
    private HashMap<Integer,HashMap<Integer,Double>> distancies;
    /**Valor k, el número de recomanacions que es calcularan*/
    private int k;

    /** Llista en la que es guardaran les recomanacions*/
    private ArrayList<Item> recomanacions; 

    
    public COLFiltering(HashMap<Integer,Usuari> usuaris, int k, Usuari usuari) throws Excepcions.UsuariAAfegirEsAnalitzat,
            Excepcions.UsuariNul, Excepcions.UsuariJaAfegit, Excepcions.SetUsuarisNul, MassaClustersExcepcio, CapClusterExcepcio {
        this.usuari = usuari;
        this.usuaris = new HashMap<>(usuaris);
        calcularDistancies();
        if (k > usuaris.size() || k <= 0) this.k = 3;
        this.k = k;
        this.recomanacions = new ArrayList<>();
        recalculaRecomanacions();
    }
    
    /** Getter de les recomanacions. 
    * @returns recomamancions, ArrayList<Item>
    */
    public ArrayList<Item> getRecomanacions(){
        return this.recomanacions;
    }
    
    /** Getter de l'id de l'usuari pel que es calculen les recomanacions
    * @returns Integer
    */
    public Integer getUsuariID(){
        return usuari.getId();
    }
    
    /** Getter de la k, el nombre de recomanacions que es calcularan
    * @returns Integer
    */
    public Integer getK(){
        return k;
    }
    
    public String escriuRecomanacions(){
        String escriure = ""; 
        for(int i = 0; i < k && i<recomanacions.size() ; i++ )
            escriure = escriure + "\n" + recomanacions.get(i);
        
        return escriure;
    }
    
    /**
    * Modificador dels usuaris pels que es calcularan les distàncies
    * @param usuaris, HashMap<Integer,Usuari>
    * @returns
    */

    public void editaUsuaris(HashMap<Integer,Usuari> usuaris) throws UsuariAAfegirEsAnalitzat, UsuariNul, UsuariJaAfegit, SetUsuarisNul{
        this.usuaris = usuaris;
        calcularDistancies();
    }
    
    /**
    * Modificador de la k
    * @param k, Integer
    * @returns
    */

    public void editaK(int k){
        if (k > usuaris.size() || k <= 0) this.k = 3;
        this.k = k; 
    }

    /**
    * Mètode privat que calcula les distàncies entre usuaris i actualitza el mapa distancies
    * @returns
    */
    private void calcularDistancies() throws Excepcions.UsuariAAfegirEsAnalitzat, Excepcions.UsuariNul,
            Excepcions.UsuariJaAfegit, Excepcions.SetUsuarisNul {

        distancies = new HashMap<>();
        for (HashMap.Entry<Integer,Usuari> entry : usuaris.entrySet()) {
            HashSet<Usuari> us = new HashSet<>(usuaris.values());
            us.remove(usuaris.get(entry.getKey()));
            DistUsuari dist = new DistUsuari(entry.getValue(),us);

            HashMap<Usuari,Double> distancia = new HashMap<>(dist.getDistancies());
            HashMap<Integer,Double> intDoub = new HashMap<>();

            for (HashMap.Entry<Usuari,Double> entry2 : distancia.entrySet()) {
                if (entry.getKey() == entry2.getKey().getId()){
                    intDoub.put(entry2.getKey().getId(), 0.0);
                }
                else intDoub.put(entry2.getKey().getId(), entry2.getValue());
            }
            distancies.put(entry.getKey(),intDoub);
        }
    }


    /**
    * Mètode public que calcula les recomanacions, actualitza el arrayList recomanacions
    * @returns
    */
    public ArrayList<Item> recalculaRecomanacions() throws Excepcions.MassaClustersExcepcio, Excepcions.CapClusterExcepcio {
        this.recomanacions = new ArrayList<>();  
        KMeans kmeans = new KMeans(distancies);
        kmeans.calcularClusters(k);

        //Usuaris mateix cluster
        HashSet<Integer> clusters = new HashSet<>(kmeans.usuarisMateixCluster(usuari));

        HashMap<Integer, Usuari> usuarisVeins = new HashMap<>();
        for (Integer entry : clusters) {
            usuarisVeins.put(entry, usuaris.get(entry));
        }

        HashSet<Item> itemsAValorar = new HashSet<>();
        for (Integer entry : clusters) {
            HashMap<Item, Valoracio> itemsValorats = new HashMap<>(usuaris.get(entry).getItemsValoracions());
            for (HashMap.Entry<Item, Valoracio> entry2 : itemsValorats.entrySet()) {
                if (!usuari.getItemsValoracions().containsKey(entry2.getKey())) itemsAValorar.add(entry2.getKey());
            }
        }

        HashMap<Item, Double> mapRatings = new HashMap<>();

        SlopeOne slopeOne = new SlopeOne(usuari, usuarisVeins);

        for (Item entry : itemsAValorar) {
            double ratingRecomanacio = slopeOne.prediccio(entry);

            mapRatings.put(entry, ratingRecomanacio);
            int j;
            for (j = 0; j < recomanacions.size(); ++j) {
                if (ratingRecomanacio > mapRatings.get(recomanacions.get(j))) {
                    break;
                }
            }
            recomanacions.add(j, entry);
        }

        while (recomanacions.size() > k) {
            recomanacions.remove(recomanacions.size() - 1);
        }
        return recomanacions;

    }

}
