package org.example.spring.frontend.web;

import org.example.spring.starter.common.beans.FlinkClusterCreateReq;
import org.example.spring.starter.common.beans.FlinkClusterInfo;
import org.example.spring.starter.common.feign.client.FeignFlinkClusterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("flink")
public class FlinkClusterController
{
    private FeignFlinkClusterClient feignFlinkClusterClient;

    @Autowired
    public FlinkClusterController(FeignFlinkClusterClient feignFlinkClusterClient)
    {
        this.feignFlinkClusterClient = feignFlinkClusterClient;
    }

    @GetMapping("clusters")
    public List<FlinkClusterInfo> allFlinkClusters() {
        return feignFlinkClusterClient.allFlinkClusters();
    }

    @PostMapping("clusters")
    public Long newFlinkCluster(FlinkClusterCreateReq req) {
        return feignFlinkClusterClient.newFlinkCluster(req);
    }

}
