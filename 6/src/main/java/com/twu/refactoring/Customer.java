package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {
	private static final double PRICE_OF_REGULAR = 1.5;
	private static final double PRICE_OF_CHILDREN = 1.5;
	private static final double PRICE_OF_NEW_RELEASE = 3.0;
	private static final int FREE_DAYS_OF_REGULAR = 2;
	private static final int FREE_DAYS_OF_CHILDREN = 3;
	private static final double FIXED_CHARGES_OF_CHILDREN = 1.5;
	private static final double FIXED_CHARGES_OF_REGULAR = 2.0;

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();

			// determine amounts for each line
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += FIXED_CHARGES_OF_REGULAR;
				if (each.getDaysRented() > FREE_DAYS_OF_REGULAR)
					thisAmount += (each.getDaysRented() - 2) * PRICE_OF_REGULAR;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * PRICE_OF_NEW_RELEASE;
				break;
			case Movie.CHILDRENS:
				thisAmount += FIXED_CHARGES_OF_CHILDREN;
				if (each.getDaysRented() > FREE_DAYS_OF_CHILDREN)
					thisAmount += (each.getDaysRented() - FREE_DAYS_OF_CHILDREN) * PRICE_OF_CHILDREN;
				break;

			}

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
					&& each.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;

		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

}
