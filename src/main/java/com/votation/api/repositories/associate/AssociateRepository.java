package com.votation.api.repositories.associate;

import com.votation.api.models.associate.Associate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AssociateRepository extends CrudRepository<Associate, Long> {

    Optional<Associate> findByDocumentNumber(String documentNumber);

}
