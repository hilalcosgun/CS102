import java.awt.Color;
import java.awt.image.BufferedImage;

import javafx.scene.layout.Background;

public class Controller {
    protected SettingsFrame settingsFrame;
    protected ToolsFrame toolsFrame;
    protected PaintFrame paintFrame;
    protected BufferedImage image;
    protected String currentTool;

    Controller(){
        settingsFrame = new SettingsFrame(this);
        toolsFrame =new ToolsFrame(this);
        paintFrame = new PaintFrame(this);
        currentTool = "pen";
    }

    public void controlContinueButton(int w, int h){
        settingsFrame.setVisible(false);
        paintFrame.setSize(w,h);
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        paintFrame.image = image;
        paintFrame.setVisible(true);
        toolsFrame.setVisible(true);  
    }

    public void clear(){
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++){
                image.setRGB(i, j, Color.WHITE.getRGB());
            }    
        }
        paintFrame.repaint();
    }
    public void usePen(int x, int y){
        int startX = x - toolsFrame.curSize;
        if(startX < 0){
            startX = 0;
        }
        int startY = y - toolsFrame.curSize;
        if(startY < 0){
            startY = 0;
        }
        for(int i = 0; i < toolsFrame.curSize* 2 + 1; i++){
            for(int j = 0; j < toolsFrame.curSize* 2 + 1; j++){
                if(startX + i < image.getWidth() && startY + j < image.getHeight()){
                    image.setRGB(startX + i, startY + j, toolsFrame.curColor.getRGB());
                } 
            }    
        }
    }
    public boolean isSimilar(Color firstColor, Color newColor){
        return (Math.abs(firstColor.getRed() - newColor.getRed()) +  
        Math.abs(firstColor.getGreen() - newColor.getGreen()) +  
        Math.abs(firstColor.getBlue() - newColor.getBlue())) / 3 < toolsFrame.curTolerance;
    
    }
    public void useLaserUp(int x, int y, Color firstColor, Color newColor){
        if(y < 0 ||y >= image.getHeight()){
            return;
        }
        Color curColor = new Color(image.getRGB(x, y));
        if(isSimilar(firstColor, curColor)){
            image.setRGB(x, y, newColor.getRGB());
            
        }
        else{
            return;
        }
        useLaserUp(x, y-1, firstColor, newColor);  
    }
    public void useLaserDown(int x, int y, Color firstColor, Color newColor){
        if(y < 0 || y >= image.getHeight()){
            return;
        }
        Color curColor = new Color(image.getRGB(x, y));
        if(isSimilar(firstColor, curColor)){
            image.setRGB(x, y, newColor.getRGB()); 
        }
        else{
            return;
        }
        useLaserDown(x, y+1, firstColor, newColor);  
    }

}

    
   


