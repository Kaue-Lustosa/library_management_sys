package utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.*;
import DAO.BibliotecaDAO;
import interfaces.InfoBooks;
import interfaces.InfoLoans;
import interfaces.InfoLogin;
import interfaces.InfoUsers;

public abstract class BibliotecaFunctions {
	
	private static BibliotecaDAO bibliotecaDAO = BibliotecaDAO.getInstance();
	ArrayList<InfoBooks> materiais;
	ArrayList<InfoUsers> users;
	
	public BibliotecaFunctions() {}
	
	//About what will be used in the library
	private static String getCurrentDate() {
	    LocalDate currentDate = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return currentDate.format(formatter);
	}
	
	private static String getReturnDate(String userType) {
	    LocalDate returnDate;
	    if (userType.equalsIgnoreCase("teacher") || userType.equalsIgnoreCase("librarian")) {
	        returnDate = LocalDate.now().plusDays(30);
	    } else if (userType.equalsIgnoreCase("student")) {
	        returnDate = LocalDate.now().plusDays(15);
	    } else {
	        return null;
	    }
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return returnDate.format(formatter);
	}

	private static InfoBooks buscaPorNome(String titulo) {
        for (InfoBooks material : bibliotecaDAO.getArrayBooks()) {
            if ((material).getTitle().equalsIgnoreCase(titulo)) {
                return material;
            }
        }
        return null;
    }

	//About the Library itself
	public static void cadastrarMaterial(String title, String author, String subject ,String releaseYear, int stock) {
		Livro newBook = new Livro(title, author, subject, releaseYear, stock);
		bibliotecaDAO.getArrayBooks().add(newBook);
	}
	
	public static void exibirListaMateriais() {
        ArrayList<InfoBooks> materiais = bibliotecaDAO.getArrayBooks();
        if (materiais == null || materiais.isEmpty()) {
            System.out.println("A biblioteca está sem materiais no momento.");
        } else {
            System.out.println("Lista de materiais no momento:");
            for (InfoBooks material : materiais) {
                System.out.println(material.getTitle());
            }
        }
    }
	
	public static void pesquisarMaterial(String titulo) {
		boolean materialEncontrado = false;
	    for (InfoBooks material : bibliotecaDAO.getArrayBooks()) {
	        if (material.getTitle().equalsIgnoreCase(titulo)) {
	            System.out.println("Material " + titulo + " no estoque! Quantidade: " + material.getStock());
	            materialEncontrado = true;
	            break;
	        }
	    }
	    if (!materialEncontrado) {
	        System.out.println("Material " + titulo + " não encontrado... tente novamente.");
	    }
	}
	
	public static void removerMaterial(String titulo, int ammount) {
		InfoBooks material = buscaPorNome(titulo);
	    if (material != null) {
	        if (material.getStock() > 0) {
	            material.setStock(material.getStock() - ammount);
	            if (material.getStock() == 0) {
	            	bibliotecaDAO.getArrayBooks().remove(material);
	            	System.out.println("\nBook " + titulo + " removed from library!");
	            } if (material.getStock() < 0) {
		            material.setStock(material.getStock() + ammount);
		            System.out.println("\nYou exceed the ammount in stock. Try a valid ammount.");
	            } else {
		            System.out.println("\n" + ammount + " books '" + titulo + "' removed from stock.\nRemaining in stock: " + material.getStock());
	            }
	        } else {
	            System.out.println("\nNo more copies of book '" + titulo + "' in stock.");
	        }
	    } else {
	        System.out.println("\nThis book doesn't exists.");
	    }
    }
	
	public static void loanBook(String username, String book, String usertype) {
        InfoBooks material = buscaPorNome(book);
        if (material != null && material.getStock() > 0) {
            Loan loan = new Loan(username, book, getCurrentDate(), getReturnDate(usertype));
            bibliotecaDAO.getArrayLoans().add(loan);
            material.setStock(material.getStock() - 1);
            System.out.println("Book '" + book + "' loaned to " + username);
        } else {
            System.out.println("This book is not available in stock for loan.");
        }
    }
	
	public static void returnBook(String user, String book) {
        InfoLoans loan = buscaPorEmprestimo(user, book);
        if (loan != null) {
            bibliotecaDAO.getArrayLoans().remove(loan);
            InfoBooks material = buscaPorNome(book);
            material.setStock(material.getStock() + 1);
            System.out.println("Book '" + book + "' returned by " + user);
        } else {
            System.out.println("This loan does not exist.");
        }
    }
	
