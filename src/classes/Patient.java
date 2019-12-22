package classes;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;

public class Patient
{
    private String userName, password, name, bloodType, room, emergencyContact, insurance, citizenshipID, address;
    private int age;
    private char gender;
    private ArrayList<Appointment> appointments;
    private ArrayList<String> oldPrescriptions, nextPrescriptions;
    private ArrayList<Doctor> doctors;
    private LocalDate birthDate;

    public Patient(String userName, String password, String name, String bloodType, String room, String emergencyContact, String insurance, String citizenshipID, String address, LocalDate birthDate, int age, char gender) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.bloodType = bloodType;
        this.room = room;
        this.emergencyContact = emergencyContact;
        this.insurance = insurance;
        this.citizenshipID = citizenshipID;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.appointments = appointments;
        this.oldPrescriptions = oldPrescriptions;
        this.nextPrescriptions = nextPrescriptions;
        this.doctors = doctors;
        this.birthDate = birthDate;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<String> getOldPrescriptions() {
        return oldPrescriptions;
    }

    public ArrayList<String> getNextPrescriptions() {
        return nextPrescriptions;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getRoom() {
        return room;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getCitizenshipID() {
        return citizenshipID;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void setOldPrescriptions(ArrayList<String> oldPrescriptions) {
        this.oldPrescriptions = oldPrescriptions;
    }

    public void setNextPrescriptions(ArrayList<String> nextPrescriptions) {
        this.nextPrescriptions = nextPrescriptions;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void updateAge()
    {
        LocalDate now = LocalDate.now();
        Period age = Period.between(birthDate, now);
        this.age = age.getYears();
    }
}
