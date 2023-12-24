/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notepadeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
 *
 * @author Dharmendra Kumar Yadav
 */
public class Notepad extends ActionPerform {

    JFrame jf,font_frame;
    JMenuBar menubar;
    JMenu file, edit, format, help;
    JMenuItem neww, open, save, save_as, page_stup, print, exit;
    JMenuItem cut, copy, paste, replace, date_time;
    JMenuItem open_font_menu, font_color, textarea_color;
    JTextArea textarea;
    String title = "Untitled-Notepand";
    File filee;
    JFileChooser filechooser;
    JFrame replaceframe;
    JTextField jt1, jt2;
    JButton jb1,ok;
    JComboBox jc_font_famally,jc_font_style,jc_font_size;
    JCheckBoxMenuItem word_wrap;
    
    public Notepad() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println(e);
        }

        jf = new JFrame(title);
        jf.setSize(600, 500);
        ImageIcon img = new ImageIcon("E:\\JPLN\\NotepadEditor\\src\\image\\Notepad.png");
        jf.setIconImage(img.getImage());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menubar = new JMenuBar();
        jf.setJMenuBar(menubar);

        //File
        file = new JMenu("File");

        neww = new JMenuItem("New");
        neww.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_DOWN_MASK));
        neww.addActionListener(this);
        file.add(neww);

        open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_DOWN_MASK));
        open.addActionListener(this);
        file.add(open);

        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK));
        save.addActionListener(this);
        file.add(save);

        save_as = new JMenuItem("Save As");
        save_as.addActionListener(this);
        file.add(save_as);

        file.addSeparator();
        page_stup = new JMenuItem("Page Setup");
        page_stup.addActionListener(this);
        file.add(page_stup);

        print = new JMenuItem("Print...");
        print.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
        print.addActionListener(this);
        file.add(print);
        file.addSeparator();
        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke('E',InputEvent.CTRL_DOWN_MASK));
        exit.addActionListener(this);
        file.add(exit);

        menubar.add(file);

        //Edit
        edit = new JMenu("Edit");
        cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke('X',InputEvent.CTRL_DOWN_MASK));
        cut.addActionListener(this);
        edit.add(cut);

        copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke('C',InputEvent.CTRL_DOWN_MASK));
        copy.addActionListener(this);
        edit.add(copy);

        paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke('V',InputEvent.CTRL_DOWN_MASK));
        edit.add(paste);
        paste.addActionListener(this);
        edit.addSeparator();

        replace = new JMenuItem("Replace");
        replace.setAccelerator(KeyStroke.getKeyStroke('R',InputEvent.CTRL_DOWN_MASK));
        replace.addActionListener(this);
        edit.add(replace);
        
        edit.addSeparator();
        date_time = new JMenuItem("Date/Time");
        date_time.addActionListener(this);
        edit.add(date_time);

        menubar.add(edit);

        //Format
        format = new JMenu("Format");
        
        word_wrap=new JCheckBoxMenuItem("Word Wrap");
        word_wrap.addActionListener(this);
        format.add(word_wrap);
        
        open_font_menu = new JMenuItem("Font...");
        open_font_menu.addActionListener(this);
        format.add(open_font_menu);
        format.addSeparator();

        font_color = new JMenuItem("Font Color");
        font_color.addActionListener(this);
        format.add(font_color);

        textarea_color = new JMenuItem("Text Area Color");
        textarea_color.addActionListener(this);
        format.add(textarea_color);

        menubar.add(format);

        //Help
        help = new JMenu("Help");
        help.addActionListener(this);
        menubar.add(help);
        // text area 
        textarea = new JTextArea();
        jf.add(textarea);

        // scrollbar
        JScrollPane scrollpane = new JScrollPane(textarea);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jf.add(scrollpane);

        //here you can add scrollpane but java apdate TextArea component inbuild scrollpane
        //JScrollPane scrollpane=new JScrollPane(textarea);
        //jf.add(scrollpane);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        filechooser = new JFileChooser();

        if (e.getSource() == neww) {
            newNotepad();
        }
        if (e.getSource() == open) {
            open();
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == save) {
            save();
        }
        if (e.getSource() == save_as) {
            saveas();
        }
        if (e.getSource() == page_stup) {
            pageSetup();
        }
        if (e.getSource() == print) {
            print();
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == cut) {
            textarea.cut();
        }
        if (e.getSource() == copy) {
            textarea.copy();
        }
        if (e.getSource() == paste) {
            textarea.paste();
        }
        if (e.getSource() == replace) {
            replaceFrame();
        }
        if (e.getSource() == jb1) {
            replace();
        }
        if (e.getSource() == date_time) {
            setDateTime();
        }
        if(e.getSource()==word_wrap){
            boolean b=word_wrap.getState();
            textarea.setLineWrap(b);
        }
        if (e.getSource() == open_font_menu) {
            openFontFrame();
        }
        if(e.getSource()==ok){
        }
        if (e.getSource() == font_color) {
            setFontColor();
        }
        if (e.getSource() == textarea_color) {
            setTextAreaColor();
        }
    }

    public void newNotepad() {
        String text = textarea.getText();
        if (!text.equals("")) {
            int i = JOptionPane.showConfirmDialog(jf, "Do you want to seave this file?");
            if (i == 0) {
                saveas();
                if (!jf.getTitle().equals(title)) {
                    setTitle(title);
                    textarea.setText("");
                }

            } else if (i == 1) {
                textarea.setText("");
            }
        }

    }

    public void open() {

        try {
            int i = filechooser.showOpenDialog(jf);
            if (i == 0) {
                textarea.setText("");
                setTitle(title);
                FileInputStream fis = new FileInputStream(filechooser.getSelectedFile());
                int ii;
                while ((ii = fis.read()) != -1) {
                    textarea.append(String.valueOf((char) ii));
                }
                fis.close();
                setTitle(filechooser.getSelectedFile().getName());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void save() {
        if (jf.getTitle().equals(title)) {
            saveas();
        } else {
            try {
                String text = textarea.getText();
                byte[] b = text.getBytes();
                FileOutputStream fos = new FileOutputStream(filee);
                fos.write(b);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    public void saveas() {
        int i = filechooser.showSaveDialog(jf);
        if (i == 0) {
            try {
                String text = textarea.getText();
                byte[] b = text.getBytes();
                filee = filechooser.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(filee);
                fos.write(b);
                fos.close();
                setTitle(filee.getName());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void pageSetup() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.pageDialog(pj.defaultPage());

    }

    public void print() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog()) {
            try {
                pj.print();
            } catch (PrinterException exc) {
                System.out.println(exc);
            }
        }
    }

    public void replaceFrame() {
        replaceframe = new JFrame("Replace");
        replaceframe.setSize(500, 300);
        replaceframe.setLayout(null);
        replaceframe.setVisible(true);

        JLabel jl1 = new JLabel("Find What:");
        jl1.setBounds(30, 50, 70, 40);
        replaceframe.add(jl1);

        jt1 = new JTextField();
        jt1.setBounds(100, 50, 300, 40);
        replaceframe.add(jt1);

        JLabel jl2 = new JLabel("Replace With:");
        jl2.setBounds(30, 100, 70, 40);
        replaceframe.add(jl2);

        jt2 = new JTextField();
        jt2.setBounds(100, 100, 300, 40);
        replaceframe.add(jt2);

        jb1 = new JButton("Replace");
        jb1.addActionListener(this);
        jb1.setBounds(200, 150, 80, 30);
        replaceframe.add(jb1);

    }

    public void replace() {
        String find_what = jt1.getText();
        String replace_with = jt2.getText();
        String text = textarea.getText();
        String new_text = text.replace(find_what, replace_with);
        textarea.setText(new_text);
        replaceframe.setVisible(false);

    }

    public void setDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        String datetime = ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        textarea.setText(datetime);
    }

    public void openFontFrame() {
        font_frame = new JFrame("Fonts...");
        font_frame.setSize(600, 300);
        font_frame.setLayout(null);

        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontfamiles=ge.getAvailableFontFamilyNames();
        jc_font_famally = new JComboBox(fontfamiles);
        jc_font_famally.setBounds(50, 50, 170, 40);
        font_frame.add(jc_font_famally);
        
        String[] font_style={"Plain","Bold","Italic"};
        jc_font_style = new JComboBox(font_style);
        jc_font_style.setBounds(280, 50, 100, 40);
        font_frame.add(jc_font_style);
        
        Integer[] font_size={20,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,35,40,45,50,55};
        jc_font_size = new JComboBox(font_size);
        jc_font_size.setBounds(450, 50, 80, 40);
        font_frame.add(jc_font_size);
        
        ok=new JButton("OK");
        ok.setBounds(280, 150, 50, 30);
        ok.addActionListener(this);
        font_frame.add(ok);
        font_frame.setVisible(true);

    }

    public void setFontToNotepad(){
        String font_family=(String)jc_font_famally.getSelectedItem();
        String font_style=(String)jc_font_style.getSelectedItem();
        Integer font_size=(Integer)jc_font_size.getSelectedItem();
        int font_stylee = 0;
        if(font_style.equals("Plane")){
            font_stylee=Font.PLAIN;
        }else if(font_style.equals("Bold")){
            font_stylee=Font.BOLD;
        }else if(font_style.equals("Italic")){
            font_stylee=Font.ITALIC;
        }
        Font f=new Font(font_style, font_stylee, font_size);
        textarea.setFont(f);
        font_frame.setVisible(false);
    }
    public void setFontColor() {
        Color c = JColorChooser.showDialog(jf, "Select font color", Color.DARK_GRAY);
        textarea.setForeground(c);
    }

    public void setTextAreaColor() {
        Color c = JColorChooser.showDialog(jf, "Select text area color", Color.DARK_GRAY);
        textarea.setBackground(c);
    }

    public void setTitle(String title) {
        jf.setTitle(title);
    }
}