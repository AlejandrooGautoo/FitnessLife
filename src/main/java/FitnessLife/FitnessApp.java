package FitnessLife;

import java.sql.*;
import Persistencia.dao.AlumnosDao;
import Persistencia.connection.ConexionBD;
import Persistencia.dao.RutinasDAO;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMTMaterialLighterIJTheme;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Alumnos;
import logica.Rutinas;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.io.PrintWriter;



public class FitnessApp extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FitnessApp.class.getName());
    
    //declaramos los objetos conexion y los daos
    private Connection connection;
    private AlumnosDao alumnosDao;
    private RutinasDAO rutinasDao;
    
    //creamos los modelos para mostrar alumnos y rutinas y las listas para almacenar datos
    DefaultTableModel tablaAlumno = new DefaultTableModel();
    DefaultTableModel tablaRutina = new DefaultTableModel();
    JTable jTableAlumnos = new JTable(tablaAlumno);
    JTable jTableRutinas = new JTable(tablaRutina);
    ArrayList<Alumnos> listaAlumnos = new ArrayList<Alumnos>();
    ArrayList<Rutinas> listaRutinas = new ArrayList<Rutinas>();
    
    
    
    public void refrescarTablaAlumno(){
        
        while(tablaAlumno.getRowCount()>0){
            tablaAlumno.removeRow(0);
        }
        if (alumnosDao != null) {
            try {
                listaAlumnos = (ArrayList<Alumnos>) alumnosDao.obtenerTodosAlumnos();
            }catch (Exception e) { // Para cualquier otra excepción inesperada
                logger.log(Level.SEVERE, "Error inesperado al cargar alumnos", e);
                listaAlumnos = new ArrayList<>();
            }
            // Usa SQLException para un manejo más específico
            // se asegura de que la lista no sea null en caso de error
            
        } else {
            logger.log(Level.WARNING, "AlumnosDao es null. No se pueden cargar alumnos de la base de datos.");
            listaAlumnos = new ArrayList<>();
        }        
        for (Alumnos alumno : listaAlumnos){
        Object a[] = new Object[8];
        a[0] = alumno.getNombre();
        a[1] = alumno.getApellido();
        a[2] = alumno.getDni();
        a[3] = alumno.getPeso();
        a[4] = alumno.getAltura();
        a[5] = alumno.getGenero();
        a[6] = alumno.getRutina();
        a[7] = alumno.getDias();
        tablaAlumno.addRow(a);
        
        }
        alumnosDao.obtenerTodosAlumnos();
        tblRegistroAlumnos.setModel(tablaAlumno);
    }
    
    public void refrescarTablaRutina(){
        while(tablaRutina.getRowCount()>0){
            tablaRutina.removeRow(0);
        }
        if (rutinasDao != null) {
            try {
                listaRutinas = (ArrayList<Rutinas>) rutinasDao.obtenerRutina();
            }catch (Exception e) { 
                logger.log(Level.SEVERE, "Error inesperado al cargar rutinas.", e);
                listaRutinas = new ArrayList<>();
            }
            // Usa SQLException para un manejo más específico
            // se asegura de que la lista no sea null en caso de error
            
        } else {
            logger.log(Level.WARNING, "RutinasDao es null. No se pueden cargar alumnos de la base de datos.");
            listaRutinas = new ArrayList<>();
        }
        for (Rutinas rutina : listaRutinas){
        Object r[] = new Object[8];
        r[0] = rutina.getEjercicio();
        r[1] = rutina.getSeriesRepeticiones();
        r[2] = rutina.getPesoEjercicio();
        r[3] = rutina.getDescanso();
        r[4] = rutina.getMusculoObjetivo();
        r[5] = rutina.getDia();
        r[6] = rutina.getTipoRutina();
        r[7] = rutina.getIdRutina();
        tablaRutina.addRow(r);
        }
        rutinasDao.obtenerRutina();
        tblRutinas.setModel(tablaRutina);
    }
    
    public FitnessApp() {
    initComponents();
    InitStyles();
    
    
    this.setTitle("Alumnos");
    this.setSize(1100, 700);
    this.setLocationRelativeTo(null);
    
    try {
        connection = ConexionBD.getConnection();         

        alumnosDao = new AlumnosDao(connection);
        rutinasDao = new RutinasDAO(connection);
        
        
        
        // crear las tablas si no existen
        alumnosDao.crearTabla();
        
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage());
        e.printStackTrace();
    }

    
    jTableAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jTableAlumnos.setRowSelectionAllowed(true);
    jTableAlumnos.setColumnSelectionAllowed(false);
    
    jTableRutinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jTableRutinas.setRowSelectionAllowed(true);
    jTableRutinas.setColumnSelectionAllowed(false);
    
    // Configurar tabla de alumnos
    tablaAlumno.addColumn("Nombre");
    tablaAlumno.addColumn("Apellido");
    tablaAlumno.addColumn("DNI");
    tablaAlumno.addColumn("Peso(KG)");
    tablaAlumno.addColumn("Altura");
    tablaAlumno.addColumn("Género");
    tablaAlumno.addColumn("Rutina");
    tablaAlumno.addColumn("Dias/semana");
    refrescarTablaAlumno();
    
    // Configurar tabla de rutinas
    tablaRutina.addColumn("Ejercicios");
    tablaRutina.addColumn("Series/Repeticiones");
    tablaRutina.addColumn("PesoEjercicio");
    tablaRutina.addColumn("Descanso");
    tablaRutina.addColumn("Musculo Objetivo");
    tablaRutina.addColumn("Dia");
    tablaRutina.addColumn("Tipo Rutina");
    tablaRutina.addColumn("ID Rutina");
    
    
    refrescarTablaRutina();
    
}
    private void InitStyles(){
        
        txtMensaje.putClientProperty( "FlatLaf.style", "font: 14 $light.font" );
        txtMensaje.setForeground(Color.black);
    }
      /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        SuperiorLila = new javax.swing.JPanel();
        txtBuenosDias = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        PilarVioleta = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lblFitnessLife = new javax.swing.JLabel();
        txtMensaje = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelAlumnos = new javax.swing.JPanel();
        BotonAñadir = new javax.swing.JButton();
        btnBorrarAlumno = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegistroAlumnos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreAlumno = new javax.swing.JTextField();
        txtApellidoAlumno = new javax.swing.JTextField();
        txtDniAlumno = new javax.swing.JTextField();
        txtPesoAlumno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDiasAlumno = new javax.swing.JTextField();
        txtAlturaAlumno = new javax.swing.JTextField();
        cboGeneroAlumno = new javax.swing.JComboBox<>();
        cboRutina = new javax.swing.JComboBox<>();
        btnModificarAlumno = new javax.swing.JButton();
        btnCrearTxtAlumno = new javax.swing.JButton();
        PanelRutinas = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEjerciciosRutina = new javax.swing.JTextField();
        txtSeriesRutina = new javax.swing.JTextField();
        txtPesoEjercicioRutina = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDescansoRutina = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMusculoRutina = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cboDiaRutina = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRutinas = new javax.swing.JTable();
        btnAñadirRutina = new javax.swing.JButton();
        btnBorrarRutina = new javax.swing.JButton();
        btnModificarRutina = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cboTipoRutina = new javax.swing.JComboBox<>();
        btnCrearTxtRutina = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SuperiorLila.setBackground(new java.awt.Color(102, 0, 102));

        txtBuenosDias.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtBuenosDias.setForeground(new java.awt.Color(255, 255, 255));
        txtBuenosDias.setText("Buenos Dias");

        txtFecha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.setText("Hoy es 14 de Junio de 2025");

        javax.swing.GroupLayout SuperiorLilaLayout = new javax.swing.GroupLayout(SuperiorLila);
        SuperiorLila.setLayout(SuperiorLilaLayout);
        SuperiorLilaLayout.setHorizontalGroup(
            SuperiorLilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuperiorLilaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(SuperiorLilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuenosDias))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SuperiorLilaLayout.setVerticalGroup(
            SuperiorLilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuperiorLilaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtBuenosDias, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        PilarVioleta.setBackground(new java.awt.Color(51, 0, 51));
        PilarVioleta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PilarVioleta.setForeground(new java.awt.Color(255, 255, 255));
        PilarVioleta.setToolTipText("");

        jSeparator1.setBackground(new java.awt.Color(204, 0, 204));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        lblFitnessLife.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblFitnessLife.setForeground(new java.awt.Color(255, 255, 255));
        lblFitnessLife.setText("FitnessLife");

        javax.swing.GroupLayout PilarVioletaLayout = new javax.swing.GroupLayout(PilarVioleta);
        PilarVioleta.setLayout(PilarVioletaLayout);
        PilarVioletaLayout.setHorizontalGroup(
            PilarVioletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PilarVioletaLayout.createSequentialGroup()
                .addGroup(PilarVioletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PilarVioletaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PilarVioletaLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblFitnessLife)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        PilarVioletaLayout.setVerticalGroup(
            PilarVioletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PilarVioletaLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lblFitnessLife, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtMensaje.setText("Un gusto volver a verte ❤");

        BotonAñadir.setBackground(new java.awt.Color(51, 0, 51));
        BotonAñadir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BotonAñadir.setForeground(new java.awt.Color(255, 255, 255));
        BotonAñadir.setText("AÑADIR");
        BotonAñadir.setBorder(null);
        BotonAñadir.setBorderPainted(false);
        BotonAñadir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAñadirActionPerformed(evt);
            }
        });

        btnBorrarAlumno.setBackground(new java.awt.Color(51, 0, 51));
        btnBorrarAlumno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBorrarAlumno.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrarAlumno.setText("BORRAR");
        btnBorrarAlumno.setBorder(null);
        btnBorrarAlumno.setBorderPainted(false);
        btnBorrarAlumno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBorrarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarAlumnoActionPerformed(evt);
            }
        });

        tblRegistroAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "Title 6", "Title 7", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistroAlumnos.setColumnSelectionAllowed(true);
        tblRegistroAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistroAlumnosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRegistroAlumnos);
        tblRegistroAlumnos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblRegistroAlumnos.getColumnModel().getColumnCount() > 0) {
            tblRegistroAlumnos.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre:");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Apellido:");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("DNI:");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Peso(KG):");

        txtNombreAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreAlumnoActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Altura:");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Género:");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Rutina:");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Dias/semana:");

        txtDiasAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasAlumnoActionPerformed(evt);
            }
        });

        cboGeneroAlumno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        cboGeneroAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGeneroAlumnoActionPerformed(evt);
            }
        });

        cboRutina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hipertrofia", "Fuerza ", "Ac. Fis.", "Perdida Peso", "Rehabilitacion" }));
        cboRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRutinaActionPerformed(evt);
            }
        });

        btnModificarAlumno.setBackground(new java.awt.Color(51, 0, 51));
        btnModificarAlumno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarAlumno.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarAlumno.setText("MODIFICAR");
        btnModificarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAlumnoActionPerformed(evt);
            }
        });

        btnCrearTxtAlumno.setText("CREAR TXT");
        btnCrearTxtAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTxtAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAlumnosLayout = new javax.swing.GroupLayout(PanelAlumnos);
        PanelAlumnos.setLayout(PanelAlumnosLayout);
        PanelAlumnosLayout.setHorizontalGroup(
            PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAlumnosLayout.createSequentialGroup()
                .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAlumnosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE))
                    .addGroup(PanelAlumnosLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAlumnosLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(txtNombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addGroup(PanelAlumnosLayout.createSequentialGroup()
                                .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(27, 27, 27)
                                .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDniAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApellidoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPesoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(PanelAlumnosLayout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel7)))))
                        .addGap(30, 30, 30)
                        .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboGeneroAlumno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAlturaAlumno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDiasAlumno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BotonAñadir, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(btnBorrarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(btnModificarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnCrearTxtAlumno)
                        .addGap(25, 25, 25)))
                .addContainerGap())
        );
        PanelAlumnosLayout.setVerticalGroup(
            PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAlumnosLayout.createSequentialGroup()
                .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAlumnosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlturaAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAlumnosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAlumnosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtApellidoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboGeneroAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAlumnosLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtDniAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelAlumnosLayout.createSequentialGroup()
                                .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelAlumnosLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel3))
                                    .addGroup(PanelAlumnosLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))))
                                .addGap(18, 18, 18)
                                .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtPesoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelAlumnosLayout.createSequentialGroup()
                                        .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtDiasAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addGap(3, 3, 3)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAlumnosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(PanelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCrearTxtAlumno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBorrarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Alumnos", PanelAlumnos);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Ejercicios:");

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Series/Repeticiones:");

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("PesoEjercicio:");

        txtEjerciciosRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEjerciciosRutinaActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Descanso:");

        txtDescansoRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescansoRutinaActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Musculo Objetivo:");

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Dia:");

        cboDiaRutina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" }));
        cboDiaRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDiaRutinaActionPerformed(evt);
            }
        });

        tblRutinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Ejercicios", "Series/Repeticiones", "PesoEjercicio", "Descanso", "Musculo Objetivo", "Dia", "Rutina"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRutinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRutinasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRutinas);
        if (tblRutinas.getColumnModel().getColumnCount() > 0) {
            tblRutinas.getColumnModel().getColumn(5).setResizable(false);
        }

        btnAñadirRutina.setBackground(new java.awt.Color(51, 0, 51));
        btnAñadirRutina.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAñadirRutina.setForeground(new java.awt.Color(255, 255, 255));
        btnAñadirRutina.setText("AÑADIR");
        btnAñadirRutina.setBorder(null);
        btnAñadirRutina.setBorderPainted(false);
        btnAñadirRutina.setMaximumSize(new java.awt.Dimension(70, 60));
        btnAñadirRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirRutinaActionPerformed(evt);
            }
        });

        btnBorrarRutina.setBackground(new java.awt.Color(51, 0, 51));
        btnBorrarRutina.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBorrarRutina.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrarRutina.setText("BORRAR");
        btnBorrarRutina.setBorder(null);
        btnBorrarRutina.setBorderPainted(false);
        btnBorrarRutina.setMaximumSize(new java.awt.Dimension(70, 60));
        btnBorrarRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarRutinaActionPerformed(evt);
            }
        });

        btnModificarRutina.setBackground(new java.awt.Color(51, 0, 51));
        btnModificarRutina.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarRutina.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarRutina.setText("MODIFICAR");
        btnModificarRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarRutinaActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Rutina:");

        cboTipoRutina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hipertrofia", "Fuerza ", "Ac. Fis.", "Perdida Peso", "Rehabilitacion" }));

        btnCrearTxtRutina.setText("CREAR TXT");
        btnCrearTxtRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTxtRutinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelRutinasLayout = new javax.swing.GroupLayout(PanelRutinas);
        PanelRutinas.setLayout(PanelRutinasLayout);
        PanelRutinasLayout.setHorizontalGroup(
            PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
            .addGroup(PanelRutinasLayout.createSequentialGroup()
                .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelRutinasLayout.createSequentialGroup()
                        .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PanelRutinasLayout.createSequentialGroup()
                                .addComponent(txtSeriesRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelRutinasLayout.createSequentialGroup()
                                .addComponent(txtEjerciciosRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(jLabel12))
                            .addGroup(PanelRutinasLayout.createSequentialGroup()
                                .addComponent(txtPesoEjercicioRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14))))
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescansoRutina)
                    .addComponent(txtMusculoRutina)
                    .addComponent(cboDiaRutina, 0, 148, Short.MAX_VALUE)
                    .addComponent(cboTipoRutina, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBorrarRutina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAñadirRutina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificarRutina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrearTxtRutina)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelRutinasLayout.setVerticalGroup(
            PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRutinasLayout.createSequentialGroup()
                .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelRutinasLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEjerciciosRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtDescansoRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelRutinasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAñadirRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMusculoRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtSeriesRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnCrearTxtRutina))
                .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelRutinasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboDiaRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11)
                            .addComponent(txtPesoEjercicioRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(PanelRutinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cboTipoRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(PanelRutinasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBorrarRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rutinas", PanelRutinas);

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addComponent(PilarVioleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SuperiorLila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PilarVioleta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(SuperiorLila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAñadirActionPerformed
       try{
        Alumnos alumno = new Alumnos();
        Rutinas rutina = new Rutinas();
       alumnosDao.crearTabla();
       alumno.setNombre(txtNombreAlumno.getText().trim());
       alumno.setApellido(txtApellidoAlumno.getText().trim());
       alumno.setDni(Integer.parseInt(txtDniAlumno.getText().trim()));
       alumno.setPeso(Integer.parseInt(txtPesoAlumno.getText().trim()));
       alumno.setAltura(Double.parseDouble(txtAlturaAlumno.getText().trim()));
       alumno.setGenero(cboGeneroAlumno.getSelectedItem().toString().trim());
       alumno.setRutina(cboRutina.getSelectedItem().toString().trim());
       alumno.setDias(Integer.parseInt(txtDiasAlumno.getText()));
       alumnosDao.añadir(alumno);
       listaAlumnos.add(alumno);                     
       txtNombreAlumno.setText("");
       txtApellidoAlumno.setText("");
       txtDniAlumno.setText("");
       txtPesoAlumno.setText("");
       txtAlturaAlumno.setText("");
       txtDiasAlumno.setText("");
       refrescarTablaAlumno();
       } catch (Exception e){
       JOptionPane.showMessageDialog(this, "ERROR" + e.getMessage());
       }
    }//GEN-LAST:event_BotonAñadirActionPerformed

    private void btnBorrarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarAlumnoActionPerformed
        Alumnos alumno = new Alumnos();
        int fila = tblRegistroAlumnos.getSelectedRow();
        if (fila!= -1){
            int dniABorrar = (int) tblRegistroAlumnos.getValueAt(fila, 2);            
            try {
                alumnosDao.borrar(dniABorrar);
            } catch (SQLException ex) {
                Logger.getLogger(FitnessApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            tablaAlumno.removeRow(fila);
            listaAlumnos.remove(fila);
            try {
                alumnosDao.borrar(alumno.getDni());
            } catch (SQLException ex) {
                Logger.getLogger(FitnessApp.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
    }//GEN-LAST:event_btnBorrarAlumnoActionPerformed

    private void txtNombreAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreAlumnoActionPerformed

    private void cboRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRutinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboRutinaActionPerformed

    private void cboGeneroAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGeneroAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGeneroAlumnoActionPerformed

    private void txtDiasAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasAlumnoActionPerformed

    private void txtEjerciciosRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEjerciciosRutinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEjerciciosRutinaActionPerformed

    private void txtDescansoRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescansoRutinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescansoRutinaActionPerformed

    private void btnAñadirRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirRutinaActionPerformed
try{
        Rutinas rutina = new Rutinas();
        rutina.setEjercicio(txtEjerciciosRutina.getText().trim());
        rutina.setSeriesRepeticiones(txtSeriesRutina.getText().trim());
        rutina.setPesoEjercicio(txtPesoEjercicioRutina.getText().trim());
        rutina.setDescanso(txtDescansoRutina.getText().trim());
        rutina.setMusculoObjetivo(txtMusculoRutina.getText().trim());
        rutina.setDia(cboDiaRutina.getSelectedItem().toString());
        rutina.setTipoRutina(cboTipoRutina.getSelectedItem().toString());
        
        rutinasDao.crearTabla();
        
        int idGenerado = rutinasDao.añadir(rutina);
        rutina.setIdRutina(idGenerado);
        listaRutinas.add(rutina);
        
        txtEjerciciosRutina.setText("");
        txtSeriesRutina.setText("");
        txtPesoEjercicioRutina.setText("");
        txtDescansoRutina.setText("");
        txtMusculoRutina.setText("");
        
        refrescarTablaRutina();
        
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());    
    }
    }//GEN-LAST:event_btnAñadirRutinaActionPerformed

    private void tblRutinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRutinasMouseClicked
        int fila = tblRutinas.getSelectedRow();
    if (fila != -1) {
        // obtengo la rutina desde la lista
        Rutinas rutina = listaRutinas.get(fila);
        
        txtEjerciciosRutina.setText(rutina.getEjercicio());
        txtSeriesRutina.setText(rutina.getSeriesRepeticiones());
        txtPesoEjercicioRutina.setText(String.valueOf(rutina.getPesoEjercicio()));
        txtDescansoRutina.setText(String.valueOf(rutina.getDescanso()));
        txtMusculoRutina.setText(String.valueOf(rutina.getMusculoObjetivo()));     // ComboBox
        cboDiaRutina.setSelectedItem(rutina.getDia());           // ComboBox
        cboTipoRutina.setSelectedItem(String.valueOf(rutina.getTipoRutina()));
    }
    }//GEN-LAST:event_tblRutinasMouseClicked

    private void btnBorrarRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarRutinaActionPerformed
        int fila = tblRutinas.getSelectedRow();
        if (fila!= -1){
            int idABorrar = (int) tblRutinas.getValueAt(fila, 7);
            try {                
                rutinasDao.borrar(idABorrar);
                tablaRutina.removeRow(fila);
                listaRutinas.remove(fila);
            } catch (SQLException ex) {
                Logger.getLogger(FitnessApp.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
           }
    }//GEN-LAST:event_btnBorrarRutinaActionPerformed

    private void tblRegistroAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistroAlumnosMouseClicked
     int fila = tblRegistroAlumnos.getSelectedRow();
    if (fila != -1) {
        // obtener el alumno desde la lista
        Alumnos alumno = listaAlumnos.get(fila);
        
        // Cargar los datos en los campos
        txtDniAlumno.setText(String.valueOf(alumno.getDni()));
        txtNombreAlumno.setText(alumno.getNombre());
        txtApellidoAlumno.setText(alumno.getApellido());
        txtPesoAlumno.setText(String.valueOf(alumno.getPeso()));
        txtAlturaAlumno.setText(String.valueOf(alumno.getAltura()));
        cboGeneroAlumno.setSelectedItem(alumno.getGenero());     // ComboBox
        cboRutina.setSelectedItem(alumno.getRutina());           // ComboBox
        txtDiasAlumno.setText(String.valueOf(alumno.getDias()));
    }
    }//GEN-LAST:event_tblRegistroAlumnosMouseClicked

    private void cboDiaRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDiaRutinaActionPerformed

       }//GEN-LAST:event_cboDiaRutinaActionPerformed

    private void limpiarCamposRutina(){
    txtEjerciciosRutina.setText("");
        txtSeriesRutina.setText("");
        txtPesoEjercicioRutina.setText("");
        txtDescansoRutina.setText("");
        txtMusculoRutina.setText("");     
        cboDiaRutina.setSelectedIndex(0); // ComboBox
        cboTipoRutina.setSelectedIndex(0);
    }
    private void limpiarCamposAlumno() {
    txtDniAlumno.setText("");
    txtNombreAlumno.setText("");
    txtApellidoAlumno.setText("");
    txtPesoAlumno.setText("");
    txtAlturaAlumno.setText("");
    cboGeneroAlumno.setSelectedIndex(0);
    cboRutina.setSelectedIndex(0);
    txtDiasAlumno.setText("");
}
    private void btnModificarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAlumnoActionPerformed
      try {
        // crear objeto alumno con los datos de los campos
        Alumnos alumno = new Alumnos();
        alumno.setDni(Integer.parseInt(txtDniAlumno.getText().trim()));
        alumno.setNombre(txtNombreAlumno.getText().trim());
        alumno.setApellido(txtApellidoAlumno.getText().trim());
        alumno.setPeso(Integer.parseInt(txtPesoAlumno.getText().trim()));
        alumno.setAltura(Double.parseDouble(txtAlturaAlumno.getText().trim()));
        alumno.setGenero(cboGeneroAlumno.getSelectedItem().toString());
        alumno.setRutina(cboRutina.getSelectedItem().toString());
        alumno.setDias(Integer.parseInt(txtDiasAlumno.getText().trim()));
        
        // actualizar en la base de datos
        alumnosDao.actualizarAlumno(alumno);
        
        // actualizar en la lista local
        int fila = tblRegistroAlumnos.getSelectedRow();
        if (fila != -1) {
            listaAlumnos.set(fila, alumno);
        }
        refrescarTablaAlumno(); 
        
        // limpiar los campos
        limpiarCamposAlumno();
        
    } catch (Exception e) {
        System.out.println("Error al modificar alumno: " + e.getMessage());
    }
    }//GEN-LAST:event_btnModificarAlumnoActionPerformed

    private void btnModificarRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarRutinaActionPerformed
        try {
        // verificar que hay una fila seleccionada
        int fila = tblRutinas.getSelectedRow();
        if (fila == -1) {
            System.out.println("Error: No hay ninguna rutina seleccionada");
            return;
        }
        
        // obtener la rutina original para conservar el ID
        Rutinas rutinaOriginal = listaRutinas.get(fila);
        
        // crear objeto rutina con los datos de los campos
        Rutinas rutina = new Rutinas();
        rutina.setIdRutina(rutinaOriginal.getIdRutina()); // IMPORTANTE: Conservar el ID
        rutina.setEjercicio(txtEjerciciosRutina.getText().trim());
        rutina.setSeriesRepeticiones(txtSeriesRutina.getText().trim());
        rutina.setPesoEjercicio(txtPesoEjercicioRutina.getText().trim());
        rutina.setDescanso(txtDescansoRutina.getText().trim());
        rutina.setMusculoObjetivo(txtMusculoRutina.getText().trim());
        rutina.setDia(cboDiaRutina.getSelectedItem().toString());
        rutina.setTipoRutina(cboTipoRutina.getSelectedItem().toString());
        // actualizar en la base de datos
        rutinasDao.actualizarRutina(rutina);
        // Actualizar en la lista local
        listaRutinas.set(fila, rutina);
        refrescarTablaRutina(); // Asumiendo que tienes este método
        limpiarCamposRutina();
    } catch (Exception e) {
        System.out.println("Error al modificar rutina: " + e.getMessage());
        e.printStackTrace(); // Para ver el stack trace completo
    }
    }//GEN-LAST:event_btnModificarRutinaActionPerformed

    private void btnCrearTxtAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearTxtAlumnoActionPerformed
        try {
        PrintWriter writer = new PrintWriter("alumnos.txt");
        writer.println("Nombre\tApellido\tDNI\tPeso\tAltura\tGenero\tRutina\tDias");
        
        for (Alumnos alumno : listaAlumnos) {
            writer.println(alumno.getNombre() + "\t" + alumno.getApellido() + "\t" + 
                          alumno.getDni() + "\t" + alumno.getPeso() + "\t" + 
                          alumno.getAltura() + "\t" + alumno.getGenero() + "\t" + 
                          alumno.getRutina() + "\t" + alumno.getDias());
        }
        
        writer.close();
        JOptionPane.showMessageDialog(this, "Archivo alumnos.txt creado en el escritorio!");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCrearTxtAlumnoActionPerformed

    private void btnCrearTxtRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearTxtRutinaActionPerformed
        try {
        PrintWriter writer = new PrintWriter("rutinas.txt");
        writer.println("ID\tEjercicios\tSeries/Repeticiones\tPeso\tDescanso\tMusculo\tDia\tTipo");
        
        for (Rutinas rutina : listaRutinas) {
            writer.println(rutina.getIdRutina() + "\t" + rutina.getEjercicio() + "\t" + 
                          rutina.getSeriesRepeticiones() + "\t" + rutina.getPesoEjercicio() + "\t" + 
                          rutina.getDescanso() + "\t" + rutina.getMusculoObjetivo() + "\t" + 
                          rutina.getDia() + "\t" + rutina.getTipoRutina());
        }
        
        writer.close();
        JOptionPane.showMessageDialog(this, "Archivo rutinas.txt creado en la carpeta del proyecto!");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCrearTxtRutinaActionPerformed
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         FlatMTMaterialLighterIJTheme.setup();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FitnessApp().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAñadir;
    private javax.swing.JPanel PanelAlumnos;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JPanel PanelRutinas;
    private javax.swing.JPanel PilarVioleta;
    private javax.swing.JPanel SuperiorLila;
    private javax.swing.JButton btnAñadirRutina;
    private javax.swing.JButton btnBorrarAlumno;
    private javax.swing.JButton btnBorrarRutina;
    private javax.swing.JButton btnCrearTxtAlumno;
    private javax.swing.JButton btnCrearTxtRutina;
    private javax.swing.JButton btnModificarAlumno;
    private javax.swing.JButton btnModificarRutina;
    private javax.swing.JComboBox<String> cboDiaRutina;
    private javax.swing.JComboBox<String> cboGeneroAlumno;
    private javax.swing.JComboBox<String> cboRutina;
    private javax.swing.JComboBox<String> cboTipoRutina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblFitnessLife;
    private javax.swing.JTable tblRegistroAlumnos;
    private javax.swing.JTable tblRutinas;
    private javax.swing.JTextField txtAlturaAlumno;
    private javax.swing.JTextField txtApellidoAlumno;
    private javax.swing.JLabel txtBuenosDias;
    private javax.swing.JTextField txtDescansoRutina;
    private javax.swing.JTextField txtDiasAlumno;
    private javax.swing.JTextField txtDniAlumno;
    private javax.swing.JTextField txtEjerciciosRutina;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JLabel txtMensaje;
    private javax.swing.JTextField txtMusculoRutina;
    private javax.swing.JTextField txtNombreAlumno;
    private javax.swing.JTextField txtPesoAlumno;
    private javax.swing.JTextField txtPesoEjercicioRutina;
    private javax.swing.JTextField txtSeriesRutina;
    // End of variables declaration//GEN-END:variables
}