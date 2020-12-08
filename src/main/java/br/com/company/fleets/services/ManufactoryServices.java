package br.com.company.fleets.services;

import java.util.Optional;

import br.com.company.fleets.dto.DataListOutputDTO;
import br.com.company.fleets.dto.ManufacturerDTO;
import br.com.company.fleets.dto.NewManufacturerDTO;
import br.com.company.fleets.entities.Manufacturer;

public interface ManufactoryServices {

	DataListOutputDTO<ManufacturerDTO> findAllManufacturer();
	
	Optional<Manufacturer> createManufacturer(NewManufacturerDTO newManufacturerDTO);

	ManufacturerDTO findManufacturerById(Long id);

	void updateManufacturerById(ManufacturerDTO manufactoryDTO);

	void deleteManufacturerById(ManufacturerDTO manufactoryDTO);
	
}
