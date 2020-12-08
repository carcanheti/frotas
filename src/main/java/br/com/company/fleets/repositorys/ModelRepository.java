package br.com.company.fleets.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.company.fleets.entities.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{

}
