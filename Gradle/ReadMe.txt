
How to execute :-

		First open command prompt (cmd)
		Check whether your computer has gradle installed by typing 'gradle -v' in the cmd
		If not installed please install gradle
		Then navigate inside the folder called 'root_project' using 'cd' command (Ex:- cd Assignment/root_project)
		After that, build the gradle project using 'gradlew build' command
		At last, run the project using 'gradlew run' command


JNI Implementation is stored in a folder called 'CImplJNILibrary'

Additional AI Implementation - AI Implementation will check whether the queue is not empty and if it is true, 
								robot health will be updated to the blocking queue health
  
Demonstration of the Blocking Queue feature - In JNI AIImplementationA, Robot will move east when it's hit

Invoke later was not used because AI threads will not sync with GUI refresh

Comments :- Gradle project build is successful without the settings.gradle and build.gradle in projects except for root_project and  main_application project
		But I have kept them in those projects so individual project can build themself