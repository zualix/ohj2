package fxPunchmix;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;


public class PunchmixGUIController {
    @FXML
    void handleSave() {
        save();
    }
    
    @FXML
    void handleEditDrink() {
        ModalController.showModal(PunchmixGUIController.class.getResource("DrinkDialogView.fxml"), "Drink", null, "");
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
        ModalController.showModal(PunchmixGUIController.class.getResource("IngDialogView.fxml"), "Ingredient", null, "");
    }

    @FXML
    void handleNewDrink() {
        ModalController.showModal(PunchmixGUIController.class.getResource("DrinkDialogView.fxml"), "Drink", null, "");
    }

    @FXML
    void handleNewIng() {
        ModalController.showModal(PunchmixGUIController.class.getResource("NewIngDialogView.fxml"), "Ingredient", null, "");
    }

    @FXML
    void handlePrint() {
        print();
    }

    @FXML
    void handleSearch() {
        search();
    }
    
    //===================================== 
    
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
    
    
}
