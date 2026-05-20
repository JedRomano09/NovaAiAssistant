
package com.mycompany.nova;
import SignUp.SignUp;
import com.mycompany.library_management_system.dbConnection2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import dashboard.dashboard;
import java.sql.DriverManager;
import java.sql.SQLException;
import admin.admin;
import java.sql.Timestamp;

public class Login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());

   dbConnection2 dbConn = new dbConnection2(); 
    
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);   
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblForgotPassword = new javax.swing.JLabel();
        loginbtn = new javax.swing.JButton();
        Signupbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(135, 206, 235));
        setSize(new java.awt.Dimension(200, 350));

        jPanel3.setBackground(new java.awt.Color(26, 32, 44));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username:");

        lblForgotPassword.setBackground(new java.awt.Color(102, 102, 255));
        lblForgotPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblForgotPassword.setForeground(new java.awt.Color(51, 51, 255));
        lblForgotPassword.setText("Forgot Password?");
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgotPasswordMouseClicked(evt);
            }
        });

        loginbtn.setBackground(new java.awt.Color(0, 0, 0));
        loginbtn.setForeground(new java.awt.Color(255, 255, 255));
        loginbtn.setText("Login");
        loginbtn.addActionListener(this::loginbtnActionPerformed);

        Signupbtn.setBackground(new java.awt.Color(0, 0, 0));
        Signupbtn.setForeground(new java.awt.Color(255, 255, 255));
        Signupbtn.setText("Sign up");
        Signupbtn.addActionListener(this::SignupbtnActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Signupbtn)
                        .addGap(15, 15, 15)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(txtPassword))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(205, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(loginbtn)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(72, 72, 72)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblForgotPassword)
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginbtn)
                    .addComponent(Signupbtn))
                .addContainerGap(227, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(257, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SignupbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignupbtnActionPerformed
        new Signup().setVisible(true);
    }//GEN-LAST:event_SignupbtnActionPerformed

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        Connection conn = null;

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty, type Username or Password!");
            return;
        }

        try {
            conn = dbConn.getConnection();

            
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                
                String type = rs.getString("usertype");
                JOptionPane.showMessageDialog(null, "Login Successful! Welcome " + username);
                new NovaAiAssistant().setVisible(true);
                this.dispose();
                
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException ex) {}
        }
    }//GEN-LAST:event_loginbtnActionPerformed

    private void lblForgotPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgotPasswordMouseClicked
        new ForgotPass().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblForgotPasswordMouseClicked

  
     
    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {                                            
       
    }                                           
    
    public class ForgotPassword extends javax.swing.JFrame {

    public ForgotPassword() {
        initComponents();
        
        txtUsername.requestFocus();
    }
    }
    
    public static Connection getConnection() {
    Connection conn = null;
    try {
        
        String url = "jdbc:mysql://localhost:3306/jdbc:mysql://library_db";
        String user = "root"; 
        String pass = "";   
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pass);
    } catch (Exception e) {
        System.out.println("Connection Error: " + e.getMessage());
    }
    return conn;
}
    
    
   
   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Signupbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblForgotPassword;
    private javax.swing.JButton loginbtn;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
