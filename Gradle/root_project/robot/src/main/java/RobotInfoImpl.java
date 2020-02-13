
//Java imports
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vageesha
 */
public class RobotInfoImpl implements RobotInfo{
    
    //All the variable declaration
    private String name;
    //Initialize 'dead' variable to false
    private boolean dead = false;
    private int x;
    private int y;
    private double health;
    
    //Declare and initialize the 'healthNotifications' ArrayBlockingQueue with size = 3
    //size = 3 should be enough because the robot can get hit only upto 3 times (health = 100 / 35) 
    private ArrayBlockingQueue<Double> healthNotifications = new ArrayBlockingQueue<>(3);
    
    //This method is used to set the 'name' variable
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
    
    //This method is used to set the 'x' variable
    @Override
    public void setX(int x)
    {
        this.x = x;
    }
    
    //This method is used to set the 'y' variable
    @Override
    public void setY(int y)
    {
        this.y = y;
    }
    
    //This method is used to set the 'health' variable
    @Override
    public void setHealth(double health)
    {
        this.health = health;
    }
    
    //This method is used to get the 'name' variable 
    @Override
    public String getName()
    {
        return name;
    }
    
    //This method is used to get the 'x' variable 
    @Override
    public int getX()
    {
        return x;
    }
    
    //This method is used to get the 'y' variable
    @Override
    public int getY()
    {
        return y;
    }
    
    //This method is used to get the 'health' variable
    @Override
    public double getHealth()
    {
        return health;
    }
    
    //This method is used to add the health to the 'healthNotifications' ArrayBlockingQueue
    //try catch block is used to catch the exceptions
    @Override
    public void addQueue(double d)
    {
        try {
            healthNotifications.put(d);
        } catch (InterruptedException ex) {
            Logger.getLogger(RobotInfoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //This method is used to remove a health value and get that health value from the 'healthNotifications' ArrayBlockingQueue
    //try catch block is used to catch the exceptions
    @Override
    public double removeQueue()
    {
        try {
            return healthNotifications.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(RobotInfoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    //This method is used to check whether the 'healthNotifications' ArrayBlockingQueue is empty
    @Override
    public boolean isEmptyQueue()
    {
        return healthNotifications.isEmpty();
    }
    
    //This method is used to get the value of 'dead' variable
    @Override
    public boolean getDead()
    {
        return dead;
    }
    
    //This method is used to set the value of 'dead' variable to true
    @Override
    public void setDead()
    {
        dead = true;
    }
}
