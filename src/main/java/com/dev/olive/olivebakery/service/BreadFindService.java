package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.model.entity.Bread;
import com.dev.olive.olivebakery.repository.BreadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-09.
 */

@Service
public class BreadFindService {

    private final BreadRepository breadRepository;

    public BreadFindService(BreadRepository breadRepository) {
        this.breadRepository = breadRepository;
    }

    public Bread findByName(String breadName) {
        return breadRepository.findByName(breadName)
                .orElseThrow(() -> new UserDefineException("해당 이름의 빵이 없습니다."));
    }

    public List<Bread> findsByNames(List<String> breadNames) {
        return breadRepository.findByNameIn(breadNames);
    }

}
