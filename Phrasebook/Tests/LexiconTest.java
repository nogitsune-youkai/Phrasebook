

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Platform;
import javafx.stage.Stage;

class LexiconTest extends ApplicationTest {

	Lexicon lexiconMock = Mockito.mock(Lexicon.class); // Creating mock object Lexicon
	Lexicon lexicon = new Lexicon();
	GUI gui = new GUI();
	
	@BeforeAll
    static void setUpClass() throws Exception {
    	ApplicationTest.launch(Phrasebook.class); // launch app thread
    }
	
	@After
	public void start(Stage stage) throws Exception { 
		gui.start(stage);
        stage.show();
	}
	
	@Test
	void testAddSearchListener() throws InterruptedException, ExecutionException, TimeoutException {
		CompletableFuture<Void> future = new CompletableFuture<>();
		Platform.runLater(() -> {
			lexiconMock.addSearchListener(gui);
			future.complete(null); // finish the program
		});
		future.get(5, TimeUnit.SECONDS);
		Mockito.verify(lexiconMock).addSearchListener(gui);
	}

	@Test
	void testSortByAlphabet() throws InterruptedException, ExecutionException, TimeoutException {
		CompletableFuture<Void> future = new CompletableFuture<>();
		Platform.runLater(() -> {
			lexiconMock.sortByAlphabet();
			future.complete(null); // finish the program
		});
		future.get(5, TimeUnit.SECONDS);
		Mockito.verify(lexiconMock).sortByAlphabet(); // check if function was invoked
	}
	
}
