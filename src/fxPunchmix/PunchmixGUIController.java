package fxPunchmix;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;


public class PunchmixGUIController {
    @FXML
    void handleSave() {
        save();
    }
    
    
    //=====================================
    
    private void save() {
        Dialogs.showMessageDialog("Should save! Not done yet!");
    }
}
