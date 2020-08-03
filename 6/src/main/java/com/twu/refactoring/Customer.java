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
    private static final double NEW_RELEASE_BONUS_DAY = 1;

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

            thisAmount = getThisAmount(thisAmount, each);

            frequentRenterPoints = getFrequentRenterPoints(frequentRenterPoints, each);

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

    private int getFrequentRenterPoints(int frequentRenterPoints, Rental rental) {
        frequentRenterPoints++;
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                && rental.getDaysRented() > NEW_RELEASE_BONUS_DAY)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private double getThisAmount(double thisAmount, Rental rental) {
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount = getSpecifiedAmount(FIXED_CHARGES_OF_REGULAR, FREE_DAYS_OF_REGULAR, PRICE_OF_REGULAR, rental);
                break;
            case Movie.NEW_RELEASE:
                thisAmount = getSpecifiedAmount(0, 0, PRICE_OF_NEW_RELEASE, rental);
                break;
            case Movie.CHILDRENS:
                thisAmount = getSpecifiedAmount(FIXED_CHARGES_OF_CHILDREN, FREE_DAYS_OF_CHILDREN, PRICE_OF_CHILDREN, rental);
                break;
        }
        return thisAmount;
    }

    private double getSpecifiedAmount(double fixedCharges, int freeDays, double priceEachDay, Rental rental) {
        double thisAmount = fixedCharges;
        if (rental.getDaysRented() > freeDays)
            thisAmount += (rental.getDaysRented() - freeDays) * priceEachDay;
        return thisAmount;
    }

}
