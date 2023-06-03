import javafx.collections.FXCollections;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import javafx.scene.layout.*;
import java.io.*;
import java.util.*;

public class Lexicon {
	
	private static final double TABLE_HEIGHT = 579;
	private static final double TABLE_WIDTH = 694;
	private static final String FILE_NAME = "WordList.txt";
	
	private String word;
	private String meaning;
	private TableView<Lexicon> table = new TableView<>();
	private TableColumn<Lexicon, String> wordColumn = new TableColumn<Lexicon, String>("Слово");
	private TableColumn<Lexicon, String> translationColumn = new TableColumn<Lexicon, String>("Перевод");
	private ObservableList<Lexicon> lexiconList = FXCollections.observableArrayList();
	private TextField wordInput = new TextField();
	private TextField meaningInput = new TextField();
	private int amountOfWords;
	private boolean isWordAdded;
	
	
	public Lexicon() {
		
	}
	public Lexicon(String word, String meaning) {
		this.word = word;
		this.meaning = meaning;
	}
	
	ObservableList<Lexicon> initDictionary() throws IOException  {
		// This method reads the words and translations from a file and adds them to the table
			File file = new File(FILE_NAME);	
			file.createNewFile();
		    try (Scanner scanner = new Scanner(file)) {
		    	
			    while(scanner.hasNextLine()) { 
				String word = scanner.nextLine();  
				String translation = scanner.nextLine();
				lexiconList.add(new Lexicon(word, translation));} 
		    }
		    
				table.setItems(lexiconList);
				getAmountOfWords();
				return lexiconList;
}
	
	
	public TableView<Lexicon> getTable() {
		return table;
	}
	
	public void setTable(TableView<Lexicon> table) {
		this.table = table;
	}
	
	public TableColumn<Lexicon, String> getWordColumn() {
		return wordColumn;
	}
	
	public void setWordColumn(TableColumn<Lexicon, String> wordColumn) {
		this.wordColumn = wordColumn;
	}
	
	public void setTranslationColumn(TableColumn<Lexicon, String> translationColumn) {
		this.translationColumn = translationColumn;
	}

	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	public void initializeColumns() {
		 wordColumn.setVisible(true);
		 table.getColumns().add(wordColumn);
		 table.getColumns().add(translationColumn);
		 table.setMinHeight(TABLE_HEIGHT);
		 table.setMinWidth(TABLE_WIDTH);
		 wordColumn.setCellValueFactory(new PropertyValueFactory<Lexicon, String>("word")); 
		 translationColumn.setCellValueFactory(new PropertyValueFactory<Lexicon, String>("meaning"));
	}
	
	public void addWordToTable(ActionEvent event) {	
		Dialog<Pair<String, String>> inputDialog = new Dialog<>();
		ButtonType addwordButton = new ButtonType("Добавить", ButtonData.OK_DONE);
	    inputDialog.setTitle("Добавить слово или словосочетание");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        wordInput.setPromptText("Слово");
    	meaningInput.setPromptText("Перевод");
    	gridPane.add(wordInput, 0, 0);
        gridPane.add(meaningInput, 2, 0);
        inputDialog.getDialogPane().setContent(gridPane);
        inputDialog.getDialogPane().getButtonTypes().add(addwordButton);
	    inputDialog.showAndWait();
		Lexicon lexicon = new Lexicon(wordInput.getText(), meaningInput.getText());
		String word = wordInput.getText();
	    String meaning = meaningInput.getText();
	 // checking if a user indeed added a word to a table
	    
		if(!word.isEmpty() && !meaning.isEmpty()) {
		isWordAdded = true;
		table.getItems().add(lexicon);
		} else {
		isWordAdded = false;
		}	
		
		wordInput.clear();
		meaningInput.clear();	
	}
	
	private int getAmountOfWords() {
		amountOfWords = table.getItems().size();
		return amountOfWords;
	}
	
	public boolean isWordAdded() {
		return isWordAdded;
	}
	
	private void searchWord(String word) {
		  // This method filters the lexiconList by the given word and selects the first matching entry
		  // If no entry is found, it returns null
		  Lexicon wordSearch = lexiconList.stream()
		    .filter(l -> l.getWord().equalsIgnoreCase(word)) // case-insensitive comparison
		    .findFirst() // get the first matching entry or an empty optional
		    .orElse(null); // return null if no entry is found, i'm not sure 
		                   // if simply returning null is a good idea, need to check docs
		  
		  table.getSelectionModel().clearSelection();
		  if (wordSearch != null) {
			  table.getSelectionModel().select(wordSearch); // selecting found word in the table
			  table.scrollTo(wordSearch); // scroll to that word in the table
		  }
	}
	
	
	// метод для поиска слов в таблице
	private void searchMeaning(String meaning) {
		  Lexicon meaningSearch = lexiconList.stream()
				  .filter(m -> m.getMeaning().equalsIgnoreCase(meaning)) // doing lazy search
				  .findFirst()
				  .orElse(null);
		
		  table.getSelectionModel().clearSelection();
		  if (meaningSearch != null) {
			  table.getSelectionModel().select(meaningSearch); // selecting found word in the table
			  table.scrollTo(meaningSearch); // scroll to that word in the table
		  }
	}
	
	public void addSearchListener(GUI gui) {
		gui.getSearchInput().textProperty().addListener((observable, oldValue, newValue) -> {
		String searchableWord = gui.getSearchInput().getText(); // getting user's input
		String searchableMeaning = gui.getSearchInput().getText();

	    if (gui.getWordSearch()) {
	        searchWord(searchableWord); // Calling a function for a word search
	      } else {
	        searchMeaning(searchableMeaning); // Otherwise calling a function for a meaning search
	      }
	    });
		//});
	}
}
