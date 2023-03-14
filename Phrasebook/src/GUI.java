import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class GUI {
	
	 public void start(Stage primaryStage) {
		 
		Button addNewWord = new Button();
	    addNewWord.setText("Добавить слово");
		StackPane root = new StackPane(); //the root node's size tracks the scene's size and 
        //changes when the stage is resized by a user
        root.getChildren().add(addNewWord);
        // Setting up a scene, JavaFX Scene class is the container for all content 
        // which makes the scene visible in a given pixel size. 
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("Разговорник");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
