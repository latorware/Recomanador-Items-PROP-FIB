package domini.Drivers; 

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock; 
import static org.mockito.Mockito.when;


import java.util.HashSet;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import domini.AtrDouble;
import domini.AtrInt;
import domini.AtrString;
import domini.Atribut;
import domini.TipusAtribut;


public class DrvTipusAtributJunit {


    @Test
    public void test1 () {
        System.out.println("EXECUCIO TEST1 :  comprovar el funcionament de la constructora de la classe");
        System.out.println("Escollirem que el nom del Tipus d'atribut és: 'Budget'  ;  i el tipus de dada és de: 'Int'");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Executant funcions...");

        try {

            TipusAtribut a = new TipusAtribut("Budget", "Int"); 
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 1 FINALITZAT");
    }


    @Test
    public void test2 () {
        System.out.println("EXECUCIO TEST2 :  mateix que TEST1, però executant a més les funcions 'getNom', i 'getTipusDada' ");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Executant funcions...");

        try {

            TipusAtribut a = new TipusAtribut("Budget", "Int"); 
            System.out.println("Resultat de la funcio 'getNom' :");
            System.out.println(a.getNom());
            assertEquals("Budget", a.getNom());
            System.out.println("Resultat de la funcio 'getTipusDada' :");
            System.out.println(a.getTipusDada());
            assertEquals("Int", a.getTipusDada());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 2 FINALITZAT");
    }


    @Test
    public void test3 () {
        System.out.println("EXECUCIO TEST3 :  ara crearem una instància de la classe amb el mateix nom i tipus, com al TEST 1 i TEST2, però el que farem sera");
        System.out.println("executar la funció 'setCalculable' amb true, i 'getCalculable' per comprovar el funcionament d'aquestes dos funcions");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Executant funcions...");

        try {

            TipusAtribut a = new TipusAtribut("Budget", "Int"); 
            System.out.println("Resultat de la funcio 'getCalculable' :");
            a.setCalculable(true);
            System.out.println(a.getCalculable());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 3 FINALITZAT");
    }


    @Test
    public void test4 () {
        System.out.println("EXECUCIO TEST4 :  ara crearem una instancia de la classe, i anirem afegint atributs al TipusAtribut amb la funció 'afegirAtribut', fins a tenir 5 atributs, i executarem la funció 'getAtributs', per comprovar el funcionament d'aquestes dos funcions");
        System.out.println("El tipus de dada de Tipus atribut serà: String. Per tant, farem veure que estem afegint atributs de tipus String, AtrString");
        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");


        AtrString atribut1 = mock(AtrString.class); 
          when(atribut1.toString()).thenReturn("atribut1"); 
        
        AtrString atribut2 = mock(AtrString.class); 
            when(atribut2.toString()).thenReturn("atribut2"); 
        
        AtrString atribut3 = mock(AtrString.class); 
            when(atribut3.toString()).thenReturn("atribut3"); 

        AtrString atribut4 = mock(AtrString.class); 
            when(atribut4.toString()).thenReturn("atribut4"); 

        AtrString atribut5 = mock(AtrString.class);
            when(atribut5.toString()).thenReturn("atribut5"); 
            

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            TipusAtribut a = new TipusAtribut("Budget", "String"); 
            System.out.println("Resultat de la funcio 'getAtributs' (quan no s'ha afegit cap atribut) :");
            System.out.println(a.getAtributs().toString());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 1) :");
            a.afegirAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 2) :");
            a.afegirAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 3) :");
            a.afegirAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 4) :");
            a.afegirAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 5) :");
            a.afegirAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 4 FINALITZAT");
    }


    @Test
    public void test5 () {
        System.out.println("EXECUCIO TEST5 :  mateix que TEST4, pero ara anirem eliminant finalment els atributs un per un amb funció 'eliminaAtribut', per comprovar el bon funcionament");
        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");


        AtrString atribut1 = mock(AtrString.class); 
          when(atribut1.toString()).thenReturn("atribut1"); 
        
        AtrString atribut2 = mock(AtrString.class); 
            when(atribut2.toString()).thenReturn("atribut2"); 
        
        AtrString atribut3 = mock(AtrString.class); 
            when(atribut3.toString()).thenReturn("atribut3"); 

        AtrString atribut4 = mock(AtrString.class); 
            when(atribut4.toString()).thenReturn("atribut4"); 

        AtrString atribut5 = mock(AtrString.class);
            when(atribut5.toString()).thenReturn("atribut5"); 
            

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            TipusAtribut a = new TipusAtribut("Budget", "String"); 
            System.out.println("Resultat de la funcio 'getAtributs' (quan no s'ha afegit cap atribut) :");
            System.out.println(a.getAtributs().toString());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 1) :");
            a.afegirAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 2) :");
            a.afegirAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 3) :");
            a.afegirAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 4) :");
            a.afegirAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 5) :");
            a.afegirAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 5)");
            a.eliminaAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 4)");
            a.eliminaAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 3)");
            a.eliminaAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 2)");
            a.eliminaAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 1)");
            a.eliminaAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 5 FINALITZAT");
    }



    @Test
    public void test6 () {
        System.out.println("EXECUCIO TEST6 :  mateix que TEST5, pero ara el Tipus Atribut sera de tipus Double. ");
        System.out.println("Cada un dels atributs tindrà un valor de 1 a 5 respectivament (atribut 1 tindrà valor 1, atribut 5 tindrà valor 5)");
        System.out.println("Cada cop que eliminem o afegim un atribut, executarem les funcions 'getMaxValor' i 'getMinValor' per comprovar el correcte funcionament");
        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");


        AtrDouble atribut1 = mock(AtrDouble.class); 
          when(atribut1.toString()).thenReturn("atribut1"); 
          when(atribut1.getAtributDouble()).thenReturn(1.0); 
        
        AtrDouble atribut2 = mock(AtrDouble.class); 
            when(atribut2.toString()).thenReturn("atribut2"); 
            when(atribut2.getAtributDouble()).thenReturn(2.0); 
        
        AtrDouble atribut3 = mock(AtrDouble.class); 
            when(atribut3.toString()).thenReturn("atribut3"); 
            when(atribut3.getAtributDouble()).thenReturn(3.0); 

        AtrDouble atribut4 = mock(AtrDouble.class); 
            when(atribut4.toString()).thenReturn("atribut4"); 
            when(atribut4.getAtributDouble()).thenReturn(4.0); 

        AtrDouble atribut5 = mock(AtrDouble.class);
            when(atribut5.toString()).thenReturn("atribut5"); 
            when(atribut5.getAtributDouble()).thenReturn(5.0); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            TipusAtribut a = new TipusAtribut("Budget", "Double"); 
            System.out.println("Resultat de la funcio 'getAtributs' (quan no s'ha afegit cap atribut) :");
            System.out.println(a.getAtributs().toString());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 1) :");
            a.afegirAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 2) :");
            a.afegirAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 3) :");
            a.afegirAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 4) :");
            a.afegirAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 5) :");
            a.afegirAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 1)");
            a.eliminaAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 2)");
            a.eliminaAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 3)");
            a.eliminaAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 4)");
            a.eliminaAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 5)");
            a.eliminaAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 6 FINALITZAT");
    }


    @Test
    public void test7 () {
        System.out.println("EXECUCIO TEST7 :  mateix que TEST6, pero amb l'ordre d'afegir i eliminar atributs invers");
        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");


        AtrDouble atribut1 = mock(AtrDouble.class); 
          when(atribut1.toString()).thenReturn("atribut1"); 
          when(atribut1.getAtributDouble()).thenReturn(1.0); 
        
        AtrDouble atribut2 = mock(AtrDouble.class); 
            when(atribut2.toString()).thenReturn("atribut2"); 
            when(atribut2.getAtributDouble()).thenReturn(2.0); 
        
        AtrDouble atribut3 = mock(AtrDouble.class); 
            when(atribut3.toString()).thenReturn("atribut3"); 
            when(atribut3.getAtributDouble()).thenReturn(3.0); 

        AtrDouble atribut4 = mock(AtrDouble.class); 
            when(atribut4.toString()).thenReturn("atribut4"); 
            when(atribut4.getAtributDouble()).thenReturn(4.0); 

        AtrDouble atribut5 = mock(AtrDouble.class);
            when(atribut5.toString()).thenReturn("atribut5"); 
            when(atribut5.getAtributDouble()).thenReturn(5.0); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            TipusAtribut a = new TipusAtribut("Budget", "Double"); 
            System.out.println("Resultat de la funcio 'getAtributs' (quan no s'ha afegit cap atribut) :");
            System.out.println(a.getAtributs().toString());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 5) :");
            a.afegirAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 4) :");
            a.afegirAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 3) :");
            a.afegirAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 2) :");
            a.afegirAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 1) :");
            a.afegirAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 5)");
            a.eliminaAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 4)");
            a.eliminaAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 3)");
            a.eliminaAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 2)");
            a.eliminaAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 1)");
            a.eliminaAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 7 FINALITZAT");
    }




    @Test
    public void test8 () {
        System.out.println("EXECUCIO TEST8 :  mateix que TEST6, pero el tipus d'atribut ara sera Int, i per tant els atributs també");
        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");


        AtrInt atribut1 = mock(AtrInt.class); 
          when(atribut1.toString()).thenReturn("atribut1"); 
          when(atribut1.getAtributInt()).thenReturn(1); 
        
        AtrInt atribut2 = mock(AtrInt.class); 
            when(atribut2.toString()).thenReturn("atribut2"); 
            when(atribut2.getAtributInt()).thenReturn(2); 
        
        AtrInt atribut3 = mock(AtrInt.class); 
            when(atribut3.toString()).thenReturn("atribut3"); 
            when(atribut3.getAtributInt()).thenReturn(3); 

        AtrInt atribut4 = mock(AtrInt.class); 
            when(atribut4.toString()).thenReturn("atribut4"); 
            when(atribut4.getAtributInt()).thenReturn(4); 

        AtrInt atribut5 = mock(AtrInt.class);
            when(atribut5.toString()).thenReturn("atribut5"); 
            when(atribut5.getAtributInt()).thenReturn(5); 

        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            TipusAtribut a = new TipusAtribut("Budget", "Int"); 
            System.out.println("Resultat de la funcio 'getAtributs' (quan no s'ha afegit cap atribut) :");
            System.out.println(a.getAtributs().toString());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 1) :");
            a.afegirAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 2) :");
            a.afegirAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 3) :");
            a.afegirAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 4) :");
            a.afegirAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 5) :");
            a.afegirAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 1)");
            a.eliminaAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 2)");
            a.eliminaAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 3)");
            a.eliminaAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 4)");
            a.eliminaAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 5)");
            a.eliminaAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 8 FINALITZAT");
    }




    @Test
    public void test9 () {
        System.out.println("EXECUCIO TEST9 :  mateix que TEST7, pero pero el tipus d'atribut ara sera Int, i per tant els atributs també");
        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");


        AtrInt atribut1 = mock(AtrInt.class); 
          when(atribut1.toString()).thenReturn("atribut1"); 
          when(atribut1.getAtributInt()).thenReturn(1); 
        
        AtrInt atribut2 = mock(AtrInt.class); 
            when(atribut2.toString()).thenReturn("atribut2"); 
            when(atribut2.getAtributInt()).thenReturn(2); 
        
        AtrInt atribut3 = mock(AtrInt.class); 
            when(atribut3.toString()).thenReturn("atribut3"); 
            when(atribut3.getAtributInt()).thenReturn(3); 

        AtrInt atribut4 = mock(AtrInt.class); 
            when(atribut4.toString()).thenReturn("atribut4"); 
            when(atribut4.getAtributInt()).thenReturn(4); 

        AtrInt atribut5 = mock(AtrInt.class);
            when(atribut5.toString()).thenReturn("atribut5"); 
            when(atribut5.getAtributInt()).thenReturn(5); 
        System.out.println("Creació de mockitos realitzada");


        System.out.println("Executant funcions...");

        try {

            TipusAtribut a = new TipusAtribut("Budget", "Int"); 
            System.out.println("Resultat de la funcio 'getAtributs' (quan no s'ha afegit cap atribut) :");
            System.out.println(a.getAtributs().toString());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 5) :");
            a.afegirAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 4) :");
            a.afegirAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 3) :");
            a.afegirAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 2) :");
            a.afegirAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha afegit l'atribut 1) :");
            a.afegirAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 5)");
            a.eliminaAtribut(atribut5);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 4)");
            a.eliminaAtribut(atribut4);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 3)");
            a.eliminaAtribut(atribut3);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 2)");
            a.eliminaAtribut(atribut2);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            System.out.println("Resultat de la funcio 'getAtributs' (quan s'ha eliminat l'atribut 1)");
            a.eliminaAtribut(atribut1);
            System.out.println(a.getAtributs().toString());
            System.out.println("getMaxValor: " + a.getMaxValor() + "       getMinValor: " + a.getMinValor());
            assertEquals(new HashSet<Atribut> (), a.getAtributs());
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
      DrvTipusAtributJunit a = new DrvTipusAtributJunit(); 
      a.test1();
      a.test2();
      a.test3();
      a.test4();
      a.test5();
      a.test6();
      a.test7();
      a.test8();
      a.test9();
      a.finalDeElsTests();
  }

}