package _06_Pixel_Art_Save_State;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.Timer;

import _05_Serialization.MinecraftCreeper;

public class PixelArtMaker implements MouseListener, ActionListener{
    private JFrame window;
    private GridInputPanel gip;
    private GridPanel gp;
    public static boolean saveTic = false;
    ColorSelectionPanel csp;
    private static final String PICTURE_LOCATION = "src/_06_Pixel_Art_Save_State/picture.dat";
    private static final String SETTINGS_LOCATION = "src/_06_Pixel_Art_Save_State/settings.dat";
    private Timer tic = new Timer(1000/60, this);
    

    public void start() {
        gip = new GridInputPanel(this);	
        window = new JFrame("Pixel Art");
        window.setLayout(new FlowLayout());
        window.setResizable(false);
        
        tic.start();

        window.add(gip);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void submitGridData(int w, int h, int r, int c) {
       if(gp==null) {
    	gp = new GridPanel(w, h, r, c);
       }
        csp = new ColorSelectionPanel();
        window.remove(gip);
        window.add(gp);
        window.add(csp);
        gp.repaint();
        gp.addMouseListener(this);
        window.pack();
    }

    public static void main(String[] args) {
       PixelArtMaker runner = new PixelArtMaker();
       runner.start();
        runner.gp=load1();
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gp.setColor(csp.getSelectedColor());
        System.out.println(csp.getSelectedColor());
        gp.clickPixel(e.getX(), e.getY());
        gp.repaint();
        //-------------------
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    private static void saveDrawing(GridPanel gp) {
    	try(FileOutputStream fos = new FileOutputStream(new File(PICTURE_LOCATION)); ObjectOutputStream oos = new ObjectOutputStream(fos)){
    		oos.writeObject(gp.pixelss);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	try(FileOutputStream fos2 = new FileOutputStream(new File(SETTINGS_LOCATION)); ObjectOutputStream oos2 = new ObjectOutputStream(fos2)){
    		oos2.writeObject(new GridPanel(gp.windowWidth,gp.windowHeight,gp.rows,gp.cols));
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private static GridPanel load1() {
    	GridPanel temp;
    	Pixel[][] temp2;
    	temp = null; 
    	temp2 = null; 
    	try (FileInputStream fis = new FileInputStream(new File(SETTINGS_LOCATION)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			temp = (GridPanel) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			
		}
		//-------------------------------
		try (FileInputStream fis = new FileInputStream(new File(PICTURE_LOCATION)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			temp2 = (Pixel[][]) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
		}
		//----------------------------------------------------------
		System.out.println(temp);
		System.out.println(temp2);
		temp.pixelss = temp2;
		return temp;
    }
  /*  private static GridInputPanel load2(PixelArtMaker run) {
		try (FileInputStream fis = new FileInputStream(new File(SETTINGS_LOCATION)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (GridInputPanel) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			run.start();
			return null;
		} catch (ClassNotFoundException e) {
			run.start();
		}
		return null;
    }
*/
	@Override
	public void actionPerformed(ActionEvent e) {
    	//System.out.println(saveTic);
    	if(saveTic==true) {
    		saveDrawing(gp);
    		saveTic=false;
    	}
		
	}
}
