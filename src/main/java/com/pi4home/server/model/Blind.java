package com.pi4home.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BLINDS")
public class Blind
{
    private String name;
    private Double percentageMaskingState;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPercentageMaskingState()
    {
        return percentageMaskingState;
    }

    public void setPercentageMaskingState(Double percentageMaskingState)
    {
        this.percentageMaskingState = percentageMaskingState;
    }
}
