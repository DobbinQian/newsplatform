package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.BalanceMapper;
import com.qdbgame.newsplatform.dao.BillMapper;
import com.qdbgame.newsplatform.entities.Balance;
import com.qdbgame.newsplatform.entities.Bill;
import com.qdbgame.newsplatform.entities.Order;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/13 10:07
 */
@DubboService
public class PaymentServiceImpl implements PaymentService {

    @Resource
    BalanceMapper balanceMapper;

    @Resource
    BillMapper billMapper;


    @Override
    public void transfer(Bill bill) {
        Long t = System.currentTimeMillis();
        balanceMapper.updateBalanceByBill(bill);
        System.out.println(System.currentTimeMillis()-t);
        createBill(bill);
    }

    @Override
    public void createBill(Bill bill) {
        billMapper.insert(bill);
    }

    @Override
    public boolean createBalance(Integer userId) {
        return balanceMapper.insert(new Balance(userId,0));
    }

    @Override
    public Bill getBill(Integer billId) {
        return billMapper.getById(billId);
    }

    @Override
    public List<Bill> getBills(Integer pageNo, Integer pageSize, Integer userId, Integer type) {
        return billMapper.getList(pageNo,pageSize,userId,type);
    }

    @Override
    public Bill paymentOrder(Order order) {
        Bill bill = new Bill();
        bill.setPayerId(order.getConsumerId());
        bill.setPayeeId(order.getGoodsOwnId());
        bill.setCreationTime(System.currentTimeMillis());
        bill.setTransactionAmount(order.getPrice());
        bill.setOrderId(order.getOrderId());
        transfer(bill);
        return bill;
    }
}
