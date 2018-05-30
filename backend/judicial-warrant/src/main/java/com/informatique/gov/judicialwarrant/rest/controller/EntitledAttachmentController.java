package com.informatique.gov.judicialwarrant.rest.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatique.gov.judicialwarrant.rest.handler.EntitledAttachmentHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/candidateAttachments")
public class EntitledAttachmentController implements Serializable {

	
	private EntitledAttachmentHandler candidateAttachmentHandler ;
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
    

}
