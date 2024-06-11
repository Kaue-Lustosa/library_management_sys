package model;

import interfaces.InfoUsers;

public class Professores extends Users implements InfoUsers {

	private String departamento;
	
	//Empty Constructor
	public Professores() {
		this("", "", "", "", "");
	}
		
	//Constructor
	public Professores(String name, String cpf, String matricula, String departamento, String birth_date) {
		super(name, cpf, matricula, birth_date);
		this.departamento = departamento;
	}
	
	//Gets & Sets
	public String getDepartamento() {return departamento;}
	public void setDepartamento(String departamento) {this.departamento = departamento;}
	
}
