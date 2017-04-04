package View;

import Controleur.GameController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

public final class CustomGamePanel  extends JFrame{   
    JSlider sliderH, sliderW, sliderB;  
    JTextField textH, textW, textB;
    JPanel heightComponent, widthComponent, mineComponent;

    public CustomGamePanel(GameController controleur){
        this.setSize(320, 250);
        this.setTitle("Custom"); 

        this.sliderH = createSlider(9, 24, 10, 1, 5);
        this.textH = new JTextField(Integer.toString(this.sliderH.getValue()), 4);

        this.sliderW = createSlider(9, 29, 11, 1, 5);
        this.textW = new JTextField(Integer.toString(this.sliderW.getValue()), 4);

        this.sliderB = createSlider(9, (int) (this.sliderH.getValue() * this.sliderW.getValue() * 0.85), 76, 5, 20);
        this.textB = new JTextField(Integer.toString(this.sliderB.getValue()), 4);
        this.sliderB.setLabelTable(this.sliderB.createStandardLabels(26));

        this.heightComponent = this.createComponent(this.sliderH, this.textH, "Height");
        this.widthComponent = this.createComponent(this.sliderW, this.textW, "Width");
        this.mineComponent = this.createComponent(this.sliderB, this.textB, "Mine");

        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(this.heightComponent);      
        this.add(this.widthComponent);      
        this.add(this.mineComponent);
        JButton buttonV = new JButton("Valide");
        buttonV.addActionListener((ActionEvent e) -> {
            controleur.restart(CustomGamePanel.this.sliderH.getValue(), CustomGamePanel.this.sliderW.getValue(), (int) ((double) (100.0 * CustomGamePanel.this.sliderB.getValue()) / (double) (CustomGamePanel.this.sliderH.getValue() * CustomGamePanel.this.sliderW.getValue())));
            this.setVisible(false);
        });
        JButton buttonC = new JButton("Cancel");
        buttonC.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
        });
        this.add(buttonV, BorderLayout.SOUTH);
        this.add(buttonC, BorderLayout.SOUTH);

        this.setResizable(false);
      }

    public void visible(boolean isVisible){
        this.setVisible(isVisible);
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
    public JPanel createComponent(JSlider slider, JTextField text, String lab){
        JPanel newComponent = new JPanel();
                JLabel label = new JLabel(lab);
                text.addKeyListener(new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent event){}
                    @Override
                    public void keyPressed(KeyEvent event){}
                    @Override
                    public void keyReleased(KeyEvent event)
                    {
                        try{
                            int value = Integer.valueOf(text.getText());
                            if(value <= slider.getMaximum()){
                                if(value >= slider.getMinimum()) slider.setValue(value);
                            }else{
                                text.setText(Integer.toString(slider.getValue()));
                            }
                        }catch(NumberFormatException e){
                            //text.setText("");
                        }
                    }
                });
        newComponent.add(label);
        newComponent.add(slider);
        newComponent.add(text);
        return newComponent;
    }
    public void myEvent(ChangeEvent event){
        this.textH.setText(Integer.toString(this.sliderH.getValue()));
        this.textW.setText(Integer.toString(this.sliderW.getValue()));
        this.textB.setText(Integer.toString(this.sliderB.getValue()));

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
