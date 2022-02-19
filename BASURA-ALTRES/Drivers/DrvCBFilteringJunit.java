package domini.Drivers; 

import static org.mockito.Mockito.mock; 
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import domini.CBFiltering;
import domini.Item;
import domini.Usuari;
import domini.Valoracio;


public class DrvCBFilteringJunit  {

    @Test
    public void test1 () {
        System.out.println("EXECUCIO TEST1 :  comprovar funcionament constructora amb un usuari i totes les distàncies");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari Usuari1 = mock(Usuari.class); 

        HashMap<Item, HashMap<Item, Double>> totesDistancies = new HashMap<>();

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            CBFiltering alg = new CBFiltering(Usuari1, totesDistancies);
        }
        catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
        }

          System.out.println("TEST 1 FINALITZAT");

    }


    @Test 
    public void test2 () {
        System.out.println("EXECUCIO TEST2 :  mateix que test 2 pero executant tambe la funcio 'knn'");
        System.out.println("Hi ha 5 valoracions");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");
        
        Usuari usuari = mock(Usuari.class); 

        Item item1 = mock(Item.class);  //valorat per usuari
        when(item1.getID()).thenReturn(1);
        Item item2 = mock(Item.class);  //valorat per usuari 
        when(item2.getID()).thenReturn(2);
        Item item3 = mock(Item.class);  //valorat per usuari
        when(item3.getID()).thenReturn(3);
        Item item4 = mock(Item.class);  //valorat per usuari
        when(item4.getID()).thenReturn(4);
        Item item5 = mock(Item.class);  //valorat per usuari
        when(item5.getID()).thenReturn(5);
        Item item6 = mock(Item.class);   
        when(item6.getID()).thenReturn(6);
        Item item7 = mock(Item.class);  
        when(item7.getID()).thenReturn(7);
        Item item8 = mock(Item.class);
        when(item8.getID()).thenReturn(8);
        Item item9 = mock(Item.class);  
        when(item9.getID()).thenReturn(9);
        Item item10 = mock(Item.class);  
        when(item10.getID()).thenReturn(10);
        

        Valoracio valoracio1 = mock(Valoracio.class);         //item1
        when(valoracio1.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio2 = mock(Valoracio.class);         //item2
        when(valoracio2.getPuntuacio()).thenReturn(2.0); 
        Valoracio valoracio3 = mock(Valoracio.class);         //item3
        when(valoracio3.getPuntuacio()).thenReturn(3.0);         
        Valoracio valoracio4 = mock(Valoracio.class);         //item4
        when(valoracio4.getPuntuacio()).thenReturn(4.0);
        Valoracio valoracio5 = mock(Valoracio.class);         //item5
        when(valoracio5.getPuntuacio()).thenReturn(5.0); 

        HashMap<Item,Valoracio> valoracions = new HashMap<>();
        valoracions.put(item1, valoracio1);
        valoracions.put(item2, valoracio2);
        valoracions.put(item3, valoracio3);
        valoracions.put(item4, valoracio4);
        valoracions.put(item5, valoracio5);

        when(usuari.getItemsValoracions()).thenReturn(valoracions);

        HashMap<Item, HashMap<Item, Double>> totesDistancies = new HashMap<>();

        HashMap<Item,Double> aux1 = new HashMap<>();
        //aux.put(item1,30.0);
        aux1.put(item2,3.0);
        aux1.put(item3,22.0);
        aux1.put(item4,9.0);
        aux1.put(item5,2.0);
        aux1.put(item6,0.0);
        aux1.put(item7,22.0);
        aux1.put(item8,3.0);
        aux1.put(item9,4.0);
        aux1.put(item10,1.0);
        totesDistancies.put(item1,aux1);

        HashMap<Item,Double> aux2 = new HashMap<>();
        aux2.put(item1,3.0);
        //aux2.put(item2,3.0);
        aux2.put(item3,7.0);
        aux2.put(item4,8.0);
        aux2.put(item5,22.0);
        aux2.put(item6,4.0);
        aux2.put(item7,0.0);
        aux2.put(item8,1.0);
        aux2.put(item9,5.0);
        aux2.put(item10,9.0);
        totesDistancies.put(item2,aux2);

        HashMap<Item,Double> aux3 = new HashMap<>();
        aux3.put(item1,11.0);
        aux3.put(item2,7.0);
        //aux3.put(item3,11.0);
        aux3.put(item4,3.0);
        aux3.put(item5,8.0);
        aux3.put(item6,4.0);
        aux3.put(item7,8.0);
        aux3.put(item8,44.0);
        aux3.put(item9,2.0);
        aux3.put(item10,8.0);
        totesDistancies.put(item3,aux3);

        HashMap<Item,Double> aux4 = new HashMap<>();
        aux4.put(item1,9.0);
        aux4.put(item2,8.0);
        aux4.put(item3,3.0);
        //aux4.put(item4,3.0);
        aux4.put(item5,2.0);
        aux4.put(item6,6.0);
        aux4.put(item7,4.0);
        aux4.put(item8,47.0);
        aux4.put(item9,0.0);
        aux4.put(item10,2.0);
        totesDistancies.put(item4,aux4);

        HashMap<Item,Double> aux5 = new HashMap<>();
        aux5.put(item1,2.0);
        aux5.put(item2,22.0);
        aux5.put(item3,8.0);
        aux5.put(item4,2.0);
        //aux5.put(item5,8.0);
        aux5.put(item6,2.0);
        aux5.put(item7,6.0);
        aux5.put(item8,4.0);
        aux5.put(item9,28.0);
        aux5.put(item10,0.0);
        totesDistancies.put(item5,aux5);

        int k = 3;

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            CBFiltering alg = new CBFiltering(usuari, totesDistancies);
            ArrayList<Item> resultat = alg.knn(k);

            System.out.print("( ");
            for(Item i : resultat){
               System.out.print(i.getID());
               System.out.print(", ");
            }
            System.out.print(" )");
            System.out.println();
        }
        catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
        }

          System.out.println("TEST 2 FINALITZAT");

    }



    @Test 
    public void test3 () {
        System.out.println("EXECUCIO TEST3 :  mateix que test 2 pero executant tambe la funcio 'knn'");
        System.out.println("Hi ha 5 valoracions, totes tenen la mateixa valoració donada per l'usuari, k=5");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");
        
        Usuari usuari = mock(Usuari.class); 

        Item item1 = mock(Item.class);  //valorat per usuari
        when(item1.getID()).thenReturn(1);
        Item item2 = mock(Item.class);  //valorat per usuari 
        when(item2.getID()).thenReturn(2);
        Item item3 = mock(Item.class);  //valorat per usuari
        when(item3.getID()).thenReturn(3);
        Item item4 = mock(Item.class);  //valorat per usuari
        when(item4.getID()).thenReturn(4);
        Item item5 = mock(Item.class);  //valorat per usuari
        when(item5.getID()).thenReturn(5);
        Item item6 = mock(Item.class);   
        when(item6.getID()).thenReturn(6);
        Item item7 = mock(Item.class);  
        when(item7.getID()).thenReturn(7);
        Item item8 = mock(Item.class);
        when(item8.getID()).thenReturn(8);
        Item item9 = mock(Item.class);  
        when(item9.getID()).thenReturn(9);
        Item item10 = mock(Item.class);  
        when(item10.getID()).thenReturn(10);
        

        Valoracio valoracio1 = mock(Valoracio.class);         //item1
        when(valoracio1.getPuntuacio()).thenReturn(5.0); 
        Valoracio valoracio2 = mock(Valoracio.class);         //item2
        when(valoracio2.getPuntuacio()).thenReturn(5.0); 
        Valoracio valoracio3 = mock(Valoracio.class);         //item3
        when(valoracio3.getPuntuacio()).thenReturn(5.0);         
        Valoracio valoracio4 = mock(Valoracio.class);         //item4
        when(valoracio4.getPuntuacio()).thenReturn(5.0);
        Valoracio valoracio5 = mock(Valoracio.class);         //item5
        when(valoracio5.getPuntuacio()).thenReturn(5.0); 

        HashMap<Item,Valoracio> valoracions = new HashMap<>();
        valoracions.put(item1, valoracio1);
        valoracions.put(item2, valoracio2);
        valoracions.put(item3, valoracio3);
        valoracions.put(item4, valoracio4);
        valoracions.put(item5, valoracio5);

        when(usuari.getItemsValoracions()).thenReturn(valoracions);

        HashMap<Item, HashMap<Item, Double>> totesDistancies = new HashMap<>();

        HashMap<Item,Double> aux1 = new HashMap<>();
        //aux.put(item1,30.0);
        aux1.put(item2,3.0);
        aux1.put(item3,22.0);
        aux1.put(item4,9.0);
        aux1.put(item5,2.0);
        aux1.put(item6,0.0);
        aux1.put(item7,22.0);
        aux1.put(item8,3.0);
        aux1.put(item9,4.0);
        aux1.put(item10,1.0);
        totesDistancies.put(item1,aux1);

        HashMap<Item,Double> aux2 = new HashMap<>();
        aux2.put(item1,3.0);
        //aux2.put(item2,3.0);
        aux2.put(item3,7.0);
        aux2.put(item4,8.0);
        aux2.put(item5,22.0);
        aux2.put(item6,4.0);
        aux2.put(item7,0.0);
        aux2.put(item8,1.0);
        aux2.put(item9,5.0);
        aux2.put(item10,9.0);
        totesDistancies.put(item2,aux2);

        HashMap<Item,Double> aux3 = new HashMap<>();
        aux3.put(item1,11.0);
        aux3.put(item2,7.0);
        //aux3.put(item3,11.0);
        aux3.put(item4,3.0);
        aux3.put(item5,8.0);
        aux3.put(item6,4.0);
        aux3.put(item7,8.0);
        aux3.put(item8,44.0);
        aux3.put(item9,2.0);
        aux3.put(item10,8.0);
        totesDistancies.put(item3,aux3);

        HashMap<Item,Double> aux4 = new HashMap<>();
        aux4.put(item1,9.0);
        aux4.put(item2,8.0);
        aux4.put(item3,3.0);
        //aux4.put(item4,3.0);
        aux4.put(item5,2.0);
        aux4.put(item6,6.0);
        aux4.put(item7,4.0);
        aux4.put(item8,47.0);
        aux4.put(item9,0.0);
        aux4.put(item10,2.0);
        totesDistancies.put(item4,aux4);

        HashMap<Item,Double> aux5 = new HashMap<>();
        aux5.put(item1,2.0);
        aux5.put(item2,22.0);
        aux5.put(item3,8.0);
        aux5.put(item4,2.0);
        //aux5.put(item5,8.0);
        aux5.put(item6,2.0);
        aux5.put(item7,6.0);
        aux5.put(item8,4.0);
        aux5.put(item9,28.0);
        aux5.put(item10,0.0);
        totesDistancies.put(item5,aux5);

        int k = 5;

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            CBFiltering alg = new CBFiltering(usuari, totesDistancies);
            ArrayList<Item> resultat = alg.knn(k);

            System.out.print("( ");
            for(Item i : resultat){
               System.out.print(i.getID());
               System.out.print(", ");
            }
            System.out.print(" )");
            System.out.println();
        }
        catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
        }

          System.out.println("TEST 3 FINALITZAT");

    }

    @Test
    public void test4 () {
        System.out.println("EXECUCIO TEST4 :  mateix que test 2 pero executant tambe la funcio 'knn'");
        System.out.println("Hi ha 5 valoracions, totes tenen la diferent valoració donada per l'usuari, k=3");
        System.out.println("Tots els items tenen la mateixa distancia");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");
        
        Usuari usuari = mock(Usuari.class); 

        Item item1 = mock(Item.class);  //valorat per usuari
        when(item1.getID()).thenReturn(1);
        Item item2 = mock(Item.class);  //valorat per usuari 
        when(item2.getID()).thenReturn(2);
        Item item3 = mock(Item.class);  //valorat per usuari
        when(item3.getID()).thenReturn(3);
        Item item4 = mock(Item.class);  //valorat per usuari
        when(item4.getID()).thenReturn(4);
        Item item5 = mock(Item.class);  //valorat per usuari
        when(item5.getID()).thenReturn(5);
        Item item6 = mock(Item.class);   
        when(item6.getID()).thenReturn(6);
        Item item7 = mock(Item.class);  
        when(item7.getID()).thenReturn(7);
        Item item8 = mock(Item.class);
        when(item8.getID()).thenReturn(8);
        Item item9 = mock(Item.class);  
        when(item9.getID()).thenReturn(9);
        Item item10 = mock(Item.class);  
        when(item10.getID()).thenReturn(10);
        

        Valoracio valoracio1 = mock(Valoracio.class);         //item1
        when(valoracio1.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio2 = mock(Valoracio.class);         //item2
        when(valoracio2.getPuntuacio()).thenReturn(2.0); 
        Valoracio valoracio3 = mock(Valoracio.class);         //item3
        when(valoracio3.getPuntuacio()).thenReturn(3.0);         
        Valoracio valoracio4 = mock(Valoracio.class);         //item4
        when(valoracio4.getPuntuacio()).thenReturn(4.0);
        Valoracio valoracio5 = mock(Valoracio.class);         //item5
        when(valoracio5.getPuntuacio()).thenReturn(5.0); 

        HashMap<Item,Valoracio> valoracions = new HashMap<>();
        valoracions.put(item1, valoracio1);
        valoracions.put(item2, valoracio2);
        valoracions.put(item3, valoracio3);
        valoracions.put(item4, valoracio4);
        valoracions.put(item5, valoracio5);

        when(usuari.getItemsValoracions()).thenReturn(valoracions);

        HashMap<Item, HashMap<Item, Double>> totesDistancies = new HashMap<>();

        HashMap<Item,Double> aux1 = new HashMap<>();
        //aux.put(item1,30.0);
        aux1.put(item2,0.0);
        aux1.put(item3,0.0);
        aux1.put(item4,0.0);
        aux1.put(item5,0.0);
        aux1.put(item6,0.0);
        aux1.put(item7,0.0);
        aux1.put(item8,0.0);
        aux1.put(item9,0.0);
        aux1.put(item10,0.0);
        totesDistancies.put(item1,aux1);

        HashMap<Item,Double> aux2 = new HashMap<>();
        aux2.put(item1,0.0);
        //aux2.put(item2,3.0);
        aux2.put(item3,0.0);
        aux2.put(item4,0.0);
        aux2.put(item5,0.0);
        aux2.put(item6,0.0);
        aux2.put(item7,0.0);
        aux2.put(item8,0.0);
        aux2.put(item9,0.0);
        aux2.put(item10,0.0);
        totesDistancies.put(item2,aux2);

        HashMap<Item,Double> aux3 = new HashMap<>();
        aux3.put(item1,0.0);
        aux3.put(item2,0.0);
        //aux3.put(item3,11.0);
        aux3.put(item4,0.0);
        aux3.put(item5,0.0);
        aux3.put(item6,0.0);
        aux3.put(item7,0.0);
        aux3.put(item8,0.0);
        aux3.put(item9,0.0);
        aux3.put(item10,0.0);
        totesDistancies.put(item3,aux3);

        HashMap<Item,Double> aux4 = new HashMap<>();
        aux4.put(item1,0.0);
        aux4.put(item2,0.0);
        aux4.put(item3,0.0);
        //aux4.put(item4,3.0);
        aux4.put(item5,0.0);
        aux4.put(item6,0.0);
        aux4.put(item7,0.0);
        aux4.put(item8,0.0);
        aux4.put(item9,0.0);
        aux4.put(item10,0.0);
        totesDistancies.put(item4,aux4);

        HashMap<Item,Double> aux5 = new HashMap<>();
        aux5.put(item1,0.0);
        aux5.put(item2,0.0);
        aux5.put(item3,0.0);
        aux5.put(item4,0.0);
        //aux5.put(item5,8.0);
        aux5.put(item6,0.0);
        aux5.put(item7,0.0);
        aux5.put(item8,0.0);
        aux5.put(item9,0.0);
        aux5.put(item10,0.0);
        totesDistancies.put(item5,aux5);

        int k = 3;

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            CBFiltering alg = new CBFiltering(usuari, totesDistancies);
            ArrayList<Item> resultat = alg.knn(k);

            System.out.print("( ");
            for(Item i : resultat){
               System.out.print(i.getID());
               System.out.print(", ");
            }
            System.out.print(" )");
            System.out.println();
        }
        catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
        }

          System.out.println("TEST 4 FINALITZAT");

    }

    @Test
    public void test5() {
        System.out.println("EXECUCIO TEST5 :  mateix que test 2 pero executant tambe la funcio 'knn'");
        System.out.println("Hi ha 5 valoracions, els tres primers ítems tenen valoració inferior a 3, k=3");
        System.out.println("Tots els items tenen la mateixa distancia menys els items recomanats per les valoracions 4 i 5");
        System.out.println("Les valoracions de 4 que no són excloses (>=10)tenen distancia 0, les valoracions de 5 que no són excloses (>=10) tenen distancia 9");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");
        
        Usuari usuari = mock(Usuari.class); 

        Item item1 = mock(Item.class);  //valorat per usuari
        when(item1.getID()).thenReturn(1);
        Item item2 = mock(Item.class);  //valorat per usuari 
        when(item2.getID()).thenReturn(2);
        Item item3 = mock(Item.class);  //valorat per usuari
        when(item3.getID()).thenReturn(3);
        Item item4 = mock(Item.class);  //valorat per usuari
        when(item4.getID()).thenReturn(4);
        Item item5 = mock(Item.class);  //valorat per usuari
        when(item5.getID()).thenReturn(5);
        Item item6 = mock(Item.class);   
        when(item6.getID()).thenReturn(6);
        Item item7 = mock(Item.class);  
        when(item7.getID()).thenReturn(7);
        Item item8 = mock(Item.class);
        when(item8.getID()).thenReturn(8);
        Item item9 = mock(Item.class);  
        when(item9.getID()).thenReturn(9);
        Item item10 = mock(Item.class);  
        when(item10.getID()).thenReturn(10);
        

        Valoracio valoracio1 = mock(Valoracio.class);         //item1
        when(valoracio1.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio2 = mock(Valoracio.class);         //item2
        when(valoracio2.getPuntuacio()).thenReturn(2.0); 
        Valoracio valoracio3 = mock(Valoracio.class);         //item3
        when(valoracio3.getPuntuacio()).thenReturn(2.0);         
        Valoracio valoracio4 = mock(Valoracio.class);         //item4
        when(valoracio4.getPuntuacio()).thenReturn(4.0);
        Valoracio valoracio5 = mock(Valoracio.class);         //item5
        when(valoracio5.getPuntuacio()).thenReturn(5.0); 

        HashMap<Item,Valoracio> valoracions = new HashMap<>();
        valoracions.put(item1, valoracio1);
        valoracions.put(item2, valoracio2);
        valoracions.put(item3, valoracio3);
        valoracions.put(item4, valoracio4);
        valoracions.put(item5, valoracio5);

        when(usuari.getItemsValoracions()).thenReturn(valoracions);

        HashMap<Item, HashMap<Item, Double>> totesDistancies = new HashMap<>();

        HashMap<Item,Double> aux1 = new HashMap<>();
        //aux.put(item1,30.0);
        aux1.put(item2,0.0);
        aux1.put(item3,0.0);
        aux1.put(item4,0.0);
        aux1.put(item5,0.0);
        aux1.put(item6,0.0);
        aux1.put(item7,0.0);
        aux1.put(item8,0.0);
        aux1.put(item9,0.0);
        aux1.put(item10,0.0);
        totesDistancies.put(item1,aux1);

        HashMap<Item,Double> aux2 = new HashMap<>();
        aux2.put(item1,0.0);
        //aux2.put(item2,3.0);
        aux2.put(item3,0.0);
        aux2.put(item4,0.0);
        aux2.put(item5,0.0);
        aux2.put(item6,0.0);
        aux2.put(item7,0.0);
        aux2.put(item8,0.0);
        aux2.put(item9,0.0);
        aux2.put(item10,0.0);
        totesDistancies.put(item2,aux2);

        HashMap<Item,Double> aux3 = new HashMap<>();
        aux3.put(item1,0.0);
        aux3.put(item2,0.0);
        //aux3.put(item3,11.0);
        aux3.put(item4,0.0);
        aux3.put(item5,0.0);
        aux3.put(item6,0.0);
        aux3.put(item7,0.0);
        aux3.put(item8,0.0);
        aux3.put(item9,0.0);
        aux3.put(item10,0.0);
        totesDistancies.put(item3,aux3);

        HashMap<Item,Double> aux4 = new HashMap<>();
        aux4.put(item1,0.0);
        aux4.put(item2,0.0);
        aux4.put(item3,0.0);
        //aux4.put(item4,3.0);
        aux4.put(item5,0.0);
        aux4.put(item6,22.0);
        aux4.put(item7,0.0);
        aux4.put(item8,22.0);
        aux4.put(item9,0.0);
        aux4.put(item10,22.0);
        totesDistancies.put(item4,aux4);

        HashMap<Item,Double> aux5 = new HashMap<>();
        aux5.put(item1,11.0);
        aux5.put(item2,11.0);
        aux5.put(item3,11.0);
        aux5.put(item4,11.0);
        //aux5.put(item5,8.0);
        aux5.put(item6,22.0);
        aux5.put(item7,22.0);
        aux5.put(item8,9.0);
        aux5.put(item9,9.0);
        aux5.put(item10,9.0);
        totesDistancies.put(item5,aux5);

        int k = 3;

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            CBFiltering alg = new CBFiltering(usuari, totesDistancies);
            ArrayList<Item> resultat = alg.knn(k);

            System.out.print("( ");
            for(Item i : resultat){
               System.out.print(i.getID());
               System.out.print(", ");
            }
            System.out.print(" )");
            System.out.println();
        }
        catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
        }

          System.out.println("TEST 5 FINALITZAT");

    }
    
    @AfterClass
    public static void finalDeElsTests () {
        System.out.println("TOTS TESTS FINALITZATS");
    }

    public static void main(String[] args) throws Exception {
        DrvCBFilteringJunit drv = new DrvCBFilteringJunit();
        drv.test1();
        drv.test2();
        drv.test3();
        drv.test4();
        drv.test5();
        drv.finalDeElsTests();
    }

}


