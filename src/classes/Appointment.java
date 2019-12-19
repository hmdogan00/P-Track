package classes;

import java.util.Calendar;

public class Appointment {
    int appointmentNo;
    String patientName, doctorName, doctorRoom, doctorProfession, prescription;
    Calendar time;
    double cost;

    public Appointment( String patientName, Doctor doctor, double cost, int appointmentNo, Calendar time){
        this.appointmentNo = appointmentNo;
        this.patientName = patientName;
        doctorName = doctor.getDName();
        doctorRoom = doctor.getDRoom();
        doctorProfession = doctor.getProfession();
        prescription = "";
        this.cost = cost;
        this.time = time;
    }

    public int getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(int appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorRoom() {
        return doctorRoom;
    }

    public double getCost(){
        return cost;
    }

    public void setDoctorRoom(String doctorRoom) {
        this.doctorRoom = doctorRoom;
    }

    public String getDoctorProfession() {
        return doctorProfession;
    }

    public void setDoctorProfession(String doctorProfession) {
        this.doctorProfession = doctorProfession;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}