package com.pi4home.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIGHTS")
public class Light
{
    @Id
    private String name;
    private boolean isTurnedOn;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTurnedOn(boolean turnedOn)
    {
        isTurnedOn = turnedOn;
    }

    public boolean isTurnedOn()
    {
        return isTurnedOn;
    }

    public String getName()
    {
        return name;
    }
}
