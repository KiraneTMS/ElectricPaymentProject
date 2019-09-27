/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.folder;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class HomeAdmin extends javax.swing.JFrame {

    /**
     * Creates new form HomePelanggan
     */
    Connection koneksi;
    String action, username, id_tagihan, id_pembayaran, id_admin;
    DefaultTableModel dtm;
    public HomeAdmin(String act) {
        initComponents();
        koneksi = Koneksi.getKoneksi("localhost", "3306", "root", "", "ppob");
        tabel.getTableHeader().setFont(new Font ("Segoe UI", Font.BOLD, 11));
        tabel.getTableHeader().setOpaque(false);
        tabel.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tabel.setRowHeight(25);
        tabel.setBackground(Color.WHITE);
        action = act;
        if (action.equals("Data Pelanggan")) {
            btnVerifikasi.setVisible(false);
            btnTambah.setVisible(false);
            btnEdit.setVisible(false);
            lblJudul.setText("DATA PELANGGAN");
            PLaporan.setVisible(false);
            ShowDataPelanggan();
        } else if (action.equals("Data Tagihan")) {
            btnVerifikasi.setVisible(false);
            lblJudul.setText("DATA TAGIHAN");
            PLaporan.setVisible(false);
            ShowDataTagihan();
        } else if (action.equals("Data Pembayaran")) {
            btnTambah.setVisible(false);
            btnHapus.setVisible(false);
            btnEdit.setVisible(false);
            lblJudul.setText("DATA PEMBAYARAN");
            ShowDataPembayaran();
        } else if(action.equals("Data Admin")) {
            btnVerifikasi.setVisible(false);
            btnEdit.setVisible(false);
            btnHapus.setVisible(false);
            lblJudul.setText("DATA ADMIN");
            PLaporan.setVisible(false);
            ShowDataAdmin();
        }
    }

    private HomeAdmin() {
        initComponents();
        koneksi = Koneksi.getKoneksi("localhost", "3306", "root", "", "ppob");
    }
    
    public void ShowDataPelanggan() {
        String[] kolom = {"NO", "USERNAME", "PASSWORD", "NAMA PELANGGAN", "NO KWH", "ALAMAT", "DAYA"};
        
        dtm = new DefaultTableModel(null, kolom);
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM pelanggan, tarif WHERE tarif.id_tarif = pelanggan.id_tarif ORDER BY pelanggan.id_pelanggan";
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {
                username = rs.getString("username");
                String password = rs.getString("password");
                String nama_pelanggan = rs.getString("nama_pelanggan");
                String no_kwh = rs.getString("no_kwh");
                String alamat = rs.getString("alamat");
                String daya = rs.getString("daya");
                
                dtm.addRow(new String[] {no + "", username, password, nama_pelanggan, no_kwh, alamat, daya});
                no++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        tabel.setModel(dtm);
    }

    public void ShowDataTagihan() {
        String[] kolom = {"NO", "ID TAGIHAN", "NAMA PELANGGAN", "BULAN", "TAHUN", "JUMLAH METER", "BIAYA TAGIHAN", "STATUS"};
        
        dtm = new DefaultTableModel(null, kolom);
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM pelanggan, tagihan, tarif WHERE pelanggan.id_pelanggan = tagihan.id_pelanggan AND tarif.id_tarif = pelanggan.id_tarif";
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {
                id_tagihan = rs.getString("id_tagihan");
                String nama_pelanggan = rs.getString("nama_pelanggan");
                String bulan = rs.getString("bulan");
                String tahun = rs.getString("tahun");
                String status = rs.getString("status");
                int jumlah_meter = rs.getInt("jumlah_meter");
                int biaya_tagihan = jumlah_meter * rs.getInt("tarifperkwh");
                
                dtm.addRow(new String[] {no + "", id_tagihan, nama_pelanggan, bulan, tahun, String.valueOf(jumlah_meter), String.valueOf(biaya_tagihan), status});
                no++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        tabel.setModel(dtm);
    }

    public void ShowDataPembayaran() {
        String[] kolom = {"NO", "ID TAGIHAN", "NAMA PELANGGAN", "BULAN", "TAHUN", "TANGGAL", "TOTAL", "STATUS"};
        
        dtm = new DefaultTableModel(null, kolom);
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM pelanggan, tagihan, pembayaran WHERE pelanggan.id_pelanggan = tagihan.id_pelanggan AND tagihan.id_tagihan = pembayaran.id_tagihan";
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {
                String nama_pelanggan = rs.getString("nama_pelanggan");
                String bulan = rs.getString("bulan");
                String tahun = rs.getString("tahun");
                String tanggal = rs.getString("tanggal_pembayaran");
                String total = rs.getString("total");
                id_tagihan = rs.getString("id_tagihan");
                String status = rs.getString("status");
                
                dtm.addRow(new String[] {no + "", id_tagihan, nama_pelanggan, bulan, tahun, tanggal, total, status});
                no++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        tabel.setModel(dtm);
    }

    public void ShowDataAdmin() {
        String[] kolom = {"NO", "ID ADMIN", "USERNAME", "PASSWORD", "NAMA ADMIN"};
        
        dtm = new DefaultTableModel(null, kolom);
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM admin";
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nama_admin = rs.getString("nama_admin");
                String id_admin = rs.getString("id_admin");
                
                dtm.addRow(new String[] {no + "", id_admin, username, password, nama_admin});
                no++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        tabel.setModel(dtm);
    }
    
    public void HapusPelanggan(){
        String user = tabel.getValueAt(row, 1).toString();
        try {
            Statement stmt = koneksi.createStatement();
            String query = "DELETE FROM pelanggan WHERE username = '"+ user +"'";
            int sukses = stmt.executeUpdate(query);
            if (sukses == 1) {
                dtm.getDataVector().removeAllElements();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                HomeAdmin ha = new HomeAdmin("Data Pelanggan");
                ha.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void HapusTagihan() {
        String id_tagih = tabel.getValueAt(row, 1).toString();
        try {
            Statement stmt = koneksi.createStatement();
            String query = "DELETE FROM tagihan WHERE id_tagihan = '"+ id_tagih +"'";
            int sukses = stmt.executeUpdate(query);
            if (sukses == 1) {
                dtm.getDataVector().removeAllElements();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                HomeAdmin ha = new HomeAdmin("Data Tagihan");
                ha.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnVerifikasi = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        PDataPelanggan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PDataTagihan = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PDataPembayaran = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        PDataAdmin = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        PLogout = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        PLaporan = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel2.setBackground(new java.awt.Color(61, 126, 190));

        lblJudul.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(255, 255, 255));
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("DATA PELANGGAN");

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
        jScrollPane1.setViewportView(tabel);

        btnTambah.setBackground(new java.awt.Color(204, 204, 204));
        btnTambah.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(61, 126, 190));
        btnTambah.setText("Tambah");
        btnTambah.setBorder(null);
        btnTambah.setBorderPainted(false);
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.setFocusable(false);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(204, 204, 204));
        btnEdit.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(61, 126, 190));
        btnEdit.setText("Edit");
        btnEdit.setBorder(null);
        btnEdit.setBorderPainted(false);
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setFocusable(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnVerifikasi.setBackground(new java.awt.Color(204, 204, 204));
        btnVerifikasi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnVerifikasi.setForeground(new java.awt.Color(61, 126, 190));
        btnVerifikasi.setText("Verifikasi");
        btnVerifikasi.setBorder(null);
        btnVerifikasi.setBorderPainted(false);
        btnVerifikasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerifikasi.setFocusable(false);
        btnVerifikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifikasiActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(204, 204, 204));
        btnHapus.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(61, 126, 190));
        btnHapus.setText("Hapus");
        btnHapus.setBorder(null);
        btnHapus.setBorderPainted(false);
        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.setFocusable(false);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(61, 126, 190));
        jLabel8.setText("X");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblJudul))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(btnVerifikasi, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        PDataPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PDataPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PDataPelangganMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(61, 126, 190));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Data Pelanggan");

        javax.swing.GroupLayout PDataPelangganLayout = new javax.swing.GroupLayout(PDataPelanggan);
        PDataPelanggan.setLayout(PDataPelangganLayout);
        PDataPelangganLayout.setHorizontalGroup(
            PDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PDataPelangganLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        PDataPelangganLayout.setVerticalGroup(
            PDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PDataPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PDataTagihan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PDataTagihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PDataTagihanMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(61, 126, 190));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Data Tagihan");

        javax.swing.GroupLayout PDataTagihanLayout = new javax.swing.GroupLayout(PDataTagihan);
        PDataTagihan.setLayout(PDataTagihanLayout);
        PDataTagihanLayout.setHorizontalGroup(
            PDataTagihanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PDataTagihanLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        PDataTagihanLayout.setVerticalGroup(
            PDataTagihanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PDataTagihanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PDataPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PDataPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PDataPembayaranMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(61, 126, 190));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Data Pembayaran");

        javax.swing.GroupLayout PDataPembayaranLayout = new javax.swing.GroupLayout(PDataPembayaran);
        PDataPembayaran.setLayout(PDataPembayaranLayout);
        PDataPembayaranLayout.setHorizontalGroup(
            PDataPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PDataPembayaranLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        PDataPembayaranLayout.setVerticalGroup(
            PDataPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PDataPembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PDataAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PDataAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PDataAdminMouseClicked(evt);
            }
        });

        label.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label.setForeground(new java.awt.Color(61, 126, 190));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setText("Data Admin");

        javax.swing.GroupLayout PDataAdminLayout = new javax.swing.GroupLayout(PDataAdmin);
        PDataAdmin.setLayout(PDataAdminLayout);
        PDataAdminLayout.setHorizontalGroup(
            PDataAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PDataAdminLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PDataAdminLayout.setVerticalGroup(
            PDataAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PDataAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PLogoutMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(61, 126, 190));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Logout");

        javax.swing.GroupLayout PLogoutLayout = new javax.swing.GroupLayout(PLogout);
        PLogout.setLayout(PLogoutLayout);
        PLogoutLayout.setHorizontalGroup(
            PLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogoutLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PLogoutLayout.setVerticalGroup(
            PLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PLogoutLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        PLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PLaporanMouseClicked(evt);
            }
        });

        label1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(61, 126, 190));
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("Laporan");

        javax.swing.GroupLayout PLaporanLayout = new javax.swing.GroupLayout(PLaporan);
        PLaporan.setLayout(PLaporanLayout);
        PLaporanLayout.setHorizontalGroup(
            PLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLaporanLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        PLaporanLayout.setVerticalGroup(
            PLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PDataTagihan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PDataPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PDataAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PDataPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PDataPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PDataTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PDataPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PDataAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        this.dispose();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void PLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLogoutMouseClicked
        Login l = new Login("Admin");
        l.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_PLogoutMouseClicked

    private void PDataPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PDataPelangganMouseClicked
        HomeAdmin ha = new HomeAdmin("Data Pelanggan");
        ha.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_PDataPelangganMouseClicked

    private void PDataTagihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PDataTagihanMouseClicked
        HomeAdmin ha = new HomeAdmin("Data Tagihan");
        ha.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_PDataTagihanMouseClicked

    private void PDataPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PDataPembayaranMouseClicked
        HomeAdmin ha = new HomeAdmin("Data Pembayaran");
        ha.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_PDataPembayaranMouseClicked

    private void PDataAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PDataAdminMouseClicked
        HomeAdmin ha = new HomeAdmin("Data Admin");
        ha.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_PDataAdminMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        String status = tabel.getValueAt(row, 7).toString();
        if (action.equals("Data Pelanggan")) {
            HapusPelanggan();
        } else if(action.equals("Data Tagihan")) {
            if (!status.equals("Belum Lunas")) {
                JOptionPane.showMessageDialog(null, "Tagihan Sudah Dibayar, Tidak Bisa Dihapus");
                HomeAdmin ha = new HomeAdmin("Data Tagihan");
                ha.setVisible(true);
                this.setVisible(false);
            }
            HapusTagihan();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    int row;
    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        row = tabel.getSelectedRow();
    }//GEN-LAST:event_tabelMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        if (action.equals("Data Tagihan")) {
            ManageDataTagihan mdt = new ManageDataTagihan("Tambah", "");
            mdt.setVisible(true);
            this.setVisible(false);
        } else if (action.equals("Data Admin")) {
            ManageDataAdmin mda = new ManageDataAdmin();
            mda.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String id_tagihan = tabel.getValueAt(row, 1).toString();
        String status = tabel.getValueAt(row, 7).toString();
        if (action.equals("Data Tagihan")) {
            if (!status.equals("Belum Lunas")) {
                JOptionPane.showMessageDialog(null, "Tagihan Sudah Dibayar, Tidak Bisa Diedit");
                HomeAdmin ha = new HomeAdmin("Data Tagihan");
                ha.setVisible(true);
                this.setVisible(false);
            } else {
                ManageDataTagihan mdt = new ManageDataTagihan("Edit", id_tagihan);
                mdt.setVisible(true);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnVerifikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifikasiActionPerformed
        String id_tagihan2 = tabel.getValueAt(row, 1).toString();
        String status = tabel.getValueAt(row, 7).toString();
        if (status.equals("Pending Admin")) {
            try {
                Statement stmt = koneksi.createStatement();
                String query = "UPDATE tagihan SET status = 'Lunas' WHERE id_tagihan = '"+ id_tagihan2 +"'";
                int sukses = stmt.executeUpdate(query);
                if (sukses == 1) {
                    JOptionPane.showMessageDialog(null, "Verifikasi Berhasil");
                    HomeAdmin ha = new HomeAdmin("Data Pembayaran");
                    ha.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Verifikasi Gagal");
                }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
        } else if (status.equals("Pending Bank")) {
            JOptionPane.showMessageDialog(null, "Tunggu Verifikasi Bank");
            HomeAdmin ha = new HomeAdmin("Data Pembayran");
            ha.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Tunggu Pelanggan Membayar");
            HomeAdmin ha = new HomeAdmin("Data Pembayran");
            ha.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnVerifikasiActionPerformed

    private void PLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLaporanMouseClicked
        Laporan l = new Laporan();
        l.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_PLaporanMouseClicked

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
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PDataAdmin;
    private javax.swing.JPanel PDataPelanggan;
    private javax.swing.JPanel PDataPembayaran;
    private javax.swing.JPanel PDataTagihan;
    private javax.swing.JPanel PLaporan;
    private javax.swing.JPanel PLogout;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnVerifikasi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JTable tabel;
    // End of variables declaration//GEN-END:variables

}
