package fxPunchmix;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;

public class NewIngDialogController {
    
    @FXML
    void handleAbout() {
        info();
    }

    @FXML
    void handleCancel() {
        cancel();
    }

    @FXML
    void handleClose() {
        close();
    }

    @FXML
    void handleSave() {
        save();
    }
    
    //==============================
    
    private void save() {
        Dialogs.showMessageDialog("Not done yet!");
    }
    
    private void close() {
        Dialogs.showMessageDialog("Not done yet!");
    }
    private void cancel() {
        Dialogs.showMessageDialog("Not done yet!");
    }
    private void info() {
        Dialogs.showMessageDialog("Not done yet!");
    }
    

}
