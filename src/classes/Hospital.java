package classes;

import java.util.ArrayList;
import java.util.Calendar;

public class Hospital {
    String hospitalName;
    ArrayList<String> rooms;
    static ArrayList<Patient> patientList;
    static ArrayList<Doctor> doctorList;
    static ArrayList<Receptionist> receptionistList;
    Calendar time;
    double totalMoney;

    public Hospital(String hospitalName, ArrayList<String> rooms, ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Receptionist> receptionistList, double totalMoney) {
        this.hospitalName = hospitalName;
        this.rooms = rooms;
        this.patientList = patientList;
        this.doctorList = doctorList;
        this.receptionistList = receptionistList;
        this.totalMoney = totalMoney;
        time = Calendar.getInstance();
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public ArrayList<String> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<String> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(ArrayList<Patient> patientList) {
        this.patientList = patientList;
    }

    public ArrayList<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(ArrayList<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public ArrayList<Receptionist> getReceptionistList() {
        return receptionistList;
    }

    public void setReceptionistList(ArrayList<Receptionist> receptionistList) {
        this.receptionistList = receptionistList;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void updateTime(){
        time = Calendar.getInstance();
    }
}
