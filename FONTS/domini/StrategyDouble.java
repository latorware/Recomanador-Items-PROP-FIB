package domini;

import utils.Pair;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class StrategyDouble implements StrategyTipus {

    /**
     * Retorna si el string paraula conte un valor negatiu.
     * @return false en cas de que no representi un valor negatiu, true en cas contrari.
     */
    public static boolean Negative(String paraula) {
        if (!Objects.equals(paraula, "")) {
            return paraula.charAt(0) == '-';
        }
        return false;
    }

    /**
     * Crea un AtrDouble amb atributDouble = paraula.
     * Associa l'AtrDouble amb el TipusAtribut obtingut de tipusAtribut.get(key)
     * Afegeix el nou Atribut al Set d'atributs del TipusAtribut.
     * Afegeix el nou Atribut a atributsItem.
     */
    @Override
    public void crearAtribut(String paraula, Map<Pair<Integer, String>, TipusAtribut> tipusAtribut, Pair<Integer, String> key, Set<Atribut> atributsItem) throws Excepcions.AtributNul, Excepcions.TipusAtributNul {
        double num = 0.0;
        if (!Negative(paraula))
            try {
                num = Double.parseDouble(paraula);
                AtrDouble nou = new AtrDouble(tipusAtribut.get(key), num);
                tipusAtribut.get(key).afegirAtribut(nou);
                atributsItem.add(nou);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
    }
}
