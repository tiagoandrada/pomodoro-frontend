package opendoors.app.desktop.pomodoro.test;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import opendoors.app.desktop.pomodoro.ToDoCardActions;
import opendoors.app.desktop.pomodoro.ToDoCardPane;
import opendoors.app.desktop.pomodoro.model.Status;
import opendoors.app.desktop.pomodoro.model.ToDoCard;

public class ToDoListTest extends Application{
	
	private ListView<ToDoCard> doingListView;
	private ListView<ToDoCard> todoListView;
	private ListView<ToDoCard> doneListView;

	private Stage window;
	private ArrayList<ToDoCard> cards;
	private ArrayList<ToDoCard> cardsDoing;
	private ArrayList<ToDoCard> cardsTodo;
	private ArrayList<ToDoCard> cardsDone;

	private ObservableList<ToDoCard> dataDoing;
	private ObservableList<ToDoCard> dataTodo;
	private ObservableList<ToDoCard> dataDone;
	
	
	private void initComponents(){
		cards = new ArrayList<ToDoCard>();
		cardsDoing = new ArrayList<ToDoCard>();
		cardsTodo = new ArrayList<ToDoCard>();
		cardsDone = new ArrayList<ToDoCard>();

		
		ToDoCard card1 = new ToDoCard("Assistir aula 02 da professora Flavia Rita", 0, Status.DOING);
		ToDoCard card2 = new ToDoCard("Assistir aula 03 da professora Flavia Rita", 1, Status.DONE);
		ToDoCard card3 = new ToDoCard("Assistir aula 04 da professora Flavia Rita", 2, Status.TODO);
		ToDoCard card4 = new ToDoCard("Assistir aula 05 da professora Flavia Rita", 3, Status.TODO);
		
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		
		doingListView = new ListView<ToDoCard>();
		todoListView = new ListView<ToDoCard>();
		doneListView = new ListView<ToDoCard>();
		
		VBox box = new VBox();
		Scene scene = new Scene(box, 400, 400);
		window.setScene(scene);
		window.setTitle("ListViewSample");
		
		for (ToDoCard toDoCard : cards) {
			if(toDoCard.getStatus() == Status.DOING){
				cardsDoing.add(toDoCard);
			}else if(toDoCard.getStatus() == Status.DONE){
				cardsDone.add(toDoCard);
			}if(toDoCard.getStatus() == Status.TODO){
				cardsTodo.add(toDoCard);
			}
		}
		
		dataDoing = FXCollections.observableArrayList(cardsDoing);
		dataDone =  FXCollections.observableArrayList(cardsDone);
		dataTodo =  FXCollections.observableArrayList(cardsTodo);
		
		box.getChildren().add(new Label("Tarefas Sendo Executadas"));
		box.getChildren().addAll(doingListView);
		box.getChildren().add(new Label("Tarefas a Serem Executadas"));
		box.getChildren().addAll(todoListView);
		box.getChildren().add(new Label("Tarefas Finalizadas"));
		box.getChildren().addAll(doneListView);
		
		
		doingListView.setCellFactory(new Callback<ListView<ToDoCard>, 
	            ListCell<ToDoCard>>() {
	                @Override 
	                public ListCell<ToDoCard> call(ListView<ToDoCard> list) {
	                    return new ColorRectCell();
	                }
	            }
	        );
		
		doneListView.setCellFactory(new Callback<ListView<ToDoCard>, 
	            ListCell<ToDoCard>>() {
	                @Override 
	                public ListCell<ToDoCard> call(ListView<ToDoCard> list) {
	                    return new ColorRectCell();
	                }
	            }
	        );
		
		todoListView.setCellFactory(new Callback<ListView<ToDoCard>, 
	            ListCell<ToDoCard>>() {
	                @Override 
	                public ListCell<ToDoCard> call(ListView<ToDoCard> list) {
	                    return new ColorRectCell();
	                }
	            }
	        );
		
		
		doingListView.setItems(dataDoing);
		doneListView.setItems(dataDone);
		todoListView.setItems(dataTodo);

		
		doingListView.getSelectionModel().select(0);
		doingListView.autosize();
		
		window.show();
	    
	}
	    
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.window = primaryStage;
		initComponents();
	}
	
	public static void main(String args[]){
		launch(args);
	}
	
	private class ColorRectCell extends ListCell<ToDoCard> implements ToDoCardActions {
		
	    @Override
	    public void updateItem(ToDoCard item, boolean empty) {
	        super.updateItem(item, empty);
	        HBox hbox = new HBox();
	        
	        if (item != null) {
	        	ToDoCardPane pane = new ToDoCardPane();
	        	hbox.getChildren().add(pane.getPane(item));
	        	pane.setListener(this);
	        	setGraphic(hbox);
	        }
	    }
	    
	    @Override
		public void upClicked(ToDoCard card) {
	    	int index = card.getIndex();
	    	cards.get(index).setIndex(index - 1);

	    	cards.get(index-1).setIndex(index);
	    	
	    	Comparator<ToDoCard> comp = new Comparator<ToDoCard>() {
				
				@Override
				public int compare(ToDoCard arg0, ToDoCard arg1) {
					if (arg0.getIndex() > arg1.getIndex())
			            return 1;
			        if (arg0.getIndex() < arg1.getIndex())
			            return -1;
			        return 0;
				}
			};
	    	
			cards.sort(comp);
			
	    	dataDoing.clear();
	    	dataDoing = FXCollections.observableArrayList(cards);
			doingListView.setItems(dataDoing);
			doingListView.getSelectionModel().select(index - 1);

			
		}

		@Override
		public void downClicked(ToDoCard card) {
			int index = card.getIndex();
	    	cards.get(index).setIndex(index + 1);
	    	cards.get(index+1).setIndex(index);

	    	
	    	Comparator<ToDoCard> comp = new Comparator<ToDoCard>() {
				
				@Override
				public int compare(ToDoCard arg0, ToDoCard arg1) {
					if (arg0.getIndex() > arg1.getIndex())
			            return 1;
			        if (arg0.getIndex() < arg1.getIndex())
			            return -1;
			        return 0;
				}
			};
	    	
			cards.sort(comp);
			
			dataDoing.clear();
			dataDoing = FXCollections.observableArrayList(cards);
			doingListView.setItems(dataDoing);
			doingListView.getSelectionModel().select(index + 1);

		}
		
	}
	
}
