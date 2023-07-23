import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class GUI {
	
	 private static final String WINDOW_TITLE = "Разговорник";
     private static final int SCENE_WIDTH = 1024;
     private static final int SCENE_HEIGHT = 768;
     private static final String ADD_NEW_WORD_MENU_TEXT = "Добавить слово";
     private static final String WORD_COUNT_TEXT = "Количество слов: ";
     private static final String ABOUT_MENU_TEXT = "О программе";
     private static final String SEARCH_INPUT_PROMPT_TEXT = "Введите слово";
     private static final String TABLE_MENU_TEXT = "Словарь";
     private static final String ABOUT_MENU_ITEM_TEXT = "О разработчике";
     private static final String ABOUT_HELP_MENU_ITEM_TEXT = "Справка";
     private static final String TABLE_MENU_ALPHABET_CATALOG_TEXT = "Алфавитный каталог";
    
	 
	 private VBox vBoxButtons = new VBox(); // VBox for buttons
	 private VBox vBoxWordCount = new VBox(); // VBox for wordCount label
	 private HBox hBoxText = new HBox(); // HBox for main panel with a table 
	 private Label countLabel = new Label(); // label which displays a number of words
	 private TextField searchInput = new TextField(); 
	 private Menu tableMenu = new Menu(TABLE_MENU_TEXT); //menu for menu bar
	 private Menu aboutProgramm = new Menu(ABOUT_MENU_TEXT);
	 private MenuItem addNewWord = new MenuItem(ADD_NEW_WORD_MENU_TEXT);
	 private MenuItem aboutMenuItem = new MenuItem(ABOUT_MENU_ITEM_TEXT);
	 private MenuItem aboutMenuHelpItem = new MenuItem(ABOUT_HELP_MENU_ITEM_TEXT);
	 private MenuItem alphabetCatalogItem = new MenuItem(TABLE_MENU_ALPHABET_CATALOG_TEXT);
	 
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
		getAboutMenuItem().setOnAction(event -> { // action code
			this.showAboutDialogWindow();
			
		});
		
		aboutMenuHelpItem.setOnAction(event -> {
			this.showHelpDialogWindow();
		});
		
	    lexicon.addSearchListener(this);
	    
	    meaningSearch.setOnAction(event -> {
	        isWordSearch = !meaningSearch.isSelected(); // Toggle the value of isWordSearch
	      });
	    
	    alphabetCatalogItem.setOnAction(event ->  {
	    	lexicon.sortByAlphabet();
	    });
	}


	static void showScene(Stage primaryStage, BorderPane root) {
		// Setting up a scene, JavaFX Scene class is the container for all content 
        // which makes the scene visible in a given pixel size. 
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();	
	}


	BorderPane createUI(Lexicon lexicon) {
		BorderPane root = new BorderPane(); //the root node's size tracks the scene's size and 
        //changes when the stage is resized by a user
		MenuBar menuBar = new MenuBar(tableMenu, aboutProgramm); // creating menuBar with specified items
		
		
		tableMenu.getItems().addAll(addNewWord, alphabetCatalogItem);
		aboutProgramm.getItems().addAll(getAboutMenuItem(), aboutMenuHelpItem); // adding submenus to menu bar
		
		// setting IDs for tableItems, so that TestFX could find them
		addNewWord.setId(ADD_NEW_WORD_MENU_TEXT);
		tableMenu.setId(TABLE_MENU_TEXT);
		aboutProgramm.setId(ABOUT_MENU_TEXT);
		alphabetCatalogItem.setId(TABLE_MENU_ALPHABET_CATALOG_TEXT);
		aboutMenuItem.setId(ABOUT_MENU_ITEM_TEXT);
		aboutMenuHelpItem.setId(ABOUT_HELP_MENU_ITEM_TEXT);
		
		
		gethBoxText().getChildren().add(lexicon.getTable());
		getvBoxButtons().getChildren().add(searchInput);
		getvBoxButtons().getChildren().add(meaningSearch);
		getvBoxWordCount().getChildren().add(gethBoxText());
		gethBoxText().getChildren().add(getvBoxButtons());
		
		searchInput.setPromptText(SEARCH_INPUT_PROMPT_TEXT);
		HBox hBoxCount = new HBox(countLabel);
		hBoxCount.setAlignment(Pos.BOTTOM_LEFT);
		hBoxCount.setPadding(new Insets(0, 0, 5, 0));
		
		countLabel.setText(WORD_COUNT_TEXT + lexicon.getTable().getItems().size());
		root.setCenter(gethBoxText());
		
		BorderPane bottomPane = new BorderPane();
		bottomPane.setLeft(hBoxCount); 
		root.setCenter(gethBoxText());
		root.setTop(menuBar);
		root.setBottom(bottomPane);
        return root;
	}


	 Lexicon initLexicon() {
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
	
	 void showAboutDialogWindow() {
		Dialog<String> aboutWindowDialog = new Dialog<>();
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(new Text("Разговорник - программа для изучения иностранных слов"));
		dialogVbox.getChildren().add(new Text("Разработчик: Nogitsune Youkai"));
	    dialogVbox.getChildren().add(new Text("Версия: 2023-07(1.0)"));
	    aboutWindowDialog.getDialogPane().setContent(dialogVbox);
		aboutWindowDialog.setTitle("О программе");
		aboutWindowDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		aboutWindowDialog.showAndWait();
	}
	 
	void showHelpDialogWindow() {
		Dialog<String> helpWindowDialog = new Dialog<>();
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(new Text("Здесь должна быть справка"));
		helpWindowDialog.getDialogPane().setContent(dialogVbox);
		helpWindowDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		helpWindowDialog.showAndWait();
	}
	
	public TextField getSearchInput() {
		return searchInput;
	}


	public boolean getWordSearch() {
		return isWordSearch;
	}


	public VBox getvBoxButtons() {
		return vBoxButtons;
	}


	public HBox gethBoxText() {
		return hBoxText;
	}


	public VBox getvBoxWordCount() {
		return vBoxWordCount;
	}


	MenuItem getAboutMenuItem() {
		return aboutMenuItem;
	}


	public Menu getTableMenu() {
		return tableMenu;
	}


	public Menu getAboutProgramm() {
		return aboutProgramm;
	}


	public Label getCountLabel() {
		return countLabel;
	}
}
