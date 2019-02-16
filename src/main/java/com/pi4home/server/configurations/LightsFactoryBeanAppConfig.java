package com.pi4home.server.configurations;

import com.pi4home.server.model.Light;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LightsFactoryBeanAppConfig
{

    @Bean(name = "entranceLight")
    public Light entranceLight()
    {
        Light light = new Light();
        light.setName("entrance");
        light.setTurnedOn(false);

        return light;
    }

    @Bean(name = "sidewalkLight")
    public Light sidewalkLight()
    {
        Light light = new Light();
        light.setName("sidewalkLight");
        light.setTurnedOn(false);

        return light;
    }

}
