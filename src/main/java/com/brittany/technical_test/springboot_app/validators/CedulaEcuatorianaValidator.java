package com.brittany.technical_test.springboot_app.validators;

import com.brittany.technical_test.springboot_app.validators.annotations.IsCedulaEcuatoriana;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CedulaEcuatorianaValidator implements ConstraintValidator<IsCedulaEcuatoriana, String>{

    @Override
    public boolean isValid(String cedula, ConstraintValidatorContext context) {
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) return false;

        int tercerDigito = Character.getNumericValue(cedula.charAt(2));
        if (tercerDigito >= 6) return false;

        int[] coef = {2,1,2,1,2,1,2,1,2};
        int suma = 0;

        for (int i = 0; i < coef.length; i++) {
            int dig = Character.getNumericValue(cedula.charAt(i)) * coef[i];
            suma += (dig >= 10) ? dig - 9 : dig;
        }

        int verificador = (suma % 10 == 0) ? 0 : 10 - (suma % 10);
        return verificador == Character.getNumericValue(cedula.charAt(9));
    }

}
