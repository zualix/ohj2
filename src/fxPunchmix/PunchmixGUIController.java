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
    
    
    //=====================================
    
    private void save() {
        Dialogs.showMessageDialog("Should save! Not done yet!");
    }
}
