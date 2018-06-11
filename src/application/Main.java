package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import it.Daniele.gestore.calendario.controller.Controller;
import it.Daniele.gestore.calendario.controller.MyController;
import it.Daniele.gestore.calendario.persister.BadFileFormatException;
import it.Daniele.gestore.calendario.persister.MyPersister;
import it.Daniele.gestore.calendario.persister.Persister;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;


public class Main extends Application {	
	@Override
	public void start(Stage primaryStage) {
		try {			
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setTitle("Gestore Eventi");
			primaryStage.setWidth(primaryScreenBounds.getWidth()*0.5);
			primaryStage.setHeight(primaryScreenBounds.getHeight()*0.75);
			primaryStage.setResizable(false);
			
			FileChooser inFile = new FileChooser();
			inFile.setTitle("Select file");
			inFile.setSelectedExtensionFilter(new ExtensionFilter(".txt", ".dat"));
			
			File suorceFile = inFile.showOpenDialog(primaryStage);
			
			Persister persister = null;
			Controller controller = null;
			
			try {
				//TODO aggiungere possibilità scelta multipla
				persister = new MyPersister(new FileReader(suorceFile));
				List<Persister> allPersisters = new ArrayList<>();
				allPersisters.add(persister);
				controller = new MyController(allPersisters);
				
				GridPane root = new MyGridPaneView(controller);			
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (FileNotFoundException | BadFileFormatException e) {
				MyController.myAlert(AlertType.ERROR, e.toString(), ButtonType.CLOSE);
			} catch (NullPointerException e) {
				MyController.myAlert(AlertType.WARNING, "Can't continue without a file!", ButtonType.OK);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
