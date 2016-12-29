package opendoors.app.desktop.pomodoro;


import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import opendoors.app.desktop.pomodoro.controller.DataController;
import opendoors.app.desktop.pomodoro.model.Assunto;
import opendoors.app.desktop.pomodoro.model.Materia;

public class FlashCardPane {
	
	private static VBox verticalRootBox;
	private Button buttonPreencherSalvarCard;
	private Button buttonDescartar;
	private HBox horizontalButtonBox;
	private Label labelTitle;

	private HTMLEditor htmlEditorFrente;
	private HTMLEditor htmlEditorVerso;
	private HTMLEditor htmlEditorFicha;
	
	private ToggleButton buttonFlashCard;
	private ToggleButton buttonFicha;
	private ToggleGroup toggleGroup;
	
	private HBox horizontalButtonsFlashPane;
	private StackPane htmlEditorPane;
	private VBox comboBoxVerticalPane;
	
	private ComboBox<String> choiceMateria;
	private ComboBox<String> choiceAssunto;
	
	private ObservableList<String> materiaOptions;
	private ObservableList<String> assuntoOptions;
		
	private DataController dataController;
	
	public FlashCardPane(){
		dataController = new DataController();
		initComponents();		
        addListeners();
	}
	
	
	public void initComponents(){
		 
		 buttonPreencherSalvarCard = new Button("Preencher Verso");
	     buttonDescartar = new Button("Descartar");

	     buttonPreencherSalvarCard.setPrefWidth(350 / 2);
	     buttonDescartar.setPrefWidth(350 / 2);
	     
	     verticalRootBox = new VBox();
	     verticalRootBox.setPadding(new Insets(15, 12, 15, 12));
		 verticalRootBox.setSpacing(10);
		 verticalRootBox.setStyle("-fx-background-color: #87CEFA");
		 
		 buttonFlashCard = new ToggleButton("Flashcard");
		 buttonFicha = new ToggleButton("Ficha");
		 toggleGroup = new ToggleGroup();
		 buttonFlashCard.setToggleGroup(toggleGroup);
		 buttonFicha.setToggleGroup(toggleGroup);
		 buttonFlashCard.setPrefWidth(350/2);
		 buttonFicha.setPrefWidth(350/2);
		 toggleGroup.selectToggle(buttonFlashCard);
		 
		 horizontalButtonsFlashPane = new HBox();
		 horizontalButtonsFlashPane.getChildren().add(buttonFlashCard);
		 horizontalButtonsFlashPane.getChildren().add(buttonFicha);
		 		 
		 horizontalButtonBox = new HBox();
		 horizontalButtonBox.setPadding(new Insets(15, 12, 15, 12));
		 horizontalButtonBox.setSpacing(30);
		 
		 htmlEditorFrente = new HTMLEditor();
		 htmlEditorFrente.setPrefHeight(350);
		 htmlEditorFrente.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		 
		 htmlEditorVerso = new HTMLEditor();
		 htmlEditorVerso.setPrefHeight(350);
		 htmlEditorVerso.setStyle("-fx-border-color: black; -fx-border-width: 2;");

		 
		 htmlEditorFicha = new HTMLEditor();
		 htmlEditorFicha.setPrefHeight(350);
		 htmlEditorFicha.setStyle("-fx-border-color: black; -fx-border-width: 2;");

		 
		 htmlEditorPane = new StackPane();
		 htmlEditorPane.getChildren().add(htmlEditorFicha);
		 htmlEditorPane.getChildren().add(htmlEditorFrente);
		 htmlEditorPane.getChildren().add(htmlEditorVerso);
		 htmlEditorVerso.setVisible(false); //Inicia com htmlEditor frente mostrndo
		 htmlEditorFicha.setVisible(false);

		 ArrayList<Materia> materias = (ArrayList<Materia>) dataController.getTodasMaterias();
		 ArrayList<String> materiasNome = new ArrayList<String>();	
		 for (Materia materia : materias) {
			 materiasNome.add(materia.getNome());
		 }
		 
		 ArrayList<Assunto> assuntos = (ArrayList<Assunto>) dataController.getTodosAssuntosPorMateria(materias.get(0));
		 ArrayList<String> assuntosNome = new ArrayList<String>();	
		 for (Assunto assunto : assuntos) {
			 assuntosNome.add(assunto.getNome());
		 }
		 
		 materiaOptions = FXCollections.observableArrayList(materiasNome);
		 assuntoOptions = FXCollections.observableArrayList(assuntosNome);
		 choiceAssunto =  new ComboBox<String>(assuntoOptions);
		 choiceAssunto.setPrefWidth(180);
		 choiceAssunto.getSelectionModel().select(0);
		 choiceMateria = new ComboBox<String>(materiaOptions);
		 choiceMateria.setPrefWidth(180);
		 choiceMateria.getSelectionModel().select(0);
		 
		 choiceMateria.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				int i = 0;
				for (String materia : materiasNome) {
					if(materia.equals(newValue)){
						break;
					}
					i++;
				}
				ArrayList<Assunto> assuntos = (ArrayList<Assunto>) dataController.getTodosAssuntosPorMateria(materias.get(i));
				ArrayList<String> assuntosNome = new ArrayList<String>();	
				for (Assunto assunto : assuntos) {
					assuntosNome.add(assunto.getNome());
				}
				assuntoOptions.clear();
				assuntoOptions.setAll(assuntosNome);// = FXCollections.observableArrayList(assuntosNome);
				choiceAssunto.getSelectionModel().select(0);
			}
		  });
		 
		 
		 comboBoxVerticalPane = new VBox();
		 comboBoxVerticalPane.getChildren().add(choiceMateria);
		 comboBoxVerticalPane.getChildren().add(choiceAssunto);
		 
		 labelTitle = new Label("FLASHCARD - FRENTE");
		 labelTitle.setStyle("-fx-font: 16px \"Serif\"; -fx-text-fill: black; -fx-stroke: white; -fx-stroke-width: 1");

		 verticalRootBox.getChildren().add(horizontalButtonsFlashPane);
		 verticalRootBox.getChildren().add(labelTitle);
         verticalRootBox.getChildren().add(htmlEditorPane);
         horizontalButtonBox.getChildren().add(comboBoxVerticalPane);
         horizontalButtonBox.getChildren().add(buttonPreencherSalvarCard);
         verticalRootBox.getChildren().add(horizontalButtonBox);
		 
	}
	
	public void addListeners(){
		
		buttonFicha.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				displayFicha();
				showButtonOptionsOnFicha();
			}
		});
		
		buttonFlashCard.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				displayFlashCardFrente();
				showComboboxOptions();
			}
		});
		
		buttonPreencherSalvarCard.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(buttonPreencherSalvarCard.getText().contains("Salvar")){
					if(buttonFicha.isSelected()){
						dataController.saveFicha(choiceMateria.getValue(), choiceAssunto.getValue(), htmlEditorFicha.getHtmlText());
						showButtonOptionsOnFicha();
					}else{
						dataController.saveFlashCard(choiceMateria.getValue(), choiceAssunto.getValue(), htmlEditorFrente.getHtmlText(), htmlEditorVerso.getHtmlText());
						displayFlashCardFrente();
						showComboboxOptions();
					}
					resetHtmlEditor();
				}else{
					if(buttonFlashCard.isSelected()){
						displayFlashCardVerso();
					}
				}
			}
		});
		        
        buttonDescartar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				resetHtmlEditor();
				displayFlashCardFrente();
				showComboboxOptions();
				
			}
		});
	}
		
	public Node getPane(){
        return verticalRootBox;
	}
	
	private void displayFlashCardVerso(){
		labelTitle.setText("FLASHCARD - VERSO");
		horizontalButtonBox.getChildren().remove(comboBoxVerticalPane);
		horizontalButtonBox.getChildren().add(buttonDescartar);
		buttonPreencherSalvarCard.setText("Salvar");
		htmlEditorFrente.setVisible(false);
		htmlEditorVerso.setVisible(true);
	}
	
	private void displayFlashCardFrente(){
		labelTitle.setText("FLASHCARD - FRENTE");
		htmlEditorFrente.setVisible(true);
		htmlEditorVerso.setVisible(false);
		htmlEditorFicha.setVisible(false);
	}
	
	private void displayFicha(){
		labelTitle.setText("FICHA");
		htmlEditorFrente.setVisible(false);
		htmlEditorVerso.setVisible(false);
		htmlEditorFicha.setVisible(true);
	}
	
	private void resetHtmlEditor(){
		htmlEditorFrente.setHtmlText("");
		htmlEditorVerso.setHtmlText("");
		htmlEditorFicha.setHtmlText("");
	}
	
	private void showComboboxOptions(){
		horizontalButtonBox.getChildren().remove(buttonDescartar);
		horizontalButtonBox.getChildren().remove(buttonPreencherSalvarCard);
		horizontalButtonBox.getChildren().remove(comboBoxVerticalPane);
		horizontalButtonBox.getChildren().add(comboBoxVerticalPane);
		horizontalButtonBox.getChildren().add(buttonPreencherSalvarCard);
		buttonPreencherSalvarCard.setText("Preencher Verso");
	}
	
	private void showButtonOptionsOnDescartar(){
		horizontalButtonBox.getChildren().remove(comboBoxVerticalPane);
		horizontalButtonBox.getChildren().remove(buttonPreencherSalvarCard);
		horizontalButtonBox.getChildren().add(buttonPreencherSalvarCard);
		buttonPreencherSalvarCard.setText("Salvar");
	}
	
	private void showButtonOptionsOnFicha(){
		horizontalButtonBox.getChildren().remove(comboBoxVerticalPane);
		horizontalButtonBox.getChildren().remove(buttonPreencherSalvarCard);
		horizontalButtonBox.getChildren().add(comboBoxVerticalPane);
		horizontalButtonBox.getChildren().add(buttonPreencherSalvarCard);
		buttonPreencherSalvarCard.setText("Salvar");
	}
	
}

