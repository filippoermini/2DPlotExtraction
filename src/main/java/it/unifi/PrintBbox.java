package it.unifi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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
    	panel1 = new MyPanel(image);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setLayout(new BorderLayout());
        frame.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 600, 600);
        panel1.setBounds(0, 0, 600, 600);
        panel1.setOpaque(true);
        panel2.setBounds(200, 100, 100, 100);
        panel2.setOpaque(false);
        lpane.add(panel1, new Integer(0), 0);
        lpane.add(panel2, new Integer(1), 0);
        frame.pack();
        frame.setVisible(true);
    }

    // This is your custom panel
    class MyPanel extends JPanel {
        private static final long serialVersionUID = -4559408638276405147L;
        private String imageFile;

        public MyPanel(String imageFile) {
            this.imageFile = imageFile;
        }
        @Override
        protected void paintComponent(Graphics g) {
            // Add your image here
            Image img = new ImageIcon(imageFile).getImage();
            g.drawImage(img, 0, 0, this);

            for(int i=0;i<data.getSample().length;i++){
            	Sample sample = data.getSample()[i];
            	Color color = genarateRandomColor();
            	g.setColor(color);
            	for(int j=0;j<sample.getValueObject().length;j++){
            		int x = (int) sample.getXaxis();
            		int y = (int) sample.getValuePoint()[j].getY();
            		//g.drawOval(x, y, 3, 3);
            		g.fillOval(x-2, y-2, 5, 5);
                	g.drawRect((int)sample.getBoundingBox()[j].getX(), (int)sample.getBoundingBox()[j].getY(), (int)sample.getBoundingBox()[j].getW(), (int)sample.getBoundingBox()[j].getH());
                	g.drawString(sample.getValueObject()[j]+"", (int)sample.getBoundingBox()[j].getX(), (int)sample.getBoundingBox()[j].getY());
            	}
            	g.drawRect((int)sample.getVerticalWindow().getX(), (int)sample.getVerticalWindow().getY(), (int)sample.getVerticalWindow().getW(), (int)sample.getVerticalWindow().getH());
            	
            }
            //Add your lines here
            //g.setColor(Color.black);
            //g.drawLine(0, 0, g.getClipBounds().width, g.getClipBounds().height);
            //g.setColor(Color.red);
            //g.drawLine(0, g.getClipBounds().height, g.getClipBounds().width, 0);
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
