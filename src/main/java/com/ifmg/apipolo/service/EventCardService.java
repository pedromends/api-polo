package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.EventCard;
import com.ifmg.apipolo.repository.EventCardRepository;
import com.ifmg.apipolo.vo.EventCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventCardService {

    @Autowired
    private EventCardRepository eventCardRepository;

    public void createEvent(EventCardVO eventCardVO) {
        System.out.println(eventCardVO);
        EventCard eventCard = eventCardRepository.getReferenceById(eventCardVO.getId());
        eventCard.setDay(eventCardVO.getDay());
        eventCard.setMonth(eventCardVO.getMonth());
        eventCard.setTitle(eventCardVO.getTitle());
        eventCard.setHour(eventCardVO.getHour());
        eventCard.setLocal(eventCardVO.getLocal());
        eventCardRepository.save(eventCard);
    }

    public List<EventCardVO> list(){

        List<EventCardVO> listVO = new ArrayList<>();
        List<EventCard> list = eventCardRepository.findAll();

        for(EventCard eventCard : list)
            listVO.add(new EventCardVO(eventCard));

        return listVO;
    }
}
