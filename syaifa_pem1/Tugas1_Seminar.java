import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Tugas1_Seminar extends JFrame {
    public Tugas1_Seminar() {
        super("Pendaftaran Seminar Informatika");

        JTextField nama = new JTextField(15);
        // Fungsi: Membuat menu pilihan
        JComboBox<String> cbTopik = new JComboBox<>(new String[]{
            "AI & Machine Learning", 
            "Cyber Security", 
            "Cloud Computing"
        });

        JRadioButton mhs = new JRadioButton("Mahasiswa 50000", true);
        JRadioButton umum = new JRadioButton("Umum 100000");
        ButtonGroup g = new ButtonGroup();
        g.add(mhs); 
        g.add(umum);

        JCheckBox ws = new JCheckBox("Workshop +30000");
        JCheckBox makan = new JCheckBox("Makan siang +25000");
        JCheckBox sert = new JCheckBox("Sertifikat +10000");
        JButton btn = new JButton("Daftar");

        // Fungsi: Menyimpan dan menampilkan riwayat data yang sudah terdaftar dalam bentuk tabel
        //         sehingga data sebelumnya tidak hilang/tertimpa saat ada data baru.
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nama", "Topik", "Kategori", "Total"}, 0);
        JTable tabel = new JTable(model);

        btn.addActionListener(e -> {
            String nm = nama.getText().trim();
            if (nm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama wajib diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int total = mhs.isSelected() ? 50000 : 100000;
            if (ws.isSelected()) { total += 30000; }
            if (makan.isSelected()) { total += 25000; }
            if (sert.isSelected()) { total += 10000; }

            int j = JOptionPane.showConfirmDialog(this, "Total Rp " + total + ". Daftar sekarang?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            if (j == JOptionPane.YES_OPTION) {
                // Fungsi: Memasukkan data pendaftar sebagai baris baru di tabel.
                String kategori = mhs.isSelected() ? "Mahasiswa" : "Umum";
                model.addRow(new Object[]{nm, cbTopik.getSelectedItem(), kategori, "Rp " + total});
                
                // Mengosongkan kembali kotak nama setelah berhasil daftar
                nama.setText("");
            }
        });

        // Fungsi: Mengatur posisi form secara rapi menggunakan koordinat baris (gridy) dan kolom (gridx),
        //         menggantikan GridLayout 1 kolom bawaan template yang menumpuk lurus ke bawah.
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Form Pendaftaran"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4); // Memberi jarak antar komponen
        c.anchor = GridBagConstraints.WEST; // Meratakan komponen ke sebelah kiri

        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Nama:"), c);
        c.gridx = 1; c.fill = GridBagConstraints.HORIZONTAL; form.add(nama, c);

        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Topik:"), c);
        c.gridx = 1; form.add(cbTopik, c);

        c.gridx = 0; c.gridy = 2; form.add(new JLabel("Kategori:"), c);
        c.gridx = 1; form.add(mhs, c);
        c.gridy = 3; form.add(umum, c);

        c.gridx = 0; c.gridy = 4; form.add(new JLabel("Tambahan:"), c);
        c.gridx = 1; form.add(ws, c);
        c.gridy = 5; form.add(makan, c);
        c.gridy = 6; form.add(sert, c);

        c.gridx = 1; c.gridy = 7; form.add(btn, c);

        // Fungsi: Membungkus JTable agar header/judul kolom tabel muncul dan bisa di-scroll jika datanya banyak.
        JScrollPane scrollTabel = new JScrollPane(tabel);
        scrollTabel.setPreferredSize(new Dimension(420, 140));

        // Fungsi: Menaruh panel form di bagian atas (NORTH) dan tabel hasil di bagian tengah (CENTER).
        setLayout(new BorderLayout(8, 8));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(form, BorderLayout.NORTH);
        add(scrollTabel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tugas1_Seminar().setVisible(true));
    }
}