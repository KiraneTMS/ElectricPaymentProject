/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_04_arsyaalifianwidiatmoko;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class UPenggunaan extends javax.swing.JFrame {

    /**
     * Creates new form UPenggunaan
     */
    DefaultTableModel dtm;
    Connection con;
    LoginRegister lr = LoginRegister.instance;
    String user = lr.iduser;

    public UPenggunaan() {
        initComponents();
        Panel.setVisible(false);
    }

    void showdata() {
        con = Koneksi.getKoneksi("localhost", "3306", "root", "", "electric");
        
        String[] kolom = {"NO", "ID PELANGGAN", "ID PENGGUNAAN", "BULAN", "TAHUN", "METER AWAL", "METER AKHIR"};
        
        dtm = new DefaultTableModel(null, kolom);
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM penggunaan WHERE id_pelanggan = '"+user+"'";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(query);
            int no = 1;
            while (rs.next()) {
                String id_pelanggan = rs.getString("id_pelanggan");
                String id_penggunaan = rs.getString("id_penggunaan");
                String bulan = rs.getString("bulan");
                String tahun = rs.getString("tahun");
                String meter_awal = rs.getString("meter_awal");
                String meter_akhir = rs.getString("meter_akhir");
                
                dtm.addRow(new String[]{no + "", id_pelanggan, id_penggunaan, bulan, tahun, meter_awal, meter_akhir});
                no++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal");
            e.printStackTrace();
        }
        tabel.setModel(dtm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Bulan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        Tahun = new javax.swing.JTextField();
        Panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Adminpanel = new javax.swing.JPanel();
        Home = new javax.swing.JLabel();
        Setting = new javax.swing.JLabel();
        Account = new javax.swing.JLabel();
        DaftarUser = new javax.swing.JLabel();
        DaftarTagihan = new javax.swing.JLabel();
        DaftarBayar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        userPanel = new javax.swing.JPanel();
        Home1 = new javax.swing.JLabel();
        Setting1 = new javax.swing.JLabel();
        Account1 = new javax.swing.JLabel();
        DaftarUser1 = new javax.swing.JLabel();
        DaftarTagihan1 = new javax.swing.JLabel();
        DaftarBayar1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel4MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 50, 50));

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel6MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 40, 50));
        getContentPane().add(Bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 100, -1));

        jLabel7.setText("Bulan");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabel8.setText("Tahun");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel9)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 90, 30));
        getContentPane().add(Tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 100, -1));

        Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel2MouseReleased(evt);
            }
        });
        Panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 30, 20));

        Adminpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Adminpanel.add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 30, 20));
        Adminpanel.add(Setting, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 70, 20));

        Account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AccountMouseReleased(evt);
            }
        });
        Adminpanel.add(Account, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, 20));

        DaftarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DaftarUserMouseReleased(evt);
            }
        });
        Adminpanel.add(DaftarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 70, 20));
        Adminpanel.add(DaftarTagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 20));
        Adminpanel.add(DaftarBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AdminMenu.jpg"))); // NOI18N
        Adminpanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 150));

        Panel.add(Adminpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 150));

        userPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        userPanel.add(Home1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 30, 20));
        userPanel.add(Setting1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 70, 20));
        userPanel.add(Account1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, 20));
        userPanel.add(DaftarUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 70, 20));
        userPanel.add(DaftarTagihan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 10));
        userPanel.add(DaftarBayar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 10));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/UserMenu.jpg"))); // NOI18N
        userPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 150));

        Panel.add(userPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 150));

        getContentPane().add(Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 100, 150));

        tabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel.setFocusable(false);
        tabel.setRowHeight(25);
        tabel.setSelectionBackground(new java.awt.Color(61, 126, 190));
        tabel.setShowVerticalLines(false);
        tabel.getTableHeader().setReorderingAllowed(false);
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tabel);

        getContentPane().add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 570, 130));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Menu.jpg"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        // TODO add your handling code here:
        Panel.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseReleased

    private void jLabel4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseReleased
        // TODO add your handling code here:
        Panel.setVisible(true);
        FirstPage fp = FirstPage.instance;
        if (fp.as.equals("Admin")) {
            Adminpanel.setVisible(true);
        }
        if (fp.as.equals("User")) {
            Adminpanel.setVisible(false);
            userPanel.setVisible(true);
        }

    }//GEN-LAST:event_jLabel4MouseReleased

    private void jLabel6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseReleased
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseReleased

    private void jLabel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseReleased
        // TODO add your handling code here:
        HomePage hp = new HomePage();
        hp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseReleased

    private void AccountMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccountMouseReleased
        // TODO add your handling code here:
        AccControl ac = new AccControl();
        ac.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AccountMouseReleased

    private void DaftarUserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DaftarUserMouseReleased
        // TODO add your handling code here:
        UserList ul = new UserList();
        ul.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_DaftarUserMouseReleased
    
    int row;
    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        row = tabel.getSelectedRow();
    }//GEN-LAST:event_tabelMouseClicked

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
        // TODO add your handling code here:
        showdata();
    }//GEN-LAST:event_jPanel1MouseReleased

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
            java.util.logging.Logger.getLogger(UPenggunaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UPenggunaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UPenggunaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UPenggunaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UPenggunaan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Account;
    private javax.swing.JLabel Account1;
    private javax.swing.JPanel Adminpanel;
    private javax.swing.JTextField Bulan;
    private javax.swing.JLabel DaftarBayar;
    private javax.swing.JLabel DaftarBayar1;
    private javax.swing.JLabel DaftarTagihan;
    private javax.swing.JLabel DaftarTagihan1;
    private javax.swing.JLabel DaftarUser;
    private javax.swing.JLabel DaftarUser1;
    private javax.swing.JLabel Home;
    private javax.swing.JLabel Home1;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel Setting;
    private javax.swing.JLabel Setting1;
    private javax.swing.JTextField Tahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JTable tabel;
    private javax.swing.JPanel userPanel;
    // End of variables declaration//GEN-END:variables
}
