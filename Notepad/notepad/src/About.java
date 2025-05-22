import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {
    JButton ok;

    About(){

        setTitle("About Notepad");
        setLayout(null);

        ImageIcon i1 = new ImageIcon("C:\\Users\\HP\\Desktop\\Projects\\Notepad\\notepad\\src\\icons\\windows.png");
        Image i2 = i1.getImage().getScaledInstance(300,60,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageWindow = new JLabel(i3);
        imageWindow.setBounds(90,20,300,80);
        add(imageWindow);


        ImageIcon i4 = new ImageIcon("C:\\Users\\HP\\Desktop\\Projects\\Notepad\\notepad\\src\\icons\\notepad.png");
        Image i5 = i4.getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel notepadImage = new JLabel(i6);
        notepadImage.setBounds(35,130,60,60);
        add(notepadImage);


        JLabel text = new JLabel("<html>Created by Sumit Kotiya<br>Version 0.1.0<br>Windows Operating System<br><br>All rights are reserved</html>");
        text.setFont(new Font("System",Font.PLAIN,16));
        text.setBounds(120,130,200,110);
        add(text);

        ok = new JButton("OK");
        ok.setFont(new Font("System",Font.BOLD,14));
        ok.setBounds(330,280,70,28);
        ok.setFocusable(false);
        ok.addActionListener(this);
        add(ok);



        setSize(470,390);
        setLocation(260,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        setVisible(false);
    }


    public static void main(String[] args) {
        new About();
    }
}
