package com.fiserv.springbootrestapi.mapper;

import com.fiserv.springbootrestapi.dto.UserDto;
import com.fiserv.springbootrestapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAutoUserMapper {

    IAutoUserMapper MAPPER = Mappers.getMapper(IAutoUserMapper.class);
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
