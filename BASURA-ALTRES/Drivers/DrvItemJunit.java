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
import utils.randoms;

import domini.Item;
import domini.Valoracio;
import domini.Usuari;
import domini.Atribut;

public class DrvItemJunit {


    @Test
    public void test1 () {
        System.out.println("EXECUCIO TEST1 :  comprovar funcionament constructora complexa, (és a dir, la que no es buida), amb un map de valoracions buit, i un set d'atributs que conté un atribut ");
        System.out.println("Es triara un id random ");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        Atribut atribut1 = mock(Atribut.class); 
            when(atribut1.toString()).thenReturn("atribut1"); 

        System.out.println("Creació de mockitos realitzada");


        HashMap<Usuari, Valoracio> valoracions = new HashMap<Usuari, Valoracio>(); 
        Set<Atribut> atributs = new HashSet<Atribut>();  
        atributs.add(atribut1); 
        int id = randoms.random_int(-99999, 99999); 

        System.out.println("Executant constructora...");

        try {

            Item a = new Item(id, valoracions, atributs);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 1 FINALITZAT");
    }



    @Test
    public void test2 () {
        System.out.println("EXECUCIO TEST2 :  mateix que TEST1, pero ara executant tambe les funcions 'getAtributs' i 'getID' i imprimint el seu resultat per comprovar el funcionament");
        System.out.println("Es triara un id random ");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        Atribut atribut1 = mock(Atribut.class); 
            when(atribut1.toString()).thenReturn("atribut1"); 

        System.out.println("Creació de mockitos realitzada");


        HashMap<Usuari, Valoracio> valoracions = new HashMap<Usuari, Valoracio>(); 
        Set<Atribut> atributs = new HashSet<Atribut>();  
        atributs.add(atribut1); 
        int id = randoms.random_int(-99999, 99999); 

        System.out.println("Executant funcions...");

        try {

            Item a = new Item(id, valoracions, atributs);
            System.out.println("Resultat de 'getID':");
            System.out.println(a.getID());
            assertEquals(id, a.getID());
            System.out.println("Resultat de 'getAtributs': ");
            System.out.println(a.getAtributs().toString());
            assertEquals(atributs, a.getAtributs());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 2 FINALITZAT");
    }





    @Test
    public void test3 () {
        System.out.println("EXECUCIO TEST3 : mateix que TEST2, pero aquesta vegada amb varies valoracions, i també més d'un atribut");
        System.out.println("Tambe es fara us de la funcio 'getValoracions' per comprovar el seu funcionament");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        //ATRIBUTS

        Atribut atribut1 = mock(Atribut.class); 
            when(atribut1.toString()).thenReturn("atribut1"); 

        Atribut atribut2 = mock(Atribut.class); 
            when(atribut2.toString()).thenReturn("atribut2"); 

        Atribut atribut3 = mock(Atribut.class); 
            when(atribut3.toString()).thenReturn("atribut3"); 

        Atribut atribut4 = mock(Atribut.class); 
            when(atribut4.toString()).thenReturn("atribut4"); 

        Atribut atribut5 = mock(Atribut.class); 
            when(atribut5.toString()).thenReturn("atribut5"); 



        //VALORACIONS

        Valoracio valoracio1 = mock(Valoracio.class);
            when(valoracio1.toString()).thenReturn("valoracio1");

        Valoracio valoracio2 = mock(Valoracio.class); 
            when(valoracio2.toString()).thenReturn("valoracio2"); 

        Valoracio valoracio3 = mock(Valoracio.class); 
            when(valoracio3.toString()).thenReturn("valoracio3"); 

        Valoracio valoracio4 = mock(Valoracio.class); 
            when(valoracio4.toString()).thenReturn("valoracio4"); 

        Valoracio valoracio5 = mock(Valoracio.class); 
            when(valoracio5.toString()).thenReturn("valoracio5"); 



        //USUARIS

        Usuari usuari1 = mock(Usuari.class);
            when(usuari1.toString()).thenReturn("usuari1");

        Usuari usuari2 = mock(Usuari.class); 
            when(usuari2.toString()).thenReturn("usuari2"); 

        Usuari usuari3 = mock(Usuari.class); 
            when(usuari3.toString()).thenReturn("usuari3"); 

        Usuari usuari4 = mock(Usuari.class); 
            when(usuari4.toString()).thenReturn("usuari4"); 

        Usuari usuari5 = mock(Usuari.class); 
            when(usuari5.toString()).thenReturn("usuari5"); 

        
        System.out.println("Creació de mockitos realitzada");



        HashMap<Usuari, Valoracio> valoracions = new HashMap<Usuari, Valoracio>(); 
        valoracions.put(usuari1, valoracio1); 
        valoracions.put(usuari2, valoracio2); 
        valoracions.put(usuari3, valoracio3); 
        valoracions.put(usuari4, valoracio4); 
        valoracions.put(usuari5, valoracio5); 
        Set<Atribut> atributs = new HashSet<Atribut>();  
        atributs.add(atribut1); 
        atributs.add(atribut2); 
        atributs.add(atribut3); 
        atributs.add(atribut4); 
        atributs.add(atribut5); 
        int id = randoms.random_int(-99999, 99999); 

        System.out.println("Executant funcions...");

        try {

            Item a = new Item(id, valoracions, atributs);
            System.out.println("Resultat de 'getID':");
            System.out.println(a.getID());
            assertEquals(id, a.getID());
            System.out.println("Resultat de 'getAtributs': ");
            System.out.println(a.getAtributs().toString());
            assertEquals(atributs, a.getAtributs());
            System.out.println("Resultat de 'getValoracions': ");
            System.out.println(a.getValoracions().toString());
            assertEquals(valoracions, a.getValoracions());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 3 FINALITZAT");
    }




    @Test
    public void test4 () {
        System.out.println("EXECUCIO TEST4 : Mateix que TEST3, pero aquesta vegada afegirem les valoracions amb la funció 'addValoracio', per comprovar que funciona bé.");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        //ATRIBUTS

        Atribut atribut1 = mock(Atribut.class); 
            when(atribut1.toString()).thenReturn("atribut1"); 

        Atribut atribut2 = mock(Atribut.class); 
            when(atribut2.toString()).thenReturn("atribut2"); 

        Atribut atribut3 = mock(Atribut.class); 
            when(atribut3.toString()).thenReturn("atribut3"); 

        Atribut atribut4 = mock(Atribut.class); 
            when(atribut4.toString()).thenReturn("atribut4"); 

        Atribut atribut5 = mock(Atribut.class); 
            when(atribut5.toString()).thenReturn("atribut5"); 



        //VALORACIONS

        Valoracio valoracio1 = mock(Valoracio.class);
            when(valoracio1.toString()).thenReturn("valoracio1");

        Valoracio valoracio2 = mock(Valoracio.class); 
            when(valoracio2.toString()).thenReturn("valoracio2"); 

        Valoracio valoracio3 = mock(Valoracio.class); 
            when(valoracio3.toString()).thenReturn("valoracio3"); 

        Valoracio valoracio4 = mock(Valoracio.class); 
            when(valoracio4.toString()).thenReturn("valoracio4"); 

        Valoracio valoracio5 = mock(Valoracio.class); 
            when(valoracio5.toString()).thenReturn("valoracio5"); 



        //USUARIS

        Usuari usuari1 = mock(Usuari.class);
            when(usuari1.toString()).thenReturn("usuari1");

        Usuari usuari2 = mock(Usuari.class); 
            when(usuari2.toString()).thenReturn("usuari2"); 

        Usuari usuari3 = mock(Usuari.class); 
            when(usuari3.toString()).thenReturn("usuari3"); 

        Usuari usuari4 = mock(Usuari.class); 
            when(usuari4.toString()).thenReturn("usuari4"); 

        Usuari usuari5 = mock(Usuari.class); 
            when(usuari5.toString()).thenReturn("usuari5"); 

        
        System.out.println("Creació de mockitos realitzada");


        HashMap<Usuari, Valoracio> valoracions = new HashMap<Usuari, Valoracio>(); 
        Set<Atribut> atributs = new HashSet<Atribut>();  
        atributs.add(atribut1); 
        atributs.add(atribut2); 
        atributs.add(atribut3); 
        atributs.add(atribut4); 
        atributs.add(atribut5); 
        int id = randoms.random_int(-99999, 99999); 

        System.out.println("Executant funcions...");

        try {

            Item a = new Item(id, valoracions, atributs);
            System.out.println("Resultat de 'getID':");
            System.out.println(a.getID());
            assertEquals(id, a.getID());
            System.out.println("Resultat de 'getAtributs': ");
            System.out.println(a.getAtributs().toString());
            assertEquals(atributs, a.getAtributs());
            System.out.println("Resultat de 'getValoracions (sense valoracions)': ");
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 1 valoració)");
            a.addValoracio(valoracio1, usuari1);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 2 valoracions)");
            a.addValoracio(valoracio2, usuari2);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 3 valoracions)");
            a.addValoracio(valoracio3, usuari3);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 4 valoracions)");
            a.addValoracio(valoracio4, usuari4);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 5 valoracions)");
            a.addValoracio(valoracio5, usuari5);
            System.out.println(a.getValoracions().toString());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 4 FINALITZAT");
    }




    @Test
    public void test5 () {
        System.out.println("EXECUCIO TEST5 : Mateix que TEST4, pero un cop haguem afegit les 5 valoracions, les eliminarem una per una amb la funció 'deleteValoracio', per comprovar el seu funcionament");

        System.out.println("Iniciant operacions previes...");


        System.out.println("Creant mockitos...");

        //ATRIBUTS

        Atribut atribut1 = mock(Atribut.class); 
            when(atribut1.toString()).thenReturn("atribut1"); 

        Atribut atribut2 = mock(Atribut.class); 
            when(atribut2.toString()).thenReturn("atribut2"); 

        Atribut atribut3 = mock(Atribut.class); 
            when(atribut3.toString()).thenReturn("atribut3"); 

        Atribut atribut4 = mock(Atribut.class); 
            when(atribut4.toString()).thenReturn("atribut4"); 

        Atribut atribut5 = mock(Atribut.class); 
            when(atribut5.toString()).thenReturn("atribut5"); 



        //VALORACIONS

        Valoracio valoracio1 = mock(Valoracio.class);
            when(valoracio1.toString()).thenReturn("valoracio1");

        Valoracio valoracio2 = mock(Valoracio.class); 
            when(valoracio2.toString()).thenReturn("valoracio2"); 

        Valoracio valoracio3 = mock(Valoracio.class); 
            when(valoracio3.toString()).thenReturn("valoracio3"); 

        Valoracio valoracio4 = mock(Valoracio.class); 
            when(valoracio4.toString()).thenReturn("valoracio4"); 

        Valoracio valoracio5 = mock(Valoracio.class); 
            when(valoracio5.toString()).thenReturn("valoracio5"); 



        //USUARIS

        Usuari usuari1 = mock(Usuari.class);
            when(usuari1.toString()).thenReturn("usuari1");

        Usuari usuari2 = mock(Usuari.class); 
            when(usuari2.toString()).thenReturn("usuari2"); 

        Usuari usuari3 = mock(Usuari.class); 
            when(usuari3.toString()).thenReturn("usuari3"); 

        Usuari usuari4 = mock(Usuari.class); 
            when(usuari4.toString()).thenReturn("usuari4"); 

        Usuari usuari5 = mock(Usuari.class); 
            when(usuari5.toString()).thenReturn("usuari5"); 

        
        System.out.println("Creació de mockitos realitzada");


        HashMap<Usuari, Valoracio> valoracions = new HashMap<Usuari, Valoracio>(); 
        Set<Atribut> atributs = new HashSet<Atribut>();  
        atributs.add(atribut1); 
        atributs.add(atribut2); 
        atributs.add(atribut3); 
        atributs.add(atribut4); 
        atributs.add(atribut5); 
        int id = randoms.random_int(-99999, 99999); 

        System.out.println("Executant funcions...");

        try {

            Item a = new Item(id, valoracions, atributs);
            System.out.println("Resultat de 'getID':");
            System.out.println(a.getID());
            assertEquals(id, a.getID());
            System.out.println("Resultat de 'getAtributs': ");
            System.out.println(a.getAtributs().toString());
            assertEquals(atributs, a.getAtributs());
            System.out.println("Resultat de 'getValoracions (sense valoracions)': ");
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 1 valoració)");
            a.addValoracio(valoracio1, usuari1);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 2 valoracions)");
            a.addValoracio(valoracio2, usuari2);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 3 valoracions)");
            a.addValoracio(valoracio3, usuari3);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 4 valoracions)");
            a.addValoracio(valoracio4, usuari4);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'afegir 5 valoracions)");
            a.addValoracio(valoracio5, usuari5);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'eliminar la valoracio de l'usuari 5)");
            a.deleteValoracio(usuari5);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'eliminar la valoracio de l'usuari 4)");
            a.deleteValoracio(usuari4);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'eliminar la valoracio de l'usuari 3)");
            a.deleteValoracio(usuari3);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'eliminar la valoracio de l'usuari 2)");
            a.deleteValoracio(usuari2);
            System.out.println(a.getValoracions().toString());
            System.out.println("Resultat de 'getValoracions (després d'eliminar la valoracio de l'usuari 1)");
            a.deleteValoracio(usuari1);
            System.out.println(a.getValoracions().toString());

          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 4 FINALITZAT");
    }



    @AfterClass
    public static void finalDeElsTests () {
        System.out.println("TOTS TESTS FINALITZATS");
    }



    public static void main(String[] args) throws Exception {
        DrvItemJunit a = new DrvItemJunit(); 
        a.test1();
        a.test2();
        a.test3();
        a.test4();
        a.test5();
        a.finalDeElsTests();
    }
}