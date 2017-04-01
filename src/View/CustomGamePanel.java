package View;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

public class CustomGamePanel  extends JFrame{   
    JSlider height = new JSlider(JSlider.HORIZONTAL,9, 24, 9);
    JSlider width = new JSlider(JSlider.HORIZONTAL,9, 30, 9);
    JSlider mines = new JSlider(JSlider.HORIZONTAL,9, 139, 9);    
    JTextField jtfHeight, jtfWidth, jtfMines;
    JPanel labels, sliders, textfields;

    public CustomGamePanel(){
        this.setSize(250, 300);
        this.setTitle("Custom");

        this.labels = new JPanel();      
        this.labels.setLayout(new BoxLayout(this.labels, BoxLayout.PAGE_AXIS));
        this.sliders = new JPanel();      
        this.sliders.setLayout(new BoxLayout(this.sliders, BoxLayout.PAGE_AXIS));
        this.textfields = new JPanel();      
        this.textfields.setLayout(new BoxLayout(this.textfields, BoxLayout.PAGE_AXIS));
        
        height.setPaintTicks(true);
        height.setPaintLabels(true);
        height.setMinorTickSpacing(1);
        height.setMajorTickSpacing(5); 
        
        width.setPaintTicks(true);
        width.setPaintLabels(true);
        width.setMinorTickSpacing(1);
        width.setMajorTickSpacing(5);
        
        mines.setPaintTicks(true);
        mines.setPaintLabels(true);
        mines.setMinorTickSpacing(5);
        mines.setMajorTickSpacing(20);   
        
        height.addChangeListener(this::myEvent);    
        width.addChangeListener(this::myEvent);   

        this.getContentPane().add(height, BorderLayout.NORTH);
        this.getContentPane().add(width, BorderLayout.CENTER);
        this.getContentPane().add(mines, BorderLayout.SOUTH);

        this.setVisible(true);
      }
    
    public void myEvent(ChangeEvent event){
        System.out.print("Valeur actuelle : " + ((JSlider)event.getSource()).getValue());
        mines.setMaximum((int) (height.getValue()*width.getValue()*0.85));
        if(height.getValue()*width.getValue()*85/100 > 200){
            mines.setMinorTickSpacing(10);
            mines.setMajorTickSpacing(40);
            mines.setLabelTable(mines.createStandardLabels(82));
        }else{
            mines.setMinorTickSpacing(5);
            mines.setMajorTickSpacing(20);
            mines.setLabelTable(mines.createStandardLabels(26));
        }
    }
}
