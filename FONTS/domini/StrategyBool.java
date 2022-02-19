package domini;

import utils.Pair;

import java.util.Map;
import java.util.Set;

public class StrategyBool implements StrategyTipus {

    /**
     * Crea un AtrBool amb atributBool = paraula.
     * Associa l'AtrBool amb el TipusAtribut obtingut de tipusAtribut.get(key)
     * Afegeix el nou Atribut al Set d'atributs del TipusAtribut.
     * Afegeix el nou Atribut a atributsItem.
     */
    @Override
    public void crearAtribut(String paraula, Map<Pair<Integer, String>, TipusAtribut> tipusAtribut, Pair<Integer, String> key, Set<Atribut> atributsItem) throws Excepcions.AtributNul, Excepcions.TipusAtributNul {
        if (paraula.equalsIgnoreCase("true") || paraula.equalsIgnoreCase("false")) {
            AtrBool nou = new AtrBool(tipusAtribut.get(key), Boolean.valueOf(paraula));
            tipusAtribut.get(key).afegirAtribut(nou);//Afegir Atribut al Set<Atribut> del TIPUS
            atributsItem.add(nou);
        }
    }
}
