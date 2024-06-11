package DAO;
import model.Material;

import java.util.ArrayList;

public class BibliotecaDAO {

	ArrayList<Material> materiais;
	private static BibliotecaDAO biblioteca;
	
	//Construtor privado
	private BibliotecaDAO() {
		this.materiais = new ArrayList<>();
	}
	
	public ArrayList<Material> getArrayMateriais() {
		return materiais;
	}
	
	//Singleton
	public static BibliotecaDAO getInstance() {
		if (biblioteca == null) {biblioteca = new BibliotecaDAO();}
        return biblioteca;
	}
	
}
	
