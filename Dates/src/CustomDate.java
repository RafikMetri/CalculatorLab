import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 10/20/13
 * Time: 7:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomDate {

    private int day, month, year, hour, minute, second;
    private int currentDay, currentMonth, currentYear, currentHour, currentMinute, currentSecond;
    private String currentMonthString, monthString;

    public CustomDate(){
        updateCurrentDate();
        setToCurrentDate();
    }

    public CustomDate(int _day, int _month, int _year, int _hour, int _minute, int _second){
        day = _day;
        month = _month;
        year = _year;
        hour = _hour;
        minute = _minute;
        second = _second;
        updateCurrentDate();
    }

    //sets this date to the current date
    private void setToCurrentDate(){
        day = currentDay;
        month = currentMonth;
        year = currentYear;
        hour = currentHour;
        minute = currentMinute;
        second = currentSecond;
        findMonthString();
        updateCurrentDate();
    }

    //updates the "current date and time" values to the actual current date
    private void updateCurrentDate(){
        SimpleDateFormat _currentDate = new SimpleDateFormat("dd-mm-yyyy");
        StringBuffer _currentDateString = new StringBuffer(String.valueOf(_currentDate.get2DigitYearStart()));

        //create currentDay
        _currentDateString.delete(0, 8);
        currentDay = Integer.parseInt(String.valueOf(_currentDateString.delete(2, 30)));

        //reset
        _currentDateString.delete(0, 30);
        _currentDateString.append(String.valueOf(_currentDate.get2DigitYearStart()));

        //create currentYear
        currentYear = Integer.parseInt(String.valueOf(_currentDateString.delete(0, 24)));

        //reset
        _currentDateString.delete(0, 30);
        _currentDateString.append(String.valueOf(_currentDate.get2DigitYearStart()));

        //create currentHour
        _currentDateString.delete(0, 11);
        currentHour = Integer.parseInt(String.valueOf(_currentDateString.delete(2,30)));

        //reset
        _currentDateString.delete(0, 30);
        _currentDateString.append(String.valueOf(_currentDate.get2DigitYearStart()));

        //create currentMinute
        _currentDateString.delete(0, 14);
        currentMinute = Integer.parseInt(String.valueOf(_currentDateString.delete(2,30)));

        //reset
        _currentDateString.delete(0, 30);
        _currentDateString.append(String.valueOf(_currentDate.get2DigitYearStart()));

        //create currentSecond
        _currentDateString.delete(0, 17);
        currentSecond = Integer.parseInt(String.valueOf(_currentDateString.delete(2,30)));

        //reset
        _currentDateString.delete(0, 30);
        _currentDateString.append(String.valueOf(_currentDate.get2DigitYearStart()));

        //create currentMonthString
        //this is done since simpleDateFormat is returning abbreviations instead of numbers
        _currentDateString.delete(0, 4);
        currentMonthString = String.valueOf(_currentDateString.delete(3, 30));

        //sets currentMonth(int) based on currentMonthString
        if (currentMonthString.equals("Jan")) {
            currentMonth = 1;

        } else if (currentMonthString.equals("Feb")) {
            currentMonth = 2;

        } else if (currentMonthString.equals("Mar")) {
            currentMonth = 3;

        } else if (currentMonthString.equals("Apr")) {
            currentMonth = 4;

        } else if (currentMonthString.equals("May")) {
            currentMonth = 5;

        } else if (currentMonthString.equals("Jun")) {
            currentMonth = 6;

        } else if (currentMonthString.equals("Jul")) {
            currentMonth = 7;

        } else if (currentMonthString.equals("Aug")) {
            currentMonth = 8;

        } else if (currentMonthString.equals("Sep")) {
            currentMonth = 9;

        } else if (currentMonthString.equals("Oct")) {
            currentMonth = 10;

        } else if (currentMonthString.equals("Nov")) {
            currentMonth = 11;

        } else if (currentMonthString.equals("Dec")) {
            currentMonth = 12;
        }
    }

    private void findMonthString(){
        //gets an abbreviation of the month based on the integer of the current month
        //this is used when a date is given and is not defaulted to the current date and time
        switch(month){
            case 1:
                monthString = "Jan";
                break;
            case 2:
                monthString = "Feb";
                break;
            case 3:
                monthString = "Mar";
                break;
            case 4:
                monthString = "Apr";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "Jun";
                break;
            case 7:
                monthString = "Jul";
                break;
            case 8:
                monthString = "Aug";
                break;
            case 9:
                monthString = "Sep";
                break;
            case 10:
                monthString = "Oct";
                break;
            case 11:
                monthString = "Nov";
                break;
            case 12:
                monthString = "Dec";
                break;
        }
    }

    public CustomDate compareDate(CustomDate date){

        //finds the difference between all values
        int dayDifference = Math.abs(this.day - date.day),
            monthDifference = Math.abs(this.month - date.month),
            yearDifference = Math.abs(this.year - date.year) - 1,
            secondDifference = Math.abs(this.second - date.second),
            minuteDifference = Math.abs(this.minute - date.minute),
            hourDifference = Math.abs(this.hour - date.hour);

        //accounts for when a day goes above the current months day limit
        //if the month is February
        if( month == 2){
            monthDifference++;
            //if it is a leap year
            if(dayDifference > 29 && yearDifference % 4 == 0){
                dayDifference -= 29;
                dayDifference *= -1;
            }
            //if it is not a leap year
            else if(dayDifference > 28 && yearDifference % 4 != 0){
                dayDifference -= 28;
                dayDifference *= -1;
            }
        }
        //if the month is an even month
        else if(dayDifference > 30 && month % 2 == 0){
            monthDifference++;
            dayDifference -= 31;
            dayDifference *= -1;
        }
        //if the month is an odd month
        else if(dayDifference > 31 && month % 2 != 0){
            monthDifference++;
            dayDifference -= 30;
            dayDifference *= -1;
        }

        //accounts for when the month goes above 12
        if(monthDifference > 12){
            monthDifference -= 12;
            monthDifference *= -1;
            yearDifference++;
        }

        //if a year goes below 0 (if the first date is larger than the second date)
        if(yearDifference < 0)
            yearDifference *= -1;

        return new CustomDate(dayDifference, monthDifference, yearDifference, hourDifference, minuteDifference, secondDifference);
    }

    public CustomDate findNextOccurringDate(CustomDate date){
        CustomDate newDate;

        //accounts for when the day goes above its month limit
        //if the month is February
        if(date.day > currentDay && currentMonth == 2){
            //if it is a leap year
            if(currentYear % 4 == 0)
                date.day += 29;
            //if it is not a leap year
            else
                date.day += 28;
        }

        //if it is an even month
        else if(date.day > currentDay && currentMonth % 2 == 0)
            date.day += 30;
        //if it is an odd month
        else if(date.day > currentDay && currentMonth % 2 != 0)
            date.day += 31;

        //accounts for when the month goes above 12
        if(date.month < currentMonth)
            date.month += 12;

        //if "date" is before "this"
        if(date.before(this)){
            newDate = new CustomDate(date.day, date.month, currentYear + 1, 0, 0, 0);
        }
        //if the "date" is after "this"
        else
            newDate = new CustomDate(Math.abs(date.day - currentDay), Math.abs(date.month - currentMonth), currentYear - 1, 0, 0, 0);

        return newDate.compareDate(getCurrentDate());
    }

    //checks to see if a given date is before this date
    public Boolean before(CustomDate date){
        Boolean _before = false;

        if(this.year < date.year)
            _before = true;
        else if(this.year == date.year && this.month < date.month)
            _before = true;
        else if(this.year == date.year && this.month == date.month && this.day < date.month)
            _before = true;

        return _before;
    }

    //getters
    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public int getSecond(){
        return second;
    }
    public int getMinute(){
        return minute;
    }
    public int getHour(){
        return hour;
    }

    public CustomDate getCurrentDate(){
        return new CustomDate(currentDay, currentMonth, currentYear, currentHour, currentMinute, currentSecond);
    }

    @Override
    public String toString(){
        return month + " " + monthString + " " + day + ", " + year + " - " + hour + ":" + minute + ":" + second;
    }
}
