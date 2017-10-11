package com.zrich;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

/**
 * Created by Administrator on 2017/8/23.
 */
public class Dom4JTest {

    public static void parse(String content) {
        SAXReader reader = new SAXReader();
        ReqPacket reqPacket = new ReqPacket();

        try {
            // to prevent from XXE, disable doctype declare and external entities
            reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
            reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            Document document = reader.read(new ByteArrayInputStream(content.getBytes("UTF-8")));

            reqPacket.setVersion(document.selectSingleNode("//OTM/Head/Version").getText().trim());
            reqPacket.setSequenceId(document.selectSingleNode("//OTM/Head/SequenceId").getText().trim());
//            reqPacket.setTimeStamp(document.selectSingleNode("//OTM/Head/TimeStamp").getText().trim());
            reqPacket.setDistributorId(document.selectSingleNode("//OTM/Head/DistributorId").getText().trim());
            reqPacket.setTransactionCode(document.selectSingleNode("//OTM/Head/TransactionCode").getText().trim());
            reqPacket.setSigned(document.selectSingleNode("//OTM/Head/Signed").getText().trim());

            reqPacket.setEncodeBody(document.selectSingleNode("//OTM/Body").getText().trim());
        } catch (DocumentException | SAXException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
