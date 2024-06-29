package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Edicts;
import com.ifmg.apipolo.repository.EdictsRepository;
import com.ifmg.apipolo.vo.EdictsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EdictsService {

    @Autowired
    private EdictsRepository edictsRepository;

    public void createEdict(EdictsVO EdictsVO) {
        Edicts edicts = new Edicts();

        edicts.setLink(EdictsVO.getLink());
        edicts.setTitle(EdictsVO.getTitle());

        edictsRepository.save(edicts);
    }

    public List<EdictsVO> listEdicts() {

        List<EdictsVO> listVO = new ArrayList<>();
        List<Edicts> list = edictsRepository.findAll();

        for(Edicts edicts : list)
            listVO.add(new EdictsVO(edicts));

        return listVO;
    }

    public void updateEdicts(EdictsVO EdictsVO) {

        Edicts edicts = edictsRepository.getReferenceById(EdictsVO.getId());

        edicts.setLink(EdictsVO.getLink());
        edicts.setTitle(EdictsVO.getTitle());

        edictsRepository.save(edicts);
    }

    public void deleteEdicts(Long id) {
        edictsRepository.deleteById(id);
    }
}
