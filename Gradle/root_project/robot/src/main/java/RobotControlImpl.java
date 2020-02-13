
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vageesha
 */
public class RobotControlImpl implements RobotControl{
    
    //Declaration of ROBOT, count, ArrayList named 'ROBOTS', and mutex variables
    //'count' variable is initialized to 0
    private RobotInfo ROBOT;
    private static int count = 0;
    private static ArrayList<RobotInfo> ROBOTS = new ArrayList<>();
    private final static Object mutex = new Object();
    private final static Object mutex2 = new Object();
    
    //This method is used to get a RobotInfo variable and assign it to the 'ROBOT' variable
    //Also this method will add the RobotInfo parameter to the ROBOTS ArrayList and count variable is increased by one
    //All the shared variables are synchronized to avoid race conditions
    @Override
    public void setRobot(RobotInfo R)
    {
        ROBOT = R;
        synchronized(mutex)
        {
                ROBOTS.add(R);
                
        }
        synchronized(mutex2)
        {
            count++;
        }
    }
    
    //This method is used to decrease the 'count' variable by one
    //Method is synchronized to to avoid race conditions
    @Override
    public void removeRobot()
    {
        synchronized(mutex2)
        {
            count--;
        }
    }
    
    //This method is used to get the 'count' variable
    //Method is synchronized to to avoid race conditions
    @Override
    public int getCount()
    {
        synchronized(mutex2)
        {
            return count;
        }
    }
    
    //This method is used to get the 'ROBOT' RobotInfo object
    @Override
    public RobotInfo getRobot()
    {
            return ROBOT;
    }
    
    //This method is used to get a array containing all the RobotInfo objects inside the 'ROBOTS' ArrayList
    //Method is synchronized to to avoid race conditions
    @Override
    public RobotInfo[] getAllRobots()
    {
        synchronized(mutex)
        {
            RobotInfo[] AllRobots = ROBOTS.toArray(new RobotInfo[ROBOTS.size()]);
            return AllRobots;
        }
    }
    
    //This method is used to decrease the 'ROBOT' RobotInfo object's y value 
    @Override
    public boolean moveNorth()
    {
        //This condition is used to avoid robot moving out of arena
        //If condition is true return false because robot's y value did not changed 
        if(ROBOT.getY() <= 0)
        {
            return false;
        }
        else
        {   
            for(RobotInfo robot : getAllRobots())
            {
                //Check whether the square that robot is moving already has a robot
                //If condition is true return false because robot's y value did not changed 
                if((robot.getX() == ROBOT.getX()) && (robot.getY() == (ROBOT.getY()-1)))
                {
                    return false;
                }
            }
            //If robot is not moving out of the arena and the square does not have a robot
            //then decrease the 'ROBOT' RobotInfo object's y value by one  
            ROBOT.setY(ROBOT.getY() - 1);
            return true;
        }
    }
    
    //This method is used to increase the 'ROBOT' RobotInfo object's x value
    @Override
    public boolean moveEast()
    {
        //This condition is used to avoid robot moving out of arena
        //If condition is true return false because robot's x value did not changed 
        if(ROBOT.getX() >= 11)
        {
            return false;
        }
        else
        {
            for(RobotInfo robot : getAllRobots())
            {
                //Check whether the square that robot is moving already has a robot
                //If condition is true return false because robot's x value did not changed
                if((robot.getX() == (ROBOT.getX()+1)) && (robot.getY() == ROBOT.getY()))
                {
                    return false;
                }
            }
            //If robot is not moving out of the arena and the square does not have a robot
            //then increase the 'ROBOT' RobotInfo object's x value by one
            ROBOT.setX(ROBOT.getX() + 1);
            return true;
        }
    }
    
    //This method is used to increase the 'ROBOT' RobotInfo object's y value
    @Override
    public boolean moveSouth()
    {
        //This condition is used to avoid robot moving out of arena
        //If condition is true return false because robot's y value did not changed 
        if(ROBOT.getY() >= 7)
        {
            return false;
        }
        else
        {
            for(RobotInfo robot : getAllRobots())
            {
                //Check whether the square that robot is moving already has a robot
                //If condition is true return false because robot's y value did not changed 
                if((robot.getX() == ROBOT.getX()) && (robot.getY() == (ROBOT.getY()+1)))
                {
                    return false;
                }
            }
            //If robot is not moving out of the arena and the square does not have a robot
            //then increase the 'ROBOT' RobotInfo object's y value by one
            ROBOT.setY(ROBOT.getY() + 1);
            return true;
        }
    }
    
    //This method is used to decrease the 'ROBOT' RobotInfo object's x value
    @Override
    public boolean moveWest()
    {
        //This condition is used to avoid robot moving out of arena
        //If condition is true return false because robot's x value did not changed 
        if(ROBOT.getX() <= 0)
        {
            return false;
        }
        else{
            for(RobotInfo robot : getAllRobots())
            {
                //Check whether the square that robot is moving already has a robot
                //If condition is true return false because robot's x value did not changed
                if((robot.getX() == (ROBOT.getX()-1)) && (robot.getY() == ROBOT.getY()))
                {
                    return false;
                }
            }
            //If robot is not moving out of the arena and the square does not have a robot
            //then decrease the 'ROBOT' RobotInfo object's x value by one
            ROBOT.setX(ROBOT.getX() - 1);
            return true;
        }
    }
    
    //This method is used to decrease the health of the robot that got hit
    @Override
    public boolean fire(int x, int y)
    {
        //Declare a RobotInfo object called 'damagedRobot'
        RobotInfo damagedRobot = null;
        
            for(RobotInfo robot : getAllRobots())
            {
                //Check whether a robot is positioned in the given x and y coordinates
                //If robot is positioned (condition true), then reduce the robot's health by 35
                if((x == robot.getX()) && (y == robot.getY()))
                {
                        damagedRobot = robot;
                        
                        //If robot's health - 35 is greater than 0 then reduce health by 35
                        //Else set the robot's health to 0
                        if((damagedRobot.getHealth() - 35) >= 0)
                        {
                            //add the new health to the queue of the robot that got hit and return true
                            damagedRobot.addQueue(damagedRobot.getHealth() - 35);
                            return true;
                        }
                        else
                        {
                            //add the new health(value 0.0) to the queue of the robot that got hit and return true 
                            damagedRobot.addQueue(0.0);
                            return true;
                        }
                    
                    /* This is a implementation that does not require a blocking queue to update the robot's health
                    
                    damagedRobot = robot;
                    if((damagedRobot.health - 35) >= 0)
                    {
                        damagedRobot.health = damagedRobot.health - 35;
                        return true;
                    }
                    else
                    {
                        damagedRobot.health = 0;
                        return true;
                    }
                    
                    */
                }
            }
            //If a robot is not found in the given coordinates, then return false
            return false;
    }
    
}
