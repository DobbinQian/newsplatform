package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.entities.Bill;
import com.qdbgame.newsplatform.entities.Order;

import java.util.List;
import java.util.Map;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/13 9:58
 */
public interface PaymentService {

    void transfer(Bill bill);

    void createBill(Bill bill);

    boolean createBalance(Integer userId);

    Bill getBill(Integer billId);

    List<Bill> getBills(Integer pageNo, Integer pageSize, Integer userId, Integer type);

    Bill paymentOrder(Order order);

}
