package classLibrary;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.math.*;
import java.text.*;
import enumLibrary.*;

public class Reservation { //Catatan penyewaan pada satu room.
	private Guest guest;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	public Reservation(Guest guest, LocalDate checkIn) {
		this.guest = guest;
		this.checkIn = checkIn;
	}	
	public Reservation(Guest guest, LocalDate checkIn, LocalDate checkOut) {
		this.guest = guest;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	public String dateLongFormat(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		return formatter.format(date);
	}
	public void printReservation() {
		String reservation = String.format("%s - %s (%s, %s)",
				dateLongFormat(this.checkIn), dateLongFormat(this.checkOut), 
				this.guest.getCompleteName(), this.guest.getRegisterationNumber());
		System.out.println(reservation);
	}
	
}
