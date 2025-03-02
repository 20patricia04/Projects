//Medicul are acces la lista de pacienti
//Are posibilitatea de a selecta un pacient si de a-l sterge
package administrare.cabinet.medical;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class CautaPacient extends JFrame implements ActionListener {

    JTable table;
    JButton btnDelete;
    String selectedID = "";

    CautaPacient() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 510);
        panel.setBackground(new Color(180, 197, 196));
        add(panel);

        JLabel lblNewLabel = new JLabel("Lista de pacienti");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(300, 35, 400, 35);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_0 = new JLabel("ID");
        lblNewLabel_0.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_0.setForeground(new Color(255, 255, 255));
        lblNewLabel_0.setBounds(50, 80, 80, 20);

        JLabel lblNewLabel_1 = new JLabel("Nume");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(120, 80, 80, 20);
        panel.add(lblNewLabel_1);


        JLabel lblNewLabel_2 = new JLabel("Prenume");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds(190, 80, 100, 20);
        panel.add(lblNewLabel_2);


        JLabel lblNewLabel_3 = new JLabel("Data nasterii");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setBounds(260, 80, 100, 20);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Gen");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_4.setBounds(360, 80, 100, 20);
        panel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("CNP");
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_5.setForeground(new Color(255, 255, 255));
        lblNewLabel_5.setBounds(430, 80, 100, 20);
        panel.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Telefon");
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_6.setForeground(new Color(255, 255, 255));
        lblNewLabel_6.setBounds(490, 80, 100, 20);
        panel.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Diagnostic");
        lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_7.setForeground(new Color(255, 255, 255));
        lblNewLabel_7.setBounds(560, 80, 100, 20);
        panel.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("Tratament");
        lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_8.setForeground(new Color(255, 255, 255));
        lblNewLabel_8.setBounds(635, 80, 100, 20);
        panel.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("Programare");
        lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_9.setForeground(new Color(255, 255, 255));
        lblNewLabel_9.setBounds(710, 80, 100, 20);
        panel.add(lblNewLabel_9);

        table = new JTable();
        table.setBounds(40, 100, 750, 290);
        table.setBackground(new Color(180, 197, 196));
        panel.add(table);

        try {
            conn c = new conn();
            String sql = "select * from patient_info order by Nume";
            ResultSet resultSet = c.statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            table.setFont(new Font("Times New Roman", Font.BOLD, 13));

            table.getSelectionModel().addListSelectionListener(e -> {
                int row = table.getSelectedRow();
                if (row != -1) {
                    selectedID = table.getValueAt(row, 0).toString();  // ID-ul pacientului din prima coloană
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        btnDelete = new JButton("Șterge");
        btnDelete.setFont(new Font("serif", Font.BOLD, 15));
        btnDelete.setBackground(new Color(255, 255, 255));
        btnDelete.setBounds(345, 440, 150, 40);
        btnDelete.addActionListener(this);
        panel.add(btnDelete);

        JButton btnNewButton = new JButton("Inapoi");
        btnNewButton.setFont(new Font("serif", Font.BOLD, 15));
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setBounds(515, 440, 150, 40);
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Meniu();
            }
        });

        setUndecorated(true);
        setLocation(450, 270);
        setSize(850, 520);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDelete) {
            if (selectedID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selectează un pacient pentru a-l șterge!");
            } else {
                String[] options = {"Da", "Nu", "Anulează"};
                int confirmation = JOptionPane.showOptionDialog(
                        this,
                        "Sigur vrei să ștergi pacientul cu ID-ul " + selectedID + "?",
                        "Confirmare Ștergere",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (confirmation == JOptionPane.YES_OPTION) {
                    try {
                        conn c = new conn();

                        String sql = "DELETE FROM patient_info WHERE ID = '" + selectedID + "'";
                        c.statement.executeUpdate(sql);

                        String deleteAppointment = "UPDATE Programari SET Disponibilitate = 'Disponibil' WHERE Patient_ID = '" + selectedID + "'";
                        c.statement.executeUpdate(deleteAppointment);

                        String query = "select * from patient_info order by Nume";
                        ResultSet rs = c.statement.executeQuery(query);
                        table.setModel(DbUtils.resultSetToTableModel(rs));

                        JOptionPane.showMessageDialog(this, "Pacientul a fost șters cu succes!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Pacientul a fost șters cu succes!");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new CautaPacient();
    }
}
