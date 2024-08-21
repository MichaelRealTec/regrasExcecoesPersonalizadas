package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import exceptions.DomainException;

public class Reservation {
	// Atributo
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private int number;
	private Date checkIn;
	private Date checkOut;
	
	// Método Getter e Setter	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	
	public Date getCheckIn() {
		return checkIn;
	}
	
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	
	public Date getCheckOut() {
		return checkOut;
	}
	
	// Método Construtor
	public Reservation() {
		
	}
	public Reservation(int number, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}		
		this.number = number;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	// Método	
	@Override
	public String toString() {
		return "Room "
				+ number
				+ ", "
				+ "check-in: "
				+ sdf.format(checkIn) // Colocando no formato Date ("dd/MM/yyyy")
				+ ", "
				+ "check-out: "
				+ sdf.format(checkOut) // Colocando no formato Date ("dd/MM/yyyy")
				+ ", "
				+ duration()
				+ " nights";
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // converte tempo final - inicial hospedado  e converte para o formato
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		// Delegamos a lógica de váliação para a Classe
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		} if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}	
}
