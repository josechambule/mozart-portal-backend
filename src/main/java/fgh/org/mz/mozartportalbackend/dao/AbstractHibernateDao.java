package fgh.org.mz.mozartportalbackend.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({ "unchecked", "deprecation" })
public class AbstractHibernateDao<T extends Serializable> {
	private Class<T> clazz;

    @Autowired
    protected SessionFactory sessionFactory;

    public void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }

    public T findOne(final long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    
	public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }
	
    public T create(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.openSession();
    }

}
