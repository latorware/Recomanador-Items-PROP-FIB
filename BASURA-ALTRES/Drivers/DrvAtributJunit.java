package domini.Drivers; 

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock; 
import static org.mockito.Mockito.when;


import java.util.HashSet;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;


import domini.Item;

import domini.TipusAtribut;
import domini.AtrBool;
import domini.AtrDouble;
import domini.AtrInt;
import domini.AtrString;


public class DrvAtributJunit {



    @Test
    public void test1 () {
        System.out.println("EXECUCIO TEST1 :  comprovar el funcionament de la primera constructora de la subclasse AtrInt (la que només requereix que li passem com a paràmetre un tipus d'atribut). ");
        System.out.println("Escollirem que el valor de l'atribut és el número: 1");
        System.out.println("Posteriorment, executatem les funcions 'getTipusAtribut' i 'getItems' per comprovar el funcionament d'aquestes. ('getItems' hauria de retornar un set buit)");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        TipusAtribut tipusatribut = mock(TipusAtribut.class); 
            when(tipusatribut.toString()).thenReturn("tipusatribut1"); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            AtrInt a = new AtrInt(tipusatribut, 1); 
            System.out.println("Resultat de la funcio 'getTipusAtribut': ");
            System.out.println(a.getTipusAtribut().toString());
            assertEquals(tipusatribut, a.getTipusAtribut());
            System.out.println("Resultat de la funcio 'getItems': ");
            System.out.println(a.getItems().toString());
            assertEquals(new HashSet<Item> (), a.getItems());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 1 FINALITZAT");
    }


    @Test
    public void test2 () {
        System.out.println("EXECUCIO TEST2: El mateix que el TEST1, pero amb l'altre constructora."); 
        System.out.println("A més executarem la funció 'getAtributInt' per comprovar el funcionament d'aquesta"); 

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        TipusAtribut tipusatribut = mock(TipusAtribut.class); 
            when(tipusatribut.toString()).thenReturn("tipusatribut1"); 

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1"); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            AtrInt a = new AtrInt(item1, tipusatribut, 1); 
            System.out.println("Resultat de la funcio 'getTipusAtribut': ");
            System.out.println(a.getTipusAtribut().toString());
            assertEquals(tipusatribut, a.getTipusAtribut());
            System.out.println("Resultat de la funcio 'getItems': ");
            System.out.println(a.getItems().toString()); 
            System.out.println("Resultat de la funcio 'getAtributInt': ");
            System.out.println(a.getAtributInt());
            assertEquals(1, a.getAtributInt());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 2 FINALITZAT");
    }



    @Test
    public void test3 () {
        System.out.println("EXECUCIO TEST3 :  El mateix que el TEST2, pero amb la subclasse AtrString, i executant la funció 'getAtributString'");
        System.out.println("Escollirem que el valor de l'atribut és: Melodramàtic");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        TipusAtribut tipusatribut = mock(TipusAtribut.class); 
            when(tipusatribut.toString()).thenReturn("tipusatribut1"); 

            
        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1"); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            AtrString a = new AtrString(item1, tipusatribut, "Melodramàtic"); 
            System.out.println("Resultat de la funcio 'getTipusAtribut': ");
            System.out.println(a.getTipusAtribut().toString());
            assertEquals(tipusatribut, a.getTipusAtribut());
            System.out.println("Resultat de la funcio 'getItems': ");
            System.out.println(a.getItems().toString()); 
            System.out.println("Resultat de la funcio 'getAtributString': ");
            System.out.println(a.getAtributString());
            assertEquals("Melodramàtic", a.getAtributString());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 3 FINALITZAT");
    }



    @Test
    public void test4 () {
        System.out.println("EXECUCIO TEST4 :  El mateix que el TEST2, pero amb la subclasse AtrDouble, i executant la funció 'getAtributDouble'");
        System.out.println("Escollirem que el valor de l'atribut és: 0.000001");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        TipusAtribut tipusatribut = mock(TipusAtribut.class); 
            when(tipusatribut.toString()).thenReturn("tipusatribut1"); 

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1"); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            AtrDouble a = new AtrDouble(item1, tipusatribut, 0.000001); 
            System.out.println("Resultat de la funcio 'getTipusAtribut': ");
            System.out.println(a.getTipusAtribut().toString());
            assertEquals(tipusatribut, a.getTipusAtribut());
            System.out.println("Resultat de la funcio 'getItems': ");
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getAtributString': ");
            System.out.println(a.getAtributDouble());
            assertEquals(0.000001, a.getAtributDouble(), 1e-15);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 4 FINALITZAT");
    }



