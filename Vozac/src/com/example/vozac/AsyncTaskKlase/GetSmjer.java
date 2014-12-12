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
import com.example.vozac.Klase.Linija;
import com.example.vozac.Klase.Vozilo;

public class GetSmjer extends AsyncTask <String, Void, String> {

	private Postavke activity;
	private String l;

	public GetSmjer (Postavke a)
    {
        this.activity = a;
    }
	
	@Override
	protected String doInBackground(String... params) {
		
		Log.d("info", "Usao u doInBackground linije");
		String username = params[0];
		String password = params[1];
		String linija = params[2];
		
		try {
			String url = "http://farisc.comlu.com/Smjerovi.php";	
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url + "?korisnickoIme=" + username + "&password=" +password + "&brojLinije=" +linija);
			HttpResponse response = httpClient.execute(httpGet);
			return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);		
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
	protected void onPostExecute(String response) {
		
		
		Log.d("info", "Usao u onPostExecute u smjeru");
		ArrayList<String> smjerovi = new ArrayList<String> ();
			
			try {
				final JSONArray jsonObj = new JSONObject(response).getJSONArray("smjerovi");
				
				smjerovi.add(" ");
				for (int i=0; i<jsonObj.length(); i++) {
					
					l = jsonObj.getJSONObject(i).getString("smjer");		
					smjerovi.add(l);
					
					Log.d ("smjer", l);
				}

				String [] obaSmjera = new String[smjerovi.size()];
				obaSmjera = smjerovi.toArray(obaSmjera);
				
			    ArrayAdapter adapter3 = new ArrayAdapter(activity, android.R.layout.simple_spinner_item, obaSmjera);
			    activity.smjer.setAdapter(adapter3);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
	}
}
