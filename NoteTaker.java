import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class NoteTaker extends JFrame {

  private static final long serialVersionUID = 6428932305742138514L;
  public int index = 0;

  public static ArrayList<NoteWindow> windows = new ArrayList<NoteWindow>();

  public NoteTaker() {

    this.setSize(600, 600);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.setResizable(false);

    JTextArea list = new JTextArea();
    list.setEditable(false);
    list.setFont(new Font("Verdana", Font.BOLD, 20));
    list.setForeground(Color.BLUE);
    list.setBackground(Color.BLACK);
    list.setLineWrap(true);

    JScrollPane scroller = new JScrollPane(list);
    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

    JButton open = new JButton("Create note");

    open.setFont(new Font("Verdana", Font.BOLD, 20));
    open.setBackground(Color.BLACK);
    open.setForeground(Color.YELLOW);

    open.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String name = "";
        while (name == "")
          name = JOptionPane.showInputDialog("What is then name of the note?", "Enter name of Note");
        if (name != null) {
          windows.add(new NoteWindow(name, index));
          index++;
        }
      }
    });

    JButton chooseColor = new JButton("Choose Background");
    chooseColor.setFont(new Font("Verdana", Font.BOLD, 20));
    chooseColor.setBackground(Color.BLACK);
    chooseColor.setForeground(Color.YELLOW);

    chooseColor.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", list.getBackground());
        list.setBackground(newColor);
        list.setForeground(getContrastColor(newColor));

      }

    });

    JButton refresh = new JButton("Update Notes");

    refresh.setFont(new Font("Verdana", Font.BOLD, 20));
    refresh.setBackground(Color.BLACK);
    refresh.setForeground(Color.YELLOW);

    refresh.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        list.setText("");
        for (int i = 0; i < windows.size(); i++) {
          list.append((i + 1) + " - " + windows.get(i).getTitle() + "\n");
        }
      }

    });

    JButton sortAlpha = new JButton("Sort Alphabetically");
    sortAlpha.setFont(new Font("Verdana", Font.BOLD, 15));
    sortAlpha.setBackground(Color.BLACK);
    sortAlpha.setForeground(Color.YELLOW);
    sortAlpha.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        ArrayList<String> names = new ArrayList<String>();

        for (int i = 0; i < windows.size(); i++) {
          names.add(windows.get(i).getTitle());
        }

        Collections.sort(names);

        list.setText("");
        for (int i = 0; i < windows.size(); i++) {
          list.append((i + 1) + " - " + names.get(i) + "\n");
        }

      }

    });

    JPanel sPanel = new JPanel();
    sPanel.setBackground(new Color(27, 6, 51));
    sPanel.add(refresh);
    sPanel.add(sortAlpha);

    JPanel nPanel = new JPanel();
    nPanel.setBackground(new Color(27, 6, 51));
    nPanel.add(open);
    nPanel.add(chooseColor);

    this.getContentPane().setBackground(new Color(33, 19, 69));
    this.getContentPane().add(BorderLayout.NORTH, nPanel);
    this.getContentPane().add(BorderLayout.CENTER, scroller);
    this.getContentPane().add(BorderLayout.SOUTH, sPanel);

    this.addWindowListener(new WindowAdapter() {

      public void windowClosing(WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window? All notes will be lost.",
            "Close Window?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }

    });

    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  public Color getContrastColor(Color color) {
    double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
    return y >= 128 ? Color.black : Color.white;
  }

}