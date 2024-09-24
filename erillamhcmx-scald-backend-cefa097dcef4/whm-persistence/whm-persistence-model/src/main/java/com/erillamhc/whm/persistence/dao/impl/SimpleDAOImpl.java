package com.erillamhc.whm.persistence.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.erillamhc.whm.persistence.dao.SimpleDAO;
import com.erillamhc.whm.persistence.exception.SimpleDAOException;

/**
 * Implementa las operaciones basicas para la persistencia de informacion.
 * version 1.1 Implementa el recurso {@link UserTransaction } para el manejo de
 * transacciones en JTA.
 *
 * @author Ivo Danic
 * @param <T>
 * @param <P>
 * @since 1.0
 * @version 1.1
 */
public class SimpleDAOImpl<T extends Serializable, P extends Serializable> implements SimpleDAO<T, P> {

    protected static final Logger LOGGER = Logger.getLogger(SimpleDAOImpl.class.getName());

    @PersistenceContext(unitName = "whm")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private final Class<T> entityClass;

    /**
     * Constructor: Inicializa los atributos necesarios para la identificacion 
     * de la entidad.
     */
    @SuppressWarnings("unchecked")
	public SimpleDAOImpl() {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    /**
     * Encuentra entidad por su llave primaria.
     * @param id Valor de identicador para encontrar la entidad.
     * @return 
     */
    @Override
    public T findByID(P id) throws SimpleDAOException {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Ejecuta un {@code NameQuery} con la {@code Entidad} predeterminado por la
     * implementacion del {@link SimpleDAOImpl}
     * @param namequery {@code NameQuery} a ejecutar.
     * @param params Parametro necesarios para el {@code NameQuery}
     * @return Regresa entidad, de otra manera un {@code null}.
     * @throws SimpleDAOException Cuando la consulta tiene más de un resultado o
     * cuando ocurre un error a la conexion de la base datos.
     */
    @Override
    public T uniqueResult(String namequery, Map<String, Object> params) throws SimpleDAOException {
        return uniqueResult(namequery, params, entityClass);
    }

    /**
     * Ejecuta un {@code NameQuery} con una {@code Entidad} distinta al predeterminado por la
     * implementacion del {@link SimpleDAOImpl}
     * @param <S> Tipo objeto de la Entidad.
     * @param namequery {@code NameQuery} a ejecutar.
     * @param params Parametro necesarios para el {@code NameQuery}
     * @param entity {@code Entidad} distinta al predeterminado
     * @return Regresa entidad, de otra manera un {@code null}.
     * @throws SimpleDAOException Cuando la consulta tiene más de un resultado o
     * cuando ocurre un error a la conexion de la base datos.
     */
    @Override
    public <S> S uniqueResult(String namequery, Map<String, Object> params, Class<S> entity) throws SimpleDAOException {
        TypedQuery<S> query = getEntityManager().createNamedQuery(namequery, entity);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            LOGGER.log(Level.INFO, "No Result by query {0}", namequery);
            printMessageException(nre, "No Result by query {0}:");
            return null;
        } catch (NonUniqueResultException nure) {
            LOGGER.log(Level.INFO, "More One Result by query {0}", namequery);
            throw new SimpleDAOException("The query has more than one result", nure);
        }
    }

    /**
     * Retorna una lista de entidades.
     * @return Objeto tipo {@link List} con las entidades encontradas.
     */
    @Override
    public List<T> findAll() {
        final String statementSelect = "SELECT entity FROM "
                + entityClass.getSimpleName()
                + " entity";
        TypedQuery<T> query = getEntityManager().createQuery(statementSelect, entityClass);
        return query.getResultList();
    }

    /**
     * Persiste una entidad.
     * @param t Entidad ha persistir.
     * @throws SimpleDAOException Se lanzá cuando ocurre un error al procesar
     * la transaccion o al persistir la entidad.
     */
    @Override
    public void save(T t) throws SimpleDAOException {
        try {
            activate();
            getEntityManager().persist(t);
            commit();
        } catch (PersistenceException pe) {
            LOGGER.log(Level.SEVERE, "save exception {0} ", pe);
            throw new SimpleDAOException(pe);
        } finally {
            rollbackTransaction();
        }
    }

    /**
     * Actualiza una entidad.
     * @param t Entidad ha actualizar
     * @throws SimpleDAOException Se lanzá cuando ocurre un error al procesar
     * la transaccion o al actualizar la entidad.
     */
    @Override
    public void update(T t) throws SimpleDAOException {
        try {
            activate();
            getEntityManager().merge(t);
            commit();
        } catch (PersistenceException pe) {
            LOGGER.log(Level.SEVERE, "update exception {0} ", pe);
            throw new SimpleDAOException(pe);
        } finally {
            rollbackTransaction();
        }
    }

     /**
     * Elimina una entidad.
     * @param t Entidad ha eliminar
     * @throws SimpleDAOException Se lanzá cuando ocurre un error al procesar
     * la transaccion o al eliminar la entidad.
     */
    @Override
    public void delete(T t) throws SimpleDAOException {
        try {
            activate();

            if (!getEntityManager().contains(t)) {
                T merge = getEntityManager().merge(t);
                getEntityManager().remove(merge);
            } else {
                getEntityManager().remove(t);
            }
            commit();
        } catch (PersistenceException pe) {
            LOGGER.log(Level.SEVERE, "delete exception {0} ", pe);
            throw new SimpleDAOException(pe);
        } finally {
            rollbackTransaction();
        }
    }

    /**
     * Persiste una lista de entidades mediante un bacth
     * @param list Lista con las entidades a procesar.
     * @throws SimpleDAOException Se lanzá cuando ocurre un error al procesar
     * la transaccion o al persistir las entidades.
     */
    @Override
    public void batch(List<T> list) throws SimpleDAOException {
        try {
            activate();
            int countCommit = 300;
            int count = 1;
            for (T t : list) {
                getEntityManager().persist(t);
                if ((count++ % countCommit) == 0) {
                    LOGGER.log(Level.INFO, "Batch commit transaction on record: {0}", count - 1);
                    commit();
                    getEntityManager().clear();
                    activate();
                    LOGGER.log(Level.INFO, "Batch activate transaction");
                }
            }
            commit();
        } catch (PersistenceException pe) {
            LOGGER.log(Level.SEVERE, "batch exception {0} ", pe);
            throw new SimpleDAOException(pe);
        } finally {
            rollbackTransaction();
        }
    }

    /**
     * Regresa el recurso {@link UserTransaction}
     * @return {@link UserTransaction}
     */
    public UserTransaction getUserTransaction() {
        return userTransaction;
    }

    /**
     * Asigna el recurso de tipo {@link UserTransaction}
     * @param userTransaction Valor que tomara el {@link UserTransaction}
     */
    public void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }

