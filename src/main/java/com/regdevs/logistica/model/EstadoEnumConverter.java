package com.regdevs.logistica.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoEnumConverter implements AttributeConverter<EstadoEnum, String> {

    @Override
    public String convertToDatabaseColumn(EstadoEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDescripcion();
    }

    @Override
    public EstadoEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        for (EstadoEnum estado : EstadoEnum.values()) {
            if (estado.getDescripcion().equalsIgnoreCase(dbData) || 
                estado.name().equalsIgnoreCase(dbData)) {
                return estado;
            }
        }
        
        // Si no coincide, devolvemos null o podríamos lanzar una excepción
        return null;
    }
}
