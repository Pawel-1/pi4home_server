package com.pi4home.server.configurations;

import com.pi4home.server.jpa.BlindRepository;
import com.pi4home.server.model.Blind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class BlindsFactoryBeanAppConfig
{
    @Autowired
    BlindRepository blindRepository;

    @Bean(name = "blindLargeWindowLeft")
    public Blind blindFactoryLargeWindowLeft()
    {
        Blind blind = new Blind();
        blind.setName("largeWindowLeft");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        return blind;
    }

    @Bean(name = "blindLargeWindowRight")
    public Blind blindFactoryLargeWindowRight()
    {
        Blind blind = new Blind();
        blind.setName("largeWindowRight");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        return blind;
    }

    @Bean(name = "blindSmallWindowRight")
    public Blind blindFactorySmallWindowLeft()
    {
        Blind blind = new Blind();
        blind.setName("smallWindowRight");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        return blind;
    }

    @Bean(name = "blindSmallWindowLeft")
    public Blind blindFactorySmallWindowMiddle()
    {
        Blind blind = new Blind();
        blind.setName("smallWindowLeft");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        return blind;
    }

    @Bean(name = "blindSmallWindowMiddle")
    public Blind blindFactorySmallWindowRight()
    {
        Blind blind = new Blind();
        blind.setName("smallWindowMiddle");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        return blind;
    }

    private Double getBlindStateFromDB(Blind blind)
    {
        Optional<Blind> blindFromDb = blindRepository
                .findById(blind.getName());

        if (blindFromDb.isPresent())
        {
            return blindFromDb.get().getPercentageMaskingState();
        }
        return 0.0;
    }
}
