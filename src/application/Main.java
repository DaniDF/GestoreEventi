package application;

import it.Daniele.gestore.BackwardCompatibility.TextMode;
import it.Daniele.gestore.calendario.controller.Controller;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;


public class Main extends Application {
	private static String[] args;
	
	@Override
	public void init() {		
		if(System.console() != null) {
			TextMode.main(Main.args);
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {			
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setTitle("Gestore Eventi");
			
			if(primaryScreenBounds.getWidth() < 400 || primaryScreenBounds.getHeight() < 400) {
				throw new ScreenDimensionException();
			}
			
			primaryStage.setWidth(primaryScreenBounds.getWidth()*0.5);
			primaryStage.setHeight(primaryScreenBounds.getHeight()*0.75);
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("file:img/icon.png"));
			
			MySelectView selPane = new MySelectView(primaryStage);
			Scene selScene = new Scene(selPane);
			selScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(selScene);
			primaryStage.show();
			
		} catch(Exception e) {
			Controller.myAlert(AlertType.ERROR, e.getClass().toString().replace("class", ""), ButtonType.CLOSE);
		}
	}
	
	@Override
	public void stop() {
		
	}
	
	public static void main(String[] args) {
		Main.args = args;
		launch(args);
	}
}
