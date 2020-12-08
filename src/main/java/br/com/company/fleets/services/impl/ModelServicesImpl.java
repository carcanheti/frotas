package br.com.company.fleets.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.company.fleets.dto.DataListOutputDTO;
import br.com.company.fleets.dto.ModelDTO;
import br.com.company.fleets.dto.NewModelDTO;
import br.com.company.fleets.entities.Model;
import br.com.company.fleets.exceptions.NotSavedRegisterException;
import br.com.company.fleets.exceptions.RegisterNotDeleteException;
import br.com.company.fleets.exceptions.RegisterNotFoundException;
import br.com.company.fleets.exceptions.RegisterNotUpdateException;
import br.com.company.fleets.repositorys.ModelRepository;
import br.com.company.fleets.services.ModelServices;

@Service
public class ModelServicesImpl implements ModelServices {

	private static final Logger LOGGER = Logger.getLogger(ModelServicesImpl.class);
	
	private ModelRepository modelRepository;
	
	@Override
	public DataListOutputDTO<ModelDTO> findAllModel() {
		DataListOutputDTO<ModelDTO> data = new DataListOutputDTO<>();

		List<Model> list = this.modelRepository.findAll();
		List<ModelDTO> listDto = list.stream().map(Model::toDTO).collect(Collectors.toList());
		data.setData(listDto);

		return data;
	}

	@Override
	@Transactional
	public Optional<Model> createModel(NewModelDTO newModelDTO) {
		try {

			Model model = newModelDTO.toEntity();

			Model entity = this.modelRepository.save(model);

			return Optional.of(entity);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new NotSavedRegisterException();
		}
	}

	@Override
	public ModelDTO findModelById(Long id) {
		Optional<Model> optional = this.modelRepository.findById(id);
		return optional.orElseThrow(() -> new RegisterNotFoundException()).toDTO();
	}

	@Override
	public void updateModelById(ModelDTO modelDTO) {
		try {

			Optional<Model> optional = this.modelRepository.findById(modelDTO.getId());
			var orig = optional.orElseThrow(() -> new RegisterNotFoundException());
			Model dest = modelDTO.toEntity();
			BeanUtils.copyProperties(orig, dest);

			this.modelRepository.save(orig);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotUpdateException();
		}

	}

	@Override
	public void deleteModelById(ModelDTO modelDTO) {
		try {

			Optional<Model> optional = this.modelRepository.findById(modelDTO.getId());
			var obj = optional.orElseThrow(() -> new RegisterNotFoundException());
			this.modelRepository.delete(obj);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotDeleteException();
		}
	}

}
