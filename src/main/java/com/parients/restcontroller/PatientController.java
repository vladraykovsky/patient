package com.parients.restcontroller;

import com.parients.model.Patient;
import com.parients.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class PatientController {

    PatientRepository patientRepository = new PatientRepository();

    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String welcome(){
        return "hello vlad";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> selectAllPatient(){
        return patientRepository.selectAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{patient_id}")
    public List<Patient> byid(@PathVariable("patient_id") String patient_id){
        return patientRepository.selectById(Integer.valueOf(patient_id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<String> update(@RequestBody Patient patient){
        System.out.println(patient);
        patientRepository.update(patient);
        System.out.println("update");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody Patient patient){
        patientRepository.add(patient);
        System.out.println("add");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity< String > delete(@RequestBody Patient patient){
        System.out.println(patient);
        patientRepository.delete(patient);
        System.out.println("delete");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/first",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> getFirstPatientFromDb(){
        return patientRepository.selectFirstPatient();
    }




    @RequestMapping(value = "/api/reset",method = RequestMethod.GET)
    public  String deletedata(){
        patientRepository.resetDataBase();
        return "database is reseted";
    }

}
