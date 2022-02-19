package domini.Drivers;

import persistencia.DataReader;
import utils.Pair;
import domini.*;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class DrvAvaluacioRecomanacioCBF {

    private static HashMap<Integer, Usuari> usuaris = new HashMap<>();
    private static HashMap<Integer, Item> items = new HashMap<>();


    public static void main(String[] args) throws Exception {

        Map<String, TipusAtribut> tipusAtributs = new HashMap<>(); //TIPUSATRIBUT
        Map<Pair<Item, Usuari>, Valoracio> valoracions = new HashMap<>();
        HashMap<Integer, HashMap<Integer,Double>> unknown = new HashMap<>();

        DataReader.carregaDades(tipusAtributs, items, usuaris,valoracions,unknown);

        
        //ArrayList<ArrayList<String>> unknown = llegeixDades("/src/data/series.public/250/ratings.test.unknown.csv");
        Integer userId;
        Integer itemId;
        double rating;

        double mitjanaDCG = 0;
        double mitjanaIDCG = 0;
        double mitjanaNDCG = 0;


        Set<Item> itemsF = new HashSet<Item>(items.values());
        HashMap<Item,HashMap<Item,Double>> distancies = new HashMap<>();
        DistItem dist = new DistItem(itemsF);
        distancies = dist.getTotesDistancies();


        for (HashMap.Entry<Integer, HashMap<Integer, Double>> user : unknown.entrySet() ) {
            userId = user.getKey();
            CBFiltering cbf = new CBFiltering(usuaris.get(userId), distancies);
            HashMap<Item, Double> lt = new HashMap<>();
            

            for( HashMap.Entry<Integer,Double> entry : unknown.get(userId).entrySet()){

                itemId = entry.getKey();
                rating = entry.getValue();
                lt.put(items.get(itemId), rating);

            }

            double DCG = 0;
            double IDCG = 0;
            double NDCG = 0;

            for (int execucions = 0; execucions < 20; ++execucions) {
                ArrayList<Item> lr = new ArrayList<>(cbf.knn(10));
                while (lr.size() > 10) {
                    lr.remove(lr.size() - 1);
                }

                DCG dcg = new DCG(lt, lr);
                dcg.calculaDGC();
                DCG += dcg.getDCG();
                IDCG += dcg.getIDCG();
                NDCG += dcg.getNDCG();
            }

            DCG /= 20.0;
            IDCG /= 20.0;
            NDCG /= 20.0;

            System.out.println("-------------------------------------------------");
            System.out.println("DCG per l'usuari " + userId + " és:");
            System.out.println("DCG: " + DCG);
            System.out.println("IDCG: " + IDCG);
            System.out.println("NDCG: " + NDCG);
            System.out.println("-------------------------------------------------");
            System.out.println();

            mitjanaDCG += DCG;
            mitjanaIDCG += IDCG;
            mitjanaNDCG += NDCG;
        }

        System.out.println("Mitjana de DCG per tots els usuaris és:");
        System.out.println("DCG: " + mitjanaDCG/unknown.size());
        System.out.println("IDCG: " + mitjanaIDCG/unknown.size());
        System.out.println("NDCG: " + mitjanaNDCG/unknown.size());


    }

}