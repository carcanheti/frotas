package br.com.company.fleets.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.company.fleets.dto.VehicleModelDTO;

@Entity
@Table(name = "MODELO_VEICULO")
public class VehicleModel implements Serializable{

	private static final long serialVersionUID = 2215305821635362921L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "MODELO_VEICULO_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name = "placa", length = 50)
	private String plate;
	
	@ManyToOne
	@JoinColumn(name = "modelo_id")
	private Model model;
	
	@ManyToOne
	@JoinColumn(name = "fabricante_id")
	private Manufacturer manufacturer;
	
	
	@Column(name = "cor", length = 255)
	private String color;
	
	
	@Column(name = "status")
	private Boolean status;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleModel other = (VehicleModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public VehicleModelDTO toDTO() {
		
		VehicleModelDTO dto = new VehicleModelDTO();
		
		dto.setId(this.id);
		dto.setColor(this.color);
		dto.setManufacturer(this.manufacturer);
		dto.setModel(this.model);
		dto.setPlate(this.plate);
		dto.setStatus(this.status);
		
		return dto;
		
		
	}
	
}
