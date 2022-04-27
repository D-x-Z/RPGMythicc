package net.danh.rpgmythicc.Utils;

public final class JSONUtils {


    private JSONUtils() {

    }

    public static String formatSimpleJSON(String msg) {
        return "{\"text\":\"" + msg + "\"}";
    }

}
