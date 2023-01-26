package classLibrary;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.math.*;
import java.text.*;
import enumLibrary.*;

public class Room {
	private String roomNumber;
	private int level;
	private RoomType type;
	private BigDecimal price;
	private LinkedList<Reservation> reservationHistories;
	
	public Room(String roomNumber, int level, RoomType type, BigDecimal price) {
		this.roomNumber = roomNumber;
		this.level = level;
		this.type = type;
		this.price = price;
		this.reservationHistories = new LinkedList<Reservation>();
	}
	public String getRoomNumber() {
		return this.roomNumber;
	}
	public int getLevel() {
		return this.level;
	}
	public void addReservation(Reservation reservation) {
		this.reservationHistories.add(reservation);
	}	
	public BigDecimal getPrice() {
		return this.price;
	}
	public String getRoomType() {
		switch(this.type) {
			case REGULAR_DOUBLE:
				return "Regular Double Bed";
			case REGULAR_SINGLE:
				return "Regular Single Bed";
			case REGULAR_TWIN:
				return "Regular Twin Bed";
			case VIP_DOUBLE:
				return "VIP Double Bed";
			case VIP_SINGLE:
				return "VIP Single Bed";
			case VIP_TWIN:
				return "VIP Twin Bed";
			default:
				return "Tidak ter-record";
		}
	}
	public void printDetail() { //print Detail dipakai untuk detail Room dan juga untuk detail tamu
		System.out.println("\nMenginap di:");
		String summary = String.format("Room Number: %s\nFloor: %d\nRoom Type: %s", this.roomNumber, this.level, this.getRoomType());
		System.out.println(summary);
	}
	public void printDetailWithHistory() { //Function ini yang dipakai untuk print detail room.
		this.printDetail();
		System.out.println("\nReservation History:");
		for(Reservation reservation : this.reservationHistories) {
			reservation.printReservation();
		}
	}
}