    @Test
    public void test5 () {
        System.out.println("EXECUCIO TEST5 :  El mateix que el TEST2, pero amb la subclasse AtrBool, i executant la funció 'getAtributBool'");
        System.out.println("Escollirem que el valor de l'atribut és: True");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        TipusAtribut tipusatribut = mock(TipusAtribut.class); 
            when(tipusatribut.toString()).thenReturn("tipusatribut1"); 

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1"); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            AtrBool a = new AtrBool(item1, tipusatribut, true); 
            System.out.println("Resultat de la funcio 'getTipusAtribut': ");
            System.out.println(a.getTipusAtribut().toString());
            assertEquals(tipusatribut, a.getTipusAtribut());
            System.out.println("Resultat de la funcio 'getItems': ");
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getAtributString': ");
            System.out.println(a.getAtributBool());
            assertEquals(true, a.getAtributBool());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 5 FINALITZAT");
    }


    @Test
    public void test6 () {
        System.out.println("EXECUCIO TEST6 :  Ara, crearem una instància de la classe AtrInt (per exemple), amb la constructora simple."); 
        System.out.println("i anirem afegint items fins a tenir-ne 5, amb la funció 'addItem', executant 'getItems' per comprovar que la funcio 'addItem' funciona correctament");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        TipusAtribut tipusatribut = mock(TipusAtribut.class); 
            when(tipusatribut.toString()).thenReturn("tipusatribut1"); 

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1"); 
        Item item2 = mock(Item.class); 
            when(item2.toString()).thenReturn("item2"); 
        Item item3 = mock(Item.class); 
            when(item3.toString()).thenReturn("item3"); 
        Item item4 = mock(Item.class); 
            when(item4.toString()).thenReturn("item4"); 
        Item item5 = mock(Item.class); 
            when(item5.toString()).thenReturn("item5"); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            AtrInt a = new AtrInt(tipusatribut, 1); 
            System.out.println("Resultat de la funcio 'getItems' (quan no hi ha cap item): ");
            System.out.println(a.getItems().toString());
            assertEquals(new HashSet<Item> (), a.getItems());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 1): ");
            a.addItem(item1);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 2): ");
            a.addItem(item2);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 3): ");
            a.addItem(item3);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 4): ");
            a.addItem(item4);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 5): ");
            a.addItem(item5);
            System.out.println(a.getItems().toString());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 6 FINALITZAT");
    }





    @Test
    public void test7 () {
        System.out.println("EXECUCIO TEST7 :  El mateix que el TEST6, pero finalment anirem eliminant els items un per un, amb la funció 'deleteItem', per comprovar el bon funcionament d'aquesta"); 

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        TipusAtribut tipusatribut = mock(TipusAtribut.class); 
            when(tipusatribut.toString()).thenReturn("tipusatribut1"); 

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1"); 
        Item item2 = mock(Item.class); 
            when(item2.toString()).thenReturn("item2"); 
        Item item3 = mock(Item.class); 
            when(item3.toString()).thenReturn("item3"); 
        Item item4 = mock(Item.class); 
            when(item4.toString()).thenReturn("item4"); 
        Item item5 = mock(Item.class); 
            when(item5.toString()).thenReturn("item5"); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            AtrInt a = new AtrInt(tipusatribut, 1); 
            System.out.println("Resultat de la funcio 'getItems' (quan no hi ha cap item): ");
            System.out.println(a.getItems().toString());
            assertEquals(new HashSet<Item> (), a.getItems());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 1): ");
            a.addItem(item1);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 2): ");
            a.addItem(item2);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 3): ");
            a.addItem(item3);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 4): ");
            a.addItem(item4);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit l'ítem 5): ");
            a.addItem(item5);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit eliminat item 5): ");
            a.deleteItem(item5);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit eliminat item 4): ");
            a.deleteItem(item4);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit eliminat item 3): ");
            a.deleteItem(item3);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit eliminat item 2): ");
            a.deleteItem(item2);
            System.out.println(a.getItems().toString());
            System.out.println("Resultat de la funcio 'getItems' (quan s'ha afegit eliminat item 1): ");
            a.deleteItem(item1);
            System.out.println(a.getItems().toString());

            assertEquals(new HashSet<Item> (), a.getItems());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 7 FINALITZAT");
    }

    @AfterClass
    public static void finalDeElsTests () {
        System.out.println("TOTS TESTS FINALITZATS");

    }
    
    public static void main(String[] args) throws Exception {
        DrvAtributJunit drv = new DrvAtributJunit();
        drv.test1();
        drv.test2();
        drv.test3();
        drv.test4();
        drv.test5();
        drv.test6();
        drv.test7();
        drv.finalDeElsTests();
    }
}
