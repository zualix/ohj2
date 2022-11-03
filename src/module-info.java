module ht {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens fxPunchmix to javafx.graphics, javafx.fxml;
}
