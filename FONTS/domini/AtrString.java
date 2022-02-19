package domini;

import domini.Excepcions.TipusAtributNul;
import domini.Excepcions.ValorAtributNul;

public  class AtrString extends Atribut {

    /**
     * Representa la dada de l'atribut.
     */
    private final String atributString;

    /**
     * Constructora.
     * Inicialitza un AtrString amb atributString = num i tipusAtribut = tipus.
     */
    public AtrString(TipusAtribut tipus, String text) throws TipusAtributNul, ValorAtributNul
    {
        super(tipus);
        if (text == null) throw new ValorAtributNul(); 
        atributString = text;
    }

    /**
     * Retorna atributString.
     * @return atributString.
     */
    public String getAtributString() {
        return atributString;
    }

    /**
     * Retorna atributBool en format String.
     */
    @Override
    public String getDadaEnString() {
        return getAtributString();
    }
}