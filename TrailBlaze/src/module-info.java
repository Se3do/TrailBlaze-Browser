module Browser {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.web;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
