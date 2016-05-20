package ro.pub.cs.systems.eim.practicaltest02;

/**
 * Created by bordin on 5/20/16.
 */
public class TimeNow {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int seccond;

    public TimeNow(int year, int month, int day, int hour, int minute, int seccond) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.seccond = seccond;

    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSeccond() {
        return seccond;
    }
    public void setSeccond(int seccond) {
        this.seccond = seccond;
    }
}
