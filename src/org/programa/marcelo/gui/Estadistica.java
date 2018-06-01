
package org.programa.marcelo.gui;


public class Estadistica extends javax.swing.JFrame {

   
    public Estadistica() {
        initComponents();
        
    }

   
    public void fijarDatosHard(int cantM, int cantH, int recaudacionCurso){
        txtMHard.setText(String.valueOf(cantM));
        txtHHard.setText(String.valueOf(cantH));
        txtRHard.setText(String.valueOf(recaudacionCurso));
        txtTAH.setText(String.valueOf(cantM+cantH));
        
        
    }
    
      
    public void fijarDatosJump(int cantM, int cantH, int recaudacionCurso){
        txtMJump.setText(String.valueOf(cantM));
        txtHJump.setText(String.valueOf(cantH));
        txtRJump.setText(String.valueOf(recaudacionCurso));
        txtTAJ.setText(String.valueOf(cantM+cantH));
        
        
        
    }
    
      
    public void fijarDatosTec(int cantM, int cantH, int recaudacionCurso){
        txtMTec.setText(String.valueOf(cantM));
        txtHTec.setText(String.valueOf(cantH));
        txtRTec.setText(String.valueOf(recaudacionCurso));
        txtTAT.setText(String.valueOf(cantM+cantH));
        
        
        
    }
    
    public int calcularYFijarRecaudacionFinal(int recH, int recJ, int recT){
        int recaudacionFinal=recH+recJ+recT;
        txtRFinal.setText(String.valueOf(recaudacionFinal));
        return recaudacionFinal;
    }
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblH = new javax.swing.JLabel();
        lblJ = new javax.swing.JLabel();
        lblTAT = new javax.swing.JLabel();
        lblRF = new javax.swing.JLabel();
        lblHH = new javax.swing.JLabel();
        lblMH = new javax.swing.JLabel();
        lblMJ = new javax.swing.JLabel();
        lblHJ = new javax.swing.JLabel();
        lblMT = new javax.swing.JLabel();
        lblHT = new javax.swing.JLabel();
        txtMHard = new javax.swing.JTextField();
        txtHHard = new javax.swing.JTextField();
        txtMJump = new javax.swing.JTextField();
        txtHJump = new javax.swing.JTextField();
        txtMTec = new javax.swing.JTextField();
        txtHTec = new javax.swing.JTextField();
        txtRFinal = new javax.swing.JTextField();
        lblRH = new javax.swing.JLabel();
        lblRJ = new javax.swing.JLabel();
        lblRT = new javax.swing.JLabel();
        txtRHard = new javax.swing.JTextField();
        txtRJump = new javax.swing.JTextField();
        txtRTec = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblTAH = new javax.swing.JLabel();
        lblTAJ = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTAH = new javax.swing.JTextField();
        txtTAJ = new javax.swing.JTextField();
        txtTAT = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblH.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblH.setText("Curso: Hardstyle Shuffle");

        lblJ.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblJ.setText("Curso: Jumpstyle");

        lblTAT.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTAT.setText("Curso: Tectonik ");

        lblRF.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblRF.setText("Recaudacion FINAL:");

        lblHH.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblHH.setForeground(new java.awt.Color(0, 0, 255));
        lblHH.setText("Hombres: ");

        lblMH.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblMH.setForeground(new java.awt.Color(255, 0, 204));
        lblMH.setText("Mujeres:");

        lblMJ.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblMJ.setForeground(new java.awt.Color(255, 0, 204));
        lblMJ.setText("Mujeres:");

        lblHJ.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblHJ.setForeground(new java.awt.Color(0, 0, 255));
        lblHJ.setText("Hombres:");

        lblMT.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblMT.setForeground(new java.awt.Color(255, 0, 255));
        lblMT.setText("Mujeres:");

        lblHT.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblHT.setForeground(new java.awt.Color(0, 0, 255));
        lblHT.setText("Hombres:");

        txtMJump.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMJumpActionPerformed(evt);
            }
        });

        lblRH.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblRH.setText("Recaudacion:");

        lblRJ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblRJ.setText("Recaudacion:");

        lblRT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblRT.setText("Recaudacion: ");

        lblTAH.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTAH.setText("Total alumnos:");

        lblTAJ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTAJ.setText("Total alumnos:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total alumnos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTAT, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMT)
                                        .addComponent(lblHT))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtHTec, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                        .addComponent(txtMTec))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblMJ)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtMJump, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblHJ)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtHJump, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblTAJ)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTAJ, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRT)
                                .addGap(18, 18, 18)
                                .addComponent(txtRTec))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRJ)
                                .addGap(18, 18, 18)
                                .addComponent(txtRJump))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRH)
                                .addGap(18, 18, 18)
                                .addComponent(txtRHard, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(lblRF)
                                .addGap(32, 32, 32)
                                .addComponent(txtRFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblH, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(lblMH)
                                .addGap(18, 18, 18)
                                .addComponent(txtMHard, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblHH)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtHHard, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTAH)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTAH, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator4)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJ, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTAT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMH)
                    .addComponent(txtMHard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHH)
                    .addComponent(txtHHard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRH)
                    .addComponent(txtRHard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTAH)
                    .addComponent(txtTAH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJ, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMJ)
                    .addComponent(txtMJump, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHJ)
                    .addComponent(txtHJump, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRJ)
                            .addComponent(txtRJump, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTAJ)
                            .addComponent(txtTAJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTAT)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMT)
                    .addComponent(txtMTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRT)
                        .addComponent(txtRTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblRF))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtRFinal)
                        .addGap(1, 1, 1)))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMJumpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMJumpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMJumpActionPerformed

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
            java.util.logging.Logger.getLogger(Estadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estadistica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblH;
    private javax.swing.JLabel lblHH;
    private javax.swing.JLabel lblHJ;
    private javax.swing.JLabel lblHT;
    private javax.swing.JLabel lblJ;
    private javax.swing.JLabel lblMH;
    private javax.swing.JLabel lblMJ;
    private javax.swing.JLabel lblMT;
    private javax.swing.JLabel lblRF;
    private javax.swing.JLabel lblRH;
    private javax.swing.JLabel lblRJ;
    private javax.swing.JLabel lblRT;
    private javax.swing.JLabel lblTAH;
    private javax.swing.JLabel lblTAJ;
    private javax.swing.JLabel lblTAT;
    private javax.swing.JTextField txtHHard;
    private javax.swing.JTextField txtHJump;
    private javax.swing.JTextField txtHTec;
    private javax.swing.JTextField txtMHard;
    private javax.swing.JTextField txtMJump;
    private javax.swing.JTextField txtMTec;
    private javax.swing.JTextField txtRFinal;
    private javax.swing.JTextField txtRHard;
    private javax.swing.JTextField txtRJump;
    private javax.swing.JTextField txtRTec;
    private javax.swing.JTextField txtTAH;
    private javax.swing.JTextField txtTAJ;
    private javax.swing.JTextField txtTAT;
    // End of variables declaration//GEN-END:variables
}
