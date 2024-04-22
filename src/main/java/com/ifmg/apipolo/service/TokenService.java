package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Talent;
import com.ifmg.apipolo.entity.Token;
import com.ifmg.apipolo.repository.TalentRepository;
import com.ifmg.apipolo.repository.TokenRepository;
import com.ifmg.apipolo.vo.TalentVO;
import com.ifmg.apipolo.vo.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public List<TokenVO> list(){

        List<TokenVO> listVO = new ArrayList<>();
        List<Token> list = tokenRepository.findAll();

        for(Token token : list)
            listVO.add(new TokenVO(token));

        return listVO;
    }
}
