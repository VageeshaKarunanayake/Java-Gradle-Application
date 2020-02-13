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
public interface RobotControl {
    
    //This method is used to get a RobotInfo variable and assign it to the 'ROBOT' variable
    //Also this method will add the RobotInfo parameter to the ROBOTS ArrayList and count variable is increased by one
    void setRobot(RobotInfo R);
    
    //This method is used to decrease the 'count' variable by one
    void removeRobot();
    
    //This method is used to get the 'count' variable
    int getCount();
    
    //This method is used to get the 'ROBOT' RobotInfo object
    RobotInfo getRobot();
    
    //This method is used to get a array containing all the RobotInfo objects inside the 'ROBOTS' ArrayList
    //Method is synchronized to to avoid race conditions
    RobotInfo[] getAllRobots();
    
    //This method is used to decrease the 'ROBOT' RobotInfo object's y value 
    boolean moveNorth();
    
    //This method is used to increase the 'ROBOT' RobotInfo object's x value
    boolean moveEast();
    
    //This method is used to increase the 'ROBOT' RobotInfo object's y value
    boolean moveSouth();
    
    //This method is used to decrease the 'ROBOT' RobotInfo object's x value
    boolean moveWest();
    
    //This method is used to decrease the health of the robot that got hit
    boolean fire(int x, int y);
}
