package domini.Drivers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Scanner;

import domini.Item;
import domini.UActiu;
import domini.Usuari;
import domini.Valoracio;

public class DrvUsuari {

    public static Usuari testConstructorUsuari (Usuari usuari, Scanner scan) {
        if (usuari != null) {
            System.out.println("ERROR. Ja hi ha un usuari creat. Intenta modificar aquest, o elimina'l primer. "); 
            return usuari; 
        }
        else {
            try {
                System.out.println("Introdueix l'íd que tindrà l'usuari");
                int id = scan.nextInt(); 
                usuari = new Usuari(id); 
                System.out.println("FET. ");
                return usuari; 
            }
            catch   (Exception a) {
                System.out.println("ERROR"); 
                System.out.println(a.getMessage());
                return null; 
            }
        }
    }

    public static Usuari testConstructorUActiu (Usuari usuari, Scanner scan) {
        if (usuari != null) {
            System.out.println("ERROR. Ja hi ha un usuari creat. Intenta modificar aquest, o elimina'l primer. "); 
            return usuari;
        }
        else {
            try {
                System.out.println("Introdueix l'íd que tindrà l'usuari");
                int id = scan.nextInt(); 
                System.out.println("Introdueix el nom que tindrà l'usuari");  
                String nom = scan.next(); 
                System.out.println("Introdueix la password que tindrà l'usuari");  
                String password = scan.next(); 
                usuari = new UActiu(id, nom, password);  
                System.out.println("FET. ");
                return usuari; 
            }
            catch   (Exception a) {
                System.out.println("ERROR"); 
                System.out.println(a.getMessage());
                return null;
            }
        }

    }

    public static Usuari testEliminaUsuari (Usuari usuari) {
        if (usuari == null) {
            System.out.println("ERROR. No hi ha cap usuari creat. "); 
            return null; 
        }
        else {
            usuari = null; 
            System.out.println("FET.");
            return null; 
        }
    }

    public static void testIsActiu(Usuari usuari) {
        if (usuari == null) {
            System.out.println("ERROR. No hi ha cap usuari creat. Crea primer un usuari. "); 
        }
        else {
            try {
                boolean resultat = usuari.isActiu(); 
                System.out.println(resultat);
                System.out.println("FET.");
            }
            catch (Exception a) {
                System.out.println("ERROR"); 
                System.out.println(a.getMessage());
            }
        }
    }

    public static void testGetId (Usuari usuari) {
        if (usuari == null) {
            System.out.println("ERROR. No hi ha cap usuari creat. Crea primer un usuari. "); 
        }
        else {
            try {
                int id = usuari.getId();  
                System.out.println("id: " + id);
                System.out.println("FET.");
            }
            catch (Exception a) {
                System.out.println("ERROR"); 
                System.out.println(a.getMessage());
            }
        }
    }

    public static void testGetItemsValoracions (Usuari usuari) {
        if (usuari == null) {
            System.out.println("ERROR. No hi ha cap usuari creat. Crea primer un usuari. "); 
        }
        else {
            try {
                HashMap<Item, Valoracio> itemsValoracions = usuari.getItemsValoracions(); 
                System.out.println("PASSANT PER STUB VALORACIO"); 
                System.out.println(itemsValoracions.toString()); 
                System.out.println("FET.");
            }
            catch (Exception a) {
                System.out.println("ERROR"); 
                System.out.println(a.getMessage());
            }
        }
    }

    public static void testAddItemValoracio (Usuari usuari, Item item) {
        if (usuari == null) {
            System.out.println("ERROR. No hi ha cap usuari creat. Crea primer un usuari. "); 
        }
        else {
            try {
                System.out.println("CRENT MOCKITOS"); 
                Valoracio valoracio1 = mock(Valoracio.class); 
        
                when(valoracio1.getItem()).thenReturn(item); 
                when(valoracio1.toString()).thenReturn("valoracio1"); 
                System.out.println("CREACIO DE MOCKITOS COMPLETADA"); 
         
                usuari.addItemValoracio(valoracio1);
                System.out.println("FET.");
            }
            catch (Exception a) {
                System.out.println("ERROR"); 
                System.out.println(a.getMessage());
            }
        }
    }

