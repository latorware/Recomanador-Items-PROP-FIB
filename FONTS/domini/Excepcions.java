/**Excepcions.java
 * */
package domini;

public class Excepcions{

    /**
     * Invalid Title Exception
     * L'entrada del títol no és correcta (null, blank or empty).
    * */
    public static class InvalidTitleException extends Exception{
        public InvalidTitleException(){
            super("ERROR_INVALID_TITLE");
        }
    }

    public static class UnexistingAtributsException extends Exception{
        public UnexistingAtributsException(){
            super("ERROR_NO_ATRIBUTS_PASSED");
        }
    }

    public static class InvalidPuntuacioException extends Exception{
        public InvalidPuntuacioException(){
            super("ERROR_INVALID_PUNTUACIO");
        }
    }

    public static class NotAutorException extends Exception{
        public NotAutorException(){
            super("ERROR_NOT_AUTOR");
        }
    }

    public static class InvalidValoracioException extends Exception{
        public InvalidValoracioException(){
            super("ERROR_ITEM_JA_VALORAT_X_USUARI");
        }
    }
    
    public static class InsuficientsValoracionsExcepcio extends Exception{
        public InsuficientsValoracionsExcepcio(){
            super("ERROR_INSUFICIENTS_VALORACIONS_PER_CREAR_RECOMANACIONS");
        }
    }
    
    public static class InsuficientsItemsExcepcio extends Exception{
        public InsuficientsItemsExcepcio(){
            super("ERROR_INSUFICIENTS_ITEMS_X_CREAR_RECOMANACIONS");
        }
    }
    
    public static class InsuficientsItemsNoValoratsExcepcio extends Exception{
        public InsuficientsItemsNoValoratsExcepcio(){
            super("ERROR_USUARI_JA_VALORAT_TOTS_ELS_ITEMS_X_CREAR_RECOMANACIONS");
        }
    }
    

    public static class InvalidAutorInItem extends Exception {
        public InvalidAutorInItem () {
            super("ERROR_AUTOR_NO_HA_VALORAT_ITEM");
        }
    }

    public static class IncorrectPassword_CANTCHANGE extends Exception {
        public IncorrectPassword_CANTCHANGE () {
            super("ERROR_CONTRASENYA_INCORRECTA. PER CANVIAR LA CONTRASSENYA, NECESSITES PRIMER INTRODUIR L'ACTUAL. "); 
        }
    }

    public static class IncompatibleTipusAtribut extends Exception {
        public IncompatibleTipusAtribut () {
            super("ERROR_TIPUS_ATRIBUT_INCOMPATIBLE"); 
        }
    }

    public static class UsuariNul extends Exception {
        public UsuariNul () {
            super("ERROR_USUARI_NUL"); 
        }
    }

    public static class SetUsuarisNul extends Exception {
        public SetUsuarisNul () {
            super("ERROR_SET_USUARIS_NUL"); 
        }
    }

    public static class SetItemsNul extends Exception {
        public SetItemsNul () {
            super("ERROR_SET_ITEMS_NUL"); 
        }
    }

    public static class ItemNul extends Exception {
        public ItemNul () {
            super("ERROR_ITEM_NUL"); 
        }
    }

    public static class ItemNoAfegit extends Exception {
        public ItemNoAfegit () {
            super("ERROR_ITEM_NO_AFEGIT"); 
        }
    }

    public static class UsuariJaAfegit extends Exception {
        public UsuariJaAfegit () {
            super("ERROR_USUARI_JA_AFEGIT"); 
        }
    }

    public static class UsuariNoAfegit extends Exception {
        public UsuariNoAfegit () {
            super("ERROR_USUARI_NO_AFEGIT"); 
        }
    }

    public static class UsuariAAfegirEsAnalitzat extends Exception {
        public UsuariAAfegirEsAnalitzat () {
            super("ERROR_USUARI_QUE_ES_VOL_AFEGIR_JA_ES_EL_ANALITZAT"); 
        }
    }

    public static class UsuariAEliminarEsAnalitzat extends Exception {
        public UsuariAEliminarEsAnalitzat () {
            super("ERROR_NO_ES_POT_ELIMINAR_EL_USUARI_ANALITZAT"); 
        }
    }

    public static class ItemsIguals extends Exception {
        public ItemsIguals () {
            super("ERROR_ITEMS_IGUALS"); 
        }
    }


    public static class MapValoracionsNul extends Exception {
        public MapValoracionsNul () {
            super("ERROR_MAP_VALORACIONS_NUL"); 
        }
    }

    public static class SetAtributsNul extends Exception {
        public SetAtributsNul () {
            super("ERROR_SET_ATRIBUTS_NUL"); 
        }
    }

    public static class ValoracioNul extends Exception {
        public ValoracioNul () {
            super("ERROR_VALORACIO_NUL"); 
        }
    }

    public static class PasswordVellaNul extends Exception {
        public PasswordVellaNul () {
            super("ERROR_PASSWORDVELLA_NUL"); 
        }
    }

