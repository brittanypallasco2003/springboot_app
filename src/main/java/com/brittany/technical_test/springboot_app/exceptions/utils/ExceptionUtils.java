package com.brittany.technical_test.springboot_app.exceptions.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionUtils {

     private static final Pattern ENUM_VALUE_PATTERN = Pattern.compile("from String \"([^\"]+)\"");

    public static String buildEnumErrorMessage(String exceptionMessage, Class<? extends Enum<?>> enumClass) {
        Matcher matcher = ENUM_VALUE_PATTERN.matcher(exceptionMessage);
        
        if (matcher.find()) {
            System.out.println("Entro esta es la excepción");
            String valorErroneo = matcher.group(1);
            return String.format(
                "Valor '%s' no es válido. Valores permitidos: %s",
                valorErroneo,
                Arrays.toString(enumClass.getEnumConstants())
            );
        }

        return "Error al leer el cuerpo de la solicitud. Verifique el formato del JSON.";
    }

}
