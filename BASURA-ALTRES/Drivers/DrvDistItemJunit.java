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

import domini.Item;
import domini.Atribut;
import domini.DistItem;
import domini.AtrBool;
import domini.AtrString;
import domini.AtrDouble;
import domini.AtrInt;
import domini.TipusAtribut;

public class DrvDistItemJunit {


    @Test 
    public void test1 () {
        System.out.println("EXECUCIO TEST1 :  comprovar funcionament constructora 1 (constructora buida)");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Executant constructora...");

        try {
            DistItem a = new DistItem();
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 1 FINALITZAT");
    }


    @Test 
    public void test2 () {
        System.out.println("EXECUCIO TEST2 :  comprovar funcionament constructora 2 amb un set de items, buit, i executant posteriorment la funcio 'getTotesDistancies' (hauria de ser un map buit)");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Executant funcions...");

        try {
            Set<Item> items = new HashSet<Item> (); 
            DistItem a = new DistItem(items);
            System.out.println("Resultat de 'getTotesDistancies': ");
            System.out.println(a.getTotesDistancies().toString());
            assertEquals(new HashMap<Item, HashMap<Item, Double> > (), a.getTotesDistancies());

          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage()); 
          }

          System.out.println("TEST 2 FINALITZAT");
    }


    @Test 
    public void test3 () {
        System.out.println("EXECUCIO TEST3 :  Mateix que TEST2, pero aquesta vegada el set contindrà un item, amb una serie de atributs de diversos tipus. (el resultat de 'getTotesDistancies' hauria de seguir tenint cap distància)");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        //ITEMS

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1");


        //TIPUS_ATRIBUTS

        TipusAtribut adult = mock(TipusAtribut.class); 
            when(adult.getCalculable()).thenReturn(true);
            when(adult.getTipusDada()).thenReturn("Bool");
        TipusAtribut budget = mock(TipusAtribut.class); 
            when(budget.getCalculable()).thenReturn(true);
            when(budget.getTipusDada()).thenReturn("Int");
            when(budget.getMaxValor()).thenReturn(200000000.); 
            when(budget.getMinValor()).thenReturn(0.0); 
        TipusAtribut genres = mock(TipusAtribut.class); 
            when(genres.getCalculable()).thenReturn(true);
            when(genres.getTipusDada()).thenReturn("String");
        TipusAtribut popularity = mock(TipusAtribut.class); 
            when(popularity.getCalculable()).thenReturn(true);
            when(popularity.getTipusDada()).thenReturn("Double");
            when(popularity.getMaxValor()).thenReturn(42.149697); 
            when(popularity.getMinValor()).thenReturn(0.113732); 



        //ATRIBUTS
        AtrBool adultFalse = mock(AtrBool.class); 
            when(adultFalse.getTipusAtribut()).thenReturn(adult); 
            when(adultFalse.getAtributBool()).thenReturn(false); 
        AtrInt budget98000000 = mock(AtrInt.class); 
            when(budget98000000.getTipusAtribut()).thenReturn(budget); 
            when(budget98000000.getAtributInt()).thenReturn(98000000); 
        AtrString genresAction = mock(AtrString.class); 
            when(genresAction.getTipusAtribut()).thenReturn(genres); 
            when(genresAction.getAtributString()).thenReturn("Action"); 
        AtrString genresAdventure = mock(AtrString.class); 
            when(genresAdventure.getTipusAtribut()).thenReturn(genres); 
            when(genresAdventure.getAtributString()).thenReturn("Adventure"); 
        AtrDouble popularity7284477 = mock(AtrDouble.class); 
            when(popularity7284477.getTipusAtribut()).thenReturn(popularity); 
            when(popularity7284477.getAtributDouble()).thenReturn(7.284477); 


        //GETATRIBUTS
        Set<Atribut> atributsItem1 = new HashSet<Atribut>(); 
        atributsItem1.add(adultFalse); 
        atributsItem1.add(budget98000000);
        atributsItem1.add(genresAction); 
        atributsItem1.add(genresAdventure); 
        atributsItem1.add(popularity7284477); 
        when(item1.getAtributs()).thenReturn(atributsItem1); 

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            Set<Item> items = new HashSet<Item> (); 
            items.add(item1); 
            DistItem a = new DistItem(items);
            System.out.println("Resultat de 'getTotesDistancies': ");
            System.out.println(a.getTotesDistancies().toString());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage()); 
          }

          System.out.println("TEST 3 FINALITZAT");
    }



    @Test 
    public void test4 () {
        System.out.println("EXECUCIO TEST4 :  Ara crearem una instancia de la classe amb tres items, els quals tenen exactament els mateixos atributs.");
        System.out.println("Executarem la funcio 'getTotesDistancies', i també 'getDistanciaItemItem' per comprovar que la distancia entre aquests 3 es 0");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        //ITEMS

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1");

        Item item2 = mock(Item.class); 
            when(item2.toString()).thenReturn("item2");

        Item item3 = mock(Item.class); 
            when(item3.toString()).thenReturn("item3");


        //TIPUS_ATRIBUTS

        TipusAtribut adult = mock(TipusAtribut.class); 
            when(adult.getCalculable()).thenReturn(true);
            when(adult.getTipusDada()).thenReturn("Bool");
        TipusAtribut budget = mock(TipusAtribut.class); 
            when(budget.getCalculable()).thenReturn(true);
            when(budget.getTipusDada()).thenReturn("Int");
            when(budget.getMaxValor()).thenReturn(200000000.); 
            when(budget.getMinValor()).thenReturn(0.0); 
        TipusAtribut genres = mock(TipusAtribut.class); 
            when(genres.getCalculable()).thenReturn(true);
            when(genres.getTipusDada()).thenReturn("String");
        TipusAtribut popularity = mock(TipusAtribut.class); 
            when(popularity.getCalculable()).thenReturn(true);
            when(popularity.getTipusDada()).thenReturn("Double");
            when(popularity.getMaxValor()).thenReturn(42.149697); 
            when(popularity.getMinValor()).thenReturn(0.113732); 



        //ATRIBUTS
        AtrBool adultFalse = mock(AtrBool.class); 
            when(adultFalse.getTipusAtribut()).thenReturn(adult); 
            when(adultFalse.getAtributBool()).thenReturn(false); 
        AtrInt budget98000000 = mock(AtrInt.class); 
            when(budget98000000.getTipusAtribut()).thenReturn(budget); 
            when(budget98000000.getAtributInt()).thenReturn(98000000); 
        AtrString genresAction = mock(AtrString.class); 
            when(genresAction.getTipusAtribut()).thenReturn(genres); 
            when(genresAction.getAtributString()).thenReturn("Action"); 
        AtrString genresAdventure = mock(AtrString.class); 
            when(genresAdventure.getTipusAtribut()).thenReturn(genres); 
            when(genresAdventure.getAtributString()).thenReturn("Adventure"); 
        AtrDouble popularity7284477 = mock(AtrDouble.class); 
            when(popularity7284477.getTipusAtribut()).thenReturn(popularity); 
            when(popularity7284477.getAtributDouble()).thenReturn(7.284477); 


        //GETATRIBUTS
        Set<Atribut> atributs = new HashSet<Atribut>(); 
        atributs.add(adultFalse); 
        atributs.add(budget98000000);
        atributs.add(genresAction); 
        atributs.add(genresAdventure); 
        atributs.add(popularity7284477); 
        when(item1.getAtributs()).thenReturn(atributs); 
        when(item2.getAtributs()).thenReturn(atributs); 
        when(item3.getAtributs()).thenReturn(atributs); 

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            Set<Item> items = new HashSet<Item> (); 
            items.add(item1); 
            items.add(item2); 
            items.add(item3); 
            DistItem a = new DistItem(items);
            System.out.println("Resultat de 'getTotesDistancies': ");
            System.out.println(a.getTotesDistancies().toString());
            System.out.println("Distancia entre item 2 i item 3: ");
            System.out.println(a.getDistanciaItemItem(item2, item3));
            assertEquals(0.0, a.getDistanciaItemItem(item2, item3), 1e-15);
            System.out.println("Distancia entre item 1 i item 3: ");
            System.out.println(a.getDistanciaItemItem(item1, item3));
            assertEquals(0.0, a.getDistanciaItemItem(item1, item3), 1e-15);
            System.out.println("Distancia entre item 1 i item 2  : ");
            System.out.println(a.getDistanciaItemItem(item1, item2));
            assertEquals(0.0, a.getDistanciaItemItem(item1, item2), 1e-15);
            
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage()); 
          }

          System.out.println("TEST 4 FINALITZAT");
    }


    @Test 
    public void test5 () {
        System.out.println("EXECUCIO TEST5 :  Similar al TEST4, però ara l'ítem 3 no coincidirà en cap atribut de cada Tipus Atribut de ni item 1 ni item 3. Pel que fa als atributs de tipus Int i Double aquesta distancia serà bastant gran. ");
        System.out.println("Entre l'item 2 i 1 aquests dos seguiran tenint els mateixos atributs");
        System.out.println("Per tant, la distancia entre item 1 i item 2 hauria de ser 0, mentre que la distancia entre item 2 i item 3, i item 1 i item 3 hauria de ser la mateixa, i amb un valor considerablement gran");


        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        //ITEMS

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1");

        Item item2 = mock(Item.class); 
            when(item2.toString()).thenReturn("item2");

        Item item3 = mock(Item.class); 
            when(item3.toString()).thenReturn("item3");


        //TIPUS_ATRIBUTS

        TipusAtribut adult = mock(TipusAtribut.class); 
            when(adult.getCalculable()).thenReturn(true);
            when(adult.getTipusDada()).thenReturn("Bool");
        TipusAtribut budget = mock(TipusAtribut.class); 
            when(budget.getCalculable()).thenReturn(true);
            when(budget.getTipusDada()).thenReturn("Int");
            when(budget.getMaxValor()).thenReturn(200000000.); 
            when(budget.getMinValor()).thenReturn(0.0); 
        TipusAtribut genres = mock(TipusAtribut.class); 
            when(genres.getCalculable()).thenReturn(true);
            when(genres.getTipusDada()).thenReturn("String");
        TipusAtribut popularity = mock(TipusAtribut.class); 
            when(popularity.getCalculable()).thenReturn(true);
            when(popularity.getTipusDada()).thenReturn("Double");
            when(popularity.getMaxValor()).thenReturn(42.149697); 
            when(popularity.getMinValor()).thenReturn(0.113732); 



        //ATRIBUTS

        //ATRIBUTS ITEM 1 I ITEM 2
        AtrBool adultFalse = mock(AtrBool.class); 
            when(adultFalse.getTipusAtribut()).thenReturn(adult); 
            when(adultFalse.getAtributBool()).thenReturn(false); 
        AtrInt budget98000000 = mock(AtrInt.class); 
            when(budget98000000.getTipusAtribut()).thenReturn(budget); 
            when(budget98000000.getAtributInt()).thenReturn(98000000); 
        AtrString genresAction = mock(AtrString.class); 
            when(genresAction.getTipusAtribut()).thenReturn(genres); 
            when(genresAction.getAtributString()).thenReturn("Action"); 
        AtrString genresAdventure = mock(AtrString.class); 
            when(genresAdventure.getTipusAtribut()).thenReturn(genres); 
            when(genresAdventure.getAtributString()).thenReturn("Adventure"); 
        AtrDouble popularity7284477 = mock(AtrDouble.class); 
            when(popularity7284477.getTipusAtribut()).thenReturn(popularity); 
            when(popularity7284477.getAtributDouble()).thenReturn(7.284477); 


        //ATRIBUTS ITEM 3
        AtrBool adultTrue = mock(AtrBool.class); 
            when(adultTrue.getTipusAtribut()).thenReturn(adult); 
            when(adultTrue.getAtributBool()).thenReturn(true); 
        AtrInt budget0 = mock(AtrInt.class); 
            when(budget0.getTipusAtribut()).thenReturn(budget); 
            when(budget0.getAtributInt()).thenReturn(0); 
        AtrString genresDrama = mock(AtrString.class); 
            when(genresDrama.getTipusAtribut()).thenReturn(genres); 
            when(genresDrama.getAtributString()).thenReturn("Drama");
        AtrDouble popularity31 = mock(AtrDouble.class); 
            when(popularity31.getTipusAtribut()).thenReturn(popularity); 
            when(popularity31.getAtributDouble()).thenReturn(31.); 



        //GETATRIBUTS
        //ITEM 1 I 2
        Set<Atribut> atributs1i2 = new HashSet<Atribut>(); 
        atributs1i2.add(adultFalse); 
        atributs1i2.add(budget98000000);
        atributs1i2.add(genresAction); 
        atributs1i2.add(genresAdventure); 
        atributs1i2.add(popularity7284477); 
        when(item1.getAtributs()).thenReturn(atributs1i2); 
        when(item2.getAtributs()).thenReturn(atributs1i2); 


        //ITEM 3
        Set<Atribut> atributs3 = new HashSet<Atribut>(); 
        atributs3.add(adultTrue); 
        atributs3.add(budget0);
        atributs3.add(genresDrama); 
        atributs3.add(popularity31); 
        when(item3.getAtributs()).thenReturn(atributs3); 
        

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            Set<Item> items = new HashSet<Item> (); 
            items.add(item1); 
            items.add(item2); 
            items.add(item3); 
            DistItem a = new DistItem(items);
            System.out.println("Resultat de 'getTotesDistancies': ");
            System.out.println(a.getTotesDistancies().toString());
            System.out.println("Distancia entre item 2 i item 3: ");
            System.out.println(a.getDistanciaItemItem(item2, item3));
            System.out.println("Distancia entre item 1 i item 3: ");
            System.out.println(a.getDistanciaItemItem(item1, item3));
            assertEquals(a.getDistanciaItemItem(item1, item3), a.getDistanciaItemItem(item2, item3), 1e-15);
            System.out.println("Distancia entre item 1 i item 2  : ");
            System.out.println(a.getDistanciaItemItem(item1, item2));
            assertEquals(0.0, a.getDistanciaItemItem(item1, item2), 1e-15);
            
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage()); 
          }

          System.out.println("TEST 5 FINALITZAT");
    }


    @Test 
    public void test6 () {
        System.out.println("EXECUCIO TEST6 :  El mateix que el TEST5, pero primer cridarem la constructora buida, i afegirem els 3 items amb la funcio 'afegirItem', per comprovar que aquesta funciona correctament"); 
        System.out.println("Un cop haguem impres el resultat de la funcio 'getTotesDistancies', eliminarem l'usuari 1 i tornarem a imprimir el resultat, per comprovar que la funcio 'eliminarItem' funciona"); 


        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        //ITEMS

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1");

        Item item2 = mock(Item.class); 
            when(item2.toString()).thenReturn("item2");

        Item item3 = mock(Item.class); 
            when(item3.toString()).thenReturn("item3");


        //TIPUS_ATRIBUTS

        TipusAtribut adult = mock(TipusAtribut.class); 
            when(adult.getCalculable()).thenReturn(true);
            when(adult.getTipusDada()).thenReturn("Bool");
        TipusAtribut budget = mock(TipusAtribut.class); 
            when(budget.getCalculable()).thenReturn(true);
            when(budget.getTipusDada()).thenReturn("Int");
            when(budget.getMaxValor()).thenReturn(200000000.); 
            when(budget.getMinValor()).thenReturn(0.0); 
        TipusAtribut genres = mock(TipusAtribut.class); 
            when(genres.getCalculable()).thenReturn(true);
            when(genres.getTipusDada()).thenReturn("String");
        TipusAtribut popularity = mock(TipusAtribut.class); 
            when(popularity.getCalculable()).thenReturn(true);
            when(popularity.getTipusDada()).thenReturn("Double");
            when(popularity.getMaxValor()).thenReturn(42.149697); 
            when(popularity.getMinValor()).thenReturn(0.113732); 



        //ATRIBUTS

        //ATRIBUTS ITEM 1 I ITEM 2
        AtrBool adultFalse = mock(AtrBool.class); 
            when(adultFalse.getTipusAtribut()).thenReturn(adult); 
            when(adultFalse.getAtributBool()).thenReturn(false); 
        AtrInt budget98000000 = mock(AtrInt.class); 
            when(budget98000000.getTipusAtribut()).thenReturn(budget); 
            when(budget98000000.getAtributInt()).thenReturn(98000000); 
        AtrString genresAction = mock(AtrString.class); 
            when(genresAction.getTipusAtribut()).thenReturn(genres); 
            when(genresAction.getAtributString()).thenReturn("Action"); 
        AtrString genresAdventure = mock(AtrString.class); 
            when(genresAdventure.getTipusAtribut()).thenReturn(genres); 
            when(genresAdventure.getAtributString()).thenReturn("Adventure"); 
        AtrDouble popularity7284477 = mock(AtrDouble.class); 
            when(popularity7284477.getTipusAtribut()).thenReturn(popularity); 
            when(popularity7284477.getAtributDouble()).thenReturn(7.284477); 


        //ATRIBUTS ITEM 3
        AtrBool adultTrue = mock(AtrBool.class); 
            when(adultTrue.getTipusAtribut()).thenReturn(adult); 
            when(adultTrue.getAtributBool()).thenReturn(true); 
        AtrInt budget0 = mock(AtrInt.class); 
            when(budget0.getTipusAtribut()).thenReturn(budget); 
            when(budget0.getAtributInt()).thenReturn(0); 
        AtrString genresDrama = mock(AtrString.class); 
            when(genresDrama.getTipusAtribut()).thenReturn(genres); 
            when(genresDrama.getAtributString()).thenReturn("Drama");
        AtrDouble popularity31 = mock(AtrDouble.class); 
            when(popularity31.getTipusAtribut()).thenReturn(popularity); 
            when(popularity31.getAtributDouble()).thenReturn(31.); 



        //GETATRIBUTS
        //ITEM 1 I 2
        Set<Atribut> atributs1i2 = new HashSet<Atribut>(); 
        atributs1i2.add(adultFalse); 
        atributs1i2.add(budget98000000);
        atributs1i2.add(genresAction); 
        atributs1i2.add(genresAdventure); 
        atributs1i2.add(popularity7284477); 
        when(item1.getAtributs()).thenReturn(atributs1i2); 
        when(item2.getAtributs()).thenReturn(atributs1i2); 


        //ITEM 3
        Set<Atribut> atributs3 = new HashSet<Atribut>(); 
        atributs3.add(adultTrue); 
        atributs3.add(budget0);
        atributs3.add(genresDrama); 
        atributs3.add(popularity31); 
        when(item3.getAtributs()).thenReturn(atributs3); 
        

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            Set<Item> items = new HashSet<Item> (); 
            items.add(item1); 
            items.add(item2); 
            items.add(item3); 
            DistItem a = new DistItem(items);
            System.out.println("Resultat de 'getTotesDistancies': ");
            System.out.println(a.getTotesDistancies().toString());
            a.eliminarItem(item1);
            System.out.println("Resultat després d'eliminar el primer item");
            System.out.println(a.getTotesDistancies().toString());
            
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage()); 
          }

          System.out.println("TEST 6 FINALITZAT");
    }



    @Test 
    public void test7 () {
        System.out.println("EXECUCIO TEST7 :  El mateix que el TEST5, pero aquest cop els tres items trindran atributs diferents entre tots ells mateixos"); 


        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        //ITEMS

        Item item1 = mock(Item.class); 
            when(item1.toString()).thenReturn("item1");

        Item item2 = mock(Item.class); 
            when(item2.toString()).thenReturn("item2");

        Item item3 = mock(Item.class); 
            when(item3.toString()).thenReturn("item3");


        //TIPUS_ATRIBUTS

        TipusAtribut adult = mock(TipusAtribut.class); 
            when(adult.getCalculable()).thenReturn(true);
            when(adult.getTipusDada()).thenReturn("Bool");
        TipusAtribut budget = mock(TipusAtribut.class); 
            when(budget.getCalculable()).thenReturn(true);
            when(budget.getTipusDada()).thenReturn("Int");
            when(budget.getMaxValor()).thenReturn(200000000.); 
            when(budget.getMinValor()).thenReturn(0.0); 
        TipusAtribut genres = mock(TipusAtribut.class); 
            when(genres.getCalculable()).thenReturn(true);
            when(genres.getTipusDada()).thenReturn("String");
        TipusAtribut popularity = mock(TipusAtribut.class); 
            when(popularity.getCalculable()).thenReturn(true);
            when(popularity.getTipusDada()).thenReturn("Double");
            when(popularity.getMaxValor()).thenReturn(42.149697); 
            when(popularity.getMinValor()).thenReturn(0.113732); 


        //ATRIBUTS

        AtrBool adultFalse = mock(AtrBool.class);                       //usat per item 1, 2
            when(adultFalse.getTipusAtribut()).thenReturn(adult); 
            when(adultFalse.getAtributBool()).thenReturn(false); 
        AtrBool adultTrue = mock(AtrBool.class);                        //usat per item 3
            when(adultTrue.getTipusAtribut()).thenReturn(adult); 
            when(adultTrue.getAtributBool()).thenReturn(true); 


        AtrInt budget98000000 = mock(AtrInt.class);                     //usat per item 1, 2
            when(budget98000000.getTipusAtribut()).thenReturn(budget); 
            when(budget98000000.getAtributInt()).thenReturn(98000000); 
        AtrInt budget20000000 = mock(AtrInt.class);                     //usat per item 3
            when(budget20000000.getTipusAtribut()).thenReturn(budget); 
            when(budget20000000.getAtributInt()).thenReturn(20000000); 


        AtrString genresAction = mock(AtrString.class);                 //usat per item 1, 3
            when(genresAction.getTipusAtribut()).thenReturn(genres); 
            when(genresAction.getAtributString()).thenReturn("Action"); 
        AtrString genresAdventure = mock(AtrString.class);              //usat per item 1, 2
            when(genresAdventure.getTipusAtribut()).thenReturn(genres); 
            when(genresAdventure.getAtributString()).thenReturn("Adventure"); 
            AtrString genresDrama = mock(AtrString.class);              //usat per item 3
            when(genresDrama.getTipusAtribut()).thenReturn(genres); 
            when(genresDrama.getAtributString()).thenReturn("Drama");


        AtrDouble popularity7284477 = mock(AtrDouble.class);            //usat per item 1,3
            when(popularity7284477.getTipusAtribut()).thenReturn(popularity); 
            when(popularity7284477.getAtributDouble()).thenReturn(7.284477); 
        AtrDouble popularity31 = mock(AtrDouble.class); 
            when(popularity31.getTipusAtribut()).thenReturn(popularity); //usat per item 2, 3
            when(popularity31.getAtributDouble()).thenReturn(31.); 




        //GETATRIBUTS
        //ITEM 1
        Set<Atribut> atributs1 = new HashSet<Atribut>(); 
        atributs1.add(adultFalse); 
        atributs1.add(genresAction);
        atributs1.add(genresAdventure); 
        atributs1.add(popularity7284477);
        atributs1.add(budget98000000);   
        when(item1.getAtributs()).thenReturn(atributs1); 

        //ITEM 2
        Set<Atribut> atributs2 = new HashSet<Atribut> (); 
        atributs2.add(adultFalse); 
        atributs2.add(budget98000000); 
        atributs2.add(genresAdventure); 
        atributs2.add(popularity31); 
        when(item2.getAtributs()).thenReturn(atributs2); 


        //ITEM 3
        Set<Atribut> atributs3 = new HashSet<Atribut>(); 
        atributs3.add(adultTrue); 
        atributs3.add(budget20000000);
        atributs3.add(genresAction);  
        atributs3.add(genresDrama); 
        atributs3.add(popularity7284477); 
        when(item3.getAtributs()).thenReturn(atributs3); 
        

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            Set<Item> items = new HashSet<Item> (); 
            items.add(item1); 
            items.add(item2); 
            items.add(item3); 
            DistItem a = new DistItem(items);
            System.out.println("Resultat de 'getTotesDistancies': ");
            System.out.println(a.getTotesDistancies().toString());
            
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
        DrvDistItemJunit drv = new DrvDistItemJunit();
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