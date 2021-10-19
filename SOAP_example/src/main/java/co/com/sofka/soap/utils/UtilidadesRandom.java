package co.com.sofka.soap.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class UtilidadesRandom {

    private UtilidadesRandom() {
    }

    public static String generarNumeroRandom(int longitud) {
        return RandomStringUtils.randomNumeric(longitud);
    }
}
