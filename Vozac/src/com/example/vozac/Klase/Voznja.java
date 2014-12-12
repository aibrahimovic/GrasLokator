package com.example.vozac.Klase;

public class Voznja {
	
	private String idVoznje;
	private int idVozila;
	private int idStanice;
	private int idKorisnika;
	private int idLinije;
	private int brojLinije;
	private boolean aktivan;
	private double lat;
	private double lon;
	private String username;
	private String password;
	private String smjer1;
	private String smjer2;
	
	public String getIdVoznje() {
		return idVoznje;
	}
	public void setIdVoznje(String idVoznje) {
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
	public int getBrojLinije() {
		return brojLinije;
	}
	public void setBrojLinije(int brojLinije) {
		this.brojLinije = brojLinije;
	}
	public String getSmjer1() {
		return smjer1;
	}
	public void setSmjer1(String smjer1) {
		this.smjer1 = smjer1;
	}
	public String getSmjer2() {
		return smjer2;
	}
	public void setSmjer2(String smjer2) {
		this.smjer2 = smjer2;
	}
}