    public static class PasswordNovaNul extends Exception {
        public PasswordNovaNul () {
            super("ERROR_PASSWORD_NOVA_NUL"); 
        }
    }

    public static class NomNul extends Exception {
        public NomNul () {
            super("ERROR_NOM_NUL"); 
        }
    }

    public static class PasswordNul extends Exception {
        public PasswordNul () {
            super("ERROR_PASSWORD_NUL"); 
        }
    }

    public static class TipusAtributNul extends Exception {
        public TipusAtributNul () {
            super ("ERROR_TIPUS_ATRIBUT_NUL"); 
        }
    }

    public static class ValorAtributNul extends Exception {
        public ValorAtributNul () {
            super("ERROR_VALOR_ATRIBUT_NUL"); 
        }
    }

    public static class TipusDadaNul extends Exception {
        public TipusDadaNul () {
            super("ERRROR_TIPUS_DADA_NUL");
        }
    }

    public static class AtributNul extends Exception {
        public AtributNul () {
            super("ERROR_ATRIBUT_NUL");
        }
    }

    public static class ItemJaTeAquestAtribut extends Exception {
        public ItemJaTeAquestAtribut () {
            super("ERROR_ITEM_JA_TE_AQUEST_ATRIBUT"); 
        }
    }

    public static class ItemNoTeAquestAtribut extends Exception {
        public ItemNoTeAquestAtribut () {
            super("ERROR_ITEM_NO_TE_AQUEST_ATRIBUT"); 
        }
    }

    public static class MassaClustersExcepcio extends Exception {
        public MassaClustersExcepcio () {
            super("HI_HA_MES_CLUSTERS_QUE_USUARIS");
        }
    }
    public static class CapClusterExcepcio extends Exception {
        public CapClusterExcepcio () {
            super("NO_HI_HA_CLUSTER");
        }
    }

    public static class SaltDeLiniaExcepcio extends Exception {
        public SaltDeLiniaExcepcio () {
            super("SALT_DE_LINIA: Comprova que a la segona linia de l'arxiu csv no hi hagi un salt de linia entre comes.");
        }
    }

    public static class UsuariAmbNomIContrasenyaNoExisteix extends Exception {
        public UsuariAmbNomIContrasenyaNoExisteix () {
            super("NOM_O_CONTRASENYA_NO_COINCIDEIXEN");
        }
    }

    public static class UsuariJaExisteix extends Exception {
        public UsuariJaExisteix () {
            super("USUARI_JA_EXISTEIX");
        }
    }
    
    public static class UsuarisNoCoincidents extends Exception {
        public UsuarisNoCoincidents () {
            super("USUARIS_NO_COINCIDENTS: Els usuaris entre algorismes no són els mateixos.");
        }
    }

    public static class BaseDeDadesNoExisteix extends Exception {
        public BaseDeDadesNoExisteix () {
            super("NO_HI_HA_BASE_DE_DADES");
        }
    }

    public static class ArxiuItemsNoExisteix extends Exception {
        public ArxiuItemsNoExisteix () {
            super("ARXIU_ITEMS.CSV_NO_EXISTEIX");
        }
    }

    public static class ArxiuRatingsDBNoExisteix extends Exception {
        public ArxiuRatingsDBNoExisteix () {
            super("ARXIU_RATINGS.DB.CSV_NO_EXISTEIX");
        }
    }

    public static class FitxerItemsModificat extends Exception {
        public FitxerItemsModificat () {
            super("ARXIU_ITEMS.CSV_HA_SIGUT_MODIFICAT");
        }
    }

    public static class ItemNoExisteix extends Exception {
        public ItemNoExisteix () {
            super("ITEM_NO_EXISTEIX");
        }
    }

    public static class UsuariJaTeValoracioAItem extends Exception {
        public UsuariJaTeValoracioAItem () {
            super("USUARI_JA_TE_UNA_VALORACIO_FETA_AL_ITEM");
        }
    }

    public static class titolHaDeSerString extends Exception {
        public titolHaDeSerString () {
            super("TITOL_HA_DE_SER_STRING");
        }
    }

    public static class idHaDeSerInteger extends Exception {
        public idHaDeSerInteger () {
            super("ID_HA_DE_SER_INTEGER");
        }
    }

    public static class UsuariNoEsCreadorDelItem extends Exception {
        public UsuariNoEsCreadorDelItem () {
            super("NO_ETS_EL_CREADOR_DEL_ITEM");
        }
    }

    public static class ItemNoTeTitol extends Exception{
        public ItemNoTeTitol(){
            super("ITEM_HA_DE_TINDRE_TITOL");
        }
    }

    public static class ItemAmbMateixTitolJaExisteix extends Exception{
        public ItemAmbMateixTitolJaExisteix(){
            super("JA_EXISTEIX_UN_ITEM_AMB_EL_MATEIX_TITOL");
        }
    }

    public static class NoTipusAtributCalculable extends Exception{
        public NoTipusAtributCalculable(){
            super("CAP_TIPUS_ATRIBUT_EN_CALCULABLE");
        }
    }

}