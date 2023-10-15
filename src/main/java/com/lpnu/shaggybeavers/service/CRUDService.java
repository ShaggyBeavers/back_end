package com.lpnu.shaggybeavers.service;

import com.lpnu.shaggybeavers.model.EntityWithId;

import java.util.List;
import java.util.Optional;

public interface CRUDService <ENTITY extends EntityWithId<ID>,ID> {

    ENTITY save(ENTITY newEntity);

    ENTITY update(ENTITY newEntity);

    ENTITY delete(ENTITY entity);
    ENTITY deleteById(ID id);

    ENTITY findById(ID id);

    Optional<ENTITY> findByIdOpt(ID id);

    List<ENTITY> findAll();
}

