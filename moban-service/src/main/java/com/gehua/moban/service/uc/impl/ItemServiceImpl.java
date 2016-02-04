package com.gehua.moban.service.uc.impl;

import com.gehua.moban.model.entity.uc.Items;
import com.gehua.moban.service.uc.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rono on 2016/1/20.
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Override
    public List<Items> getItems() {
        List<Items> list = new ArrayList<>();
        list.add(new Items(1,"鞋子1",1.21D));
        list.add(new Items(1,"鞋子2",1.22D));
        list.add(new Items(1,"鞋子3",1.23D));
        return list;
    }
}
