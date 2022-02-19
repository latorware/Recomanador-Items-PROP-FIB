package domini;

import domini.Excepcions.TipusAtributNul;

public  class AtrDouble extends Atribut {

    /**
     * Representa la dada de l'atribut.
     */
    private final double atributDouble;

    /**
     * Constructora.
     * Inicialitza un AtrDouble amb atributDouble = num i tipusAtribut = tipus.
     */
    public AtrDouble(TipusAtribut tipus, double numero) throws TipusAtributNul
    {
        super(tipus);
        atributDouble = numero;
    }

    /**
     * Retorna atributDouble.
     * @return atributDouble.
     */
    public double getAtributDouble() {
        return atributDouble;
    }

    /**
     * Retorna atributDouble en format String.
     */
    @Override
    public String getDadaEnString() {
        return String.valueOf(atributDouble);
    }
}