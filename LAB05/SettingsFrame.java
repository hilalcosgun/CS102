import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame implements ActionListener{
    protected Controller controller;
    protected int width, height;
    protected JPanel contentPanel;
    protected JLabel wLabel, hLabel;
    protected JTextField wField, hField;
    protected JButton continueButton;
    
    SettingsFrame(Controller controller){
        this.controller = controller;
        this.setSize(200,150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        contentPanel = new JPanel();
        wLabel = new JLabel("width");
        hLabel = new JLabel("height");
        wField = new JTextField(10);
        hField = new JTextField(10);
        continueButton = new JButton("Continue");
        continueButton.addActionListener(this);
        contentPanel.add(wLabel);
        contentPanel.add(wField);
        contentPanel.add(hLabel);
        contentPanel.add(hField);
        contentPanel.add(continueButton);
        this.add(contentPanel);
        this.setVisible(true);
    }

    public boolean checkValidation(){
        String wText = wField.getText();
        String hText = hField.getText();

        if(wText.isEmpty() || hText.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter both values.");
        }    
        else{
            try {
                width = Integer.parseInt(wText);
                height = Integer.parseInt(hText);
                if (width > 0 && width < 1000 && height > 0 && height < 1000) {
                    return true;
                } 
                else {
                    JOptionPane.showMessageDialog(null,"Please enter values between 0 and 1000.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter integer values.");
            }
        }
        return false;
    }    
        
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(this.checkValidation()){
            controller.currentTool = "clear";
            controller.controlContinueButton(width, height);
        }
    }
}

