package f1tel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class F1Dashboard extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Dash.fxml"));
		Scene scene = new Scene(root);
		Image f1Icon = new Image(getClass().getResourceAsStream("F1.png"));
		primaryStage.getIcons().add(f1Icon);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setMaximized(true);
	} 
    
}
