package model;

import interfaces.InfoUsers;

public class Estudantes extends Users implements InfoUsers {

	private String curso;
	
	//Empty Constructor
	public Estudantes() {
		this("", "", "", "", "");
	}
		
	//Constructor
	public Estudantes(String name, String cpf, String matricula, String birth_date, String curso) {
		super(name, cpf, matricula, birth_date);
		this.curso = curso;
	}
	
	//Gets & Sets
	public String getCurso() {return curso;}
	public void setCurso(String curso) {this.curso = curso;}

}
