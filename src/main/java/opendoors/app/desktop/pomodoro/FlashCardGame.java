package opendoors.app.desktop.pomodoro;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import opendoors.app.desktop.pomodoro.controller.DataController;
import opendoors.app.desktop.pomodoro.model.Assunto;
import opendoors.app.desktop.pomodoro.model.FlashCard;
import opendoors.app.desktop.pomodoro.model.Materia;

public class FlashCardGame extends Application {
	
	
	private WebView browser;
	private WebEngine webEngine;
	private ScrollPane scrollPane;
	
	private VBox vBox;
	private HBox hBox;
	private VBox comboBoxVBox;
	
	private Button buttonNextStep;
	private ComboBox<String> comboChoiceMateria;
	private ComboBox<String> comboChoiceAssunto;
	
	private ArrayList<Materia> materias;
	private ArrayList<Assunto> assuntos;
	private ArrayList<FlashCard> flashcards;
	
	private ObservableList<String> materiaOptions;
	private ObservableList<String> assuntoOptions;
	
	private DataController dataController;
	
	private int flashCardId = 0;
	
	private void initComponents(){	
		
		dataController = new DataController();
		
		fillLists();
		
		comboChoiceAssunto = new ComboBox<String>();
		comboChoiceMateria = new ComboBox<String>();
		preencherComboBoxes();
		
		browser = new WebView();
		webEngine = browser.getEngine();
		hBox = new HBox();
		comboBoxVBox = new VBox();
		
		comboBoxVBox.getChildren().add(comboChoiceMateria);
		comboBoxVBox.getChildren().add(comboChoiceAssunto);
		hBox.getChildren().add(comboBoxVBox);
		buttonNextStep =new Button("VERSO");
		buttonNextStep.setPrefSize(100, 50);
		
		hBox.getChildren().add(buttonNextStep);
		hBox.setPadding(new Insets(25, 25, 25, 25));
		hBox.setSpacing(30);
	
	
		scrollPane = new ScrollPane();
		
		browser.setPrefSize(399, 299);
		scrollPane.setContent(browser);
		if(flashcards != null && flashcards.size() > 0){
			webEngine.loadContent(flashcards.get(0).getFrontContent());
		}else{
			webEngine.loadContent("Nenhum FlashCard para este assunto foi encontrado.");

		}

		scrollPane.setMaxSize(400, 300);
		scrollPane.setPrefSize(400, 300);
		
		vBox = new VBox();
		vBox.setPadding(new Insets(25, 25, 25, 25));
		
		vBox.getChildren().add(scrollPane);
		vBox.getChildren().add(hBox);
	}
	
	private void fillLists(){
		materias = (ArrayList<Materia>) dataController.getTodasMaterias();
		assuntos = (ArrayList<Assunto>) dataController.getTodosAssuntosPorMateria(materias.get(0));
		flashcards = (ArrayList<FlashCard>) dataController.getTodosFlashCardsPorAssunto(assuntos.get(0));
	}
	
	private void preencherComboBoxes(){
		
		ArrayList<String> materiasNome = new ArrayList<String>();	
		 for (Materia materia : materias) {
			 materiasNome.add(materia.getNome());
		 }
		 
		 ArrayList<String> assuntosNome = new ArrayList<String>();	
		 for (Assunto assunto : assuntos) {
			 assuntosNome.add(assunto.getNome());
		 }
		 
		 materiaOptions = FXCollections.observableArrayList(materiasNome);
		 assuntoOptions = FXCollections.observableArrayList(assuntosNome);
		 comboChoiceAssunto =  new ComboBox<String>(assuntoOptions);
		 comboChoiceAssunto.setPrefWidth(180);
		 comboChoiceAssunto.getSelectionModel().select(0);
		 comboChoiceMateria = new ComboBox<String>(materiaOptions);
		 comboChoiceMateria.setPrefWidth(180);
		 comboChoiceMateria.getSelectionModel().select(0);
		 
		 comboChoiceMateria.valueProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					int i = 0;
					for (String materia : materiasNome) {
						if(materia.equals(newValue)){
							break;
						}
						i++;
					}
					assuntos.clear();
					assuntos = (ArrayList<Assunto>) dataController.getTodosAssuntosPorMateria(materias.get(i));

					assuntosNome.clear();
					for (Assunto assunto : assuntos) {
						assuntosNome.add(assunto.getNome());
					}
					assuntoOptions.clear();
					assuntoOptions.setAll(assuntosNome);// = FXCollections.observableArrayList(assuntosNome);
					comboChoiceAssunto.getSelectionModel().select(0);
				}
		 });
		 
		 comboChoiceAssunto.valueProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if(!assuntoOptions.isEmpty()){
						int i = 0;
						for (String assunto : assuntosNome) {
							if(assunto.equals(newValue)){
								break;
							}
							i++;
						}
						if(flashcards != null){
							flashcards.clear();
							flashCardId = 0;
						}
						flashcards = (ArrayList<FlashCard>) dataController.getTodosFlashCardsPorAssunto(assuntos.get(i));
						if(flashcards != null && flashcards.size() >= 1){
							webEngine.loadContent(flashcards.get(0).getFrontContent());
						}else{
							webEngine.loadContent("Nenhum FlashCard para este assunto foi encontrado.");
						}
					}

				}
		 });
		 
	}
	
	private void addListener(){
		buttonNextStep.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(buttonNextStep.getText().contains("VERSO")){
					vBox.setStyle("-fx-background-color: #67d4a5");
					buttonNextStep.setText("PRÓXIMO");
					webEngine.loadContent(flashcards.get(flashCardId).getBackContent());
				}else{
					buttonNextStep.setText("VERSO");
					vBox.setStyle("-fx-background-color: #87CEFA");
					if(++flashCardId >= flashcards.size()){
						flashCardId = 0;
					}
					webEngine.loadContent(flashcards.get(flashCardId).getFrontContent());
				}
				
			}
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		initComponents();
		addListener();
		vBox.setStyle("-fx-background-color: #87CEFA");

		Scene scene = new Scene(vBox);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	
	public static void main(String args[]){
		launch(args);
	}
	
}
