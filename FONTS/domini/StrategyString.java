package domini;

import utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StrategyString implements StrategyTipus {

    /**
     * En cas de que paraula no contingui ";", crea un AtrString amb atributString = paraula.
     * En cas de que paraula contingui ";", crea diferents AtrString amb cada extracte de fer paraula.split(";").
     * Associa l'AtrString amb el TipusAtribut obtingut de tipusAtribut.get(key)
     * Afegeix el nou Atribut al Set d'atributs del TipusAtribut.
     * Afegeix el nou Atribut a atributsItem.
     */
    @Override
    public void crearAtribut(String paraula, Map<Pair<Integer, String>, TipusAtribut> tipusAtribut, Pair<Integer, String> key, Set<Atribut> atributsItem) throws Excepcions.AtributNul, Excepcions.TipusAtributNul, Excepcions.ValorAtributNul {
        if (paraula.contains(";")) {
            ArrayList<String> dades = new ArrayList<String>(List.of(paraula.split(";")));
            for (String dada : dades) {
                AtrString nou = new AtrString(tipusAtribut.get(key), dada);
                tipusAtribut.get(key).afegirAtribut(nou);
                atributsItem.add(nou);
            }
        }
        else {
            AtrString nou = new AtrString(tipusAtribut.get(key), paraula);
            tipusAtribut.get(key).afegirAtribut(nou);
            atributsItem.add(nou);
        }
    }
}
