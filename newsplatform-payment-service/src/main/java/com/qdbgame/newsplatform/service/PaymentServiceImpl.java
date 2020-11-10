package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.BalanceMapper;
import com.qdbgame.newsplatform.dao.BillMapper;
import com.qdbgame.newsplatform.entities.Balance;
import com.qdbgame.newsplatform.entities.Bill;
import com.qdbgame.newsplatform.entities.Order;
import com.qdbgame.newsplatform.tools.exception.ResultException;
import lombok.extern.slf4j.Slf4j;
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
        Integer balance = balanceMapper.getBalanceByUserId(bill.getPayerId());
        if(balance<bill.getTransactionAmount()){
            throw new ResultException("转账失败,余额不足");
        }
        balanceMapper.updateBalanceByBill(bill);
    }

    @Override
    public void createBill(Bill bill) {
        billMapper.insert(bill);
    }

    @Override
    public void createBalance(Integer userId) {
        balanceMapper.insert(new Balance(userId,0));
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
        createBill(bill);
        return bill;
    }
}
