package model;

import interfaces.InfoLogin;
import interfaces.InfoUsers;

public class Bibliotecario extends Users implements InfoUsers, InfoLogin {

	private String login;
	private String password;
	
	//Empty Constructor
	public Bibliotecario() {
			this("", "", "", "", "", "");
		}
	
	//Constructor
	public Bibliotecario(String name, String cpf, String matricula, String birth_date, String login, String password) {
		super(name, cpf, matricula, birth_date);
		this.login = login;
		this.password = password;
	}
	
	//Gets & Sets
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

}