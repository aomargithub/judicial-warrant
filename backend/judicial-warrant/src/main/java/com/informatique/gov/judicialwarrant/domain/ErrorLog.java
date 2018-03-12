package com.informatique.gov.judicialwarrant.domain;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "error_log")
@Data
public class ErrorLog implements Serializable{

    private static final long serialVersionUID = 1446867146429014230L;

    @Id
    @SequenceGenerator(name = "ErrorLogSequence", sequenceName = "error_log_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ErrorLogSequence")
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "stack_trace")
    private String stackTrace;

    @Column(name = "request_id")
    private Long requestId;

    @Nationalized
    @Column(name = "user_name")
    private String userName;

    @Column(name = "create_date")
    private Date createDate;
}
