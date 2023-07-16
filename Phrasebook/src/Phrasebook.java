import javafx.application.Application;
import javafx.stage.Stage;

public class Phrasebook extends Application{
	
	public Phrasebook() {
		
	}

	@Override
    public void start(Stage primaryStage) { // stage is our window
		// Entry point for an application
		GUI gui = new GUI();
		gui.start(primaryStage);
	}
	
	public static void main(String[] argc) {
		launch(argc);
	}
}
	
