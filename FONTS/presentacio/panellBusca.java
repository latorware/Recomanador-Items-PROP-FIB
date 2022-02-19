package presentacio;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class panellBusca {

    private VistaSesioIniciada iVistaSesioIniciada; 
    private CtrlPresentacio iCtrlPresentacio;
    private JFrame frameVista;
    boolean itemTrobat;
    private String nomItem; 
    private JPanel panellError; 
    private JLabel labelError; 


    private JPanel panellInfoItem; 
    private JPanel panellSuperior;
    private JLabel titolItem; 
    private JLabel info; 
    private JPanel panellSuperiorCentral; 
    private JPanel panellSuperiorCentralDRET; 
    private JPanel panellCentral; 
    private JLabel infoAtributs;
    private JButton eliminaItem; 
    private JButton creaValoracio; 
    private JTable table; 

    /**
     * constructora de la classe
     * @param frameVista frame principal de l'aplicació
     * @param iCtrlPresentacio instància de controlador de presentació
     * @param vistaSesioIniciada instància de la classe que conté la vista que engloba tots els elements de la sessió iniciada
     * @param nomItem nom de l'ítem que es desitja cercar
     */
    public panellBusca(JFrame frameVista, CtrlPresentacio iCtrlPresentacio, VistaSesioIniciada vistaSesioIniciada, String nomItem) throws Exception {
        iVistaSesioIniciada = vistaSesioIniciada; 
        this.iCtrlPresentacio = iCtrlPresentacio; 
        this.frameVista = frameVista; 
        this.nomItem = nomItem; 

        if (this.iCtrlPresentacio.existeixItem(this.nomItem)) {
            itemTrobat = true; 
            inicialitzaPanellInfoItem();
        }
        else {
            itemTrobat = false; 
            inicialitzaPanellError();
        }

    }


    /**
     * 
     * @return retorna el panell de la classe. És a dir, el panell que fa referència a la consulta de items
     */
    public JPanel getJPanel() {
        if (itemTrobat) {

            return panellInfoItem; 
        }
        else {
            return panellError; 
        }
    }

    /**
     * inicialitza la classe amb un panell que mostra un missatge en que s'informa a l'usuari que ha iniciat la sessió que l'ítem no s'ha trobat
     */
    private void inicialitzaPanellError() {
        panellError = new JPanel();
        panellError.setLayout(new GridLayout(1, 1));
        labelError = new JLabel("<html>El ítem amb el nom: <b>" + nomItem + "</b> no s'ha trobat.<br>Pots intentar buscar un altre ítem, o crear un amb aquest nom</html>");
        labelError.setHorizontalAlignment(SwingConstants.CENTER);
        labelError.setFont(new Font(null, Font.PLAIN, 20));
        panellError.add(labelError);
    }

    /**
     * inicialitza la classe amb un panell que és una taula on es mostra per a cada TipusAtribut definit per l'ítem que es vol buscar el seu nom, i l'atribut/s d'aquests TipusAtribut
     */
    private void inicialitzaPanellInfoItem() throws Exception {
        panellInfoItem = new JPanel();
        panellInfoItem.setLayout(new BorderLayout(0, 0));
        panellInfoItem.setBackground(new Color(-13487566));
        panellSuperior = new JPanel();
        panellSuperior.setLayout(new BorderLayout(0, 5));
        panellSuperior.setBackground(new Color(-12828863));
        panellInfoItem.add(panellSuperior, BorderLayout.NORTH);
        titolItem = new JLabel();
        titolItem.setBackground(new Color(-12828863));
        //Font titolItemFont = this.$$$getFont$$$(null, Font.BOLD, 14, titolItem.getFont());
        //if (titolItemFont != null) titolItem.setFont(titolItemFont);
        titolItem.setFont(new Font(null, Font.BOLD, 14)); 
        titolItem.setForeground(new Color(-1));
        titolItem.setHorizontalAlignment(0);
        titolItem.setText("TITOL: " + nomItem);
        panellSuperior.add(titolItem, BorderLayout.NORTH);
        info = new JLabel();
        //Font infoFont = this.$$$getFont$$$(null, -1, 12, info.getFont());
        //if (infoFont != null) info.setFont(infoFont);
        info.setFont(new Font(null, Font.PLAIN, 12)); 
        info.setForeground(new Color(-6908266));
        info.setHorizontalAlignment(0);
        info.setText("ID:" + iCtrlPresentacio.idItem(nomItem));
        panellSuperior.add(info, BorderLayout.SOUTH);

        panellCentral = new JPanel(); 
        panellCentral.setLayout(new BorderLayout(0,5));
        panellCentral.setBackground(new Color(50,50,50));
        panellInfoItem.add(panellCentral, BorderLayout.CENTER); 

        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-13487551));
        panellCentral.add(scrollPane1, BorderLayout.CENTER);


        panellSuperiorCentral = new JPanel(); 
        panellSuperiorCentral.setLayout(new BorderLayout(0,0));
        panellSuperiorCentral.setBackground(new Color(25,25,25));
        panellCentral.add(panellSuperiorCentral, BorderLayout.NORTH); 

        infoAtributs = new JLabel(); 
        infoAtributs.setForeground(new Color(210, 210, 210));
        infoAtributs.setBackground(new Color(25, 25, 25));
        infoAtributs.setFont(new Font(null, Font.PLAIN, 12));
        infoAtributs.setText("A continuació, es mostren els atributs de el ítem: ");
        panellSuperiorCentral.add(infoAtributs, BorderLayout.WEST);

        panellSuperiorCentralDRET = new JPanel(); 
        panellSuperiorCentralDRET.setLayout(new BorderLayout(0,0));
        panellSuperiorCentralDRET.setBackground(new Color(25,25,25));
        panellSuperiorCentral.add(panellSuperiorCentralDRET, BorderLayout.EAST); 

        creaValoracio = new JButton(); 
        creaValoracio.setForeground(new Color(150, 150, 150));
        creaValoracio.setBackground(new Color(0,0,0));
        creaValoracio.setFont(new Font(null, Font.PLAIN, 12)); 
        creaValoracio.setText("Crea valoracio sobre aquest item");
        panellSuperiorCentralDRET.add(creaValoracio, BorderLayout.WEST); 

        eliminaItem = new JButton(); 
        eliminaItem.setForeground(new Color(150, 150, 150));
        eliminaItem.setBackground(new Color(0,0,0));
        eliminaItem.setFont(new Font(null, Font.PLAIN, 12)); 
        eliminaItem.setText("Elimina aquest ítem");
        panellSuperiorCentralDRET.add(eliminaItem, BorderLayout.EAST); 

        String[] columnNames = {"TipusAtribut", "Atribut/s"}; 
        HashMap<String, String> valorsTipusAtributdeItem = iCtrlPresentacio.getValorsTipusAtributDeItem(nomItem); 
        String[][] data = new String[valorsTipusAtributdeItem.size()][];
        int iterador = 0; 
        for (HashMap.Entry<String, String> entry : valorsTipusAtributdeItem.entrySet()) {
            data[iterador] = new String[]{entry.getKey(), entry.getValue()}; 
            iterador++; 
        }
        

        table = new JTable(data, columnNames); 
        table.setFont(new Font(null, Font.PLAIN, 15));
        table.setBackground(new Color(50,50,50));
        table.setSelectionForeground(new Color(197,197,197));
        table.setSelectionBackground(new Color(30,30,30));
        table.setForeground(new Color(190,190,190));
        scrollPane1.setViewportView(table);
        table.setDefaultEditor(Object.class, null);

        JOptionPane.showMessageDialog(frameVista, "Si hi ha algun atribut que sigui molt llarg i no el vegis, fes doble click sobre ell per veure'l sencer");

        eliminaItem.addActionListener(new java.awt.event.ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                tractaelimina(e); 
                
            }

        }); 

        creaValoracio.addActionListener(new java.awt.event.ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                tractacrea(e); 
                
            }

        }); 

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable table1 = (JTable)evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    //int index = list.locationToIndex(evt.getPoint());
                    int row = table1.getSelectedRow(); 
                    String atributSeleccionat = (String) table1.getValueAt(row, 1); 
                    String TipusAtribut = (String) table1.getValueAt(row, 0); 
                    
                    JPanel fields = new JPanel(new GridLayout(2, 1));
                    JLabel nominfo = new JLabel("Atribut/s del TipusAtribut: " + TipusAtribut); 
                    JLabel valor = new JLabel(atributSeleccionat); 
                    fields.add(nominfo); 
                    fields.add(valor); 
                    String[] options2 = {"OK"}; 
            
                    JOptionPane.showOptionDialog(frameVista, fields, "Consulta atribut de un TipusAtribut", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]); 

                }
            }
        });


    }

    /**
     * Funció que fa el tractament quan l'usuari clica al botó d'eliminar el ítem que s'està consultant. Per tant, elimina el ítem
     */
    private void tractaelimina(ActionEvent e) {
        int n = JOptionPane.showConfirmDialog(frameVista, "<html>Estas SEGUR que vols <b>ELIMINAR</b> aquest ítem??</html>", "Elimina ítem", JOptionPane.YES_NO_OPTION); 
                    
        if (n == JOptionPane.YES_OPTION) {
            try {
                iCtrlPresentacio.eliminaItem(nomItem);
            }
            catch (Exception exccepcio) {
                JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut eliminar el item" + exccepcio.getMessage());
                return; 
            }

            JOptionPane.showMessageDialog(frameVista, "FET. Ara seras traslladat a la vista de recomanacions");
            iVistaSesioIniciada.canviaAPanellRecomanacio();
            
        }
    }   

    /**
     * Funció que fa el tractament quan l'usuari clica al botó de crear una valoració per a l'ítem que s'està consultant. Per tant, crea una valoració
     */
    private void tractacrea(ActionEvent e) {
        try {

            JPanel fields = new JPanel(new GridLayout(6, 1));
            JLabel nomTitolInfo = new JLabel("<html>Especifica el TITOL de l'ítem del que vols crear una valoracio: </html>"); 
            JTextField nomTitol = new JTextField(20); 
            nomTitol.setText(nomItem);
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
