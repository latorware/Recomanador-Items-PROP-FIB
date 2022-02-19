package domini;
import java.util.*;


public class SlopeOne {

    /*
    Slope One és un algorisme utilitzat per fer collaborative filtering i consisteix en
    predir la valoració que donaria l'usuari actiu a un item donat, a partir de les valoracions
    fetes per altres usuaris. Els altres usuaris solen ser usuaris pròxims, és a dir, amb
    gustos similars i es treuen del k-means.
     */


    //ATRIBUTS

    /**
     * usuari és l'usuari actiu al qual es vol fer una predicció sobre un item j.
     */
    Usuari usuari;

    /**
     * Els usuaris veins són tots aquells usuaris que es troven en el mateix cluster i per tant comparteixen gustos
     * amb l'usuari actiu. Aquests usuaris es troven amb l'algorisme k-Means.
     */
    HashMap<Integer,Usuari> usuarisVeins;

    //CONSTRUCTORA


    /**
     * Constructora de la classe SlopeOne. Crea una instància de la classe sense calcular cap predicció, a més, guarda
     * l'usuari actiu i els usuaris veins d'aquest.
     * @param usuari és l'usuari actiu al qual volem fer la predicció.
     * @param usuarisVeins és el conjunt d'usuaris que formen part del mateix cluster que e'l 'usuari actiu, inclòs
     *                     ell mateix.
     */
    public SlopeOne(Usuari usuari, HashMap<Integer,Usuari> usuarisVeins) {
        this.usuari = usuari;
        this.usuarisVeins = usuarisVeins;
    }

    //OPERACIONS

    /**
     * Predicció d'un usuari "usuari" sobre un item. Retorna un real que és la puntuació que l'usuari donaria.
     * @param j és l'item del qual volem saber la seva predicció per l'usuari actiu.
     */
    public double prediccio(Item j) {
        double mitjanaValoracions = 0;
        HashMap<Item, Valoracio> itemsValoracions = new HashMap<Item,Valoracio>(usuari.getItemsValoracions());

        //itemsValoracions són tots aquells items que l'usuari actiu ha valorat
        for (HashMap.Entry<Item,Valoracio> entry : itemsValoracions.entrySet()) {
            mitjanaValoracions += entry.getValue().getPuntuacio();
        }

        HashMap<Item,ArrayList<Double>> diferencies = new HashMap<>();
        for (HashMap.Entry<Integer,Usuari> entry : usuarisVeins.entrySet()) {
            if (entry.getValue().getItemsValoracions().containsKey(j) && entry.getValue() != usuari) {
                for (HashMap.Entry<Item,Valoracio> entry2 : entry.getValue().getItemsValoracions().entrySet()) {
                    if (entry2.getKey() != j && usuari.getItemsValoracions().containsKey(entry2.getKey())) {
                        ArrayList<Double> puntuacions = new ArrayList<>();
                        if (!diferencies.containsKey(entry2.getKey())) diferencies.put(entry2.getKey(),puntuacions);
                        double diferencia = entry.getValue().getItemsValoracions().get(j).getPuntuacio() - entry.getValue().getItemsValoracions().get(entry2.getKey()).getPuntuacio();
                        diferencies.get(entry2.getKey()).add(diferencia);
                    }
                }
            }
        }

        double prediccio = 0;
        int numDiferencies = 0;
        for (HashMap.Entry<Item,ArrayList<Double>> entry : diferencies.entrySet()) {
            double suma = 0;
            for (int i = 0; i < entry.getValue().size(); ++i) {
                suma += entry.getValue().get(i);
            }
            suma /= entry.getValue().size();
            suma = (suma + usuari.getItemsValoracions().get(entry.getKey()).getPuntuacio())*entry.getValue().size();
            prediccio += suma;
            numDiferencies += entry.getValue().size();
        }
        if (numDiferencies != 0) {
            prediccio /= numDiferencies;
        }
        else prediccio = mitjanaValoracions /= itemsValoracions.size();

        return prediccio;
    }

}
