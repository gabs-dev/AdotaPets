package com.adotapets.backend.service;

import com.adotapets.backend.model.Pet;
import com.adotapets.backend.model.User;
import com.adotapets.backend.model.dto.PetDto;
import com.adotapets.backend.model.form.PetForm;
import com.adotapets.backend.repository.PetRepository;
import com.adotapets.backend.repository.UserRepository;
import com.adotapets.backend.util.DateWithTimeZone;
import com.adotapets.backend.util.convert.ConvertToBusiness;
import com.adotapets.backend.util.convert.ConvertToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository repository;

    @Autowired
    UserRepository userRepository;

    public PetDto savePet(PetForm form) {
        Pet pet = ConvertToBusiness.pet(form);
        User user = userRepository.findById(form.getResponsavelId()).get();
        pet.setResponsavel(user);
        if (pet.getId() == null)
            pet.setDataCadastro(DateWithTimeZone.getDateWithTimeZone());
        pet = repository.save(pet);
        return ConvertToDto.pet(pet);
    }

    public List<PetDto> findAllPets() {
        List<Pet> all = repository.findAll();
        return ConvertToDto.petList(all);
    }

    public PetDto findPetById(Long id) {
        Optional<Pet> optional = repository.findById(id);
        if (optional.isPresent())
            return ConvertToDto.pet(optional.get());
        return null;
    }

    public List<PetDto> findPetsByResponsavel(Long responsavelId) {
        List<Pet> all = repository.findPetsByResponsavel(responsavelId);
        return ConvertToDto.petList(all);
    }

}
