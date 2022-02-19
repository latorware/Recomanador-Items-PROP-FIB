
/**
 ItemDistItem.Java
 especificació de classe ItemDistItem
 */

package domini;


import java.lang.Math;

/**
 ItemDistItem

 Classe que calcula la distàncie entre Items. 
 Conté dos ítems, un DistItem i una distància. 
 * */



public class ItemDistItem extends Distancia {

    /** Item que representa el primer ítem */
    private Item item1; 
    /** Item que representa el segon ítem */
    private Item item2; 
    /** DistItem que s'utilitza per calcular distàncies entre items */
    private DistItem distItem;
    /**Double on es guardarà la distància resultant*/
    private Double distancia; 


    /**CONSTRUCTORES*/
    public ItemDistItem (Item item1, Item item2, DistItem distItem) {
        this.item1 = item1; 
        this.item2 = item2; 
        this.distItem = distItem; 
        calculaDistancia(); 
    }

    /**METODES*/
    
    /**
    * @brief Funció que calcula la disància entre dos atributs, depenent del tipusAtribut calcularem la distància d'una foma o altre. 
    * Genèricament la diferència total màxima per dos items representa la suma de 10?2 a la distància. 
    * Per altre banda la diferència total mínima representa 0. 
    * El resultat es guardarà a la variable distància. 
    * @param 
    * @return
    */
    private void calculaDistancia() {
        
        double suma = 0; 
        boolean trobat_almenys_un = false; 
        //system.out.println("Començant calcul distancia entre item: " + item1.getID() + " i item: " + item2.getID());
        //system.out.println("Atributs que es comparen: ");
        for (TipusAtribut tAtrCurrent : distItem.tipusAtributsItems.get(item1).keySet()) {
            if (tAtrCurrent.getCalculable()) {
                if (distItem.tipusAtributsItems.get(item2).containsKey(tAtrCurrent)) {
                    //system.out.println("    Es compara TipusAtribut: " + tAtrCurrent.getNom());
                    //system.out.println("        D'aquest TipusAtribut es comparen els atributs: ");
                    trobat_almenys_un = true; 
                    //depen del tipus atribut, calcularem la distancia de una forma u altre
                    //Genericament, la diferencia total maxima dins de un TipusAtribut per dos items, representa una suma de 10^2 a la distancia. Per altre banda, la diferencia total minima representa una suma de 0

                    if (tAtrCurrent.getTipusDada().equals("Bool")) {
                        
                        suma += distTAtributBool(tAtrCurrent); 

                    }

                    else if (tAtrCurrent.getTipusDada().equals("String")) {

                        suma += distTAtributString(tAtrCurrent); 

                    }

                    else if (tAtrCurrent.getTipusDada().equals("Int")) {
                        suma += distTAtributInt(tAtrCurrent); 

                    }

                    else if (tAtrCurrent.getTipusDada().equals("Double")) {
                        suma += distTAtributDouble(tAtrCurrent); 

                    }
                }
            }
        }

        if (!trobat_almenys_un) distancia =  Double.MAX_VALUE; //inifinit

        else {
            distancia = Math.sqrt(suma); 
        }
    }


    
    /** 
    * @brief Retorna dist entre els dos items al quadrat ( per calcular distancia euclidea) per al tipusatribut introduit (el qual a de ser tipus bool)
    * @param tAtrCurrent, TipusAtribut que representa l'atribut que s'està utlitzant en aqyell moment
    * @return Double
    */
    //
    private Double distTAtributBool (TipusAtribut tAtrCurrent) {
        //si els bools coincideixen, distancia no hi haura. Sino aquesta acabara sent 10^2
        //com que es bool, nomes hi ha un atribut per TipusAtribut
        for (Atribut atributCurrent1 : distItem.tipusAtributsItems.get(item1).get(tAtrCurrent)) { //nomes iterara un cop
            for (Atribut atributCurrent2 : distItem.tipusAtributsItems.get(item2).get(tAtrCurrent)) { //nomes iterara un cop
                //system.out.println("            atribut de item1: " + ((AtrBool) atributCurrent1).getAtributBool());
                //system.out.println("            atribut de item2: " + ((AtrBool) atributCurrent2).getAtributBool());
                if ( ( ((AtrBool) atributCurrent1).getAtributBool() ) != ( ((AtrBool) atributCurrent2).getAtributBool() ) ) {
                    return Math.pow(10.0, 2); 
                }
                else {
                    return 0.0; 
                }
            }
        }
        return 0.0; 
    }

    /** 
    * @brief Retorna dist entre els dos items al quadrat ( per calcular distancia euclidea) per al tipusatribut introduit (el qual a de ser tipus int)
    * @param tAtrCurrent, TipusAtribut que representa l'atribut que s'està utlitzant en aqyell moment
    * @return Double
    */

