//Meniul interactiv care permite navigarea prin aplicatie
//Doctorul poate cauta pacientii, adauga programar, adauga un pacient sau programare noua, sa isi vada programul

package administrare.cabinet.medical;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Meniu extends JFrame {



    Meniu(){

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 1670, 200);
        panel.setBackground(new Color(180, 197, 196));
        add(panel);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5, 210, 1670, 710);
        panel1.setBackground(new Color(180, 197, 196));
        add(panel1);

        JLabel lblNewLabel = new JLabel("Bine ati revenit!");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 65));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(580, 45, 600, 100);
        panel1.add(lblNewLabel);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/dr.png"));
        Image image = i1.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(600, 110, 400, 400);
        panel1.add(l1);


        JButton b1 = new JButton("Adauga pacient");
        b1.setBounds(40, 80, 200, 50);
        b1.setFont(new Font("serif", Font.BOLD, 20));
        b1.setBackground(new Color(255, 255, 255));
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PacientNou();
            }
        });



        JButton b2 = new JButton("Lista pacientilor");
        b2.setBounds(290, 80, 200, 50);
        b2.setFont(new Font("serif", Font.BOLD, 20));
        b2.setBackground(new Color(255, 255, 255));
        panel.add(b2);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    new CautaPacient();
            }
        });



        JButton b3 = new JButton("Editeaza pacient");
        b3.setBounds(550, 80, 200, 50);
        b3.setFont(new Font("serif", Font.BOLD, 20));
        b3.setBackground(new Color(255, 255, 255));
        panel.add(b3);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditeazaPacient();
            }
        });

        JButton b4 = new JButton("Adauga programare");
        b4.setBounds(820, 80, 220, 50);
        b4.setFont(new Font("serif", Font.BOLD, 20));
        b4.setBackground(new Color(255, 255, 255));
        panel.add(b4);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdaugaProgramare();
            }
        });


        JButton b5 = new JButton("Rezultat consultatie");
        b5.setBounds(1110, 80, 220, 50);
        b5.setFont(new Font("serif", Font.BOLD, 20));
        b5.setBackground(new Color(255, 255, 255));
        panel.add(b5);
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    new Rezultat();
            }
        });

        JButton b6 = new JButton("Vizualizare program");
        b6.setBounds(1400, 80, 220, 50);
        b6.setFont(new Font("serif", Font.BOLD, 20));
        b6.setBackground(new Color(255, 255, 255));
        panel.add(b6);
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Program();
            }
        });



        setLocation(410, 210);
        setSize(1750, 1000);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Meniu();
    }
}
