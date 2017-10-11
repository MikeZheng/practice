package com.zrich;

import java.io.IOException;
import java.rmi.AccessException;
import java.security.Principal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;

/**
 * Created by Administrator on 2017/7/24.
 */
public class BeanUtilTest {

    public static void main(String[] args) {

        SourceSubClass1 sourceSubClass1 = new SourceSubClass1();
        sourceSubClass1.setChannelSalePrice(123L).setGoodsName("goods Name").setGoodsNo(333L).setGoodsShortName("short name").setOuterGoodsId("123");
        SourceClass1 sourceClass1 = new SourceClass1();
        sourceClass1.setApplyNo("aoppno ").setChannelCode(1111L).setChannelName("asdlsdfj").setExchangeTime("lllll").setList(new ArrayList<SourceSubClass1>());
        sourceClass1.getList().add(sourceSubClass1);

        System.out.println(sourceClass1);

        SourceClass2 sourceClass2 = new SourceClass2();
//        sourceClass2.setList(new ArrayList<SourceSubClass2>());
//        BeanUtils.copyFields(sourceClass1, sourceClass2, "list");
        System.out.println(sourceClass2);

        System.out.println("123".equals(null));
        System.out.println("AP2017071714015100000029".substring(0,2));
        System.out.println("AP2017071714015100000029".substring(2,16));
        System.out.println("AP2017071714015100000029".substring(16));

        System.out.println(Integer.valueOf("1"));

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        System.out.println(format.format(new Date()));
        System.out.println("123|123|22".split("\\|").length);
        System.out.println(Pattern.compile("//||").split("123|123|22", 0).length);

        try {
            System.out.println(new String(new BASE64Decoder().decodeBuffer("tMltXAcbEO9oK0Ia5O3Hrg==")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        NumberFormat format1 = NumberFormat.getCurrencyInstance();
        System.out.println(9999999999.99- 0.01);
        System.out.println(format1.format(9999999999.99 - 0.01));


    }


}
