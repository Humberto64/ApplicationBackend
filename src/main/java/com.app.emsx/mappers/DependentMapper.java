package com.app.emsx.mappers;

import com.app.emsx.dtos.dependent.DependentRequest;
import com.app.emsx.dtos.dependent.DependentResponse;
import com.app.emsx.entities.Dependent;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DependentMapper {

    default Dependent toEntity(DependentRequest dto) {
        if (dto == null) return null;

        Dependent entity = new Dependent();
        entity.setName(dto.getName());
        entity.setRelationship(dto.getRelationship());
        entity.setPhone(dto.getPhone());

        return entity;
    }

    default DependentResponse toResponse(Dependent entity) {
        if (entity == null) return null;

        DependentResponse dto = new DependentResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRelationship(entity.getRelationship());
        dto.setPhone(entity.getPhone());

        if (entity.getEmployee() != null) {
            String first = entity.getEmployee().getFirstName();
            String last  = entity.getEmployee().getLastName();
            dto.setEmployeeName(
                    ((first != null ? first : "") + " " + (last != null ? last : "")).trim()
            );
        } else {
            dto.setEmployeeName(null);
        }

        return dto;
    }

    default void updateEntity(Dependent entity, DependentRequest dto) {
        if (dto == null || entity == null) return;

        entity.setName(dto.getName());
        entity.setRelationship(dto.getRelationship());
        entity.setPhone(dto.getPhone());

    }
}
