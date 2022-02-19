package domini.Drivers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import domini.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;


public class DrvkMeansJunit {


    @Test
    public void test1 () {
        System.out.println("EXECUCIO TEST1 :  comprovar funcionament constructora amb unes distàncies ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        HashMap<Integer, HashMap<Integer, Double>> distancies = new HashMap<>();

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            KMeans kmeans = new KMeans(distancies);
        }
        catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
        }

        System.out.println("TEST 1 FINALITZAT");
        System.out.println();

    }

    @Test
    public void test2 () {
        System.out.println("EXECUCIO TEST2 :  execució de k-Means amb distàncies");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari usuari1 = mock(Usuari.class);
        when(usuari1.getId()).thenReturn(1);
        Usuari usuari2 = mock(Usuari.class);
        when(usuari2.getId()).thenReturn(2);

        Usuari usuari3 = mock(Usuari.class);
        when(usuari3.getId()).thenReturn(3);
        Usuari usuari4 = mock(Usuari.class);
        when(usuari4.getId()).thenReturn(4);
        Usuari usuari5 = mock(Usuari.class);

        when(usuari5.getId()).thenReturn(5);
        Usuari usuari6 = mock(Usuari.class);
        when(usuari6.getId()).thenReturn(6);

        HashMap<Integer, HashMap<Integer, Double>> distancies = new HashMap<>();

        HashMap<Integer,Double> aux1 = new HashMap<>();
        aux1.put(usuari1.getId(),0.0);
        aux1.put(usuari2.getId(),3.0);
        aux1.put(usuari3.getId(),5.0);
        aux1.put(usuari4.getId(),10.0);
        aux1.put(usuari5.getId(),3.0);
        aux1.put(usuari6.getId(),20.0);
        distancies.put(usuari1.getId(),aux1);

        HashMap<Integer,Double> aux2 = new HashMap<>();
        aux2.put(usuari1.getId(),3.0);
        aux2.put(usuari2.getId(),0.0);
        aux2.put(usuari3.getId(),2.0);
        aux2.put(usuari4.getId(),7.0);
        aux2.put(usuari5.getId(),0.0);
        aux2.put(usuari6.getId(),17.0);
        distancies.put(usuari2.getId(),aux2);

        HashMap<Integer,Double> aux3 = new HashMap<>();
        aux3.put(usuari1.getId(),5.0);
        aux3.put(usuari2.getId(),2.0);
        aux3.put(usuari3.getId(),0.0);
        aux3.put(usuari4.getId(),5.0);
        aux3.put(usuari5.getId(),2.0);
        aux3.put(usuari6.getId(),15.0);
        distancies.put(usuari3.getId(),aux3);

        HashMap<Integer,Double> aux4 = new HashMap<>();
        aux4.put(usuari1.getId(),10.0);
        aux4.put(usuari2.getId(),7.0);
        aux4.put(usuari3.getId(),5.0);
        aux4.put(usuari4.getId(),0.0);
        aux4.put(usuari5.getId(),7.0);
        aux4.put(usuari6.getId(),10.0);
        distancies.put(usuari4.getId(),aux4);

        HashMap<Integer,Double> aux5 = new HashMap<>();
        aux5.put(usuari1.getId(),3.0);
        aux5.put(usuari2.getId(),0.0);
        aux5.put(usuari3.getId(),2.0);
        aux5.put(usuari4.getId(),7.0);
        aux5.put(usuari5.getId(),0.0);
        aux5.put(usuari6.getId(),17.0);
        distancies.put(usuari5.getId(),aux5);

        HashMap<Integer,Double> aux6 = new HashMap<>();
        aux6.put(usuari1.getId(),20.0);
        aux6.put(usuari2.getId(),17.0);
        aux6.put(usuari3.getId(),15.0);
        aux6.put(usuari4.getId(),10.0);
        aux6.put(usuari5.getId(),17.0);
        aux6.put(usuari6.getId(),0.0);
        distancies.put(usuari6.getId(),aux6);



        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            KMeans kmeans = new KMeans(distancies);
            kmeans.calcularClusters(3);
            ArrayList<HashSet<Integer>> resultat = kmeans.getClusters();

            for (int i = 0; i < resultat.size(); ++i) {
                System.out.println("Cluster " + i + ":");
                for (Integer entry : resultat.get(i)) System.out.println(entry);
            }
        }
        catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
        }

        System.out.println("TEST 2 FINALITZAT");
    }

    //Com es pot veure, després d'executar el test número 2, els usuaris 4 i 6 es solen col·locar en
    //el mateix cluster. Aquest son els usuaris més allunyats de tots els altres, el 2 i el 5 també es col·loquen
    //en el mateix cluster, ja que estan a distància 0.


    @AfterClass
    public static void finalDeElsTests () {
        System.out.println("TOTS TESTS FINALITZATS");
    }


    public static void main(String[] args) throws Exception {
        DrvkMeansJunit a = new DrvkMeansJunit();
        a.test1();
        a.test2();
        a.finalDeElsTests();
    }


}