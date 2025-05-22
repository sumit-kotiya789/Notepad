import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea textArea;
    JMenuItem newDoc,openDoc,saveDoc,printDoc,exitFile,copyEdit,pasteEdit,cutEdit,selectAllEdit, about;
    String text;

    Notepad(){
        setTitle("Notepad");

        //set title icon
        ImageIcon notepad = new ImageIcon("C:\\Users\\HP\\Desktop\\Projects\\Notepad\\notepad\\src\\icons\\notepad.png");
        Image notepadIcon = notepad.getImage();
        setIconImage(notepadIcon);

        //Menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        //------------------------------------>File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("Aerial", Font.PLAIN,14));
        menuBar.add(fileMenu);
        //File Menu Item
        newDoc  = new JMenuItem("New");
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newDoc.addActionListener(this);

        openDoc = new JMenuItem("Open");
        openDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openDoc.addActionListener(this);

        saveDoc = new JMenuItem("Save");
        saveDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveDoc.addActionListener(this);

        printDoc = new JMenuItem("Print");
        printDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        printDoc.addActionListener(this);

        exitFile = new JMenuItem("Exit");
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        exitFile.addActionListener(this);

        //Add to file menu
        fileMenu.add(newDoc);
        fileMenu.add(openDoc);
        fileMenu.add(saveDoc);
        fileMenu.add(printDoc);
        fileMenu.add(exitFile);
        //------------------------------------>Edit Menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setFont(new Font("Aerial", Font.PLAIN,14));
        menuBar.add(editMenu);
        //Edit menu Items
        copyEdit = new JMenuItem("Copy");
        copyEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyEdit.addActionListener(this);

        pasteEdit = new JMenuItem("Paste");
        pasteEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pasteEdit.addActionListener(this);

        cutEdit = new JMenuItem("Cut");
        cutEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cutEdit.addActionListener(this);

        selectAllEdit = new JMenuItem("Select All");
        selectAllEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectAllEdit.addActionListener(this);
        //Add to Edit Menu
        editMenu.add(copyEdit);
        editMenu.add(pasteEdit);
        editMenu.add(cutEdit);
        editMenu.add(selectAllEdit);
        //------------------------------------->Help Menu
        JMenu helpMenu = new JMenu("About");
        helpMenu.setFont(new Font("Aerial", Font.PLAIN,14));
        menuBar.add(helpMenu);
        //Help menu item
        about = new JMenuItem("About Notepad");
        about.addActionListener(this);

        //Add to help menu
        helpMenu.add(about);


        //Text Area
        textArea = new JTextArea();
        textArea.setFont(new Font("SAN_SERIF",Font.PLAIN,19));
        textArea.setLineWrap(true);         //to line break
        textArea.setWrapStyleWord(true);    //To send whole word to next line if not fit
        JScrollPane scrollPane = new JScrollPane(textArea);     //add text area inside  scroll bar
        scrollPane.setBorder(BorderFactory.createEmptyBorder());     //No border for scroll bar
        add(scrollPane);                            //Add scroll pane


        setJMenuBar(menuBar);
        setSize(800,500);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == newDoc){
            textArea.setText("");
        } else if (actionEvent.getSource() == openDoc) {
            JFileChooser chooser = new JFileChooser();      //file chooser obj
            chooser.setAcceptAllFileFilterUsed(false);      //set to not accept any file
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File Only", "txt");
            chooser.addChoosableFileFilter(filter);     //filter only text file

            int action = chooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION){
                return;
            }else {
                File file = chooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    textArea.read(reader,null);
                }catch (Exception e){
                    System.out.println("Exception Occurred: " + e.getMessage());
                }
            }
        } else if (actionEvent.getSource() == saveDoc) {
            JFileChooser saveAs = new JFileChooser();
            saveAs.setApproveButtonText("Save");

            int action = saveAs.showOpenDialog(this);
            if(action == JFileChooser.APPROVE_OPTION){
                File fileName = new File(saveAs.getSelectedFile() + ".txt");
                BufferedWriter outFile = null;
                try{
                    outFile = new BufferedWriter(new FileWriter(fileName));
                    textArea.write(outFile);
                }catch (Exception e){
                    System.out.println("Exception Occurred: " + e.getMessage());
                }

            }
        } else if (actionEvent.getSource() == printDoc) {
            try {
                textArea.print();
            }catch (Exception e){
                System.out.println("Exception Occurred: " + e.getMessage());
            }
        } else if (actionEvent.getSource() == exitFile) {
            System.exit(0);
        } else if (actionEvent.getSource() == copyEdit) {
            text = textArea.getSelectedText();
        } else if (actionEvent.getSource() == cutEdit) {
            text = textArea.getSelectedText();
            textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
        } else if (actionEvent.getSource() == pasteEdit) {
            textArea.insert(text,textArea.getCaretPosition());
        } else if (actionEvent.getSource() == selectAllEdit) {
            textArea.selectAll();
        } else if (actionEvent.getSource() == about) {
                new About();
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }


}
