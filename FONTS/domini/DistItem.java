package domini;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import domini.Excepcions.ItemNoAfegit;
import domini.Excepcions.ItemNul;
import domini.Excepcions.ItemsIguals;
import domini.Excepcions.SetItemsNul;

public class DistItem extends Distancia {
    
    //ATRIBUTS


    /**
     * Colecció que per a cada item te organitzats tots els seus atributs segons el tipus d'atribut que siguin aquests
     */
    protected HashMap<Item, HashMap <TipusAtribut, HashSet<Atribut> > > tipusAtributsItems; //protected ja que ItemDistItem accedira

    /**
     * Colecció que per a cada item te enmagatzemades les distancies a tots els altres items
     */
    private HashMap<Item, HashMap<Item, Double> > distancies; 



    //CONSTRUCTORES

    /**
     * Constructora buida de la classe
     */
    public DistItem () {
        super(); 
        tipusAtributsItems = new HashMap<Item, HashMap <TipusAtribut, HashSet<Atribut> > > ();
        distancies = new HashMap<Item, HashMap<Item, Double> > (); 
    }

    /**
     * Crea una instancia de la classe, on principalment els items amb els que treballarà aquesta son passats com a paràmetre
     * @param items conjunt d'items amb els quals treballarà la instància de la classe
     * @throws ItemNul
     */
    public DistItem (Set<Item> items) throws SetItemsNul, ItemNul {
        super(); 
        if (items == null) throw new SetItemsNul(); 
        distancies = new HashMap<Item, HashMap<Item, Double> > (); 
        tipusAtributsItems = new HashMap<Item, HashMap <TipusAtribut, HashSet<Atribut> > > ();
        for (Item itemCurrent : items) {
            afegirItem(itemCurrent);
        }
        //imprimirtipusAtributsItems();

    }


    //OPERACIONS

    /**
     * funcio que, donat un item, organitza els atributs d'aquest segos el TipusAtribut, i afegeix això a la colecció tipusAtributsItems
     * @param item es l'item amb el que processara els atributs
     */
    private void procesaAtrAnalitzats(Item item) {
        //REQUISIT: a d'existir key item a tipusAtributsItems (i supmap inicialitzat)
        for (Atribut atribut: item.getAtributs()) {
            if (tipusAtributsItems.get(item).containsKey(atribut.getTipusAtribut())) { //ja tenim el tipus atribut
                tipusAtributsItems.get(item).get(atribut.getTipusAtribut()).add(atribut); 
            }
            else { //sinicialitza set
                HashSet<Atribut> set = new HashSet<Atribut>();
                set.add(atribut); 
                tipusAtributsItems.get(item).put(atribut.getTipusAtribut(), set);
            }
        }
    }


    /**
     * funcio que, afegeix un item més a la classe, i per tant es calcularàn les distàncies també cap a aquest
     * @param item es l'item que s'afegirà
     */
    public void afegirItem (Item item) throws ItemNul {
        //REQUISIT: Tots els altres items ja estan a tipusAtributsItems, i les seves distancies a distancies
        if (item == null) throw new ItemNul(); 
        nouItemProcesa(item);
        nouItemAfegeixDistances(item);
    }


    /**
     * funcio que, es l'encarregada d'afegir un item a la coleccio tipusAtributsItems, quan aquest ítem es nou
     * @param item es litem que s'afegira
     */
    private void nouItemProcesa (Item item) {
        HashMap<TipusAtribut, HashSet<Atribut> > map = new  HashMap<TipusAtribut, HashSet<Atribut> > (); 
        tipusAtributsItems.put(item, map); 
        procesaAtrAnalitzats(item);
    }


    /**
     * funcio que, és l'encarregada de calcular les distancies d'un item cap a tots els altres, quan aquest ítem és nou
     * @param item és l'ítem nou amb el que es calcularan les distancies
     */
    private void nouItemAfegeixDistances (Item item) {
        HashMap<Item, Double > map = new  HashMap<Item, Double > ();
        distancies.put(item, map); 
        calculaDistanciesDeItem(item);
    }


