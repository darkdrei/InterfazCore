/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import core.Core;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import logica.data.ZipUtils;

/**
 * Estructura de codigo tomada de la pagina de documentacion y modificado para el uso en la tesis 
 * @author https://www.javatips.net/api/DockingFrames-master/docking-frames-common/src/bibliothek/gui/dock/common/DefaultSingleCDockable.java
 */
public class LectorArchivo extends JPanel implements ActionListener {

    private JLabel titulo;
    private JTextPane descripcion;
    private JButton cargar;
    private JButton cancelar;
    private JButton aceptar;
    private JTextArea area;
    private Xml xml;
    private File file_zip;
    private Core core;
    OSValidator os;

    public LectorArchivo(Core core) {
        this.core = core;
        this.setLayout(new GridBagLayout());
        JLabel titulo = new JLabel() {
            @Override
            public void updateUI() {
                setFont(null);
                super.updateUI();
                setFont(getFont().deriveFont(32f));
            }
        };
        titulo.setText("Cargado de Componentes");
        GridBagConstraints constrain = new GridBagConstraints();
        //this.getContentPane().add(titulo, new GridBagConstraints(1, 0, 4, 1, 0, 0, 0, 0, null, 0, 0));
        // El titulo de la pantalla
        GridBagConstraints constraints = new GridBagConstraints();
        // JButton boton1 = new JButton("Boton 1");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        /*constraints.weighty=1;
        constraints.anchor =GridBagConstraints.NORTH;*/
        constraints.fill = GridBagConstraints.NONE;
        this.add(titulo, constraints);
        constraints.weighty = 0;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;

        //Label para q no se desborde
        // El titulo contenido de area
        JLabel desborde = new JLabel("              ");
        //JScrollPane scrollPane = new JScrollPane(area);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 3;
        constraints.weightx = 0;
        constraints.weighty = 0;
        //constraints.weighty=1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        this.add(desborde, constraints);
        constraints.weighty = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;

        // El titulo contenido de area
        area = new JTextArea("Area de cargado del archivo");
        area.setSize(30, 30);
        JScrollPane scrollPane = new JScrollPane(area);
        constraints.fill = GridBagConstraints.BOTH;
        //this.add(area, constraints);
        add(area, new GridBagConstraints(2, 1, 2, 1, 1.0, 1.0,
                GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        constraints.weighty = 0;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;

        //Boton de cargar archivo
        cargar = new JButton("Cargar Archivo");
        cargar.addActionListener(this);
        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        /*constraints.weighty=1;
        constraints.anchor =GridBagConstraints.NORTH;*/
        constraints.fill = GridBagConstraints.FIRST_LINE_END;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        //this.add(cargar, constraints);
        constraints.weighty = 0;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        add(cargar, new GridBagConstraints(4, 1, 1, 1, 0, 0,
                GridBagConstraints.FIRST_LINE_END, GridBagConstraints.NORTHEAST,
                new Insets(0, 0, 0, 0), 0, 0));

        //Boton de cargar archivo
        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.fill = GridBagConstraints.NONE;
        this.add(cancelar, constraints);
        constraints.weighty = 0;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        JLabel desborde2 = new JLabel("              ");
        add(desborde2, new GridBagConstraints(4, 2, 1, 1, 0, 0,
                GridBagConstraints.FIRST_LINE_END, GridBagConstraints.NORTH,
                new Insets(0, 0, 0, 0), 0, 0));

        //Boton de cargar archivo
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //constraints.weighty=1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 0;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        this.add(aceptar, constraints);
        this.changeButtonStatus(false);
    }

    private void changeButtonStatus(boolean enabled) {
        this.aceptar.setEnabled(enabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path_base_extract = "";
        if (os.getOS().equals("win")) {
            path_base_extract = "src\\configuracion\\jars";
        } else {
            path_base_extract = "src/configuracion/jars";
        }
        if (e.getSource() == aceptar) {
            area.setText("precionar aceptar");
            this.changeButtonStatus(false);

            if (xml != null) {
                WriteComponenXml write_xml = new WriteComponenXml();
                ZipUtils.extract(file_zip, new File(path_base_extract));
                write_xml.writeFile("src/configuracion/xml_configuracion.xml", xml);
            } else {
                JOptionPane.showMessageDialog(this, "Debe cargar un modulo.", "Cargar Modulo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == cancelar) {
            area.setText("precionar cancelar");
        } else if (e.getSource() == cargar) {
            area.setText("precionar Cargar");
            this.changeButtonStatus(true);
            JFileChooser f = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("zip & ZIP", "zip");
            f.setFileFilter(filter);
            int op = f.showOpenDialog(f);
            if (op == JFileChooser.APPROVE_OPTION) {
                Zip z = new Zip();
                Object[] r = z.validarArchivoGrup(f.getSelectedFile());
                if (!((boolean) r[0])) {
                    area.setText(((String) r[1]));
                } else {
                    file_zip = f.getSelectedFile();
                    File[] files = z.getFiles(f.getSelectedFile());
                    int i = 0, index_xml = -1, index_jar = -1;
                    stop:
                    for (i = 0; i < files.length; i++) {
                        if (files[i].getName().contains("xml")) {
                            index_xml = i;
                        } else if (files[i].getName().contains("jar")) {
                            index_jar = i;
                        }
                        if (index_jar != -1 && index_xml != -1) {
                            LectorXml read_xml = new LectorXml();
                            read_xml.setFile(files[index_xml]);
                            read_xml.readNodeFile();
                            area.setText(read_xml.toString());
                            xml = read_xml.getXml();
                            ListComponenXml list = new ListComponenXml();
                            String path_base;
                            if (os.getOS().equals("win")) {
                                path_base = "src\\configuracion\\xml_configuracion.xml";
                            } else {
                                path_base = "src/configuracion/xml_configuracion.xml";
                            }
                            list.loadingFile(path_base);
                            list.readNodeFile();
                            xml.getRuta().setNombre(files[index_xml].getName());
                            xml.getRuta().setDireccionXml(path_base_extract + (os.getOS().equals("win") ? "\\" : "/") + files[index_xml].getName());
                            xml.getRuta().setDireccionJar(path_base_extract + (os.getOS().equals("win") ? "\\" : "/") + files[index_jar].getName());
                            break stop;
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "La extensión debe ser ZIP.", "Debe seleccionar un archivo con extension ZIP.", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}
