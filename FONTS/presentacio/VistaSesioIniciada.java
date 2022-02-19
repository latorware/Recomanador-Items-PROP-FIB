package presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class VistaSesioIniciada {
    private CtrlPresentacio iCtrlPresentacio;
    private JFrame frameVista;
    private JPanel partEsquerre;
    private JPanel partDreta;
    private JPanel partSuperior;
    private JPanel panellExterior;
    private JLabel nom;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    /**
     * 
     * @param frameVista frame principal de l'aplicació
     * @param iCtrlPresentacio instància de controlador de presentació
     */
    public VistaSesioIniciada(JFrame frameVista, CtrlPresentacio iCtrlPresentacio) {
        this.iCtrlPresentacio = iCtrlPresentacio;
        this.frameVista = frameVista;
        mostraGuia();

        this.frameVista.revalidate();
        this.frameVista.setPreferredSize(new Dimension(800, 600));
        this.frameVista.setMinimumSize(new Dimension(1000, 800));
        this.frameVista.pack();
        this.frameVista.setVisible(true);


        //$$$setupUI$$$();
        inicialitza();


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canviaAPanellRecomanacio();
            }
        });


        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String)JOptionPane.showInputDialog(
                    frameVista,
                    "Introdueix el nom de el ítem que vols cercar:", "Busca",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null);
                if (s != null) {
                    canviaAPAnellCerca(s);
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tractaCrea();
            }
        });



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tractaAdministraUsuari();
            }
        });
    }

    /**
     * @return  retorna el panell de la classe. És a dir, el panell que fa referència a la sessió iniciada d'un usuari
     */
    public JPanel getJPanel() {
        return panellExterior;
    }

    /**
     * Mostra una guia de tot el que pot fer l'usuari en aquest panell 
     */
    private void mostraGuia() {
        String msg = "<html><b>INFO: Com usar el programa:<b><br>A la part superior hi ha icones amb les possibles accions possibles:<ul><li>Icona <b>Refresca<b>: refresca llista recomanacions personal"
                + "<li>Icona <b>afegeix<b>: crea un item (globalment) o una valoració"
                + "<li>Icona <b>cerca<b>: cerca un item per consultar-lo o eliminar-lo."
                + "<li>Icona <b>engranatge<b>: elimina el teu usuari, o consulta i/o elimina les teves valoracions. </ul></html>";
        JOptionPane.showMessageDialog(frameVista, msg);
    }

    /**
     * inicialitza la classe, és a dir, és computen els elements de la interfície gràfica d'aquesta classe
     */
    private void inicialitza() {
        panellExterior = new JPanel();
        panellExterior.setLayout(new BorderLayout(0, 0));
        panellExterior.setBackground(new Color(-13487566));
        partSuperior = new JPanel();
        partSuperior.setLayout(new BorderLayout(0, 0));
        partSuperior.setBackground(new Color(-14803426));
        panellExterior.add(partSuperior, BorderLayout.NORTH);
        partDreta = new JPanel();
        partDreta.setLayout(new BorderLayout(10, 0));
        partDreta.setBackground(new Color(-14803426));
        partSuperior.add(partDreta, BorderLayout.CENTER);
        nom = new JLabel();
        //Font nomFont = this.$$$getFont$$$(null, Font.BOLD, 14, nom.getFont());
        //if (nomFont != null) nom.setFont(nomFont);
        nom.setFont(new Font(null, Font.BOLD, 14));
        nom.setForeground(new Color(-10197916));
        nom.setHorizontalAlignment(0);
        nom.setOpaque(false);
        nom.setText("<html>Benvingut: <b>" + iCtrlPresentacio.consultaNomUsuari() + "</b></html>");
        partDreta.add(nom, BorderLayout.CENTER);
        button1 = new JButton();
        button1.setBackground(new Color(-14803426));
        button1.setIcon(new ImageIcon(getClass().getResource("icons/icons64x64/settings.png")));
        button1.setText("");
        partDreta.add(button1, BorderLayout.EAST);
        partEsquerre = new JPanel();
        partEsquerre.setLayout(new BorderLayout(0, 0));
        partEsquerre.setBackground(new Color(-14803426));
        partSuperior.add(partEsquerre, BorderLayout.WEST);
        button2 = new JButton();
        button2.setBackground(new Color(-14803426));
        button2.setIcon(new ImageIcon(getClass().getResource("icons/icons64x64/reload.png")));
        button2.setText("");
        partEsquerre.add(button2, BorderLayout.WEST);
        button3 = new JButton();
        button3.setBackground(new Color(-14803426));
        button3.setIcon(new ImageIcon(getClass().getResource("icons/icons64x64/add.png")));
        button3.setText("");
        partEsquerre.add(button3, BorderLayout.CENTER);
        button4 = new JButton();
        button4.setBackground(new Color(-14803426));
        button4.setIcon(new ImageIcon(getClass().getResource("icons/icons64x64/searchIcon.png")));
        button4.setText("");
        partEsquerre.add(button4, BorderLayout.EAST);
    }


    /**
     * Mostra el panell de la classe panellRecomanacio
     */
    public void canviaAPanellRecomanacio() {
        BorderLayout layout = (BorderLayout) panellExterior.getLayout();
        if (layout.getLayoutComponent(BorderLayout.CENTER) != null) {
            panellExterior.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
        try {
            panellExterior.add(new panellRecomanacio(frameVista, iCtrlPresentacio, this).getJPanel(), BorderLayout.CENTER);
            frameVista.setPreferredSize(new Dimension(800, 600));
            frameVista.setMinimumSize(new Dimension(1000, 800));
            frameVista.pack();
        } catch (Exception excepcio) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut actualitzar/mostrar recomanacions\n" + excepcio.getMessage());
            return;
        }
    }

    /**
     * Mostra el panell de la classe panellBusca
     * @param nomItem nom de l'ítem que es vol deitjar consultar
     */
    public void canviaAPAnellCerca (String nomItem) {
        BorderLayout layout = (BorderLayout) panellExterior.getLayout();
        if (layout.getLayoutComponent(BorderLayout.CENTER) != null) {
            panellExterior.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
        try {
            panellExterior.add(new panellBusca(frameVista, iCtrlPresentacio, this, nomItem).getJPanel(), BorderLayout.CENTER);
            frameVista.setPreferredSize(new Dimension(800, 600));
            frameVista.setMinimumSize(new Dimension(1000, 800));
            frameVista.pack();
        } catch (Exception excepcio) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut mostrar la cerca de el item\n" + excepcio.getMessage());
            //excepcio.printStackTrace();
            return;
        }
    }

    /**
     * Tracta el fet que l'usuari hagi clicat al botó crear. Per tant, el fet de crear un item / valoracio
     */
    public void tractaCrea() {
        String[] options = {"Crea un item", "Crea una valoracio"};
        int resultDialog = JOptionPane.showOptionDialog(frameVista, "Que es el que vols crear?", "Afegeix", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 

        if (resultDialog == 0) {    //CREEM ITEM
            try {
                String msg = "<html><b>A continuació hauras d'especificar els atributs del nou item per a cada TipusAtribut</b>" + 
                "<br>PERO ATENCIO: " +
                "<ul><li>Si un TipusAtribut es de tipus <b>Int</b>, només podras especificar un i només un nombre enter."
                + "<li>Si un TipusAtribut es de tipus <b>Double</b>, només podras especificar un i només un nombre (enter o decimal)"
                + "<li>Si un TipusAtribut es de tipus <b>Bool</b>, en el menú desplegable podras triar si l'atribut serà cert, false, o no el vols especificar"
                + "<li>Si un TipusAtribut es de tipus <b>String</b>, llavors tindràs la possibilitar d'escriure un atribut, o més d'ún. Si escrius més d'ún, aquests han d'anar separats per <b>UN ; I SENSE ESPAIS</b>"
                + "<li> Si <b>no vols especificar cap atribut</b>, deixa el camp d'escritura en blanc"
                + "</ul></html>"; 
                JOptionPane.showMessageDialog(frameVista, msg);

                HashMap<String, String> atributsItemNou = new HashMap<String, String> ();
                String[] options2 = {"Seguent"}; 

                for (Map.Entry<String, String> entry : iCtrlPresentacio.consultaTipusTipusAtributs().entrySet()) {

                    if (entry.getValue().equals("Int")) {
                        JPanel fields = new JPanel(new GridLayout(2, 1));
                        JLabel nominfo = new JLabel("<html>Especifica un i nomes un <b>Int</b> (enter), per al TipusAtribut " + entry.getKey() + " per al nou item. <br> Si no en vols especificar cap, deixa el camp en blanc</html>"); 
                        JTextField valor = new JTextField(20); 
                        fields.add(nominfo); 
                        fields.add(valor); 

                        int resultat = JOptionPane.showOptionDialog(frameVista, fields, "Especifica els TipusAtribut", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]); 

                        if (resultat == 0) {
                            if ((valor.getText() != null) && (valor.getText().length() > 0)) {
                                atributsItemNou.put(entry.getKey(), valor.getText());
                            }
                            else {
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(frameVista, "No s'ha creat litem ja que has cancelat la seva creacio");
                            return;
                        }
                

                    }
                    else if (entry.getValue().equals("Double")) {
                        JPanel fields = new JPanel(new GridLayout(2, 1));
                        JLabel nominfo = new JLabel("<html>Especifica un i només un <b>Double</b> (DECIMAL o enter), per al TipusAtribut " + entry.getKey() + " per al nou item. <br> Si no en vols especificar cap, deixa el camp en blanc</html>"); 
                        JTextField valor = new JTextField(20); 
                        fields.add(nominfo); 
                        fields.add(valor);

                        int resultat = JOptionPane.showOptionDialog(frameVista, fields, "Especifica els TipusAtribut", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]); 

                        if (resultat == 0) {
                            if ((valor.getText() != null) && (valor.getText().length() > 0)) {
                                atributsItemNou.put(entry.getKey(), valor.getText());
                            }
                            else {
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(frameVista, "No s'ha creat litem ja que has cancelat la seva creacio");
                            return;
                        }

                    }
                    else if (entry.getValue().equals("Bool")) {
                        JPanel fields = new JPanel(new GridLayout(2, 1));
                        JLabel nominfo = new JLabel("<html>Especifica la opció que vols triar per a el tipusAtribut " + entry.getKey() + " el qual es de tipus Bool</html>"); 
                        JComboBox<String> comboBox1 = new JComboBox<>(new String[]{"TRUE", "FALSE", "NO ESPECIFICAR"});
                        fields.add(nominfo); 
                        fields.add(comboBox1); 

                        int resultat = JOptionPane.showOptionDialog(frameVista, fields, "Especifica els TipusAtribut", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]); 

                        if (resultat == 0) {
                            atributsItemNou.put(entry.getKey(), (String) comboBox1.getSelectedItem());

                        }
                        else {
                            JOptionPane.showMessageDialog(frameVista, "No s'ha creat litem ja que has cancelat la seva creacio");
                            return;
                        }
                    }
                    else if (entry.getValue().equals("String")) {
                        JPanel fields = new JPanel(new GridLayout(2, 1));
                        JLabel nominfo = new JLabel("<html>Especifica un <b>String</b> (paraules), per al TipusAtribut " + entry.getKey() + " per al nou item. <br> Si no en vols especificar cap, deixa el camp en blanc<br> Si vols especificar més d'un atribut per aquest tipusAtribut, aquests han d'anar separats per <b>UN ; I SENSE ESPAIS</b></html>"); 
                        JTextField valor = new JTextField(20); 
                        fields.add(nominfo); 
                        fields.add(valor); 

                        int resultat = JOptionPane.showOptionDialog(frameVista, fields, "Especifica els TipusAtribut", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]); 

                        if (resultat == 0) {
                            if ((valor.getText() != null) && (valor.getText().length() > 0)) {
                                atributsItemNou.put(entry.getKey(), valor.getText());
                            }
                            else {
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(frameVista, "No s'ha creat litem ja que has cancelat la seva creacio");
                            return;
                        }
                    }

                }

                iCtrlPresentacio.creaItem(atributsItemNou);
                JOptionPane.showMessageDialog(frameVista, "ITEM CREAT CORRECTAMENT");



            }
            catch (Exception excepcio) {
                JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut crear l'item" + excepcio.getMessage());
                return; 
            }
        }
        else if (resultDialog == 1) {       //CREEM VALORACIO
            try {

                JPanel fields = new JPanel(new GridLayout(6, 1));
                JLabel nomTitolInfo = new JLabel("<html>Especifica el TITOL de l'ítem del que vols crear una valoracio: </html>"); 
                JTextField nomTitol = new JTextField(20); 
                JLabel puntuacioInfo = new JLabel("<html>Tria la puntuació que vols per a aquest item: </html>"); 
                JComboBox<Integer> comboBoxPuntuacio = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5});
                JLabel comentariInfo = new JLabel("<html>Si vols afegir un comentari, aqui ho pots fer. Sinó, pots deixar el camp en blanc</html>"); 
                JTextField comentari = new JTextField(20); 
                fields.add(nomTitolInfo); 
                fields.add(nomTitol);
                fields.add(puntuacioInfo); 
                fields.add(comboBoxPuntuacio);
                fields.add(comentariInfo); 
                fields.add(comentari);  

                String[] options2 = {"Fet"}; 

                int resultat = JOptionPane.showOptionDialog(frameVista, fields, "Creacio valoracio", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]); 

                if (resultat == 0) {
                    if ((nomTitol.getText() != null) && (nomTitol.getText().length() > 0)) {
                        if ((comentari.getText() != null) && (comentari.getText().length() > 0)) {
                            iCtrlPresentacio.creaValoracio(nomTitol.getText(), (Integer) comboBoxPuntuacio.getSelectedItem(), comentari.getText());
                        }
                        else {
                            iCtrlPresentacio.creaValoracio(nomTitol.getText(), (Integer) comboBoxPuntuacio.getSelectedItem(), null);
                        }

                        JOptionPane.showMessageDialog(frameVista, "VALORACIO CREADA CORRECTAMENT");

                        return; 
                    }
                    else {
                        JOptionPane.showMessageDialog(frameVista, "No s'ha creat la valoracio ja que no has introduit cap titol d'ítem");
                        return;
                    }
                }
                else {
                    JOptionPane.showMessageDialog(frameVista, "No s'ha creat la valoracio ja que has cancelat la seva creacio");
                    return;
                }
            }
            catch (Exception excepcio) {
                JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut crear la valoracio" + excepcio.getMessage());
                return; 
            }

        }



    }

    /**
     * Tracta el fet que l'usuari hagi clicat al botó de engranatge. Per tant, el fet de eliminar l'usuari / el fet de mostrar el panell de la classe panellAdministraValoracions
     */
    public void tractaAdministraUsuari() {
        String[] options = {"Elimina usuari", "Consulta/administra les teves valoracions"};
        int resultDialog = JOptionPane.showOptionDialog(frameVista, "Que es el que vols fer?", "Configuracio/Consulta usuari", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 

        if (resultDialog == 0) {
            int result = JOptionPane.showConfirmDialog(frameVista, "Estas segur que vols eliminar el teu usuari?", "Eliminar usuari", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 

            if (result == JOptionPane.YES_OPTION) {
                iCtrlPresentacio.eliminaUsuari();
                JOptionPane.showMessageDialog(frameVista, "<html> El teu usuari <b> ha sigut eliminat </b> correctament. Ara el programa es tancarà. <br> La proxima vegada que el obris, podràs iniciar sessió amb un altre usuari, o registrar-te amb un usuari nou</html>");
                System.exit(1);
                return;
            }
            else {
                return; 
            }

        }
        else if (resultDialog == 1) { //panell que administra valoracions
            canviaPanellAdministraValoracions();
        }


    }

    /**
     * Mostra el panell de la classe panellAdministraValoracions
     */
    public void canviaPanellAdministraValoracions() {
        BorderLayout layout = (BorderLayout) panellExterior.getLayout();
        if (layout.getLayoutComponent(BorderLayout.CENTER) != null) {
            panellExterior.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
        try {
            panellExterior.add(new panellAdministraValoracions(frameVista, iCtrlPresentacio, this).getJPanel(), BorderLayout.CENTER);
            frameVista.setPreferredSize(new Dimension(800, 600));
            frameVista.setMinimumSize(new Dimension(1000, 800));
            frameVista.pack();
        } catch (Exception excepcio) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut mostrar la vista de les teves valoracions\n" + excepcio.getMessage());
            return;
        }

    }
 



}
