package javaClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class person 
{
    Connection connection = null;
    Statement statement = null;
    PreparedStatement prepareStatement = null;
    ResultSet resultSet = null;
    String first;
    String last;
    String username;
    String password;
    String phoneNumber;
    String CNIC;
    int age;
    String gender;

    public person() 
    {
        
    }

    public person(String username, String password, String first, String last, int age, String CNIC) {
        this.username = username;
        this.password = password;
        this.first = first;
        this.last = last;
        this.age = age;
        this.CNIC = CNIC;
    }

    public person(String first, String last, String username, String phoneNumber, String CNIC, int age, String gender) {
        this.first = first;
        this.last = last;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.CNIC = CNIC;
        this.age = age;
        this.gender = gender;
    }

    public person(String first, String cnic,String phoneNumber, int age,String last)
    {
        this.first = first;
        this.CNIC = cnic;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.last = last;
    }
    
    public person(String first, String last, String username, String password, String phoneNumber, String CNIC, int age, String gender) {
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.CNIC = CNIC;
        this.age = age;
        this.gender = gender;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public PreparedStatement getPrepareStatement() {
        return prepareStatement;
    }

    public void setPrepareStatement(PreparedStatement prepareStatement) {
        this.prepareStatement = prepareStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public final void  setLast(String last) {
        this.last = last;
    }

    public final void setFirst(String first) {
        this.first = first;
    }

    public final String getLast() {
        return last;
    }

    public final String getFirst() {
        return first;
    }

    @Override
    public String toString() {
        return "person{" + "connection=" + connection + ", statement=" + statement + ", prepareStatement=" + prepareStatement + ", resultSet=" + resultSet + ", fName=" + first + ", lName=" + last + ", username=" + username + ", password=" + password + ", phoneNumber=" + phoneNumber + ", CNIC=" + CNIC + ", age=" + age + ", gender=" + gender + '}';
    }
    
}