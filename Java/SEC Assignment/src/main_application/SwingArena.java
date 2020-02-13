package main_application;

import java.awt.*;
import javax.swing.*;

/**
 * A Swing GUI element that displays a grid on which you can draw images, text and lines.
 */
public class SwingArena extends JPanel
{
    // Represents the image to draw. You can modify this to introduce multiple images.
    private static final String IMAGE_FILE = "1554047213.png";
    private ImageIcon robot1;
    private ImageIcon robot2;
    private ImageIcon robot3;
    
    // The following values are arbitrary, and you may need to modify them according to the 
    // requirements of your application.
    private int gridWidth = 12;
    private int gridHeight = 8;
    
    // 1st Robot modifiable variables
    private String robot1Name;
    private double robot1X = 0;
    private double robot1Y = 0;
    private double robot1Health;
    private double R1shotX = 0;
    private double R1shotY = 0;
    
    // 2nd Robot modifiable variables
    private String robot2Name;
    private double robot2X = 6;
    private double robot2Y = 7;
    private double robot2Health;
    private double R2shotX = 6;
    private double R2shotY = 7;
    
    // 3rd Robot modifiable variables
    private String robot3Name;
    private double robot3X = 11;
    private double robot3Y = 0;
    private double robot3Health;
    private double R3shotX = 11;
    private double R3shotY = 0;
    
    private double gridSquareSize; // Auto-calculated
    

    /**
     * Creates a new arena object, loading the robot image.
     */
    public SwingArena()
    {
        // Here's how you get an Image object from an image file (which you provide in the 
        // 'resources/' directory.
        robot1 = new ImageIcon(getClass().getClassLoader().getResource(IMAGE_FILE));
        robot2 = new ImageIcon(getClass().getClassLoader().getResource(IMAGE_FILE));
        robot3 = new ImageIcon(getClass().getClassLoader().getResource(IMAGE_FILE));
        // You will get an exception here if the specified image file cannot be found.
    }
    
    //Method is used to set the name of the 1st robot
    public void setRobot1Name(String s)
    {
        robot1Name = s;
        repaint();
    }
    
    //Method is used to set the health of the 1st robot
    public void setRobot1Health(double d)
    {
        robot1Health = d;
        repaint();
    }
    
    //Method is used to set the position of the 1st robot
    public void setRobot1Position(double x, double y)
    {
        robot1X = x;
        robot1Y = y;
        R1shotX = robot1X;
        R1shotY = robot1Y;
        repaint();
    }
    
    //Method is used to draw the fire line of the 1st robot
    public void robot1Shot(int xx, int yy) throws InterruptedException
    {
            R1shotX = robot1X + (xx - robot1X);
            R1shotY = robot1Y + (yy - robot1Y);
            repaint();
            Thread.sleep(250);
            R1shotX = robot1X;
            R1shotY = robot1Y;
            repaint();
    }
    
    //Method is used to set the name of the 2nd robot
    public void setRobot2Name(String s)
    {
        robot2Name = s;
        repaint();
    }
    
    //Method is used to set the health of the 2nd robot
    public void setRobot2Health(double d)
    {
        robot2Health = d;
        repaint();
    }
    
    //Method is used to set the position of the 2nd robot
    public void setRobot2Position(double x, double y)
    {
        robot2X = x;
        robot2Y = y;
        R2shotX = robot2X;
        R2shotY = robot2Y;
        repaint();
    }
    
    //Method is used to draw the fire line of the 2nd robot
    public void robot2Shot(int xx, int yy) throws InterruptedException
    {
            R2shotX = robot2X + (xx - robot2X);
            R2shotY = robot2Y + (yy - robot2Y);
            repaint();
            Thread.sleep(250);
            R2shotX = robot2X;
            R2shotY = robot2Y;
            repaint();
    }
    
    //Method is used to set the name of the 3rd robot
    public void setRobot3Name(String s)
    {
        robot3Name = s;
        repaint();
    }
    
    //Method is used to set the health of the 3rd robot
    public void setRobot3Health(double d)
    {
        robot3Health = d;
        repaint();
    }
    
    //Method is used to set the position of the 3rd robot
    public void setRobot3Position(double x, double y)
    {
        robot3X = x;
        robot3Y = y;
        R3shotX = robot3X;
        R3shotY = robot3Y;
        repaint();
    }
    
    //Method is used to draw the fire line of the 3rd robot
    public void robot3Shot(int xx, int yy) throws InterruptedException
    {
            R3shotX = robot3X + (xx - robot3X);
            R3shotY = robot3Y + (yy - robot3Y);
            repaint();
            Thread.sleep(250);
            R3shotX = robot3X;
            R3shotY = robot3Y;
            repaint();
    }
    
