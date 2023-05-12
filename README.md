Installation guide

1. Clone repository using git terminal
2. Download latest version of JavaFX SDK for Windows platform(x64) here  https://gluonhq.com/products/javafx/
3. import project into Eclipse IDE
4. Open project properties window - Project -> Properties
5. Go to Java Build Path 
6. Add your javaFX SDK library: Select JavaFX20 -> Edit -> User Libraries -> Click on JavaFX20 -> Add External JARs - and add folowing modules:
   javafx.base.jar
   javafx.controls.jar
   javafx.fxml.jar
   javafx.graphics.jar
7. Add path to your jre - Select JRE System Library - Edit - Alternate JRE   
8. Right click on project -> Run As -> Run Configuration -> Arguments -> VM Arguments and type following: --module-path "\path to javafx\lib" --add-modules javafx.controls,javafx.fxml
   and insert your path to the lib folder of your JavaFX library in " " 
9. Compile and run   
   
For now everything should be working
