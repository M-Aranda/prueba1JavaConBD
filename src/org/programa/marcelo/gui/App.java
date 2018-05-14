package org.programa.marcelo.gui;

import Convenios.Convenio;
import Cursos.Curso;
import Registros.Alumno;
import Registros.Matricula;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import conexion.Conexion;
import static conexion.Conexion.getConnection;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo Aranda
 */
public class App extends javax.swing.JFrame {

    private List<Curso> cursos;
    private List<Convenio> convenios;
    private List<Alumno> alumnos;
    private List<Matricula> matriculas;

    private int cantHombres;
    private int cantMujeres;

    private int cantMHard;
    private int cantHHard;
    private int cantMJump;
    private int cantHJump;
    private int cantMTec;
    private int cantHTec;

    private int recH;
    private int recJ;
    private int recT;

    private Connection conn = null;
    private Conexion con;
    private Estadisticas est;

    //query para los insert
    static final String WRITE_OBJECT_SQL = "INSERT INTO matricula(nombre, valor_objeto) VALUES (?, ?)";

    //query para los select
    static final String READ_OBJECT_SQL = "SELECT valor_objeto FROM matricula WHERE id = ?";

    public App() {

        try {

            conn = getConnection();
            initComponents();
            this.setTitle("Sistema de matriculas Beat Dance School");
            setLocationRelativeTo(null);
            txtRun.requestFocus();
            initCursos();
            initConvenios();
            deshabilitarCamposNoeditables();
            txtDescuento.setText("0");
            txtTotalFinal.setText("0");
            txtNCuotas.setToolTipText("Debe presionar enter para calcular el valor de cada cuota");
            ocultarBtnCancelar();
            inicializarAtributos();
            bloquearTodoMenosCampoRunYBTNbuscar();

            rescatarRegistrosDeSQL();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void rescatarRegistrosDeSQL() throws SQLException {
        int cantReg = determinarCantRegistros();
        for (int i = 0; i < cantReg; i++) {
            try {
                matriculas.add((Matricula) readJavaObject(conn, i + 1));
            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private int determinarCantRegistros() throws SQLException {
        String sqlSelect = "SELECT COUNT(*) FROM matricula;";

        Statement stmt = conn.createStatement();
        int max = 0;

        ResultSet rs = stmt.executeQuery(sqlSelect);
        if (rs.next()) {
            max = rs.getInt(1);
        }

        return max;

    }

    public void cargarDatosEstadistica() {

        recolectarDatosTC();
        est.fijarDatosHard(cantMHard, cantHHard, recH);
        est.fijarDatosJump(cantMJump, cantHJump, recJ);
        est.fijarDatosTec(cantMTec, cantHTec, recT);
        est.calcularYFijarRecaudacionFinal(recH, recJ, recT);

    }

    //para escribir el objeto
    public static long writeJavaObject(Connection conn, Object object) throws Exception {
        String className = object.getClass().getName();
        PreparedStatement pstmt = conn.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);

        // fijar parametros de ingreso
        pstmt.setString(1, className);
        pstmt.setObject(2, object);
        pstmt.executeUpdate();

        // obtener la clave generada para el id
        ResultSet rs = pstmt.getGeneratedKeys();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }

        rs.close();
        pstmt.close();

        return id;
    }

    //para leer el objeto
    public static Object readJavaObject(Connection conn, long id) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Object object = rs.getObject(1);
        String className = object.getClass().getName();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }

        Object deSerializedObject = objectIn.readObject();

        rs.close();
        pstmt.close();

        return deSerializedObject;
    }

    private void inicializarAtributos() {
        rdbtnMasculino.setSelected(true);
        alumnos = new ArrayList<>();
        matriculas = new ArrayList<>();
        cantHombres = 0;
        cantMujeres = 0;
        cantHHard = 0;
        cantHJump = 0;
        cantHTec = 0;
        cantMHard = 0;
        cantMJump = 0;
        cantMTec = 0;
        recH = 0;
        recJ = 0;
        recT = 0;
        est = new Estadisticas();
        est.setVisible(false);

    }

    private void ocultarBtnCancelar() {
        btnCancelar.setEnabled(false);
        btnCancelar.setVisible(false);
    }

    private void mostrarBtnCancelar() {
        btnCancelar.setEnabled(true);
        btnCancelar.setVisible(true);
    }

    private void mostrarMatrMensTotal() {
        if (String.valueOf(cboCurso.getSelectedItem()).equalsIgnoreCase(cursos.get(0).getNombre())) {
            txtMatricula.setText(String.valueOf(cursos.get(0).getMatricula()));
            txtMensualidad.setText(String.valueOf(cursos.get(0).getMensualidad()));
            txtTotal.setText(String.valueOf(cursos.get(0).getMatricula() + (cursos.get(0).getMensualidad() * cursos.get(0).getCantidadDeMeses())));
            txtTotalFinal.setText(String.valueOf(cursos.get(0).getMatricula() + (cursos.get(0).getMensualidad() * cursos.get(0).getCantidadDeMeses())));

        } else if (String.valueOf(cboCurso.getSelectedItem()).equalsIgnoreCase(cursos.get(1).getNombre())) {
            txtMatricula.setText(String.valueOf(cursos.get(1).getMatricula()));
            txtMensualidad.setText(String.valueOf(cursos.get(1).getMensualidad()));
            txtTotal.setText(String.valueOf(cursos.get(1).getMatricula() + (cursos.get(1).getMensualidad() * cursos.get(1).getCantidadDeMeses())));
            txtTotalFinal.setText(String.valueOf(cursos.get(1).getMatricula() + (cursos.get(1).getMensualidad() * cursos.get(1).getCantidadDeMeses())));

        } else if (String.valueOf(cboCurso.getSelectedItem()).equalsIgnoreCase(cursos.get(2).getNombre())) {
            txtMatricula.setText(String.valueOf(cursos.get(2).getMatricula()));
            txtMensualidad.setText(String.valueOf(cursos.get(2).getMensualidad()));
            txtTotal.setText(String.valueOf(cursos.get(2).getMatricula() + (cursos.get(2).getMensualidad() * cursos.get(2).getCantidadDeMeses())));
            txtTotalFinal.setText(String.valueOf(cursos.get(2).getMatricula() + (cursos.get(2).getMensualidad() * cursos.get(2).getCantidadDeMeses())));

        } else if (String.valueOf(cboCurso.getSelectedItem()).equalsIgnoreCase(cursos.get(3).getNombre())) {
            txtMatricula.setText(String.valueOf(cursos.get(3).getMatricula()));
            txtMensualidad.setText(String.valueOf(cursos.get(3).getMensualidad()));
            txtTotal.setText(String.valueOf(cursos.get(3).getMatricula() + (cursos.get(3).getMensualidad() * cursos.get(3).getCantidadDeMeses())));
            txtTotalFinal.setText(String.valueOf(cursos.get(3).getMatricula() + (cursos.get(3).getMensualidad() * cursos.get(3).getCantidadDeMeses())));

        }

    }

    private void recolectarDatosH() {
        cantHHard = 0;
        cantMHard = 0;
        recH = 0;

        for (Matricula m : matriculas) {
            if (m.getAlumno().getSexo().equals("Masculino") && m.getCurso().equals("Hardstyle Shuffle")) {

                cantHHard++;
                recH += m.getTotalFinal();
            } else if (m.getAlumno().getSexo().equals("Femenino") && m.getCurso().equals("Hardstyle Shuffle")) {

                cantMHard++;
                recH += m.getTotalFinal();
            }
        }
    }

    private void recolectarDatosJ() {
        cantHJump = 0;
        cantMJump = 0;
        recJ = 0;

        for (Matricula m : matriculas) {
            if (m.getAlumno().getSexo().equals("Masculino") && m.getCurso().equals("Jumpstyle")) {

                cantHJump++;
                recJ += m.getTotalFinal();
            } else if (m.getAlumno().getSexo().equals("Femenino") && m.getCurso().equals("Jumpstyle")) {
                cantMJump++;
                recJ += m.getTotalFinal();
            }
        }
    }

    private void recolectarDatosT() {
        cantHTec = 0;
        cantMTec = 0;
        recT = 0;

        for (Matricula m : matriculas) {

            if (m.getAlumno().getSexo().equals("Masculino") && m.getCurso().equals("Tecktonik")) {

                cantHTec++;
                recT += m.getTotalFinal();
            } else if (m.getAlumno().getSexo().equals("Femenino") && m.getCurso().equals("Tecktonik")) {

                cantMTec++;
                recT += m.getTotalFinal();
            }
        }
    }

    private void recolectarDatosTC() {
        recolectarDatosH();
        recolectarDatosJ();
        recolectarDatosT();
    }

    private void deshabilitarCamposNoeditables() {
        txtMatricula.setEnabled(false);
        txtMensualidad.setEnabled(false);
        txtTotal.setEnabled(false);
        pnlConvenio.setEnabled(false);
        cboConvenio.setEnabled(false);
        txtDescuento.setEnabled(false);
        txtTotalFinal.setEnabled(false);
        txtValorCuota.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupSexo = new javax.swing.ButtonGroup();
        pnlPrincipal = new javax.swing.JPanel();
        lblRun = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblCurso = new javax.swing.JLabel();
        lblMatricula = new javax.swing.JLabel();
        lblMensualidad = new javax.swing.JLabel();
        lblTotalCurso = new javax.swing.JLabel();
        chkConvenio = new javax.swing.JCheckBox();
        pnlConvenio = new javax.swing.JPanel();
        cboConvenio = new javax.swing.JComboBox<>();
        lblDescuento = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        lblTotalFinal = new javax.swing.JLabel();
        lblNCuotas = new javax.swing.JLabel();
        lblTotalCuota = new javax.swing.JLabel();
        txtRun = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        cboCurso = new javax.swing.JComboBox<>();
        txtMatricula = new javax.swing.JTextField();
        txtMensualidad = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtTotalFinal = new javax.swing.JTextField();
        txtValorCuota = new javax.swing.JTextField();
        txtNCuotas = new javax.swing.JTextField();
        rdbtnMasculino = new javax.swing.JRadioButton();
        rdbtnFemenino = new javax.swing.JRadioButton();
        btnBuscar = new javax.swing.JButton();
        btnMatricular = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        mnuP = new javax.swing.JMenuBar();
        menuBtnArchivo = new javax.swing.JMenu();
        menuEstadisticas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlPrincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos alumno\n", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlPrincipal.setToolTipText("Datos alumno\n");
        pnlPrincipal.setName(""); // NOI18N

        lblRun.setText("Run:");

        lblSexo.setText("Sexo:");

        lblNombre.setText("Nombre:");

        lblDireccion.setText("Direccion:");

        lblCurso.setText("Curso:");

        lblMatricula.setText("Matricula:");

        lblMensualidad.setText("Mensualidad:");

        lblTotalCurso.setText("Total:");

        chkConvenio.setText("Convenio");
        chkConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConvenioActionPerformed(evt);
            }
        });

        pnlConvenio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Convenio", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        cboConvenio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboConvenioActionPerformed(evt);
            }
        });

        lblDescuento.setText("Descuento:");

        javax.swing.GroupLayout pnlConvenioLayout = new javax.swing.GroupLayout(pnlConvenio);
        pnlConvenio.setLayout(pnlConvenioLayout);
        pnlConvenioLayout.setHorizontalGroup(
            pnlConvenioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConvenioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConvenioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboConvenio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlConvenioLayout.createSequentialGroup()
                        .addComponent(lblDescuento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pnlConvenioLayout.setVerticalGroup(
            pnlConvenioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConvenioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlConvenioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescuento)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        lblTotalFinal.setText("Total FINAL:");

        lblNCuotas.setText("N° Cuotas");

        lblTotalCuota.setText("Total cuota:");

        cboCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCursoActionPerformed(evt);
            }
        });

        txtNCuotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNCuotasKeyPressed(evt);
            }
        });

        btnGroupSexo.add(rdbtnMasculino);
        rdbtnMasculino.setText("Masculino");

        btnGroupSexo.add(rdbtnFemenino);
        rdbtnFemenino.setText("Femenino");

        btnBuscar.setText("...");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                    .addComponent(lblTotalCurso)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTotal))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                    .addComponent(lblMensualidad)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtMensualidad))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                    .addComponent(lblMatricula)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtMatricula))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                    .addComponent(lblCurso)
                                    .addGap(18, 18, 18)
                                    .addComponent(cboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblRun)
                                    .addComponent(lblDireccion)
                                    .addComponent(lblSexo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                        .addComponent(rdbtnMasculino)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdbtnFemenino))
                                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                        .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscar))
                                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING))))))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkConvenio)
                            .addComponent(pnlConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(lblTotalCuota)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValorCuota))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(lblNCuotas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(lblTotalFinal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalFinal)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRun)
                    .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexo)
                    .addComponent(rdbtnMasculino)
                    .addComponent(rdbtnFemenino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCurso)
                    .addComponent(cboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatricula)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMensualidad)
                    .addComponent(txtMensualidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCurso)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkConvenio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalFinal)
                    .addComponent(txtTotalFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNCuotas)
                    .addComponent(txtNCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCuota)
                    .addComponent(txtValorCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        btnMatricular.setText("Matricular");
        btnMatricular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatricularActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        menuBtnArchivo.setText("Archivo");

        menuEstadisticas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuEstadisticas.setText("Ver estadisticas de curso");
        menuEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEstadisticasActionPerformed(evt);
            }
        });
        menuBtnArchivo.add(menuEstadisticas);

        mnuP.add(menuBtnArchivo);

        setJMenuBar(mnuP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMatricular)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMatricular)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pnlPrincipal.getAccessibleContext().setAccessibleName("Datos alumno");
        pnlPrincipal.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCursoActionPerformed
        mostrarMatrMensTotal();

    }//GEN-LAST:event_cboCursoActionPerformed

    private int calcularDescuento(int total, int porc) {

        int resultado = (total * porc) / 100;
        return resultado;
    }

    private void cboConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboConvenioActionPerformed

        int totalEnNumeros = Integer.parseInt(txtTotal.getText());

        int descuento = 0;

        if (String.valueOf(cboConvenio.getSelectedItem()).contains(convenios.get(0).getNombre())) {
            descuento = calcularDescuento(totalEnNumeros, (convenios.get(0).getPorcentaje()));

        } else if (String.valueOf(cboConvenio.getSelectedItem()).contains(convenios.get(1).getNombre())) {
            descuento = calcularDescuento(totalEnNumeros, (convenios.get(1).getPorcentaje()));

        } else if (String.valueOf(cboConvenio.getSelectedItem()).contains(convenios.get(2).getNombre())) {
            descuento = calcularDescuento(totalEnNumeros, (convenios.get(2).getPorcentaje()));
        }

        txtDescuento.setText(String.valueOf(descuento));
        int totalFinal = totalEnNumeros - descuento;
        txtTotalFinal.setText(String.valueOf(totalFinal));


    }//GEN-LAST:event_cboConvenioActionPerformed

    private void chkConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConvenioActionPerformed
        if (chkConvenio.isSelected()) {
            pnlConvenio.setEnabled(true);
            cboConvenio.setEnabled(true);
        } else if (!chkConvenio.isSelected()) {
            pnlConvenio.setEnabled(false);
            cboConvenio.setEnabled(false);
        }
    }//GEN-LAST:event_chkConvenioActionPerformed


    private void menuEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEstadisticasActionPerformed

        cargarDatosEstadistica();

        est.setVisible(true);
        //cierra y destruye la ventana al salirse de ella
        est.setDefaultCloseOperation(est.DISPOSE_ON_CLOSE);


    }//GEN-LAST:event_menuEstadisticasActionPerformed

    private void txtNCuotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCuotasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                int cantidadCuotas = Integer.parseInt(txtNCuotas.getText());
                int valorCuota = calcularValorCuotas(Integer.parseInt(txtTotalFinal.getText()), cantidadCuotas);
                txtValorCuota.setText(String.valueOf(valorCuota));

            } catch (Exception e) {
                msgDeRutErrorCampoDeNCuotas();
            }

        };
    }//GEN-LAST:event_txtNCuotasKeyPressed

    private boolean verificarPresenciaDeRun() {
        boolean runExiste = false;

        String rutABuscar = txtRun.getText();

        for (Matricula m : matriculas) {
            if (m.getAlumno().getRun().toLowerCase().contains(rutABuscar)) {
                runExiste = true;

            }

        }
        return runExiste;
    }

    private boolean verificarPresenciaDeDatos() {

        boolean informacionValida = true;

        if (txtRun.getText().isEmpty()) {
            informacionValida = false;
        }

        if (txtNombre.getText().isEmpty()) {
            informacionValida = false;
        }

        if (txtDireccion.getText().isEmpty()) {
            informacionValida = false;
        }

        if (txtMatricula.getText().isEmpty()) {
            informacionValida = false;
        }

        if (txtTotalFinal.getText().isEmpty()) {
            informacionValida = false;
        }

        if (txtNCuotas.getText().isEmpty()) {
            informacionValida = false;
        }

        if (txtNCuotas.getText().isEmpty()) {
            informacionValida = false;
        }

        try {
            Integer.parseInt(txtNCuotas.getText());
        } catch (NumberFormatException e) {
            informacionValida = false;
        }

        if (txtValorCuota.getText().isEmpty()) {
            informacionValida = false;
        }

        return informacionValida;

    }


    private void btnMatricularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatricularActionPerformed
        boolean todoOk = verificarPresenciaDeDatos();
        boolean rutExiste = verificarPresenciaDeRun();

        if (todoOk && !rutExiste) {

            try {
                String run = txtRun.getText();
                String sexo = "";
                if (rdbtnMasculino.isSelected()) {
                    sexo = "Masculino";
                    cantHombres++;
                } else if (rdbtnFemenino.isSelected()) {
                    sexo = "Femenino";
                    cantMujeres++;
                }
                String nombre = txtNombre.getText();
                String direccion = txtDireccion.getText();

                Alumno a = new Alumno(run, sexo, nombre, direccion);
                alumnos.add(a);

                String curso = String.valueOf(cboCurso.getSelectedItem());
                int precioMatricula = Integer.parseInt(txtMatricula.getText());
                int precioMensualidad = Integer.parseInt(txtMensualidad.getText());
                int precioTotal = Integer.parseInt(txtTotal.getText());
                boolean convenio = false;
                if (chkConvenio.isSelected()) {
                    convenio = true;
                } else {
                    convenio = false;
                }

                String tipoDeConvenio = "";

                if (convenio) {
                    tipoDeConvenio = String.valueOf(cboConvenio.getSelectedItem());

                } else if (!convenio) {
                    tipoDeConvenio = "Sin convenio";
                }

                int descuento = Integer.parseInt(txtDescuento.getText());
                int totalFinal = Integer.parseInt(txtTotalFinal.getText());
                int cantCuotas = Integer.parseInt(txtNCuotas.getText());
                int valorCuota = Integer.parseInt(txtValorCuota.getText());

                Matricula m = new Matricula(a, curso, precioMatricula, precioMensualidad, precioTotal, convenio, tipoDeConvenio, descuento, totalFinal, cantCuotas, valorCuota);

                writeJavaObject(conn, m);

                msgDeRegistro();

                limpiarForumulario();
                bloquearTodoMenosCampoRunYBTNbuscar();
            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (!todoOk) {
            msgDeFaltaDeDatos();
        } else if (todoOk && rutExiste) {
            msgDeRutRepetido();
        }


    }//GEN-LAST:event_btnMatricularActionPerformed

    private void bloquearTodoMenosCampoRunYBTNbuscar() {

        rdbtnFemenino.setEnabled(false);
        rdbtnMasculino.setEnabled(false);
        txtNombre.setEnabled(false);
        txtDireccion.setEnabled(false);
        cboCurso.setEnabled(false);
        chkConvenio.setEnabled(false);
        txtDescuento.setEnabled(false);
        txtNCuotas.setEnabled(false);
        btnMatricular.setEnabled(false);

    }

    private void abilitarTodoMenosCampoRunYBTNbuscar() {

        rdbtnFemenino.setEnabled(true);
        rdbtnMasculino.setEnabled(true);
        txtNombre.setEnabled(true);
        txtDireccion.setEnabled(true);
        cboCurso.setEnabled(true);
        chkConvenio.setEnabled(true);
        txtNCuotas.setEnabled(true);
        btnMatricular.setEnabled(true);

    }

    private void bloquearTodosLosCamposYBotonesEC() {
        txtRun.setEnabled(false);
        rdbtnFemenino.setEnabled(false);
        rdbtnMasculino.setEnabled(false);
        txtNombre.setEnabled(false);
        txtDireccion.setEnabled(false);
        cboCurso.setEnabled(false);
        chkConvenio.setEnabled(false);
        txtDescuento.setEnabled(false);
        txtNCuotas.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnMatricular.setEnabled(false);

        mostrarBtnCancelar();

    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        try {
            matriculas.clear();
            int cantRegistros = determinarCantRegistros();
            for (int i = 0; i < cantRegistros; i++) {
                try {
                    matriculas.add((Matricula) readJavaObject(conn, i + 1));
                } catch (Exception ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String rutABuscar = txtRun.getText();
            boolean seEncontro = false;

            for (Matricula m : matriculas) {
                if (m.getAlumno().getRun().toLowerCase().contains(rutABuscar)) {
                    seEncontro = true;

                    if (seEncontro) {

                        if (m.getAlumno().getSexo().equals("Masculino")) {
                            rdbtnMasculino.isSelected();
                        } else {
                            rdbtnFemenino.isSelected();
                        }

                        txtNombre.setText(m.getAlumno().getNombre());
                        txtDireccion.setText(m.getAlumno().getDireccion());

                        if (m.getCurso().equals("Hardstyle Shuffle")) {
                            cboCurso.setSelectedIndex(1);
                        } else if (m.getCurso().equals("Jumpstyle")) {
                            cboCurso.setSelectedIndex(2);
                        } else if (m.getCurso().equals("Tecktonik")) {
                            cboCurso.setSelectedIndex(3);
                        }

                        txtMatricula.setText(String.valueOf(m.getPrecioMatricula()));
                        txtMensualidad.setText(String.valueOf(m.getPrecioMensualidad()));
                        txtTotal.setText(String.valueOf(m.getPrecioTotal()));

                        if (m.isConvenio()) {

                            chkConvenio.setSelected(true);

                            if (m.getTipoConvenio().equals(convenios.get(1))) {
                                cboConvenio.setSelectedIndex(1);
                            } else if (m.getTipoConvenio().equals(convenios.get(2))) {
                                cboConvenio.setSelectedIndex(2);
                            }

                            txtDescuento.setText(String.valueOf(m.getDescuento()));

                        } else if (!m.isConvenio()) {

                            chkConvenio.setSelected(false);
                            cboConvenio.setSelectedIndex(0);
                            txtDescuento.setText("0");
                        }

                        txtTotalFinal.setText(String.valueOf(m.getTotalFinal()));
                        txtNCuotas.setText(String.valueOf(m.getCantCuotas()));
                        txtValorCuota.setText(String.valueOf(m.getTotalCuota()));

                        bloquearTodosLosCamposYBotonesEC();

                    }

                }

            }

            if (!seEncontro) {
                msgDeNoEncontrado();
                txtRun.requestFocus();
                abilitarTodoMenosCampoRunYBTNbuscar();

            }

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnBuscarActionPerformed


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        txtRun.setEnabled(true);
        btnBuscar.setEnabled(true);

        bloquearTodoMenosCampoRunYBTNbuscar();

        limpiarForumulario();
        ocultarBtnCancelar();


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void msgDeNoEncontrado() {
        String titulo = "Mensaje";
        String msg = "Ese run no esta registrado; lo puede ingresar.";
        int tipo_de_msg = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, msg, titulo, tipo_de_msg);
    }

    private void msgDeFaltaDeDatos() {
        String titulo = "Error";
        String msg = "Verifique que los datos hayan sido correctamente ingresados (No olvide presionar enter al terminarde seleccionar la cantidad de cuotas). ";
        int tipo_de_msg = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, msg, titulo, tipo_de_msg);

    }

    private void msgDeRegistro() {
        String titulo = "Mensaje";
        String msg = "Alumno matriculado.";
        int tipo_de_msg = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, msg, titulo, tipo_de_msg);

    }

    private void msgDeRutRepetido() {
        String titulo = "Error";
        String msg = "El run ingresado ya esta registrado.";
        int tipo_de_msg = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, msg, titulo, tipo_de_msg);

    }

    private void msgDeRutErrorCampoDeNCuotas() {
        String titulo = "Error";
        String msg = "Debe seleccionar un numero entero para las cuotas.";
        int tipo_de_msg = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, msg, titulo, tipo_de_msg);

    }

    private void limpiarForumulario() {
        txtRun.setText("");
        rdbtnMasculino.setSelected(true);
        txtNombre.setText("");
        txtDireccion.setText("");
        cboCurso.setSelectedIndex(0);
        chkConvenio.setSelected(false);
        cboConvenio.setEnabled(false);
        pnlConvenio.setEnabled(false);
        txtDescuento.setText("0");
        txtNCuotas.setText("");
        txtValorCuota.setText("");
        cboConvenio.setSelectedIndex(0);
        txtRun.requestFocus();

    }

    private void initConvenios() {
        convenios = new ArrayList<>();

        convenios.add(new Convenio("Ningun convenio", 0));
        convenios.add(new Convenio("Caja los andes", 5));
        convenios.add(new Convenio("Caja los heroes", 7));

        cboConvenio.removeAllItems();

        for (Convenio c : convenios) {
            cboConvenio.addItem(c.toString());
        }

    }

    private void initCursos() {
        cursos = new ArrayList<>();

        cursos.add(new Curso("Seleccione curso...", 0, 0, 0));
        cursos.add(new Curso("Hardstyle Shuffle", 80000, 40000, 6));
        cursos.add(new Curso("Jumpstyle", 60000, 50000, 4));
        cursos.add(new Curso("Tecktonik", 90000, 60000, 3));

        cboCurso.removeAllItems();

        for (Curso curso : cursos) {
            cboCurso.addItem(curso.getNombre());
        }

    }

    private int calcularValorCuotas(int precio, int cantidadDeCuotas) {
        int resultado = precio / cantidadDeCuotas;

        return resultado;

    }

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.ButtonGroup btnGroupSexo;
    private javax.swing.JButton btnMatricular;
    private javax.swing.JComboBox<String> cboConvenio;
    private javax.swing.JComboBox<String> cboCurso;
    private javax.swing.JCheckBox chkConvenio;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblMatricula;
    private javax.swing.JLabel lblMensualidad;
    private javax.swing.JLabel lblNCuotas;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRun;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTotalCuota;
    private javax.swing.JLabel lblTotalCurso;
    private javax.swing.JLabel lblTotalFinal;
    private javax.swing.JMenu menuBtnArchivo;
    private javax.swing.JMenuItem menuEstadisticas;
    private javax.swing.JMenuBar mnuP;
    private javax.swing.JPanel pnlConvenio;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JRadioButton rdbtnFemenino;
    private javax.swing.JRadioButton rdbtnMasculino;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtMensualidad;
    private javax.swing.JTextField txtNCuotas;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRun;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalFinal;
    private javax.swing.JTextField txtValorCuota;
    // End of variables declaration//GEN-END:variables

}
