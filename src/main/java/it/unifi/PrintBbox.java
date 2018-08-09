package it.unifi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import it.unifi.export.Sample;
import it.unifi.export.SampleMatrix;
import it.unifi.export.SampleMatrixList;


public class PrintBbox {
	
	private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panel1; 
    private JPanel panel2 = new JPanel();
    private SampleMatrix data;
    
    public PrintBbox(String image,SampleMatrix data)
    {
    	this.data = data;
    	panel1 = new MyPanel(image,frame);
        frame.setPreferredSize(new Dimension(600,500));
        frame.setLayout(new BorderLayout());
        frame.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, frame.getHeight(), frame.getHeight());
        panel1.setBounds(0, 0, 1000, 1000);
        panel1.setOpaque(true);
        //panel2.setBounds(200, 100, 100, 100);
        //panel2.setOpaque(false);
        lpane.add(panel1, new Integer(0), 0);
        //lpane.add(panel2, new Integer(1), 0);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // This is your custom panel
    class MyPanel extends JPanel {
        private static final long serialVersionUID = -4559408638276405147L;
        private String imageFile;
        private JFrame jFrame;
        
        public MyPanel(String imageFile, JFrame jFrame) {
            this.imageFile = imageFile;
            this.jFrame = jFrame;
        }
        @Override
        protected void paintComponent(Graphics g) {
            // Add your image here
        	BufferedImage img = null;
        	try {
        	    img = ImageIO.read(new File(imageFile));
        	    
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}
        	Image dimg = img.getScaledInstance(this.jFrame.getWidth(), this.jFrame.getHeight(),Image.SCALE_SMOOTH);
        	//ImageIcon imageIcon = new ImageIcon(dimg);
            double scaledH = Math.abs(img.getHeight() - this.jFrame.getHeight()) / (double)img.getHeight();
            double scaledW = Math.abs(img.getWidth() - this.jFrame.getWidth()) / (double)img.getWidth();
            //Image scaledImage = img.getScaledInstance(1000,1000,Image.SCALE_SMOOTH);
            g.drawImage(dimg, 0, 0, null);
            //calcolo il fattore di scala
            
            for(int i=0;i<data.getSample().length;i++){
            	Sample sample = data.getSample()[i];
            	Color color = genarateRandomColor();
            	g.setColor(color);
            	for(int j=0;j<sample.getValueObject().length;j++){
            		int x = (int) sample.getXaxis();
            		int y = (int) sample.getValuePoint()[j].getY();
            		
            		x = x + (int)(scaledW * x);
            		y = y + (int)(scaledH * y);
            		//g.drawOval(x, y, 3, 3);
            		g.fillOval(x-2, y-2, 5, 5);
                	g.drawRect(
                			(int)sample.getBoundingBox()[j].getX() + (int)(sample.getBoundingBox()[j].getX()*scaledW),
                			(int)sample.getBoundingBox()[j].getY() + (int)(sample.getBoundingBox()[j].getY()*scaledH), 
                			(int)sample.getBoundingBox()[j].getW() + (int)(sample.getBoundingBox()[j].getW()*scaledW), 
                			(int)sample.getBoundingBox()[j].getH() + (int)(sample.getBoundingBox()[j].getH()*scaledH));
                	//g.drawString(sample.getValueObject()[j]+"", (int)sample.getBoundingBox()[j].getX(), (int)sample.getBoundingBox()[j].getY());
            	}
            	g.drawRect(
            			(int)sample.getVerticalWindow().getX() + (int)(sample.getVerticalWindow().getX()*scaledW), 
            			(int)sample.getVerticalWindow().getY() + (int)(sample.getVerticalWindow().getY()*scaledH),
            			(int)sample.getVerticalWindow().getW() + (int)(sample.getVerticalWindow().getW()*scaledW), 
            			(int)sample.getVerticalWindow().getH() + (int)(sample.getVerticalWindow().getH()*scaledH));
            }
        }
    }
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		int line = 0,index = 0;
		if(args.length == 2){
			index = Integer.parseInt(args[0]);
			line =  Integer.parseInt(args[1]);
		}else{
			printUsage();
			System.exit(0);
		
		}
		
		String filename = "Serialize"+line+"Line.json";
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(filename));
		SampleMatrixList data = gson.fromJson(reader, SampleMatrixList.class); // contains the whole reviews list
        new PrintBbox(index+".png",data.getSampleByIndex(index));
	}

	private static void printUsage() {
		System.out.println("inserire indice e numero di rette del grafico");
		
	}
	
	private static Color genarateRandomColor(){
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		return new Color(r, g, b);
	}

}
