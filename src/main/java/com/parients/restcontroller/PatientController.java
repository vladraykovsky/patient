package com.parients.restcontroller;

import com.parients.model.Patient;
import com.parients.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/patient")
public class PatientController {

    PatientRepository patientRepository = new PatientRepository();


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> selectAllPatient(){
        return patientRepository.selectAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{patient_id}" , method = RequestMethod.GET)
    public List<Patient> byid(@PathVariable("patient_id") String patient_id){
        return patientRepository.selectById(Integer.valueOf(patient_id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/update",method = RequestMethod.PATCH)
    public ResponseEntity<String> update(@RequestBody Patient patient){
        System.out.println(patient);
        patientRepository.update(patient);
        System.out.println("update");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody Patient patient){
        patientRepository.add(patient);
        System.out.println("add");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "{patient_id}" ,method = RequestMethod.DELETE)
    public ResponseEntity< String > delete(@PathVariable("patient_id") String patient_id){
        Patient patient = new Patient(Long.valueOf(Integer.parseInt(patient_id))," ",
                " "," "," "," "," "," ");
        patientRepository.delete(patient);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/first",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> getFirstPatientFromDb(){
        return patientRepository.selectFirstPatient();
    }

}
