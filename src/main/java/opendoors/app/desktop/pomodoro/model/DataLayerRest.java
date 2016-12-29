package opendoors.app.desktop.pomodoro.model;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DataLayerRest implements IDataLayer{

	private static String url = "http://localhost:8080/pomodoro-backend/pomodoro/materia";

	@Override
	public List<Materia> getTodasMaterias() {
		try{
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed: HTTP code error : " +response.getStatus());
			}
			
			Gson gson = new Gson();
			String output = response.getEntity(String.class);
			MateriaGsonWrapper materiaWrapper = gson.fromJson(output, MateriaGsonWrapper.class);
			return materiaWrapper.getMaterias();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Assunto> getTodosAssuntosPorMateria(Materia materia) {
		try{
			Client client = Client.create();
			WebResource webResource = client.resource(url+"/"+materia.getId()+"/assuntos");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed: HTTP code error : " +response.getStatus());
			}
			
			Gson gson = new Gson();
			String output = response.getEntity(String.class);
			AssuntoGsonWrapper assuntosWrapper = gson.fromJson(output, AssuntoGsonWrapper.class);
			return assuntosWrapper.getAssuntos();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<FlashCard> getTodosFlashCardsPorAssunto(Assunto assunto) {
		try{
			Client client = Client.create();
			WebResource webResource = client.resource(url+"/"+assunto.getMateria().getId()+"/assuntos/"+assunto.getId()+"/flashcards");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed: HTTP code error : " +response.getStatus());
			}
			
			Gson gson = new Gson();
			String output = response.getEntity(String.class);
			FlashCardGsonWrapper flashcardsWrapper = gson.fromJson(output, FlashCardGsonWrapper.class);
			return flashcardsWrapper.getFlashCards();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Ficha> getTodasFichasPorAssunto(Assunto assunto) {
		try{
			Client client = Client.create();
			WebResource webResource = client.resource(url+"/"+assunto.getMateria().getId()+"/assuntos/"+assunto.getId()+"/fichas");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed: HTTP code error : " +response.getStatus());
			}
			
			Gson gson = new Gson();
			String output = response.getEntity(String.class);
			FichaGsonWrapper fichaWrapper = gson.fromJson(output, FichaGsonWrapper.class);
			return fichaWrapper.getFichas();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void saveMateria(Materia materia) {
		Client client = Client.create();
		WebResource webResource = client.resource(url+"/post");

		Gson gson = new Gson();
		String formData = gson.toJson(materia);
		
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, formData);
		
		if(response.getStatus() != 201){
			throw new RuntimeException("Failed: HTTP code error : " +response.getStatus());
		}else{
			System.out.println(response.getStatus());
			System.out.println("saved "+materia);
		}
		
	}

	@Override
	public void saveAssunto(Assunto assunto) {
		Client client = Client.create();
		WebResource webResource = client.resource(url+"/assuntos/post");

		Gson gson = new Gson();
		String formData = gson.toJson(assunto);
		
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, formData);
		
		if(response.getStatus() != 201){
			throw new RuntimeException("Failed: HTTP code error : " +response.getStatus());
		}
		
	}

	@Override
	public void saveFlashCard(FlashCard flashcard) {
		
		Client client = Client.create();
		WebResource webResource = client.resource(url+"/"+flashcard.getAssunto().getMateria().getId()+"/assuntos/"+flashcard.getAssunto().getId()+"/flashcards/post");

		Gson gson = new Gson();
		String formData = gson.toJson(flashcard);
		
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, formData);
		
		if(response.getStatus() != 201){
			throw new RuntimeException("Failed: HTTP code error : " +response.getStatus());
		}
		
	}

	@Override
	public void saveFicha(Ficha ficha) {
		Client client = Client.create();
		WebResource webResource = client.resource(url+"/"+ficha.getAssunto().getMateria().getId()+"/assuntos/"+ficha.getAssunto().getId()+"/fichas/post");

		Gson gson = new Gson();
		String formData = gson.toJson(ficha);
		
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, formData);
		
		if(response.getStatus() != 201){
			throw new RuntimeException("Failed: HTTP code error : " +response.getStatus());
		}
		
	}
	
	
}
