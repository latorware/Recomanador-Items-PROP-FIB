package domini;

import domini.Excepcions.TipusAtributNul;

public  class AtrInt extends Atribut {

    /**
     * Representa la dada de l'atribut.
     */
    private final int atributInt;

    /**
     * Constructora.
     * Inicialitza un AtrInt amb atributInt = num i tipusAtribut = tipus.
     */
    public AtrInt(TipusAtribut tipus, int numero) throws TipusAtributNul
    {
        super(tipus);
        atributInt = numero;
    }

    /**
     * Retorna atributInt.
     * @return atributInt.
     */
    public int getAtributInt() {
        return atributInt;
    }

    /**
     * Retorna atributBool en format String.
     */
    @Override
    public String getDadaEnString() {
        return String.valueOf(atributInt);
    }
}