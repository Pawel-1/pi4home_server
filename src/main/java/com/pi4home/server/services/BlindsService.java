package com.pi4home.server.services;

import com.pi4home.server.jpa.BlindRepository;
import com.pi4home.server.messagesBroker.QueueProducer;
import com.pi4home.server.model.Blind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlindsService
{
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
        Blind blindByName = getBlindByName(blindFromRq.getName());

        try
        {
            queueProducer.produce(blindByName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        blindRepository.save(blindByName);
        return blindByName;
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
}
