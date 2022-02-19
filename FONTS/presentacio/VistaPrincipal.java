package presentacio;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;


import utils.Pair;

public class VistaPrincipal {

    private CtrlPresentacio iCtrlPresentacio; 
    private JFrame frameVista = new JFrame("Vista principal"); 
    private JPanel panellIniciaSessio = new JPanel(); 
    private JLabel usuariLabel = new JLabel("Usuari"); 
    private JTextField usuariText = new JTextField(20); 
    private JLabel contrasenyaLabel  = new JLabel("Contrasenya");
    private JPasswordField contrasenyaText   = new JPasswordField(20); 
    private JButton iniciaSessioButton = new JButton(); 
    private JButton registraUsuariButton = new JButton(); 
    private GridBagConstraints gbcIniciaSesio = new GridBagConstraints(); 
    
    /*
    private GridBagConstraints gbcsesioIniciada = new GridBagConstraints(); 
    private JPanel sesioIniciada = new JPanel(); 
    private ImageIcon settingsIcon = new ImageIcon("icons/settings.png"); 
    private ImageIcon reloadIcon = new ImageIcon("icons/reload.jpg"); 
    private ImageIcon searchIcon = new ImageIcon("icons/searchIcon.png"); 
    private JLabel settingsButton = new JLabel(settingsIcon); 
    private JLabel reloadButton = new JLabel(reloadIcon); 
    private JLabel searchButton = new JLabel(searchIcon); 
    */

    /**
     * Consturctora de la classe
     * @param pCtrlPresentacio instància de controlador de presentació
     */
    public VistaPrincipal (CtrlPresentacio pCtrlPresentacio) {
        iCtrlPresentacio = pCtrlPresentacio; 
        
        inicialitzarComponents(); 
    }

    /**
     * Fa visible la vista principal, és a dir els objectes de la interfície del programa que formen part de l'inici del programa, és a dir, quan encara no s'ha iniciat ninguna sessió per ningun usuari
     */
    public void  ferVisible () {
        frameVista.setVisible(true);
    }

