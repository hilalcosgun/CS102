import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TreePanel extends JPanel implements ActionListener{
    private int currentSeason;
    private int[][] snowflakes;
    private int[] xSpeeds;
    private int[] ySpeeds;
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private JPanel floor;
    private Timer timer;
    private double shiftAmount;


    public void setCurrentSeason(int currentSeason) {
        this.currentSeason = currentSeason;
    }
    TreePanel(){
        currentSeason = 0;
        floor = new JPanel();
        floor.setPreferredSize(new Dimension(WIDTH,50));
        snowflakes = new int[500][2];
        xSpeeds = new int[500];
        ySpeeds = new int[500];
        for(int i = 0; i < snowflakes.length; i++){
            createSnowflake(i);
        }
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setLayout(new BorderLayout());
        this.add(floor, BorderLayout.SOUTH);
        timer = new Timer(70, this);
        timer.start();
        shiftAmount = 0.005;
    }
    public void fillLeaves(Graphics g){
        int[] xPoints = {46,117,221,268,294,381,568,694,645,510,308,154};
        int[] yPoints = {342,184,153,198,93,80,120,256,390,433,384,447};
        g.fillPolygon(xPoints, yPoints, 12);
    }
    public void createSnowflake(int i){
        Random rand = new Random();
        int x;
        int y;
        int xSpeed;
        int ySpeed;
        x = rand.nextInt(WIDTH);
        y = rand.nextInt(HEIGHT - floor.getHeight());
        xSpeed = rand.nextInt(15)-7;
        ySpeed = rand.nextInt(15);
        snowflakes[i][0] = x;
        snowflakes[i][1] = y;
        xSpeeds[i] = xSpeed;
        ySpeeds[i] = ySpeed;
    }   
    public void WindBody(Graphics g, int strength){
        int[] xPoints = {185,175,205,350,365,320,395,380,405,475,440,505,455,500,560,600,575,500,440,470,540,260,330,360,340};
        int[] yPoints = {415,250,390,440,320,240,310,455,530,505,350,190,350,505,450,300,470,555,630,720,750,750,715,620,485};
        int newX;
        for(int i = 0; i < xPoints.length; i++){
            newX = (xPoints[i] + (int) (Math.round(shiftAmount*strength* (750 - yPoints[i]))));
            xPoints[i] = newX;
        }
        g.fillPolygon(xPoints,yPoints,25);

    }
    public void WindLeaves(Graphics g, int strength){
        int[] xPoints = {46,117,221,268,294,381,568,694,645,510,308,154};
        int[] yPoints = {342,184,153,198,93,80,120,256,390,433,384,447};
        int newX;
        for(int i = 0; i < xPoints.length; i++){
            newX = (int) (xPoints[i] + Math.round(shiftAmount*strength* (750 - yPoints[i])));
            xPoints[i] = newX;
        }
        g.fillPolygon(xPoints, yPoints, 12);
    }
    public void fillSnowflakes(Graphics g){
        for(int i = 0; i < snowflakes.length; i++){
            g.fillOval(snowflakes[i][0], snowflakes[i][1], 4, 4);
        }
    }
    public void fillBody(Graphics g){
        int[] xPoints = {185,175,205,350,365,320,395,380,405,475,440,505,455,500,560,600,575,500,440,470,540,260,330,360,340};
        int[] yPoints = {415,250,390,440,320,240,310,455,530,505,350,190,350,505,450,300,470,555,630,720,750,750,715,620,485};
        g.fillPolygon(xPoints, yPoints, 25);
    }
    public void fillApples(Graphics g){
        g.fillOval(117, 278, 25, 25);
        g.fillOval(221, 324, 25, 25);
        g.fillOval(366, 167, 25, 25);
        g.fillOval(561, 222, 25, 25);
        g.fillOval(499, 362, 25, 25);
    }
    @Override
    public void paintComponent(Graphics g){
        if(currentSeason == 0){
            this.setBackground(new Color(158, 232, 240));
            super.paintComponent(g);
            g.setColor(new Color(0,204,102));
            WindLeaves(g,1);
            g.setColor(new Color(118,31,31));
            WindBody(g,1);
            floor.setBackground(new Color(122,154,103));
        }
        else if(currentSeason == 1){
            this.setBackground(new Color(149, 205, 229));
            super.paintComponent(g);
            g.setColor(new Color(0,204,0));
            fillLeaves(g);
            g.setColor(new Color(105,27,27));
            fillBody(g);
            g.setColor(new Color(204, 0, 0));
            fillApples(g);
            floor.setBackground(new Color(0, 150, 0));
        }
        else if(currentSeason == 2){
            this.setBackground(new Color(69, 135, 145));
            super.paintComponent(g);
            g.setColor(new Color(204,102,0));
            WindLeaves(g,2);
            g.setColor(new Color(90,22,22));
            WindBody(g, 2);
            floor.setBackground(new Color(153, 76, 0));
            
        }
        else if(currentSeason == 3){
            this.setBackground(new Color(86, 84, 115));
            super.paintComponent(g);
            g.setColor(new Color(51,25,0));
            WindBody(g,3);
            floor.setBackground(new Color(128, 128, 128));
            g.setColor(Color.WHITE);
            fillSnowflakes(g);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        shiftAmount = shiftAmount* -1;
        if(currentSeason == 3){
            for(int i = 0; i < snowflakes.length; i++){
                snowflakes[i][0] = snowflakes[i][0] + xSpeeds[i];
                snowflakes[i][1] = snowflakes[i][1] + ySpeeds[i];
                if(snowflakes[i][0] >= this.WIDTH || snowflakes[i][1] >= HEIGHT - floor.HEIGHT){
                    createSnowflake(i);
                }
            }  
        }
        repaint();
    }


}
