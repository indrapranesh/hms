package global.coda.hms.service;


import global.coda.hms.constant.ApplicationConstant;
import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.PatientNotFoundException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.mapper.PatientMapper;
import global.coda.hms.mapper.UserMapper;
import global.coda.hms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientMapper patientMapper;

    @Autowired
    UserMapper userMapper;

    public Patient createPatient(Patient patient) {
        userMapper.createUser(patient.getUserDetails());
        patient.setUserId(patient.getUserDetails().getUserId());
        patientMapper.createPatient(patient);
        return patient;
    }

    public Patient readPatient(int patientId) throws BusinessException,SystemException {
        Patient patient = patientMapper.readPatient(patientId);
        try{
            if(patient==null){
                throw new PatientNotFoundException(ApplicationConstant.PATIENT_NOT_FOUND);
            }
            else{
                return patient;
            }
        } catch(PatientNotFoundException e){
            throw  new BusinessException(e.getMessage());
        } catch(Exception e){
            throw new SystemException(e);
        }
    }

    public Patient editPatient(Patient patient) throws BusinessException, SystemException{
        try{
            int userChanged = userMapper.updateUser(patient.getUserDetails());
            int patientChanged = patientMapper.editPatient(patient);
            if(userChanged == 1 && patientChanged == 1){
                return readPatient(patient.getPatientId());
            }else{
                throw new SystemException(new Exception(ApplicationConstant.WENT_WRONG));
            }
        }catch(SystemException e){
            throw e;
        }catch(Exception e){
            throw new SystemException(e);
        }
    }

    public boolean deletePatient(int patientId) throws BusinessException,SystemException{

        int patientDeleted,userDeleted;
        try{
            patientDeleted = patientMapper.deletePatient(patientId);
            userDeleted = userMapper.deleteUser(patientId);
            if (patientDeleted==1 && userDeleted ==1) {
                return true;
            }
            else {
                return false;
            }
        }catch(Exception e){
            throw new SystemException(e);
        }

    }
}
