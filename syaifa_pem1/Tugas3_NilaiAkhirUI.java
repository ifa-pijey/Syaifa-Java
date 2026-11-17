import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
public class Tugas3_NilaiAkhirUI extends JFrame {
    public Tugas3_NilaiAkhirUI() {
        super("Nilai Akhir");

        JTextField t = new JTextField(6);
        JTextField u = new JTextField(6);
        JTextField a = new JTextField(6);
        JCheckBox bonus = new JCheckBox("Bonus keaktifan (+3)");
        JButton btn = new JButton("Hitung");

        // Fungsi: Tombol untuk menyimpan hasil nilai ke file .txt. Awalnya dinonaktifkan (false)
        //         dan baru aktif setelah nilai berhasil dihitung.
        JButton btnSimpan = new JButton("Simpan Laporan (.txt)");
        btnSimpan.setEnabled(false);

        JLabel hasil = new JLabel("Hasil: -");
        
        // Fungsi: Menampilkan visualisasi grafik batang (0-100%) sesuai dengan besar nilai akhir mahasiswa.
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setStringPainted(true); // Menampilkan angka persentase di tengah batang progress

        btn.addActionListener(e -> {
            double nt, nu, na;
            try {
                nt = Double.parseDouble(t.getText().trim());
                nu = Double.parseDouble(u.getText().trim());
                na = Double.parseDouble(a.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Semua nilai harus angka.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nt < 0 || nt > 100 || nu < 0 || nu > 100 || na < 0 || na > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus 0 sampai 100.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double akhir = nt * 0.3 + nu * 0.3 + na * 0.4;
            if (bonus.isSelected()) { akhir = Math.min(100, akhir + 3); }

            String grade;
            if (akhir >= 85) { grade = "A"; }
            else if (akhir >= 70) { grade = "B"; }
            else if (akhir >= 55) { grade = "C"; }
            else { grade = "D"; }

            hasil.setText(String.format("Nilai %.1f, grade %s, %s", akhir, grade, akhir >= 55 ? "Lulus" : "Tidak lulus"));

            // Fungsi: Mengisi batang JProgressBar sesuai nilai akhir dan mengaktifkan tombol Simpan.
            bar.setValue((int) Math.round(akhir));
            btnSimpan.setEnabled(true);
        });

        // Fungsi: Membuka jendela dialog Save File bawaan sistem agar hasil nilai bisa disimpan menjadi file .txt.
        btnSimpan.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File("Hasil_Nilai.txt"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    Files.writeString(fc.getSelectedFile().toPath(), hasil.getText());
                    JOptionPane.showMessageDialog(this, "Laporan nilai berhasil disimpan!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Gagal menyimpan file.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        setLayout(new GridLayout(0, 1, 4, 4)); // [ASLI TEMPLATE] Tata letak vertikal 1 kolom
        add(new JLabel("Tugas:")); add(t);
        add(new JLabel("UTS:")); add(u);
        add(new JLabel("UAS:")); add(a);
        add(bonus);
        add(btn);
        add(bar);       // Menambahkan JProgressBar ke layar
        add(hasil);
        add(btnSimpan); // Menambahkan tombol Simpan ke layar

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tugas3_NilaiAkhirUI().setVisible(true));
    }
}