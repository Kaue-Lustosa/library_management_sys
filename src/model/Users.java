package model;

import interfaces.InfoUsers;

public class Users implements InfoUsers {
	
	private String name;
	private String cpf;
	private String matricula;
	private String birth_date;
	
	//Empty Constructor
	public Users() {
		this("", "", "", "");
	}
	
	//Constructor
	public Users(String name, String cpf, String matricula, String birth_date) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.matricula = matricula;
		this.birth_date = birth_date;
	}

	//Gets & Sets
	@Override
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getCPF() {return cpf;}
	public void setCPF(String cpf) {this.cpf = cpf;}
	public String getMatricula() {return matricula;}
	public void setMatricula(String matricula) {this.matricula = matricula;}
	public String getBirthDate() {return birth_date;}
	public void setBirthDate(String birth_date) {this.birth_date = birth_date;}
	
	public void exibirDetalhes() {
		System.out.println("Nome: " + name);
        System.out.println("CPF: " + cpf);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Data de Nascimento: " + birth_date);
	}
}
