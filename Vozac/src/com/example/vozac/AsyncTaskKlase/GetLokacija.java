package com.example.vozac.AsyncTaskKlase;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.example.vozac.Klase.Vozilo;

public class GetLokacija extends AsyncTask <Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
			
		String address = "Bistrik, Sarajevo";
	    String requestUri = "http://maps.googleapis.com/maps/api/geocode/json?address={0}&sensor=false";
		
		HttpGet httpGet = new HttpGet(requestUri);
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpGet);     
			return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String response) {
		try {
			final JSONArray jsonObj = new JSONObject(response).getJSONArray("results");
			double lat;
			double lon;
			
			lat = jsonObj.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			lon = jsonObj.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

		}
			
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
