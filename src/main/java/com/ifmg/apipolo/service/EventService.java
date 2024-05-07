package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.EventCard;
import com.ifmg.apipolo.repository.EventRepository;
import com.ifmg.apipolo.vo.EventVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventVO> list(){

        List<EventVO> listVO = new ArrayList<>();
        List<EventCard> list = eventRepository.findAll();

        for(EventCard eventCard : list)
            listVO.add(new EventVO(eventCard));

        return listVO;
    }
}