    //Retorna dist entre els dos items al quadrat ( per calcular distancia euclidea) per al tipusatribut introduit (el qual a de ser tipus int)
    private Double distTAtributInt (TipusAtribut tAtrCurrent) {
        //calcularem la diferencia que hem de sumar respecte el valor minim i el maxim de tots els atributs que hi ha del tipusAtribut
        //com que es de tipus numero  , cada item nomes tindra un atribut del tipus

        int numero1 = 0; 
        int numero2 = 0; 


        for (Atribut atributCurrent1 : distItem.tipusAtributsItems.get(item1).get(tAtrCurrent)) { //nomes iterara un cop
            numero1 = ((AtrInt) atributCurrent1).getAtributInt(); 
            //system.out.println("            atribut de item1: " + ((AtrInt) atributCurrent1).getAtributInt());
        }

        for (Atribut atributCurrent2 : distItem.tipusAtributsItems.get(item2).get(tAtrCurrent)) { //nomes iterara un cop
            numero2 = ((AtrInt) atributCurrent2).getAtributInt(); 
            //system.out.println("            atribut de item2: " + ((AtrInt) atributCurrent2).getAtributInt());
        }

        int diferencia = Math.abs(numero1 - numero2); 
        double diferenciaMaxima = tAtrCurrent.getMaxValor() - tAtrCurrent.getMinValor(); 

        return Math.pow( (diferencia / (diferenciaMaxima * 1.0) )* 10.0 , 2.0); 


    }
    
    /** 
    * @brief Retorna dist entre els dos items al quadrat ( per calcular distancia euclidea) per al tipusatribut introduit (el qual a de ser tipus string)
    * @param tAtrCurrent, TipusAtribut que representa l'atribut que s'està utlitzant en aqyell moment
    * @return Double
    */

    //Retorna dist entre els dos items al quadrat ( per calcular distancia euclidea) per al tipusatribut introduit (el qual a de ser tipus string)
    private Double distTAtributString (TipusAtribut tAtrCurrent) {
        //primer es mirara quans atributs d'aquest tipus string hi ha per cada un dels dos items
        //calcularem la suma de la distancia per a aquest tipusAtribut, segons el maxim(nombre atributs item1, nombre atributs item2)

        int atributsCoincidents = 0; 

        int divisor = Math.max( distItem.tipusAtributsItems.get(item1).get(tAtrCurrent).size(), 
                                distItem.tipusAtributsItems.get(item2).get(tAtrCurrent).size()
                                ); 

        for (Atribut atributCurrent1 : distItem.tipusAtributsItems.get(item1).get(tAtrCurrent)) {
            for (Atribut atributCurrent2 : distItem.tipusAtributsItems.get(item2).get(tAtrCurrent)) {
                //system.out.println("            atribut de item1: " + ((AtrString) atributCurrent1).getAtributString() + " amb atribut de item2: " + ((AtrString) atributCurrent2).getAtributString());
                if (((AtrString) atributCurrent1).getAtributString().equals(((AtrString) atributCurrent2).getAtributString())) {
                    atributsCoincidents++; 
                    
                }   
            }
        }

        return Math.pow( ( ( 1.0 - (atributsCoincidents / (divisor*1.0) ) ) *10.0 ), 2 ); 

    }

    /** 
    * @brief Retorna dist entre els dos items al quadrat ( per calcular distancia euclidea) per al tipusatribut introduit (el qual a de ser tipus double)
    * @param tAtrCurrent, TipusAtribut que representa l'atribut que s'està utlitzant en aqyell moment
    * @return Double
    */

    //Retorna dist entre els dos items al quadrat ( per calcular distancia euclidea) per al tipusatribut introduit (el qual a de ser tipus DOUUUUBLE)
    private Double distTAtributDouble (TipusAtribut tAtrCurrent) {
        //igual que si tipus int

        double numero1 = 0; 
        double numero2 = 0; 


        for (Atribut atributCurrent1 : distItem.tipusAtributsItems.get(item1).get(tAtrCurrent)) { //nomes iterara un cop
            numero1 = ((AtrDouble) atributCurrent1).getAtributDouble(); 
            //system.out.println("            atribut de item1: " + ((AtrDouble) atributCurrent1).getAtributDouble());
        }

        for (Atribut atributCurrent2 : distItem.tipusAtributsItems.get(item2).get(tAtrCurrent)) { //nomes iterara un cop
            numero2 = ((AtrDouble) atributCurrent2).getAtributDouble(); 
            //system.out.println("            atribut de item2: " + ((AtrDouble) atributCurrent2).getAtributDouble());
        }

        double diferencia = Math.abs(numero1 - numero2); 
        double diferenciaMaxima = tAtrCurrent.getMaxValor() - tAtrCurrent.getMinValor(); 

        return Math.pow( (diferencia / (diferenciaMaxima * 1.0) )* 10.0 , 2.0); 

    }


    /** 
    * @brief Getter de la distància
    * @param 
    * @return Double
    */

    public Double getDistancia() {
        return distancia; 
    }




}



