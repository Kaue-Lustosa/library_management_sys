package DAO;
import java.util.ArrayList;

import interfaces.InfoBooks;
import interfaces.InfoLoans;
import interfaces.InfoLogin;
import interfaces.InfoUsers;

public class BibliotecaDAO {

	ArrayList<InfoBooks> books;
	ArrayList<InfoUsers> users;
	ArrayList<InfoLoans> loans;
	ArrayList<InfoLogin> login;
	private static BibliotecaDAO biblioteca;
	
	//Private Constructor
	private BibliotecaDAO() {
		this.books = new ArrayList<>();
		this.users = new ArrayList<>();
		this.loans = new ArrayList<>();
		this.login = new ArrayList<>();
	}
	
	//Getters & Setters
	public ArrayList<InfoBooks> getArrayBooks() {return books;}
	public ArrayList<InfoUsers> getArrayUsers() {return users;}
	public ArrayList<InfoLoans> getArrayLoans() {return loans;}
	public ArrayList<InfoLogin> getArrayLogin() {return login;}

	//Singleton
	public static BibliotecaDAO getInstance() {
		if (biblioteca == null) {biblioteca = new BibliotecaDAO();}
        return biblioteca;
	}
	
}
	
