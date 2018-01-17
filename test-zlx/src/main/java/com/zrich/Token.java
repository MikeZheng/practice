package com.zrich;

import com.eg.egsc.common.component.auth.utils.JWTUtils;

import java.util.UUID;


public class Token {
    public static void main(String[] args) {
        String token = JWTUtils.getToken(UUID.randomUUID().toString());
        System.out.println(token);
        String existToken="eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTE1OTA0NzQ3fQ.N_rvgxoDUdHCe_O1rwyOW5xRgGg2IKNcb_ZcMT8CQckDl71pCu6YI3ZkgdgAXU3mrBS2xgz626X8jy3-8nHdY9s6JeRJ2hMov0P88erbfe_H5nYv9Um4DVHFUIyZQXlcQ0vgFM4OxAPEKpryK2PoXOt09HNPKneaEPEiPsz53EA";
        JWTUtils.JWTResult result = JWTUtils.checkToken(existToken);
        System.out.println(result.getCode());
        System.out.println(result.getMsg());
        System.out.println(result.getUid());
        System.out.println(JWTUtils.getToken("test", 604800000));
    }
}


