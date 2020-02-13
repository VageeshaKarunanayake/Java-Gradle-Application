/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//java imports
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vageesha
 */
public class GUIManageImpl implements GUIManage{
    
    //JTextArea, SwingArena and boolean variables declarations
    private static JTextArea logger;
    private static SwingArena arena;
    private static boolean started = false;
    
    //1st robot, 2nd robot, 3rd robot declarations
    private static RobotInfo R1;
    private static RobotInfo R2;
    private static RobotInfo R3;
    
    //1st robot control, 2nd robot control and 3rd robot control declarations
    private static RobotControl RC1;
    private static RobotControl RC2;
    private static RobotControl RC3;
    
    //Thread AIAT, Thread AIBT and Thread AICT declarations 
    private static Thread AIAT;
    private static Thread AIBT;
    private static Thread AICT;
    
    //All the mutex objects declarations and initializations
    private final static Object mutex = new Object();
    private final static Object mutex2 = new Object();
    private final static Object mutex3 = new Object();
    private final static Object mutex4 = new Object();
    private final static Object mutex5 = new Object();
    private final static Object mutex6 = new Object();
    
    //Robot AI declarations
    private static RobotAI AIA;
    private static RobotAI AIB;
    private static RobotAI AIC;
    
    //GUI Manage variable declaration 
    private GUIManage GUIMI;
    
    //JNI Thread management variable declaration
    private int JNICSTART;
    
    //Method for assigning values for Robot AI variables
    @Override
    public void AISet(RobotAI AIAA, RobotAI AIBB, RobotAI AICC)
    {
        AIA = AIAA;
        AIB = AIBB;
        AIC = AICC;
    }
    
    //Method for assigning value for GUI Manage variable
    @Override
    public void GUIOBjectSet(GUIManage GG)
    {
        GUIMI = GG;
    }
    
    //Method for assigning values for RobotInfo variables
    @Override
    public void guiManageRobots(RobotInfo R11, RobotInfo R22, RobotInfo R33)
    {
        R1 = R11;
        R2 = R22;
        R3 = R33;
    }
    
    //Method for assigning values for Robot Control variables
    @Override
    public void guiManageRobotControls(RobotControl RC11, RobotControl RC22, RobotControl RC33)
    {
        RC1 = RC11;
        RC2 = RC22;
        RC3 = RC33;
    }
    
    //Given method from ExamppleSwingApp.java file
    //This method is implemented to create GUI (Graphical User Interface)
    @Override
    public void UIload()
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame window = new JFrame("Robot AI Test (Swing)");
            arena = new SwingArena();
            
            arena.setRobot1Name(R1.getName());
            arena.setRobot1Position(R1.getX(), R1.getY());
            arena.setRobot1Health(R1.getHealth());
            
            arena.setRobot2Name(R2.getName());
            arena.setRobot2Position(R2.getX(), R2.getY());
            arena.setRobot2Health(R2.getHealth());
            
            arena.setRobot3Name(R3.getName());
            arena.setRobot3Position(R3.getX(), R3.getY());
            arena.setRobot3Health(R3.getHealth());
            
            JToolBar toolbar = new JToolBar();
            JButton btn1 = new JButton("Start");
            JButton btn2 = new JButton("Stop");
            toolbar.add(btn1);
            toolbar.add(btn2);
            
            //Modified the original given code
            btn1.addActionListener((event) ->
            {
                //Boolean value 'started' equals to true, do nothing
                //This is used to prevent system crashing when user click start button more than once
                if(started)
                {
                    //Do nothing
                }
                else
                {
                    //Set 'started' value to true to let the system know that threads have already began
                    started = true;
                    
                    //Set the 'JNICSTART' variable to 1
                    //This is used to indicate that the C JNI AI Implementation should run in the infinite while loop
                    synchronized(mutex6)
                    {
                        JNICSTART = 1;
                    }
                    
                    //Print 'Program Started' in the log
                    logger.append("Program Started\n");

                    //Initialize the AIAT thread and execute the 'runAI' method in AIA AI Implementation object
                    AIAT = new Thread(new Runnable(){
                    @Override
                    public void run() {
                                        AIA.runAI(RC1, GUIMI);
                                      }
                    });

                    //Initialize the AIBT thread and execute the 'runAI' method in AIB AI Implementation object
                    AIBT = new Thread(new Runnable(){
                        @Override
                        public void run() {
                                            AIB.runAI(RC2, GUIMI);
                                          }
                    });

                    //Initialize the AICT thread and execute the 'runAI' method in AIC AI Implementation object
                    AICT = new Thread(new Runnable(){
                        @Override
                        public void run() {
                                            AIC.runAI(RC3, GUIMI);
                                          }
                    });

                    //Start AIAT, AIBT and AICT threads
                    AIAT.start();
                    AIBT.start();
                    AICT.start();
                }
           
            });
            
