package br.com.company.fleets.dto;

import br.com.company.fleets.entities.Manufacturer;
import io.swagger.annotations.ApiModelProperty;

public class NewManufacturerDTO {

	@ApiModelProperty(example = "Ford")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Manufacturer toEntity() {
		
		Manufacturer entity = new Manufacturer();
		entity.setName(this.name);
		
		return entity;
	}
}