    /**
     * Inicialitza els components de la interfície gràfica d'aquesta classe
     */
    private void inicialitzarComponents () {
        frameVista.setLayout(new GridBagLayout());
        frameVista.setPreferredSize(new Dimension(700, 500));
        frameVista.setMinimumSize(new Dimension(700, 500));
        //frameVista.setSize(500,500);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gbcIniciaSesio.fill = GridBagConstraints.BOTH; 
        frameVista.getContentPane().add(panellIniciaSessio);
        //panellIniciaSessio.setLayout(null);

        //panellIniciaSessio.setPreferredSize(panellIniciaSessio.getSize());;
        panellIniciaSessio.setLayout(new GridBagLayout());
        //((JComponent) frameVista.getContentPane()).setOpaque(true); 
        //frameVista.setBackground(new Color(50, 50, 50));
        frameVista.getContentPane().setBackground(new Color(50, 50, 50));

        //USUARI LABEL
        //usuariLabel.setBounds(10,20,80,25);
        gbcIniciaSesio.gridx = 0;
        gbcIniciaSesio.gridy = 0; 
        gbcIniciaSesio.gridwidth = 1;
        gbcIniciaSesio.ipady = 10; 
        //gbcIniciaSesio.gridwidth = 3; 
        gbcIniciaSesio.fill = GridBagConstraints.BOTH; 
        usuariLabel.setForeground(new Color(130, 130, 130));
        usuariLabel.setOpaque(true);
        usuariLabel.setBackground(new Color(20, 20, 20));
        usuariLabel.setFont(new Font(null, Font.BOLD, 12));
        usuariLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //usuariLabel.setPreferredSize(frameVista.getSize());
        panellIniciaSessio.add(usuariLabel, gbcIniciaSesio); 

        //USUARI TEXT
        //usuariText.setBounds(100,20,165,25);
        gbcIniciaSesio.gridx = 1;
        gbcIniciaSesio.gridy = 0; 
        gbcIniciaSesio.gridwidth = 1; 
        gbcIniciaSesio.ipady = 10; 
        usuariText.setBackground(new Color(30, 30, 30));
        usuariText.setBorder(new LineBorder(Color.black, 2));
        usuariText.setForeground(new Color(70, 70, 70));
        //usuariText.setPreferredSize(frameVista.getSize());
        panellIniciaSessio.add(usuariText, gbcIniciaSesio); 

        //CONTRASENYA LABEL
        //contrasenyaLabel.setBounds(10,50,80,25);
        gbcIniciaSesio.gridx = 0;
        gbcIniciaSesio.gridy = 1; 
        gbcIniciaSesio.ipady = 10; 
        gbcIniciaSesio.gridwidth = 1;
        contrasenyaLabel.setOpaque(true);
        contrasenyaLabel.setBackground(new Color(20, 20, 20));
        contrasenyaLabel.setFont(new Font(null, Font.PLAIN, 12));
        contrasenyaLabel.setForeground(new Color(120, 120, 120));
        contrasenyaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //contrasenyaLabel.setPreferredSize(frameVista.getSize());
        panellIniciaSessio.add(contrasenyaLabel, gbcIniciaSesio); 

        //CONTRASENYA TEXT
        //contrasenyaText.setBounds(100,50,165,25);
        gbcIniciaSesio.gridx = 1;
        gbcIniciaSesio.gridy = 1; 
        gbcIniciaSesio.ipady = 10; 
        //contrasenyaText.setForeground(Color.black);
        gbcIniciaSesio.gridwidth = 1; 
        contrasenyaText.setBackground(new Color(30, 30, 30));
        contrasenyaText.setBorder(new LineBorder(Color.black, 2));
        //contrasenyaText.setPreferredSize(frameVista.getSize());
        contrasenyaText.setForeground(new Color(70, 70, 70));
        panellIniciaSessio.add(contrasenyaText, gbcIniciaSesio); 

        //INICIA SESSIO BUTTON
        //iniciaSessioButton.setBounds(10,80,140,25);
        gbcIniciaSesio.gridx = 0;
        gbcIniciaSesio.gridy = 2; 
        gbcIniciaSesio.gridwidth = 4; 
        gbcIniciaSesio.ipady = 20; 
        iniciaSessioButton.setText("Inicia Sessió");
        iniciaSessioButton.setFont(new Font(null, Font.BOLD, 12));
        iniciaSessioButton.setForeground(new Color(210, 210, 210));
        iniciaSessioButton.setBackground(new Color(0, 0, 0));
        iniciaSessioButton.setBorder(new LineBorder(Color.BLACK, 1, true));
        iniciaSessioButton.setHorizontalAlignment(SwingConstants.CENTER);
        //iniciaSessioButton.setPreferredSize(frameVista.getSize());
        panellIniciaSessio.add(iniciaSessioButton,gbcIniciaSesio); 
        
        //RESGISTRA EL USER BUTTON
        //registraUsuariButton.setBounds(155,80,140,25);
        gbcIniciaSesio.gridx = 0;
        gbcIniciaSesio.gridy = 3; 
        gbcIniciaSesio.gridwidth = 4; 
        gbcIniciaSesio.ipady = 20; 
        registraUsuariButton.setText("Registra't");
        registraUsuariButton.setForeground(new Color(200, 200, 200));
        registraUsuariButton.setFont(new Font(null, Font.BOLD, 12));
        registraUsuariButton.setBackground(new Color(0, 0, 0));
        registraUsuariButton.setBorder(new LineBorder(Color.BLACK, 1, true));
        registraUsuariButton.setHorizontalAlignment(SwingConstants.CENTER);
        registraUsuariButton.setBorder(new MatteBorder(1, 0, 0, 0, new Color(90, 90, 90)));
        //registraUsuariButton.setPreferredSize(frameVista.getSize());
        panellIniciaSessio.add(registraUsuariButton,gbcIniciaSesio); 

        //panellIniciaSessio.setOpaque(false);
        //frameVista.setBackground(Color.blue);

        //frameVista.setResizable(false); 

        frameVista.pack();



        registraUsuariButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                registraClicat(e); 
                
            }

        }); 


        iniciaSessioButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                iniciaClicat(e); 
                
            }

        }); 


        especificaPath();
        comprovaCanvisArxiuItems (); 



    }

    /**
     * Pregunta i fa set del path de la base de dades amb la que l'usuari vol treballar 
     */
    private void especificaPath () {

        String[] options = {"OK"};

        JPanel fields = new JPanel(new GridLayout(2, 1));
        JLabel nom = new JLabel("<html>Especifica el path (carpeta) on estan els arxius csv de la base de dades que vols utilitzar (ex: path de la carpeta movies.sample)<br> El directori ha de contenir un arxiu anomenat 'items.csv', i un altre anomenat 'ratings.db.csv'.<br> Per tal que funcioni en tots els sistemes operatius és necessari que tanquis el path amb els separadors específics d'aquell SO (Exemple: Windows utilitza '\\', Ubuntu '/', per ubuntu seria ../250/)"); 
        JTextField path = new JTextField(20); 
        fields.add(nom); 
        fields.add(path);
        int resultDialog; 
        
        resultDialog = JOptionPane.showOptionDialog(frameVista, fields, "Path base dades", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
           
        try {
            iCtrlPresentacio.especificaPath(path.getText());
            JOptionPane.showMessageDialog(frameVista, "PATH CARREGAT CORRECTAMENT");
        }

        catch (Exception excepcio) {
            JOptionPane.showMessageDialog(frameVista, "ERROR en path: " + path.getText() + " MISSATGE: " + excepcio.getMessage() + "  TORNA-HO A PROVAR");
            especificaPath();
        }
    }


    /**
     * Tracta el fet que l'usuari hagi clicat al botó de registrar-se. És a dir, tot el procediment de registre d'un usuari
     * @param e
     */
    private void registraClicat (ActionEvent e) {

        if ((usuariText.getText() == null) || (usuariText.getText().length() <= 0)) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut registrar\n" + "No has introduit cap nom d'usuari");
            return; 
        }

        if ((new String(contrasenyaText.getPassword()) == null) || (new String(contrasenyaText.getPassword()).length() <= 0)) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut registrar\n" + "No has introduit cap contrassenya");
            return; 
        }

        JPanel fields = new JPanel(new GridLayout(2, 1));
        JLabel nominfo = new JLabel("<html> Per registrar-te:<br>Torna a introduir la contrassenya</html>"); 
        JPasswordField valor = new JPasswordField(20); 
        fields.add(nominfo); 
        fields.add(valor); 
        String[] options2 = {"OK"}; 

        int resultat = JOptionPane.showOptionDialog(frameVista, fields, "Confirma Contrassenya", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]); 

        if (resultat == 0) {
            if ((new String(valor.getPassword()) == null) || (new String(valor.getPassword()).length() <= 0)) {
                JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut registrar\n" + "La contrassenya no és la mateixa");
                return; 
            }
            else {
                if (!(new String(valor.getPassword())).equals(new String(contrasenyaText.getPassword()))) {
                    JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut registrar\n" + "Les contrassenyes no coincideixen");
                    return; 
                }
            }
        }
        else {
            return; 
        }


        //si arribem aqui tot esta correcte. Falta comprovar que CtrlDomini pugui registrar

        try {
            iCtrlPresentacio.registraUsuari(usuariText.getText(), new String(contrasenyaText.getPassword())); 
            JOptionPane.showMessageDialog(frameVista, "Registre realitzat correctament\n" + "Iniciant sessio.....");
            vistaSesioIniciada();
        }
        catch (Exception exepcio) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut registrar\n" + exepcio.getMessage());
            //exepcio.printStackTrace();
            return; 
        }
    }

    /**
     * Tracta el fet que l'usuari hagi clicat al botó de iniciar sessió. És a dir, tot el procediment de inici de sessió de l'usuari
     * @param e
     */
    private void iniciaClicat (ActionEvent e) {
        if ((usuariText.getText() == null) || (usuariText.getText().length() <= 0)) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut iniciar\n" + "No has introduit cap nom d'usuari");
            return; 
        }

        if ((new String(contrasenyaText.getPassword()) == null) || (new String(contrasenyaText.getPassword()).length() <= 0)) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut iniciar\n" + "No has introduit cap contrassenya");
            return; 
        }

        try {
            iCtrlPresentacio.iniciaSesio(usuariText.getText(), new String(contrasenyaText.getPassword())); 
            JOptionPane.showMessageDialog(frameVista, "CORRECTE \n" + "Iniciant sessio.....");
            vistaSesioIniciada();
        }
        catch (Exception exepcio) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut iniciar sessio\n" + exepcio.getMessage());
            return; 
        }

    }

    /**
     * Comprova si s'han fet canvis en els TipusAtributs de la base de dades amb la que l'usuari vol treballar respecte que siguin compatibles amb la configuració interna guardada
     */
    private void comprovaCanvisArxiuItems() {

        try {
            if (iCtrlPresentacio.canvisTipusAtributsArxiuItems()) {
                String msg = "<html><b>ATENCIO: S'han detectat canvis a l'arxiu que conté informacio sobre els TipusAtributs<b>" + 
                "<br>Per tant, a continuacio, hauras d'especificar el següent:" +
                "<ul><li><b>Tipus<b> de cada TipusAtribut: <i>String, Int, Bool o Double<i>"
                + "<li>Els TipusAtribut <b>calculables<b>"
                + "<li>Quin TipusAtribut conté els atributs que fan referència al <b>títol<b> d'un ítem</ul><br>" +
                "<b>INFO: El fet que s'hagin detectat canvis implica que tots aquells usuaris registrats previament per a aquesta base de dades siguin eliminats</b></html>"; 
                JOptionPane.showMessageDialog(frameVista, msg);

                canviaConfiguracioTipusAtributs();

            }
            else {
                String msg = "<html><b>ATENCIO: NO s'han detectat canvis a l'arxiu que conté informacio sobre els TipusAtributs<b>" + 
                "<br>Pero si vols, pots tornar a especificar la configuració, és a dir, especificar la seguent informació: " +
                "<ul><li><b>Tipus<b> de cada TipusAtribut: <i>String, Int, Bool o Double<i>"
                + "<li>Els TipusAtribut <b>calculables<b>"
                + "<li>Quin TipusAtribut conté els atributs que fan referència al <b>títol<b> d'un ítem</ul>" +
                "<br> Vols tornar a especificar això? Si no ho fas, la configuració continuarà essent la mateixa que hi havia l'ultima vegada que es va executar el programa"
                + "<br><b>INFO: Tornar a especificar la configuracio implica que tots aquells usuaris registrats previament per a aquesta base de dades siguin eliminats</b></html>";
                int n = JOptionPane.showConfirmDialog(frameVista, msg, "Configuració TipusAtributs", JOptionPane.YES_NO_OPTION); 
                    
                if (n == JOptionPane.YES_OPTION) {
                    canviaConfiguracioTipusAtributs();
                }
            }

            JOptionPane.showMessageDialog(frameVista, "Només pots iniciar sessió amb un usuari que hagis creat anteriorment per a la base de dades (path) que has triat abans. Sinó, t'hauras de registrar, o tornar a registrar");
        }
        catch (Exception excepcio) {
            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'han pogut comprovar els canvis en els TipusAtribut. Tancant programa\n" + excepcio);
            System.exit(1);
        }



    }

    /**
     * En cas que s'hagin detectat canvis en els TipusAtributs de la base de dades amb la que l'usuari vol treballar respecte que siguin compatibles amb la configuració interna guardada, es preguntarà a l'usuari i es setejarà la nova configuració dels TipusAtributs
     */
    private void canviaConfiguracioTipusAtributs() throws Exception {


        HashSet<String> nomsTipusAtribut = iCtrlPresentacio.getNomTipusAtributs(); 
        HashMap<String, Pair<String, Boolean> > tipusICalculable = new HashMap<String, Pair<String, Boolean> > (); 
        String nomTipusAtributTitol; 
        String nomTipusAtributId; 
        String[] options = {"Següent"};

        for (String nomActual : nomsTipusAtribut) {

            JPanel fields = new JPanel(new GridLayout(3, 1));
            JLabel nom = new JLabel("TipusAtribut: " + nomActual); 
            JComboBox<String> comboBox1 = new JComboBox<>(new String[]{"String", "Int", "Bool", "Double"});
            JComboBox<String> comboBox2 = new JComboBox<>(new String[]{"Calculable", "NO Calculable"});
            comboBox2.setSelectedIndex(1);
            fields.add(nom); 
            fields.add(comboBox1);
            fields.add(comboBox2);

            int resultDialog; 

            do {
                resultDialog = JOptionPane.showOptionDialog(frameVista, fields, "Especifica les propietats del TipusAtribut", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (resultDialog == JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(frameVista, "NO POTS TANCAR AQUESTA FINESTRA: necesites especificar primer la configuracio");
                }
            } while (resultDialog == JOptionPane.CLOSED_OPTION );  

            Pair<String, Boolean> pairActual = new Pair<String, Boolean> (); 

            pairActual.setFirst((String) comboBox1.getSelectedItem());

            if (comboBox2.getSelectedItem().equals("NO Calculable")) {
                pairActual.setSecond(false);
            }
            else {
                pairActual.setSecond(true);
            }

            tipusICalculable.put(nomActual, pairActual); 


        }

        JPanel fields = new JPanel(new GridLayout(2, 1));
        JLabel nom = new JLabel("Especifica el TipusAtribut que conté els atributs que representen el títol (A DE SER UN TIPISATRIBUT DE TIPUS STRING)"); 
        JComboBox<String> comboBox1 = new JComboBox<>(nomsTipusAtribut.toArray(String[]::new));
        fields.add(nom); 
        fields.add(comboBox1); 

        int resultDialog; 

        do {
            resultDialog = JOptionPane.showOptionDialog(frameVista, fields, "Especifica el TipusAtribut que conte els titols dels items", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
            if (resultDialog == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(frameVista, "NO POTS TANCAR AQUESTA FINESTRA: necesites especificar primer la configuracio");
            }
        } while (resultDialog == JOptionPane.CLOSED_OPTION ); 

        nomTipusAtributTitol = (String) comboBox1.getSelectedItem(); 


        JPanel fields2 = new JPanel(new GridLayout(2, 1));
        JLabel nom2 = new JLabel("Especifica el TipusAtribut que conté els atributs que representen el ID (A DE SER UN TIPUSATRIBUT DE TIPUS ID)"); 
        JComboBox<String> comboBox2 = new JComboBox<>(nomsTipusAtribut.toArray(String[]::new));
        fields2.add(nom2); 
        fields2.add(comboBox2); 

        int resultDialog2; 

        do {
            resultDialog2 = JOptionPane.showOptionDialog(frameVista, fields2, "Especifica el TipusAtribut que conte els IDS dels items", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
            if (resultDialog == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(frameVista, "NO POTS TANCAR AQUESTA FINESTRA: necesites especificar primer la configuracio");
            }
        } while (resultDialog2 == JOptionPane.CLOSED_OPTION ); 

        nomTipusAtributId = (String) comboBox2.getSelectedItem(); 

        try {
            iCtrlPresentacio.setTipusAtributId(nomTipusAtributId); 
            iCtrlPresentacio.setTipusAtributTitol(nomTipusAtributTitol);
            iCtrlPresentacio.setTipusATipusAtributs(tipusICalculable);
        }
        catch (Exception excepcio) {
            JOptionPane.showMessageDialog(frameVista, "<html>NO S'HA POGUT ESPECIFICAR LA CONFIGURACIO DEGUT A: " + excepcio.getMessage() + "<br> TORNA-HO A PROVAR</html>"); 
            excepcio.printStackTrace();
            canviaConfiguracioTipusAtributs();
        }

    }



    /**
     * Crea una instància de la classe vistaSesioIniciada, perquè s'ha iniciat sessió 
     */
    private void vistaSesioIniciada () {
        frameVista.getContentPane().removeAll();
        frameVista.getContentPane().invalidate(); 
        frameVista.setContentPane(new VistaSesioIniciada(frameVista, iCtrlPresentacio).getJPanel());

    }




}