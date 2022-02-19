package domini.Drivers; 

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock; 
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import domini.DistUsuari;
import domini.Item;
import domini.Usuari;
import domini.Valoracio;


public class DrvDistUsuariJunit {


    @Test
    public void test1 () {
        System.out.println("EXECUCIO TEST1 :  comprovar funcionament constructora 1 (la simple) amb un usuari ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari Usuari1 = mock(Usuari.class); 

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            DistUsuari a = new DistUsuari(Usuari1);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 1 FINALITZAT");

    }


    @Test
    public void test2 () {
        System.out.println("EXECUCIO TEST2 :  comprovar funcionament constructora 2 amb un set de usuaris buit ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari UAnalitzat = mock(Usuari.class); 
        Set<Usuari> usuaris = new HashSet<Usuari> (); 

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            DistUsuari a = new DistUsuari(UAnalitzat, usuaris);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 2 FINALITZAT");

    }


    @Test
    public void test3 () {
        System.out.println("EXECUCIO TEST3 :  mateix que test 2 pero executant tambe la funcio 'getDistancies' ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari UAnalitzat = mock(Usuari.class); 
        Set<Usuari> usuaris = new HashSet<Usuari> (); 

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            DistUsuari a = new DistUsuari(UAnalitzat, usuaris); 

            System.out.println("Resultat de getDistancies: ");
            System.out.println(a.getDistancies().toString());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 3 FINALITZAT");

    }


    @Test
    public void test4 () {
        System.out.println("EXECUCIO TEST4 :  Creacio d'una instancia de la classe, amb nomes un usuari a part del analitzat, i execucio de la funcio 'getDistancies'. Per afegir l'usuari no analitzat es fara servir la funcio 'afegirUsuari'"); 
        System.out.println("En els items que aquests dos usuaris coincideixen amb les seves valoracions, han puntuat aquests amb la mateixa nota, aixi que el resultat de les distancies hauria de ser 0 ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        //MOCKS DE USUARIS

        Usuari UAnalitzat = mock(Usuari.class); 
        when(UAnalitzat.toString()).thenReturn("UAnalitzat"); 
        Usuari Usuari1 = mock(Usuari.class); 
        when(Usuari1.toString()).thenReturn("Usuari1"); 
        

        // MOCKS DE ITEMS

        Item item1 = mock(Item.class);  //Nomes valorat per UAnalitzat
        Item item2 = mock(Item.class);  //valorat tant per UAnalitzat com per Usuari1
        Item item3 = mock(Item.class);  //valorat tant per UAnalitzat com per Usuari1
        Item item4 = mock(Item.class);  //valorat tant per UAnalitzat com per Usuari1
        Item item5 = mock(Item.class);  //Nomes valorat per Usuari1


        // MOCKS DE VALORACIONS

        Valoracio valoracio1 = mock(Valoracio.class);         //de UAnalitzat per item1
            when(valoracio1.getPuntuacio()).thenReturn(2.0); 


        Valoracio valoracio2 = mock(Valoracio.class);         //de UAnalitzat per item2
            when(valoracio2.getPuntuacio()).thenReturn(5.0); 
        Valoracio valoracio3 = mock(Valoracio.class);         //de Usuari1 per item2
            when(valoracio3.getPuntuacio()).thenReturn(5.0); 

        Valoracio valoracio4 = mock(Valoracio.class);         //de UAnalitzat per item3
            when(valoracio4.getPuntuacio()).thenReturn(4.0); 
        Valoracio valoracio5 = mock(Valoracio.class);         //de Usuari1 per item3
            when(valoracio5.getPuntuacio()).thenReturn(4.0); 

        Valoracio valoracio6 = mock(Valoracio.class);     //de UAnalitzat per item4
            when(valoracio6.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio7 = mock(Valoracio.class);         //de Usuari1 per item4
            when(valoracio7.getPuntuacio()).thenReturn(1.0); 
            
        Valoracio valoracio8 = mock(Valoracio.class);         //de Usuari1 per item5
            when(valoracio8.getPuntuacio()).thenReturn(1.0);
            


        // getItemsValoracions DE USUARI

        HashMap<Item, Valoracio> itemsValoracionsUAnalitzat = new HashMap<Item, Valoracio> ();     //UAnalitzat
        itemsValoracionsUAnalitzat.put(item1, valoracio1); 
        itemsValoracionsUAnalitzat.put(item2, valoracio2); 
        itemsValoracionsUAnalitzat.put(item3, valoracio4); 
        itemsValoracionsUAnalitzat.put(item4, valoracio6); 
            when(UAnalitzat.getItemsValoracions()).thenReturn(itemsValoracionsUAnalitzat); 


        HashMap<Item, Valoracio> itemsValoracionsUsuari1 = new HashMap<Item, Valoracio> ();     //Usuari1
        itemsValoracionsUsuari1.put(item2, valoracio3); 
        itemsValoracionsUsuari1.put(item3, valoracio5); 
        itemsValoracionsUsuari1.put(item4, valoracio7); 
        itemsValoracionsUsuari1.put(item5, valoracio8); 
            when(Usuari1.getItemsValoracions()).thenReturn(itemsValoracionsUsuari1); 


        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            DistUsuari a = new DistUsuari(UAnalitzat);
            a.afegirUsuari(Usuari1);
            System.out.println("Resultat de getDistancies: ");
            double distancia = a.getDistancies().get(Usuari1); 
            System.out.println(a.getDistancies().toString());
            assertEquals(distancia, 0.0, 1e-15);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 4 FINALITZAT");

    }



    @Test
    public void test5 () {
        System.out.println("TEST5 :     El mateix que el TEST4, pero ara a part de l'usuari analitzat en tindrem 2 més. ");
        System.out.println("A més, els items en que aquests dos coincideixen amb l'usuari analitzat tindran diferent rating que aquest, per tant les distancies seran superiors a 0");
        System.out.println("Aquests dos usuaris seran afegits amb un set a la constructora");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        // MOCKS DE ITEMS

        Item item1 = mock(Item.class); 
        Item item2 = mock(Item.class); 
        Item item3 = mock(Item.class); 


        // MOCKS DE USERS

        Usuari Usuari1 = mock(Usuari.class); 
            when(Usuari1.toString()).thenReturn("Usuari1"); 
        Usuari Usuari2 = mock(Usuari.class); 
            when(Usuari2.toString()).thenReturn("Usuari2"); 
        Usuari Usuari3 = mock(Usuari.class); 
            when(Usuari3.toString()).thenReturn("Usuari3"); 
        

        // MOCKS DE VALORACIONS

        Valoracio valoracio1 = mock(Valoracio.class);    //de Usuari 1 per item1
            when(valoracio1.getPuntuacio()).thenReturn(2.0); 
        Valoracio valoracio2 = mock(Valoracio.class);   //de Usuari 2 per item1
            when(valoracio2.getPuntuacio()).thenReturn(5.0); 
        Valoracio valoracio3 = mock(Valoracio.class);   //de Usuari 3 per item1
             when(valoracio3.getPuntuacio()).thenReturn(1.0); 

        Valoracio valoracio4 = mock(Valoracio.class);   //de Usuari1 per item2
            when(valoracio4.getPuntuacio()).thenReturn(4.0); 
        Valoracio valoracio5 = mock(Valoracio.class);   //de Usuari2 per item2
            when(valoracio5.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio6 = mock(Valoracio.class);   //de Usuari3 per item2
            when(valoracio6.getPuntuacio()).thenReturn(3.0); 

        Valoracio valoracio7 = mock(Valoracio.class);   //de Usuari1 per item3
            when(valoracio7.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio8 = mock(Valoracio.class);   //de Usuari2 per item3
            when(valoracio8.getPuntuacio()).thenReturn(3.0); 
        Valoracio valoracio9 = mock(Valoracio.class);   //de Usuari3 per item3
            when(valoracio9.getPuntuacio()).thenReturn(4.0); 

        
        // getItemsValoracions DE USUARI

        HashMap<Item, Valoracio> itemsValoracionsUsuari1 = new HashMap<Item, Valoracio> ();     //USUARI1
        itemsValoracionsUsuari1.put(item1, valoracio1); 
        itemsValoracionsUsuari1.put(item2, valoracio4); 
        itemsValoracionsUsuari1.put(item3, valoracio7); 
            when(Usuari1.getItemsValoracions()).thenReturn(itemsValoracionsUsuari1); 


        HashMap<Item, Valoracio> itemsValoracionsUsuari2 = new HashMap<Item, Valoracio> ();     //USUARI2
        itemsValoracionsUsuari2.put(item1, valoracio2); 
        itemsValoracionsUsuari2.put(item2, valoracio5); 
        itemsValoracionsUsuari2.put(item3, valoracio8); 
            when(Usuari2.getItemsValoracions()).thenReturn(itemsValoracionsUsuari2); 

        
        HashMap<Item, Valoracio> itemsValoracionsUsuari3 = new HashMap<Item, Valoracio> ();     //USUARI3
        itemsValoracionsUsuari3.put(item1, valoracio3); 
        itemsValoracionsUsuari3.put(item2, valoracio6); 
        itemsValoracionsUsuari3.put(item3, valoracio9); 
            when(Usuari3.getItemsValoracions()).thenReturn(itemsValoracionsUsuari3); 

        
        Set<Usuari> usuaris = new HashSet<Usuari> (); 
        usuaris.add(Usuari2); 
        usuaris.add(Usuari3); 

        
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            DistUsuari a = new DistUsuari(Usuari1, usuaris);
            System.out.println("Resultat de getDistancies: ");
            System.out.println(a.getDistancies().toString());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 5 FINALITZAT");


    }


    @Test
    public void test6 () {
        System.out.println("TEST6 :     El mateix que el TEST5, pero eliminant després l'usuari 3, i tornant a executar finalment 'getDistancies' . ");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        // MOCKS DE ITEMS

        Item item1 = mock(Item.class); 
        Item item2 = mock(Item.class); 
        Item item3 = mock(Item.class); 


        // MOCKS DE USERS

        Usuari Usuari1 = mock(Usuari.class); 
            when(Usuari1.toString()).thenReturn("Usuari1"); 
        Usuari Usuari2 = mock(Usuari.class); 
            when(Usuari2.toString()).thenReturn("Usuari2");
        Usuari Usuari3 = mock(Usuari.class); 
            when(Usuari3.toString()).thenReturn("Usuari3"); 

        // MOCKS DE VALORACIONS

        Valoracio valoracio1 = mock(Valoracio.class);    //de Usuari 1 per item1
            when(valoracio1.getPuntuacio()).thenReturn(2.0); 
        Valoracio valoracio2 = mock(Valoracio.class);   //de Usuari 2 per item1
            when(valoracio2.getPuntuacio()).thenReturn(5.0); 
        Valoracio valoracio3 = mock(Valoracio.class);   //de Usuari 3 per item1
             when(valoracio3.getPuntuacio()).thenReturn(1.0); 

        Valoracio valoracio4 = mock(Valoracio.class);   //de Usuari1 per item2
            when(valoracio4.getPuntuacio()).thenReturn(4.0); 
        Valoracio valoracio5 = mock(Valoracio.class);   //de Usuari2 per item2
            when(valoracio5.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio6 = mock(Valoracio.class);   //de Usuari3 per item2
            when(valoracio6.getPuntuacio()).thenReturn(3.0); 

        Valoracio valoracio7 = mock(Valoracio.class);   //de Usuari1 per item3
            when(valoracio7.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio8 = mock(Valoracio.class);   //de Usuari2 per item3
            when(valoracio8.getPuntuacio()).thenReturn(3.0); 
        Valoracio valoracio9 = mock(Valoracio.class);   //de Usuari3 per item3
            when(valoracio9.getPuntuacio()).thenReturn(4.0); 

        
        // getItemsValoracions DE USUARI

        HashMap<Item, Valoracio> itemsValoracionsUsuari1 = new HashMap<Item, Valoracio> ();     //USUARI1
        itemsValoracionsUsuari1.put(item1, valoracio1); 
        itemsValoracionsUsuari1.put(item2, valoracio4); 
        itemsValoracionsUsuari1.put(item3, valoracio7); 
            when(Usuari1.getItemsValoracions()).thenReturn(itemsValoracionsUsuari1); 


        HashMap<Item, Valoracio> itemsValoracionsUsuari2 = new HashMap<Item, Valoracio> ();     //USUARI2
        itemsValoracionsUsuari2.put(item1, valoracio2); 
        itemsValoracionsUsuari2.put(item2, valoracio5); 
        itemsValoracionsUsuari2.put(item3, valoracio8); 
            when(Usuari2.getItemsValoracions()).thenReturn(itemsValoracionsUsuari2); 

        
        HashMap<Item, Valoracio> itemsValoracionsUsuari3 = new HashMap<Item, Valoracio> ();     //USUARI3
        itemsValoracionsUsuari3.put(item1, valoracio3); 
        itemsValoracionsUsuari3.put(item2, valoracio6); 
        itemsValoracionsUsuari3.put(item3, valoracio9); 
            when(Usuari3.getItemsValoracions()).thenReturn(itemsValoracionsUsuari3); 

        
        Set<Usuari> usuaris = new HashSet<Usuari> (); 
        usuaris.add(Usuari2); 
        usuaris.add(Usuari3); 

        
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            DistUsuari a = new DistUsuari(Usuari1, usuaris);
            System.out.println("Resultat de getDistancies: ");
            System.out.println(a.getDistancies().toString());
            a.eliminarUsuari(Usuari3);
            System.out.println("Resultat de getDistancies despres d'haver eliminat el usuari 3: ");
            System.out.println(a.getDistancies().toString());
            
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 6 FINALITZAT");


    }
 

    @Test
    public void test7 () {
        System.out.println("TEST7 :     El mateix que el TEST 5, pero finalment executant la funcio 'recalcularTotesDistancies' i fent 'getDistancies' un altre cop, per comprovar que dona el mateix");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        // MOCKS DE ITEMS

        Item item1 = mock(Item.class); 
        Item item2 = mock(Item.class); 
        Item item3 = mock(Item.class); 


        // MOCKS DE USERS

        Usuari Usuari1 = mock(Usuari.class); 
            when(Usuari1.toString()).thenReturn("Usuari1"); 
        Usuari Usuari2 = mock(Usuari.class); 
            when(Usuari2.toString()).thenReturn("Usuari2"); 
        Usuari Usuari3 = mock(Usuari.class); 
            when(Usuari3.toString()).thenReturn("Usuari3"); 
        

        // MOCKS DE VALORACIONS

        Valoracio valoracio1 = mock(Valoracio.class);    //de Usuari 1 per item1
            when(valoracio1.getPuntuacio()).thenReturn(2.0); 
        Valoracio valoracio2 = mock(Valoracio.class);   //de Usuari 2 per item1
            when(valoracio2.getPuntuacio()).thenReturn(5.0); 
        Valoracio valoracio3 = mock(Valoracio.class);   //de Usuari 3 per item1
             when(valoracio3.getPuntuacio()).thenReturn(1.0); 

        Valoracio valoracio4 = mock(Valoracio.class);   //de Usuari1 per item2
            when(valoracio4.getPuntuacio()).thenReturn(4.0); 
        Valoracio valoracio5 = mock(Valoracio.class);   //de Usuari2 per item2
            when(valoracio5.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio6 = mock(Valoracio.class);   //de Usuari3 per item2
            when(valoracio6.getPuntuacio()).thenReturn(3.0); 

        Valoracio valoracio7 = mock(Valoracio.class);   //de Usuari1 per item3
            when(valoracio7.getPuntuacio()).thenReturn(1.0); 
        Valoracio valoracio8 = mock(Valoracio.class);   //de Usuari2 per item3
            when(valoracio8.getPuntuacio()).thenReturn(3.0); 
        Valoracio valoracio9 = mock(Valoracio.class);   //de Usuari3 per item3
            when(valoracio9.getPuntuacio()).thenReturn(4.0); 

        
        // getItemsValoracions DE USUARI

        HashMap<Item, Valoracio> itemsValoracionsUsuari1 = new HashMap<Item, Valoracio> ();     //USUARI1
        itemsValoracionsUsuari1.put(item1, valoracio1); 
        itemsValoracionsUsuari1.put(item2, valoracio4); 
        itemsValoracionsUsuari1.put(item3, valoracio7); 
            when(Usuari1.getItemsValoracions()).thenReturn(itemsValoracionsUsuari1); 


        HashMap<Item, Valoracio> itemsValoracionsUsuari2 = new HashMap<Item, Valoracio> ();     //USUARI2
        itemsValoracionsUsuari2.put(item1, valoracio2); 
        itemsValoracionsUsuari2.put(item2, valoracio5); 
        itemsValoracionsUsuari2.put(item3, valoracio8); 
            when(Usuari2.getItemsValoracions()).thenReturn(itemsValoracionsUsuari2); 

        
        HashMap<Item, Valoracio> itemsValoracionsUsuari3 = new HashMap<Item, Valoracio> ();     //USUARI3
        itemsValoracionsUsuari3.put(item1, valoracio3); 
        itemsValoracionsUsuari3.put(item2, valoracio6); 
        itemsValoracionsUsuari3.put(item3, valoracio9); 
            when(Usuari3.getItemsValoracions()).thenReturn(itemsValoracionsUsuari3); 

        
        Set<Usuari> usuaris = new HashSet<Usuari> (); 
        usuaris.add(Usuari2); 
        usuaris.add(Usuari3); 

        
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            DistUsuari a = new DistUsuari(Usuari1, usuaris);
            System.out.println("Resultat de getDistancies: ");
            System.out.println(a.getDistancies().toString());
            System.out.println("Resultat de getDistancies despres de recalcular-les: ");
            a.recalcularTotesDistancies();
            System.out.println(a.getDistancies().toString());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 7 FINALITZAT");


    }



    @Test
    public void test8 () {
        System.out.println("TEST8 :     Creacio d'una instància de la classe amb un usuari a part de l'analitzat. ");
        System.out.println("En cap de les valoracions entre els dos usuaris coincideixen els items, per tant la distancia entre aquests dos hauria de ser la maxima. ");
        System.out.println("Aixo es pot observar a l'executar la funcio 'getDistancies");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        // MOCKS DE ITEMS

        Item item1 = mock(Item.class); 
        Item item2 = mock(Item.class); 


        // MOCKS DE USERS

        Usuari Usuari1 = mock(Usuari.class); 
            when(Usuari1.toString()).thenReturn("Usuari1"); 
        Usuari Usuari2 = mock(Usuari.class); 
            when(Usuari2.toString()).thenReturn("Usuari2"); 
        

        // MOCKS DE VALORACIONS

        Valoracio valoracio1 = mock(Valoracio.class);    //de Usuari 1 per item1
            when(valoracio1.getPuntuacio()).thenReturn(2.0); 


        Valoracio valoracio2 = mock(Valoracio.class);   //de Usuari2 per item2
            when(valoracio2.getPuntuacio()).thenReturn(1.0); 

        
        // getItemsValoracions DE USUARI

        HashMap<Item, Valoracio> itemsValoracionsUsuari1 = new HashMap<Item, Valoracio> ();     //USUARI1
        itemsValoracionsUsuari1.put(item1, valoracio1); 
            when(Usuari1.getItemsValoracions()).thenReturn(itemsValoracionsUsuari1); 


        HashMap<Item, Valoracio> itemsValoracionsUsuari2 = new HashMap<Item, Valoracio> ();     //USUARI2
        itemsValoracionsUsuari2.put(item2, valoracio2); 
            when(Usuari2.getItemsValoracions()).thenReturn(itemsValoracionsUsuari2); 

        
        Set<Usuari> usuaris = new HashSet<Usuari> (); 
        usuaris.add(Usuari2); 

        
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            DistUsuari a = new DistUsuari(Usuari1, usuaris);
            System.out.println("Resultat de getDistancies: ");
            double distancia = a.getDistancies().get(Usuari2); 
            System.out.println(a.getDistancies().toString());
            assertEquals(distancia, 20.000, 1e-15);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 8 FINALITZAT");


    }



    @AfterClass
    public static void finalDeElsTests () {
        System.out.println("TOTS TESTS FINALITZATS");
    }



    public static void main(String[] args) throws Exception {
        DrvDistUsuariJunit a = new DrvDistUsuariJunit(); 

        a.test1();
        a.test2();
        a.test3();
        a.test4();
        a.test5();
        a.test6();
        a.test7();
        a.test8();
        a.finalDeElsTests();
    }


}

