package classes;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Patient
{
    private String  name, bloodType, emergencyContact, insurance, address;
    private int age;
    private String gender;
    private ArrayList<Appointment> appointments;
    private ArrayList<String> oldPrescriptions, nextPrescriptions;
    private ArrayList<Doctor> doctors;
    private LocalDate birthDate;
    private int citizenshipID;
    private int phoneNumber;

    public Patient(String name, String bloodType, String emergencyContact, String insurance, int citizenshipID, String address, String birthDate, String gender, int phoneNumber) {

        this.name = name;
        this.bloodType = bloodType;
        this.emergencyContact = emergencyContact;
        this.insurance = insurance;
        this.citizenshipID = citizenshipID;
        this.address = address;
        this.gender = gender;
        this.appointments = appointments;
        this.oldPrescriptions = oldPrescriptions;
        this.nextPrescriptions = nextPrescriptions;
        this.doctors = doctors;
        this.phoneNumber = phoneNumber;
        LocalDate localBirthDate = LocalDate.parse(birthDate);
        this.birthDate = localBirthDate;
        updateAge();
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

    public String getName() {
        return name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getInsurance() {
        return insurance;
    }

    public int getCitizenshipID() {
        return citizenshipID;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getPhoneNumber() {return phoneNumber;}

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

    public void setGender(String gender) {
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

    public void setPhoneNumber(int phoneNumber){this.phoneNumber = phoneNumber;}
    public void updateAge()
    {
        LocalDate now = LocalDate.now();
        Period age = Period.between(birthDate, now);
        this.age = age.getYears();
    }
}
