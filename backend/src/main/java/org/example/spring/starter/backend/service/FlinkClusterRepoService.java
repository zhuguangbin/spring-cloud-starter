package org.example.spring.starter.backend.service;

import com.google.common.collect.Lists;
import org.example.spring.starter.backend.domain.FlinkCluster;
import org.example.spring.starter.backend.repository.FlinkClusterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FlinkClusterRepoService
{
    private FlinkClusterRepository repository;

    @Autowired
    public FlinkClusterRepoService(FlinkClusterRepository repository)
    {
        this.repository = repository;
    }

    public Optional<FlinkCluster> findOneById(Long id) {
        return repository.findById(id);
    }

    public List<FlinkCluster> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    public Optional<FlinkCluster> findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void save(FlinkCluster cluster){
        repository.save(cluster);
    }

}
