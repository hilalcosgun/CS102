import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TreeFrame extends JFrame implements ActionListener {
    JButton Spring;
    JButton Summer;
    JButton Fall;
    JButton Winter;
    TreePanel treePanel;

    TreeFrame(){

        JPanel buttonPanel = new JPanel();
        Spring = new JButton("Spring");
        Summer = new JButton("Summer");
        Fall = new JButton("Fall");
        Winter = new JButton("Winter");
        Spring.addActionListener(this);
        Summer.addActionListener(this);
        Fall.addActionListener(this);
        Winter.addActionListener(this);
        buttonPanel.add(Spring);
        buttonPanel.add(Summer);
        buttonPanel.add(Fall);
        buttonPanel.add(Winter);

        treePanel = new TreePanel();

        this.setTitle("Seasons");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0,197,204));
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(treePanel, BorderLayout.CENTER);
        this.setVisible(true);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Spring){
            treePanel.setCurrentSeason(0);
            treePanel.repaint();
        }
        else if(e.getSource() == Summer){
            treePanel.setCurrentSeason(1);
            treePanel.repaint();
        }
        else if(e.getSource() == Fall){
            treePanel.setCurrentSeason(2);
            treePanel.repaint();
        }
        else if(e.getSource() == Winter){
            treePanel.setCurrentSeason(3);
            treePanel.repaint();
        }
    }
    
}
