package global.coda.hms.controller;


import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.mapper.UserMapper;
import global.coda.hms.model.Doctor;
import global.coda.hms.model.DoctorPatientMapper;
import global.coda.hms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor) throws BusinessException,SystemException{
        Doctor newDoctor = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(newDoctor, HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<?> readDoctor(@PathVariable int doctorId) throws BusinessException,SystemException {
        Doctor doctor = doctorService.readDoctor(doctorId);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }


    @PutMapping("/edit")
    public ResponseEntity<?> editDoctor(@RequestBody Doctor doctor) throws  BusinessException, SystemException{
        Doctor updatedDoctor = doctorService.editDoctor(doctor);
        return new ResponseEntity<>(updatedDoctor,HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable int doctorId) throws BusinessException,SystemException{
        boolean deleteDoctor = doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/patients")
    public ResponseEntity<?> getPatients(@RequestParam(value = "DoctorId",required = false) Integer doctorId) throws BusinessException,SystemException{
        if(doctorId == null){
            doctorId=0;
        }

        List<DoctorPatientMapper> listPatients = doctorService.listPatients(doctorId);
        return  new ResponseEntity<>(listPatients,HttpStatus.OK);
    }
}



