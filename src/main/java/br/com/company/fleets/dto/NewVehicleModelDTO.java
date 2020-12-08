package br.com.company.fleets.dto;

import br.com.company.fleets.entities.Manufacturer;
import br.com.company.fleets.entities.Model;
import br.com.company.fleets.entities.VehicleModel;
import io.swagger.annotations.ApiModelProperty;

public class NewVehicleModelDTO {

	@ApiModelProperty(example = "ABC-8711")
	private String plate;

	@ApiModelProperty(example = "Class C 1.1 Avantgarde Turbo Flex")
	private Model model;
	
	@ApiModelProperty(example = "Mercedes-Benz")
	private Manufacturer manufacturer;
	
	@ApiModelProperty(example = "black")
	private String color;
	
	@ApiModelProperty(name = "true")
	private boolean status;
	
	
	
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}


	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public VehicleModel toEntity() {
		
		VehicleModel entity = new VehicleModel();
		entity.setPlate(this.plate);
		entity.setManufacturer(this.manufacturer);
		entity.setModel(this.model);
		entity.setStatus(this.status);
		
		return entity;
	}
}
