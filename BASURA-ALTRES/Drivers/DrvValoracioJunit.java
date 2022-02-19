package domini.Drivers; 

import static org.mockito.Mockito.mock; 
import static org.mockito.Mockito.when;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import domini.Item;
import domini.Usuari;
import domini.Valoracio;


public class DrvValoracioJunit {

    @Test
    public void test1(){
        System.out.println("EXECUCIO TEST1 :  comprovar funcionament constructora amb un autor, item, descripcio i puntuacio ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari autor = mock(Usuari.class); 
        Item itemValorat = mock(Item.class);
        double puntuacio = 3.0;
        String descripcio = "Hola";
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            Valoracio valoracio = new Valoracio(autor, puntuacio, descripcio, itemValorat);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 1 FINALITZAT");

    }

    @Test
    public void test2(){
        System.out.println("EXECUCIO TEST2 :  comprovar funcionament getAutor() ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari autor = mock(Usuari.class); 
        when(autor.getId()).thenReturn(123345);
        Item itemValorat = mock(Item.class);
        double puntuacio = 3.0;
        String descripcio = "Hola";
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            Valoracio valoracio = new Valoracio(autor, puntuacio, descripcio, itemValorat);
            Usuari uautor = valoracio.getAutor();
            System.out.print("Autor: ");
            System.out.println(uautor.getId());
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 2 FINALITZAT");

    }

    @Test
    public void test3(){
        System.out.println("EXECUCIO TEST3 :  comprovar funcionament getPuntuacio() ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari autor = mock(Usuari.class); 
        when(autor.getId()).thenReturn(123345);
        Item itemValorat = mock(Item.class);
        double puntuacio = 3.0;
        String descripcio = "Hola";
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            Valoracio valoracio = new Valoracio(autor, puntuacio, descripcio, itemValorat);
            Double punt = valoracio.getPuntuacio();
            System.out.print("Puntuacio : ");
            System.out.println(punt);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 3 FINALITZAT");

    }

    @Test
    public void test4(){
        System.out.println("EXECUCIO TEST4 :  comprovar funcionament getDescripcio() ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari autor = mock(Usuari.class); 
        when(autor.getId()).thenReturn(123345);
        Item itemValorat = mock(Item.class);
        double puntuacio = 3.0;
        String descripcio = "Hola";
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            Valoracio valoracio = new Valoracio(autor, puntuacio, descripcio, itemValorat);
            String desc = valoracio.getDescripcio();
            System.out.print("Descripcio: ");
            System.out.println(desc);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 4 FINALITZAT");

    }

    @Test
    public void test5(){
        System.out.println("EXECUCIO TEST5 :  comprovar funcionament getItem() ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari autor = mock(Usuari.class); 
        when(autor.getId()).thenReturn(123345);
        Item itemValorat = mock(Item.class);
        when(itemValorat.getID()).thenReturn(144);
        double puntuacio = 3.0;
        String descripcio = "Hola";
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            Valoracio valoracio = new Valoracio(autor, puntuacio, descripcio, itemValorat);
            int idItem = valoracio.getItem().getID();
            System.out.print("Id d'item: ");
            System.out.println(idItem);
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 5 FINALITZAT");

    }

    @Test
    public void test6(){
        System.out.println("EXECUCIO TEST6 :  comprovar funcionament setDescripcio()  amb un usuari que és l'autor");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari autor = mock(Usuari.class); 
        when(autor.getId()).thenReturn(123345);
        Item itemValorat = mock(Item.class);
        double puntuacio = 3.0;
        String descripcio = "Sóc la descrpició antiga";
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            Valoracio valoracio = new Valoracio(autor, puntuacio, descripcio, itemValorat);
            String descAntiga = valoracio.getDescripcio();
            System.out.print("Descripcio antiga: ");
            System.out.println(descAntiga);

            valoracio.setDescripcio("Sóc la nova descripció.", autor);

            String descNova = valoracio.getDescripcio();
            System.out.print("Descripcio nova: ");
            System.out.println(descNova);
            
          }
          catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
          }

          System.out.println("TEST 6 FINALITZAT");

    }

    @Test
    public void test7(){
        System.out.println("EXECUCIO TEST7 :  comprovar funcionament setPuntuacio() amb un usuari que és l'autor");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari autor = mock(Usuari.class); 
        when(autor.getId()).thenReturn(123345);
        Item itemValorat = mock(Item.class);
        double puntuacio = 5.0;
        String descripcio = "Sóc la descrpició antiga";
        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            Valoracio valoracio = new Valoracio(autor, puntuacio, descripcio, itemValorat);
            Double puntAntiga = valoracio.getPuntuacio();
            System.out.print("Puntuació antiga: ");
            System.out.println(puntAntiga);

            valoracio.setPuntuacio(1, autor);

            Double puntNova = valoracio.getPuntuacio();
            System.out.print("Puntuació nova: ");
            System.out.println(puntNova);
            
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
      DrvValoracioJunit a = new DrvValoracioJunit(); 
      a.test1();
      a.test2();
      a.test3();
      a.test4();
      a.test5();
      a.test6();
      a.test7();
      a.finalDeElsTests();
  }
    
}
