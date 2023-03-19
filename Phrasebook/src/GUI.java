import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;

public class GUI {
	Button addNewWord = new Button("Добавить слово");
	Button saveButton = new Button("Сохранить");
	VBox hBox = new VBox();
	
	 public void start(Stage primaryStage) {
		 
	    addNewWord.setOnAction(event -> { addWord();});
	    
		StackPane root = new StackPane(); //the root node's size tracks the scene's size and 
        //changes when the stage is resized by a user
		hBox.getChildren().add(addNewWord);
		hBox.getChildren().add(saveButton);
		hBox.setAlignment(Pos.BASELINE_RIGHT);
        hBox.setSpacing(3);
		//root.setAlignment(Pos.BASELINE_RIGHT);
        root.getChildren().add(hBox);
        // Setting up a scene, JavaFX Scene class is the container for all content 
        // which makes the scene visible in a given pixel size. 
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Разговорник");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	private void addWord() {
		// Will implement functionality for adding a new words here
		TextInputDialog inputDialog = new TextInputDialog();
		inputDialog.setTitle("Добавить новое слово");
		inputDialog.setContentText("Введите слово или словосочетание");		
		inputDialog.show();
		//TextField word = inputDialog.getEditor();
	}
}
