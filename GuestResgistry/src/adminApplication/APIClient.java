package adminApplication;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class APIClient {
	public static void main(String[] args) {
		String[] latlng = geocodingRequest("GB");
		System.out.println(latlng[0] + ", " + latlng[1]);
	}

	private static String[] geocodingRequest(String country) {
		Client client = ClientBuilder.newClient();
		String url = "https://maps.googleapis.com/maps/api/geocode/xml?components=country:Nepal&key=AIzaSyBuT9VzPqnYSaB5Os0U4mkI8C7CiuFzwao";
		System.out.println(url);
		WebTarget target = client.target(url);
				String output = (target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class));
		String[] latlng = new String[2];
		System.out.println(output);
		int latStart = output.indexOf("<lat>") + 5;
		int latEnd = output.indexOf("</lat>");
		latlng[0] = output.substring(latStart, latEnd);
		int lngStart = output.indexOf("<lng>") + 8;
		int lngEnd = output.indexOf("</lng>");
		latlng[1] = output.substring(lngStart, lngEnd);
		return latlng;
	}

}
