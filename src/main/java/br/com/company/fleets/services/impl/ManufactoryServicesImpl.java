package br.com.company.fleets.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.company.fleets.dto.DataListOutputDTO;
import br.com.company.fleets.dto.ManufacturerDTO;
import br.com.company.fleets.dto.NewManufacturerDTO;
import br.com.company.fleets.entities.Manufacturer;
import br.com.company.fleets.exceptions.NotSavedRegisterException;
import br.com.company.fleets.exceptions.RegisterNotDeleteException;
import br.com.company.fleets.exceptions.RegisterNotFoundException;
import br.com.company.fleets.exceptions.RegisterNotUpdateException;
import br.com.company.fleets.repositorys.ManufacturerRepository;
import br.com.company.fleets.services.ManufactoryServices;

@Service
public class ManufactoryServicesImpl implements ManufactoryServices {

	private static final Logger LOGGER = Logger.getLogger(ManufactoryServicesImpl.class);
	
	private ManufacturerRepository manufacturerRepository;
	
	@Override
	public DataListOutputDTO<ManufacturerDTO> findAllManufacturer() {
		DataListOutputDTO<ManufacturerDTO> data = new DataListOutputDTO<>();

		List<Manufacturer> list = this.manufacturerRepository.findAll();
		List<ManufacturerDTO> listDto = list.stream().map(Manufacturer::toDTO).collect(Collectors.toList());
		data.setData(listDto);

		return data;
	}

	@Override
	@Transactional
	public Optional<Manufacturer> createManufacturer(NewManufacturerDTO newManufacturerDTO) {
		try {

			Manufacturer Manufacturer = newManufacturerDTO.toEntity();

			Manufacturer entity = this.manufacturerRepository.save(Manufacturer);

			return Optional.of(entity);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new NotSavedRegisterException();
		}
	}

	@Override
	public ManufacturerDTO findManufacturerById(Long id) {
		Optional<Manufacturer> optional = this.manufacturerRepository.findById(id);
		return optional.orElseThrow(() -> new RegisterNotFoundException()).toDTO();
	}

	@Override
	public void updateManufacturerById(ManufacturerDTO manufactoryDTO) {
		try {

			Optional<Manufacturer> optional = this.manufacturerRepository.findById(manufactoryDTO.getId());
			var orig = optional.orElseThrow(() -> new RegisterNotFoundException());
			Manufacturer dest = manufactoryDTO.toEntity();
			BeanUtils.copyProperties(orig, dest);

			this.manufacturerRepository.save(orig);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotUpdateException();
		}

	}

	@Override
	public void deleteManufacturerById(ManufacturerDTO manufactoryDTO) {
		try {

			Optional<Manufacturer> optional = this.manufacturerRepository.findById(manufactoryDTO.getId());
			var obj = optional.orElseThrow(() -> new RegisterNotFoundException());
			this.manufacturerRepository.delete(obj);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotDeleteException();
		}
	}

}
