package com.informatique.gov.judicialwarrant.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.service.AttachmentTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttachmentTypeServiceTest {

	@Autowired
	private AttachmentTypeService attachmentTypeService;

	@Test
	public void getById() throws JudicialWarrantException {
		AttachmentTypeDto attachmentTypeDto = null;

		attachmentTypeDto = attachmentTypeService.getById((long) 1);

		assertNotNull("attachment can't be null", attachmentTypeDto);
	}

	@Test
	public void create_success() throws JudicialWarrantException {
		AttachmentTypeDto attachmentTypeDto = new AttachmentTypeDto();
		attachmentTypeDto.setArabicName("jwcd Rquest");
		attachmentTypeDto.setEnglishName("jwcd Rquest");

		attachmentTypeDto = attachmentTypeService.save(attachmentTypeDto);

		assertNotNull("attachment not saves successfuly", attachmentTypeDto.getId());
		assertNotNull("attachment can't be null", attachmentTypeDto);
		assertNotNull("arabic name can't be null", attachmentTypeDto.getArabicName());
		assertNotNull("english name can't be null", attachmentTypeDto.getEnglishName());
		assertNotNull("list order can't be null", attachmentTypeDto.getListOrder());
	}

	@Test
	public void update_success() throws JudicialWarrantException {
		AttachmentTypeDto attachmentTypeDto = null;

		attachmentTypeDto = attachmentTypeService.getById((long) 1);
		attachmentTypeDto.setEnglishName("new English Name");
		attachmentTypeDto = attachmentTypeService.save(attachmentTypeDto);

		assertEquals("english name not updated successfully", attachmentTypeDto.getEnglishName(), "new English Name");
	}

}
