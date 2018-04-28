/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.logic;

/**
 *
 * @author jon
 */
public class ManagerFactory {
    public static Manager getManager() {
        return new ManagerImplementation();
    }
}
