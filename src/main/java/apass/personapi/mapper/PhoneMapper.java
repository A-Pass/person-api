package apass.personapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import apass.personapi.dto.request.PhoneDTO;
import apass.personapi.entity.Phone;

@Mapper
public interface PhoneMapper {

	PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);
	
	Phone toModel(PhoneDTO phoneDTO);
	
	PhoneDTO toDTO(Phone phone);
}
