import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ToolsFrame extends JFrame implements ActionListener {
    protected Controller controller;
    protected int curSize;
    protected Color curColor;
    protected int curTolerance;
    protected JButton clear, pen, penSize, laser, color, tolerance;
    ToolsFrame(Controller controller){
        this.controller = controller;
        this.setSize(100,400);
        this.setLayout(new GridLayout(6,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        curSize = 5;
        curColor = Color.BLACK;
        curTolerance = 1;
        clear = new JButton("Clear");
        pen = new JButton("Pen");
        penSize = new JButton("Pen Size");
        laser = new JButton("Laser");
        color = new JButton("Color");
        tolerance = new JButton("Tolerance");
        clear.addActionListener(this);
        pen.addActionListener(this);
        penSize.addActionListener(this);
        laser.addActionListener(this);
        color.addActionListener(this);
        tolerance.addActionListener(this);
        this.add(clear);
        this.add(pen);
        this.add(penSize);
        this.add(laser);
        this.add(color);
        this.add(tolerance);
        this.setVisible(false);

    }
    public void penSize(){
        String penSize = JOptionPane.showInputDialog("Enter pen size:","");
        if(penSize != null){
            if(!penSize.isEmpty()){
                try {
                    curSize = Integer.parseInt(penSize);
                }catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter an integer value.");
                }
            }
            
        }  
    }
    public void tolerance(){
        String tolerance = JOptionPane.showInputDialog("Enter tolerance level(0-255):","");
        if(tolerance != null){
            if(!tolerance.isEmpty()){
                try {
                if(Integer.parseInt(tolerance) >= 0 && Integer.parseInt(tolerance) <= 255){
                    curTolerance = Integer.parseInt(tolerance);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Tolerance level must be between 0-255");
                }
            
                }catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter an integer value.");
                }
            }  
        }
    }
    public void color(){
        Color temp = curColor;
        curColor = JColorChooser.showDialog(null, "Pick a Color!", Color.WHITE);
        if(curColor == null){
            curColor = temp;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clear){
            controller.currentTool = "clear";
            controller.clear();
        }
        else if(e.getSource() == pen){
            controller.currentTool = "pen";
        }
        else if(e.getSource() == penSize){
            penSize();
        }
        else if(e.getSource() == color){
            color();
        }
        else if(e.getSource() == laser){
            controller.currentTool = "laser";
        }
        else if(e.getSource() == tolerance){
            tolerance();
        }
    }
}
