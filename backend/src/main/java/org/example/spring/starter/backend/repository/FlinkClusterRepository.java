package org.example.spring.starter.backend.repository;

import org.example.spring.starter.backend.domain.FlinkCluster;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FlinkClusterRepository extends CrudRepository<FlinkCluster, Long>
{
    Optional<FlinkCluster> findByName(String name);

}
