package com.zrich;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/15.
 */
@Service
public class TestService {

    @Autowired
    private TestDAO testDAO;

    public void count() {
        System.out.println(testDAO.count());
    }
}
