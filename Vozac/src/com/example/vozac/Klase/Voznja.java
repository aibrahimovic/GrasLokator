package com.example.vozac.Klase;

public class Voznja {
	
	private int idVoznje;
	private int idVozila;
	private int idStanice;
	private int idKorisnika;
	private int idLinije;
	private boolean aktivan;
	private double lat;
	private double lon;
	private String username;
	private String password;
	
	public int getIdVoznje() {
		return idVoznje;
	}
	public void setIdVoznje(int idVoznje) {
		this.idVoznje = idVoznje;
	}
	public int getIdVozila() {
		return idVozila;
	}
	public void setIdVozila(int idVozila) {
		this.idVozila = idVozila;
	}
	public int getIdStanice() {
		return idStanice;
	}
	public void setIdStanice(int idStanice) {
		this.idStanice = idStanice;
	}
	public int getIdKorisnika() {
		return idKorisnika;
	}
	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	public int getIdLinije() {
		return idLinije;
	}
	public void setIdLinije(int idLinije) {
		this.idLinije = idLinije;
	}
	public boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
