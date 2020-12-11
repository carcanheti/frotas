package br.com.company.fleets.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.company.fleets.dto.DataListOutputDTO;
import br.com.company.fleets.dto.DataOutputDTO;
import br.com.company.fleets.dto.NewVehicleModelDTO;
import br.com.company.fleets.dto.VehicleModelDTO;
import br.com.company.fleets.entities.VehicleModel;
import br.com.company.fleets.models.ErrorResponse;
import br.com.company.fleets.services.impl.VehicleModelServicesImpl;
import io.swagger.annotations.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "Veículo")
@RestController
@RequestMapping(value = "/vehicle/parameter")
public class VehicleModelController {

	private VehicleModelServicesImpl vehicleModelServicesImpl;

	public VehicleModelController(VehicleModelServicesImpl vehicleModelServicesImpl) {
		this.vehicleModelServicesImpl = vehicleModelServicesImpl;
	}


	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar veículo por id")
	@GetMapping("/id")
	public ResponseEntity<VehicleModelDTO> findVehicleModelById(@RequestParam Long id) {
		VehicleModelDTO vehicleModelDTO = this.vehicleModelServicesImpl.findVehicleModelById(id);
		return ResponseEntity.ok(vehicleModelDTO);
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar veículo por placa")
	@GetMapping("/plate")
	public ResponseEntity<VehicleModelDTO> findVehicleModelByPlate(@RequestParam String plate) {
		VehicleModelDTO vehicleModelDTO = this.vehicleModelServicesImpl.findVehicleModelByPlate(plate);
		return ResponseEntity.ok(vehicleModelDTO);
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar veículo(s) por status")
	@GetMapping("{status}")
	public ResponseEntity<DataListOutputDTO<VehicleModelDTO>> findVehicleModelByStatus(@PathVariable boolean status) {
		DataListOutputDTO<VehicleModelDTO> list = this.vehicleModelServicesImpl.findVehicleModelByStatus(status);
		return ResponseEntity.ok(list);
	}

	@GetMapping
    public ResponseEntity<List<VehicleModelDTO>> findVehicleModelByPage(
                        @RequestParam(defaultValue = "0") int pageNo, 
                        @RequestParam(defaultValue = "10") int pageSize,
                        @RequestParam(defaultValue = "id") String sortBy) 
    {
        List<VehicleModelDTO> list = this.vehicleModelServicesImpl.findVehicleModelByPage(pageNo, pageSize, sortBy);
 
        return ResponseEntity.ok(list);
        		
    }

	@ApiResponses({ @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Salvar veículo")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DataOutputDTO<VehicleModelDTO>> saveModels(
			@RequestBody NewVehicleModelDTO newVehicleModelDTO) {

		DataOutputDTO<VehicleModelDTO> data = new DataOutputDTO<>();
		Optional<VehicleModel> op = this.vehicleModelServicesImpl.createVehicleModel(newVehicleModelDTO);

		if (op.isPresent()) {

			VehicleModelDTO dto = op.get().toDTO();
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
	@ApiOperation("Alterar cadastro de veículo")
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateModel(@RequestBody VehicleModelDTO vehicleModelDTO) {
		this.vehicleModelServicesImpl.updateVehicleModelById(vehicleModelDTO);
	}

	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Deletar veículo")
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteModel(@RequestBody VehicleModelDTO vehicleModelDTO) {
		this.vehicleModelServicesImpl.deleteVehicleModelById(vehicleModelDTO);
	}

}
