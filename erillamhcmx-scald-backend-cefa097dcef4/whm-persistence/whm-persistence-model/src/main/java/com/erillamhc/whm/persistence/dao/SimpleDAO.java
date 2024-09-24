package com.erillamhc.whm.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.erillamhc.whm.persistence.exception.SimpleDAOException;

/**
 * Dao que implementa operaciones basicas a la base de datos. Version 1.1 Se
 * agrega operaciones para el manejo de transacciones en JTA. Version 1.2
 * Algunas operaciones lanzan {@link SimpleDAOException}.
 *
 * @author Ivo Danic G.
 * @version 1.2
 * @param <T>
 * @param <P>
 * @since 1.0
 *
 */
public interface SimpleDAO<T, P extends Serializable> {

    /**
     * Busca una entidad por su ID.
     *
     * @param id Id de la entidad a buscar.
     * @return Regresa la entidad obtenida por su ID.
     *
     */
    T findByID(P id) throws SimpleDAOException;

    /**
     * Regresa una lista de entidades.
     *
     * @return Regresa una lista de entidades encontradas en la BD.
     *
     */
    List<T> findAll();

    /**
     * Guarda una entidad.
     *
     * @param t Enitdad a guardar.
     * @throws com.mx.dmlink.toolbox.persistence.exception.SimpleDAOException
     *
     */
    void save(T t) throws SimpleDAOException;

    /**
     * Actualiza una entidad.
     *
     * @param t Enitdad a actualizar.
     * @throws com.mx.dmlink.toolbox.persistence.exception.SimpleDAOException
     *
     */
    void update(T t) throws SimpleDAOException;

    /**
     * Elimina una entidad.
     *
     * @param t Enitdad a eliminar.
     * @throws com.mx.dmlink.toolbox.persistence.exception.SimpleDAOException
     *
     */
    void delete(T t) throws SimpleDAOException;

    /**
     * Guarda una lista de entidades.
     *
     * @param list Lista a guardar.
     * @throws SimpleDAOException
     */
    void batch(List<T> list) throws SimpleDAOException;

    /**
     * Revierte los cambios hechos en la base de datos.
     *
     * @throws com.mx.dmlink.toolbox.persistence.exception.SimpleDAOException
     */
    void rollbackTransaction() throws SimpleDAOException;

    /**
     * Verifica si la transaccion esta activa.
     *
     * @return Regres <code>true</code> si la transaccion actual esta activa,
     * <code>false</code> en caso contrario.
     * @throws com.mx.dmlink.toolbox.persistence.exception.SimpleDAOException
     */
    boolean isTransactionActive() throws SimpleDAOException;

    /**
     * Aplica los cambios y cierra la transaccion.
     *
     * @throws com.mx.dmlink.toolbox.persistence.exception.SimpleDAOException
     */
    void commit() throws SimpleDAOException;

    /**
     * Activa la transacci�n actual.
     *
     * @throws com.mx.dmlink.toolbox.persistence.exception.SimpleDAOException
     */
    void activate() throws SimpleDAOException;

    /**
     * Consulta para obtener un unico resultado
     *
     * @param namequery
     * @param params
     * @return
     * @throws com.mx.dmlink.toolbox.persistence.exception.SimpleDAOException
     */
    T uniqueResult(String namequery, Map<String, Object> params) throws SimpleDAOException;

    /**
     *
     * @param <S>
     * @param namequery
     * @param params
     * @param entity
     * @return
     * @throws SimpleDAOException
     */
    <S> S uniqueResult(String namequery, Map<String, Object> params, Class<S> entity) throws SimpleDAOException;
}