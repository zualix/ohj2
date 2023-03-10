package fxPunchmix;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import punchmix.Punchmix;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;



public class PunchmixMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			final FXMLLoader ldr = new FXMLLoader(getClass().getResource("PunchmixGUIView.fxml"));
			final Pane root = (Pane)ldr.load();
			final PunchmixGUIController punchCtrl = (PunchmixGUIController)ldr.getController();
			
			final Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("punchmix.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("PunchmixGUIView.fxml"));
			//Scene scene = new Scene(root,850,400);
			//scene.getStylesheets().add(getClass().getResource("punchmix.css").toExternalForm());
			
			primaryStage.setOnCloseRequest((event) -> {
				if ( !punchCtrl.canClose() ) event.consume();
			});
			
			Punchmix punch = new Punchmix();
			punchCtrl.setPunch(punch);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
