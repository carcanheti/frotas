package br.com.company.fleets.dto;

import br.com.company.fleets.entities.Model;
import io.swagger.annotations.ApiModelProperty;

public class ModelDTO {

	@ApiModelProperty(example = "1")
	private Long id;
	
	
	@ApiModelProperty(example = "Ford")
	private String name;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public Model toEntity() {
		Model entity = new Model();
		entity.setName(this.name);
		entity.setId(this.id);
		
		return entity;
	}

}
