package model;

import interfaces.InfoBooks;

public class Livro implements InfoBooks {
	
	private String title;
	private String author;
	private String subject;
	private String releaseYear;
	private int stock;
	
	//Empty Constructor
	public Livro() {
		this("", "", "", "", 0);
	}
	//Constructor
	public Livro(String title, String author, String subject, String releaseYear, int stock) {
		super();
		this.title = title;
		this.author = author;
		this.subject = subject;
		this.releaseYear = releaseYear;
		this.stock = stock;
	}
	//Gets & Sets
	@Override
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	@Override
	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}
	@Override
	public String getSubject() {return subject;}
	public void setSubject(String subject) {this.subject = subject;}
	@Override
	public String getReleaseYear() {return releaseYear;}
	public void setReleaseYear(String releaseYear) {this.releaseYear = releaseYear;}
	@Override
	public int getStock() {return stock;}
	public void setStock(int stock) {this.stock = stock;}
	
	@Override
	public void exibirDetalhes() {
		System.out.println("Título: " + title);
        System.out.println("Autor: " + author);
        System.out.println("Assunto: " + subject);
        System.out.println("Ano de lançamento: " + releaseYear);
        System.out.println("Quantidade no estoque: " + stock);
	}
	
}
