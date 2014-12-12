package com.example.vozac.AsyncTaskKlase;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.vozac.Drugi;
import com.example.vozac.Klase.Voznja;

public class DeleteVoznja extends AsyncTask<String, Void, Void> {

	private Voznja v;
	private Drugi activity;

	private String username = null;
	private String password = null;
	private String idVoznje = null;
	
	public DeleteVoznja (Drugi a) {
		this.activity = a;
	}

	@Override
	protected Void doInBackground(String... params) {
		Log.d("info", "Usao u doInBackground u delete voznji");
		
		username = params[0];
		password = params[1];
		idVoznje = params[2];
		
		HttpClient httpclient = new DefaultHttpClient();
		String url = "http://farisc.comlu.com/Voznje.php";
	
		HttpDelete httpdelete = new HttpDelete(url + "?korisnickoIme=" + username + "&password=" + password + "&idVoznje=" + idVoznje);
		try {
			HttpResponse response = httpclient.execute(httpdelete);
			Log.d("ovo je pravo vazno polje", EntityUtils.toString(response.getEntity(), HTTP.UTF_8));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		        
		return null;
	}

	@Override
	protected void onPostExecute(Void response) {
		
		Log.d("info", "Usao u onPostExecute delete voznje");
		try {
			activity.trenutni_smjer.setText("azra");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}