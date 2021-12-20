package com.jumia.apiexercise.exception;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@JsonSerialize
public class ApiError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int status;
	private String msg;
	private String field;
	private Date date;

	public ApiError(int status, String msg, Date date){
		this.status = status;
		this.msg = msg;
		this.date = date;
	}
	
}
