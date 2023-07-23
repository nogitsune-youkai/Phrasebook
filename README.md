Installation guide

1. Clone repository using git terminal
2. Download latest version of JavaFX SDK for Windows platform(x64) here  https://gluonhq.com/products/javafx/
3. import project into Eclipse IDE
4. Add path to your jre - Select JRE System Library - Edit - Alternate JRE   
5. Right click on project -> Run As -> Run Configuration -> Create new launch configuration -> select Phrasebook as a main class
6. Arguments -> VM Arguments and type following: --module-path "\path to javafx\lib" --add-modules javafx.controls,javafx.fxml
   and insert your path to the lib folder of your JavaFX library in " ", JAVAFX is contained in lib folder, Do this step for all 2 Test Configurations, in order for testing to work
7. Go to GUITest configuration -> Arguments -> and type - --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED   
9. Compile and run   
   
For now everything should be working
