/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author jon
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(bank.management.exceptionmapper.AccountExceptionMapper.class);
        resources.add(bank.management.exceptionmapper.BankingBussinessExceptionMapper.class);
        resources.add(bank.management.exceptionmapper.CredentialExceptionMapper.class);
        resources.add(bank.management.exceptionmapper.CustomerExceptionMapper.class);
        resources.add(bank.management.exceptionmapper.CustomerUnauthorizedExceptionMapper.class);
        resources.add(bank.management.exceptionmapper.EntityDeleteExceptionMapper.class);
        resources.add(bank.management.exceptionmapper.TransactionExceptionMapper.class);
        resources.add(bank.management.rest.BankingREST.class);
    }
    
}
