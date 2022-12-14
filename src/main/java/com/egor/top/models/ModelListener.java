package com.egor.top.models;

import com.egor.top.models.security.RoleModel;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;

@Slf4j
public class ModelListener {

    private static final String DELETED = "DELETED ";
    private static final String CREATED = "CREATED ";
    private static final String UPDATED = "UPDATED ";

    private static final String PREFIX = "[{}] with ";
    private static final String NAME = "name {}";
    private static final String ID = "id {}";

    @PostPersist
    private void postPersist(AbstractItemModel abstractItemModel) {
        audit(abstractItemModel, CREATED);
    }

    @PostUpdate
    private void postUpdate(AbstractItemModel abstractItemModel) {
        audit(abstractItemModel, UPDATED);
    }

    @PostRemove
    private void postRemove(AbstractItemModel abstractItemModel) {
        audit(abstractItemModel, DELETED);
    }

    @PrePersist
    private void prePersist(AbstractItemModel abstractItemModel) {
        if (abstractItemModel instanceof RoleModel) {
            AbstractNamedModel roleModel = (AbstractNamedModel) abstractItemModel;
            roleModel.setName("ROLE_" + roleModel.getName());
        }
    }

    private void audit(AbstractItemModel abstractItemModel, String status) {
        String className = abstractItemModel.getClass().getSimpleName();
        if (abstractItemModel instanceof AbstractNamedModel) {
            LOG.trace(status + PREFIX + NAME, className, ((AbstractNamedModel) abstractItemModel).getName());
            return;
        }
        LOG.trace(status + PREFIX + ID, className, abstractItemModel.getId());
    }

}
