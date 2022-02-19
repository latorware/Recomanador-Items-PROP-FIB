package domini.Drivers;

import domini.*;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class DrvAvaluacioRecomanacioCOLFiltering {

    private static HashMap<Integer, Usuari> usuaris = new HashMap<>();
    private static HashMap<Integer, Item> items = new HashMap<>();


    public static void main(String[] args) throws IOException, Excepcions.InvalidValoracioException,
            Excepcions.UnexistingAtributsException, Excepcions.InvalidPuntuacioException,
            Excepcions.UsuariAAfegirEsAnalitzat, Excepcions.UsuariNul, Excepcions.UsuariJaAfegit,
            Excepcions.SetUsuarisNul, Excepcions.ValoracioNul, Excepcions.ItemNul, Excepcions.MapValoracionsNul,
            Excepcions.SetAtributsNul, Excepcions.MassaClustersExcepcio, Excepcions.CapClusterExcepcio {

        System.out.println("Introdueix el nom del directori del tipus d'item: 'movies.sample' per exemple");
        Scanner input = new Scanner(System.in);
        String tipusItem =  input.nextLine();
        System.out.println("Introdueix el nom del directori del numero de dades: '250' per exemple");
        Scanner input2 = new Scanner(System.in);
        String numDades =  input2.nextLine();

        ArrayList<ArrayList<String>> dades = llegeixDades("/data/" + tipusItem + "/" + numDades + "/ratings.test.known.csv");
        ArrayList<ArrayList<String>> unknown = llegeixDades("/data/" + tipusItem + "/" + numDades + "/ratings.test.unknown.csv");
        Integer userId;
        Integer itemId;
        double rating;

        int posUser = 0;
        int posItem = 0;
        int posRating = 0;

        for (int i = 0; i < 3; ++i) {
            if (Objects.equals(unknown.get(0).get(i), "itemId")) posItem = i;
            if (Objects.equals(unknown.get(0).get(i), "rating")) posRating = i;
            if (Objects.equals(unknown.get(0).get(i), "userId")) posUser = i;
        }

        for (int i = 1; i < dades.size(); ++i) {
            userId = Integer.parseInt(dades.get(i).get(posUser));
            itemId = Integer.parseInt(dades.get(i).get(posItem));
            rating = Double.parseDouble(dades.get(i).get(posRating));
            if (!usuaris.containsKey(userId)) {
                Usuari usuari = new Usuari(userId);
                usuaris.put(userId, usuari);
            }
            if (!items.containsKey(itemId)) {
                HashMap<Usuari, Valoracio> valoracions = new HashMap<>();
                HashSet<Atribut> atributs = new HashSet<>();
                Item item = new Item(itemId,valoracions,atributs);
                items.put(itemId,item);
            }


            Valoracio valoracio = new Valoracio(usuaris.get(userId), rating, null, items.get(itemId));
            if (!items.get(itemId).getValoracions().containsKey(usuaris.get(userId))) items.get(itemId).addValoracio(valoracio,usuaris.get(userId));
            if (!usuaris.get(userId).getItemsValoracions().containsKey(valoracio.getItem())) usuaris.get(userId).addItemValoracio(valoracio);
        }



        double mitjanaDCG = 0;
        double mitjanaIDCG = 0;
        double mitjanaNDCG = 0;

        System.out.println("Introdueix un nombre k per tal de fer k clusters:");
        Scanner input3 = new Scanner(System.in);
        int k =  input3.nextInt();


        COLFiltering colFiltering = new COLFiltering(usuaris,k);

        for (int i = 1; i < unknown.size(); i+=10) {
            userId = Integer.parseInt(unknown.get(i).get(posUser));
            HashMap<Item, Double> lt = new HashMap<>();
            for (int j = i; j < i+10; ++j) {
                itemId = Integer.parseInt(unknown.get(j).get(posItem));
                rating = Double.parseDouble(unknown.get(j).get(posRating));
                lt.put(items.get(itemId), rating);
            }

            double DCG = 0;
            double IDCG = 0;
            double NDCG = 0;

            for (int execucions = 0; execucions < 20; ++execucions) {
                ArrayList<Item> lr = new ArrayList<>(colFiltering.recomanacions(usuaris.get(userId)));
                while (lr.size() > 10) {
                    lr.remove(lr.size() - 1);
                }

                DCG dcg = new DCG(lt, lr);
                dcg.calculaDGC();
                DCG += dcg.getDCG();
                IDCG += dcg.getIDCG();
                NDCG += dcg.getNDCG();
            }

            DCG /= 20.0;
            IDCG /= 20.0;
            NDCG /= 20.0;

            System.out.println("-------------------------------------------------");
            System.out.println("DCG per l'usuari " + userId + " és:");
            System.out.println("DCG: " + DCG);
            System.out.println("IDCG: " + IDCG);
            System.out.println("NDCG: " + NDCG);
            System.out.println("-------------------------------------------------");
            System.out.println();

            mitjanaDCG += DCG;
            mitjanaIDCG += IDCG;
            mitjanaNDCG += NDCG;
        }

        System.out.println("Mitjana de DCG per tots els usuaris és:");
        System.out.println("DCG: " + mitjanaDCG/(unknown.size()/10));
        System.out.println("IDCG: " + mitjanaIDCG/(unknown.size()/10));
        System.out.println("NDCG: " + mitjanaNDCG/(unknown.size()/10));


    }


    private static ArrayList<ArrayList<String>> llegeixDades(String dir) throws IOException {
        String direccioArxiu = System.getProperty("user.dir") + dir;
        FileInputStream fileInputStream = new FileInputStream(direccioArxiu);
        DataInputStream items = new DataInputStream(fileInputStream);

        String line;
        ArrayList<ArrayList<String>> lines = new ArrayList<>();
        while ((line = items.readLine()) != null) {
            StringBuilder camp = new StringBuilder();
            boolean cometes = true;
            ArrayList<String> lin = new ArrayList<>();

            for (char c : line.toCharArray()) {
                if (c == '"' && cometes) cometes = false;
                else if (c == '"' && !cometes) cometes = true;
                else if (cometes && c == ',') {
                    lin.add(camp.toString());
                    StringBuilder camp2 = new StringBuilder();
                    camp = camp2;
                }
                else camp.append(c);
            }
            lin.add(camp.toString());
            lines.add(lin);
        }
        return lines;
    }

}
