package fxPunchmix;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;

public class DrinkDialogController {
    @FXML
    void handleSave() {
        save();
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
    void handleDelete() {
    	delete();
    }

    
    
    //========================================
    
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
	
    
}
