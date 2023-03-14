import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Phrasebook extends Application{

	@Override
    public void start(Stage primaryStage) { // stage is our window
		// Entry point for an application
        //btn.setOnAction(new EventHandler<ActionEvent>() {
            //@Override
            //public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
            //}
		GUI gui = new GUI();
		gui.start(primaryStage);
	}
}
	
