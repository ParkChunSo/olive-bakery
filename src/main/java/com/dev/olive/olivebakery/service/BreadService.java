package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.domain.entity.Bread;
import com.dev.olive.olivebakery.repository.BreadRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by YoungMan on 2019-02-12.
 */

@Service
public class BreadService {

    private final BreadRepository breadRepository;

    public BreadService(BreadRepository breadRepository) {
        this.breadRepository = breadRepository;
    }

    public Bread findByName(String breadName) {
        return breadRepository.findByName(breadName)
                .orElseThrow(() -> new UserDefineException("해당 이름의 빵이 없습니다."));
    }

    public List<Bread> findsByNames(List<String> breadNames) {
        return breadRepository.findByNameIn(breadNames);
    }

    public int getFinalPrice(LinkedHashMap<String, Integer> breadInfos) {
        List<String> breadNames = new ArrayList<String>(breadInfos.keySet());
        List<Integer> counts = new ArrayList<Integer>(breadInfos.values());
        List<Bread> breads = findsByNames(breadNames);
        int finalPrice = 0;

        for (int i = 0; i < breadInfos.size(); i++) {
            finalPrice = finalPrice + (breads.get(i).getPrice() * counts.get(i));
        }

        return finalPrice;
    }

    public void getBread(){
        List<Bread> breadList = breadRepository.findAll();
    }

}
