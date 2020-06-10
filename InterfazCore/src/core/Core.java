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
import bibliothek.gui.dock.common.CContentArea;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.action.CAction;
import bibliothek.gui.dock.common.intern.CDockable;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import logica.BasePanel;
import logica.CargarArchivoArbol;
import logica.CodePanel;
import logica.CopyCodeAction;
import logica.DesArbol;
import logica.LectorArchivo;
import logica.ModelTree;
import logica.OSValidator;

public class Core
        extends JFrame {

    private BasePanel currentSelection;
    private CodePanel currentCode;
    private LectorArchivo lector_archivo;
    private JTree tree;
    private DefaultSingleCDockable lectorSeleccionDockable;
    private CGrid layout;
    public CargarArchivoArbol cargador_de_archivo;
     
    public CControl control;
    

        
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
        
        this.control= new CControl(this);
        this.control.setTheme("eclipse");
        add(this.control.getContentArea());
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
                JDialog eliminar = new EliminarComponente(Core.this, true);
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
                JDialog vista = new ListaComponentes(Core.this, true);
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
        lector_archivo = new LectorArchivo(this);
        this.lectorSeleccionDockable = new DefaultSingleCDockable("selection", "Selection");
        this.lectorSeleccionDockable.setLayout(new BorderLayout());
        this.lectorSeleccionDockable.add(lector_archivo, BorderLayout.CENTER);
        this.lectorSeleccionDockable.setCloseable(true);
        this.layout.add(30, 0, 70, 100, this.lectorSeleccionDockable);

        /**
         * **********************************************************************
         */
        this.currentSelection = new BasePanel();
        DefaultSingleCDockable currentSelectionDockable = new DefaultSingleCDockable("selection", "Seleccion", new CAction[0]);
        currentSelectionDockable.setLayout(new BorderLayout());
        currentSelectionDockable.add(this.currentSelection, "Center");
        currentSelectionDockable.setCloseable(false);
        // layout.add(30.0D, 0.0D, 70.0D, 100.0D, new CDockable[] { currentSelectionDockable });

        this.currentCode = new CodePanel();
        DefaultSingleCDockable currentCodeDockable = new DefaultSingleCDockable("code", "Code", new CAction[0]);
        currentCodeDockable.setLayout(new BorderLayout());
        currentCodeDockable.add(this.currentCode.toComponent(), "Center");
        currentCodeDockable.setCloseable(false);
        if (!control.getController().isRestrictedEnvironment()) {
            currentCodeDockable.addAction(new CopyCodeAction(this.currentCode));
        }
        this.layout.add(30.0D, 0.0D, 70.0D, 100.0D, new CDockable[]{currentCodeDockable});
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
            System.err.println(path.toString());
            System.err.println(path.getPathComponent(path.getPath().length - 1).toString());
            System.out.println("COnponente es .... " + path.getPathComponent(path.getPath().length - 1).toString());
            DesArbol desc = cargador_de_archivo.getNodo(path.getPathComponent(path.getPath().length - 1).toString());
            try {
                if (cargador_de_archivo.validarExistencia(desc.getNombre())) {
                    currentCode.setCode(cargador_de_archivo.producirContenido(desc));
                }
            } catch (NullPointerException er) {
                currentCode.setCode("");
            }
        });
        this.layout.add(0.0D, 0.0D, 30.0D, 100.0D, new CDockable[]{listDockable});
        
        /*   Ejecutador de codigo       */
        ExecuteComponent executecomponene = new ExecuteComponent();
        DefaultSingleCDockable executecomponene_dockable= new DefaultSingleCDockable("Execute", "Ejecucion", new CAction[0]);
        executecomponene_dockable.setLayout(new BorderLayout());
        executecomponene_dockable.add(executecomponene, BorderLayout.CENTER);
        executecomponene_dockable.setCloseable(true);
        //this.layout.add(30, 0, 70, 100, executecomponene_dockable);
        this.layout.add(30.0D, 0.0D, 70.0D, 100.0D, new CDockable[]{executecomponene_dockable});
        /********************************/        
        control.getContentArea().deploy(this.layout);
        /** 
         * * CARGAR EN CODE **
         */
    }

    public JTree getTree() {
        return tree;
    }

    public void lectorVisible() {
        // this.lectorSeleccionDockable.setVisible(true);
        layout.select(30, 0, 70, 100, lectorSeleccionDockable);
        control.getContentArea().deploy(layout);
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
}
