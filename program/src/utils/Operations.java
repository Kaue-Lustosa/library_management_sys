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

public abstract class Operations {
	
	private static BibliotecaDAO bibliotecaDAO = BibliotecaDAO.getInstance();
	ArrayList<InfoBooks> materiais;
	ArrayList<InfoUsers> users;
	
	public Operations() {}
	
	//About what will be used in the library
	private static String getCurrentDate() {
	    LocalDate currentDate = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return currentDate.format(formatter);
	}
	
	private static String returnDate(String userType) {
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
            System.out.println("\nLista de materiais no momento:\n");
            for (InfoBooks material : materiais) {
                System.out.println(" - " + material.getTitle());
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
	
	public static void loanBook(String cpf, String book) {
        InfoBooks material = buscaPorNome(book);
        if (material != null && material.getStock() > 0) {
        	boolean check = false;
        	check = verifyUser(verifyUserType(cpf));
        	for (InfoUsers user : bibliotecaDAO.getArrayUsers()) {
                if (user.getCPF().equals(cpf)) {
                	if(check == false) {
                        System.out.println("It wasn't possible to loan the book '" + book + "' to the user " + user.getName() + ".");
                	} else {
                		InfoLoans users = getActiveLoansByUser(user.getName());
                		if(users != null) {
                    		Loan loan = new Loan(user.getName(), book, getCurrentDate(), returnDate(verifyUserType(cpf)));
                            users.setActiveLoans(users.getActiveLoans() + 1);
                            bibliotecaDAO.getArrayLoans().add(loan);
                            material.setStock(material.getStock() - 1);
                            System.out.println("Book '" + book + "' loaned to " + user.getName());
                            System.out.println("Now, " + user.getName() + " haves " + users.getActiveLoans() + " active loans.");
                		} else {
                			Loan loan = new Loan(user.getName(), book, getCurrentDate(), returnDate(verifyUserType(cpf)));
                	        loan.setActiveLoans(1);
                	        bibliotecaDAO.getArrayLoans().add(loan);
                            material.setStock(material.getStock() - 1);
                            System.out.println("Book '" + book + "' loaned to " + user.getName());
                            System.out.println("Now, " + user.getName() + " haves " + loan.getActiveLoans() + " active loans.");
                		}
                	}
                } else {
                    System.out.println("Usuário com o CPF " + cpf + " não registrado.");
                }
        	}
        	
        } else {
            System.out.println("This book is not available in stock for loan.");
        }
    }
	
	public static void returnBook(String cpf, String book) {
        InfoLoans loan = buscaPorEmprestimo(cpf, book);
        if (loan != null) {
        	loan.setActiveLoans(loan.getActiveLoans() - 1);
        	for (InfoUsers user : bibliotecaDAO.getArrayUsers()) {
                if (user.getCPF().equals(cpf)) {
                	if(loan.getActiveLoans() == 0) {
                        System.out.println("User '" + user.getName() + "' have returned his last book loaned ");
                	} else {
                        System.out.println("Book '" + book + "' returned by " + user.getName());
                	}
                    bibliotecaDAO.getArrayLoans().remove(loan);
                    InfoBooks material = buscaPorNome(book);
                    material.setStock(material.getStock() + 1);
                }  else {
                    System.out.println("Usuário com o CPF " + cpf + " não registrado.");
                }
        	}
        } else {
            System.out.println("This user isn't with any active loan in the moment.");
        }
    }
	
	//About info of users
	private static InfoLoans getActiveLoansByUser(String cpf) {
	    for (InfoLoans loan : bibliotecaDAO.getArrayLoans()) {
	        if (loan.getCPF().equals(cpf)) {
	            return loan;
	        }
	    }
	    return null;
	}

	private static InfoLoans buscaPorEmprestimo(String cpf, String book) {
        for (InfoLoans loan : bibliotecaDAO.getArrayLoans()) {
            if (loan.getCPF().equalsIgnoreCase(cpf) && loan.getBook().equalsIgnoreCase(book)) {
                return loan;
            }
        }
        return null;
    }
	
	public static String verifyUserType(String cpf) {
        for (InfoUsers user : bibliotecaDAO.getArrayUsers()) {
            if (user.getCPF().equals(cpf)) {
            	if (user.getMatricula().matches("\\d{3}\\.\\d{4}\\.745\\.\\d{2}")) {
                    return "Teacher";
                } else if (user.getMatricula().matches("\\d{3}\\.\\d{4}\\.500\\.\\d{2}")) {
                    return "Student";
                } else if (user.getMatricula().matches("\\d{3}\\.\\d{4}\\.501\\.\\d{2}")) {
                    return "Librarian";
                }
            }
        }
        return "Usuário com o CPF " + cpf + " não registrado.";
	}
	
	public static boolean verifyUser(String cpf) {
		InfoLoans loan = getActiveLoansByUser(cpf);
		if(loan != null) {
			if (verifyUserType(cpf).equalsIgnoreCase("Teacher") || verifyUserType(cpf).equalsIgnoreCase("Librarian")) {
				for (InfoUsers user : bibliotecaDAO.getArrayUsers()) {
                    if (user.getCPF().equals(cpf)) {
                    	if(loan.getActiveLoans() < 5) {
	                    	System.out.println("\nThe user " + user.getName() + " haves " + loan.getActiveLoans() + " active loans.");
	    	                return true;
	                    }
		        		if (loan.getActiveLoans() >= 5) {
			                System.out.println("\nThe user " + user.getName() + " reached the maximum number of loans possible!");
			                return false;
			            
		        		}
                    } else {
                        System.out.println("Usuário com o CPF " + cpf + " não registrado.");
                    }
	            }
	        }
	        if (verifyUserType(cpf).equalsIgnoreCase("Student")) {
	        	for (InfoUsers user : bibliotecaDAO.getArrayUsers()) {
                    if (user.getCPF().equals(cpf)) {
                    	if(loan.getActiveLoans() < 3) {
	                    	System.out.println("\nThe user " + user.getName() + " haves " + loan.getActiveLoans() + " active loans.");
	    	                return true;
	                    }
		        		if (loan.getActiveLoans() >= 3) {
			                System.out.println("\nThe user " + user.getName() + " reached the maximum number of loans possible!");
			                return false;
			            
		        		}
                    } else {
                        System.out.println("Usuário com o CPF " + cpf + " não registrado.");
                    }
	            }
	        } else {
                System.out.println("Verificação falhou. Tentar novamente.");
	        }
		} else {
			for (InfoUsers user : bibliotecaDAO.getArrayUsers()) {
                if (user.getCPF().equals(cpf)) {
                	System.out.println("\nThe user " + user.getName() + " haves " + 0 + " active loans.");
                    return true;
                } else {
                    System.out.println("Usuário com o CPF " + cpf + " não registrado.");
                }
			}
        }
        return false;
    }

    public static void listUserLoans(String cpf) {
    	for (InfoUsers user : bibliotecaDAO.getArrayUsers()) {
            if (user.getCPF().equals(cpf)) {
            	System.out.println("\nEmpréstimos ativos do usuário " + user.getName() + ":");
                for (InfoLoans loan : bibliotecaDAO.getArrayLoans()) {
                    if (loan.getCPF().equalsIgnoreCase(cpf)) {
                        System.out.println(" - Livro: " + loan.getBook() + ", Data de empréstimo: " + loan.getLoanDate() + ", Data de devolução: " + loan.getReturnDate());
                    }
                }
            } else {
                System.out.println("Usuário com o CPF " + cpf + " não registrado.");
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
	public static void programInterface() {
		
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
        try {
            int action = Integer.parseInt(scanner.nextLine());
            switch(action) {
	        	case 1:
	                System.out.print("\nType the book's title: ");
	                String title_added = scanner.nextLine();
	                System.out.print("Type the book's author: ");
	                String author = scanner.nextLine();
	                System.out.print("Type the book's subject: ");
	                String subject = scanner.nextLine();
	                System.out.print("Type the book's release year: ");
	                String year = scanner.nextLine();
	                System.out.print("Type the ammount of books stocked: ");
	                int ammount = Integer.parseInt(scanner.nextLine());
	        		cadastrarMaterial(title_added, author, subject, year, ammount);
	                System.out.println("\nBook " + title_added + " registered into library!\n");
	                System.out.println("============================================================");
	                programInterface();
	        	case 2:
	        		System.out.println("============================================================\n");
	        		exibirListaMateriais();
	        		System.out.println("\n============================================================");
	                programInterface();
	        	case 3:
	                System.out.print("\nType the book's title you want to search: ");
	                String title_search = scanner.nextLine();
	        		pesquisarMaterial(title_search);
	        		System.out.println("\n============================================================");
	                programInterface();
	        	case 4:
	                System.out.print("\nType the user's CPF: "); 
	                String user_loaning = scanner.nextLine();
	                System.out.print("Type the book's title: ");
	                String title_loaned = scanner.nextLine();
	                loanBook(user_loaning, title_loaned);
	                System.out.println("\n============================================================");
	                programInterface();
	        	case 5:
	        		System.out.print("\nType the user's CPF: ");
	                String user_name = scanner.nextLine();
	                System.out.print("Type the book's title: ");
	                String title_returned = scanner.nextLine();
	                returnBook(user_name, title_returned);
	                System.out.println("\n============================================================");
	                programInterface();
	        	case 6:
	        		System.out.print("Type the book's title you want to remove: ");
	                String title_removed = scanner.nextLine();
	                System.out.print("Type the ammount of books you want to remove from stock: ");
	                int ammount_removed = Integer.parseInt(scanner.nextLine());
	        		System.out.print("Are you sure you want to remove the book " + title_removed + " from the library? (y/n) ");
	        		String answer = scanner.nextLine();
	                if(answer.equals("yes")|| answer.equals("y")) {
	                	removerMaterial(title_removed, ammount_removed);
	                    System.out.println("\n============================================================");
	                    programInterface();
	                } if (answer.equals("no")|| answer.equals("n")) {
	                	System.out.println("\n============================================================");
	                    programInterface();
	                } else {
	                	System.out.println("\nType a valid entry.");
	                	System.out.println("\n============================================================");
	                    programInterface();
	                }
	        	case 7:
	        		System.out.print("\nType the user's CPF: ");
	                String user_verified = scanner.nextLine();
	                verifyUser(user_verified);
	                System.out.println("\n============================================================");
	                programInterface();
	        	case 8:
	        		System.out.print("\nType the user's CPF: ");
	                String user_listed = scanner.nextLine();
	                listUserLoans(user_listed);
	                System.out.println("\n============================================================");
	                programInterface();
	        	case 9:
	        		System.out.println("\nClosing the program...\n");
	        		System.exit(0);
	        	default:
	                System.out.print("\nType a valid number: ");
	                action = scanner.nextInt();
            }
        } catch(java.lang.NumberFormatException a) {
        	System.out.print("\nThat's not a valid answer! Try again.\n");
            System.out.println("\n============================================================");
            programInterface();
        }
        
        scanner.close();
	}

}