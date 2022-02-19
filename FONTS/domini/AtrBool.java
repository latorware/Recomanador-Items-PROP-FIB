package domini;

import domini.Excepcions.TipusAtributNul;

public class AtrBool extends Atribut {

    /**
     * Representa la dada de l'atribut.
     */
    private final boolean atributBool;

    /**
     * Constructora.
     * Inicialitza un AtrBool amb atributBool = num i tipusAtribut = tipus.
     */
    public AtrBool(TipusAtribut tipus, boolean bool) throws TipusAtributNul
    {
        super(tipus);
        atributBool = bool;
    }

    /**
     * Retorna atributBool.
     * @return atributBool.
     */
    public boolean getAtributBool() {
        return atributBool;
    }

    /**
     * Retorna atributBool en format String.
     */
    @Override
    public String getDadaEnString() {
        return String.valueOf(atributBool);
    }
}