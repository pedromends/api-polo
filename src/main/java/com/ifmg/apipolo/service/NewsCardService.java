package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.NewsCard;
import com.ifmg.apipolo.repository.NewsCardRepository;
import com.ifmg.apipolo.vo.NewsCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsCardService {

    @Autowired
    private NewsCardRepository newsCardRepository;

    public List<NewsCardVO> list(){

        List<NewsCardVO> listVO = new ArrayList<>();
        List<NewsCard> list = newsCardRepository.findAll();

        for(NewsCard newsCard : list)
            listVO.add(new NewsCardVO(newsCard));

        return listVO;
    }
}
