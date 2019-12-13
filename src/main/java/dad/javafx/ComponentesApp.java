package dad.javafx;

import dad.javafx.components.ListSelector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ComponentesApp extends Application {
	
	private ListSelector<String> listSelector;

	@Override
	public void start(Stage primaryStage) throws Exception {
		listSelector = new ListSelector<String>();
		listSelector.setLeftLabel("Jugadores");
		listSelector.setRightLabel("Participantes");
		listSelector.getLeftItems().addAll("Perico", "Palotes", "Menganita", "Fulanito");
		
		Scene scene = new Scene(listSelector);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Componentes");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
