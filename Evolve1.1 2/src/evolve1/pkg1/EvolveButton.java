package evolve1.pkg1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author joechan
 */
public class EvolveButton extends JButton{

    public EvolveButton(String label) {
        super ( label );
        event click = new event();
        this.addActionListener(click);
        this.setEnabled(false);
    }
    
    public class event implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent click) {
            EvoGUI gui = EvoGUI.getInstance();
            gui.evolvePopulation();
            gui.cx1.setSelected(false);
            gui.cx2.setSelected(false);
            gui.cx3.setSelected(false);
            gui.cx4.setSelected(false);
            gui.label.setText("Select 2 images");
            gui.checkcounter = 0;
            gui.EvolveButton.setEnabled(false);
            
        }
        
    }
    
    
}
