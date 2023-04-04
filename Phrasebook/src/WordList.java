import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WordList {
	
	public WordList() {
		
	}
	
	public ObservableList<Lexicon> getList() {
		ObservableList<Lexicon> dictionary = FXCollections.observableArrayList();
		dictionary.add(new Lexicon("временное слово", "temp word"));
		return dictionary;
	}
}
