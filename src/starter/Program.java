package starter;

import java.util.*;
import classLibrary.*;

public class Program {

	public static void main(String[] args) {
		Hotel.initialization();
		Scanner scanner = new Scanner(System.in);
		mainMenu(scanner);
		scanner.close();
	}
	
	public static void printMainMenu() {
		String mainMenu = "Pilih nomor menu untuk masuk ke menunya:\n1. All Guest Data\n2. All Room Data\n3. About This Hotel\n4. Exit Application";
		System.out.println(mainMenu);
	}
	public static void printGuestMenu() {
		String guestMenu = "Pilih nomor menu untuk masuk ke menunya:\n1. Guest Information\n2. Back to Main Menu\n3. Exit Application";
		System.out.println(guestMenu);
	}
	public static void printRoomMenu() {
		String roomMenu = "Pilih nomor menu untuk masuk ke menunya:\n1. Room Information\n2. Back to Main Menu\n3. Exit Application";
		System.out.println(roomMenu);		
	}
	public static void printExitGuestMenu() {
		String guestMenu = "Pilih nomor menu untuk masuk ke menunya:\n1. Back to All Guest Data\n2. Back to Main Menu\n3. Exit Application";
		System.out.println(guestMenu);		
	}
	public static void printExitRoomMenu() {
		String roomMenu = "Pilih nomor menu untuk masuk ke menunya:\n1. Back to All Room Data\n2. Back to Main Menu\n3. Exit Application";
		System.out.println(roomMenu);		
	}
	public static boolean validateInput(String input, int maxMenu) {
		boolean validation = false;
		try {
			int numericInput = Integer.parseInt(input);
			if(numericInput <= maxMenu && numericInput >= 1) {
				validation = true;
			} else {
				System.out.printf("Tolong masukan antara 1 - %d\n", maxMenu);
			}
		}catch(Exception exception) {
			System.out.println("Format yang anda masukan salah, coba lagi.");
		}
		return validation;
	}
	
	public static void mainMenu(Scanner scanner) {
		printMainMenu();
		String input = scanner.nextLine().trim();
		boolean validation = validateInput(input, 4);
		if(validation == false) {
			mainMenu(scanner);
		}
		switch(input) {
			case "1":
				guestMenu(scanner);
			case "2":
				roomMenu(scanner);
			case "3":
				Hotel.printAbout();
				mainMenu(scanner);
			case "4":
				System.exit(0);
			default:
				System.out.println("Ada masalah dengan selection menunya.");
		}			
	}
	public static void guestMenu(Scanner scanner) {
		Hotel.printAllGuests();
		printGuestMenu();
		String input = scanner.nextLine().trim();
		boolean validation = validateInput(input, 3);
		if(validation == false) {
			guestMenu(scanner);
		}
		switch(input) {
			case "1":
				guestDetailMenu(scanner);
			case "2":
				mainMenu(scanner);
			case "3":
				System.exit(0);
			default:
				System.out.println("Ada masalah dengan selection menunya.");			
		}				
	}
	public static void roomMenu(Scanner scanner) {
		Hotel.printAllRooms();
		printRoomMenu();
		String input = scanner.nextLine().trim();
		boolean validation = validateInput(input, 3);
		if(validation == false) {
			roomMenu(scanner);
		}
		switch(input) {
			case "1":
				roomDetailMenu(scanner);
			case "2":
				mainMenu(scanner);
			case "3":
				System.exit(0);
			default:
				System.out.println("Ada masalah dengan selection menunya.");			
		}				
	}
	public static void guestDetailMenu(Scanner scanner) {
		System.out.println("Masukan nomor Register yang anda ingin lihat informasinya:");
		String key = scanner.nextLine().trim().toUpperCase();
		Guest guest = Hotel.findGuest(key);
		guest.printDetail();
		printExitGuestMenu();
		String input = scanner.nextLine().trim();
		boolean validation = validateInput(input, 3);
		if(validation == false) {
			guestMenu(scanner);
		}
		switch(input) {
			case "1":
				guestMenu(scanner);
			case "2":
				mainMenu(scanner);
			case "3":
				System.exit(0);
			default:
				System.out.println("Ada masalah dengan selection menunya.");			
		}			
	}
	public static void roomDetailMenu(Scanner scanner) {
		System.out.println("Masukan nomor Room yang anda ingin lihat informasinya:");
		String key = scanner.nextLine().trim();
		Room room = Hotel.findRoom(key);
		room.printDetailWithHistory();
		printExitGuestMenu();
		String input = scanner.nextLine().trim();
		boolean validation = validateInput(input, 3);
		if(validation == false) {
			roomMenu(scanner);
		}
		switch(input) {
			case "1":
				roomMenu(scanner);
			case "2":
				mainMenu(scanner);
			case "3":
				System.exit(0);
			default:
				System.out.println("Ada masalah dengan selection menunya.");			
		}				
	}
}
