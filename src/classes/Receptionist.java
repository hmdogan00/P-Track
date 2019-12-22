package classes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class Receptionist
{
    private String userword;
    private String password;
    private String name;
    private char gender;
    private ArrayList<Appointment> appointments;
    private ArrayList<Patient> patients;
    Hospital hospital;

    public Receptionist(Hospital hospital,String userword, String password, String name, char gender)
    {
        this.userword = userword;
        this.password = password;
        this.name = name;
        this.gender = gender;
        appointments = new ArrayList<Appointment>();
        patients = new ArrayList<Patient>();
    }

    public String getName()
    {
        return name;
    }

    public char getGender()
    {
        return gender;
    }
    public void addPatient(String username,String password,String name, String bloodtype, String room, String emergencyContact, String insurance, String  citizenshipID,  String address, int PatientAge, char PatientGender, LocalDate birthDate)
    {
        Patient patient = new Patient(username,password,name,bloodtype,room,emergencyContact,insurance,citizenshipID,address,birthDate,PatientAge,PatientGender);
        patients.add(patient);
    }
    public void addAppointment(Patient patient,Doctor doctor,double cost, Calendar time)
    {
        Appointment appointment = new Appointment(patient.getName(),doctor,cost,time);
        patient.addAppointment(appointment);
        doctor.addAppointment(appointment);
    }
    public void changePatientInfo(Patient patient)
    {




    }
    public void addMoney(double priceOfAppointment)
    {
        hospital.setTotalMoney(hospital.totalMoney + priceOfAppointment);
    }
}

