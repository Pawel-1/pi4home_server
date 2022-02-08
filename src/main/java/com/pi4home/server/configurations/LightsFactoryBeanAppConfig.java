package com.pi4home.server.configurations;

import com.pi4home.server.jpa.LightRepository;
import com.pi4home.server.model.Light;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LightsFactoryBeanAppConfig
{

    @Autowired
    LightRepository lightRepository;

    @Bean(name = "entranceLight")
    public Light entranceLight()
    {
        Light light = new Light();
        light.setName("entranceLight");
        light.setTurnedOn(getLightFronDb(light));

        return light;
    }

    private boolean getLightFronDb(Light light)
    {
        return lightRepository.findById(light.getName())
                .map(Light::isTurnedOn)
                .orElse(false);
    }
}
