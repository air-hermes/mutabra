package com.mutabra.services;

import com.google.code.morphia.Datastore;
import com.mutabra.domain.CodedEntity;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class CodedEntityServiceImpl<E extends CodedEntity>
        extends EntityServiceImpl<E, String>
        implements CodedEntityService<E> {

    public CodedEntityServiceImpl(final Datastore datastore, final Class<E> entityClass) {
        super(datastore, entityClass);
    }
}
