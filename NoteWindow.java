import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NoteWindow extends JFrame {

  private static final long serialVersionUID = -8156373390011941802L;
  private int index;

  public NoteWindow(String title, int index) {
    this.setIndex(index);
    this.setTitle(title);
    this.setSize(new Dimension(500, 400));
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.getContentPane().add(getTextArea());

    this.addWindowListener(new WindowAdapter() {

      public void windowClosing(WindowEvent windowEvent) {
        NoteTaker.windows.remove(index);
      }

    });

    this.setVisible(true);
  }

  private JScrollPane getTextArea() {

    JTextArea toRet = new JTextArea();
    toRet.setFont(new Font("Tahoma", Font.BOLD, 20));
    toRet.setBackground(new Color(32, 34, 61));
    toRet.setForeground(Color.BLUE);

    JScrollPane scroller = new JScrollPane(toRet);
    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    toRet.requestFocus();

    return scroller;

  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

}
