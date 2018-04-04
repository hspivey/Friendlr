package com.cooksys.Friendlr.person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

	Person toPerson(PersonDto dto);
	@Mapping(target="ids", source="friends")
    PersonDto toDto(Person person);
    
    default Long fromPerson(Person pers) {
        return pers.getId();
    }

	
	//List<Person> toPersonList(List<PersonDto> dto);
//List<id> toidList(List<id>)
	
	
}
