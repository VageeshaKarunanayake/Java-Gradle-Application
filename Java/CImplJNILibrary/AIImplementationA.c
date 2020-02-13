// Native methods implementation of
// C:/Users/Vageesha/Documents/NetBeansProjects/SEC Assignment/src/ai_implementation_a/AIImplementationA.java

#include "AIImplementationA.h"


void JNICALL Java_AIImplementationA_callCImplementation
  (JNIEnv * env, jobject object, jobject param1, jobject param2) {

    //Below code will get the classes of the param1 (Robot Control Object) and param2 (GUI Manage)
    jclass RC = (*env)->GetObjectClass(env, param1);
    jclass GM = (*env)->GetObjectClass(env, param2);
    
    //Below code is used to find all the methods of the Robot Control and assign them to individual jmethods 
    jmethodID moveSouth = (*env)->GetMethodID(env, RC, "moveSouth", "()Z");
    jmethodID moveNorth = (*env)->GetMethodID(env, RC, "moveNorth", "()Z");
    jmethodID moveEast = (*env)->GetMethodID(env, RC, "moveEast", "()Z");
    jmethodID moveWest = (*env)->GetMethodID(env, RC, "moveWest", "()Z");
    jmethodID getRobot = (*env)->GetMethodID(env, RC, "getRobot", "()LRobotInfo;");
    jmethodID getAllRobots = (*env)->GetMethodID(env, RC, "getAllRobots", "()[LRobotInfo;");
    jmethodID removeRobot = (*env)->GetMethodID(env, RC, "removeRobot", "()V");
    jmethodID getCount = (*env)->GetMethodID(env, RC, "getCount", "()I");
    jmethodID fire = (*env)->GetMethodID(env, RC, "fire", "(II)Z");
    
    //Below code is used to find all the methods of the GUI Manage and assign them to individual jmethods 
    jmethodID writeLog = (*env)->GetMethodID(env, GM, "writeLog", "(Ljava/lang/String;)V");
    jmethodID refresh = (*env)->GetMethodID(env, GM, "refresh", "()V");
    jmethodID Robot1Shoot = (*env)->GetMethodID(env, GM, "Robot1Shoot", "(II)V");
    jmethodID getJNICSTRAT = (*env)->GetMethodID(env, GM, "getJNICSTRAT", "()I");
    
    //get the robot object from 'getRobot' method and assign it to jobject called 'robotobj' 
    jobject robotobj =  (*env)->CallObjectMethod(env, param1, getRobot);
    
    //get the class of the robot object
    jclass robot = (*env)->GetObjectClass(env, robotobj);
    
    //Below code is used to find all the methods of the RobotInfo and assign them to individual jmethods
    jmethodID setDead = (*env)->GetMethodID(env, robot, "setDead", "()V");
    jmethodID setHealth = (*env)->GetMethodID(env, robot, "setHealth", "(D)V");
    
    jmethodID getDead = (*env)->GetMethodID(env, robot, "getDead", "()Z");
    jmethodID getHealth = (*env)->GetMethodID(env, robot, "getHealth", "()D");
    jmethodID getName = (*env)->GetMethodID(env, robot, "getName", "()Ljava/lang/String;");
    jmethodID getY = (*env)->GetMethodID(env, robot, "getY", "()I");
    jmethodID getX = (*env)->GetMethodID(env, robot, "getX", "()I");
    
    jmethodID removeQueue = (*env)->GetMethodID(env, robot, "removeQueue", "()D");
    jmethodID isEmptyQueue = (*env)->GetMethodID(env, robot, "isEmptyQueue", "()Z");
    
    //get the name of the robot object called 'robotobj' and assign it to 'robotname' jobject
    jobject robotname = (*env)->CallObjectMethod(env, robotobj, getName);
    
    //cast the jobject into jstring and finally convert the name into char* 
    char* robotN = (char*)(*env)->GetStringUTFChars(env, (jstring)robotname , NULL);
    
    //declare a char8 called 'direction' and it's value is south
    char* direction = "south";
    
    //If exception found return the control to Java code  
    if((*env)->ExceptionCheck(env))
    {
        return;
    }

    //This while loop will end when the stop button is clicked
    //Button click will make the JNICSTART variable false
    while ((*env)->CallIntMethod(env, param2, getJNICSTRAT))
            {
                //Check whether the robot is dead by calling 'getDead' method of robot object
                //If true, breaks from the infinite loop
                if((*env)->CallBooleanMethod(env, robotobj, getDead))
                {
                   break; 
                }
                
                //Check whether the RobotInfo object's ArrayBlockingQueue is empty  
                if(!(*env)->CallBooleanMethod(env, robotobj, isEmptyQueue))
                {
                    //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                    (*env)->CallVoidMethod(env, robotobj, setHealth, (*env)->CallDoubleMethod(env, robotobj, removeQueue));
                    
                    //Display the name of the robot
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                    //Display 'is moving East' in the same line
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is moving East\n"));
                    //Move the robot to east by calling 'moveEast' method
                    (*env)->CallBooleanMethod(env, param1, moveEast);
                    //Below method will refresh the GUI and display the accurate position and health 
                    (*env)->CallVoidMethod(env, param2, refresh);
                }
                
                //This is used to check whether the robot's health is equal to 0
                //If true, reduce the count variable value by calling 'removeRobot' method
                //'setDead' method will set the 'dead' variable of robot object to true
                //At last, break from infinite loop
                if((*env)->CallDoubleMethod(env, robotobj, getHealth) == 0)
                {
                    (*env)->CallVoidMethod(env, param1, removeRobot);
                    (*env)->CallVoidMethod(env, robotobj, setDead);
                    //Display the name of the robot
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                    //Display 'is Dead' in the same line
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is Dead\n"));
                    
                    //If count variable of Robot Control Object equals to 0, 
                    //then display that the game is over
                    //At the end break from infinite loop
                    if((*env)->CallIntMethod(env, param1, getCount) == 0)
                    {
                        (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, "Game Over\n"));
                    }
                    break;
                }

                //If count variable of Robot Control Object equals to 1, 
                //then display the winner robot's name and that the game is over
                //At the end break from infinite loop
                if((*env)->CallIntMethod(env, param1, getCount) == 1)
                {
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is the winner\n"));
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, "Game Over\n"));
                    break;
                }
                
                //get a RobotInfo object array by calling the 'getAllRobots' method and save the returned array to a jobjectArray
                jobjectArray allrobots = (*env)->CallObjectMethod(env, param1, getAllRobots);
       
                //For loop
                for(int i = 0; i < (*env)->GetArrayLength(env, allrobots); i++)
                {
                    
                    //Check whether the RobotInfo object's ArrayBlockingQueue is empty  
                    if(!(*env)->CallBooleanMethod(env, robotobj, isEmptyQueue))
                    {
                        //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                        (*env)->CallVoidMethod(env, robotobj, setHealth, (*env)->CallDoubleMethod(env, robotobj, removeQueue));

                        //Display the name of the robot
                        (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                        //Display 'is moving East' in the same line
                        (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is moving East\n"));
                        //Move the robot to east by calling 'moveEast' method
                        (*env)->CallBooleanMethod(env, param1, moveEast);
                        //Below method will refresh the GUI and display the accurate position and health 
                        (*env)->CallVoidMethod(env, param2, refresh);
                    }
                    
                    //If 'robotobj' object's name equals to the robot object's name in the allrobots array, do nothing
                    if (strcmp((char*)(*env)->GetStringUTFChars(env, (jstring)(*env)->CallObjectMethod(env, (*env)->GetObjectArrayElement(env, allrobots, i), getName), NULL), robotN) == 0)
                    {
                        //Do nothing
                    }
                    else
                    {   
                        //absolute value of 'robotobj' object's x - robot object's x in the allrobots array is less or equal to 2 and
                        //absolute value of 'robotobj' object's y - robot object's y in the allrobots array is less or equal to 2 and
                        //robot object's health in the allrobots array is not equal to 0
                        if((abs((*env)->CallIntMethod(env, robotobj, getX) - (*env)->CallIntMethod(env, (*env)->GetObjectArrayElement(env, allrobots, i), getX)) <= 2) && (abs((*env)->CallIntMethod(env, robotobj, getY) - (*env)->CallIntMethod(env, (*env)->GetObjectArrayElement(env, allrobots, i), getY)) <= 2) && ((*env)->CallDoubleMethod(env, (*env)->GetObjectArrayElement(env, allrobots, i), getHealth) != 0))
                        {
                            //Check whether the RobotInfo object's ArrayBlockingQueue is empty  
                            if(!(*env)->CallBooleanMethod(env, robotobj, isEmptyQueue))
                            {
                                //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                                (*env)->CallVoidMethod(env, robotobj, setHealth, (*env)->CallDoubleMethod(env, robotobj, removeQueue));

                                //Display the name of the robot
                                (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                                //Display 'is moving East' in the same line
                                (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is moving East\n"));
                                //Move the robot to east by calling 'moveEast' method
                                (*env)->CallBooleanMethod(env, param1, moveEast);
                                //Below method will refresh the GUI and display the accurate position and health 
                                (*env)->CallVoidMethod(env, param2, refresh);
                            }
                            
                            //Sleep the thread for half a second
                            usleep(500000);
                        
                            //Display the name of the robot
                            (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                            //Display 'is firing' in the same line
                            (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is firing\n"));
                            
                            //'Robot1Shoot' method will draw the line from 1st robot to robot stored inside the 'allrobots' array
                            //This method will take the parameters as the x and y of the robot that get hit
                            (*env)->CallVoidMethod(env, param2, Robot1Shoot, (*env)->CallIntMethod(env, (*env)->GetObjectArrayElement(env, allrobots, i), getX), (*env)->CallIntMethod(env, (*env)->GetObjectArrayElement(env, allrobots, i), getY));
                            //Below method will refresh the GUI and display the accurate position and health
                            (*env)->CallVoidMethod(env, param2, refresh);
                            
                            //Call the 'fire' method of Robot Control object called 'rc' and 
                            //store the return boolean value in the 'shot' variable 
                            jboolean shot = (*env)->CallBooleanMethod(env, param1, fire, (*env)->CallIntMethod(env, (*env)->GetObjectArrayElement(env, allrobots, i), getX), (*env)->CallIntMethod(env, (*env)->GetObjectArrayElement(env, allrobots, i), getY));
                            
                            //If shot is equal to true, then display fire successful with 'robotobj' object's name
                            //Else display fire missed with 'robotobj' object's name
                            if(shot)
                            {
                                (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                                (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " fire successful\n"));
                            }
                            else
                            {
                                (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                                (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " fire missed\n"));
                            }

                            //Check whether the RobotInfo object's ArrayBlockingQueue is empty  
                            if(!(*env)->CallBooleanMethod(env, robotobj, isEmptyQueue))
                            {
                                //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                                (*env)->CallVoidMethod(env, robotobj, setHealth, (*env)->CallDoubleMethod(env, robotobj, removeQueue));

                                //Display the name of the robot
                                (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                                //Display 'is moving East' in the same line
                                (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is moving East\n"));
                                //Move the robot to east by calling 'moveEast' method
                                (*env)->CallBooleanMethod(env, param1, moveEast);
                                //Below method will refresh the GUI and display the accurate position and health 
                                (*env)->CallVoidMethod(env, param2, refresh);
                            }
                            
                            //This break keyword will allows to break from for loop
                            break;
                        }
                    } 
                }
                
                //This is used to check whether the robot's health is equal to 0
                //If true, reduce the count variable value by calling 'removeRobot' method
                //'setDead' method will set the 'dead' variable of robot object to true
                //At last, break from infinite loop
                if((*env)->CallDoubleMethod(env, robotobj, getHealth) == 0)
                {
                    (*env)->CallVoidMethod(env, param1, removeRobot);
                    (*env)->CallVoidMethod(env, robotobj, setDead);
                    //Display the name of the robot
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                    //Display 'is Dead' in the same line
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is Dead\n"));
                    
                    //If count variable of Robot Control Object equals to 0, 
                    //then display that the game is over
                    //At the end break from infinite loop
                    if((*env)->CallIntMethod(env, param1, getCount) == 0)
                    {
                        (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, "Game Over\n"));
                    }
                    break;
                }

                //If count variable of Robot Control Object equals to 1, 
                //then display the winner robot's name and that the game is over
                //At the end break from infinite loop
                if((*env)->CallIntMethod(env, param1, getCount) == 1)
                {
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is the winner\n"));
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, "Game Over\n"));
                    break;
                }

                //Check whether the RobotInfo object's ArrayBlockingQueue is empty  
                if(!(*env)->CallBooleanMethod(env, robotobj, isEmptyQueue))
                {
                    //Get the health from ArrayBlockingQueue and assign the health to the robot object's health
                    (*env)->CallVoidMethod(env, robotobj, setHealth, (*env)->CallDoubleMethod(env, robotobj, removeQueue));

                    //Display the name of the robot
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, robotN));
                    //Display 'is moving East' in the same line
                    (*env)->CallVoidMethod(env, param2, writeLog, (*env)->NewStringUTF(env, " is moving East\n"));
                    //Move the robot to east by calling 'moveEast' method
                    (*env)->CallBooleanMethod(env, param1, moveEast);
                    //Below method will refresh the GUI and display the accurate position and health 
                    (*env)->CallVoidMethod(env, param2, refresh);
                }
                
                
                //Check whether the 'direction' variable contain the 'north' value
                //If true, Then check whether robot can move to north by calling 'moveNorth' method in Robot Control
                //Robot will return true if robot can move north
                //then 'refresh' method will refresh the GUI and 
                //Thread will sleep for 1 second after updating GUI
                //Else 'direction' variable value is changed to 'east'
                if(strcmp(direction, "north") == 0)
                {
                    if(!(*env)->CallBooleanMethod(env, param1, moveNorth))
                    { 
                        direction = "east";
                    }
                    else
                    {
                        (*env)->CallVoidMethod(env, param2, refresh);
                        usleep(1000000);
                    }
                }
                
                //Check whether the 'direction' variable contain the 'east' value
                //If true, Then check whether robot can move to east by calling 'moveEast' method in Robot Control
                //Robot will return true if robot can move east
                //then 'refresh' method will refresh the GUI and 
                //Thread will sleep for 1 second after updating GUI
                //Else 'direction' variable value is changed to 'south'
                else if(strcmp(direction, "east") == 0)
                {
                    if(!(*env)->CallBooleanMethod(env, param1, moveEast))
                    { 
                        direction = "south";
                    }
                    else
                    {
                        (*env)->CallVoidMethod(env, param2, refresh);
                        usleep(1000000);
                    }
                }
                
                //Check whether the 'direction' variable contain the 'south' value
                //If true, Then check whether robot can move to south by calling 'moveSouth' method in Robot Control
                //Robot will return true if robot can move south
                //then 'refresh' method will refresh the GUI and 
                //Thread will sleep for 1 second after updating GUI
                //Else 'direction' variable value is changed to 'west'
                else if(strcmp(direction, "south") == 0)
                {
                    if(!(*env)->CallBooleanMethod(env, param1, moveSouth))
                    { 
                        direction = "west";
                    }
                    else
                    {
                        (*env)->CallVoidMethod(env, param2, refresh);
                        usleep(1000000);
                    }
                }
                
                //Check whether the 'direction' variable contain the 'west' value
                //If true, Then check whether robot can move to west by calling 'moveWest' method in Robot Control
                //Robot will return true if robot can move west
                //then 'refresh' method will refresh the GUI and 
                //Thread will sleep for 1 second after updating GUI
                //Else 'direction' variable value is changed to 'north'
                else if(strcmp(direction, "west") == 0)
                {
                    if(!(*env)->CallBooleanMethod(env, param1, moveWest))
                    { 
                        direction = "north";
                    }
                    else
                    {
                        (*env)->CallVoidMethod(env, param2, refresh);
                        usleep(1000000);
                    }
                }
                else
                {
                    //If direction variable has a unknown value, Do nothing
                }
                
                //If exception found return the control to Java code  
                if((*env)->ExceptionCheck(env))
                {
                    return;
                }

    }
    
}
