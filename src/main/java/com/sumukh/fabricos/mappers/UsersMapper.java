package com.sumukh.fabricos.mappers;

import com.sumukh.fabricos.Dtos.AuthRequestDto;
import com.sumukh.fabricos.Entities.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    Users toUsers(AuthRequestDto authRequestDto);
}
