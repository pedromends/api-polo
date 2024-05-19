package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.MainNewCard;
import com.ifmg.apipolo.entity.NewsCard;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.NewsCardRepository;
import com.ifmg.apipolo.vo.MainNewCardVO;
import com.ifmg.apipolo.vo.NewsCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsCardService {

    @Autowired
    private NewsCardRepository newsCardRepository;

    @Autowired
    private ImageRepository imageRepository;

    public void createCard(NewsCardVO newsCardVO) {

        NewsCard newsCard = new NewsCard();

        newsCard.setTip(newsCardVO.getTip());
        newsCard.setTitle(newsCardVO.getTitle());
        newsCard.setRead(newsCardVO.getRead());
        newsCard.setDate(newsCardVO.getDate());
        newsCard.setImg(newsCardVO.getImg());

        newsCardRepository.save(newsCard);
    }

    public void updateCard(NewsCardVO newsCardVO) {

        Image cardImage = imageRepository.getReferenceById(newsCardVO.getImg().getId());
        NewsCard newsCard = newsCardRepository.getReferenceById(newsCardVO.getId());

        newsCard.setTip(newsCardVO.getTip());
        newsCard.setTitle(newsCardVO.getTitle());
        newsCard.setRead(newsCardVO.getRead());
        newsCard.setDate(newsCardVO.getDate());
        cardImage.setCode(newsCardVO.getImg().getCode());

        imageRepository.save(cardImage);
        newsCardRepository.save(newsCard);
    }

    public List<NewsCardVO> list(){

        List<NewsCardVO> listVO = new ArrayList<>();
        List<NewsCard> list = newsCardRepository.findAll();

        for(NewsCard newsCard : list)
            listVO.add(new NewsCardVO(newsCard));

        return listVO;
    }

    public void deleteModality(Long id) {
        newsCardRepository.deleteById(id);
    }
}
