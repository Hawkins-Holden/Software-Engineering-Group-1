package application;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;


public class SerializeJson {

	public static String getAddress(String jsonObject) throws JSONException{
		System.out.println(jsonObject);
		JSONObject json = new JSONObject(jsonObject);
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		String returner = "";
		int tempest = 0;
		
		jsonMap = JsonToMap.jsonToMap(json);
		
		tempest = jsonMap.get("results").toString().indexOf("geometry");
		System.out.println(tempest);
		returner = jsonMap.get("results").toString().substring(20, tempest-1); //"formatted address_ starts at index 2
		System.out.println(returner);
		
		return returner;
		
	}
	
}
