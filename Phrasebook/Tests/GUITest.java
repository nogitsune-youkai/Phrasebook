

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.application.Platform;
import javafx.stage.Stage;


public class GUITest extends ApplicationTest {
	GUI guiMock = Mockito.mock(GUI.class);
	GUI gui = new GUI();
    Lexicon lexiconMock = Mockito.mock(Lexicon.class); // Создание мок-объекта Lexicon
    
	
	@BeforeAll
    static void setUpClass() throws Exception {
    	ApplicationTest.launch(Phrasebook.class);
    }
	
	@After
	public void start(Stage stage) throws Exception { 
		gui.start(stage);
        stage.show();
	}
	
	
	@Test
    public void testMenuBar() {
        // check that the menu bar exists and has two menus
        FxAssert.verifyThat("#menuBar", NodeMatchers.isNotNull());
        FxAssert.verifyThat("#menuBar", NodeMatchers.hasChildren(2, ".menu"));

        // check that the first menu has the text "Таблица"
        FxAssert.verifyThat("#tableMenu", LabeledMatchers.hasText("Словарь"));

        // check that the second menu has the text "О программе"
        FxAssert.verifyThat("#aboutProgramm", LabeledMatchers.hasText("О программе"));
    }
	

	
	
	
	
	@Test
	public void VerifyInitializationOfLexiconList() throws InterruptedException, ExecutionException, TimeoutException, IOException {
					
	    CompletableFuture<Void> future = new CompletableFuture<>();
		Platform.runLater(() -> {
				
				
		guiMock.start(new Stage());
		guiMock.initLexicon();
				
		try {
		lexiconMock.initDictionary();
						
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		future.complete(null);    
	});
		future.get(5, TimeUnit.SECONDS);
		// checking if these there was invocation on these functions
		Mockito.verify(guiMock).initLexicon();
		Mockito.verify(lexiconMock).initDictionary();
	}
	
}

