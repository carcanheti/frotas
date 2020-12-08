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

import br.com.company.fleets.dto.ManufacturerDTO;
import br.com.company.fleets.dto.ModelDTO;

@Entity
@Table(name = "MODELO")
public class Model implements Serializable{

	private static final long serialVersionUID = -8094699148599262380L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "MODELO_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name = "nome" , length = 255)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "fabricante_id")
	private Manufacturer manufacturer;

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
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
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
		Model other = (Model) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public ModelDTO toDTO() {

		ModelDTO dto = new ModelDTO();

		dto.setId(this.id);
		dto.setName(this.name);
		

		return dto;

	}

	
}
