package global.coda.hms.controller;


import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.mapper.UserMapper;
import global.coda.hms.model.Patient;
import global.coda.hms.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/patient")
public class PatientController {

    public static final Logger LOGGER = LogManager.getLogger(PatientController.class);


    @Autowired
    private PatientService patientService;

    @Autowired
    private UserMapper userMapper;

@PostMapping("/create")
    public ResponseEntity<?> createPatient(@RequestBody Patient patient){
        Patient newPatient = patientService.createPatient(patient);
        return new ResponseEntity<Patient>(newPatient, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> readPatient(@PathVariable int patientId, HttpServletRequest request,HttpServletRequest response) throws BusinessException, SystemException{
        Patient patient = patientService.readPatient(patientId);
        String str = (String) request.getAttribute("random");
        System.out.println(str);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }


    @PutMapping("/edit")
    public ResponseEntity<?> editPatient(@RequestBody Patient patient) throws  BusinessException, SystemException{
        Patient updatedPatient = patientService.editPatient(patient);
        return new ResponseEntity<>(updatedPatient,HttpStatus.OK);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable int patientId) throws BusinessException,SystemException{
        boolean deletePatient = patientService.deletePatient(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}



