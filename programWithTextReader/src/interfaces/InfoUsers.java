package interfaces;

public interface InfoUsers {
	
	public abstract String getName();
	public abstract void setName(String name);
	public abstract String getCPF();
	public abstract void setCPF(String cpf);
	public abstract String getMatricula();
	public abstract void setMatricula(String matricula);
	public abstract String getBirthDate();
	public abstract void setBirthDate(String birthDate);
	
	public abstract void exibirDetalhes();

}