             btn2.addActionListener((event) ->
            {
      
                if(started)
                {
                    //Set the 'started' variable value to false
                    started = false;
                    
                    //Set the 'JNICSTART' variable to 0
                    //This is used to indicate that the C JNI AI Implementation should stop
                    synchronized(mutex6)
                    {
                        JNICSTART = 0;
                    }
                    
                    //Print 'Program Started' in the log
                    logger.append("Program Stopped\n");

                    //Interrupt AIAT, AIBT and AICT threads
                    AIAT.interrupt();
                    AIBT.interrupt();
                    AICT.interrupt();
                }
                else
                {
                    //Boolean value 'started' equals to false, do nothing
                    //This is used to prevent system crashing when user click stop button more than once
                }
            });
            
            logger = new JTextArea();
            JScrollPane loggerArea = new JScrollPane(logger);
            loggerArea.setBorder(BorderFactory.createEtchedBorder());
            
            JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, arena, logger);

            Container contentPane = window.getContentPane();
            contentPane.setLayout(new BorderLayout());
            contentPane.add(toolbar, BorderLayout.NORTH);
            contentPane.add(splitPane, BorderLayout.CENTER);
            
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setPreferredSize(new Dimension(800, 800));
            window.pack();
            window.setVisible(true);
            
            splitPane.setDividerLocation(0.75);
        });
    }
    
    //This method is used to refresh the GUI (Graphical User Interface)
    @Override
    public void refresh()
    {
        //Call 1st robot, 2nd robot and 3rd robot 's health and position update methods declared in the SwingArena
        synchronized(mutex)
        {
            arena.setRobot1Position(R1.getX(), R1.getY());
            arena.setRobot1Health(R1.getHealth());

            arena.setRobot2Position(R2.getX(), R2.getY());
            arena.setRobot2Health(R2.getHealth());

            arena.setRobot3Position(R3.getX(), R3.getY());
            arena.setRobot3Health(R3.getHealth());
        }
        
    }
    
    //This method is used to draw a line from 1st robot to given a given location (xx and yy are the line end positions)
    //Method is synchronized to avoid race conditions
    @Override
    public void Robot1Shoot(int xx, int yy)
    {
        synchronized(mutex2)
        {
            try {
                arena.robot1Shot(xx, yy);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExampleSwingApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //This method is used to draw a line from 2nd robot to given a given location (xx and yy are the line end positions)
    //Method is synchronized to avoid race conditions
    @Override
    public void Robot2Shoot(int xx, int yy)
    {
        synchronized(mutex3)
        {
            try {
                arena.robot2Shot(xx, yy);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExampleSwingApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //This method is used to draw a line from 3rd robot to given a given location (xx and yy are the line end positions)
    //Method is synchronized to avoid race conditions
    @Override
    public void Robot3Shoot(int xx, int yy)
    {
        synchronized(mutex4)
        {
            try {
                arena.robot3Shot(xx, yy);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExampleSwingApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //This method is used to write in the GUI log
    //Method is synchronized to avoid race conditions
    @Override
    public void writeLog(String s)
    {
        synchronized(mutex5)
        {
            logger.append(s);
        }
    }
    
    //This method is used to get 'JNICSTART' variable to AI Implementation in C language
    //Method is synchronized to avoid race conditions
    @Override
    public int getJNICSTRAT()
    {
        synchronized(mutex)
        {
            return JNICSTART;
        }
    }
}
