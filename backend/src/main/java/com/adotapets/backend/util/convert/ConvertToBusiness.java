package com.adotapets.backend.util.convert;

import com.adotapets.backend.model.Pet;
import com.adotapets.backend.model.User;
import com.adotapets.backend.model.enums.Sexo;
import com.adotapets.backend.model.enums.TipoPet;
import com.adotapets.backend.model.form.PetForm;
import com.adotapets.backend.model.form.UserForm;
import com.adotapets.backend.util.DateWithTimeZone;

import java.time.LocalDateTime;

public class ConvertToBusiness {

    public static User user(UserForm form) {
        User user = new User();
        user.setId(form.getId() != null ? form.getId() : null);
        user.setNome(form.getNome());
        user.setEmail(form.getEmail());
        user.setCnpj(form.getCnpj());
        return user;
    }

    public static Pet pet(PetForm form) {
        Pet pet = new Pet();
        pet.setId(form.getId() != null ? form.getId() : null);
        pet.setNome(form.getNome());
        pet.setRaca(form.getRaca());
        pet.setTipoPet(TipoPet.getTipoPet(form.getTipoPet()));
        pet.setPeso(form.getPeso());
        pet.setIdade(form.getIdade());
        pet.setSexo(Sexo.getSexo(form.getSexo()));
        pet.setVacinado(form.isVacinado());
        pet.setCastrado(form.isCastrado());
        pet.setInformacoesAdicionais(form.getInformacoesAdicionais());
        pet.setDataAdocao(form.getDataAdocao() == null ? DateWithTimeZone.getDateWithTimeZone() : form.getDataAdocao());
        pet.setEmAdocao(form.isEmAdocao());
        pet.setAdotado(form.isAdotado());
        return pet;
    }

}
