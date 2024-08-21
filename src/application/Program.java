package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Reservation;
import exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		// regrasExcecoesPersonalizadas
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// Vamos trabalhar com data e deixar no formato - dd/MM/yyyy
		
		try {
			System.out.print("Room number: ");
			int number = sc.nextInt();
			System.out.print("Check-in date (dd/MM/yyyy): ");
			Date checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			Date checkOut = sdf.parse(sc.next());
			
			/*
			if(!checkOut.after(checkIn)) { // Se a data de checkOut for menor do que data de CheckIn então
				System.out.println("Error in reservation: Check-out date must be after check-in date");
			} else {
			*/
			
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			
			// Solução 3 = boa
			// tratamento de exceções
			
			reservation.updateDates(checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
			
		}
		catch(ParseException e){
			System.out.println("Invalid date format");
		}
		catch(DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());
		}
		
		
		
		
		/*
		
		// Solução 2 = ruim
		// Método retornando String
		String error = reservation.updateDates(checkIn, checkOut);
		if(error != null) {
			System.out.println("Error in reservation: " + error);
		} else {
			System.out.println("Reservation: " + reservation);
		}
		
		
						
		
		
		/*
		// Solução 1 = ruim
		// Toda a lógica de programação dentro do programa principal
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now)) {
			System.out.println("Error in reservation: Reservation dates for update must be future dates");
		} else if (!checkOut.after(checkIn)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		} else {
			reservation.updateDates(checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
		}
		*/
			
		//}
		
		
		
		
		sc.close();
	}

}
