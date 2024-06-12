package model;

import interfaces.InfoLoans;

public class Loan implements InfoLoans {
	
	private String user;
	private String book;
	private String loanDate;
	private String returnDate;
	
	
	//Empty Constructor
	public Loan() {
		this("", "", "", "");
	}
	
	//Constructor
	public Loan(String user, String book, String loanDate, String returnDate) {
		super();
		this.user = user;
		this.book = book;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}

	//Getters & Setters
	public String getUser() {return user;}
	public void setUser(String user) {this.user = user;}
	public String getBook() {return book;}
	public void setBook(String book) {this.book = book;}
	public String getLoanDate() {return loanDate;}
	public void setLoanDate(String loanDate) {this.loanDate = loanDate;}
	public String getReturnDate() {return returnDate;}
	public void setReturnDate(String returnDate) {this.returnDate = returnDate;}
}
