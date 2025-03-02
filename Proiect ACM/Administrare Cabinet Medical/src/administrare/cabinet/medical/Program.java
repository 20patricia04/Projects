//Doctorul poate vedea in ce zile e ocupat si care este casitgul pentru saptamana actuala, dar si sa stearga toate programarile pentru a incepe o saptamana noua

package administrare.cabinet.medical;
import com.sun.tools.javac.Main;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Program extends JFrame implements ActionListener {

    JButton b2;

    Program() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 510);
        panel.setBackground(new Color(180, 197, 196));
        add(panel);

        JLabel lblNewLabel = new JLabel("Programul pentru saptamana aceasta");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(190, 35, 600, 35);
        panel.add(lblNewLabel);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/program.png"));
        Image image = imageIcon.getImage().getScaledInstance(280, 330, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(50, 30, 220, 450);
        panel.add(l1);

        JLabel lblNewLabel_1 = new JLabel("Ziua si ora");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(370, 80, 80, 20);
        panel.add(lblNewLabel_1);


        JLabel lblNewLabel_2 = new JLabel("Disponibilitate");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds(510, 80, 100, 20);
        panel.add(lblNewLabel_2);


        JLabel lblNewLabel_3 = new JLabel("Pret");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setBounds(690, 80, 100, 20);
        panel.add(lblNewLabel_3);


        JTable table = new JTable();
        table.setBounds(340, 100, 440, 320);
        table.setBackground(new Color(180, 197, 196));
        panel.add(table);

        JLabel castigLabel = new JLabel("Castig total: ");
        castigLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        castigLabel.setBounds(620, 440, 250, 15);
        panel.add(castigLabel);


        try {
            conn c = new conn();
            String sql = "select * from Programari";
            ResultSet resultSet = c.statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            table.setFont(new Font("Times New Roman", Font.BOLD, 13));

            String sumaSql = "SELECT SUM(Pret) AS CastigSaptamanal FROM Programari WHERE Disponibilitate = 'Indisponibil'";
            ResultSet sumaResult = c.statement.executeQuery(sumaSql);
            if (sumaResult.next()) {
                double castig = sumaResult.getDouble("CastigSaptamanal");
                castigLabel.setText("Castig total: " + castig + " RON");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        JButton btnNewButton = new JButton("Inapoi");
        btnNewButton.setFont(new Font("serif", Font.BOLD, 15));
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setBounds(255, 460, 150, 40);
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Meniu();
            }
        });


        b2 = new JButton("Saptamana noua");
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(new Color(209, 244, 255, 168));
        b2.setForeground(new Color(5, 5, 5));
        b2.setBounds(440, 460, 150, 40);
        b2.addActionListener(this);
        panel.add(b2);


        setUndecorated(true);
        setLocation(450, 270);
        setSize(850, 520);
        setLayout(null);
        setVisible(true);

    }


        @Override
        public void actionPerformed(ActionEvent E) {

            if (E.getSource() == b2) {
                conn c = new conn();
        try {
            String q1 = "UPDATE Programari SET Disponibilitate = 'Disponibil' WHERE Disponibilitate = 'Indisponibil'";
            c.statement.executeUpdate(q1);
        }catch (Exception e1) {
        e1.printStackTrace();
        }

            }
            else {
                setVisible(false);
            }
            new Program();
        }




    public static void main(String[] args) {
        new Program();
    }
}
