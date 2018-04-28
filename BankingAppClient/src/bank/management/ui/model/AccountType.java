/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ui.model;

/**
 * Represents types of accounts. Accounts can be:
 * <ul>
 *      <li><strong>SAVINGS</strong> Savings accounts</li>
 *      <li><strong>CHECK</strong> Checking accounts</li>
 *      <li><strong>CREDIT</strong> Credit accounts</li>
 * </ul>
 * Only the credit accounts can have {@link Account#creditLine}
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 * @see bank.management.entity.Account
 * @see gestionbancariaserver.entity.Account#type
 */
public enum AccountType {
    SAVINGS, CHECK, CREDIT
}