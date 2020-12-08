package br.com.company.fleets.services;

import java.util.List;
import java.util.Optional;

import br.com.company.fleets.dto.DataListOutputDTO;
import br.com.company.fleets.dto.NewVehicleModelDTO;
import br.com.company.fleets.dto.VehicleModelDTO;
import br.com.company.fleets.entities.VehicleModel;

public interface VehicleModelServices {
	
	DataListOutputDTO<VehicleModelDTO> findVehicleModelByStatus(boolean status);
	
	VehicleModelDTO findVehicleModelByPlate(String plate);
	
	List<VehicleModelDTO> findVehicleModelByPage(int pageNo, int pageSize, String sortBy);
	
	Optional<VehicleModel> createVehicleModel(NewVehicleModelDTO newVehicleModelDTO);

	VehicleModelDTO findVehicleModelById(Long id);

	void updateVehicleModelById(VehicleModelDTO vehicleModelDTO);

	void deleteVehicleModelById(VehicleModelDTO vehicleModelDTO);
	
}
