

import java.io.IOException;
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




public class GUITest extends ApplicationTest {
	
	GUI guiMock = Mockito.mock(GUI.class);
	GUI gui = new GUI();
    Lexicon lexiconMock = Mockito.mock(Lexicon.class); // Creating mock object Lexicon
    
	
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
		// checking all items in menu bar
        for(int i = 0; i >= gui.getTableMenu().getItems().size(); i++) {
        String tableMenuitems = gui.getTableMenu().getItems().get(i).getId();
        Assertions.assertEquals("Словарь", tableMenuitems);
        Assertions.assertEquals("Добавить слово", tableMenuitems); 
        }   
        
        for(int i = 0; i >= gui.getTableMenu().getItems().size(); i++) {
        String aboutProgramMenuItems = gui.getTableMenu().getItems().get(i).getId();
        Assertions.assertEquals("Алфавитный каталог", aboutProgramMenuItems);
        Assertions.assertEquals("О программе", aboutProgramMenuItems);
        Assertions.assertEquals("О разработчике", aboutProgramMenuItems);
        Assertions.assertEquals("Справка", aboutProgramMenuItems);
        }
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
		future.complete(null); // finish the program
	});
		future.get(5, TimeUnit.SECONDS);
		// checking if these there was invocation on these functions
		Mockito.verify(guiMock).initLexicon();
		Mockito.verify(lexiconMock).initDictionary();
	}
	
	@Test
	public void showAboutDialogWindowTest()  throws InterruptedException, ExecutionException, TimeoutException, IOException {
		CompletableFuture<Void> future = new CompletableFuture<>();
		Platform.runLater(() -> {
		guiMock.showAboutDialogWindow();
		future.complete(null); // finish the program
		});
		future.get(5, TimeUnit.SECONDS);
		Mockito.verify(guiMock).showAboutDialogWindow();;
	}
}

