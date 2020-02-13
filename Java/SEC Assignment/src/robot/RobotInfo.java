/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

/**
 *
 * @author Vageesha
 */
public interface RobotInfo {
    
    //This method is used to set the 'name' variable
    void setName(String name);
    
    //This method is used to set the 'x' variable
    void setX(int x);
    
    //This method is used to set the 'y' variable
    void setY(int y);
    
    //This method is used to set the 'health' variable
    void setHealth(double health);
    
    //This method is used to get the 'name' variable 
    String getName();
    
    //This method is used to get the 'x' variable
    int getX();
    
    //This method is used to get the 'y' variable
    int getY();
    
    //This method is used to get the 'health' variable
    double getHealth();
    
    //This method is used to add the health to the 'healthNotifications' ArrayBlockingQueue
    void addQueue(double d);
    
    //This method is used to remove a health value and get that health value from the 'healthNotifications' ArrayBlockingQueue
    double removeQueue();
    
    //This method is used to check whether the 'healthNotifications' ArrayBlockingQueue is empty
    boolean isEmptyQueue();
    
    //This method is used to get the value of 'dead' variable
    boolean getDead();
    
    //This method is used to set the value of 'dead' variable to true
    void setDead();
}
