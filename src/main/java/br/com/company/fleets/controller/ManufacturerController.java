package br.com.company.fleets.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.company.fleets.dto.DataListOutputDTO;
import br.com.company.fleets.dto.DataOutputDTO;
import br.com.company.fleets.dto.ManufacturerDTO;
import br.com.company.fleets.dto.NewManufacturerDTO;
import br.com.company.fleets.entities.Manufacturer;
import br.com.company.fleets.models.ErrorResponse;
import br.com.company.fleets.services.impl.ManufactoryServicesImpl;
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


@Api(tags = "Fabricantes")
@RestController
@RequestMapping(value = "/manufacturer/parameter" )
public class ManufacturerController {

	private ManufactoryServicesImpl manufactoryServicesImpl;

	public ManufacturerController(ManufactoryServicesImpl manufactoryServicesImpl) {
		this.manufactoryServicesImpl = manufactoryServicesImpl;
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar fabricantes")
	@GetMapping
	public ResponseEntity<DataListOutputDTO<ManufacturerDTO>> findAll() {

		DataListOutputDTO<ManufacturerDTO> list = this.manufactoryServicesImpl.findAllManufacturer();

		return ResponseEntity.ok(list);
	}

	@ApiResponses({ @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Salvar fabricante")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DataOutputDTO<ManufacturerDTO>> saveManufacturer(@RequestBody NewManufacturerDTO newManufacturerDTO) {

		DataOutputDTO<ManufacturerDTO> data = new DataOutputDTO<>();
		Optional<Manufacturer> op = this.manufactoryServicesImpl.createManufacturer(newManufacturerDTO);

		if (op.isPresent()) {

			ManufacturerDTO dto = op.get().toDTO();
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
	@ApiOperation("Alterar cadastro de fabricante")
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
		this.manufactoryServicesImpl.updateManufacturerById(manufacturerDTO);
	}

	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Deletar fabricante")
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
		this.manufactoryServicesImpl.deleteManufacturerById(manufacturerDTO);
	}

}
	

