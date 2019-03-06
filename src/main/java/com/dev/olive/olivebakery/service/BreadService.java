package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.model.entity.Bread;
import com.dev.olive.olivebakery.repository.BreadRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by YoungMan on 2019-02-12.
 */

@Service
public class BreadService {

    private final BreadRepository breadRepository;
    private BreadFindService breadFindService;

    public BreadService(BreadRepository breadRepository, BreadFindService breadFindService) {
        this.breadRepository = breadRepository;
        this.breadFindService = breadFindService;
    }

    /*public int getFinalPrice(List<Bread> breads) {
        return breads.stream().mapToInt(bread -> Math.toIntExact(bread.getPrice())).sum();
    }*/

    public int getFinalPrice(LinkedHashMap<String, Integer> breadInfos) {
        List<String> breadNames = new ArrayList<String>(breadInfos.keySet());
        List<Integer> counts = new ArrayList<Integer>(breadInfos.values());
        List<Bread> breads = breadFindService.findsByNames(breadNames);
        int finalPrice = 0;

        for (int i = 0; i < breadInfos.size(); i++) {
            finalPrice = finalPrice + (breads.get(i).getPrice() * counts.get(i));
        }

        return finalPrice;
    }
}
