package com.adotapets.backend.util.convert;

import com.adotapets.backend.model.Pet;
import com.adotapets.backend.model.User;
import com.adotapets.backend.model.dto.PetDto;
import com.adotapets.backend.model.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertToDto {

    public static UserDto user(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        dto.setCnpj(user.getCnpj());
        dto.setBloqueado(user.isBloqueado());
        return dto;
    }

    public static List<UserDto> userList(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public static PetDto pet(Pet pet) {
        PetDto dto = new PetDto();
        dto.setId(pet.getId());
        dto.setNome(pet.getNome());
        dto.setRaca(pet.getRaca());
        dto.setTipoPet(pet.getTipoPet());
        dto.setPeso(pet.getPeso());
        dto.setIdade(pet.getIdade());
        dto.setSexo(pet.getSexo());
        dto.setVacinado(pet.isVacinado());
        dto.setCastrado(pet.isCastrado());
        dto.setInformacoesAdicionais(pet.getInformacoesAdicionais());
        dto.setDataCadastro(pet.getDataCadastro());
        dto.setDataAdocao(pet.getDataAdocao());
        dto.setEmAdocao(pet.isEmAdocao());
        dto.setAdotado(pet.isAdotado());
        dto.setResponsavelId(pet.getResponsavel().getId());
        return dto;
    }

    public static List<PetDto> petList(List<Pet> pets) {
        return pets.stream().map(PetDto::new).collect(Collectors.toList());
    }

}
