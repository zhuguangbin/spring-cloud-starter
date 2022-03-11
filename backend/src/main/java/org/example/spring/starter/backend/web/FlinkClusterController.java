package org.example.spring.starter.backend.web;

import org.example.spring.starter.common.beans.FlinkClusterCreateReq;
import org.example.spring.starter.common.beans.FlinkClusterInfo;
import org.example.spring.starter.common.enums.ClusterState;
import org.example.spring.starter.backend.domain.FlinkCluster;
import org.example.spring.starter.backend.exception.BackendException;
import org.example.spring.starter.backend.exception.Status;
import org.example.spring.starter.backend.service.FlinkClusterRepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("flinkclusters")
public class FlinkClusterController
{
    private static final Logger logger = LoggerFactory.getLogger(FlinkClusterController.class);

    private FlinkClusterRepoService service;

    @Autowired
    public FlinkClusterController(FlinkClusterRepoService service)
    {
        this.service = service;
    }

    @GetMapping
    public List<FlinkClusterInfo> allFlinkClusters() {
        return service.findAll().stream().map(FlinkCluster::info).collect(Collectors.toList());
    }

    @PostMapping
    public Long newFlinkCluster(@RequestBody FlinkClusterCreateReq req)
    {

        Optional<FlinkCluster> opt = service.findByName(req.getName());
        if (opt.isPresent()) {
            throw new BackendException(String.format("FlinkCluster %s already exists", req.getName()), Status.ALREADY_EXISTS) ;
        }
        FlinkCluster cluster = new FlinkCluster(req.getName(), req.getProjectId(), req.getDesc(), req.getOwner());
        cluster.setState(ClusterState.TO_DEPLOY);
        service.save(cluster);
        return cluster.getId();
    }
}
