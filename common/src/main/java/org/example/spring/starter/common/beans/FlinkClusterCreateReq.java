package org.example.spring.starter.common.beans;


public class FlinkClusterCreateReq
{
    private String name;
    private Long projectId;
    private String desc;
    private String owner;

    public FlinkClusterCreateReq()
    {
    }

    public FlinkClusterCreateReq(String name, Long projectId, String desc, String owner)
    {
        this.name = name;
        this.projectId = projectId;
        this.desc = desc;
        this.owner = owner;
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
}
