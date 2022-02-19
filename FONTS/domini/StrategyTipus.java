package domini;

import utils.Pair;

import java.util.Map;
import java.util.Set;

public interface StrategyTipus {

    /**
     * Crea un Atribut amb dadaAtribut = paraula.
     * Associa l'Atribut amb el TipusAtribut obtingut de tipusAtribut.get(key)
     * Afegeix el nou Atribut al Set d'atributs del TipusAtribut.
     * Afegeix el nou Atribut a atributsItem.
     */
    default void crearAtribut(String paraula, Map<Pair<Integer, String>, TipusAtribut> tipusAtribut, Pair<Integer, String> key, Set<Atribut> atributsItem) throws Excepcions.AtributNul, Excepcions.TipusAtributNul, Excepcions.ValorAtributNul {
    }

}
