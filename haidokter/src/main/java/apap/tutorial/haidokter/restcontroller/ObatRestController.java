package apap.tutorial.haidokter.restcontroller;

import apap.tutorial.haidokter.model.ObatModel;
import apap.tutorial.haidokter.model.ResepModel;
import apap.tutorial.haidokter.service.ObatRestService;
import apap.tutorial.haidokter.service.ResepRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

public class ObatRestController {
    @Autowired
    private ObatRestService obatRestService;

    @PostMapping(value = "/obat")
    private ObatModel createObat(@Valid @RequestBody ObatModel obat, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        else {
            return obatRestService.createObat(obat);
        }
    }

    @DeleteMapping(value = "/obat/{idObat}")
    private ResponseEntity<String> deleteObat(@PathVariable("id") Long idObat){
        try{
            obatRestService.deleteObatByID(idObat);
            return ResponseEntity.ok("Obat has been deleted!");
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Obat with ID " + String.valueOf(idObat) + " not found!");
        }
    }

    @GetMapping(value = "/obat/{idObat}")
    private ObatModel retrieveObat(@PathVariable(value = "id") Long idObat){
        try {
            return obatRestService.getObatById(idObat);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Obat with ID " + String.valueOf(idObat) + " not found!");
        }
    }

    @PutMapping(value = "/obat/{idObat}")
    private ObatModel updateObat(@PathVariable(value = "idObat") Long idObat, @RequestBody ObatModel obat){
        try{
            return obatRestService.updateObat(idObat, obat);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Obat with ID " + String.valueOf(idObat) + " not found!");
        }
    }

    @GetMapping(value = "/obat-all")
    private List<ObatModel> retrieveListObat(){return obatRestService.retrieveListObat();}
}
