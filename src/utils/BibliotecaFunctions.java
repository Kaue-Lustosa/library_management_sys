package utils;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import DAO.BibliotecaDAO;
import interfaces.InfoBooks;
import interfaces.InfoLogin;
import interfaces.InfoUsers;

public abstract class BibliotecaFunctions {
	
	private static BibliotecaDAO bibliotecaDAO = BibliotecaDAO.getInstance();
	ArrayList<InfoBooks> materiais;
	ArrayList<InfoUsers> users;
	
	public BibliotecaFunctions() {}

	public static void pesquisarMaterial(String titulo) {
		boolean materialEncontrado = false;
	    for (InfoBooks material : bibliotecaDAO.getArrayBooks()) {
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
        ArrayList<InfoBooks> materiais = bibliotecaDAO.getArrayBooks();
        if (materiais == null || materiais.isEmpty()) {
            System.out.println("A biblioteca está sem materiais no momento.");
        } else {
            System.out.println("Lista de materiais no momento:");
            for (InfoBooks material : materiais) {
                System.out.println(material.getTitulo());
            }
        }
    }
	
	public static void cadastrarMaterial(String titulo, String autor, String assunto ,int anoLancamento, int qtdEstoque) {
		Livro newBook = new Livro(titulo, autor, assunto, anoLancamento, qtdEstoque);
		bibliotecaDAO.getArrayBooks().add(newBook);
	}
	
	public static void removerMaterial(String titulo) {
		InfoBooks material = buscaPorNome(titulo);
        if (material != null) {
            bibliotecaDAO.getArrayBooks().remove(material);
            System.out.println("Book '" + titulo + "' removed.");
        } else {
            System.out.println("This book doesn't exists.");
        }
    }
	
	private static InfoBooks buscaPorNome(String titulo) {
        for (InfoBooks material : bibliotecaDAO.getArrayBooks()) {
            if ((material).getTitulo().equalsIgnoreCase(titulo)) {
                return material;
            }
        }
        return null;
    }

	public static void registerStudent(String name, String cpf, String matricula, String curso, String birth_date) {
		
		Estudantes newStudent = new Estudantes(name, cpf, matricula, curso, birth_date);
		bibliotecaDAO.getArrayUsers().add(newStudent);
		
	}
	
	public static void registerTeacher(String name, String cpf, String matricula, String departamento, String birth_date) {
		
		Professores newTeacher = new Professores(name, cpf, matricula, departamento, birth_date);
		bibliotecaDAO.getArrayUsers().add(newTeacher);
		
	}

	public static void registerLibrarian(String name, String cpf, String matricula, String birth_date, String login, String password) {
		
		Bibliotecario newLibrarian = new Bibliotecario(name, cpf, matricula, birth_date, login, password);
		bibliotecaDAO.getArrayUsers().add(newLibrarian);
		bibliotecaDAO.getArrayLogin().add(newLibrarian);
		
	}
	
	public static boolean loginUser(String login, String password) {
		
		ArrayList<InfoLogin> registration = bibliotecaDAO.getArrayLogin();
		
		if(registration == null || registration.isEmpty()) {
            System.out.println("A biblioteca está sem bibliotecários no momento.");
		} else {
			for (int cont = 0; cont < registration.size();) {
				InfoLogin data = registration.get(cont);
	            if (data.getLogin().equals(login) && data.getPassword().equals(password)) {
	                System.out.println("\nWelcome, " + data.getLogin() + "!");
	                return true;
	            } else {
	            	System.out.println("Wrong user/password. Try again.");
	            	return false;
	            }
	        }
		}
		return false;
	}

	public static void programInterface(Scanner leitor) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\n1. Add a book");
        System.out.println("2. List books");
        System.out.println("3. Search a book");
        System.out.println("4. Loan a book");
        System.out.println("5. Retrieve a book");
        System.out.println("6. Remove a book");
        System.out.println("7. Quit");
        System.out.print("\nType your entry: ");
        int action = Integer.parseInt(leitor.nextLine());
        System.out.println(action);

        switch(action) {
        	case 1:
                System.out.print("\nType the book's title: ");
                String title_added = leitor.nextLine();
                System.out.println(title_added);
                System.out.print("Type the book's author: ");
                String author = leitor.nextLine();
                System.out.println(author);
                System.out.print("Type the book's subject: ");
                String subject = leitor.nextLine();
                System.out.println(subject);
                System.out.print("Type the book's release year: ");
                int year = Integer.parseInt(leitor.nextLine());
                System.out.println(year);
                System.out.print("Type the ammount of books stocked: ");
                int ammount = Integer.parseInt(leitor.nextLine());
                System.out.println(ammount);
        		cadastrarMaterial(title_added, author, subject, year, ammount);
                System.out.println("\nBook " + title_added + " registered into library!\n");
                System.out.println("============================================================");
                programInterface(leitor);
        	case 2:
        		System.out.println("============================================================\n");
        		exibirListaMateriais();
        		System.out.println("\n============================================================");
                programInterface(leitor);
        	case 3:
                System.out.print("\nType the book's title you want to search: ");
                String title_search = leitor.nextLine();
                System.out.println(title_search);
        		pesquisarMaterial(title_search);
        		System.out.println("\n============================================================");
                programInterface(leitor);
        	case 4:
        		break;
        	case 5:
        		break;
        	case 6:
        		System.out.print("Type the book's title you want to remove: ");
                String title_removed = leitor.nextLine();
                System.out.println(title_removed);
        		removerMaterial(title_removed);
                System.out.println("\nBook " + title_removed + " removed from library!\n");
                System.out.println("\n============================================================");
                programInterface(leitor);
        	case 7:
        		System.out.println("\nClosing the program...\n");
        		break;
        	default:
                System.out.print("\nType a valid number: ");
                action = scanner.nextInt();
        }
        scanner.close();
	}
}