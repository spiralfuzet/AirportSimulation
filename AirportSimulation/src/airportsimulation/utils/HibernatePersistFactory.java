/*
 * Copyright (c) 2015 Rendszergazda.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rendszergazda - initial API and implementation and/or initial documentation
 */
package airportsimulation.utils;

import airportsimulation.hibernate.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Rendszergazda
 * @param <T> The buildt and persisted type to be built
 */
public abstract class HibernatePersistFactory<T> implements Factory<T> {

    @Override
    public final T create(List<String> fields) {
        final T t = doCreate(fields);
        persist(t);
        return t;
    }

    protected abstract T doCreate(List<String> fields);

    private void persist(final T t) {
        try {
            doPersist(t);
        } catch (HibernateException ex) {
            System.err.println("Cannot persist '" + t + "' due to:\n" + ex);
        }
    }

    private void doPersist(final T t) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("persisting: " + t);
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
    }

}
