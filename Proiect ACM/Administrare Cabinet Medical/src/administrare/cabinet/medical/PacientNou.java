//Doctorul poate aduga pacienti noi

package administrare.cabinet.medical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class PacientNou extends JFrame implements ActionListener {
    JComboBox comboBox;
    JTextField textFieldName, textFieldBirthday, textFieldPhone, textFieldprn, textFieldCnp;
    Choice c1;
    JButton b1, b2;

    PacientNou() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 510);
        panel.setBackground(new Color(180, 197, 196));
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(280, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(320, 25, 200, 500);
        panel.add(l1);

        JLabel lblNewLabel = new JLabel("Formular inregistrare pacient");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(240, 15, 400, 100);
        panel.add(lblNewLabel);

        /// Nume
        JLabel l3 = new JLabel("Nume");
        l3.setFont(new Font("Tahoma", Font.BOLD, 16));
        l3.setForeground(new Color(5, 5, 5));
        l3.setBounds(50, 115, 200, 100);
        panel.add(l3);

        textFieldName = new JTextField();
        textFieldName.setBounds(50, 185, 150, 30);
        textFieldName.setFont(new Font("Tahoma", Font.BOLD, 15));
        textFieldName.setBackground(new Color(209, 244, 255, 168));
        panel.add(textFieldName);

        /// Prenume
        JLabel l4 = new JLabel("Prenume");
        l4.setFont(new Font("Tahoma", Font.BOLD, 16));
        l4.setForeground(new Color(5, 5, 5));
        l4.setBounds(50, 225, 200, 100);
        panel.add(l4);

        textFieldprn = new JTextField();
        textFieldprn.setBounds(50, 295, 150, 30);
        textFieldprn.setFont(new Font("Tahoma", Font.BOLD, 15));
        textFieldprn.setBackground(new Color(209, 244, 255, 168));
        panel.add(textFieldprn);

        /// Data nasterii
        JLabel l5 = new JLabel("Data nasterii");
        l5.setFont(new Font("Tahoma", Font.BOLD, 16));
        l5.setForeground(new Color(5, 5, 5));
        l5.setBounds(50, 325, 200, 100);
        panel.add(l5);

        textFieldBirthday = new JTextField();
        textFieldBirthday.setBounds(50, 395, 150, 30);
        textFieldBirthday.setFont(new Font("Tahoma", Font.BOLD, 15));
        textFieldBirthday.setBackground(new Color(209, 244, 255, 168));
        panel.add(textFieldBirthday);

        /// Gen
        JLabel l6 = new JLabel("Gen");
        l6.setFont(new Font("Tahoma", Font.BOLD, 16));
        l6.setForeground(new Color(5, 5, 5));
        l6.setBounds(570, 115, 200, 100);
        panel.add(l6);

        comboBox = new JComboBox(new String[]{"Feminin", "Masculin"});
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
        comboBox.setBackground(new Color(209, 244, 255, 168));
        comboBox.setForeground(new Color(5, 5, 5));
        comboBox.setBounds(570, 185, 150, 30);
        panel.add(comboBox);

        /// CNP
        JLabel l7 = new JLabel("CNP");
        l7.setFont(new Font("Tahoma", Font.BOLD, 16));
        l7.setForeground(new Color(5, 5, 5));
        l7.setBounds(570, 185, 200, 100);
        panel.add(l7);

        textFieldCnp = new JTextField();
        textFieldCnp.setBounds(570, 255, 150, 30);
        textFieldCnp.setFont(new Font("Tahoma", Font.BOLD, 15));
        textFieldCnp.setBackground(new Color(209, 244, 255, 168));
        panel.add(textFieldCnp);

        /// Telefon
        JLabel l8 = new JLabel("Numar de telefon");
        l8.setFont(new Font("Tahoma", Font.BOLD, 16));
        l8.setForeground(new Color(5, 5, 5));
        l8.setBounds(570, 255, 200, 100);
        panel.add(l8);

        textFieldPhone = new JTextField();
        textFieldPhone.setBounds(570, 325, 150, 30);
        textFieldPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        textFieldPhone.setBackground(new Color(209, 244, 255, 168));
        panel.add(textFieldPhone);

        /// Programare
        JLabel l9 = new JLabel("Programare");
        l9.setFont(new Font("Tahoma", Font.BOLD, 16));
        l9.setForeground(new Color(5, 5, 5));
        l9.setBounds(570, 355, 200, 50);
        panel.add(l9);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Programari where Disponibilitate='Disponibil'");
            while (resultSet.next()) {
                c1.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        c1.setBounds(570, 400, 150, 30);
        c1.setFont(new Font("Tahoma", Font.BOLD, 15));
        c1.setForeground(new Color(5, 5, 5));
        panel.add(c1);

        /// Butoane
        b1 = new JButton("Finalizeaza inregistrarea");
        b1.setFont(new Font("Tahoma", Font.BOLD, 15));
        b1.setBackground(new Color(209, 244, 255, 168));
        b1.setForeground(new Color(5, 5, 5));
        b1.setBounds(140, 460, 250, 30);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("Anuleaza inregistrarea");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            conn c = new conn();

            String s2 = textFieldName.getText();
            String s3 = textFieldprn.getText();
            String s4 = textFieldBirthday.getText();
            String s5 = (String) comboBox.getSelectedItem();
            String s6 = textFieldCnp.getText();
            String s7 = textFieldPhone.getText();
            String s8 = c1.getSelectedItem();

            try {
                String q = "INSERT INTO patient_info (Nume, Prenume, DataNastere, Gen, CNP, Telefon, Programare) " +
                        "VALUES ('" + s2 + "', '" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')";
                String q1 = "UPDATE Programari SET Disponibilitate = 'Indisponibil' WHERE Zi_ora = '" + s8 + "'";
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "Adaugat cu succes.");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PacientNou();
    }
}
