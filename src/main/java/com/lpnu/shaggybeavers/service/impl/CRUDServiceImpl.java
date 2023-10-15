package com.lpnu.shaggybeavers.service.impl;

import com.lpnu.shaggybeavers.exception.NotExistsObjectException;
import com.lpnu.shaggybeavers.model.EntityWithId;
import com.lpnu.shaggybeavers.service.CRUDService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class CRUDServiceImpl <ENTITY extends EntityWithId<ID>, ID> implements CRUDService<ENTITY, ID> {

    protected abstract JpaRepository<ENTITY,ID> getRepository();

    @Override
    @Transactional
    public ENTITY save (@NotNull ENTITY newEntity) {
        return getRepository().save(newEntity);
    }

    @Override
    @Transactional
    public ENTITY update (@NotNull ENTITY newEntity) {
        ENTITY oldEntity = this.findById(newEntity.getId());
        BeanUtils.copyProperties(newEntity,oldEntity);
        return getRepository().save(oldEntity);
    }

    @Override
    @Transactional
    public ENTITY delete (@NotNull ENTITY entity) {
        getRepository().delete(entity);
        return entity;
    }

    @Override
    public ENTITY deleteById (@NotNull ID id) {
        ENTITY entity = findById(id);
        getRepository().deleteById(id);
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public ENTITY findById (@NotNull ID id) {
        return getRepository().findById(id).orElseThrow(NotExistsObjectException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ENTITY> findByIdOpt (@NotNull ID id) {
        return getRepository().findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ENTITY> findAll(){
        return getRepository().findAll();
    }
}
