package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.FAQ;
import com.ifmg.apipolo.repository.FAQRepository;
import com.ifmg.apipolo.vo.FAQVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    public void createFAQ(FAQVO faqVO) {
        FAQ faq = new FAQ();

        faq.setAnswer(faqVO.getAnswer());
        faq.setQuestion(faqVO.getQuestion());
        faq.setIdHeading(faqVO.getIdHeading());
        faq.setIdBody(faqVO.getIdBody());

        faqRepository.save(faq);
    }

    public List<FAQVO> listFAQ() {

        List<FAQVO> listVO = new ArrayList<>();
        List<FAQ> list = faqRepository.findAll();

        for(FAQ faq : list)
            listVO.add(new FAQVO(faq));

        return listVO;
    }

    public void updateFAQ(FAQVO faqVO) {

        FAQ faq = faqRepository.getReferenceById(faqVO.getId());
        faq.setAnswer(faqVO.getAnswer());
        faq.setQuestion(faqVO.getQuestion());
        faqRepository.save(faq);
    }

    public void deleteFAQ(Long id) {
        faqRepository.deleteById(id);
    }
}
