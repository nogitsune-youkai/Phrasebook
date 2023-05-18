import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI {
	 private Button addNewWord = new Button("Добавить слово");
	 private Button saveButton = new Button("Сохранить");
	 private VBox vBoxButtons = new VBox(); // VBox for buttons
	 private VBox vBoxWordCount = new VBox(); // VBox for wordCount label
	 private HBox hBoxBottom = new HBox();
	 private HBox hBoxText = new HBox();
	 private Label countLabel = new Label();
	 
	 
	 public void start(Stage primaryStage) {
		
		Lexicon lexicon = new Lexicon(); 
		lexicon.initializeColumns();
		try {
			lexicon.initDictionary();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addNewWord.setOnAction(event -> { 
			
		    lexicon.add(event);
		    if(lexicon.isWordAdded()) { // just to make sure if a user didn't change his mind on adding a word in the last moment
		    	countLabel.setText("Количество слов: " + lexicon.getTable().getItems().size());	
		    }
		    });
	    
		BorderPane root = new BorderPane(); //the root node's size tracks the scene's size and 
        //changes when the stage is resized by a user
		hBoxText.getChildren().add(lexicon.getTable());
		vBoxButtons.getChildren().add(addNewWord);
		vBoxButtons.getChildren().add(saveButton);
		vBoxWordCount.getChildren().add(hBoxText);
		hBoxText.getChildren().add(vBoxButtons);
		countLabel = new Label("Количество слов: " + lexicon.getTable().getItems().size());
		hBoxBottom = new HBox(countLabel);
		vBoxWordCount.getChildren().add(hBoxBottom);
		hBoxBottom.setAlignment(Pos.CENTER);
		
		hBoxText.setAlignment(Pos.BASELINE_RIGHT);
        
        root.setCenter(hBoxText);
        root.setBottom(vBoxWordCount);
        // Setting up a scene, JavaFX Scene class is the container for all content 
        // which makes the scene visible in a given pixel size. 
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Разговорник");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
