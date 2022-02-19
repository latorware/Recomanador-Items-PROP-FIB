package domini;

import utils.Pair;

import java.util.Comparator;

public class PairComparator implements Comparator<Pair<Integer, String>> {

    @Override
    public int compare(Pair<Integer, String> o1, Pair<Integer, String> o2) {
        return o1.first().compareTo(o2.first());
    }
}