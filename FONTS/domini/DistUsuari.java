package domini; 

import java.lang.Math;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import domini.Excepcions.*;


public class DistUsuari extends Distancia {
    
    //ATRIBUTS

    /**
     * Usuari a partir del qual es calcularan totes les distancies a altres usuaris en la instancia de la classe (DistUsuari) pertaneixent.
     */
    private Usuari UAnalitzat; 

    /**
     * Map que conté totes les distàncies que hi ha entre l'usuari "UAnalitzat" i tots els altres usuaris.
     */
    private HashMap<Usuari, Double> distancies; 


    //CONSTRUCTORES
    
    
    /**
     * Constructora inicial. Crea una instància de la classe, pero sense calcular cap distància cap a cap usuari.
     * @param UAnalitzat És l'usuari a partir del qual es calcularà la distància cap als altres usuaris. Inicialment cap a cap usuari.
     */
    public DistUsuari (Usuari UAnalitzat) throws UsuariNul {
        super(); 
        if (UAnalitzat == null) throw new UsuariNul(); 
        distancies = new HashMap<Usuari, Double> (); 
        this.UAnalitzat = UAnalitzat; 
    }

    /**
     * Constructora, que a partir dels parametres calcula les distancies d'un usuari cap a altres. 
     * @param UAnalitzat És l'usuari a partir del qual és calcularà la distància cap als altres usuaris. 
     * @param usuaris Són tots els usuaris amb els quals és calcularà per a cada un d'ells la distància cap a l'usuari UAnalitzat.
     * @throws UsuariAAfegirEsAnalitzat
     * @throws UsuariJaAfegit
     */
    public DistUsuari (Usuari UAnalitzat, Set<Usuari> usuaris) throws UsuariNul, SetUsuarisNul, UsuariJaAfegit, UsuariAAfegirEsAnalitzat {
        super(); 
        if (UAnalitzat == null) throw new UsuariNul(); 
        if (usuaris == null) throw new SetUsuarisNul (); 
        this.UAnalitzat = UAnalitzat; 
        distancies = new HashMap<Usuari, Double> ();  
        for (Usuari u : usuaris) {
            afegirUsuari(u); 
            
        }

    }


    //OPERACIONS

    /**
     * S'afegeix un altre usuari amb el qual es calcularà la distància cap a l'usuari UAnalitzat. 
     * @param usuari És l'usuari nou amb el qual també es calcularà la distància cap a l'usuari UAnalitzat. 
     */
    public void afegirUsuari (Usuari usuari) throws UsuariJaAfegit, UsuariAAfegirEsAnalitzat {
        if (UAnalitzat == usuari) throw new UsuariAAfegirEsAnalitzat (); 
        if (distancies.containsKey(usuari)) throw new UsuariJaAfegit (); 
        distancies.put(usuari, calcularDistancia(usuari)); 
    }

    /**
     * Elimina un usuari d'entre aquells amb els quals els calculava la distància cap a l'usuari UAnalitzat.
     * @param usuari És l'usuari que s'eliminarà del calcul de distàncies cap a l'usuari UAnalitzat. 
     */
    public void eliminarUsuari (Usuari usuari) throws UsuariNoAfegit, UsuariAEliminarEsAnalitzat {
        if (usuari == UAnalitzat) throw new UsuariAEliminarEsAnalitzat (); 
        if (!distancies.containsKey(usuari)) throw new UsuariNoAfegit (); 
        distancies.remove(usuari); 
    }

    /**
     * funcio que és la responsable de recalcular totes les distancies entre l'usuari UAnalitzat i tots els altres. Aquesta funcio, per tant, és útil quan es creen/modifiquen/eliminen valoracions
     */
    public void recalcularTotesDistancies () {
        HashMap<Usuari, Double> newDistancies = new HashMap<Usuari, Double> (); 

        for (Map.Entry<Usuari, Double> i : distancies.entrySet()) {
            newDistancies.put(i.getKey(), calcularDistancia(i.getKey())); 
        }

        distancies.clear();
        distancies = newDistancies;

    }

    /**
     * Calcula la distància entre un usuari i l'usuari UAnalitzat. 
     * @param usuari És l'usuari amb el qual és calcularà la distància cap a l'usuari UAnalitzat. 
     * @return retorna la distància calculada
     */
    private Double calcularDistancia (Usuari usuari) {
        double suma = 0; 
        boolean trobatAlmenysUn = false; 


        for (Map.Entry<Item, Valoracio> itemValoracioCurrent: UAnalitzat.getItemsValoracions().entrySet()) {
            if (usuari.getItemsValoracions().containsKey(itemValoracioCurrent.getKey())) {
                trobatAlmenysUn = true; 
                double puntuacioUserU = itemValoracioCurrent.getValue().getPuntuacio();
                double puntuacioUserV = usuari.getItemsValoracions().get(itemValoracioCurrent.getKey()).getPuntuacio();
                suma += Math.pow(puntuacioUserU - puntuacioUserV, 2); 
            }
        }
        
        
        if (trobatAlmenysUn == false) {
            suma = 20; //maxim
            return suma; 
        }

        else {
            suma = Math.sqrt(suma); 
            if (suma > 20.0) return 20.0;
            else return suma; 
        }

    }

    
    /**
     * 
     * @return
     */
    public HashMap<Usuari, Double> getDistancies () {
        return distancies; 
    }
}