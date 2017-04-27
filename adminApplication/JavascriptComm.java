package adminApplication;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class JavascriptComm {

	public int getSize() {

		ArrayList<String> latLongArray = new ArrayList<String>();
		latLongArray = getLocations();
		System.out.println("size retrieved");
		return latLongArray.size();

	}

	public Double getString(int i) {
		ArrayList<String> latLongArray = getLocations();
		Double number = null;
		if (!latLongArray.get(i).isEmpty()) {
			number = Double.parseDouble(latLongArray.get(i));
			System.out.println("lat or long retrieved: " + number);
		} else {
			ArrayList<Integer> zipData = AnalyticsController.getZipData();
			int j = i / 2;
			if (!(zipData.get(j)==null || zipData.get(j).toString().isEmpty())) {
				String[] latlong = APIClient.geocodingRequest(zipData.get(j).toString());
				if (i % 2 == 0) {
					number = Double.parseDouble(latlong[0]);
				} else {
					number = Double.parseDouble(latlong[1]);
				}
			}
		}
		return number;

	}

	private ArrayList<String> getLocations() {
		ArrayList<String> latlngs = new ArrayList<String>();
		for (String[] latlng : AnalyticsController.getLatLongData()) {
			latlngs.add(latlng[0]);
			latlngs.add(latlng[1]);
		}
		return latlngs;
	}

}