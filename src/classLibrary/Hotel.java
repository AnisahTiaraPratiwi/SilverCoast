package classLibrary;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.math.*;
import java.text.*;
import enumLibrary.*;

public class Hotel {
	private static String name;
	private static LocalDate establishDate;
	private static String country;
	private static String state;
	private static String city;
	
	private static HashMap<String, Guest> guests;
	private static HashMap<String, Room> rooms;
	
	public static void setHotel() {
		name = "Silver Coast Hotel";
		establishDate = LocalDate.of(1978, 12, 12);
		country = "Australia";
		state = "Queensland";
		city = "Gold Coast";
	}
	public static void setRooms() {
		rooms = new HashMap<String, Room>();
		rooms.put("301", new Room("301", 3, RoomType.REGULAR_SINGLE, new BigDecimal(800000)));
		rooms.put("302", new Room("302", 3, RoomType.REGULAR_DOUBLE, new BigDecimal(1000000)));
		rooms.put("303", new Room("303", 3, RoomType.REGULAR_TWIN, new BigDecimal(1200000)));
		rooms.put("401", new Room("401", 4, RoomType.VIP_SINGLE, new BigDecimal(1000000)));
		rooms.put("402", new Room("402", 4, RoomType.VIP_DOUBLE, new BigDecimal(1200000)));
		rooms.put("403", new Room("403", 4, RoomType.VIP_TWIN, new BigDecimal(1400000)));
	}
	public static void setGuests() {
		guests = new HashMap<String, Guest>();
		Guest danny = new Guest("A021", "Danny", "Tan", LocalDate.of(1990, 11, 23), "Beijing", Gender.MALE, "312008923111990002");
		danny.setRoom(rooms.get("301"), LocalDate.of(2018, 4, 12), LocalDate.of(2018, 4, 14));
		
		Guest dessy = new Guest("A022", "Dessy", "Wang", LocalDate.of(1990, 11, 11), "Beijing", Gender.FEMALE, "312008911111990002");
		dessy.setRoom(rooms.get("301"), LocalDate.of(2018, 4, 12), LocalDate.of(2018, 4, 14));
		
		Guest sunarti = new Guest("A023", "Sunarti", "Wijaya", LocalDate.of(1985, 4, 18), "Bandung", Gender.FEMALE, "312008923111990002");
		sunarti.setRoom(rooms.get("302"), LocalDate.of(2018, 5, 15), LocalDate.of(2018, 5, 17));
		
		Guest ardi = new Guest("A024", "Ardi", "Sanjaya", LocalDate.of(1985, 8, 1), "Jakarta", Gender.MALE, "31200890108190002");
		ardi.setRoom(rooms.get("302"), LocalDate.of(2018, 5, 15), LocalDate.of(2018, 5, 17));
		
		Guest muliawan = new Guest("A025", "Muliawan", "Sanjaya", LocalDate.of(2000, 10, 10), "Jakarta", Gender.MALE, "3120089010102000002");
		muliawan.setRoom(rooms.get("302"), LocalDate.of(2018, 5, 15), LocalDate.of(2018, 5, 17));
		
		Guest tirta = new Guest("A026", "Tirta", "Raharja", LocalDate.of(1988, 10, 14), "Jakarta", Gender.MALE, "3120089014101988002");
		tirta.setRoom(rooms.get("401"), LocalDate.of(2018, 5, 15), LocalDate.of(2018, 5, 18));
		
		danny.setFamilies(new LinkedList<Guest>(Arrays.asList(new Guest[] {dessy})));
		dessy.setFamilies(new LinkedList<Guest>(Arrays.asList(new Guest[] {danny})));
		sunarti.setFamilies(new LinkedList<Guest>(Arrays.asList(new Guest[] {ardi, muliawan})));
		ardi.setFamilies(new LinkedList<Guest>(Arrays.asList(new Guest[] {sunarti, muliawan})));
		muliawan.setFamilies(new LinkedList<Guest>(Arrays.asList(new Guest[] {sunarti, ardi})));		
		
		guests.put(danny.getRegisterationNumber(), danny);
		guests.put(dessy.getRegisterationNumber(), dessy);
		guests.put(sunarti.getRegisterationNumber(), sunarti);
		guests.put(ardi.getRegisterationNumber(), ardi);
		guests.put(muliawan.getRegisterationNumber(), muliawan);
		guests.put(tirta.getRegisterationNumber(), tirta);
	}
	public static void initialization() {
		setHotel();
		setRooms();
		setGuests();
	}
	public static String establishDateLongFormat() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		return formatter.format(establishDate);
	}	
	public static void printAbout() {
		String about = String.format("Hotel ini bernama %s. Sudah didirikan sejak %s di %s, %s, di kota %s",
				name, establishDateLongFormat(), country, state, city);
		System.out.println(about);
	}
	public static void printAllGuests() {
		for(Guest guest : guests.values()) {
			guest.printSummary();
		}
	}
	public static void printAllRooms() {
		LinkedList<Room> registeredRooms = new LinkedList<Room>(rooms.values());
		StringBuilder output = new StringBuilder();
		printRooms(registeredRooms, output);
	}	
	public static Guest findGuest(String key) {
		return guests.get(key);
	}
	public static Room findRoom(String key) {
		return rooms.get(key);
	}
	
	/*3 functions dibawah digunakan untuk algorithm recursive untuk print room sederetan.*/
	private static void printRooms(LinkedList<Room> roomList, StringBuilder output) {
		if(roomList.size() == 0) {
			System.out.println(output);
		} else {
			groupingRoom(roomList, output);
		}	
	}
	private static void groupingRoom(LinkedList<Room> roomList, StringBuilder output) {
		LinkedList<Room> removedRoom = new LinkedList<Room>();
		int level = roomList.getFirst().getLevel();
		String header = String.format("Lantai %d: %s", level, roomList.getFirst().getRoomNumber());
		roomList.removeFirst();
		output.append(header);
		for(Room room : roomList) {
			if(room.getLevel() == level) {
				output.append(", ");
				output.append(room.getRoomNumber());
				removedRoom.add(room);			
			}
		}
		roomList = removingRooms(roomList, removedRoom); //Menghapus seluruh room yang sudah di print.
		output.append("\n");
		printRooms(roomList, output);
	}
	private static LinkedList<Room> removingRooms(LinkedList<Room> roomList, LinkedList<Room> removedRoom){
		for(Room room: removedRoom) {
			roomList.remove(room);
		}
		return roomList;
	}
	

}
