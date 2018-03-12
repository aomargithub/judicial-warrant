package com.informatique.gov.judicialwarrant.support.modelmpper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.NotificationChannel;
import com.informatique.gov.judicialwarrant.rest.dto.NotificationChannelDto;

@Component
public class NotificationChannelMapper extends AbstractModelMapper<NotificationChannel, NotificationChannelDto, Byte>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4287329172000925825L;
	private final static Logger logger = LogManager.getLogger(NotificationChannelMapper.class);

	@Override
	public NotificationChannelDto toDto(NotificationChannel entity) {
		NotificationChannelDto dto = null;
		
		if(isConvertable(entity)){
			dto = new NotificationChannelDto();
			
			dto.setId(entity.getId());
			dto.setCode(entity.getCode());
			dto.setArabicName(entity.getArabicName());
			dto.setEnglishName(entity.getEnglishName());
			dto.setIsActive(entity.getIsActive());
		}
		
		return dto;
	}

	@Override
	protected NotificationChannel toEntity(NotificationChannelDto dto, boolean nullId) {
		NotificationChannel entity = null;
		
		if(isConvertable(dto)){
			entity = new NotificationChannel();
			
			entity.setId(nullId ? null : dto.getId());
			entity.setCode(dto.getCode());
			entity.setArabicName(dto.getArabicName());
			entity.setEnglishName(dto.getEnglishName());
			entity.setIsActive(dto.getIsActive());
		}
		
		return entity;
	}

}
