/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_04_arsyaalifianwidiatmoko;

/**
 *
 * @author Lenovo
 */
public class FirstPage extends javax.swing.JFrame {

    public static FirstPage instance;
    
    
    String as = null;
    public FirstPage() {
        initComponents();
        instance = this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Admin = new javax.swing.JLabel();
        Bank = new javax.swing.JLabel();
        User = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AdminMouseReleased(evt);
            }
        });
        getContentPane().add(Admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 130, 90));

        Bank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BankMouseReleased(evt);
            }
        });
        getContentPane().add(Bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 130, 90));

        User.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                UserMouseReleased(evt);
            }
        });
        getContentPane().add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 130, 90));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 40, 50));

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel3MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 40, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/First Menu.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserMouseReleased
        // TODO add your handling code here:
        as = "User";
        LoginRegister log = new LoginRegister();
        log.setVisible(true);
        System.out.println(as);
        this.setVisible(false);
        return;
    }//GEN-LAST:event_UserMouseReleased

    private void jLabel3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseReleased
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseReleased

    private void BankMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BankMouseReleased
        // TODO add your handling code here:
        as = "Bank";
        LoginRegister log = new LoginRegister();
        log.setVisible(true);
        System.out.println(as);
        this.setVisible(false);
        return;
    }//GEN-LAST:event_BankMouseReleased

    private void AdminMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminMouseReleased
        // TODO add your handling code here:
        as = "Admin";
        LoginRegister log =new LoginRegister();
        log.setVisible(true);
        System.out.println(as);
        this.setVisible(false);
        return;
    }//GEN-LAST:event_AdminMouseReleased

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
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FirstPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Admin;
    private javax.swing.JLabel Bank;
    private javax.swing.JLabel User;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
