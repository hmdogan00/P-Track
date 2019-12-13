package classes;
import java.util.ArrayList;

public class Doctor {
        private String username;
        private String password;
        private String dName;
        private char gender;
        private String dRoom;
        private ArrayList<Appointment> dAppointments;
        private String profession;
        private int availability;

        public Doctor(String username,String password,String dName,char gender,String dRoom,String profession,int availability)
        {
            this.username = username;
            this.password = password;
            this.dName = dName;
            this.gender = gender;
            this.dRoom = dRoom;
            this.profession = profession;
            this.availability = availability;
            dAppointments = new ArrayList<Appointment>();
        }

        public String getDName()
        {
            return dName;
        }

        public char getGender()
        {
            return gender;
        }

        public String getDRoom()
        {
            return dRoom;
        }

        public ArrayList<Appointment> getDAppointment()
        {
            return dAppointments;
        }

        public String getProfession()
        {
            return profession;
        }

        public int getAvailability()
        {
            return availability;
        }

        public void addPrescription(Appointment appointment,String prescription)
        {
            appointment.setPrescription(prescription);
        }

        public void setDRoom(String room)
        {
            dRoom = room;
        }

        public void setAvailability(int availability)
    {
        this.availability = availability;
    }

        public void addAppointment(Appointment appointment)
        {
            dAppointments.add(appointment);
        }
}

