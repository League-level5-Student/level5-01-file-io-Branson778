package _06_Pixel_Art_Save_State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

public class GridPanel extends JPanel implements Serializable{

    private static final long serialVersionUID = 1L;
    int windowWidth;
    int windowHeight;
    private int pixelWidth;
    private int pixelHeight;
    int rows;
    int cols;

    // 1. Create a 2D array of pixels. Do not initialize it yet.
Pixel[][] pixelss;
    private Color color;

    public GridPanel(int w, int h, int r, int c) {
        this.windowWidth = w;
        this.windowHeight = h;
        this.rows = r;
        this.cols = c;

        this.pixelWidth = windowWidth / cols;
        this.pixelHeight = windowHeight / rows;

        color = Color.WHITE;

        setPreferredSize(new Dimension(windowWidth, windowHeight));

        // 2. Initialize the pixel array using the rows and cols variables.
 pixelss = new Pixel[rows][cols];

        // 3. Iterate through the array and initialize each element to a new pixel.
 for (int i = 0; i < pixelss.length; i++) {
	for (int j = 0; j < pixelss[i].length; j++) {
		pixelss[i][j] = new Pixel(i,j);
	}
}

    }

    public void setColor(Color c) {
        color = c;
    }

    public void clickPixel(int mouseX, int mouseY) {
        // 5. Use the mouseX and mouseY variables to change the color
        //    of the pixel that was clicked. *HINT* Use the pixel's dimensions.
int ro = mouseX/pixelWidth;
int co = mouseY/pixelHeight;
pixelss[ro][co].color = color;
    }

    @Override
    public void paintComponent(Graphics g) {
        // 4. Iterate through the array.
        //    For every pixel in the list, fill in a rectangle using the pixel's color.
        //    Then, use drawRect to add a grid pattern to your display.
    	 for (int i = 0; i < pixelss.length; i++) {
    			for (int j = 0; j < pixelss[i].length; j++) {
    				g.setColor(pixelss[i][j].color);
               g.fillRect(pixelss[i][j].x*pixelWidth,pixelss[i][j].y*pixelHeight,pixelWidth,pixelHeight);
               g.setColor(Color.BLACK);
               g.drawRect(pixelss[i][j].x*pixelWidth,pixelss[i][j].y*pixelHeight,pixelWidth,pixelHeight);
    			}
    		}
    }
}
