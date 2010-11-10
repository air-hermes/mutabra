package com.noname.web.base.pages;

import com.noname.domain.BaseEntity;
import com.noname.services.BaseEntityService;

public abstract class AbstractEntityPage<E extends BaseEntity>
		extends AbstractPage {

	protected abstract BaseEntityService<E> getEntityService();

	protected E get(final Long entityId) {
		return entityId != null ? getEntityService().get(entityId) : null;
	}

	protected E create() {
		return getEntityService().create();
	}

	protected void save(final E entity) {
		getEntityService().saveOrUpdate(entity);
	}

	protected void delete(final E entity) {
		getEntityService().delete(entity);
	}
}