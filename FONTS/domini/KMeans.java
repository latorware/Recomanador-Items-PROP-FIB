package domini;

import java.util.*;

import domini.Excepcions.MassaClustersExcepcio;
import domini.Excepcions.CapClusterExcepcio;

public class KMeans {

    /*
    L'algorisme k-Means és un mètode d'agrupament que té com a objectiu la
    partició d'un conjunt de n elements en k clústers en el qual cada observació
    pertany al grup més proper a la mitjana del clúster.

    En aquest cas volem fer clústers d'usuaris (u1, u2, ..., un).
    Volem fer k particions de tal forma que cada usuari estigui amb un grup
    d'usuaris semblants. Per tal de determinar la proximitat entre dos usuaris diferents
    posseïm la distància entre cada parell d'usuaris. La distància és la distància euclidea
    i es calcula a partir de les valoracions de cada usuari a un ítem.
     */

    //ATRIBUTS

    /**
     * MAX_VALUE és un real que representa el valor infinit, aquest es fa servir per definir una distància màxima
     * a partir de la qual es calcula la distància mínima a un centroide.
     */
    private static final Double maxValue = Double.MAX_VALUE;

    /**
     * clusters és una ArrayList on a cada posició hi ha un HashMap amb els usuaris que conformen cada cluster
     */
    private ArrayList<HashSet<Integer>> clusters = new ArrayList<>();

    /**
     * distancies és un HashMap de HashMaps de idUsari, distància. Aquestes son afegides per la constructora del
     * k-Means i les calcula la classe DistUsuari.
     */
    private HashMap<Integer,HashMap<Integer,Double>> distancies;


    //CONSTRUCTORA

    /**
     * Constructora inicial. Crea una instància de la classe, pero sense calcular cap cluster, a més es guarden les
     * distàncies entre usuaris.
     * @param distancies Són les distàncies entre cada parell d'usuaris, no existeix distància entre un usuari
     *                   i ell mateix, aquesta distància serà considerada 0 pel mateix algorisme.
     */
    public KMeans(HashMap<Integer,HashMap<Integer,Double>> distancies) {
        this.distancies = distancies;
    }


    //OPERACIONS

    /**
     * Busca el centroide que té distància mínima des d'un usuari.
     * @param dist Són les distàncies entre un usuari "usuari" i tots els altres usuaris.
     * @param usuari És l'usuari que està buscant el centroide a mínima distància.
     */
    private Integer buscaCentroideMinDist(HashMap<Integer,Double> dist, Integer usuari) {
        int centroide = -1;
        double min_dist = maxValue;
        for(int i = 0; i < clusters.size(); ++i) {
            double mitjana = 0;
            /* La distància d'un centroide a un usuari serà la mitjana de les distàncies dels usuaris
               del cluster a l'usuari. No calculem la distància al centroide directament, ja que no tenim punts
               en l'espai, sinó que només disposem de les distàncies entre tots els usuaris.
            */

            for (Integer entry : clusters.get(i)) {
                if (!Objects.equals(entry, usuari)) mitjana += dist.get(entry);
            }
            mitjana /= clusters.get(i).size();
            if (mitjana < min_dist) {
                min_dist = mitjana;
                centroide = i;
            }
        }

        return centroide;
    }

    /**
     * Calcula els k clusters i introdueix cada usuari al corresponent cluster.
     * @param k És l'usuari del qual volem trobar tots els seus veins (usuaris del mateix cluster).
     */
    public void calcularClusters(int k) throws MassaClustersExcepcio, CapClusterExcepcio {
        /* Primer escollim k usuaris random per ser els centroides
           Després classifiquem cada usuari al centroide més proper
           La distància d'un usuari X a un centroide es calcularà com a la mitjana de cada totes les distàncies
           de cada usuari que formi part del centroide a l'usuari X.
        */
        if (k > distancies.size()) throw new MassaClustersExcepcio();
        else if (k <= 0) throw new CapClusterExcepcio();
        while (clusters.size() < k){
            Set<Integer> keySet = distancies.keySet();
            List<Integer> keyList = new ArrayList<>(keySet);
            int size = keyList.size();
            int randIdx = new Random().nextInt(size);
            int centroide = keyList.get(randIdx);
            boolean disponible = true;
            for (HashSet<Integer> cluster : clusters) {
                if (cluster.contains(centroide)) {
                    disponible = false;
                    break;
                }
            }
            if (disponible) {
                HashSet<Integer> set = new HashSet<>();
                set.add(centroide);
                clusters.add(set);
            }
        }

        //Fem 20 iteracions per assegurar-nos que els usuaris no es queden en el primer cluster que troben sinó
        //que es van adequant als clusters.
        for (int j = 0; j < 20; ++j) {
            for(HashMap.Entry<Integer,HashMap<Integer,Double>> entry : distancies.entrySet()) {
                int c = buscaCentroideMinDist(entry.getValue(), entry.getKey());
                int actual = -1;
                for (int i = 0; i < k; ++i) {
                    if (clusters.get(i).contains(entry.getKey())) actual = i;
                }
                if (actual != -1 && c != actual && clusters.get(actual).size() > 1) {
                    clusters.get(actual).remove(entry.getKey());
                    clusters.get(c).add(entry.getKey());
                }
                else if (actual == -1) clusters.get(c).add(entry.getKey());
            }
        }


    }

    //GETTERS

    /**
     * Retorna els usuaris que es troven al mateix cluster que l'usuari "usuari".
     * @param usuari És l'usuari del qual volem trobar tots els seus veins (usuaris del mateix cluster).
     */
    public HashSet<Integer> usuarisMateixCluster (Usuari usuari) {
        Integer id = usuari.getId();
        for (HashSet<Integer> cluster : clusters) {
            if (cluster.contains(id)) return cluster;
        }
        return null;
    }

    /**
     * Retorna tots els clusters amb els seus corresponents usuaris per cluster.
     */
    public ArrayList<HashSet<Integer>> getClusters() {
        return clusters;
    }
}
