package model;

import interfaces.InfoLoans;

public class Loan implements InfoLoans {
	
	private String cpf;
	private String book;
	private String loanDate;
	private String returnDate;
    private int activeLoans; //independent variable (ignore this guy, he's better alone)
	
	
	//Empty Constructor
	public Loan() {
		this("", "", "", "");
	}
	
	//Constructor
	public Loan(String cpf, String book, String loanDate, String returnDate) {
		super();
		this.cpf = cpf;
		this.book = book;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}

	//Getters & Setters
	public String getCPF() {return cpf;}
	public void setCPF(String cpf) {this.cpf = cpf;}
	public String getBook() {return book;}
	public void setBook(String book) {this.book = book;}
	public String getLoanDate() {return loanDate;}
	public void setLoanDate(String loanDate) {this.loanDate = loanDate;}
	public String getReturnDate() {return returnDate;}
	public void setReturnDate(String returnDate) {this.returnDate = returnDate;}
	public int getActiveLoans() {return activeLoans;}
	public void setActiveLoans(int activeLoans) {this.activeLoans = activeLoans;}
}