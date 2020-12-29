package javaClasses;
public class vehicle 
{
    String vehicleName;
    String vehicleType;
    String vehicleNumber;
    String vehicleColor;
    
    public vehicle()
    {
        
    }

    public vehicle(String vehicleName, String vehicleType, String vehicleNumber, String vehicleColor) {
        this.vehicleName = vehicleName;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.vehicleColor = vehicleColor;
    }
    
    public vehicle(String name, String type)
    {
        vehicleName = name;
        vehicleType = type;
    }
    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    @Override
    public String toString() {
        return "vehicle{" + "vehicleName=" + vehicleName + ", vehicleType=" + vehicleType + ", vehicleNumber=" + vehicleNumber + ", vehicleColor=" + vehicleColor + '}';
    }
    
    
}