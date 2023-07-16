import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

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
	private FilteredList<Lexicon> filteredList;
	private TextField wordInput = new TextField();
	private TextField meaningInput = new TextField();
	private int amountOfWords;
	private boolean isWordAdded;
	private String chosenLetter;
	
	
	public Lexicon() {
	}
	public Lexicon(String word, String meaning) {
		this.word = word;
		this.meaning = meaning;
	}
	
	ObservableList<Lexicon> initDictionary() throws IOException  {
		// This method reads the words and translations from a file and adds them to the table
			File file = new File(FILE_NAME);	
			// checking if file exist, if not, show a message to user
			if(file.exists() == false) {
				Dialog<String> fileNotFoundDialog = new Dialog<>();	
				VBox dialogVbox = new VBox(20);
				dialogVbox.getChildren().add(new Text("Файл WordList.txt не найден, "
						 + "он должен находится в одной и той же папке, что и .exe"));
				fileNotFoundDialog.getDialogPane().setContent(dialogVbox);
				fileNotFoundDialog.setTitle("Файл не найден");
				fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
				fileNotFoundDialog.showAndWait();
			};
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
	

	public String getWord() {
		return word;
	}
	
	
	public String getMeaning() {
		return meaning;
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
		System.gc(); // free memory
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
	
	// function for searching words in the table
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
	
	
	// function for searching English meanings of words in the table
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
	}
	
	public void sortByAlphabet() {
		List<String> letters = List.of("Показывать все слова","А", "Б", "В", "Г", "Д", "Е",
				                                         "Ё", "Ж", "З", "И", "Й", "К", 
				                                         "Л", "М", "Н", "О", "П", "Р", 
				                                         "С", "Т", "У", "Ф", "Х", "Ц", 
                                                         "Ч", "Ш", "Щ", "Э", "Ю", "Я");	
		// Create a dialog box to let the user choose a letter          
		ChoiceDialog<String> letterChoiceDialog = new ChoiceDialog<String>("Показывать все слова", letters);
	    letterChoiceDialog.setTitle("Выберите букву");
	    // If the user does not choose a letter, show the whole table
	    if(!letterChoiceDialog.showAndWait().isPresent()) {
	    	table.setItems(lexiconList);
	    } 
	    // Get the chosen letter from the dialog box
		chosenLetter = letterChoiceDialog.getResult(); 
		
		// If the chosen letter is null, show the whole table and return
		if(chosenLetter == null || chosenLetter == letterChoiceDialog.getDefaultChoice()) {
			table.setItems(lexiconList);	
			return;
		}
		// Filter the lexicon list by the first letter of the word 
		// and show the filtered list in the table
		filteredList  = lexiconList.filtered(p -> p.getWord().charAt(0) 
				== chosenLetter.charAt(0)); 
		table.setItems(filteredList);	
	}
}
