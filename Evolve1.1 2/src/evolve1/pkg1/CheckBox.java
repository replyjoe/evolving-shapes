package evolve1.pkg1;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;

/**
 *
 * @author joechan
 */
public class CheckBox extends JCheckBox{
    
    private String label;
    
    public CheckBox(String label) {
        super ( label );
        this.label = label;
        this.setText(label);
        event selection = new event();
        this.addItemListener(selection);
        this.setEnabled(true);
    }
    
    public class event implements ItemListener {
        public void itemStateChanged(ItemEvent selection) {
            if(isSelected()==true){
                EvoGUI gui = EvoGUI.getInstance();
                gui.enableEvolveButton();
            } else {
                setText(label);
            }
            if(isSelected()==false){
                EvoGUI gui = EvoGUI.getInstance();
                gui.disableEvolveButton();
            } else {
                setText(label);
            }
        }
        
        
        
    }
    
    
    
    
    
}
