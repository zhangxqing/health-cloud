package com.health.system.service.mapper;

import java.util.List;

/**
 * 通用的dto到实体映射器。
 *
 * @author zq
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List <E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);
}
