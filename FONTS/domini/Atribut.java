package domini;


import java.util.*;

import domini.Excepcions.ItemJaTeAquestAtribut;
import domini.Excepcions.ItemNul;
import domini.Excepcions.TipusAtributNul;

public abstract class Atribut{

    /**
     * Conjunt d'items que contenen l'atribut.
     */
    private final Set<Item> items;

    /**
     * Defineix de quin tipus és l'atribut i si és calculable per l'algorisme.
     */
    private final TipusAtribut tipusAtribut;

    /**
     * Crea una instància d'atribut sense items.
     * @param tipus defineix el tipus d'atribut.
     */
    public Atribut(TipusAtribut tipus) throws TipusAtributNul
    {
        if (tipus == null) throw new TipusAtributNul(); 
        tipusAtribut = tipus;
        items = new HashSet<>();
    }

    /**
     * Crea una instància d'atribut amb un item inicial.
     * @param item item que conté l'atribut.
     * @param tipus defineix el tipus d'atribut.
     */
    public Atribut(Item item, TipusAtribut tipus) throws ItemNul, TipusAtributNul
    {
        if (item == null) throw new ItemNul(); 
        if (tipus == null) throw new TipusAtributNul(); 
        tipusAtribut = tipus;
        items = new HashSet<>();
        items.add(item);
    }

    /**
     * Afegeix un item al conjunt d'items que contenen l'atribut.
     * @param item és el nou item que conté l'atribut.
     */
    public void addItem(Item item) throws ItemNul, ItemJaTeAquestAtribut
    {
        if (item == null) throw new ItemNul(); 
        if (this.items.contains(item)) throw new ItemJaTeAquestAtribut(); 
        this.items.add(item);
    }

    /**
     * Retorna el tipus d'atribut.
     * @return tipus d'atribut.
     */
    public TipusAtribut getTipusAtribut()
    {
        return tipusAtribut;
    }

    /**
     * Retorna el conjunt d'items.
     * @return conjunt d'items.
     */
    public Set<Item> getItems()
    {
        return items;
    }

    public abstract String getDadaEnString();
}