package org.example.spring.starter.backend.domain;


import org.example.spring.starter.common.beans.FlinkClusterInfo;
import org.example.spring.starter.common.enums.ClusterState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "flink_cluster")
public class FlinkCluster
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`name`", nullable = false, unique = true)
    @NotNull(message = "Flink Cluster Name cannot be null ")
    private String name;

    @Column(name = "`project_id`", nullable = false)
    @Min(value = 0, message = "project id should be greater than 0")
    @Max(value = 1000, message = "project id should be less than 1000")
    private Long projectId;

    @Enumerated
    @Column(name = "`state`", nullable = false)
    private ClusterState state;

    @Column(name = "`desc`", nullable = true)
    private String desc;

    @Column(name = "`owner`", nullable = false)
    private String owner;

    @Column(name = "`operator`", nullable = true)
    private String operator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`create_time`", nullable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`update_time`", nullable = false)
    private Date updateTime;

    public FlinkCluster()
    {
    }

    public FlinkCluster(Long id)
    {
        this.id = id;
    }

    public FlinkCluster(String name, Long projectId, String desc, String owner)
    {
        this.name = name;
        this.projectId = projectId;
        this.desc = desc;
        this.owner = owner;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public ClusterState getState()
    {
        return state;
    }

    public void setState(ClusterState state)
    {
        this.state = state;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public FlinkClusterInfo info() {
        return new FlinkClusterInfo(this.id, this.name, this.projectId, this.state, this.desc, this.owner, this.operator, this.createTime, this.updateTime);
    }
}
