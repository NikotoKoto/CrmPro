package com.example.crm.user.mappeur;

import com.example.crm.user.domain.User;
import com.example.crm.user.entity.UserEntity;

public class UserMappeur {

    public static User toDomain(UserEntity userEntity){
        return new User(
                userEntity.getFirstname(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }

    public static UserEntity toEntity(User user){
        return new UserEntity(
                user.firstname(),
                user.lastName(),
                user.email(),
                user.password()
        );
    }
}
