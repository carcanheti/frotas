package br.com.company.fleets.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.company.fleets.dto.DataListOutputDTO;
import br.com.company.fleets.dto.NewVehicleModelDTO;
import br.com.company.fleets.dto.VehicleModelDTO;
import br.com.company.fleets.entities.VehicleModel;
import br.com.company.fleets.exceptions.NotSavedRegisterException;
import br.com.company.fleets.exceptions.RegisterNotDeleteException;
import br.com.company.fleets.exceptions.RegisterNotFoundException;
import br.com.company.fleets.exceptions.RegisterNotUpdateException;
import br.com.company.fleets.repositorys.VehicleModelRepository;
import br.com.company.fleets.services.VehicleModelServices;

@Service
public class VehicleModelServicesImpl implements VehicleModelServices {

	private static final Logger LOGGER = Logger.getLogger(VehicleModelServicesImpl.class);
	
	private VehicleModelRepository vehicleModelRepository;


	@Override
	@Transactional
	public Optional<VehicleModel> createVehicleModel(NewVehicleModelDTO newVehicleModelDTO) {
		try {

			VehicleModel vehicleModel = newVehicleModelDTO.toEntity();

			VehicleModel entity = this.vehicleModelRepository.save(vehicleModel);

			return Optional.of(entity);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new NotSavedRegisterException();
		}
	}

	@Override
	public VehicleModelDTO findVehicleModelById(Long id) {
		Optional<VehicleModel> optional = this.vehicleModelRepository.findById(id);
		return optional.orElseThrow(() -> new RegisterNotFoundException()).toDTO();
	}

	@Override
	public void updateVehicleModelById(VehicleModelDTO vehicleModelDTO) {
		try {

			Optional<VehicleModel> optional = this.vehicleModelRepository.findById(vehicleModelDTO.getId());
			var orig = optional.orElseThrow(() -> new RegisterNotFoundException());
			VehicleModel dest = vehicleModelDTO.toEntity();
			BeanUtils.copyProperties(orig, dest);

			this.vehicleModelRepository.save(orig);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotUpdateException();
		}

	}

	@Override
	public void deleteVehicleModelById(VehicleModelDTO vehicleModelDTO) {
		try {

			Optional<VehicleModel> optional = this.vehicleModelRepository.findById(vehicleModelDTO.getId());
			var obj = optional.orElseThrow(() -> new RegisterNotFoundException());
			this.vehicleModelRepository.delete(obj);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotDeleteException();
		}
	}

	@Override
	public DataListOutputDTO<VehicleModelDTO> findVehicleModelByStatus(boolean status) {
				
			DataListOutputDTO<VehicleModelDTO> data = new DataListOutputDTO<>();

			List<VehicleModel> list = this.vehicleModelRepository.findByStatusTrue();
			List<VehicleModelDTO> listDto = list.stream().map(VehicleModel::toDTO).collect(Collectors.toList());
			data.setData(listDto);
	
			return data;
		
	}

	@Override
	public VehicleModelDTO findVehicleModelByPlate(String plate) {
		
		Optional<VehicleModel> optional = this.vehicleModelRepository.findVehicleModelByPlate(plate);
		return optional.orElseThrow(() -> new RegisterNotFoundException()).toDTO();
		
	}

	@Override
	public List<VehicleModelDTO> findVehicleModelByPage(int pageNo, int pageSize, String sortBy) {
		
		List<VehicleModelDTO> data = new ArrayList<>();
		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		 
        Page<VehicleModel> pagedResult = this.vehicleModelRepository.findAll(paging);
         
        if(pagedResult.hasContent()) {
       
    		List<VehicleModel> lista = pagedResult.getContent();
    		return lista.stream().map(VehicleModel::toDTO).collect(Collectors.toList());
    
        }
		return data;	
        
	}

}
