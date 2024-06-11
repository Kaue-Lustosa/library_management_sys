package model;

import interfaces.InfoBooks;

public class Livro implements InfoBooks {
	
	private String titulo;
	private String autor;
	private String assunto;
	private int anoLancamento;
	private int qtdEstoque;
	
	//Empty Constructor
	public Livro() {
		this("", "", "", 0, 0);
	}
	//Constructor
	public Livro(String titulo, String autor, String assunto, int anoLancamento, int qtdEstoque) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.assunto = assunto;
		this.anoLancamento = anoLancamento;
		this.qtdEstoque = qtdEstoque;
	}
	//Gets & Sets
	@Override
	public String getTitulo() {return titulo;}
	public void setTitulo(String titulo) {this.titulo = titulo;}
	public String getAutor() {return autor;}
	public void setAutor(String autor) {this.autor = autor;}
	public String getAssunto() {return assunto;}
	public void setAssunto(String assunto) {this.assunto = assunto;}
	public int getAnoLancamento() {return anoLancamento;}
	public void setAnoLancamento(int anoLancamento) {this.anoLancamento = anoLancamento;}
	public int getQtdEstoque() {return qtdEstoque;}
	public void setQtdEstoque(int qtdEstoque) {this.qtdEstoque = qtdEstoque;}
	
	@Override
	public void exibirDetalhes() {
		System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Assunto: " + assunto);
        System.out.println("Ano de lançamento: " + anoLancamento);
        System.out.println("Quantidade no estoque: " + qtdEstoque);
	}
	
}
