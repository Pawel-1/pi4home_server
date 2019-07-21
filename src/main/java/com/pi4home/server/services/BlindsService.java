package com.pi4home.server.services;

import com.pi4home.server.jpa.BlindRepository;
import com.pi4home.server.messagesBroker.QueueProducer;
import com.pi4home.server.model.Blind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlindsService
{
    private static final Logger logger = LoggerFactory.getLogger(BlindsService.class);

    @Autowired
    QueueProducer queueProducer;

    @Autowired
    private BlindRepository blindRepository;

    @Autowired
    private Blind blindLargeWindowLeft;

    @Autowired
    private Blind blindLargeWindowRight;

    @Autowired
    private Blind blindSmallWindowLeft;

    @Autowired
    private Blind blindSmallWindowMiddle;

    @Autowired
    private Blind blindSmallWindowRight;

    @Autowired
    List<Blind> blindList = Arrays.asList(
            blindLargeWindowLeft,
            blindLargeWindowRight,
            blindSmallWindowLeft,
            blindSmallWindowMiddle,
            blindSmallWindowRight);

    public Blind setBlindState(Blind blindFromRq)
    {
        logger.info("Processing blind update: " + blindFromRq.getName());
        Blind blindByName = getBlindByName(blindFromRq.getName());
        blindByName.setPercentageMaskingState(blindFromRq.getPercentageMaskingState());

        try
        {
            queueProducer.produce(blindByName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        updateDb(blindByName);
        return blindByName;
    }

    private void updateDb(Blind blindByName)
    {
        logger.info("saving data in DB: " + blindByName.getName() + "state: " + blindByName.getPercentageMaskingState());
        blindRepository.save(blindByName);
    }

    private Blind getBlindByName(String blindName)
    {
        return blindList
                .stream()
                .filter(blind -> blind.getName().equals(blindName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    public List<Blind> getBlindStatus()
    {
        return blindList;
    }

    public List<Blind> updateBlindStateInDb(String name, Double percentageMaskingState)
    {
        Blind blindByName = getBlindByName(name);
        blindByName.setPercentageMaskingState(percentageMaskingState);
        updateDb(blindByName);

        List<Blind> blindList = new ArrayList<>();

        Iterable<Blind> blindRepositoryAll = blindRepository.findAll();
        blindRepositoryAll.forEach(blindList::add);
        return blindList;
    }

    public void initDb()
    {
        blindList
                .forEach(blindRepository::save);

    }
}
