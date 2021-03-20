/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author dark
 */
import interfazcore.ListaComponentes;
import interfazcore.ExecuteComponent;
import interfazcore.EliminarComponente;
import bibliothek.gui.DockController;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.common.action.CAction;
import bibliothek.gui.dock.common.intern.CDockable;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import logica.BasePanel;
import logica.CargarArchivoArbol;
import logica.CodePanel;
import logica.CopyCodeAction;
import logica.DesArbol;
import logica.LectorArchivo;
import logica.ListComponenXml;
import logica.ModelTree;
import logica.OSValidator;
import logica.ValidXml;
import logica.Xml;
import java.util.ArrayList;

public class Core
        extends JFrame {

    private BasePanel currentSelection;
    private CodePanel currentCode;
    private LectorArchivo lector_archivo;
    private JTree tree;
    private DefaultSingleCDockable lectorSeleccionDockable;
    private CGrid layout;
    public CargarArchivoArbol cargador_de_archivo;
    public ListComponenXml list = new ListComponenXml();
    public String path = "";
    public OSValidator os;
    public CControl control;
    public ArrayList<CodePanel> currentCodes = new ArrayList();
    public DefaultSingleCDockables componentes_de_arbol = new DefaultSingleCDockables();

    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, UnsupportedLookAndFeelException {
        DockController.disableCoreWarning();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Core readme = new Core();
        readme.setDefaultCloseOperation(3);
        readme.setSize(800, 600);
        readme.setLocationRelativeTo(null);
        OSValidator os = new OSValidator();
        String path = "";
        if (os.getOS().equals("win")) {
            path = "src\\ima\\icono.png";
        } else {
            path = "src/ima/icono.png";
        }
        try {
            Thread.sleep(2000);
            Image image = new ImageIcon(path).getImage();
            readme.setIconImage(image);
        } catch (Exception e) {

        }

        readme.setVisible(true);
    }

    public Core()
            throws InstantiationException, IllegalAccessException {
        setTitle("Interfaz - Integradora");
        String warningMsg = "";

        this.control = new CControl(this);
        this.control.setTheme("eclipse");
        add(this.control.getContentArea());

        if (os.getOS().equals("win")) {
            path = "src\\configuracion\\xml_configuracion.xml";
        } else {
            path = "src/configuracion/xml_configuracion.xml";
        }
        ValidXml vxml = new ValidXml();
        boolean exisFile = vxml.exisFile(path);
        boolean validExtencion = vxml.validExtencion(path);
        if (exisFile & validExtencion) {
            list.loadingFile(path);
            list.readNodeFile();
        } else {
            JDialog d = new JDialog();
            d.setTitle("El archivo de configuracion ha sido alterado");
            d.setVisible(true);
        }
        /*
         Agregar Menu
         */
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        menubar.add(menu);
        JMenuItem addComponente = new JMenuItem("Agregar Componente");
        menu.add(addComponente);
        JMenuItem deleteComponentes = new JMenuItem("Eliminar Componente");
        menu.add(deleteComponentes);

        deleteComponentes.addActionListener(new ActionListener() {
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog eliminar = new EliminarComponente(Core.this, list, exisFile, validExtencion, true);
                eliminar.setVisible(true);
            }

        });

        JMenu componente = new JMenu("Componentes");
        menubar.add(componente);

        JMenu help = new JMenu("Ayuda");
        menubar.add(help);
        JMenuItem acercade = new JMenuItem("Acerca de");
        help.add(acercade);

        setJMenuBar(menubar);
        addComponente.addActionListener(new ActionListener() {
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                lectorVisible();
            }

        });

        JMenuItem Lista = new JMenuItem("Lista de Componentes");
        componente.add(Lista);

        Lista.addActionListener(new ActionListener() {
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog vista = new ListaComponentes(Core.this, list, exisFile, validExtencion, warningMsg, true);
                vista.setVisible(true);
            }

        });
        /**
         * **************************************
         */

        this.layout = new CGrid(control);

        /**
         * **********************************************************************
         */
        /*                  CODIGFO DE CARGADO DE ARCHIVO                        */
        lector_archivo = new LectorArchivo(this, list);
        this.lectorSeleccionDockable = new DefaultSingleCDockable("selection", "Agregar componente");
        this.lectorSeleccionDockable.setLayout(new BorderLayout());
        this.lectorSeleccionDockable.add(lector_archivo, BorderLayout.CENTER);
        this.lectorSeleccionDockable.setCloseable(true);
        this.layout.add(30, 0, 70, 100, this.lectorSeleccionDockable);

        /**
         * **********************************************************************
         */
        this.currentSelection = new BasePanel();
        DefaultSingleCDockable currentSelectionDockable = new DefaultSingleCDockable("selection", "Agregar componente", new CAction[0]);
        currentSelectionDockable.setLayout(new BorderLayout());
        currentSelectionDockable.add(this.currentSelection, "Center");
        currentSelectionDockable.setCloseable(false);
        // layout.add(30.0D, 0.0D, 70.0D, 100.0D, new CDockable[] { currentSelectionDockable });
        /**
         * ********************************************************************
         */

        //layout.select(30.0D, 0.0D, 70.0D, 100.0D, currentSelectionDockable);
        /**
         * **********************************************************************
         */
        // Construccion del arbol
        DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode("Archivo");
        DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
        tree = new JTree(modelo);
        // Construccion de los datos del arbol
        /**
         * **********************************************************************
         */

        DefaultSingleCDockable listDockable = new DefaultSingleCDockable("list", "Tutorials", new CAction[0]);
        listDockable.setLayout(new BorderLayout());
        //listDockable.add(new JScrollPane(tutorialsTree), "Center");
        listDockable.add(new JScrollPane(tree), "Center");

        listDockable.setCloseable(false);
        //listDockable.addAction(new CopyCodeAction(this.currentCode));
        cargador_de_archivo = new CargarArchivoArbol(this);
        listDockable.addAction(cargador_de_archivo);
        tree.addTreeSelectionListener((TreeSelectionEvent e) -> {
            TreePath path = tree.getSelectionPath();
            String name_file = path.getPathComponent(path.getPath().length - 1).toString();
            if (!componentes_de_arbol.existCeldaDefaultSingleCDockable(name_file)) {
                DesArbol desc = cargador_de_archivo.getNodo(path.getPathComponent(path.getPath().length - 1).toString());
                CodePanel temporal_codepanel = new CodePanel();
                DefaultSingleCDockable currentCodeDockable_ = new DefaultSingleCDockable(name_file, name_file, new CAction[0]);
                currentCodeDockable_.setLayout(new BorderLayout());
                currentCodeDockable_.add(temporal_codepanel.toComponent(), "Center");
                currentCodeDockable_.setCloseable(false);
                if (!control.getController().isRestrictedEnvironment()) {
                    currentCodeDockable_.addAction(new CopyCodeAction(temporal_codepanel));
                }
                this.layout.add(30.0D, 0.0D, 70.0D, 100.0D, new CDockable[]{currentCodeDockable_});
                temporal_codepanel.setCode(cargador_de_archivo.producirContenido(desc));
                componentes_de_arbol.add(new CeldaDefaultSingleCDockable(name_file, currentCodeDockable_));
                control.getContentArea().deploy(this.layout);
            } else {
                CeldaDefaultSingleCDockable celda_componen_arbol = componentes_de_arbol.getCeldaDefaultSingleCDockable(name_file);
                celda_componen_arbol.getDefault_single_dockable().setVisible(true);
                celda_componen_arbol.getDefault_single_dockable().toFront();
            }
        });
        this.layout.add(0.0D, 0.0D, 30.0D, 100.0D, new CDockable[]{listDockable});
        if (os.getOS().equals("win")) {
            path = "src\\configuracion\\xml_configuracion.xml";
        } else {
            path = "src/configuracion/xml_configuracion.xml";
        }

        if (exisFile & validExtencion) {
            /* Ejecutador de codigo */
            this.loadMultiDockable();
        }

        /**
         * *****************************
         * * * CARGAR EN CODE * * *
         */
        this.deploy();
    }

    private void loadMultiDockable() {
        for (Xml x : list.getXmls()) {
            this.addNewDockable(x, false);
        }
    }

    public void addNewDockable(Xml x, Boolean isVisible) {
        DefaultSingleCDockable exist = (DefaultSingleCDockable) control.getSingleDockable(Integer.toString(x.getId()));
        if (x.getStatus().getActive() && exist == null) {
            ExecuteComponent executecomponene = new ExecuteComponent(x);
            DefaultSingleCDockable executecomponene_dockable = new DefaultSingleCDockable(Integer.toString(x.getId()), x.getAutor().getNombre(), new CAction[0]);
            executecomponene_dockable.setLayout(new BorderLayout());
            executecomponene_dockable.add(executecomponene, BorderLayout.CENTER);
            executecomponene_dockable.setCloseable(true);
            this.layout.add(30.0D, 0.0D, 70.0D, 100.0D, new CDockable[]{executecomponene_dockable});
            if (isVisible) {
                executecomponene_dockable.setVisible(true);
            }
        } else {
            exist.setVisible(true);
        }
    }

    public void deleteDockable(Xml x) {
        SingleCDockable single = (DefaultSingleCDockable) control.getSingleDockable(Integer.toString(x.getId()));
        if (single != null) {
            single.setVisible(false);
        }
    }

    public void showDockable(Xml x) {
        SingleCDockable single = (DefaultSingleCDockable) control.getSingleDockable(Integer.toString(x.getId()));
        if (single != null) {
            single.setVisible(true);
        }
    }

    public void deploy() {
        control.getContentArea().deploy(this.layout);
    }

    public JTree getTree() {
        return tree;
    }

    public void lectorVisible() {
        this.lectorSeleccionDockable.setVisible(true);
        lectorSeleccionDockable.toFront();
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }

    private void select(ModelTree.Node node) {
        if (node == null) {
            this.currentSelection.set(null, null, null, null);
        } else {
            try {
                this.currentSelection.set(node.getTitle(), node.getDescription(), node.getImage(), node.getMainClass());
                this.currentCode.setCode(node.getCode());
            } catch (IOException e) {
                e.printStackTrace();
                this.currentCode.setCode("");
            }
        }
    }

    public class CeldaDefaultSingleCDockable {

        private String uuid;
        private DefaultSingleCDockable default_single_dockable;

        public CeldaDefaultSingleCDockable(String uuid, DefaultSingleCDockable default_single_dockable) {
            this.uuid = uuid;
            this.default_single_dockable = default_single_dockable;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public DefaultSingleCDockable getDefault_single_dockable() {
            return default_single_dockable;
        }

        public void setDefault_single_dockable(DefaultSingleCDockable default_single_dockable) {
            this.default_single_dockable = default_single_dockable;
        }
    }

    public class DefaultSingleCDockables {

        private ArrayList<CeldaDefaultSingleCDockable> default_single_cdockables;

        public DefaultSingleCDockables() {
            default_single_cdockables = new ArrayList<>();
        }

        public boolean existCeldaDefaultSingleCDockable(String name) {
            for (CeldaDefaultSingleCDockable obj : this.default_single_cdockables) {
                if (obj.getUuid().equals(name)) {
                    return true;
                }
            }
            return false;
        }

        public void add(CeldaDefaultSingleCDockable row) {
            this.default_single_cdockables.add(row);
        }

        public CeldaDefaultSingleCDockable getCeldaDefaultSingleCDockable(String name) {
            for (CeldaDefaultSingleCDockable obj : this.default_single_cdockables) {
                if (obj.getUuid().equals(name)) {
                    return obj;
                }
            }
            return null;
        }
    }
}