    /**
     * Asigna el administrador de entidades.
     * @param entityManager Valor que tomara el {@link EntityManager}
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Retorna la unidad de persistencia.
     * @return {@link EntityManager}
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Revierte los cambios de la transaccion a un estado anterior.
     * @throws SimpleDAOException Se lanza cuando ocurre un error al revertir la transaccion.
     */
    @Override
    public void rollbackTransaction() throws SimpleDAOException {
        if (isTransactionActive()) {
            try {
                getUserTransaction().rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                LOGGER.log(Level.SEVERE, "rollback TX exception {0} ", ex);
                throw new SimpleDAOException(ex);
            }
        }
    }

    /**
     * Verifica si la transaccion actual esta activa.
     * @return {@code True} cuando la transaccion esta activa, 
     * caso contrario regresa {@code False}
     * @throws SimpleDAOException Se lanza cuando ocurre un error al activar la transaccion.
     */
    @Override
    public boolean isTransactionActive() throws SimpleDAOException {
        try {
            int status = getUserTransaction().getStatus();
            return status == Status.STATUS_ACTIVE;
        } catch (SystemException se) {
            LOGGER.log(Level.INFO, "User Transaction exception: {0}", se);
            throw new SimpleDAOException(se);
        }
    }

    /**
     * Compromete la transaccion. 
     * @throws SimpleDAOException Se lanza cuando ocurre un error al comprometer la transaccion.
     */
    @Override
    public void commit() throws SimpleDAOException {
        if (isTransactionActive()) {
            try {
                getUserTransaction().commit();
            } catch (HeuristicMixedException | HeuristicRollbackException
                    | IllegalStateException | RollbackException
                    | SecurityException | SystemException ex) {
                LOGGER.log(Level.SEVERE, "commit TX exception {0} ", ex);
                throw new SimpleDAOException(ex);
            }
        } else {
            throw new SimpleDAOException("Transaction not active");
        }
    }

    /**
     * Activa la transaccion de {@link UserTransaction}
     * @throws SimpleDAOException Se lanza cuando se intenta activar la transaccion.
     */
    @Override
    public void activate() throws SimpleDAOException {
        if (!isTransactionActive()) {
            try {
                getUserTransaction().begin();
            } catch (NotSupportedException | SystemException ex) {
                LOGGER.log(Level.SEVERE, "activate TX exception {0} ", ex);
                throw new SimpleDAOException(ex);
            }
        }
    }

    /**
     * Imprime el mensaje de la excepcion en un flujo {@link Logger}
     * @param throwable Excepcion a imprimir
     * @param msg Mensaje adicional al mensaje de la excepcion. 
     */
    private void printMessageException(Throwable throwable, String msg) {
        LOGGER.log(Level.INFO, msg, throwable.getMessage());
    }
}
