package com.adotapets.backend.util.convert;

import com.adotapets.backend.model.User;
import com.adotapets.backend.model.form.UserForm;
import com.adotapets.backend.util.Security;

public class ConvertToBusiness {

    public static User user(UserForm form) {
        User user = new User();
        user.setId(form.getId() != null ? form.getId() : null);
        user.setNome(form.getNome());
        user.setEmail(form.getEmail());
        user.setCnpj(form.getCnpj());
        return user;
    }

}
