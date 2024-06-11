package utils;

import java.util.ArrayList;
import model.*;
import DAO.BibliotecaDAO;

public abstract class BibliotecaFunctions implements Material {
	
	private static BibliotecaDAO bibliotecaDAO = BibliotecaDAO.getInstance();
	ArrayList<Material> materiais;
	
	public BibliotecaFunctions() {
	}

	public static void pesquisarMaterial(String titulo) {
		boolean materialEncontrado = false;
	    for (Material material : bibliotecaDAO.getArrayMateriais()) {
	        if (material.getTitulo().equalsIgnoreCase(titulo)) {
	            System.out.println("Material " + titulo + " no estoque!");
	            materialEncontrado = true;
	            break;
	        }
	    }
	    if (!materialEncontrado) {
	        System.out.println("Material " + titulo + " não encontrado... tente novamente.");
	    }
	}
	
	public static void exibirListaMateriais() {
        ArrayList<Material> materiais = bibliotecaDAO.getArrayMateriais();
        if (materiais.isEmpty()) {
            System.out.println("A biblioteca está sem materiais no momento.");
        } else {
            System.out.println("Lista de materiais no momento:");
            for (Material material : materiais) {
                System.out.println(material.getTitulo());
            }
        }
    }
	
	public static void cadastrarMaterial(String titulo, String autor, int anoLancamento, String edicao) {
		Livro novoLivro = new Livro(titulo, autor, anoLancamento, edicao);	
		bibliotecaDAO.getArrayMateriais().add(novoLivro);
	}
	public static void cadastrarMaterial(String titulo, String editora, int numeroPaginas) {
		Revista novaRevista = new Revista(titulo, editora, numeroPaginas);
		bibliotecaDAO.getArrayMateriais().add(novaRevista);
	}
	
	public static void removerMaterial(String titulo) {
        Material material = buscaPorNome(titulo);
        if (material != null) {
            bibliotecaDAO.getArrayMateriais().remove(material);
            System.out.println("Material '" + titulo + "' removido.");
        } else {
            System.out.println("Esse material não existe.");
        }
    }
	
	private static Material buscaPorNome(String titulo) {
        for (Material material : bibliotecaDAO.getArrayMateriais()) {
            if ((material).getTitulo().equalsIgnoreCase(titulo)) {
                return material;
            }
        }
        return null;
    }

}
