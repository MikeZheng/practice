package com.zrich;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Created by Administrator on 2017/8/22.
 */
public class StringReplaceTest {

    private final static String[] chars = new String[]{"<","?",">","\""," ", "=","/","&",";"};
    private final static String[] strings = new String[]{"%3C","%3F", "%3E","%22","+","%3D","%2F","%26","%3B"};

    public static void main(String[] args) throws DocumentException, SAXException, UnsupportedEncodingException {

        String str = "%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3COTM%3E%3CHead%3E%3CVersion%3E3.0.0%3C%2FVersion%3E%3CSequenceId%3E33333333%3C%2FSequenceId%3E%3CTimeStamp%3E2017-08-11+10%3A35%3A52%3C%2FTimeStamp%3E%3CDistributorId%3EO10090%3C%2FDistributorId%3E%3CTransactionCode%3E01%3C%2FTransactionCode%3E%3CSigned%3EYTllYzAzNTJmOTZkMzQzMTAyZTI1MmMxNDkzZTMzYjc%3D%3C%2FSigned%3E%3C%2FHead%3E%3CBody%3E%3C%21%5BCDATA%5BQjRBNjIwRDI0NTJDMDc1NDc4ODM3MTZFRUQyQzFGRjUyOUVDQ0ZENTdCMUVGRDU4MEFCOEQ2NUNEQkU1ODk1MUEzMkU0MUE0NkQ3NDQyOTFDQUIzMkZFQTA1MTBFMUM3ODIzQTczMDYwNDFEQjcyMERCRDk0RDUzRUYwQjVDNDZDQjYzMjZEQ0FCNzY2RTVCMTM0ODJBMjc4QTU0MjQwNjhEOTBCOTU4QzU4NTc2REYxMTQ3Q0Y4MEI1OTFDMTdDMjkyQzE3MDdGMzhCNjM3Njc4NkZBNEYwNzE4MTE5RDM0NzY4MTI0NkUyOUZFQkI5RUJGMEU0QUE2NUY1M0FBNzcyQzQyQ0RGRjhBQzJGRkUwOTBEOUM5QUJFMDQ3MkI4NTQwRUFDREJCRTdFNjEwQ0IwREFEQjU3RTBGNDFCMjIyNTcxRDVEQkQ4N0VCNUNFRUYwMjI3QzBGODA5RDJCMjJFQjYxQTExQzZBM0E5NEMyNDM5QTgzOTgxRDQ5QUMzNEI1QzFDMTZFOTA4NzFFNDVBNEFBMDZDQTAwNDY2Mzg%3D%5D%5D%3E%3C%2FBody%3E%3C%2FOTM%3E";

        StringReplaceTest.detransfer(str);

        str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE foo [<!ENTITY xxergezb SYSTEM \"file:///etc/passwd\">]><OTM><Head><Version>3.0.0</Version><SequenceId>33333333&xxergezb;</SequenceId><TimeStamp>2017-08-11 10%3A35%3A52</TimeStamp><DistributorId>O10090</DistributorId><TransactionCode>01</TransactionCode><Signed>YTllYzAzNTJmOTZkMzQzMTAyZTI1MmMxNDkzZTMzYjc=</Signed></Head><Body><%21%5BCDATA%5BQjRBNjIwRDI0NTJDMDc1NDc4ODM3MTZFRUQyQzFGRjUyOUVDQ0ZENTdCMUVGRDU4MEFCOEQ2NUNEQkU1ODk1MUEzMkU0MUE0NkQ3NDQyOTFDQUIzMkZFQTA1MTBFMUM3ODIzQTczMDYwNDFEQjcyMERCRDk0RDUzRUYwQjVDNDZDQjYzMjZEQ0FCNzY2RTVCMTM0ODJBMjc4QTU0MjQwNjhEOTBCOTU4QzU4NTc2REYxMTQ3Q0Y4MEI1OTFDMTdDMjkyQzE3MDdGMzhCNjM3Njc4NkZBNEYwNzE4MTE5RDM0NzY4MTI0NkUyOUZFQkI5RUJGMEU0QUE2NUY1M0FBNzcyQzQyQ0RGRjhBQzJGRkUwOTBEOUM5QUJFMDQ3MkI4NTQwRUFDREJCRTdFNjEwQ0IwREFEQjU3RTBGNDFCMjIyNTcxRDVEQkQ4N0VCNUNFRUYwMjI3QzBGODA5RDJCMjJFQjYxQTExQzZBM0E5NEMyNDM5QTgzOTgxRDQ5QUMzNEI1QzFDMTZFOTA4NzFFNDVBNEFBMDZDQTAwNDY2Mzg=%5D%5D></Body></OTM>";
        StringReplaceTest.transfer(str);

        String correct = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><OTM>数量的疯狂</OTM>";
        String wrong = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE foo [<!ENTITY xxergezb SYSTEM \"file:///etc/passwd\">]><OTM>&xxergezb;</OTM>";

        readBySAXReader(correct);
        readBySAXReader(wrong);
        readByDBF(correct);
        readByDBF(wrong);
        readByDocumentHelper(correct);
        readByDocumentHelper(wrong);
    }

    public static void transfer(String str) {
        for (int i = 0; i < chars.length; i++) {
            str = str.replace(chars[i], strings[i]);
        }
        System.out.println(str);
    }

    public static void detransfer(String str) {
        for (int i = 0; i < chars.length; i++) {
            str = str.replace(strings[i], chars[i]);
        }
        System.out.println(str);
    }

    public static void readBySAXReader(String content) {
        System.out.println("============================ Read By SAXReader Start ==================================");
        System.out.println(" Content is "+ content);
        try {
            SAXReader reader = new SAXReader();

            Document document = reader.read(new InputSource(new StringReader(content)));
            System.out.println("Before disable DOCTYPE, the value is = "+document.getRootElement().getText());

            reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
            reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

            document = reader.read(new ByteArrayInputStream(content.getBytes("UTF-8")));
            System.out.println("Before disable DOCTYPE, the value is = "+document.getRootElement().getText());
        } catch (SAXException e) {
//            e.printStackTrace();
        } catch (DocumentException e) {
//            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
        }
        System.out.println("============================ Read By SAXReader End ==================================");
    }

    public static void readByDocumentHelper(String content) {
        try {
            Document document = DocumentHelper.parseText(content);
            System.out.println(document.getRootElement().getText());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }



    public static void readByDBF(String content) {
        System.out.println("============================ Read By DocumentBuilderFactory ==================================");
        System.out.println(" Content is "+ content);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder= factory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.parse(new ByteArrayInputStream(content.getBytes("UTF-8")));
            System.out.println("Before disable DOCTYPE, the value is = "+document.getLastChild().getFirstChild());

            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);

            documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.parse(new ByteArrayInputStream(content.getBytes("UTF-8")));
            System.out.println("After disable DOCTYPE, the value is =" + document.getLastChild().getFirstChild());
        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
        } catch (SAXException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        System.out.println("============================ Read By DocumentBuilderFactory End ==================================");
    }
}
