package com.adotapets.backend.controller;

import com.adotapets.backend.model.dto.PetDto;
import com.adotapets.backend.model.form.PetForm;
import com.adotapets.backend.response.ResponseHandler;
import com.adotapets.backend.service.PetService;
import com.adotapets.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PutMapping
    public ResponseEntity<Object> create(@RequestBody PetForm form) {
        PetDto pet = petService.savePet(form);
        HttpStatus status = pet != null ? HttpStatus.CREATED : HttpStatus.MULTI_STATUS;
        return ResponseHandler.generateResponse("", status, pet);
    }

    @GetMapping
    public ResponseEntity<Object> list() {
        List<PetDto> pets = petService.findAllPets();
        HttpStatus status = pets.size() != 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseHandler.generateResponse("", status, pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        PetDto pet = petService.findPetById(id);
        HttpStatus status = pet != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseHandler.generateResponse("", status, pet);
    }

}
