package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void createImage(ImageVO imageVO) {
        Image image = imageRepository.getReferenceById(imageVO.getId());
        image.setCode(imageVO.getCode());
        imageRepository.save(image);
    }

    public ImageVO getOne(Long id){

        Optional<Image> image = imageRepository.findById(id);
        return new ImageVO(image);
    }

    public List<ImageVO> list(){

        List<ImageVO> listVO = new ArrayList<>();
        List<Image> list = imageRepository.findAll();

        for(Image img : list)
            listVO.add(new ImageVO(img));

        return listVO;
    }

    public void updateImage(ImageVO imageVO){

        Image image = imageRepository.getReferenceById(imageVO.getId());

        image.setCode(imageVO.getCode());
        image.setName(imageVO.getName());

        imageRepository.save(image);
    }

    public void deleteCapacitation(Long id) {
        imageRepository.deleteById(id);
    }
}
