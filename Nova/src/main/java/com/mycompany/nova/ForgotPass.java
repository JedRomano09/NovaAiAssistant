
package com.mycompany.nova;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ForgotPass extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ForgotPass.class.getName());

    
    public ForgotPass() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRetypePass = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        btnSavePass = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtCurrentPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(135, 206, 235));

        jPanel1.setBackground(new java.awt.Color(26, 32, 44));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Current Password:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("New Passoword:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Retype New Password:");

        btnSavePass.setBackground(new java.awt.Color(0, 0, 0));
        btnSavePass.setForeground(new java.awt.Color(255, 255, 255));
        btnSavePass.setText("Save Password");
        btnSavePass.addActionListener(this::btnSavePassActionPerformed);

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back ");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRetypePass, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsername)
                                    .addComponent(txtCurrentPass)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtNewPass)))))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSavePass)
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCurrentPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRetypePass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnBack))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnSavePass)))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
     txtUsername.setText("");
    txtCurrentPass.setText("");
    txtNewPass.setText("");
    txtRetypePass.setText("");
    
    new Login().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSavePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePassActionPerformed
    String user = txtUsername.getText();
    String currentPass = String.valueOf(txtCurrentPass.getPassword());
    String newPass = String.valueOf(txtNewPass.getPassword());
    String retypePass = String.valueOf(txtRetypePass.getPassword());
    
   
    if(user.isEmpty() || currentPass.isEmpty() || newPass.isEmpty() || retypePass.isEmpty()){
        JOptionPane.showMessageDialog(this, "Please fill up all fields!");
        return;
    }
    
   
    if(!newPass.equals(retypePass)){
        JOptionPane.showMessageDialog(this, "New Password does not match!");
        txtRetypePass.setText("");
        return;
    }
    
    try{
        String url = "jdbc:mysql://localhost:3306/library_db";
        String dbUser = "root";
        String dbPass = "";
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
        
        
        String checkUserQuery = "SELECT * FROM users WHERE username=?";
        PreparedStatement checkUserPst = conn.prepareStatement(checkUserQuery);
        checkUserPst.setString(1, user);
        ResultSet rsUser = checkUserPst.executeQuery();
        
        if(!rsUser.next()){ 

            JOptionPane.showMessageDialog(this, "Username not found!");
            txtUsername.setText("");
            txtUsername.requestFocus();
            return;
        }
        
       
        String checkPassQuery = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement checkPassPst = conn.prepareStatement(checkPassQuery);
        checkPassPst.setString(1, user);
        checkPassPst.setString(2, currentPass);
        ResultSet rsPass = checkPassPst.executeQuery();
        
        if(!rsPass.next()){ 
          
            JOptionPane.showMessageDialog(this, "Current Password does not match!");
            txtCurrentPass.setText("");
            txtCurrentPass.requestFocus();
            return;
        }
        
      
        String updateQuery = "UPDATE users SET password=? WHERE username=?";
        PreparedStatement updatePst = conn.prepareStatement(updateQuery);
        updatePst.setString(1, newPass);
        updatePst.setString(2, user);
        updatePst.executeUpdate();
        
        JOptionPane.showMessageDialog(this, "Password Changed Successfully!");
        new Login().setVisible(true);
        this.dispose();
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnSavePassActionPerformed

    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(() -> new ForgotPass().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSavePass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtCurrentPass;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtRetypePass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
