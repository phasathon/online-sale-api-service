package com.rm9.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ConfirmtResp extends TemplateJsonResponse implements Serializable{

	@JsonIgnore
	private static final long serialVersionUID = 1L;

}
