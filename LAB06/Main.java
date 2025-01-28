
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame myFrame = new JFrame();
        Panel panel = new Panel();
        myFrame.add(panel);
        myFrame.setSize(600,600);
        panel.setSize(myFrame.getSize());
        myFrame.setVisible(true);
    }
}