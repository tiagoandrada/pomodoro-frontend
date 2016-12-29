package opendoors.app.desktop.pomodoro.model;

import java.util.List;

public interface IDataLayer {

	public List<Materia> getTodasMaterias();
	
	public List<Assunto> getTodosAssuntosPorMateria(Materia materia);
	
	public List<FlashCard> getTodosFlashCardsPorAssunto(Assunto assunto);
	
	public List<Ficha> getTodasFichasPorAssunto(Assunto assunto);
	
	public void saveMateria(Materia materia);
	
	public void saveAssunto(Assunto assunto);
	
	public void saveFlashCard(FlashCard flashcard);
	
	public void saveFicha(Ficha ficha);
 	
}
