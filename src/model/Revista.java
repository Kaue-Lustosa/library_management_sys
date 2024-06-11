package model;

public class Revista implements Material{

	String titulo;
	String editora;
	int numeroPaginas;
	
	//Construtor vazio
	public Revista() {
		this("", "", 0);
	}
	//Construtor
	public Revista(String titulo, String editora, int numeroPaginas) {
		this.titulo = titulo;
		this.editora = editora;
		this.numeroPaginas = numeroPaginas;
	}
	
	//Gets & Sets
	@Override
	public String getTitulo() {return titulo;}
	public void setTitulo(String titulo) {this.titulo = titulo;}
	public String getEditora() {return editora;}
	public void setEditora(String editora) {this.editora = editora;}
	public int getNumeroPaginas() {return numeroPaginas;}
	public void setNumeroPaginas(int numeroPaginas) {this.numeroPaginas = numeroPaginas;}

	@Override
	public void exibirDetalhes() {
		System.out.println("Título: " + titulo);
        System.out.println("Autor: " + editora);
        System.out.println("Número de Páginas: " + numeroPaginas);
	}

	
}
