import utils.BibliotecaFunctions;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
    	    	
    	BibliotecaFunctions.registerLibrarian("Ana Cristina", "070.992.006-12", "512.2021.500.21", "24/04/2000", "ana.cristina.070@ufrn.edu.br", "senha123");
    	
    	BibliotecaFunctions.registerTeacher("André Salomão", "882.355.182-49", "809.2013.745.33", "DEART", "24/04/1980");
    	BibliotecaFunctions.registerTeacher("Ana Karla", "733.812.541-20", "510.2012.745.19", "DEF","24/04/1993");
    	BibliotecaFunctions.registerTeacher("Efésios Clécio", "541.324.255-15", "112.2005.745.21", "DIMAP", "24/04/1984");
    	BibliotecaFunctions.registerTeacher("Anderson Paiva", "334.835.992-13", "611.2007.745.18", "DMAT", "24/04/1989");
    	BibliotecaFunctions.registerTeacher("Rafaela de Moraes", "515.607.802-30", "710.2009.745.40", "DCB", "24/04/1969");
    	
    	BibliotecaFunctions.registerStudent("José Ribeira", "880.505.563-73", "013.2022.501.20", "Educação Física", "24/04/2002");
    	BibliotecaFunctions.registerStudent("Enzo Fonseca", "635.050.702-09", "112.2021.501.18", "Medicina", "24/04/2003");
    	BibliotecaFunctions.registerStudent("Rafaela Tavares", "202.207.665-36", "809.2020.501.20", "Química", "24/04/2000");
    	BibliotecaFunctions.registerStudent("Gabriela Lima", "453.090.603-55", "312.2021.501.20", "Biomedicia", "24/04/2001");
    	BibliotecaFunctions.registerStudent("Maria Gabriela", "889.239.792-22", "098.2019.501.24", "Tecnologia da Informação", "24/04/1995");

    	boolean check = false;
    	
    	try {
    		File archive = new File("C:\\Users\\Kauê\\eclipse-workspace\\library_management_sys/src/utils/presets");
            Scanner leitor = new Scanner(archive);
            
            //Scanner scanner = new Scanner(System.in);
        	
            System.out.println("============================================================");
            System.out.println("\nWelcome to the Digital Library System - DLS!\n");
            System.out.println("============================================================");
            System.out.print("\nLogin: ");
            String login = leitor.nextLine();
            System.out.print(login);
            System.out.print("\nPassword: ");
            String password = leitor.nextLine();
            System.out.print(password);
            check = BibliotecaFunctions.loginUser(login, password);
            System.out.println("\n============================================================");
            if(!check) {
            	System.out.println("\nClosing the program...\n");
            } else {
            	BibliotecaFunctions.programInterface(leitor);
            }
            
            //scanner.close();
            
            leitor.close();
        } catch (FileNotFoundException a) {
            System.out.println("Ocorreu um erro.");
            a.printStackTrace();
        }
    	
    	
    }
}
