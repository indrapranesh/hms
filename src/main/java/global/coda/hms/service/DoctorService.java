package global.coda.hms.service;


import global.coda.hms.constant.ApplicationConstant;
import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.DoctorNotFoundException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.mapper.DoctorMapper;
import global.coda.hms.mapper.UserMapper;
import global.coda.hms.model.Doctor;
import global.coda.hms.model.DoctorPatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    UserMapper userMapper;

    public Doctor createDoctor(Doctor doctor) {
        userMapper.createUser(doctor.getUserDetails());
        doctor.setUserId(doctor.getUserDetails().getUserId());
        doctorMapper.createDoctor(doctor);
        return doctor;
    }

    public Doctor readDoctor(int doctorId) throws BusinessException,SystemException{
        Doctor doctor = doctorMapper.readDoctor(doctorId);
        try{
            if(doctor==null){
                throw new DoctorNotFoundException(ApplicationConstant.DOCTOR_NOT_FOUND);
            }
            else{
                return doctor;
            }
        } catch(DoctorNotFoundException e){
            throw  new BusinessException(e.getMessage());
        } catch(Exception e){
            throw new SystemException(e);
        }
    }

    public List<DoctorPatientMapper> listPatients(int doctorId) throws BusinessException,SystemException{
        List<DoctorPatientMapper> listPatients = doctorMapper.listPatients(doctorId);
        return listPatients;
    }

    public Doctor editDoctor(Doctor doctor) throws BusinessException, SystemException{
        try{
            int userChanged = userMapper.updateUser(doctor.getUserDetails());
            int doctorChanged = doctorMapper.editDoctor(doctor);
            if(userChanged == 1 && doctorChanged == 1){
                return readDoctor(doctor.getDoctorId());
            }else{
                throw new SystemException(new Exception(ApplicationConstant.WENT_WRONG));
            }
        }catch(SystemException e){
            throw e;
        }catch(Exception e){
            throw new SystemException(e);
        }
    }

    public boolean deleteDoctor(int doctorId) throws BusinessException,SystemException{

        int doctorDeleted,userDeleted;
        try{
            doctorDeleted = doctorMapper.deleteDoctor(doctorId);
            userDeleted = userMapper.deleteUser(doctorId);
            if (doctorDeleted==1 && userDeleted ==1) {
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
