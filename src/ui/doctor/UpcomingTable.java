package ui.doctor;

public class UpcomingTable {
    String name, lastAppointment, phoneNo;

    public UpcomingTable(String name, String lastAppointment, String phoneNo){
        this.name = name;
        this.lastAppointment = lastAppointment;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastAppointment() {
        return lastAppointment;
    }

    public void setLastAppointment(String lastAppointment) {
        this.lastAppointment = lastAppointment;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
