/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vageesha
 */
public interface GUIManage {
    
    //Method for assigning values for Robot AI variables
    void AISet(RobotAI AIAA, RobotAI AIBB, RobotAI AICC);
    
    //Method for assigning value for GUI Manage variable
    void GUIOBjectSet(GUIManage GG); 
    
    //Method for assigning values for RobotInfo variables
    void guiManageRobots(RobotInfo R11, RobotInfo R22, RobotInfo R33);
    
    //Method for assigning values for Robot Control variables
    void guiManageRobotControls(RobotControl RC11, RobotControl RC22, RobotControl RC33);
    
    //Given method from ExamppleSwingApp.java file
    //This method is implemented to create GUI (Graphical User Interface)
    void UIload();
    
    //This method is used to refresh the GUI (Graphical User Interface)
    void refresh();
    
    //This method is used to draw a line from 1st robot to given a given location (xx and yy are the line end positions)
    void Robot1Shoot(int xx, int yy);
    
    //This method is used to draw a line from 2nd robot to given a given location (xx and yy are the line end positions)
    void Robot2Shoot(int xx, int yy);
    
    //This method is used to draw a line from 3rd robot to given a given location (xx and yy are the line end positions)
    void Robot3Shoot(int xx, int yy);
    
    //This method is used to write in the GUI log
    void writeLog(String s);
    
    //This method is used to get 'JNICSTART' variable to AI Implementation in C language
    int getJNICSTRAT();
}
