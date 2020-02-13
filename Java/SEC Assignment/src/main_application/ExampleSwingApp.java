package main_application;

//imports of AI Implementation A,B and C classes
import ai_implementation_c.AIImplementationC;
import ai_implementation_b.AIImplementationB;
import ai_implementation_a.AIImplementationA;

//imports of RobotInfo interface and class
import robot.RobotInfo;
import robot.RobotInfoImpl;

//imports of RobotControl interface and class
import robot.RobotControl;
import robot.RobotControlImpl;

public class ExampleSwingApp 
{
    //1st robot, 2nd robot, 3rd robot declarations
    private static RobotInfo R1;
    private static RobotInfo R2;
    private static RobotInfo R3;

    //1st robot control, 2nd robot control and 3rd robot control declarations
    private static RobotControl RC1;
    private static RobotControl RC2;
    private static RobotControl RC3;
    
    public static void main(String[] args) throws InterruptedException 
    {
        //1st robot initialization and robot name, health, X and Y variable modifications
        R1 = new RobotInfoImpl();
        R1.setName("SDR1");
        R1.setHealth(100);
        R1.setX(0);
        R1.setY(0);
        
        //2nd robot initialization and robot name, health, X and Y variable modifications
        R2 = new RobotInfoImpl();
        R2.setName("TER2");
        R2.setHealth(100);
        R2.setX(6);
        R2.setY(7);
        
        //3rd robot initialization and robot name, health, X and Y variable modifications
        R3 = new RobotInfoImpl();
        R3.setName("R2D2");
        R3.setHealth(100);
        R3.setX(11);
        R3.setY(0);
        
        //1st robot control initialization and robot assignment for robot control
        RC1 = new RobotControlImpl();
        RC1.setRobot(R1);
        
        //2nd robot control initialization and robot assignment for robot control
        RC2 = new RobotControlImpl();
        RC2.setRobot(R2);
        
        //3rd robot control initialization and robot assignment for robot control
        RC3 = new RobotControlImpl();
        RC3.setRobot(R3);
        
        //AI Implementation objects declaration and initialization
        AIImplementationA AIA = new AIImplementationA();
        AIImplementationB AIB = new AIImplementationB();
        AIImplementationC AIC = new AIImplementationC();
        
        //GUI Manage object declaration and initialization
        GUIManageImpl GUIMI = new GUIManageImpl();
        
        //Insert the created GUI Manage object to GUI Manage object
        GUIMI.GUIOBjectSet(GUIMI);
        
        //Insert the created robots objects object to GUI Manage object
        GUIMI.guiManageRobots(R1, R2, R3);
        
        //Insert the created Robot control object to GUI Manage object
        GUIMI.guiManageRobotControls(RC1, RC2, RC3);
        
        //Insert the created AI Implementation objects to GUI Manage object
        GUIMI.AISet(AIA, AIB, AIC);

        //Call UIload method of GUI Manage object
        GUIMI.UIload();
        
    }
}
