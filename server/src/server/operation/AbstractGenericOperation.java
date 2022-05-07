/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.operation;

import server.dbb.DBRepository;
import server.dbb.impl.RepositoryDBGeneric;
import server.repository.Repository;
import common.domain.GenericEntity;

/**
 *
 * @author Djina
 */
public abstract class AbstractGenericOperation<T extends GenericEntity, R> {
    protected final Repository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

    public final void execute(T entity) throws Exception {
        try {
            preconditions(entity);
            startTransaction();
            executeOperation(entity);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    // Operation-specific method
    protected abstract void preconditions(T entity) throws Exception;

    private void startTransaction() throws Exception {
        ((DBRepository) repository).connect();
    }

    // Operation-specific method
    protected abstract void executeOperation(T entity) throws Exception;

    private void commitTransaction() throws Exception {
        ((DBRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DBRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DBRepository) repository).disconnect();
    }
}
