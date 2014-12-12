package com.example.vozac.AsyncTaskKlase;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.vozac.Postavke;
import com.example.vozac.Klase.Vozilo;

public class GetBrojVozila extends AsyncTask <String, Void, String> {

	private Postavke activity;
	private String broj, tip;

	public GetBrojVozila (Postavke a)
    {
        this.activity = a;
    }
	
	@Override
	protected String doInBackground(String... params) {
		
		String username = params[0];
		String password = params[1];
		String tip = params[2];
		
		try {
			String url = "http://farisc.comlu.com/Vozila.php";	
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url + "?korisnickoIme=" + username + "&password=" +password + "&tipVozila=" +tip);
			HttpResponse response = httpClient.execute(httpGet);
			return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);		
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String response) {
		
		ArrayList<String> brojevi = new ArrayList<String> ();
				
		try {
			final JSONArray jsonObj = new JSONObject(response).getJSONArray("vozila");
			brojevi.add(" ");
			for (int i=0; i<jsonObj.length(); i++) {
				broj = jsonObj.getJSONObject(i).getString("brojVozila");			
				brojevi.add(broj);
			}

			String [] brojeviVozila = new String[brojevi.size()];
			brojeviVozila = brojevi.toArray(brojeviVozila);
			
		    ArrayAdapter adapter2 = new ArrayAdapter(activity, android.R.layout.simple_spinner_item, brojeviVozila);
		    activity.broj.setAdapter(adapter2);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
