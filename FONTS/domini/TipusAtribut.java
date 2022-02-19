package domini;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import domini.Excepcions.*;
import utils.Pair;

public class TipusAtribut {

    /**
     * Representa l'identificador de TipusAtribut.
     */
    private final String nom;

    /**
     * Indicador per saber si s'utilitza el TipusAtribut per calcular recomanacions.
     */
    private boolean calculable;

    /**
     * Representa tots els atributs que pertanyen al TipusAtribut.
     */
    private final Set<Atribut> atributs = new HashSet<>();

    /**
     * Valor maxim entre tots els atributs.
     */
    private double max;

    /**
     * Valor minim entre tots els atributs.
     */
    private double min;

    /**
     * Indica de quin tipus de dada son els valors dels atributs.
     */
    private final String tipusDada;

    /**
     * Indicador per saber si els valors max i mix estan inicialitzats.
     */
    private boolean MaxMinInicialitzats = false;

    /**
     * Referencia a l'estrategia per calcular el metode crearAtribut().
     */
    private StrategyTipus strategyTipus;

    /**
     * Constructora.
     * Segons el tipus de tipusDada s'inicialitza una estrategia.
     * Els atributs max i min s'inicialitzen a -1.
     */
    public TipusAtribut(String nom, String tipusDada, boolean calculable) throws Excepcions.IncompatibleTipusAtribut, NomNul, TipusDadaNul {
        if (tipusDada == null) throw new TipusDadaNul (); 
        if (nom == null) throw new NomNul(); 
        if ((tipusDada.equals("Int")) || (tipusDada.equals("Double")) || (tipusDada.equals("String")) || (tipusDada.equals("Bool"))) {
            this.tipusDada = tipusDada;
            switch (tipusDada) {
                case "Int":
                    this.strategyTipus = new StrategyInt();
                    break;
                case "Double":
                    this.strategyTipus = new StrategyDouble();
                    break;
                case "Bool":
                    this.strategyTipus = new StrategyBool();
                    break;
                case "String":
                    this.strategyTipus = new StrategyString();
                    break;
            }
        }
        else {
            throw new IncompatibleTipusAtribut (); 
        }
        this.nom = nom;
        this.calculable = calculable;
        max = -1;
        min = -1;
    }

    /**
     * Afegeix un Atribut = atribut a atributs.
     * Recalcula els valors de min i max en cas de que el tipusDada sigui un Int o Double.
     * @param atribut atribut a afegir.
     */
    public void afegirAtribut(Atribut atribut) throws AtributNul {
        if (atribut == null) throw new AtributNul (); 
        if (this.tipusDada.equals("Int"))
        {   
            int num = ((AtrInt)atribut).getAtributInt();
            if (!MaxMinInicialitzats) {
                max = num;
                min = num; 
                MaxMinInicialitzats = true; 
            }
            else {
                
                if (num > max) max = num;
                else if (num < min) min = num;
            }

        }
        else if (this.tipusDada.equals("Double"))
        {
            double num = ((AtrDouble)atribut).getAtributDouble();
            if (!MaxMinInicialitzats) {
                max = num;
                min = num; 
                MaxMinInicialitzats = true; 
            }
            else {
                
                if (num > max) max = num;
                else if (num < min) min = num;
            }

        }
        atributs.add(atribut); 
    }

    /**
     * Elimina un Atribut = atribut d'atributs.
     * @param atribut atribut a eliminar.
     */
    public void eliminaAtribut(Atribut atribut) throws AtributNul
    {
        if (atribut == null) throw new AtributNul (); 
        if (this.tipusDada.equals("Int"))
        {
            int num = ((AtrInt)atribut).getAtributInt();
            if (num == max)
            {
                max = -1;
                this.atributs.remove(atribut);
                for (Atribut a : atributs) {
                    num = ((AtrInt)a).getAtributInt();
                    if (num > max) max = num;
                }
            }
            if (num == min)
            {
                min = -1;
                this.atributs.remove(atribut);
                boolean primer = true; 
                for (Atribut a : atributs) {
                    num = ((AtrInt)a).getAtributInt();
                    if (primer) {
                        min = num; 
                        primer = false; 
                    }
                    if (num < min) min = num;
                }
            }
            this.atributs.remove(atribut); 
        }
        else if (this.tipusDada.equals("Double"))
        {
            double num = ((AtrDouble)atribut).getAtributDouble();
            if (num == max)
            {
                max = -1;
                this.atributs.remove(atribut);
                for (Atribut a : atributs) {
                    num = ((AtrDouble)a).getAtributDouble();
                    if (num > max) max = num;
                }
            }
            if (num == min)
            {
                min = -1; 
                this.atributs.remove(atribut);
                boolean primer = true; 
                for (Atribut a : atributs) {
                    num = ((AtrDouble)a).getAtributDouble();
                    if (primer) {
                        min = num; 
                        primer = false; 
                    }
                    if (num < min) min = num;
                }
            }
            this.atributs.remove(atribut); 
        }
        else {
            this.atributs.remove(atribut); 
        }
    }

    /**
     * Crea una sublclasse d'Atribut segons l'estrategia inicialitzada.
     * Afegeix la subclasse al set atributsItem.
     * @param paraula valor de la classe Atribut.
     * @param tipusAtributs map de tots els tipusAtributs.
     * @param key etiqueta seleccionada del map tipusAtributs.
     * @param atributsItem set d'Atributs que pertayen a un item.
     */
    public void crearAtribut(String paraula, Map<Pair<Integer, String>, TipusAtribut> tipusAtributs, Pair<Integer, String> key, Set<Atribut> atributsItem) throws ValorAtributNul, AtributNul, TipusAtributNul {
        strategyTipus.crearAtribut(paraula, tipusAtributs, key, atributsItem);
    }

    /**
     * Assigna un valor a calculable.
     */
    public void setCalculable(boolean calculable) {
        this.calculable = calculable;
    }

    /**
     * Retorna calculable
     * @return calculable
     */
    public boolean getCalculable() {
        return calculable;
    }

    /**
     * Retorna nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retorna max.
     * @return max
     */
    public double getMaxValor() {
        return max; 
    }

    /**
     * Retorna min.
     * @return min.
     */
    public double getMinValor() {
        return min; 
    }

    /**
     * Retorna tipusDada.
     * @return tipusDada.
     */
    public String getTipusDada () {
        return tipusDada; 
    }

    /**
     * Retorna atributs.
     * @return atributs.
     */
    public Set<Atribut> getAtributs () {
        return atributs;
    }
}