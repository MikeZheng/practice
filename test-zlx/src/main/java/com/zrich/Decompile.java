package com.zrich;

public class Decompile {

    public String getSequence(String sysCode) {
        if (sysCode == null)
            throw new IllegalArgumentException();
        if (sysCode.length() > 6){
            throw new IllegalArgumentException();
        }
        if (sysCode.length()  >= 2) {
StringBuilder stringBuilder = new StringBuilder();
stringBuilder.append(sysCode).append(getSubSequence());
return stringBuilder.toString();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String getSubSequence() {
        return null;
    }

}
