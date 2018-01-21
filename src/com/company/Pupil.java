package com.company;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 15 on 19.01.2018.
 */
public class Pupil {


    private String name;
    private String lastName;
    private double readingSpeed;
    private int numberBooksRead;
    private double partBook;
    private Boolean isReading;
    private Integer numberBookReadNow;
    private Calendar dateBirth;

    public void setNumberBookReadNow(Integer numberBookReadNow) {
        this.numberBookReadNow = numberBookReadNow;
    }

    public Calendar getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Calendar dateBirth) {
        this.dateBirth = dateBirth;
    }

    public int getNumberBookReadNow() {
        return numberBookReadNow;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getReadingSpeed() {
        return readingSpeed;
    }

    public void setReadingSpeed(double readingSpeed) {
        this.readingSpeed = readingSpeed;
    }

    public Integer getNumberBooksRead() {
        return numberBooksRead;
    }

    public void setNumberBooksRead(int numberBooksRead) {
        this.numberBooksRead = numberBooksRead;
    }

    public Pupil(String name, String lastName, double readingSpeed, int numberBooksRead, double partBook, Boolean isReading, Integer numberBookReadNow, Calendar dateBirth) {
        this.name = name;
        this.lastName = lastName;
        this.readingSpeed = readingSpeed;
        this.numberBooksRead = numberBooksRead;
        this.partBook = partBook;
        this.isReading = isReading;
        this.numberBookReadNow = numberBookReadNow;
        this.dateBirth = dateBirth;
    }



    public double getPartBook() {
        return partBook;
    }

    public void setPartBook(double partBook) {
        this.partBook = partBook;
    }

    public Boolean getReading() {
        return isReading;
    }

    public void setReading(Boolean reading) {
        isReading = reading;
    }

    public void read()
    {
        partBook += readingSpeed*Math.random();
    }

}
