package javaClasses;
public class chalan 
{
    String code;
    String description;
    int penalty;
    String date;
    String time;
    int hour;
    int minute;
    String am_pm;

    
    public chalan()
    {
        
    }
    
    public chalan(String reason, String date, String time)
    {
        description = reason;
        this.date = date;
        this.time = time;
    }
    public chalan(String code, String description, int penalty, String date, String time, int hour, int minute, String am_pm) {
        this.code = code;
        this.description = description;
        this.penalty = penalty;
        this.date = date;
        this.time = time;
        this.hour = hour;
        this.minute = minute;
        this.am_pm = am_pm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getAm_pm() {
        return am_pm;
    }

    public void setAm_pm(String am_pm) {
        this.am_pm = am_pm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "chalan{" + "code=" + code + ", description=" + description + ", penalty=" + penalty + ", date=" + date + ", time=" + time + ", hour=" + hour + ", minute=" + minute + ", am_pm=" + am_pm + '}';
    }

    
    
    
    
}
