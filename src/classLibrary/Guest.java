package classLibrary;

import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.math.*;
import java.text.*;
import enumLibrary.*;

public class Guest {
	private String registerationNumber;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String birthCity;
	private Gender gender;
	private String idCardNumber;
	private LinkedList<Guest> families;
	private Room currentRoom;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	public Guest(String registerationNumber, String firstName, String lastName, LocalDate birthDate, String birthCity, Gender gender,
			String idCardNumber) {
		this.registerationNumber = registerationNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.birthCity = birthCity;
		this.gender = gender;
		this.idCardNumber = idCardNumber;
	}
	
	public String getRegisterationNumber() {
		return this.registerationNumber;
	}
	public void setFamilies(LinkedList<Guest> families) {
		this.families = families;
	}	
	
	/* function setRoom ada 2, dimana di-set bersamaan dengan tanggal checkout atau tidak.
	 * Ketika program setRoom untuk seorang tamu, otomatis system akan save tamu ini sebagai 
	 * reservation history di dalam Room.
	 * */
	public void setRoom(Room currentRoom, LocalDate checkIn) {
		this.currentRoom = currentRoom;
		this.checkIn = checkIn;
		this.currentRoom.addReservation(new Reservation(this, this.checkIn));
	}	
	public void setRoom(Room currentRoom, LocalDate checkIn, LocalDate checkOut) {
		this.currentRoom = currentRoom;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.currentRoom.addReservation(new Reservation(this, this.checkIn, this.checkOut));
	}	
	
	public long getAge() {
		return ChronoUnit.YEARS.between(this.birthDate, LocalDate.now());
	}
	public String getCompleteName() {
		return String.format("%s %s", this.firstName, this.lastName);
	}
	public void printSummary() {
		String summary = String.format("%s dengan Nomor Register: %s", this.getCompleteName(), this.registerationNumber);
		System.out.println(summary);
	}
	public long getStayDuration() {
		if(this.checkOut == null) {
			return ChronoUnit.DAYS.between(this.checkIn, LocalDate.now());
		}
		return ChronoUnit.DAYS.between(this.checkIn, this.checkOut);
	}
	public BigDecimal calculatePrice() {
		BigDecimal duration = new BigDecimal(this.getStayDuration());
		BigDecimal price = duration.multiply(this.currentRoom.getPrice());
		return price;
	}
	public String priceInRupiah() {
		Locale indonesia = new Locale("id", "ID");
		NumberFormat formatter = NumberFormat.getInstance(indonesia);
		return formatter.format(this.calculatePrice());
	}
	public String getGender() {
		switch(this.gender) {
			case MALE:
				return "Laki-laki";
			case FEMALE:
				return "Perempuan";
			default:
				return "Tidak ter-record";
		}
	}
	public String birthDateLongFormat() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		return formatter.format(this.birthDate);
	}
	public String getBirthInformation() {
		String birthInformation = String.format("%s, %s (%d tahun)", this.birthCity, this.birthDateLongFormat(), this.getAge());
		return birthInformation;
	}
	public void printDetail() {
		String detail = String.format("First Name: %s\nLast Name: %s\nGender: %s\nBirth Information: %s\nID Card: %s\nMenginap selama: %d hari\nBiaya penginapan: %s", 
				this.firstName, this.lastName, this.getGender(), this.getBirthInformation(), this.idCardNumber, this.getStayDuration(), this.priceInRupiah());
		System.out.println(detail);
		this.currentRoom.printDetail();
		System.out.println("\nAnggota Keluarga:");
		for(Guest family : this.families) {
			family.printSummary();
		}
	}
	
}
