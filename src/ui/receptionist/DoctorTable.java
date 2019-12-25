package ui.receptionist;

public class DoctorTable {
    String name, department, roomNumber, availability;
    int phoneNo;

    public DoctorTable(String name, String department, String roomNumber, String availability, int phoneNo){
        this.name = name;
        this.department = department;
        this.roomNumber = roomNumber;
        this.availability = availability;
        this.phoneNo = phoneNo;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }


}
