package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Event;
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
        List<Event> list = eventRepository.findAll();

        for(Event event : list)
            listVO.add(new EventVO(event));

        return listVO;
    }
}
