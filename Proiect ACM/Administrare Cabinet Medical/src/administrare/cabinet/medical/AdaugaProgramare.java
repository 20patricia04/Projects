//medicul poate adauga o programare in una din zilele libere

package administrare.cabinet.medical;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AdaugaProgramare extends JFrame implements ActionListener {

    Choice c1, c2;
    JButton b1, b2;

    AdaugaProgramare(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 510);
        panel.setBackground(new Color(180, 197, 196));
        add(panel);

        JLabel lblNewLabel = new JLabel("Programare noua");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(300, 50, 600, 40);
        panel.add(lblNewLabel);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/adaugaprogramare.png"));
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
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
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



        JLabel lblNewLabel_1 = new JLabel("Ziua si ora programarii");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(480, 280, 180, 20);
        panel.add(lblNewLabel_1);


        c2 = new Choice();
        try {
            conn c = new conn();
            ResultSet resultSet= c.statement.executeQuery("select * from Programari where Disponibilitate='Disponibil'");
            while (resultSet.next()) {
                c2.add(resultSet.getString(1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        c2.setBounds(480, 310, 200, 30);
        c2.setFont(new Font("Tahoma", Font.BOLD, 15));
        c2.setForeground(new Color(5, 5, 5));
        panel.add(c2);



        b1 = new JButton("Finalizeaza");
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
            conn c = new conn();

            String s1 = (String) c1.getSelectedItem();
            String[] parts = s1.split(" ");
            String column2Value = parts[0];
            String column3Value = parts[1];


            String s2 = (String) c2.getSelectedItem();

            try {
                String q = "UPDATE patient_info SET Programare = '" + s2 + "' WHERE Nume= '" + column2Value + "' AND Prenume = '" + column3Value + "'";
                String q1 = "UPDATE Programari SET Disponibilitate = 'Indisponibil' WHERE Zi_ora = '" + s2 + "'";
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "Programare adaugata.");
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }



    public static void main(String[] args) {
        new AdaugaProgramare();
    }
}
