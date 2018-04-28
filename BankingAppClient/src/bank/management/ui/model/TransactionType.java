/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ui.model;

/**
 * Represents types of transactions. Transactions can be:
 * <ul>
 *      <li><strong>DEPOSIT</strong> Money deposits</li>
 *      <li><strong>TRANSFER</strong> Money transfer</li>
 *      <li><strong>PAYMENT</strong> Payments</li>
 * </ul>
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 * @see bank.management.entity.Transaction
 * @see gestionbancariaserver.entity.Transaction#type
 */
public enum TransactionType {
    DEPOSIT, TRANSFER, PAYMENT
}
