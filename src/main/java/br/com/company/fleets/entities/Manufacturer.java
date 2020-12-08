package br.com.company.fleets.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.company.fleets.dto.ManufacturerDTO;

@Entity
@Table(name = "FABRICANTE")
public class Manufacturer implements Serializable {

	private static final long serialVersionUID = 4988612505061288833L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FABRICANTE_SEQ", allocationSize = 1)
	private Long id;

	@Column(name = "nome", length = 255)
	private String name;

	@OneToMany(mappedBy = "manufacturer")
	private List<Model> models;

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

	public List<Model> getModels() {
		return models;
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
		Manufacturer other = (Manufacturer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ManufacturerDTO toDTO() {

		ManufacturerDTO dto = new ManufacturerDTO();

		dto.setId(this.id);
		dto.setName(this.name);
		

		return dto;

	}

}
