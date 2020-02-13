
//Java Imports
import static java.lang.Math.abs;
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
public class AIImplementationC implements RobotAI {

    @Override
    public void runAI(RobotControl rc, GUIManage GM) {
        
        //Declare a string variable called 'direction' and initialized the variable with 'west' value
        String direction = "west";
        
        //Declare a RobotInfo variable called 'myRobot' to store a RoboInfo object and declaration of 'shot' boolean variable
        RobotInfo myRobot;
        boolean shot;
        
        //Try catch block is used to catch exceptions
        try
        {
            //Infinite loop
            while (true)
            {
                //Get the RobotInfo object from Robot Control object and store it in 'myRobot' variable
                myRobot = rc.getRobot();
                
                //Check whether the robot is dead
                //If true, breaks from the infinite loop
                if(myRobot.getDead())
                {
                   break; 
                }
                
                //Check whether the RobotInfo object's ArrayBlockingQueue is empty 
                if(!myRobot.isEmptyQueue())
                {
                    //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                    myRobot.setHealth(myRobot.removeQueue());
                    //Below method will refresh the GUI and display the accurate position and health
                    GM.refresh();
                }
                
                //This is used to check whether the robot's health is equal to 0
                //If true, reduce the count variable value by calling 'removeRobot' method
                //'setDead' method will set the 'dead' variable of robot object to true
                //At last, break from infinite loop
                if(myRobot.getHealth() == 0)
                {
                    rc.removeRobot();
                    myRobot.setDead();
                    //Display that robot has died with 'myRobot' object's name
                    GM.writeLog(myRobot.getName()+" is Dead\n");
                    
                    //If count variable of Robot Control Object equals to 0, 
                    //then display that the game is over
                    //At the end break from infinite loop
                    if(rc.getCount() == 0)
                    {
                        GM.writeLog("Game over\n");
                    }
                    break;   
                }
                
                //If count variable of Robot Control Object equals to 1, 
                //then display the winner robot's name and that the game is over
                //At the end break from infinite loop
                if(rc.getCount() == 1)
                {
                    GM.writeLog(myRobot.getName()+" is the winner\n");
                    GM.writeLog("Game over\n");
                    break;
                }
                
                //For each loop
                for (RobotInfo ROBOT : rc.getAllRobots())
                {
                    //Check whether the RobotInfo object's ArrayBlockingQueue is empty
                    if(!myRobot.isEmptyQueue())
                    {
                        //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                        myRobot.setHealth(myRobot.removeQueue());
                        //Below method will refresh the GUI and display the accurate position and health
                        GM.refresh();
                    }
                    
                    //If 'myRobot' object's name does not equals to 'ROBOT' object's name and
                    //absolute value of 'myRobot' object's x - 'ROBOT' object's x is less or equal to 2 and
                    //absolute value of 'myRobot' object's y - 'ROBOT' object's y is less or equal to 2 and
                    //'ROBOT' object's health is not equal to 0
                    if ((!ROBOT.getName().equals(myRobot.getName())) && (abs(myRobot.getX() - ROBOT.getX()) <= 2) && (abs(myRobot.getY() - ROBOT.getY()) <= 2) && (ROBOT.getHealth() != 0))
                    {
                        //Check whether the RobotInfo object's ArrayBlockingQueue is empty
                        if(!myRobot.isEmptyQueue())
                        {
                            //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                            myRobot.setHealth(myRobot.removeQueue());
                            //Below method will refresh the GUI and display the accurate position and health
                            GM.refresh();
                        }
                        //AIImplementationC thread will sleep for a half a second
                        Thread.sleep(500);
                        //Display the myRobot object's name with is firing
                        GM.writeLog(myRobot.getName()+" is firing\n");
                        //'Robot3Shoot' method will draw the line from 3rd robot to robot stored inside the 'ROBOT' object
                        GM.Robot3Shoot(ROBOT.getX(), ROBOT.getY());
                        //Below method will refresh the GUI and display the accurate position and health
                        GM.refresh();
                        
                        //Call the 'fire' method of Robot Control object called 'rc' and 
                        //store the return boolean value in the 'shot' variable 
                        shot = rc.fire(ROBOT.getX(), ROBOT.getY());
                        
                        //If shot is equal to true, then display fire successful with 'myRobot' object's name
                        //Else display fire missed with 'myRobot' object's name
                        if(shot)
                        {
                            GM.writeLog(myRobot.getName()+" fire successful\n");
                        }
                        else
                        {
                            GM.writeLog(myRobot.getName()+" fire missed\n");
                        }
                        
                        //Check whether the RobotInfo object's ArrayBlockingQueue is empty  
                        if(!myRobot.isEmptyQueue())
                        {
                            //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                            myRobot.setHealth(myRobot.removeQueue());
                            //Below method will refresh the GUI and display the accurate position and health
                            GM.refresh();
                        }
                        
                        //This break keyword will allows to break from for each loop                        
                        break;
                    }
                }
                
                //This is used to check whether the robot's health is equal to 0
                //If true, reduce the count variable value by calling 'removeRobot' method
                //'setDead' method will set the 'dead' variable of robot object to true
                //At last, break from infinite loop
                if(myRobot.getHealth() == 0)
                {
                    rc.removeRobot();
                    myRobot.setDead();
                    //Display that robot has died with 'myRobot' object's name
                    GM.writeLog(myRobot.getName()+" is Dead\n");
                    
                    //If count variable of Robot Control Object equals to 0, 
                    //then display that the game is over
                    //At the end break from infinite loop
                    if(rc.getCount() == 0)
                    {
                        GM.writeLog("Game over\n");
                    }
                    break;   
                }
                
                //If count variable of Robot Control Object equals to 1, 
                //then display the winner robot's name and that the game is over
                //At the end break from infinite loop
                if(rc.getCount() == 1)
                {
                    GM.writeLog(myRobot.getName()+" is the winner\n");
                    GM.writeLog("Game over\n");
                    break;
                }
                
                //Check whether the RobotInfo object's ArrayBlockingQueue is empty
                if(!myRobot.isEmptyQueue())
                {
                    //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                    myRobot.setHealth(myRobot.removeQueue());
                    //Below method will refresh the GUI and display the accurate position and health
                    GM.refresh();
                }
                
                //Check whether the 'direction' variable contain the 'north' value
                //If true, Then check whether robot can move to north by calling 'moveNorth' method in Robot Control
                //Robot will return true if robot can move north
                //then 'refresh' method will refresh the GUI and 
                //Thread will sleep for 1 second after updating GUI
                //Else 'direction' variable value is changed to 'west'
                if(direction.equals("north"))
                {
                    if(!rc.moveNorth())
                    { 
                        direction = "west";
                    }
                    else
                    {
                        GM.refresh();
                        Thread.sleep(1000);
                    }
                }
                
                //Check whether the 'direction' variable contain the 'east' value
                //If true, Then check whether robot can move to east by calling 'moveEast' method in Robot Control
                //Robot will return true if robot can move east
                //then 'refresh' method will refresh the GUI and 
                //Thread will sleep for 1 second after updating GUI
                //Else 'direction' variable value is changed to 'north'
                else if(direction.equals("east"))
                {
                    if(!rc.moveEast())
                    { 
                        direction = "north";
                    }
                    else
                    {
                        GM.refresh();
                        Thread.sleep(1000);
                    }
                }
                
                //Check whether the 'direction' variable contain the 'south' value
                //If true, Then check whether robot can move to south by calling 'moveSouth' method in Robot Control
                //Robot will return true if robot can move south
                //then 'refresh' method will refresh the GUI and 
                //Thread will sleep for 1 second after updating GUI
                //Else 'direction' variable value is changed to 'east'
                else if(direction.equals("south"))
                {
                    if(!rc.moveSouth())
                    { 
                        direction = "east";
                    }
                    else
                    {
                        GM.refresh();
                        Thread.sleep(1000);
                    }
                }
                
                //Check whether the 'direction' variable contain the 'west' value
                //If true, Then check whether robot can move to west by calling 'moveWest' method in Robot Control
                //Robot will return true if robot can move west
                //then 'refresh' method will refresh the GUI and 
                //Thread will sleep for 1 second after updating GUI
                //Else 'direction' variable value is changed to 'south'
                else if(direction.equals("west"))
                {
                    if(!rc.moveWest())
                    { 
                        direction = "south";
                    }
                    else
                    {
                        GM.refresh();
                        Thread.sleep(1000);
                    }
                }
                else
                {
                    //If direction variable has a unknown value, Do nothing
                }
            }
        } catch (InterruptedException ex) {
                    Logger.getLogger("logger").info("AI ImplementationC interrupted! Exception - " + ex);
                }    
            

    }    
}
