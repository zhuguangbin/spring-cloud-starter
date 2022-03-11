package org.example.spring.starter.common.feign.client;

import org.example.spring.starter.common.beans.FlinkClusterCreateReq;
import org.example.spring.starter.common.beans.FlinkClusterInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "backend", contextId = "flink-manager")
public interface FeignFlinkClusterClient
{
    @GetMapping("flinkclusters")
    List<FlinkClusterInfo> allFlinkClusters();

    @PostMapping("flinkclusters")
    Long newFlinkCluster(@RequestBody FlinkClusterCreateReq req);


}
