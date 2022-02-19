package presentacio;

import javax.swing.*;

import utils.Pair;

import java.awt.*;
import java.util.HashMap;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class panellAdministraValoracions {
    private VistaSesioIniciada iVistaSesioIniciada; 
    private CtrlPresentacio iCtrlPresentacio;
    private JFrame frameVista;
    private boolean almenysUnaValoracioFeta;
    private JPanel panellError;
    private JLabel labelError;
    private JPanel panellTaula;
    private JLabel titolPanellValoracions;
    private JLabel info;
    private JPanel panellSuperior;
    private JTable table; 

    /**
     * Constructora de la classe
     * @param frameVista frame principal de l'aplicació
     * @param iCtrlPresentacio instància de controlador de presentació
     * @param vistaSesioIniciada instància de la classe que conté la vista que engloba tots els elements de la sessió iniciada
     */
    public panellAdministraValoracions(JFrame frameVista, CtrlPresentacio iCtrlPresentacio, VistaSesioIniciada vistaSesioIniciada) throws Exception {
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
     * 
     * @return retorna el panell de la classe. És a dir, el panell que fa referència a l'administració de les valoracions. 
     */
    public JPanel getJPanel() {
        if (almenysUnaValoracioFeta) {
            return panellTaula;
        } else {
            return panellError;
        }
    }

    /**
     * s'inicialitza la classe amb un panell que té un missatge en que s'informa a l'usuari que no té cap valoració feta
     */
    private void inicialitzaPanellError() {
        panellError = new JPanel();
        panellError.setLayout(new GridLayout(1, 1));
        labelError = new JLabel("<html>Encara no has fet cap valoracio. <br> Per crear una valoracio, clica al boto acció +</html>");
        labelError.setHorizontalAlignment(SwingConstants.CENTER);
        labelError.setFont(new Font(null, Font.PLAIN, 20));
        panellError.add(labelError);
    }

    /**
     * s'inicialitza la classe amb un panell que té una taula amb totes les valoracions de l'usuari que té sessió iniciada
     */
    private void inicialitzaPanellTaula() throws Exception {
        
        panellTaula = new JPanel();
        panellTaula.setLayout(new BorderLayout(0, 0));
        panellTaula.setBackground(new Color(-13487566));
        panellSuperior = new JPanel();
        panellSuperior.setLayout(new BorderLayout(0, 5));
        panellSuperior.setBackground(new Color(-12828863));
        panellTaula.add(panellSuperior, BorderLayout.NORTH);
        titolPanellValoracions = new JLabel();
        titolPanellValoracions.setBackground(new Color(-12828863));
        //Font titolPanellRecomanacionsFont = this.$$$getFont$$$(null, Font.BOLD, 14, titolPanellRecomanacions.getFont());
        //if (titolPanellRecomanacionsFont != null) titolPanellRecomanacions.setFont(titolPanellRecomanacionsFont);
        titolPanellValoracions.setFont(new Font(null, Font.BOLD, 14)); 
        titolPanellValoracions.setForeground(new Color(-1));
        titolPanellValoracions.setHorizontalAlignment(0);
        titolPanellValoracions.setText("Les teves valoracions");
        panellSuperior.add(titolPanellValoracions, BorderLayout.NORTH);
        info = new JLabel();
        //Font infoFont = this.$$$getFont$$$(null, -1, 12, info.getFont());
        //if (infoFont != null) info.setFont(infoFont);
        info.setFont(new Font(null, Font.PLAIN, 12)); 
        info.setForeground(new Color(-6908266));
        info.setHorizontalAlignment(0);
        info.setText("Per eliminar una valoracio   , fes doble click sobre la valoracio que vols...");
        panellSuperior.add(info, BorderLayout.SOUTH);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-13487551));
        panellTaula.add(scrollPane1, BorderLayout.CENTER);


        String[] columnNames = {"Titol Item", "Puntuacio", "Comentari"}; 
        HashMap<String, Pair<Integer, String> > valoracions = iCtrlPresentacio.consultaValoracionsUsuari(); 
        String[][] data = new String[valoracions.size()][];
        int iterador = 0; 
        for (HashMap.Entry<String, Pair<Integer, String> > entry : valoracions.entrySet()) {
            if (entry.getValue().second() == null) {
                data[iterador] = new String[]{entry.getKey(), "" + entry.getValue().first(), ""}; 
            }
            else {
                data[iterador] = new String[]{entry.getKey(), "" + entry.getValue().first(), entry.getValue().second()}; 
            }


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

        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable table1 = (JTable)evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    //int index = list.locationToIndex(evt.getPoint());
                    int row = table1.getSelectedRow(); 
                    String nomItemSeleccionat = (String) table1.getValueAt(row, 0); 
                    
                    int n = JOptionPane.showConfirmDialog(frameVista, "<html>Has seleccionat la valoracio de litem:  " + nomItemSeleccionat + "<br>" + " Vols eliminar aquesta valoració?</html>", "eliminar valoracio", JOptionPane.YES_NO_OPTION); 
                    
                    if (n == JOptionPane.YES_OPTION) {
                        try {
                            iCtrlPresentacio.eliminaValoracio(nomItemSeleccionat);
                            JOptionPane.showMessageDialog(frameVista, "FET.");
                            iVistaSesioIniciada.canviaPanellAdministraValoracions();
                        }

                        catch (Exception exepcio) {
                            JOptionPane.showMessageDialog(frameVista, "ERROR: No s'ha pogut eliminar la valoracio seleccionada\n" + exepcio.getMessage());
                            return;
                        }
                    }

                }
            }
        });
        
    }






















    }