    /**
     * This method is called in order to redraw the screen, either because the user is manipulating 
     * the window, OR because you've called 'repaint()'.
     *
     * You will need to modify the last part of this method; specifically the sequence of calls to
     * the other 'draw...()' methods. You shouldn't need to modify anything else about it.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gfx = (Graphics2D) g;
        gfx.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                             RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        // First, calculate how big each grid cell should be, in pixels. (We do need to do this
        // every time we repaint the arena, because the size can change.)
        gridSquareSize = Math.min(
            (double) getWidth() / (double) gridWidth,
            (double) getHeight() / (double) gridHeight);
            
        int arenaPixelWidth = (int) ((double) gridWidth * gridSquareSize);
        int arenaPixelHeight = (int) ((double) gridHeight * gridSquareSize);
            
            
        // Draw the arena grid lines. This may help for debugging purposes, and just generally
        // to see what's going on.
        gfx.setColor(Color.GRAY);
        gfx.drawRect(0, 0, arenaPixelWidth - 1, arenaPixelHeight - 1); // Outer edge

        for(int gridX = 1; gridX < gridWidth; gridX++) // Internal vertical grid lines
        {
            int x = (int) ((double) gridX * gridSquareSize);
            gfx.drawLine(x, 0, x, arenaPixelHeight);
        }
        
        for(int gridY = 1; gridY < gridHeight; gridY++) // Internal horizontal grid lines
        {
            int y = (int) ((double) gridY * gridSquareSize);
            gfx.drawLine(0, y, arenaPixelWidth, y);
        }

        
        // Invoke helper methods to draw things at the current location.
        // ** You will need to adapt this to the requirements of your application. **
        drawImage(gfx, robot1, robot1X, robot1Y);
        drawLabel(gfx, robot1Name+" "+robot1Health, robot1X, robot1Y);
        drawLine(gfx, robot1X, robot1Y, R1shotX, R1shotY);
        
        drawImage(gfx, robot2, robot2X, robot2Y);
        drawLabel(gfx, robot2Name+" "+robot2Health, robot2X, robot2Y);
        drawLine(gfx, robot2X, robot2Y, R2shotX, R2shotY);
        
        drawImage(gfx, robot3, robot3X, robot3Y);
        drawLabel(gfx, robot3Name+" "+robot3Health, robot3X, robot3Y);
        drawLine(gfx, robot3X, robot3Y, R3shotX, R3shotY);
    }
    
    
    /** 
     * Draw an image in a specific grid location. *Only* call this from within paintComponent(). 
     *
     * Note that the grid location can be fractional, so that (for instance), you can draw an image 
     * at location (3.5,4), and it will appear on the boundary between grid cells (3,4) and (4,4).
     *     
     * You shouldn't need to modify this method.
     */
    private void drawImage(Graphics2D gfx, ImageIcon icon, double gridX, double gridY)
    {
        // Get the pixel coordinates representing the centre of where the image is to be drawn. 
        double x = (gridX + 0.5) * gridSquareSize;
        double y = (gridY + 0.5) * gridSquareSize;
        
        // We also need to know how "big" to make the image. The image file has a natural width 
        // and height, but that's not necessarily the size we want to draw it on the screen. We 
        // do, however, want to preserve its aspect ratio.
        double fullSizePixelWidth = (double) robot1.getIconWidth();
        double fullSizePixelHeight = (double) robot1.getIconHeight();
        
        double displayedPixelWidth, displayedPixelHeight;
        if(fullSizePixelWidth > fullSizePixelHeight)
        {
            // Here, the image is wider than it is high, so we'll display it such that it's as 
            // wide as a full grid cell, and the height will be set to preserve the aspect 
            // ratio.
            displayedPixelWidth = gridSquareSize;
            displayedPixelHeight = gridSquareSize * fullSizePixelHeight / fullSizePixelWidth;
        }
        else
        {
            // Otherwise, it's the other way around -- full height, and width is set to 
            // preserve the aspect ratio.
            displayedPixelHeight = gridSquareSize;
            displayedPixelWidth = gridSquareSize * fullSizePixelWidth / fullSizePixelHeight;
        }

        // Actually put the image on the screen.
        gfx.drawImage(icon.getImage(), 
            (int) (x - displayedPixelWidth / 2.0),  // Top-left pixel coordinates.
            (int) (y - displayedPixelHeight / 2.0), 
            (int) displayedPixelWidth,              // Size of displayed image.
            (int) displayedPixelHeight, 
            null);
    }
    
    
    /**
     * Displays a string of text underneath a specific grid location. *Only* call this from within 
     * paintComponent(). 
     *
     * You shouldn't need to modify this method.
     */
    private void drawLabel(Graphics2D gfx, String label, double gridX, double gridY)
    {
        gfx.setColor(Color.BLUE);
        FontMetrics fm = gfx.getFontMetrics();
        gfx.drawString(label, 
            (int) ((gridX + 0.5) * gridSquareSize - (double) fm.stringWidth(label) / 2.0), 
            (int) ((gridY + 1.0) * gridSquareSize) + fm.getHeight());
    }
    
    /** 
     * Draws a (slightly clipped) line between two grid coordinates. 
     *
     * You shouldn't need to modify this method.
     */
    private void drawLine(Graphics2D gfx, double gridX1, double gridY1, 
                                          double gridX2, double gridY2)
    {
        gfx.setColor(Color.RED);
        
        // Recalculate the starting coordinate to be one unit closer to the destination, so that it
        // doesn't overlap with any image appearing in the starting grid cell.
        final double radius = 0;
        double angle = Math.atan2(gridY2 - gridY1, gridX2 - gridX1);
        double clippedGridX1 = gridX1 + Math.cos(angle) * radius;
        double clippedGridY1 = gridY1 + Math.sin(angle) * radius;
        
        gfx.drawLine((int) ((clippedGridX1 + 0.5) * gridSquareSize), 
                     (int) ((clippedGridY1 + 0.5) * gridSquareSize), 
                     (int) ((gridX2 + 0.5) * gridSquareSize), 
                     (int) ((gridY2 + 0.5) * gridSquareSize));
    }
}
