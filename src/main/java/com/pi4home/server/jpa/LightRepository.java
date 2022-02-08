package com.pi4home.server.jpa;

import com.pi4home.server.model.Light;
import org.springframework.data.repository.CrudRepository;

public interface LightRepository extends CrudRepository<Light, String>
{
}
