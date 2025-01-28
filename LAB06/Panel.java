import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener{
    protected BufferedImage[] images;
    protected int currentImage = 0;
    protected int[] iterationCount;
    Panel(){
        images = new BufferedImage[3];
        setBackground(Color.WHITE);
        try {
            images[0] = ImageIO.read(new File("image.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            images[1] = ImageIO.read(new File("image2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            images[2] = ImageIO.read(new File("image3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(600,600);
        setFocusable(true);
        iterationCount = new int[3];
        Timer timer = new Timer(1, this);
        timer.start();
        MyKeyListener keyListener = new MyKeyListener();
        this.addKeyListener(keyListener);    
    }

    private int[][] findChain(int[] initial, int width, int height){
        int chainLength = Math.min(width-initial[0]-1, height-initial[1]-1) + 1;
        int[][] chain = new int[chainLength][2];
        for (int i = 0; i < chain.length; i++) {
            chain[i][0] = initial[0] + i;
            chain[i][1] = initial[1] + i;
        }
        return chain;
    }

    private int[][][] findPixels(){
        BufferedImage image = images[currentImage];
        int width = image.getWidth();
        int height = image.getHeight(); 
        int maxChainLength = Math.min(width, height);
        int[][][] pixels = new int[width+height-1][maxChainLength][2];
        int k = height-1;
        int m = 1;
        for(int i = 0; i < pixels.length; i++){
            if(k >= 0){
                int[] initial = {0,k};
                pixels[i] = findChain(initial, width, height);
                k--;
            }
            else if( m < width){
                int[] initial = {m,0};
                pixels[i] = findChain(initial, width, height);
                m++;
            }
        }
        return pixels;
    } 

    private double calculateBrightness(BufferedImage image, int x, int y){
        Color color = new Color(image.getRGB(x, y));
        return (0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());
    }

    private void sortImage(){
        BufferedImage image = images[currentImage];
        int[][][] pixels = findPixels();
        for(int chainNum = 0; chainNum < pixels.length ; chainNum++){
            int chain[][] = pixels[chainNum];
            int current = iterationCount[currentImage];
            double maxBrightness = Integer.MIN_VALUE;
            int maxIndex = -1;
            for(int j = current; j < chain.length; j++){
                double brightness = calculateBrightness(image,chain[j][0], chain[j][1]);
                if( brightness > maxBrightness){
                    maxBrightness = brightness;
                    maxIndex = j;
                }
            }
            if(current < chain.length){
                int newColor = image.getRGB(chain[maxIndex][0], chain[maxIndex][1]);
                int temp = image.getRGB(chain[current][0], chain[current][1]);
                image.setRGB(chain[current][0], chain[current][1], newColor); 
                image.setRGB(chain[maxIndex][0], chain[maxIndex][1], temp); 
            }
        }
        iterationCount[currentImage]++; 
        repaint();       
    }

   @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage image = images[currentImage];
        double f = (Math.min(((double) this.getWidth()) / image.getWidth() , ((double) this.getHeight()) / image.getHeight() )) ;
        int resizedWidth = (int) (image.getWidth() * f);
        int resizedHeight = (int) (image.getHeight() * f);
        g.drawImage(image, 0, 0, resizedWidth, resizedHeight,null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        sortImage();
    }

    public class MyKeyListener implements KeyListener{
        BufferedImage image = images[currentImage];
        @Override
        public void keyTyped(KeyEvent e) {
            if(e.getKeyChar() == 'R' || e.getKeyChar() == 'r'){
                    try {
                        if(currentImage == 0){
                            images[currentImage] = ImageIO.read(new File("image.jpg"));
                        }
                        else if(currentImage == 1){
                            images[currentImage] = ImageIO.read(new File("image2.jpg"));
                        }
                        else if(currentImage == 2){
                            images[currentImage] = ImageIO.read(new File("image3.jpg"));
                        }
                    } catch (IOException f) {
                        f.printStackTrace();
                }
                iterationCount[currentImage] = 0;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 37){
                currentImage--;
                if(currentImage < 0){
                    currentImage = 2;
                }
            }    
            else if(e.getKeyCode() == 39 ){
                currentImage++;
                if(currentImage > 2){
                    currentImage = 0;
                }
            }  
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }  
    }
}
