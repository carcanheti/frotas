package br.com.company.fleets.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.company.fleets.entities.VehicleModel;

@Repository
public interface VehicleModelRepository extends PagingAndSortingRepository<VehicleModel, Long>{
	
	public List<VehicleModel>  findByStatusTrue();
	
	@Query(name = " select vc from VehicleModel vc where plate = :plate")
	public Optional<VehicleModel> findVehicleModelByPlate(@Param("plate") String plate);
}
