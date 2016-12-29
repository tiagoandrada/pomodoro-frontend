package opendoors.app.desktop.pomodoro;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import opendoors.app.desktop.pomodoro.model.ToDoCard;

public class ToDoCardPane {

	private Button buttonUp;
	private Button buttonDown;
	private HBox hbox;
	private HBox hboxText;
	private ToDoCard card;
	
	private ArrayList<ToDoCardActions> observadores = new ArrayList<ToDoCardActions>();
	
	public HBox getPane(ToDoCard card){
		this.card = card;
		hbox = new HBox();
		hbox.setPrefSize(350, 60);
		hbox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5 5 5 5;");
		
		hboxText = new HBox();
		hboxText.setMinSize(300, 50);
		buttonUp = new Button();
		
		Image imageDown = new Image("file:./resources/arrow_down_min.png");
		Image imageUp = new Image("file:./resources/arrow_up_min.png");

		buttonUp.setGraphic(new ImageView(imageUp));
		buttonUp.setMaxSize(15, 15);
		buttonDown = new Button();
		buttonDown.setGraphic(new ImageView(imageDown));
		
		hboxText.getChildren().add(new Label(card.getDescricao()));
		
		hbox.getChildren().addAll(hboxText, buttonUp, buttonDown);
		addListeners();
		return hbox;
	}
	
	public void addListeners(){
		buttonUp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				for (ToDoCardActions toDoCardActions : observadores) {
					System.out.println("clicado embaixo");
					toDoCardActions.upClicked(card);
				}
				
			}
		});
		
		buttonDown.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				for (ToDoCardActions toDoCardActions : observadores) {
					System.out.println("clicado embaixo");

					toDoCardActions.downClicked(card);
				}
			}
		});
	}
	
	public void setListener(ToDoCardActions listener){
		observadores.add(listener);
	}
	
}
