package evolve1.pkg1;

import java.awt.BorderLayout;
import static java.awt.Color.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author joechan
 */
public class EvoGUI extends JFrame{
    
    protected int population;
    protected ArrayList<float[]> geno;
    protected ArrayList<float[]> parents;
    protected ArrayList<float[]> children;
    protected EvolveButton EvolveButton;
    protected CheckBox cx1;
    protected CheckBox cx2;
    protected CheckBox cx3;
    protected CheckBox cx4;
    protected int checkcounter;
    protected int count;
    protected JLabel label;
    protected JLabel counter;
    
    protected EvoCanvas[] display; //4 or 6
    
    public static final EvoGUI INSTANCE = new EvoGUI();

    private EvoGUI() {
        
        this.population = 4;
        this.geno = new ArrayList<float[]>();
        this.parents = new ArrayList<float[]>();
        this.children = new ArrayList<float[]>();
        this.checkcounter = 0;
        
        // [shape angle,edge ratio,plane scale,plane angle,r,g,b,r,g,b]
        // [1,2,3,4,5,6,7,8,9,10]
        
        this.EvolveButton = new EvolveButton("Evolve");
        
        this.cx1 = new CheckBox("1");
        this.cx2 = new CheckBox("2");
        this.cx3 = new CheckBox("3");
        this.cx4 = new CheckBox("4");
        
        this.display  = new EvoCanvas[population];
        this.count = 0;
        this.label = new JLabel("Select 2 Images");
        this.counter = new JLabel("");
        
        setTitle( "Evolvutionary Art" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        
        JPanel panelUp      = new JPanel();
        JPanel panelCenter  = new JPanel();
        JPanel panelRight   = new JPanel();
        JPanel panelLeft    = new JPanel();
        JPanel panelDown    = new JPanel();
        
        add(panelUp, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelRight, BorderLayout.EAST);
        add(panelLeft, BorderLayout.WEST);
        add(panelDown, BorderLayout.SOUTH);
        
        panelUp.setPreferredSize(new Dimension(60,40));
        panelCenter.setPreferredSize(new Dimension(680,680)); //panelCenter.setBackground(BLUE); 
        panelRight.setPreferredSize(new Dimension(60,60)); //panelRight.setBackground(BLUE);
        panelLeft.setPreferredSize(new Dimension(60,60)); //panelLeft.setBackground(BLUE);
        panelDown.setPreferredSize(new Dimension(60,80));
        
        panelCenter.setLayout(new GridLayout(2,2,10,10));
        
        for (int x = 0; x < 4; x++) {
            this.display[x] = new EvoCanvas();
            if(x==0){
                this.display[x].setBackground(WHITE);
            }
            if(x==1){
                this.display[x].setBackground(WHITE);
            }
            if(x==2){
                this.display[x].setBackground(WHITE);
            }
            if(x==3){
                this.display[x].setBackground(WHITE);
            }
            //add new matrix button to grid
            panelCenter.add(this.display[x]);
        }
        
        panelUp.setLayout(null);
        panelDown.setLayout(null);
        panelLeft.setLayout(null);
        panelRight.setLayout(null);
        
        this.EvolveButton.setBounds( 370, 20, 60, 40 ); panelDown.add( this.EvolveButton );
        this.cx1.setBounds(10,120,60,60); panelLeft.add(this.cx1);
        this.cx2.setBounds(10,120,60,60); panelRight.add(this.cx2);
        this.cx3.setBounds(10,360,60,60); panelLeft.add(this.cx3);
        this.cx4.setBounds(10,360,60,60); panelRight.add(this.cx4);
        
        this.label.setBounds( 20, 10, 640, 20 ); panelUp.add(this.label);
        this.counter.setBounds( 285, 10, 640, 20 ); panelUp.add(this.counter);
        
        //testing
        
        generatePopulation(this.population);
        
        display[0].drawGenotype(geno.get(0));
        display[1].drawGenotype(geno.get(1));
        display[2].drawGenotype(geno.get(2));
        display[3].drawGenotype(geno.get(3));
        
    };
    
    public static EvoGUI getInstance() {
        return INSTANCE;
    }
    
    protected void enableEvolveButton(){
        this.checkcounter++;
        if(this.checkcounter==1){
            this.label.setText("Select 1 more");
        }
        if(this.checkcounter==2){
            this.EvolveButton.setEnabled(true);
            this.label.setText("Press Evolve!");
        } else if(this.checkcounter>=3){
            this.EvolveButton.setEnabled(false);
            this.label.setText("Select less images");
        }
    }
    
    protected void disableEvolveButton(){
        this.checkcounter--;
        if(this.checkcounter==0){
            this.label.setText("Select 2 images");
            this.EvolveButton.setEnabled(false);
        }
        if(this.checkcounter==1){
            this.label.setText("Select 1 more");
            this.EvolveButton.setEnabled(false);
        }
        if(this.checkcounter==2){
            this.EvolveButton.setEnabled(true);
            this.label.setText("Press Evolve!");
        } else if(this.checkcounter>=3){
            this.EvolveButton.setEnabled(false);
            this.label.setText("Select less images");
        }
    }
    
    protected void generatePopulation(int population){
        for(int n = 0 ; n < population ; n++){
            this.geno.add(generateGenotype());
        };
        
    };

    private float[] generateGenotype() {
        float[] genotype = new float[10];
        Random rand = new Random();
        genotype[0] = 90-rand.nextInt(70);    //20 to 90 degrees angles
        genotype[1] = rand.nextFloat()+0.5f;     //0.5 to 1.5 ratio for one side to the other
        genotype[2] = rand.nextFloat()+0.5f;       //scaling/size of the shapes x1 to x2 its; size
        genotype[3] = rand.nextInt(359);        //shape rotation according the plane
        
        genotype[4] = rand.nextFloat();         //genes to create 1st colour using the HSB method
        genotype[5] = rand.nextFloat();         //the range of 0.0 to 1.0
        genotype[6] = rand.nextFloat();
        
        genotype[7] = rand.nextFloat();         //genes to create 2nd colour using the HSB method
        genotype[8] = rand.nextFloat();         //the range of 0.0 to 1.0
        genotype[9] = rand.nextFloat();

        //showing genotype in system
        
//        for(int x = 0;x <10;x++){
//            System.out.printf("%.2f", genotype[x]);
//            System.out.print("  ");
//        };
//        System.out.println();
//        
         return genotype;
    };

    void evolvePopulation() {
        Random rand = new Random();
        getSelectedParents();
        children.add(mutateChild(crossoverParents(parents.get(0),parents.get(1))));        
        children.add(mutateChild(crossoverParents(parents.get(1),parents.get(0))));
        children.add(mutateChild(crossoverParents(parents.get(0),parents.get(1))));
        children.add(mutateChild(crossoverParents(parents.get(1),parents.get(0))));
        children.add(mutateChild(crossoverParents(parents.get(0),parents.get(1))));
        children.add(mutateChild(crossoverParents(parents.get(1),parents.get(0))));
        children.add(mutateChild(crossoverParents(parents.get(0),parents.get(1))));
        children.add(mutateChild(crossoverParents(parents.get(1),parents.get(0))));
        
        geno.clear();
        parents.clear();
        
        geno.add(children.get(rand.nextInt(8)));
        geno.add(children.get(rand.nextInt(8)));
        geno.add(children.get(rand.nextInt(8)));
        geno.add(generateGenotype());
        
        this.display[0].drawGenotype(geno.get(0));
        this.display[1].drawGenotype(geno.get(1));
        this.display[2].drawGenotype(geno.get(2));
        this.display[3].drawGenotype(geno.get(3));         //ALWAYS show a new genotype to provent convergance & increase user option

        children.clear();
        this.count = this.count+1;
        this.counter.setText("Number of Evolutions: "+Integer.toString(count));
    }

    private void getSelectedParents() {
        if(this.cx1.isSelected()){
            parents.add(getGenotype(0));
        }
        if(this.cx2.isSelected()){
            parents.add(getGenotype(1));
        }
        if(this.cx3.isSelected()){
            parents.add(getGenotype(2));
        }
        if(this.cx4.isSelected()){
            parents.add(getGenotype(3));
        }
    }

    private float[] getGenotype(int i) {
        return geno.get(i);
    }

    private float[] crossoverParents(float[] p1, float[] p2) {
        float[] child = new float[10];
        Random rand = new Random();
        
        int crossoverpoint = rand.nextInt(8)+1;            //single point mutation
        for(int x=0;x<crossoverpoint;x++){
            child[x] = p1[x];
        }
        for(int x=crossoverpoint;x<10;x++){
            child[x] = p2[x];
        }
        return child;
    }

    private float[] mutateChild(float[] child) {
        
        Random rand = new Random();
        
        if(rand.nextInt(100)>70){                      // /30 change of value encoding mutation on a larger scale
            if(rand.nextInt(100)>90){       
                child[0] = checkGeno0(child[0]+rand.nextInt(50)-rand.nextInt(50)); // 1/10 chance genotype 0 will mutate
            };
            if(rand.nextInt(100)>90){       
                child[1] = checkGenoSmall(child[1]+(rand.nextFloat()-rand.nextFloat()/2)) + 0.5f; // 1/10 chance genotype 1 will mutate
            };
            if(rand.nextInt(100)>90){       
                child[2] = checkGenoScale(child[2]+(rand.nextFloat()-rand.nextFloat()/1.5f)); // 1/10 chance genotype 2 will mutate
            };
            if(rand.nextInt(100)>90){       
                child[3] = checkGenoRotate(child[3]+rand.nextInt(120)-rand.nextInt(120)); // 1/10 chance genotype 3 will mutate
            };
            if(rand.nextInt(100)>90){       
                child[4] = checkGenoSmall(child[4]+(rand.nextFloat()-rand.nextFloat()/2));
            };
            if(rand.nextInt(100)>90){       
                child[5] = checkGenoSmall(child[5]+(rand.nextFloat()-rand.nextFloat()/2));
            };
            if(rand.nextInt(100)>90){       
                child[6] = checkGenoSmall(child[6]+(rand.nextFloat()-rand.nextFloat()/2));
            };
            if(rand.nextInt(100)>90){       
                child[7] = checkGenoSmall(child[7]+(rand.nextFloat()-rand.nextFloat()/2));
            };
            if(rand.nextInt(100)>90){       
                child[8] = checkGenoSmall(child[8]+(rand.nextFloat()-rand.nextFloat()/2));
            };
            if(rand.nextInt(100)>90){       
                child[9] = checkGenoSmall(child[9]+(rand.nextFloat()-rand.nextFloat()/2));
            };
        };
        
        if(rand.nextInt(100)>60){                      // 4/10 change of value encoding mutation smaller shift
            if(rand.nextInt(100)>90){       
                child[0] = checkGeno0(child[0]+rand.nextInt(5)-rand.nextInt(5)); // 1/10 chance genotype 0 will mutate
            };
            if(rand.nextInt(100)>90){       
                child[1] = checkGenoSmall(child[1]+(rand.nextFloat()-rand.nextFloat()/3)) + 0.5f; // 1/10 chance genotype 1 will mutate
            };
            if(rand.nextInt(100)>90){       
                child[2] = checkGenoScale(child[2]+(rand.nextFloat()-rand.nextFloat()/3)); // 1/10 chance genotype 2 will mutate
            };
            if(rand.nextInt(100)>90){       
                child[3] = checkGenoRotate(child[3]+rand.nextInt(50)-rand.nextInt(50)); // 1/10 chance genotype 3 will mutate
            };
            if(rand.nextInt(100)>90){       
                child[4] = checkGenoSmall(child[4]+(rand.nextFloat()-rand.nextFloat()/3));
            };
            if(rand.nextInt(100)>90){       
                child[5] = checkGenoSmall(child[5]+(rand.nextFloat()-rand.nextFloat()/3));
            };
            if(rand.nextInt(100)>90){       
                child[6] = checkGenoSmall(child[6]+(rand.nextFloat()-rand.nextFloat()/3));
            };
            if(rand.nextInt(100)>90){       
                child[7] = checkGenoSmall(child[7]+(rand.nextFloat()-rand.nextFloat()/3));
            };
            if(rand.nextInt(100)>90){       
                child[8] = checkGenoSmall(child[8]+(rand.nextFloat()-rand.nextFloat()/3));
            };
            if(rand.nextInt(100)>90){       
                child[9] = checkGenoSmall(child[9]+(rand.nextFloat()-rand.nextFloat()/3));
            };
        };
        return child;
    };
        
    private float checkGeno0(float geno){     //keep new mutated genome within the limit of 20-160
        if (geno < 20) {                      //when mutation reaches below 20 for example 21 to 18 this function
            geno = 20+(20-geno);             //this function buts a reflector on the 20 value so that the next geno0 = 22
        };
        if (geno > 90) {
            geno = 90-(geno-90);
        };
        return geno;
    };
    
    private float checkGenoSmall(float geno){ //KEEP MUTATIONS AT RANGE 0-1
        if (geno < 0) {                      
            geno = 0+(0-geno);             
        };
        if (geno > 1) {
            geno = 1-(geno-1);
        };
        return geno;
    };
    
    private float checkGenoScale(float geno){   //KEEP MUTATIONS AT RANGE 0.5-2
        if (geno < 0.5) {                      
            geno = 0.5f+(0.5f-geno);             
        }
        if (geno > 2) {
            geno = 2-(geno-2);
        };
        return geno;
    };
    
    private float checkGenoRotate(float geno){   //KEEP MUTATIONS AT RANGE 0-359
        if (geno <0) {                      
            geno = 359-geno;             
        };
        if (geno > 359) {
            geno = geno-360;
        };
        return geno;
    };
}
