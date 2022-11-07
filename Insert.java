import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.*;

import javafx.scene.paint.Color;

import java.awt.event.*;

class Window extends JFrame implements ActionListener {
    public JLabel l1, l2, l3, output;
    public JTextField f1, f2;
    public JButton inserts, show;
    String value, records[];

    Window() {
        // JFrame f = new JFrame("Insertion");
        setVisible(true);
        setSize(600, 400);
        l1 = new JLabel("Enter Numbers Of Recoreds ");
        l2 = new JLabel("Enter 2 Values : ");
        output = new JLabel();
        f1 = new JTextField();
        f2 = new JTextField();
        inserts = new JButton("Insert");
        show = new JButton("Show");
        // add(f);
        add(l1);
        add(l2);
        add(f1);
        add(f2);
        add(inserts);
        add(show);
        add(output);
        // f.setBackground(Color.MAGENTA);
        l1.setBounds(50, 50, 150, 30);
        l2.setBounds(50, 100, 150, 30);
        f1.setBounds(250, 50, 150, 30);
        f2.setBounds(250, 100, 150, 30);
        inserts.setBounds(50, 150, 100, 40);
        show.setBounds(200, 150, 100, 40);
        output.setBounds(300, 230, 200, 30);
        // inserts.setBackground(Color.PURPLE);
        inserts.addActionListener(this);
        show.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inserts) {
            try {
                System.out.println("Event");
                String result = insertData();
                output.setText(result);
            } catch (ClassNotFoundException | SQLException e1) {
            }
        } else {
            JOptionPane.showMessageDialog(this, value);
        }

    }

    public String insertData() throws ClassNotFoundException, SQLException {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String url = "jdbc:ucanaccess://D:/MY USERS/Aditya/IF5I/Adv Java/Adv_Java_V/Unit 5/Aditya1.accdb";
        Connection c = DriverManager.getConnection(url);
        PreparedStatement p = c.prepareStatement("insert into ? values(?,?)");
        records = f2.getText().split(" ");
        p.setString(0, "Table1");
        p.setString(1, records[0]);
        p.setString(2, records[1]);
        int i = p.executeUpdate();
        PreparedStatement p2 = c.prepareStatement("Select * from Table1");
        ResultSet rs = p2.executeQuery();
        while (rs.next()) {
            value += (rs.getInt(1) + "\t" + rs.getString(2) + "\n");
        }
        return i + " Record Inserted ";
    }
}

public class Insert {
    public static void main(String a[]) throws ClassNotFoundException, SQLException {
        new Window();
    }
}
