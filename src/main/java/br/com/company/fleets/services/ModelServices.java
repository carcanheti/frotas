package br.com.company.fleets.services;

import java.util.Optional;

import br.com.company.fleets.dto.DataListOutputDTO;
import br.com.company.fleets.dto.ModelDTO;
import br.com.company.fleets.dto.NewModelDTO;
import br.com.company.fleets.entities.Model;

public interface ModelServices {

	DataListOutputDTO<ModelDTO> findAllModel();
	
	Optional<Model> createModel(NewModelDTO newModelDTO);

	ModelDTO findModelById(Long id);

	void updateModelById(ModelDTO modelDTO);

	void deleteModelById(ModelDTO modelDTO);
	
}
