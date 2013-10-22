/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 10/20/13
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */

import com.sun.glass.events.CustomEvent;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Dates {

    public static void main(String[] args){
        Dates dates = new Dates();
        Boolean exit = false;

        while(exit == false){
            System.out.println("What would you like to do?\n    1. Find out how old you are to the day\n    2. Find out how long until your anniversary \n    3. Compare two dates \n    4. End the program (you may also enter any other number to exit)");
            Scanner input = new Scanner(System.in);
            int userInput = input.nextInt();

            switch(userInput){
                case 1:
                    System.out.println("Enter in the following data in order: The day, month, and year of your birth:");
                    int birthDay = input.nextInt();
                    int birthMonth = input.nextInt();
                    int birthYear = input.nextInt();

                    dates.calculateBirthday(new CustomDate(birthDay, birthMonth, birthYear, 0, 0, 0));
                    break;
                case 2:
                    System.out.println("Enter in the following data in order: The day, month, and year of your anniversary");
                    int annDay = input.nextInt();
                    int annMonth = input.nextInt();
                    int annYear = input.nextInt();

                    dates.calculateAnniversary(new CustomDate(annDay, annMonth, annYear, 0, 0, 0));
                    break;
                case 3:
                    System.out.println("Enter in the following data in order: The day, month, and year of the first date");
                    int day1 = input.nextInt();
                    int month1 = input.nextInt();
                    int year1 = input.nextInt();
                    System.out.println("Enter in the following data in order: The day, month, and year of the second date");
                    int day2 = input.nextInt();
                    int month2 = input.nextInt();
                    int year2 = input.nextInt();

                    dates.compareDates(new CustomDate(day1, month1, year1, 0, 0, 0), new CustomDate(day2, month2, year2, 0, 0, 0));
                    break;
                default:
                    System.out.println("Program has ended");
                    exit = true;
                    break;
            }
        }
    }

    public void calculateBirthday(CustomDate birthDate){
        CustomDate currentDate = new CustomDate();
        CustomDate newDate = currentDate.compareDate(birthDate);

        System.out.println("You are " + newDate.getYear() + " year(s), " + newDate.getMonth() + " month(s), and " + newDate.getDay() + " day(s) old.");
    }

    public void calculateAnniversary(CustomDate anniversaryDate){
        CustomDate currentDate = new CustomDate();
        CustomDate newDate = currentDate.findNextOccurringDate(anniversaryDate);

        System.out.println("The next occurring day of the given anniversary will be in " + newDate.getYear() + " year(s), " + newDate.getMonth() + " month(s) " + newDate.getDay() + " day(s), " + newDate.getHour() + " hour(s),  " + newDate.getMinute() + " minutes(s) and " + newDate.getSecond() + " second(s)");
    }

    public void compareDates(CustomDate date1, CustomDate date2){
        CustomDate newDate = date1.compareDate(date2);

        System.out.println("The difference between these two dates are: " + newDate.getYear() + " year(s), " + newDate.getMonth() + " month(s), and " + newDate.getDay() + " day(s) old.");
    }
}
