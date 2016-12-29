package opendoors.app.desktop.pomodoro;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application{

	private PomodorTimerProgress timer;
	private FlashCardPane flashCardPane;
	private VBox verticalRootBox;
	
	private void initComponents(){
		
		flashCardPane = new FlashCardPane();
    	timer = new PomodorTimerProgress(25);
		
		verticalRootBox = new VBox();
		verticalRootBox.setStyle("-fx-background-color: #87CEFA");

		HBox horizontalTimerPane = new HBox();
		timer.getIndicator().setPrefSize(200, 200);
		horizontalTimerPane.getChildren().add(timer.getIndicator());
		horizontalTimerPane.setPadding(new Insets(15, 75, 0, 75));
				
		verticalRootBox.getChildren().add(horizontalTimerPane);
		verticalRootBox.getChildren().add(flashCardPane.getPane());
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		initComponents();
		
		primaryStage.setTitle("ConcurseiroPomodoro");
		primaryStage.setAlwaysOnTop(true);
		
		
		Scene scene = new Scene(verticalRootBox, 350, 680);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		
		new Thread(timer).start();
		
		primaryStage.show();
		
	}
	
	public static void main (String args[]){
		launch(args);
	}

}

