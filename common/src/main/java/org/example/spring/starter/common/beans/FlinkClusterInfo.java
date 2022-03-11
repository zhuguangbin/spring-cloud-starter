package org.example.spring.starter.common.beans;

import org.example.spring.starter.common.enums.ClusterState;

import java.util.Date;

public class FlinkClusterInfo
{
    private Long id;
    private String name;
    private Long projectId;
    private ClusterState state;
    private String desc;
    private String owner;
    private String operator;
    private Date createTime;
    private Date updateTime;

    public FlinkClusterInfo()
    {
    }

    public FlinkClusterInfo(Long id, String name, Long projectId, ClusterState state, String desc, String owner, String operator, Date createTime, Date updateTime)
    {
        this.id = id;
        this.name = name;
        this.projectId = projectId;
        this.state = state;
        this.desc = desc;
        this.owner = owner;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
}
