package opendoors.app.desktop.pomodoro.controller;

import java.util.ArrayList;
import java.util.List;

import opendoors.app.desktop.pomodoro.model.Assunto;
import opendoors.app.desktop.pomodoro.model.DataLayerDB;
import opendoors.app.desktop.pomodoro.model.DataLayerRest;
import opendoors.app.desktop.pomodoro.model.Ficha;
import opendoors.app.desktop.pomodoro.model.FlashCard;
import opendoors.app.desktop.pomodoro.model.IDataLayer;
import opendoors.app.desktop.pomodoro.model.Materia;

public class DataController {

	IDataLayer dataLayer = new DataLayerRest();
	
	public List<Materia> getTodasMaterias(){
		return dataLayer.getTodasMaterias();
	}
	
	public List<Assunto> getTodosAssuntosPorMateria(Materia materia){
		return dataLayer.getTodosAssuntosPorMateria(materia);
	}
	
	public List<FlashCard> getTodosFlashCardsPorAssunto(Assunto assunto){
		return dataLayer.getTodosFlashCardsPorAssunto(assunto);
	}
	
	public List<Ficha> getTodasFichasPorAssunto(Assunto assunto){
		return dataLayer.getTodasFichasPorAssunto(assunto);
	}
	
	public void saveMateria(Materia materia){
		dataLayer.saveMateria(materia);
	}
	
	public void saveAssunto(Assunto assunto){
		dataLayer.saveAssunto(assunto);
	}
	
	public void saveFlashCard(String materia, String assunto, String front, String back){
		
		ArrayList<Materia> materias = (ArrayList<Materia>) getTodasMaterias();
		Materia materiaFound = null;
		for (Materia materia2 : materias) {
			if(materia2.getNome().equals(materia)){
				materiaFound = materia2;
			}
		}
		Assunto assuntoFound = null;
		ArrayList<Assunto> assuntos = (ArrayList<Assunto>) getTodosAssuntosPorMateria(materiaFound);
		for (Assunto assunto2 : assuntos) {
			if(assunto2.getNome().equals(assunto)){
				assuntoFound = assunto2;
			}
		}
		
		FlashCard card = new FlashCard(assuntoFound, front, back);
		dataLayer.saveFlashCard(card);
	}
	
	public void saveFicha(String materia, String assunto,  String ficha){
		ArrayList<Materia> materias = (ArrayList<Materia>) getTodasMaterias();
		Materia materiaFound = null;
		for (Materia materia2 : materias) {
			if(materia2.getNome().equals(materia)){
				materiaFound = materia2;
			}
		}
		Assunto assuntoFound = null;
		ArrayList<Assunto> assuntos = (ArrayList<Assunto>) getTodosAssuntosPorMateria(materiaFound);
		for (Assunto assunto2 : assuntos) {
			if(assunto2.getNome().equals(assunto)){
				assuntoFound = assunto2;
			}
		}
		
		Ficha card = new Ficha(assuntoFound, ficha);
		dataLayer.saveFicha(card);
	}
	
}
