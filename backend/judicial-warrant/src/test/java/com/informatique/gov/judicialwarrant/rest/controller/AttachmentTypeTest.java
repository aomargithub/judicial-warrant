package com.informatique.gov.judicialwarrant.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatique.gov.judicialwarrant.rest.dto.AttachmentTypeDto;
import com.informatique.gov.judicialwarrant.service.AttachmentTypeService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AttachmentTypeTest {

	
	private MockMvc mockMvc;

    @Mock
    private AttachmentTypeService attachmentTypeService;

    @InjectMocks
    private AttachmentTypeController attachmentTypeController;
	
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(attachmentTypeController)
                .build();
    }
    
    @Test
    public void test_get_all_success() throws Exception {
        List<AttachmentTypeDto> attachmentTypes = Arrays.asList(
                new AttachmentTypeDto(),
                new AttachmentTypeDto());

        when(attachmentTypeService.getAll()).thenReturn(attachmentTypes);

        mockMvc.perform(get("/attachmentTypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].arabicName", is("name")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].englishName", is("name")));

        verify(attachmentTypeService, times(1)).getAll();
        verifyNoMoreInteractions(attachmentTypeService);
    }
    
    @Test
    public void test_get_by_id_success() throws Exception {
        AttachmentTypeDto attachmentTypeDto = new AttachmentTypeDto();

        when(attachmentTypeService.getById((long) 1)).thenReturn(attachmentTypeDto);

        mockMvc.perform(get("/attachmentTypes/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.englishName", is("")));

        verify(attachmentTypeService, times(1)).getById((long) 1);
        verifyNoMoreInteractions(attachmentTypeService);
    }
    
    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {

        when(attachmentTypeService.getById((long) 1)).thenReturn(null);

        mockMvc.perform(get("/attachmentTypes/{id}", 1))
                .andExpect(status().isNotFound());

        verify(attachmentTypeService, times(1)).getById((long) 1);
        verifyNoMoreInteractions(attachmentTypeService);
    }
    
    @Test
    public void test_create_user_success() throws Exception {
        AttachmentTypeDto attachmentTypeDto = new AttachmentTypeDto();

        when(attachmentTypeService.getById(attachmentTypeDto.getId()) != null).thenReturn(false);
        doNothing().when(attachmentTypeService).save(attachmentTypeDto);

        mockMvc.perform(
                post("/attachmentTypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(attachmentTypeDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("http://localhost/users/")));

        verify(attachmentTypeService, times(1)).getById(attachmentTypeDto.getId());
        verify(attachmentTypeService, times(1)).save(attachmentTypeDto);
        verifyNoMoreInteractions(attachmentTypeService);
    }
    
    @Test
    public void test_create_user_fail_409_conflict() throws Exception {
        AttachmentTypeDto attachmentTypeDto = new AttachmentTypeDto();

        when(attachmentTypeService.getById(attachmentTypeDto.getId()) != null).thenReturn(true);

        mockMvc.perform(
                post("/attachmentTypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(attachmentTypeDto)))
                .andExpect(status().isConflict());

        verify(attachmentTypeService, times(1)).getById(attachmentTypeDto.getId());
        verifyNoMoreInteractions(attachmentTypeService);
    }
    
    @Test
    public void test_update_user_success() throws Exception {
        AttachmentTypeDto attachmentTypeDto = new AttachmentTypeDto();

        when(attachmentTypeService.getById(attachmentTypeDto.getId())).thenReturn(attachmentTypeDto);
        doNothing().when(attachmentTypeService).update(attachmentTypeDto);

        mockMvc.perform(
                put("/attachmentTypes/{id}", attachmentTypeDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(attachmentTypeDto)))
                .andExpect(status().isOk());

        verify(attachmentTypeService, times(1)).getById(attachmentTypeDto.getId());
        verify(attachmentTypeService, times(1)).update(attachmentTypeDto);
        verifyNoMoreInteractions(attachmentTypeService);
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
