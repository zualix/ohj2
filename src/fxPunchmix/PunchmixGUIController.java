package fxPunchmix;

import java.io.IOException;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PunchmixGUIController {
    @FXML
    void handleSave() {
        save();
    }
    
    @FXML
    void handleEditDrink() {
        editDrink();
    }
    
    @FXML
    void handleAbout() {
        about();
    }

    @FXML
    void handleClose() {
        close();
    }

    @FXML
    void handleDel() {
        delete();
    }

    @FXML
    void handleEditIng() {
        editIng();
    }

    @FXML
    void handleNewDrink() {
        newDrink();
    }

    @FXML
    void handleNewIng() {
        newIng();
    }

    @FXML
    void handlePrint() {
        print();
    }

    @FXML
    void handleSearch() {
        search();
    }
    
    //===========================================================================================================
    
    private void save() {
        Dialogs.showMessageDialog("Should save! Not done yet!");
    }
    
    private void about() {
        Dialogs.showMessageDialog("Not done yet!");
    }
    
    private void close() {
        Dialogs.showMessageDialog("Not done yet!");
    }
    
    private void delete() {
        Dialogs.showMessageDialog("Not done yet!");
    }
    
    private void print() {
        Dialogs.showMessageDialog("Not done yet!");
    }
    
    private void search() {
        Dialogs.showMessageDialog("Not done yet!");
    }
    
    private void newIng() {
        //ModalController.showModal(PunchmixGUIController.class.getResource("NewIngDialogView.fxml"), "New Ingredient", null, "");
    	
    	Stage secStage = new Stage();
    	try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("NewIngDialogView.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("punchmix.css").toExternalForm());
			secStage.setScene(scene);
			secStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    private void newDrink() {
        //ModalController.showModal(PunchmixGUIController.class.getResource("DrinkDialogView.fxml"), "Drink", null, "");
        
    	Stage secStage = new Stage();
    	try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("DrinkDialogView.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("punchmix.css").toExternalForm());
			secStage.setScene(scene);
			secStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
        

    }
    
    private void editIng() {
        //ModalController.showModal(PunchmixGUIController.class.getResource("IngDialogView.fxml"), "Ingredient", null, "");
    	Stage secStage = new Stage();
    	try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("IngDialogView.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("punchmix.css").toExternalForm());
			secStage.setScene(scene);
			secStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    private void editDrink() {
        //ModalController.showModal(PunchmixGUIController.class.getResource("DrinkDialogView.fxml"), "Drink", null, "");
    	Stage secStage = new Stage();
    	try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("DrinkDialogView.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("punchmix.css").toExternalForm());
			secStage.setScene(scene);
			secStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
}
