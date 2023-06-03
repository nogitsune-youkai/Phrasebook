import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class GUI {
	
	 private static final String WINDOW_TITLE = "Разговорник";
     private static final int SCENE_WIDTH = 800;
     private static final int SCENE_HEIGHT = 600;
     private static final String ADD_NEW_WORD_TEXT = "Добавить слово";
     private static final String SAVE_TEXT = "Сохранить";
     private static final String WORD_COUNT_TEXT = "Количество слов: ";
     private static final String ABOUT_BUTTON_TEXT = "О программе";
     private static final String SEARCH_INPUT_PROMPT_TEXT = "Введите слово";
     
    
	 private Button addNewWord = new Button(ADD_NEW_WORD_TEXT);
	 private Button saveButton = new Button(SAVE_TEXT);
	 private Button aboutButton = new Button(ABOUT_BUTTON_TEXT);
	 private VBox vBoxButtons = new VBox(); // VBox for buttons
	 private VBox vBoxWordCount = new VBox(); // VBox for wordCount label
	 private HBox hBoxText = new HBox(); // HBox for main panel with a table 
	 private Label countLabel = new Label(); // label which displays a number of words
	 private TextField searchInput = new TextField();
	 
	 private CheckBox  meaningSearch = new CheckBox("Поиск по значению");
	 boolean isWordSearch = !meaningSearch.isSelected(); // Get the value of the check box
	 
	 
	 public void start(Stage primaryStage) {
		 
		Lexicon lexicon = initLexicon();
	    BorderPane root = createUI(lexicon);
	    showScene(primaryStage, root); 			
	    meaningSearch.setSelected(false); // By default, search by word
		addNewWord.setOnAction(event -> { 
			
		    lexicon.addWordToTable(event);
		    if(lexicon.isWordAdded()) { // just to make sure if a user didn't change his mind on adding a word in the last moment
		    	countLabel.setText("Количество слов: " + lexicon.getTable().getItems().size());	
		    }
		    });
		aboutButton.setOnAction(event -> { // action code
			GUI.showAboutDialogWindow();
		});
		//searchInput.setOnAction(e -> {
			//lexicon.addSearchListener(this);
		//});
	    lexicon.addSearchListener(this);
	    
	    meaningSearch.setOnAction(event -> {
	        isWordSearch = !meaningSearch.isSelected(); // Toggle the value of isWordSearch
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
		vBoxButtons.getChildren().add(searchInput);
		vBoxButtons.getChildren().add(meaningSearch);
		vBoxWordCount.getChildren().add(hBoxText);
		hBoxText.getChildren().add(vBoxButtons);
		
		searchInput.setPromptText(SEARCH_INPUT_PROMPT_TEXT);
		HBox hBoxCount = new HBox(countLabel);
		hBoxCount.setAlignment(Pos.BOTTOM_LEFT);
		hBoxCount.setPadding(new Insets(0, 0, 5, 0));
		
		countLabel.setText(WORD_COUNT_TEXT + lexicon.getTable().getItems().size());
		root.setCenter(hBoxText);
		HBox hBoxAbout = new HBox(aboutButton); 
		hBoxAbout.setAlignment(Pos.BASELINE_RIGHT);
		
		BorderPane.setMargin(hBoxAbout, new Insets(-4, 15, 0, 0));
		BorderPane bottomPane = new BorderPane();
		bottomPane.setLeft(hBoxCount); 
		bottomPane.setRight(hBoxAbout); 
		root.setCenter(hBoxText);
		root.setBottom(bottomPane);
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
	
	private static void showAboutDialogWindow() {
		Dialog<String> aboutWindowDialog = new Dialog<>();
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(new Text("Разговорник - программа для изучения иностранных слов"));
		dialogVbox.getChildren().add(new Text("Разработчик: Nogitsune Youkai"));
	    dialogVbox.getChildren().add(new Text("Версия: 2023-05(1.0)"));
	    aboutWindowDialog.getDialogPane().setContent(dialogVbox);
		aboutWindowDialog.setTitle("О программе");
		aboutWindowDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		aboutWindowDialog.showAndWait();
	}
	
	public TextField getSearchInput() {
		return searchInput;
	}


	public boolean getWordSearch() {
		return isWordSearch;
	}
}
