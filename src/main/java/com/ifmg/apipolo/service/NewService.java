package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.New;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.MainNewCardRepository;
import com.ifmg.apipolo.repository.NewRepository;
import com.ifmg.apipolo.vo.MainNewCardVO;
import com.ifmg.apipolo.vo.NewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private MainNewCardService mainNewCardService;

    public void createNew(NewVO newVO) {

        New newNew = new New();

        newNew.setTitle(newVO.getTitle());
        newNew.setDate(newVO.getDate());
        newNew.setIsMain(newVO.getIsMain());
        newNew.setCode(newVO.getCode());
        newNew.setActive(true);

        newRepository.save(newNew);
    }

    public void updateNew(NewVO newVO) {

        New newNew = newRepository.getReferenceById(newVO.getId());

        if(newVO.getDate() != null)
            newNew.setDate(newNew.getDate());

        if(newVO.getIsMain())
            this.setMainNew(newVO);

        newRepository.save(newNew);
    }

    private void setMainNew(NewVO newVO) {
        MainNewCardVO mainNewCardVO = new MainNewCardVO();



        mainNewCardService.updateMainNew(mainNewCardVO);
    }

    public NewVO getOne(Long id){
        return new NewVO(newRepository.getReferenceById(id));
    }

    public List<NewVO> listThree(){

        List<NewVO> listVO = new ArrayList<>();
        List<New> list = newRepository.getLatestThree();

        for(New newNew : list)
            listVO.add(new NewVO(newNew));

        return listVO;
    }

    public List<NewVO> searchItems(String query){
        List<NewVO> listVO = new ArrayList<>();
        List<New> list = newRepository.searchItems(query);

        for(New newNew : list)
            listVO.add(new NewVO(newNew));

        return listVO;
    }



    public Page<New> list(Pageable pageable){

        return newRepository.findAllDesc(pageable);
    }

    public void deleteNew(Long id) {
        Optional<New> newDel = newRepository.findById(id);

        newDel.get().setActive(false);

        newRepository.save(newDel.get());
    }
}
