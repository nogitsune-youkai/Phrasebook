import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;

public class GUI {
	private Button addNewWord = new Button("Добавить слово");
	private Button saveButton = new Button("Сохранить");
	private TextField textInput = new TextField();
	private VBox vBoxButtons = new VBox();
	
	 public void start(Stage primaryStage) {
		 
	    addNewWord.setOnAction(event -> { addWord();});
	    
		StackPane root = new StackPane(); //the root node's size tracks the scene's size and 
        //changes when the stage is resized by a user
		vBoxButtons.getChildren().add(addNewWord);
		vBoxButtons.getChildren().add(saveButton);
		vBoxButtons.setAlignment(Pos.BASELINE_RIGHT);
        vBoxButtons.setSpacing(3);
		//root.setAlignment(Pos.BASELINE_RIGHT);
        root.getChildren().add(vBoxButtons);
        root.getChildren().add(textInput);
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
		inputDialog.showAndWait();
		textInput.setText(inputDialog.getResult());
	}
}
