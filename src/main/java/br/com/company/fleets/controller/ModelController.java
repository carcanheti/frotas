package br.com.company.fleets.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.company.fleets.dto.DataListOutputDTO;
import br.com.company.fleets.dto.DataOutputDTO;
import br.com.company.fleets.dto.ModelDTO;
import br.com.company.fleets.dto.NewModelDTO;
import br.com.company.fleets.entities.Model;
import br.com.company.fleets.models.ErrorResponse;
import br.com.company.fleets.services.impl.ModelServicesImpl;
import io.swagger.annotations.*;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Api(tags = "Modelo")
@RestController
@RequestMapping(value = "/model/parameter" )
public class ModelController {

	private ModelServicesImpl modelServicesImpl;

	public ModelController(ModelServicesImpl modelServicesImpl) {
		this.modelServicesImpl = modelServicesImpl;
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar modelos")
	@GetMapping
	public ResponseEntity<DataListOutputDTO<ModelDTO>> findAll() {

		DataListOutputDTO<ModelDTO> list = this.modelServicesImpl.findAllModel();

		return ResponseEntity.ok(list);
	}

	@ApiResponses({ @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Salvar modelos")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DataOutputDTO<ModelDTO>> saveModels(@RequestBody NewModelDTO newModelDTO) {

		DataOutputDTO<ModelDTO> data = new DataOutputDTO<>();
		Optional<Model> op = this.modelServicesImpl.createModel(newModelDTO);

		if (op.isPresent()) {

			ModelDTO dto = op.get().toDTO();
			data.setData(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(data);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(data);
	}

	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Alterar cadastro de modelos")
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateModel(@RequestBody ModelDTO modelDTO) {
		this.modelServicesImpl.updateModelById(modelDTO);
	}

	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Deletar modelos")
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteModel(@RequestBody ModelDTO modelDTO) {
		this.modelServicesImpl.deleteModelById(modelDTO);
	}

}
	

