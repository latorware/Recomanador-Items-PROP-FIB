package presentacio;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class panellRecomanacio {
    private VistaSesioIniciada iVistaSesioIniciada; 
    private CtrlPresentacio iCtrlPresentacio;
    private JFrame frameVista;
    private boolean almenysUnaValoracioFeta;
    private JPanel panellError;
    private JLabel labelError;
    private JPanel panellTaula;
    private JLabel titolPanellRecomanacions;
    private JLabel info;
    private JPanel panellSuperior;
    private JList<String> list1;
    private String algorismeQueSutilitza; 

    /**
     * Constructora de la classe
     * @param frameVista frame principal de l'aplicació
     * @param iCtrlPresentacio instància de controlador de presentació
     * @param vistaSesioIniciada instància de la classe que conté la vista que engloba tots els elements de la sessió iniciada
     */
    public panellRecomanacio(JFrame frameVista, CtrlPresentacio iCtrlPresentacio, VistaSesioIniciada vistaSesioIniciada) throws Exception {
        iVistaSesioIniciada = vistaSesioIniciada; 
        this.iCtrlPresentacio = iCtrlPresentacio;
        this.frameVista = frameVista;
        if (this.iCtrlPresentacio.almenysUnaValoracioFeta()) {
            this.almenysUnaValoracioFeta = true;
            inicialitzaPanellTaula();
        } else {
            this.almenysUnaValoracioFeta = false;
            inicialitzaPanellError();
        }

    }

    /**
     * @return retorna el panell de la classe. És a dir, el panell que fa referència a la consulta de recomanacions
     */
    public JPanel getJPanel() {
        if (almenysUnaValoracioFeta) {
            return panellTaula;
        } else {
            return panellError;
        }
    }

    /**
     * inicialitza la classe amb un panell en que s'informa a l'usuari que té sessió iniciada que per obtenir valoracions primer a de fer una valoració com a mínim. 
     */
    private void inicialitzaPanellError() {
        panellError = new JPanel();
        panellError.setLayout(new GridLayout(1, 1));
        labelError = new JLabel("<html>Per obtenir recomanacions necesites almenys fer una <b>valoració</b> sobre un item<br>Pots crear una <b>valoracio amb el boto +</b></html>");
        labelError.setHorizontalAlignment(SwingConstants.CENTER);
        labelError.setFont(new Font(null, Font.PLAIN, 20));
        panellError.add(labelError);
    }

    /**
     * inicialitza la classe amb un panell en que és format llista i es mostren els noms dels ítems que s'han recomanat a l'usuari que té la sessió que està iniciada. L'usuari podrà fer doble click per consultar els items que vol desitjar    consultar
     */
    private void inicialitzaPanellTaula() throws Exception {
        String[] options = {"COLFiltering", "CBFiltering", "Híbrid"};
        int resultDialog; 
        do {
            resultDialog = JOptionPane.showOptionDialog(frameVista, "Quin algorisme vols triar per fer la recomanació?", "Tipus recomanacio", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
            if (resultDialog == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(frameVista, "Has de triar un algorisme");
            }
        } while (resultDialog == JOptionPane.CLOSED_OPTION );  

        algorismeQueSutilitza = options[resultDialog]; 

        panellTaula = new JPanel();
        panellTaula.setLayout(new BorderLayout(0, 0));
        panellTaula.setBackground(new Color(-13487566));
        panellSuperior = new JPanel();
        panellSuperior.setLayout(new BorderLayout(0, 5));
        panellSuperior.setBackground(new Color(-12828863));
        panellTaula.add(panellSuperior, BorderLayout.NORTH);
        titolPanellRecomanacions = new JLabel();
        titolPanellRecomanacions.setBackground(new Color(-12828863));
        //Font titolPanellRecomanacionsFont = this.$$$getFont$$$(null, Font.BOLD, 14, titolPanellRecomanacions.getFont());
        //if (titolPanellRecomanacionsFont != null) titolPanellRecomanacions.setFont(titolPanellRecomanacionsFont);
        titolPanellRecomanacions.setFont(new Font(null, Font.BOLD, 14)); 
        titolPanellRecomanacions.setForeground(new Color(-1));
        titolPanellRecomanacions.setHorizontalAlignment(0);
        titolPanellRecomanacions.setText("Recomanacions");
        panellSuperior.add(titolPanellRecomanacions, BorderLayout.NORTH);
        info = new JLabel();
        //Font infoFont = this.$$$getFont$$$(null, -1, 12, info.getFont());
        //if (infoFont != null) info.setFont(infoFont);
        info.setFont(new Font(null, Font.PLAIN, 12)); 
        info.setForeground(new Color(-6908266));
        info.setHorizontalAlignment(0);
        info.setText("Per consultar un item, fes doble click sobre aquest");
        panellSuperior.add(info, BorderLayout.SOUTH);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-13487551));
        panellTaula.add(scrollPane1, BorderLayout.CENTER);


        ArrayList<String> nomsRecomanacions = null; 
        if (algorismeQueSutilitza.equals("CBFiltering")) {
            nomsRecomanacions = iCtrlPresentacio.nomsItemsRecomanatsCB();
        }
        else if (algorismeQueSutilitza.equals("COLFiltering")) {
            nomsRecomanacions = iCtrlPresentacio.nomsItemsRecomanatsCOL(); 
        }
        else if (algorismeQueSutilitza.equals("Híbrid")) {
            nomsRecomanacions = iCtrlPresentacio.nomsItemsRecomanatsHYBRID(); 
        }
        list1 = new JList<String>(nomsRecomanacions.toArray(String[]::new));
        list1.setFont(new Font(null, Font.PLAIN, 15));
        //DefaultListCellRenderer renderer = (DefaultListCellRenderer) list1.getCellRenderer();
        //renderer.setHorizontalAlignment(SwingConstants.CENTER); 
        list1.setBackground(new Color(-13487566));
        list1.setLayoutOrientation(0);
        list1.setForeground(new Color(197, 197, 197));
        list1.setSelectionBackground(new Color(30,30,30));
        list1.setSelectionForeground(new Color(197, 197, 197));
        scrollPane1.setViewportView(list1);

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList<String> list = (JList<String>)evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    //int index = list.locationToIndex(evt.getPoint());
                    String nomItemSeleccionat = list.getSelectedValue(); 
                    
                    int n = JOptionPane.showConfirmDialog(frameVista, "<html>Has seleccionat el ítem: " + nomItemSeleccionat + "<br>" + " Vols consultar aquest ítem?</html>", "consultar ítem", JOptionPane.YES_NO_OPTION); 
                    
                    if (n == JOptionPane.YES_OPTION) {
                        iVistaSesioIniciada.canviaAPAnellCerca(nomItemSeleccionat);
                    }

                }
            }
        });
    }

}
