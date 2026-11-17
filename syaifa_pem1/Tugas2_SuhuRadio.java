import javax.swing.*;
import java.awt.*;

public class Tugas2_SuhuRadio extends JFrame {
    public Tugas2_SuhuRadio() {
        super("Konversi Suhu");

        JTextField txt = new JTextField("25", 8);
        // Fungsi: Menambahkan penggeser (slider) dari -50 sampai 150 derajat agar user bisa
        //         mengubah suhu secara interaktif tanpa harus selalu mengetik manual.
        JSlider slider = new JSlider(-50, 150, 25);
        slider.setMajorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        ButtonGroup g = new ButtonGroup();
        JRadioButton f = new JRadioButton("Fahrenheit", true);
        JRadioButton k = new JRadioButton("Kelvin");
        JRadioButton r = new JRadioButton("Reamur");
        f.setActionCommand("F"); 
        k.setActionCommand("K"); 
        r.setActionCommand("R");
        g.add(f); g.add(k); g.add(r);

        JButton btn = new JButton("Konversi");
        JLabel hasil = new JLabel("Hasil: -");
        hasil.setFont(new Font("SansSerif", Font.BOLD, 14)); // Mempertebal huruf hasil

        Runnable prosesKonversi = () -> {
            double c;
            try {
                c = Double.parseDouble(txt.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Isi suhu dengan angka.");
                return;
            }

            // Fungsi: Mengubah warna teks hasil sesuai tingkat suhu
            if (c <= 15) {
                hasil.setForeground(Color.BLUE);
            } else if (c <= 35) {
                hasil.setForeground(new Color(0, 128, 0));
            } else {
                hasil.setForeground(Color.RED);
            }

            switch (g.getSelection().getActionCommand()) {
                case "F": hasil.setText(String.format("Hasil: %.2f F", c * 9 / 5 + 32)); break;
                case "K": hasil.setText(String.format("Hasil: %.2f K", c + 273.15)); break;
                default:  hasil.setText(String.format("Hasil: %.2f R", c * 4 / 5));
            }
        };

        // Fungsi: Mendeteksi setiap kali slider digeser, lalu otomatis mengisi kotak teks & menghitung hasilnya.
        slider.addChangeListener(e -> {
            txt.setText(String.valueOf(slider.getValue()));
            prosesKonversi.run();
        });

        btn.addActionListener(e -> prosesKonversi.run());

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        setLayout(new GridLayout(0, 1, 4, 4));
        add(new JLabel("Suhu (Celcius):")); 
        add(txt);
        add(slider); // Menambahkan komponen slider ke dalam jendela
        add(f); add(k); add(r); 
        add(btn); add(hasil);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tugas2_SuhuRadio().setVisible(true));
    }
}