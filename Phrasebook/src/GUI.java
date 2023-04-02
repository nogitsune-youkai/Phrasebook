import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.*;

public class GUI {
	 private Button addNewWord = new Button("Добавить слово");
	 private Button saveButton = new Button("Сохранить");
	 private VBox vBoxButtons = new VBox(); // VBox for buttons
	 private HBox hBoxText = new HBox();
	 
	 public void start(Stage primaryStage) {
		
		Lexicon lexicon = new Lexicon(); 
		lexicon.initializeColumns();
	    addNewWord.setOnAction(event -> { lexicon.add(event);});
	    
		StackPane root = new StackPane(); //the root node's size tracks the scene's size and 
        //changes when the stage is resized by a user
		//hBoxText.getChildren().add(textInput);
		hBoxText.getChildren().add(lexicon.getTable());
		vBoxButtons.getChildren().add(addNewWord);
		vBoxButtons.getChildren().add(saveButton);
		hBoxText.getChildren().add(vBoxButtons);
		hBoxText.setAlignment(Pos.BASELINE_RIGHT);
        

        root.getChildren().add(hBoxText);
        // Setting up a scene, JavaFX Scene class is the container for all content 
        // which makes the scene visible in a given pixel size. 
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Разговорник");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
