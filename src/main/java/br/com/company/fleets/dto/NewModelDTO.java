package br.com.company.fleets.dto;

import br.com.company.fleets.entities.Model;
import io.swagger.annotations.ApiModelProperty;

public class NewModelDTO {

	@ApiModelProperty(example = "Ford")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Model toEntity() {
		
		Model entity = new Model();
		entity.setName(this.name);
		
		return entity;
	}
}
