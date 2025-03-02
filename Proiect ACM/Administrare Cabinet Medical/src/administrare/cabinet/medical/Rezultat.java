//medicul poate adauga diagnosticul si tratamentul in urma unei consultatii

package administrare.cabinet.medical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Rezultat extends JFrame implements ActionListener {

    Choice c1;
    JButton b1, b2;
    JTextField tDiagnostic, tTratament;

    Rezultat() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 510);
        panel.setBackground(new Color(180, 197, 196));
        add(panel);

        JLabel lblNewLabel = new JLabel("Rezultatul consultatiei");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(300, 50, 600, 40);
        panel.add(lblNewLabel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/rezult.png"));
        Image image = imageIcon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(70, 130, 300, 300);
        panel.add(l1);

        JLabel lp = new JLabel("Selecteaza un pacient");
        lp.setFont(new Font("Times New Roman", Font.BOLD, 17));
        lp.setForeground(new Color(255, 255, 255));
        lp.setBounds(480, 200, 180, 20);
        panel.add(lp);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info order by Nume");
            while (resultSet.next()) {
                String entry = resultSet.getString(2) + " " + resultSet.getString(3);
                c1.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(480, 230, 200, 30);
        c1.setFont(new Font("Tahoma", Font.BOLD, 15));
        c1.setForeground(new Color(5, 5, 5));
        panel.add(c1);

        JLabel lDiagnostic = new JLabel("Diagnostic");
        lDiagnostic.setFont(new Font("Tahoma", Font.BOLD, 16));
        lDiagnostic.setForeground(new Color(5, 5, 5));
        lDiagnostic.setBounds(480, 270, 200, 20);
        panel.add(lDiagnostic);

        tDiagnostic = new JTextField();
        tDiagnostic.setBounds(480, 300, 200, 30);
        tDiagnostic.setFont(new Font("Tahoma", Font.BOLD, 15));
        tDiagnostic.setBackground(new Color(209, 244, 255, 168));
        panel.add(tDiagnostic);

        JLabel lTratament = new JLabel("Tratament");
        lTratament.setFont(new Font("Tahoma", Font.BOLD, 16));
        lTratament.setForeground(new Color(5, 5, 5));
        lTratament.setBounds(480, 340, 200, 20);
        panel.add(lTratament);

        tTratament = new JTextField();
        tTratament.setBounds(480, 370, 200, 30);
        tTratament.setFont(new Font("Tahoma", Font.BOLD, 15));
        tTratament.setBackground(new Color(209, 244, 255, 168));
        panel.add(tTratament);

        b1 = new JButton("Adauga");
        b1.setFont(new Font("Tahoma", Font.BOLD, 15));
        b1.setBackground(new Color(209, 244, 255, 168));
        b1.setForeground(new Color(5, 5, 5));
        b1.setBounds(140, 460, 250, 30);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("Anuleaza");
        b2.setFont(new Font("Tahoma", Font.BOLD, 15));
        b2.setBackground(new Color(209, 244, 255, 168));
        b2.setForeground(new Color(5, 5, 5));
        b2.setBounds(430, 460, 250, 30);
        b2.addActionListener(this);
        panel.add(b2);

        setUndecorated(true);
        setLocation(450, 270);
        setSize(850, 520);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn c = new conn();
                String selectedPatient = c1.getSelectedItem();
                String[] parts = selectedPatient.split(" ");
                String firstName = parts[0];
                String lastName = parts[1];

                String diagnostic = tDiagnostic.getText();
                String tratament = tTratament.getText();

                String updateQuery = "UPDATE patient_info SET Diagnostic = '" + diagnostic + "', Tratament = '" + tratament + "' WHERE Nume = '" + firstName + "' AND Prenume = '" + lastName + "'";
                c.statement.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null, "Diagnostic și tratament adăugate cu succes.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Eroare la actualizarea datelor.");
            }
        } else if (e.getSource() == b2) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Rezultat();
    }
}
