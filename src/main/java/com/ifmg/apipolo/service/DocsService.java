package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Docs;
import com.ifmg.apipolo.repository.DocsRepository;
import com.ifmg.apipolo.vo.DocsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocsService {

    @Autowired
    private DocsRepository docsRepository;

    public void createDocs(DocsVO DocsVO) {
        Docs docs = new Docs();

        docs.setLink(DocsVO.getLink());
        docs.setTitle(DocsVO.getTitle());

        docsRepository.save(docs);
    }

    public List<DocsVO> listDocs() {

        List<DocsVO> listVO = new ArrayList<>();
        List<Docs> list = docsRepository.findAll();

        for(Docs Docs : list)
            listVO.add(new DocsVO(Docs));

        return listVO;
    }

    public void updateDocs(DocsVO DocsVO) {

        Docs docs = docsRepository.getReferenceById(DocsVO.getId());
        docs.setLink(DocsVO.getLink());
        docs.setTitle(DocsVO.getTitle());

        docsRepository.save(docs);
    }

    public void deleteDocs(Long id) {
        docsRepository.deleteById(id);
    }
}