    public static void testGetNom (Usuari usuari) {
        if (usuari == null) {
            System.out.println("ERROR. No hi ha cap usuari creat. Crea primer un usuari. "); 
        }
        else if (!usuari.isActiu()) {
            System.out.println("ERROR. Un usuari que no es actiu no té nom "); 
        }
        else {
            try {
                String nom = ((UActiu) usuari).getNom(); 
                System.out.println("nom: " + nom);
                System.out.println("FET.");
            }
            catch (Exception a) {
                System.out.println("ERROR"); 
                System.out.println(a.getMessage());
            }
        }
    }

    public static void testGetContrasenya (Usuari usuari) {
        if (usuari == null) {
            System.out.println("ERROR. No hi ha cap usuari creat. Crea primer un usuari. "); 
        }
        else if (!usuari.isActiu()) {
            System.out.println("ERROR. Un usuari que no es actiu no té contrassenya. "); 
        }
        else {
            try {
                String contrassenya = ((UActiu) usuari).getContrasenya(); 
                System.out.println("contrassenya: " + contrassenya);
                System.out.println("FET.");
            }
            catch (Exception a) {
                System.out.println("ERROR"); 
                System.out.println(a.getMessage());
            }
        }
    }

    public static void testCanviaPassword (Usuari usuari, Scanner scan) {
        if (usuari == null) {
            System.out.println("ERROR. No hi ha cap usuari creat. Crea primer un usuari. "); 
        }
        else if (!usuari.isActiu()) {
            System.out.println("ERROR. Un usuari que no es actiu no té contrassenya. "); 
        }
        else {
            try {
                System.out.println("Introdueix la password que tenies fins ara: ");  
                String passwordActual = scan.next(); 
                System.out.println("Introdueix la nova password: ");  
                String passwordNova = scan.next(); 
                ((UActiu) usuari).canviaPassword(passwordActual, passwordNova); 
                System.out.println("FET.");
            }
            catch (Exception a) {
                System.out.println("ERROR"); 
                System.out.println(a.getMessage());
            }
        }
    }

    public static void mostraopcions () {
        System.out.println("Escull una opcio:"); 
        System.out.println("1: torna a mostrar el menu");
        System.out.println("2: test Constructor Usuari (usuari no actiu)");
        System.out.println("3: test Constructor U Actiu");
        System.out.println("4: test is actiu");
        System.out.println("5: ELIMINA INSTANCIA usuari");
        System.out.println("6: test get id");
        System.out.println("7: test getItems valoracions");
        System.out.println("8: test add item valoracio (sempre sintentara afegir una valoracio pertaneixent a un mateix item)");
        System.out.println("9: test get nom");
        System.out.println("10: test get contrasenya");
        System.out.println("11: test canvia password");
        System.out.println("0: Sortir");
    }


    public static void main (String [] args){


        Usuari usuari = null; 
        Item item1 = mock(Item.class); 
        when(item1.toString()).thenReturn("item1"); 

        Scanner scan = new Scanner(System.in); 
        mostraopcions();
        int option = scan.nextInt(); 

        

        while (option != 0) {
            switch (option) {
                case 1:
                    mostraopcions();
                    break;

                case 2: 
                    usuari = testConstructorUsuari(usuari, scan);
                    break; 

                case 3: 
                    usuari = testConstructorUActiu (usuari, scan); 
                    break; 

                case 4: 
                    testIsActiu(usuari); 
                    break; 

                case 5: 

                    usuari = testEliminaUsuari (usuari); 
                    break; 

                case 6: 
                    testGetId (usuari); 
                    break; 

                case 7: 
                    testGetItemsValoracions (usuari); 
                    break; 

                case 8: 
                    testAddItemValoracio (usuari, item1); 
                    break; 

                case 9: 
                    testGetNom (usuari); 
                    break; 

                case 10: 
                    testGetContrasenya (usuari); 
                    break; 

                case 11: 
                    testCanviaPassword (usuari, scan); 
                    break; 
            }

            System.out.println("Tria una altre opció");
            option = scan.nextInt();
        }


        System.out.println("Fi del test");


    } 


    
}