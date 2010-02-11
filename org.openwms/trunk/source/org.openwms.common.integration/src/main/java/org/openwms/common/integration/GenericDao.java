/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.common.integration;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A GenericDao.
 * <p>
 * This interface provides basic functionality to create/read/update and remove
 * Entity classes. These can be POJOs without any dependencies. Merely the
 * implementation of this interface knows about how the Entity is been
 * persisted.
 * </p>
 * 
 * @author <a href="mailto:openwms@googlemail.com">Heiko Scherrer</a>
 * @version $Revision: 877 $
 * @since 0.1
 */
public interface GenericDao<T extends Serializable, ID extends Serializable> {

    /**
     * Suffix for the FIND_ALL named query
     */
    public final static String FIND_ALL = ".findAll";

    /**
     * Suffix for the FIND_BY_ID named query
     */
    public final static String FIND_BY_ID = ".findById";

    /**
     * Find and return the Entity identified by the technical <tt>id</tt>.
     * 
     * @param id
     *            - Unique technical key to find the Entity
     * @return - Entity class identified by id
     */
    T findById(ID id);

    /**
     * Find all entities and return them in a {@link java.util.List}.
     * 
     * @return - List of all Entity classes
     */
    List<T> findAll();

    /**
     * Find all Entities with an NamedQuery. Pass in the name of the JPA
     * NamedQuery and a parameter map.
     * 
     * @param queryName
     *            - Defined name of the NamedQuery.
     * @param params
     *            - Map of parameters to pass to the query
     * @return - List of found Entity classes
     */
    List<T> findByQuery(String queryName, Map<String, ?> params);

    /**
     * Find and return the Entity identified by the natural unique id.
     * 
     * @param id
     *            - Natural key to find the Entity (also known as business key)
     * @return - The found Entity
     */
    T findByUniqueId(Serializable id);

    /**
     * Updates an Entity with the persistence layer and return the synchronized.
     * 
     * @param entity
     *            - Entity instance to be synchronized with the persistence
     *            layer
     * @return - The synchronized Entity instance. If JPA as implementation is
     *         used, the returned instance is in managed state.
     */
    T save(T entity);

    /**
     * Removes an already persistent Entity.
     * 
     * @param entity
     *            - Entity instance to be removed
     */
    void remove(T entity);

    /**
     * Persist a transient entity.
     * 
     * @param entity
     *            - Entity instance to be persisted
     */
    void persist(T entity);

    /**
     * Set the type of Entity to deal with
     * 
     * @param persistentClass
     *            - Class type of the Entity instance
     */
    void setPersistentClass(Class<T> persistentClass);
}