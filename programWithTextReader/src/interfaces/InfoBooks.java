package interfaces;

public interface InfoBooks {
	
	public abstract String getTitle();
	public abstract void setTitle(String title);
	public abstract String getAuthor();
	public abstract void setAuthor(String author);
	public abstract String getSubject();
	public abstract void setSubject(String subject);
	public abstract String getReleaseYear();
	public abstract void setReleaseYear(String releaseYear);
	public abstract int getStock();
	public abstract void setStock(int i);
	
	public abstract void exibirDetalhes();

}