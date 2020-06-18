package evolve1.pkg1;

import javax.swing.JFrame;
/**
 *
 * @author joechan
 */
public class Evolve11 extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
{
    
        JFrame frame = EvoGUI.getInstance();
        frame.setVisible( true );
        frame.setLocationRelativeTo( null );
        frame.setResizable( false );
        frame.setDefaultCloseOperation( Evolve11.EXIT_ON_CLOSE ); // exits run
    
    
}
}
