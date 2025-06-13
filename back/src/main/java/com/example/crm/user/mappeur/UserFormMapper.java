package com.example.crm.user.mappeur;

import com.example.crm.user.dto.UserForm;
import com.example.crm.user.entity.UserEntity;

public class UserFormMapper {

    public static UserEntity toEntity(UserForm form) {
        return new UserEntity(
                form.firstname(),
                form.name(),
                form.email(),
                form.password()
        );
    }
}