	private static InfoLoans buscaPorEmprestimo(String user, String book) {
        for (InfoLoans loan : bibliotecaDAO.getArrayLoans()) {
            if (loan.getUser().equalsIgnoreCase(user) && loan.getBook().equalsIgnoreCase(book)) {
                return loan;
            }
        }
        return null;
    }
	
	public static void verifyUser(String username) {
        int count = 0;
        for (InfoLoans loan : bibliotecaDAO.getArrayLoans()) {
            if (loan.getUser().equalsIgnoreCase(username)) {
                count++;
            }
        }
        System.out.println("O usuário " + username + " tem " + count + " empréstimos ativos.");
    }

    public static void listUserLoans(String username) {
        System.out.println("Empréstimos ativos do usuário " + username + ":");
        for (InfoLoans loan : bibliotecaDAO.getArrayLoans()) {
            if (loan.getUser().equalsIgnoreCase(username)) {
                System.out.println("Livro: " + loan.getBook() + ", Data de empréstimo: " + loan.getLoanDate() + ", Data de devolução: " + loan.getReturnDate());
            }
        }
    }
	
	//About registration and authentication
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

	//About the program itself
	public static void programInterface(Scanner leitor) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\n1. Add a book");
        System.out.println("2. List books");
        System.out.println("3. Search a book");
        System.out.println("4. Loan a book");
        System.out.println("5. Return a loaned book");
        System.out.println("6. Remove a book");
        System.out.println("7. Verify user");
        System.out.println("8. List user's loans");
        System.out.println("9. Quit");
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
                String year = leitor.nextLine();
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
                System.out.print("\nType the user's name: "); 
                String user_loaning = leitor.nextLine();
                System.out.println(user_loaning);
                System.out.print("Type the book's title: ");
                String title_loaned = leitor.nextLine();
                System.out.println(title_loaned);
                System.out.print("Type the user's type: "); //TODO implement a sorter to identify the user's type by the registration ID
                String user_type = leitor.nextLine();
                System.out.println(user_type);
                loanBook(user_loaning, title_loaned, user_type);
                System.out.println("\n============================================================");
                programInterface(leitor);
        	case 5:
        		System.out.print("\nType the user's name: ");
                String user_name = leitor.nextLine();
                System.out.println(user_name);
                System.out.print("Type the book's title: ");
                String title_returned = leitor.nextLine();
                System.out.println(title_returned);
                returnBook(user_name, title_returned);
                System.out.println("\n============================================================");
                programInterface(leitor);
        	case 6:
        		System.out.print("Type the book's title you want to remove: ");
                String title_removed = leitor.nextLine();
                System.out.println(title_removed);
                System.out.print("Type the ammount of books you want to remove from stock: ");
                int ammount_removed = Integer.parseInt(leitor.nextLine());
                System.out.println(ammount_removed);
        		System.out.print("Are you sure you want to remove the book " + title_removed + " from the library? (y/n) ");
        		String answer = leitor.nextLine();
                System.out.println(answer);
                if(answer.equals("yes")|| answer.equals("y")) {
                	removerMaterial(title_removed, ammount_removed);
                    System.out.println("\n============================================================");
                    programInterface(leitor);
                } if (answer.equals("no")|| answer.equals("n")) {
                	System.out.println("\n============================================================");
                    programInterface(leitor);
                } else {
                	System.out.println("\nType a valid entry.");
                	System.out.println("\n============================================================");
                    programInterface(leitor);
                }
        	case 7:
        		System.out.print("\nType the user's name: ");
                String user_verified = leitor.nextLine();
                System.out.println(user_verified);
                verifyUser(user_verified);
                System.out.println("\n============================================================");
                programInterface(leitor);
        	case 8:
        		System.out.print("\nType the user's name: ");
                String user_listed = leitor.nextLine();
                System.out.println(user_listed);
                listUserLoans(user_listed);
                System.out.println("\n============================================================");
                programInterface(leitor);
        	case 9:
        		System.out.println("\nClosing the program...\n");
        		System.exit(0);
        	default:
                System.out.print("\nType a valid number: ");
                action = scanner.nextInt();
        }
        scanner.close();
	}

}