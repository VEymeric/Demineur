package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

public class CustomGamePanel  extends JFrame{   
    JSlider sliderH, sliderW, sliderB;  
    JTextField textH, textW, textB;
    JPanel heightComponent, widthComponent, mineComponent;

    public CustomGamePanel(){
        this.setSize(400, 400);
        this.setTitle("Custom");

        sliderH = createSlider(9, 24, 15, 1, 5);
        textH = new JTextField(Integer.toString(sliderH.getValue()), 4);
        sliderW = createSlider(9, 24, 15, 1, 5);
        textW = new JTextField(Integer.toString(sliderW.getValue()), 4);

        sliderB = createSlider(9, 24, 15, 1, 5);
        textB = new JTextField(Integer.toString(sliderH.getValue()), 4);

        this.heightComponent = this.createComponent(sliderH, textH);
        this.widthComponent = this.createComponent(sliderW, textW);
        this.mineComponent = this.createComponent(sliderB, textB);
        sliderW.setValue(0);

        this.setLayout(new GridLayout(4,1));
        this.add(this.heightComponent);      
        this.add(this.widthComponent);      
        this.add(this.mineComponent);
        this.add(new JButton("test"));
        this.setVisible(true);
      }
    public void stateChanged(ChangeEvent ev){
        sliderH.setValue(0);
  }
    public JSlider createSlider(int min, int max, int value, int minorTick, int majorSpacing){
        JSlider slider = new JSlider(JSlider.HORIZONTAL,min, max, value);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(minorTick);
        slider.setMajorTickSpacing(majorSpacing);
        slider.addChangeListener(this::myEvent);
        return slider;
    }
    public JPanel createComponent(JSlider slider, JTextField text){
        JPanel newComponent = new JPanel(); ;         
        newComponent.add(slider);
        newComponent.add(text);
        return newComponent;
    }
    public void myEvent(ChangeEvent event){
        this.textH.setText(Integer.toString(sliderH.getValue()));
        this.textW.setText(Integer.toString(sliderW.getValue()));
        this.textB.setText(Integer.toString(sliderB.getValue()));

        this.sliderB.setMaximum((int) (this.sliderH.getValue()*this.sliderW.getValue()*0.85));
        if(this.sliderH.getValue()*this.sliderW.getValue()*85/100 > 200){
            this.sliderB.setMinorTickSpacing(10);
            this.sliderB.setMajorTickSpacing(40);
            this.sliderB.setLabelTable(this.sliderB.createStandardLabels(82));
        }else{
            this.sliderB.setMinorTickSpacing(5);
            this.sliderB.setMajorTickSpacing(20);
            this.sliderB.setLabelTable(this.sliderB.createStandardLabels(26));

        }
    }
}
