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
	
	 private static final String WINDOW_TITLE = "Разговорник";
     private static final int SCENE_WIDTH = 800;
     private static final int SCENE_HEIGHT = 600;
     private static final String ADD_NEW_WORD_TEXT = "Добавить слово";
     private static final String SAVE_TEXT = "Сохранить";
     private static final String WORD_COUNT_TEXT = "Количество слов: ";
    
	 private Button addNewWord = new Button(ADD_NEW_WORD_TEXT);
	 private Button saveButton = new Button(SAVE_TEXT);
	 private VBox vBoxButtons = new VBox(); // VBox for buttons
	 private VBox vBoxWordCount = new VBox(); // VBox for wordCount label
	 private HBox hBoxBottom = new HBox(); // HBox for bottom panel
	 private HBox hBoxText = new HBox(); // HBox for text panel
	 private Label countLabel = new Label(); // label which displays a number of words
	 
	 
	 public void start(Stage primaryStage) {
		 
		Lexicon lexicon = initLexicon();
	    BorderPane root = createUI(lexicon);
	    showScene(primaryStage, root); 			
		addNewWord.setOnAction(event -> { 
			
		    lexicon.add(event);
		    if(lexicon.isWordAdded()) { // just to make sure if a user didn't change his mind on adding a word in the last moment
		    	countLabel.setText("Количество слов: " + lexicon.getTable().getItems().size());	
		    }
		    });
	}


	private void showScene(Stage primaryStage, BorderPane root) {
		// Setting up a scene, JavaFX Scene class is the container for all content 
        // which makes the scene visible in a given pixel size. 
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();	
	}


	private BorderPane createUI(Lexicon lexicon) {
		BorderPane root = new BorderPane(); //the root node's size tracks the scene's size and 
        //changes when the stage is resized by a user
		hBoxText.getChildren().add(lexicon.getTable());
		vBoxButtons.getChildren().add(addNewWord);
		vBoxButtons.getChildren().add(saveButton);
		vBoxWordCount.getChildren().add(hBoxText);
		hBoxText.getChildren().add(vBoxButtons);
		countLabel.setText(WORD_COUNT_TEXT + lexicon.getTable().getItems().size());
		hBoxBottom = new HBox(countLabel);
		vBoxWordCount.getChildren().add(hBoxBottom);
        hBoxBottom.setAlignment(Pos.CENTER);
		hBoxText.setAlignment(Pos.BASELINE_RIGHT);
		root.setCenter(hBoxText);
        root.setBottom(vBoxWordCount);
        return root;
	}


	private Lexicon initLexicon() {
		Lexicon lexicon = new Lexicon(); 
		lexicon.initializeColumns();
		try {
			lexicon.initDictionary();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lexicon;
	}
}
