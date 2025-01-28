import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

public class PaintFrame extends JFrame implements MouseInputListener{
    protected Controller controller;
    protected BufferedImage image;
    public PaintFrame(Controller controller){
        this.controller = controller;
        this.setTitle("Paint Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseMotionListener(this);
        this.setVisible(false);
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        controller.usePen(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(controller.currentTool.equals("pen")){
            controller.usePen(e.getX(), e.getY());
            repaint();
        }
        if(controller.currentTool.equals("laser")){
            Color firstColor , newColor;
            if(e.getX() > 0 && e.getY() > 0 && e.getX() < image.getWidth() && e.getY() < image.getHeight()){
                firstColor = new Color(image.getRGB(e.getX(), e.getY()));
                newColor = controller.toolsFrame.curColor;
                controller.useLaserUp(e.getX(), e.getY(), firstColor, newColor );
                controller.useLaserDown(e.getX(), e.getY()+1, firstColor, newColor );
                repaint();
            }
            
        }
      
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    
}

