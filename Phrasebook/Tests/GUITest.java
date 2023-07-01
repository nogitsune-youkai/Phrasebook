

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javafx.application.Platform;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class GUITest {
	
	@BeforeAll
	static void initJfxRuntime() throws Exception {
	    Platform.startup(() -> {}); // mandatory call for starting JavaFX runtime
	}

	@Test
	void testStart() {
		
		Platform.runLater(() -> {
		GUI gui = new GUI();
        Stage stage = new Stage();
        
        
        Lexicon lexicon = Mockito.mock(Lexicon.class);
        
        gui.start(stage);
        
        // Getting BorderPane
        BorderPane root = (BorderPane) stage.getScene().getRoot();
        
        // Checking BorderPane for null value
        Assertions.assertNotNull(root);
        
        // Checking if all elements are present in root
        Assertions.assertEquals(gui.getvBoxButtons(), root.getLeft());
        Assertions.assertEquals(gui.gethBoxText(), root.getCenter());
        Assertions.assertEquals(gui.getvBoxWordCount(), root.getRight());
        
        // Getting MenuBar
        MenuBar menuBar = (MenuBar) stage.getScene().lookup("#menuBar");
        
        Assertions.assertNotNull(menuBar);
        
        MenuItem aboutMenuItem = gui.getAboutMenuItem();
        
        Assertions.assertNotNull(aboutMenuItem);
        
        // force an action on item
        aboutMenuItem.fire();    
        
        // checking if there was invocation on aboutMenuItem
        Mockito.verify(aboutMenuItem);
		});
	}

}
