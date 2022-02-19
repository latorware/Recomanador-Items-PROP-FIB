package domini.Drivers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import domini.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;


public class DrvSlopeOneJunit {


    @Test
    public void test1 () {
        System.out.println("EXECUCIO TEST1 :  comprovar funcionament constructora amb un usuari ");

        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari usuari = mock(Usuari.class);
        HashMap<Integer,Usuari> usuarisVeins = new HashMap<>();

        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant constructora...");

        try {
            SlopeOne slopeOne = new SlopeOne(usuari,usuarisVeins);
        }
        catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
        }

        System.out.println("TEST 1 FINALITZAT");
        System.out.println();

    }

    @Test
    public void test2 () {
        System.out.println("EXECUCIO TEST2 :  execució de SlopeOne amb usuari i els seus veins");
        System.out.println("Iniciant operacions previes...");

        System.out.println("Creant mockitos...");

        Usuari usuari1 = mock(Usuari.class);
        when(usuari1.getId()).thenReturn(1);
        Usuari usuari2 = mock(Usuari.class);
        when(usuari2.getId()).thenReturn(2);
        Usuari usuari3 = mock(Usuari.class);
        when(usuari3.getId()).thenReturn(3);


        Item item1 = mock(Item.class);
        when(item1.getID()).thenReturn(1);
        Item item2 = mock(Item.class);
        when(item2.getID()).thenReturn(2);
        Item item3 = mock(Item.class);
        when(item3.getID()).thenReturn(3);


        Valoracio valoracio1 = mock(Valoracio.class);
        when(valoracio1.getPuntuacio()).thenReturn(5.0);
        Valoracio valoracio2 = mock(Valoracio.class);
        when(valoracio2.getPuntuacio()).thenReturn(3.0);
        Valoracio valoracio3 = mock(Valoracio.class);
        when(valoracio3.getPuntuacio()).thenReturn(2.0);
        Valoracio valoracio4 = mock(Valoracio.class);
        when(valoracio4.getPuntuacio()).thenReturn(3.0);
        Valoracio valoracio5 = mock(Valoracio.class);
        when(valoracio5.getPuntuacio()).thenReturn(4.0);
        Valoracio valoracio6 = mock(Valoracio.class);
        when(valoracio6.getPuntuacio()).thenReturn(2.0);
        Valoracio valoracio7 = mock(Valoracio.class);
        when(valoracio7.getPuntuacio()).thenReturn(5.0);

        HashMap<Item,Valoracio> valoracions1 = new HashMap<>();
        valoracions1.put(item1, valoracio1);
        valoracions1.put(item2, valoracio2);
        valoracions1.put(item3, valoracio3);
        when(usuari1.getItemsValoracions()).thenReturn(valoracions1);

        HashMap<Item,Valoracio> valoracions2 = new HashMap<>();
        valoracions2.put(item1, valoracio4);
        valoracions2.put(item2, valoracio5);
        when(usuari2.getItemsValoracions()).thenReturn(valoracions2);

        HashMap<Item,Valoracio> valoracions3 = new HashMap<>();
        valoracions3.put(item2, valoracio6);
        valoracions3.put(item3, valoracio7);
        when(usuari3.getItemsValoracions()).thenReturn(valoracions3);

        HashMap<Integer, Usuari> usuarisVeins = new HashMap<>();
        usuarisVeins.put(usuari1.getId(),usuari1);
        usuarisVeins.put(usuari2.getId(),usuari2);
        usuarisVeins.put(usuari3.getId(),usuari3);


        System.out.println("Creació de mockitos realitzada");

        System.out.println("Executant funcions...");

        try {
            SlopeOne slopeOne = new SlopeOne(usuari3,usuarisVeins);
            System.out.println("Predicció de l'item 3 per l'usuari 1 és:");
            System.out.println(slopeOne.prediccio(item1));

        }
        catch (Exception e) {
            Assert.fail("Exception " + e.getMessage());
        }

        System.out.println("TEST 2 FINALITZAT");
    }

    //Aquí aplico el exmeple de Wikipedia de Slope One, que no només funciona per a aquest valors i numero de items
    //i usuaris, sinó que és aplicable a qualsevol situació. Com veiem, els resultats són correctes.


    @AfterClass
    public static void finalDeElsTests () {
        System.out.println("TOTS TESTS FINALITZATS");
    }



    public static void main(String[] args) throws Exception {
        DrvSlopeOneJunit a = new DrvSlopeOneJunit(); 
        a.test1();
        a.test2();
        a.finalDeElsTests();
    }


}