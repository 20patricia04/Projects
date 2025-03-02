// Pagina pentru logare
//Utilizatorul trebuie sa introduca un cont care se afla deja in baza de date


package administrare.cabinet.medical;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2;


    Login() {


        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(40, 120, 100, 30);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        namelabel.setForeground(Color.BLACK);
        add(namelabel);

        JLabel password = new JLabel("Password");
        password.setBounds(40, 170, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 16));
        password.setForeground(Color.BLACK);
        add(password);

        textField = new JTextField();
        textField.setBounds(150, 120, 150, 30);
        textField.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField.setBackground(new Color(209, 244, 255, 168));
        add(textField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150, 170, 150, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setBackground(new Color(209, 244, 255, 168));
        add(jPasswordField);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(300, -10, 400, 400);
        add(label);

        b1 = new JButton("Login");
        b1.setBounds(30, 250, 130, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setForeground(Color.BLACK);
        b1.setBackground(new Color(209, 244, 255, 168));
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(170, 250, 130, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setForeground(Color.BLACK);
        b2.setBackground(new Color(94, 123, 151, 168));
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(new Color(180, 197, 196));
        setLocation(450, 270);
        setSize(720, 420);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn c = new conn();
                String user = textField.getText();
                String Pass = jPasswordField.getText();
                String q= "select * from login where ID = '" +user+ "' and PW = '" +Pass+ "'";
                ResultSet resultSet= c.statement.executeQuery(q);
                if (resultSet.next()) {
                    new Meniu();
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Nume sau parola invalide!");
                }

            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        else  {
            System.exit(10);
        }
    }

    public static void main(String[] args) {
        new Login();
    }

}