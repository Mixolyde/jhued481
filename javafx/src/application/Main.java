package application;
	
import db.DerbyManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent retailView = FXMLLoader.load(getClass().getResource("RetailSystemMain.fxml"));
			Scene scene = new Scene(retailView);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Retail System Management");
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
				@Override
				public void handle(WindowEvent arg0) {
					// TODO Auto-generated method stub
					Main.onCloseAction();
				}
				
			});
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		DerbyManager.getInstance().printData();
		launch(args);
	}
	
	public static void onCloseAction(){
		System.out.println("Printing final database dump.");
		try {
			DerbyManager.getInstance().printData();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		System.exit(0);
	}

}
