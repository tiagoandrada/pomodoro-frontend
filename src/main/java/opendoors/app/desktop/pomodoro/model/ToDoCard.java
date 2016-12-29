package opendoors.app.desktop.pomodoro.model;

public class ToDoCard {

	private int index;
	private String descricao;
	private Status status;
	
	public ToDoCard(String descricao, int index, Status status){
		this.descricao = descricao;
		this.index = index;
		this.status = status;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
}
