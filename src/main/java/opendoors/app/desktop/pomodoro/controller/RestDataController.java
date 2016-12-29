package opendoors.app.desktop.pomodoro.controller;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import opendoors.app.desktop.pomodoro.model.Materia;
import opendoors.app.desktop.pomodoro.model.MateriaGsonWrapper;

public class RestDataController {

	private static String url = "http://localhost:8080/pomodoro-backend/pomodoro/resources/materia";
	
	public static void main(String args[]){
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
			
			for (Materia materia : materiaWrapper.getMaterias()) {
				System.out.println(materia.getNome());
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
