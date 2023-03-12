package com.example.csv.controllers;

import com.example.csv.domain.*;
import com.example.csv.helper.CSVHelper;
import com.example.csv.services.CSVService;
import com.example.csv.services.ContratService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/csv/contrat")
@AllArgsConstructor
@Slf4j
public class ContratController {

    @Autowired
    private final ContratService fileService;
    @CrossOrigin
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {

                fileService.saveFile(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                log.info(String.valueOf(e));
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    @CrossOrigin
    @PostMapping
    public Contrat save(@RequestBody Contrat contrat){

        return  fileService.save(contrat);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getContrat(@PathVariable("id") Long id){
        Contrat contrat = fileService.getContrat(id);
        if(contrat.equals(null)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contrat, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteContrat(@PathVariable("id") Long id){
        if(fileService.getContrat(id)== null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        fileService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin
    @PatchMapping("/edit")
    public ResponseEntity<Void> updateContrat(@RequestBody Contrat contrat){

        boolean updated = fileService.update(contrat);
        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<GetAllType<Contrat>> getAllContrats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean asc
    )
    {
        GetAllType<Contrat> data = fileService.getAllContrats(page, size, sortBy, asc);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


}
