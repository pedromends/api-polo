package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Position;
import com.ifmg.apipolo.repository.PositionRepository;
import com.ifmg.apipolo.vo.PositionVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {

    PositionRepository positionRepository;

    public void createPosition(PositionVO positionVO){
        Position position = new Position();

        position.setDescription(positionVO.getDescription());
        positionRepository.save(position);
    }

    public List<PositionVO> listPositions(){
        List<PositionVO> listVO = new ArrayList<>();
        List<Position> list = positionRepository.findAll();

        for(Position position : list) {
            listVO.add(new PositionVO(position));
        }
        return listVO;
    }

    public void updatePosition(PositionVO positionVO){
        Position position = positionRepository.getReferenceById(positionVO.getId());

        if(positionVO.getDescription() != null)
            position.setDescription(positionVO.getDescription());

        positionRepository.save(position);
    }

    public void deletePosition(Long id){
        positionRepository.deleteById(id);
    }
}
