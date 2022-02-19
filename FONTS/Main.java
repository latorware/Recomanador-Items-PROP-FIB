
import presentacio.CtrlPresentacio;

/**
 * Classe Main del programa. La seva funció es crear i inicialitzar una instància de classe CtrlPresentacio
 */
public class Main {

    public static void main(String[] args) throws Exception
    {
        javax.swing.SwingUtilities.invokeLater (
            new Runnable() {
                public void run() {
                    CtrlPresentacio ctrlPresentacio = CtrlPresentacio.getInstance();
                    try {
                        ctrlPresentacio.inicialitzarPresentacio();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } 
                }
            }
        );
    }
}