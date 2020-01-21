package global.coda.hms.model;

import java.util.List;

public final class DoctorPatientMapper {
    Doctor doctor;
    List<Patient> patient;

    public DoctorPatientMapper() {

    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Patient> getPatient() {
        return patient;
    }

    public void setPatient(List<Patient> patient) {
        this.patient = patient;
    }
}
