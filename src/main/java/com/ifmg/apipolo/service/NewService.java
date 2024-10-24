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
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
        New oldMainNew = newRepository.findMainNew();

        if(newVO.getImg() != null){
            Image image = new Image(newVO.getImg());
            imageRepository.save(image);

            newNew.setImg(imageRepository.getlastInserted());
        } else {
            newNew.setImg(imageRepository.getReferenceById(60L));
        }

        newNew.setTitle(newVO.getTitle());
        newNew.setDate(Date.from(Instant.now()));
        newNew.setCode(newVO.getCode());
        newNew.setActive(true);
        newNew.setIsMain(newVO.getIsMain());
        newRepository.save(newNew);

        if(newVO.getIsMain()){
            oldMainNew.setIsMain(false);
        }

        newRepository.save(oldMainNew);
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

    @Transactional
    public void updateNew(NewVO newVO) {

        Optional<New> newNew = newRepository.findById(newVO.getId());
        New oldMainNew = newRepository.findMainNew();

        if(newVO.getImg() != null){
            Image image = new Image(newVO.getImg());
            imageRepository.save(image);

            newNew.get().setImg(imageRepository.getlastInserted());
        } else {
            newNew.get().setImg(imageRepository.getReferenceById(60L));
        }

        newNew.get().setTitle(newVO.getTitle());
        newNew.get().setDate(Date.from(Instant.now()));
        newNew.get().setCode(newVO.getCode());
        newNew.get().setActive(true);
        newNew.get().setIsMain(newVO.getIsMain());

        newRepository.save(newNew.get());

        if(newVO.getIsMain()){
            oldMainNew.setIsMain(false);
        }

        newRepository.save(oldMainNew);
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
