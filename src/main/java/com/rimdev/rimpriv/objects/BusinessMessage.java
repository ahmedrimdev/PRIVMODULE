package com.rimdev.rimpriv.objects;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class BusinessMessage {

	String serviceCode;
	Boolean errorflag;
	Boolean notifflag;
	Map<String, Object> para;
	Map<String, Object> common;

}
