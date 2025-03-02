//In cazul in  care au fost introduse date gresite, medicul le poate vizualiza si edita

package administrare.cabinet.medical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EditeazaPacient extends JFrame implements ActionListener {

    JTextField textFieldBirthday, textFieldPhone, textFieldCnp;
    JComboBox comboBox;
    Choice c1;

    public EditeazaPacient() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 510);
        panel.setBackground(new Color(180, 197, 196));
        add(panel);

        JLabel lblNewLabel = new JLabel("Editeaza datele pacientului");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(240, 15, 400, 100);
        panel.add(lblNewLabel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(430, 130, 270, 270);
        panel.add(l1);

        JLabel lblNewLabel1 = new JLabel("Alege pacientul");
        lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel1.setForeground(new Color(5, 5, 5));
        lblNewLabel1.setBounds(90, 130, 150, 20);
        panel.add(lblNewLabel1);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()) {
                String entry = resultSet.getString(2) + " " + resultSet.getString(3);
                c1.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(90, 160, 200, 30);
        c1.setFont(new Font("Tahoma", Font.BOLD, 15));
        c1.setForeground(new Color(5, 5, 5));
        panel.add(c1);

        JLabel l5 = new JLabel("Data nasterii");
        l5.setFont(new Font("Tahoma", Font.BOLD, 16));
        l5.setForeground(new Color(5, 5, 5));
        l5.setBounds(90, 160, 200, 100);
        panel.add(l5);

        textFieldBirthday = new JTextField();
        textFieldBirthday.setBounds(90, 230, 150, 30);
        textFieldBirthday.setFont(new Font("Tahoma", Font.BOLD, 15));
        textFieldBirthday.setBackground(new Color(209, 244, 255, 168));
        panel.add(textFieldBirthday);

        JLabel l6 = new JLabel("Gen");
        l6.setFont(new Font("Tahoma", Font.BOLD, 16));
        l6.setForeground(new Color(5, 5, 5));
        l6.setBounds(90, 240, 200, 100);
        panel.add(l6);

        comboBox = new JComboBox(new String[]{"Feminin", "Masculin"});
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
        comboBox.setBackground(new Color(209, 244, 255, 168));
        comboBox.setForeground(new Color(5, 5, 5));
        comboBox.setBounds(90, 310, 150, 30);
        panel.add(comboBox);

        JLabel l7 = new JLabel("CNP");
        l7.setFont(new Font("Tahoma", Font.BOLD, 16));
        l7.setForeground(new Color(5, 5, 5));
        l7.setBounds(90, 305, 200, 100);
        panel.add(l7);

        textFieldCnp = new JTextField();
        textFieldCnp.setBounds(90, 365, 150, 30);
        textFieldCnp.setFont(new Font("Tahoma", Font.BOLD, 15));
        textFieldCnp.setBackground(new Color(209, 244, 255, 168));
        panel.add(textFieldCnp);

        JLabel l8 = new JLabel("Numar de telefon");
        l8.setFont(new Font("Tahoma", Font.BOLD, 16));
        l8.setForeground(new Color(5, 5, 5));
        l8.setBounds(90, 365, 200, 100);
        panel.add(l8);

        textFieldPhone = new JTextField();
        textFieldPhone.setBounds(90, 435, 150, 30);
        textFieldPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        textFieldPhone.setBackground(new Color(209, 244, 255, 168));
        panel.add(textFieldPhone);

        JButton check = new JButton("Check");
        check.setBounds(400, 440, 90, 20);
        check.setFont(new Font("Tahoma", Font.BOLD, 15));
        check.setBackground(new Color(209, 244, 255, 168));
        check.setForeground(new Color(5, 5, 5));
        panel.add(check);
        check.addActionListener(e -> {
            try {
                conn c = new conn();
                String selectedPatient = c1.getSelectedItem();
                String[] parts = selectedPatient.split(" ");
                String firstName = parts[0];
                String lastName = parts[1];

                String query = "SELECT * FROM patient_info WHERE Nume = '" + firstName + "' AND Prenume = '" + lastName + "'";
                ResultSet rs = c.statement.executeQuery(query);

                if (rs.next()) {
                    textFieldBirthday.setText(rs.getString("DataNastere"));
                    textFieldCnp.setText(rs.getString("CNP"));
                    textFieldPhone.setText(rs.getString("Telefon"));
                    comboBox.setSelectedItem(rs.getString("Gen"));
                } else {
                    JOptionPane.showMessageDialog(null, "Pacientul nu a fost găsit.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JButton upd = new JButton("Update");
        upd.setBounds(520, 440, 90, 20);
        upd.setFont(new Font("Tahoma", Font.BOLD, 15));
        upd.setBackground(new Color(209, 244, 255, 168));
        upd.setForeground(new Color(5, 5, 5));
        panel.add(upd);
        upd.addActionListener(e -> {
            try {
                conn c = new conn();
                String selectedPatient = c1.getSelectedItem();
                String[] parts = selectedPatient.split(" ");
                String firstName = parts[0];
                String lastName = parts[1];

                String birthday = textFieldBirthday.getText();
                String cnp = textFieldCnp.getText();
                String phone = textFieldPhone.getText();
                String gender = comboBox.getSelectedItem().toString();

                String updateQuery = "UPDATE patient_info SET DataNastere = '" + birthday + "', CNP = '" + cnp + "', Telefon = '" + phone + "', Gen = '" + gender + "' WHERE Nume = '" + firstName + "' AND Prenume = '" + lastName + "'";
                c.statement.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null, "Datele pacientului au fost actualizate.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JButton anl = new JButton("Anuleaza");
        anl.setBounds(640, 440, 90, 20);
        anl.setFont(new Font("Tahoma", Font.BOLD, 15));
        anl.setBackground(new Color(209, 244, 255, 168));
        anl.setForeground(new Color(5, 5, 5));
        panel.add(anl);
        anl.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setLocation(450, 270);
        setSize(850, 520);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        new EditeazaPacient();
    }
}
