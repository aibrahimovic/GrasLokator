package com.example.vozac.AsyncTaskKlase;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.vozac.Login;
import com.example.vozac.Postavke;
import com.example.vozac.Klase.Vozac;

public class UcitajVozaca extends AsyncTask<Vozac, Void, String> {

	private Vozac v;
	//private Context activity;
	private Login activity;
	private String username = null;
	private String password = null;
	


	public UcitajVozaca(Login a) {
		this.activity = a;
	}
	@Override
	protected String doInBackground(Vozac... params) {
		
		v = params[0];
		username = v.getUsername();
		password = v.getSifra();

		try {

			String url = "http://farisc.comlu.com/Korisnici.php";

			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url + "?korisnickoIme=" + username + "&password=" + password);

			HttpResponse response = httpClient.execute(httpGet);
			
			
			return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String response) {
		try {

		String id;

			final JSONArray jsonObj = new JSONObject(response).getJSONArray("korisnik");
			//if (jsonObj.getJSONObject(0).isNull("idKorisnik")) {
				
			//} else {
				id = jsonObj.getJSONObject(0).getString("idKorisnika");

				id = id.replace("<!-- Hosting24 Analytics Code -->", "");
				id = id.replace("<script type=\"text/javascript\" src=\"http://stats.hosting24.com/count.php\"></script>", "");
				id = id.replace("<!-- End Of Analytics Code -->", "");
				

				
				if (id != "") {
					Intent i = new Intent(activity, Postavke.class);
					i.putExtra("username", username);
					i.putExtra("password", password);
					i.putExtra("idKorisnika", id);
					i.putExtra ("lat", activity.lat);
					i.putExtra ("lon", activity.lon);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					activity.startActivity(i);
					activity.ukloniStatus();
					//activity.status.setVisibility(View.GONE);
				}
				else {
					activity.postaviStatus();
					//activity.status.setVisibility(View.VISIBLE);
				}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}