package javaClasses;

public class admin extends person
{
    String beatName;
    String empID;
    String emailAddress;
    String rank;

    public admin() 
    {
        
    }

    public admin(String first, String cnic, String phoneNumber, int age, String last) {
        super(first, cnic, phoneNumber, age, last);
    }
    
    

    public admin(String fName, String lName, String username, String phoneNumber, String CNIC, int age, String gender,String beatName, String empID, String rank) {
        super(fName, lName, username, phoneNumber, CNIC, age, gender);
        this.beatName = beatName;
        this.empID = empID;
        this.rank = rank;
    }
    
    
    public admin(String fName, String lName, String phoneNumber, String CNIC, int age, String gender)
    {
        super(fName, lName, phoneNumber, CNIC, age, gender);
    }

    public admin(String beatName, String beltNumber, String emailAddress, String rank, String fName, String lName, String username, String password, String phoneNumber, String CNIC, int age, String gender) {
        super(fName, lName, username, password, phoneNumber, CNIC, age, gender);
        this.beatName = beatName;
        this.empID = empID;
        this.emailAddress = emailAddress;
        this.rank = rank;
    }

    public String getBeatName() {
        return beatName;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() 
    {
        return super.toString()+ "admin{" + "beatName=" + beatName + ", empID=" + empID + ", emailAddress=" + emailAddress + ", rank=" + rank + '}';
    }
    
    
    
}
