package fxPunchmix;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import punchmix.Drink;
import punchmix.Ingredient;
import punchmix.Mix;
import punchmix.Punchmix;
import side.*;


public class PunchmixGUIController implements Initializable{
	

    @FXML private ListChooser<Drink> chooserDrink;
    @FXML private Label labelError;
    @FXML private TextField searcCrit;
    @FXML private ScrollPane panelDrink;
	
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        start();      
    }

    
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
       // TulostusController tulostusCtrl = TulostusController.tulosta(null); 
       //tulostaValitut(tulostusCtrl.getTextArea()); 

    }

    @FXML
    void handleSearch() {
        //search();
        String crit = searcCrit.getText(); 
        if ( crit.isEmpty() )
            showError(null);
        else
        	showError("Ei osata vielä hakea : " + crit);

    }
    
    //===========================================================================================================
    private Punchmix punch;
    private Drink drinkPos;
    private TextArea areaDrink = new TextArea();
    
   
    protected void start() {
        panelDrink.setContent(areaDrink);
        areaDrink.setFont(new Font("Courier New", 12));
        panelDrink.setFitToHeight(true);
        
        chooserDrink.clear();
        chooserDrink.addSelectionListener(e -> showDrink());
    }

    private void showError(String err) {
        if ( err == null || err.isEmpty() ) {
        	labelError.setText("");
        	labelError.getStyleClass().removeAll("err");
            return;
        }
        labelError.setText(err);
        labelError.getStyleClass().add("err");
    }
    
    
    private void save() {
        Dialogs.showMessageDialog("Should save! Not done yet!");
    }
   
    public boolean canClose() {
    	save();
    	return true;
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
    
    

    protected void showDrink() {
    	drinkPos = chooserDrink.getSelectedObject();
    	
    	if (drinkPos == null) return;
    	
    	areaDrink.setText("");
    	try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaDrink)) {
    		drinkPos.print(os);
    	}
    }
    
    public void print(PrintStream os, final Drink drink) {
        os.println("----------------------------------------------");
        drink.print(os);
        os.println("----------------------------------------------");

    }
    

    public void printSelected(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Printing all the drinks");
            for (int i = 0; i < punch.getDrinks(); i++) {
                Drink drink =  punch.giveDrink(i);
                print(os, drink);
                os.println("\n\n");
            }
        }
    }
    
    private void search(int idd) {
    	chooserDrink.clear();
    	
    	int index = 0;
    	for (int i = 0; i < punch.getDrinks(); i++) {
    		Drink drink = punch.giveDrink(i);
    		if (drink.getIdd() == idd) index = i;
    		chooserDrink.add(drink.getName(), drink);
    	}
    	chooserDrink.setSelectedIndex(index);
    }
    
    
    
    protected void newDrinkEx() {
    	Drink extra = new Drink();
    	extra.recIdd();
    	extra.someMix();
    	
    	try {
    		punch.addMix(extra);
    	} catch (SailoException e) {
			Dialogs.showMessageDialog("Problem to crate new " + e.getMessage());
			return;
		}
    	search(extra.getIdd());
    }
    
    public void setPunch(Punchmix punch){
    	this.punch = punch;
    	showDrink();
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