    /**
     * funcio que, és l'encarregada d'eliminar un ítem de la classe, i per tant no es calcularan les distancies d'aquest cap a altres items
     * @param item és l'ítem que s'eliminarà
     */
    public void eliminarItem (Item item) throws ItemNoAfegit {
        if (!tipusAtributsItems.containsKey(item)) throw new ItemNoAfegit(); 
        tipusAtributsItems.remove(item); 
        for (Item itemCurrent : distancies.keySet()) {
            if (itemCurrent != item) {
                distancies.get(itemCurrent).remove(item); 
            }
        }
        distancies.remove(item); 
    }



    /**
     * funcio que calcula totes les distancies d'un item cap a tots els altres, i afegeix aquestes a la colecció distancies
     * @param item és l'ítem en el que es calcularan les distancies
     */
    private void calculaDistanciesDeItem (Item item) {
        //REQUISIT: a d'existir key item a distancies (i supmap inicialitzat). I tambe, a de estar procesat item a tipusatributsitem
        for (Item itemCurrent : distancies.keySet()) {
            if (itemCurrent != item) {
                Double distanciaCurrent = calculaDistanciaItemItem(item, itemCurrent); 
                distancies.get(itemCurrent).put(item, distanciaCurrent); 
                distancies.get(item).put(itemCurrent, distanciaCurrent); 
            }
        }


    }


    /**
     * funcio que calcula la distancia entre dos ítems
     * @param item1 és un ítem
     * @param item2 és l'altre
     * @return
     */
    private Double calculaDistanciaItemItem (Item item1, Item item2) {
        //REQUISIT: han d'existir els dos items a tipusAtributsItems
        double aRetornar = new ItemDistItem(item1, item2, this).getDistancia(); 
        //System.out.println("Distancia entre item " + item1.getID() + "i item " + item2.getID() + " = " + aRetornar);
        return (aRetornar); 

    }



    //GETTERS


    /**
     * funció que informa de totes les distancies que van des d'un determinat ítem cap a tots els altres
     * @param item és l'ítem a partir del qual es calcularan les distancies cap als altres ítems
     * @return retorna un map on les claus son els ids dels ítems i els valors son la distància entre l'ítem del paràmetre i aquests. 
     */
    public HashMap<Item, Double> getDistanciesDeItem (Item item) throws ItemNoAfegit {
        if (!tipusAtributsItems.containsKey(item)) throw new ItemNoAfegit(); 
        return distancies.get(item); 
    }

    /**
     * funció que informa de totes les distancies de tots els ítems cap a tots els items. 
     * @return retorna una colecció, en que per a cada ítem es retorna la distància cap a cada un dels altres items. La clau del map extern, és un id d'un item, i la del intern, també. El valor del map intern, per tant, és la distància entre aquets dos ítems
     */
    public HashMap<Item, HashMap<Item, Double> > getTotesDistancies () {

        return distancies; 
    }

    /**
     * funció que informa de la distància entre dos items
     * @param item1 és un ítem
     * @param item2 és l'altre
     * @return Retorna la distancia entre aquests dos items 
     */
    public Double getDistanciaItemItem (Item item1, Item item2) throws ItemNoAfegit, ItemsIguals {
        if (item1 == item2) throw new ItemsIguals(); 
        if ((!tipusAtributsItems.containsKey(item1)) || (!tipusAtributsItems.containsKey(item2))) throw new ItemNoAfegit(); 
        return distancies.get(item1).get(item2); 
    }


    public void imprimirtipusAtributsItems () {

        for (HashMap.Entry<Item, HashMap <TipusAtribut, HashSet<Atribut> > > entry : tipusAtributsItems.entrySet()) {
            System.out.println("TIPUS ATRIBUTS DE ITEM: " + entry.getKey().getID());
            for (HashMap.Entry<TipusAtribut, HashSet<Atribut> > entry2 : entry.getValue().entrySet()) {
                System.out.println("    TIPUS ATRIBUT: " + entry2.getKey().getNom() + "   TIPUS: " + entry2.getKey().getTipusDada() + "    Atributs d'aquest: ");
                for (Atribut actual : entry2.getValue()) {
                    System.out.println("          ATRIBUT: " + actual.getDadaEnString());

                }
            }
        }


    }

}