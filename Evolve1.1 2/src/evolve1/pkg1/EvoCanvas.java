package evolve1.pkg1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author joechan
 */
public class EvoCanvas extends Canvas{
    public static final int CANVAS_SIZE = 200;
    protected int[] xPoints;
    protected int[] yPoints;   
    protected float canvasRotation;
    protected float[] shapeColor1;
    protected float[] shapeColor2;
    protected int xLength;
    protected int xDifference;
    protected int yHeight;
    
    public EvoCanvas(){
        super();
        setSize(CANVAS_SIZE,CANVAS_SIZE);
        setBackground(Color.BLUE);
        setVisible(true);
        setFocusable(false);
        this.xPoints = new int[6];
        this.yPoints = new int[6];
        this.shapeColor1 = new float[3];
        this.shapeColor2 = new float[3];
        this.xLength = 70;
    };
    
    public void drawGenotype(float[] genotype){
        
        repaint();
        
        float parallelAngle = (float)Math.toRadians(genotype[0]);
        float xLength = (float) 70.0; 
        float yLength = genotype[1]*xLength;
        float scaling = genotype[2];
        
        float xFixing = -170;                                   //starting co-ordinates
        float yFixing = 450; 
        
        float xCoord = (float) Math.abs(cos(parallelAngle)*yLength);     //finds our first point ine
        float yCoord = (float) Math.abs(sin(parallelAngle)*yLength);

        
        this.xPoints[0] = (int)(scaling*xFixing);
        this.xPoints[1] = (int)(scaling*xFixing+xLength);
        this.xPoints[2] = (int)(scaling*xFixing+xLength)+(int)(xLength/2);
        this.xPoints[3] = (int)(scaling*xFixing+xLength)+(int)(xCoord);
        this.xPoints[4] = (int)(scaling*xFixing)+(int)(xCoord);
        this.xPoints[5] = (int)(scaling*xFixing)+(int)(xLength/2);
        
        this.yPoints[0] = (int)(scaling*yFixing);
        this.yPoints[1] = (int)(scaling*yFixing);
        this.yPoints[2] = (int)(scaling*yFixing)-(int)(yCoord/2);
        this.yPoints[3] = (int)(scaling*yFixing)-(int)(yCoord);
        this.yPoints[4] = (int)(scaling*yFixing)-(int)(yCoord);
        this.yPoints[5] = (int)(scaling*yFixing)-(int)(yCoord/2);

        this.xDifference = this.xPoints[3]-this.xPoints[0];
        this.yHeight = this.yPoints[3]-this.yPoints[0];             //shape should stack perfectly with these space fixings

        
        canvasRotation = genotype[3];

        shapeColor1[0] = genotype[4];        
        shapeColor1[1] = genotype[5];
        shapeColor1[2] = genotype[6]; 
        
        shapeColor2[0] = genotype[7];        
        shapeColor2[1] = genotype[8];
        shapeColor2[2] = genotype[9]; 

    };
    
    
    public void paint(Graphics g){


        Color c = Color.getHSBColor(shapeColor1[0],shapeColor1[1],shapeColor1[2]);
        Color d = Color.getHSBColor(shapeColor2[0],shapeColor2[1],shapeColor2[2]);
        Color e = Color.BLACK;
        
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(canvasRotation),160, -240);       //rotation of plane by genotype[3]
        
        for (int j = 0; j < 60; j++){
            
            e=c;        //invert colours with every row
            c=d;
            d=e;
            
            for (int l = 0; l < 12+j; l++){
            

                g.setColor(c);
                g.fillPolygon(this.xPoints,this.yPoints, this.xPoints.length);

                for (int i = 0; i < this.xPoints.length; i++) {
                    this.xPoints[i] = this.xPoints[i]+70;
                };

                g.setColor(d);
                g.fillPolygon(this.xPoints,this.yPoints, this.xPoints.length);

                for (int i = 0; i < this.xPoints.length; i++) {
                    this.xPoints[i] = this.xPoints[i]+70;
                };
            }
            
            for (int i = 0; i < this.xPoints.length; i++) {
                this.xPoints[i] = this.xPoints[i]-1890-(140*j)+this.xDifference;
            };
            
            for (int k = 0; k < this.yPoints.length; k++) {
                this.yPoints[k] = this.yPoints[k]+this.yHeight;
            };

        };
    };
}

