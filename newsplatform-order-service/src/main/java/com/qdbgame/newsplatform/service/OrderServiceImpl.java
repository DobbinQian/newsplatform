package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.OrderMapper;
import com.qdbgame.newsplatform.entities.Goods;
import com.qdbgame.newsplatform.entities.Order;
import com.qdbgame.newsplatform.tools.trade.BrowsePermissionTrade;
import com.qdbgame.newsplatform.tools.trade.NewsTrade;
import com.qdbgame.newsplatform.tools.trade.VoucherTrade;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/27 10:55
 */
@DubboService
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private NewsTrade newsTrade;

    @Resource
    private BrowsePermissionTrade browsePermissionTrade;

    @Resource
    private VoucherTrade voucherTrade;

    @DubboReference(check = false)
    private PaymentService paymentService;

    @DubboReference(check = false)
    private VoucherService voucherService;

    @DubboReference(check = false)
    private NewsService newsService;

    @DubboReference(check = false)
    private BrowsePermissionService browsePermissionService;

    @DubboReference(check = false)
    private TransactionService transactionService;


    @Override
    public Order createOrder(Goods goods, Integer userId) {
        Order order = new Order();
        order.setState(Order.State.NON_PAYMENT);
        order.setGoodsId(goods.getGoodsId());
        order.setGoodsOwnId(goods.getOwnId());
        order.setGoodsOwnType(goods.getOwnType());
        order.setGoodsType(goods.getType());
        order.setConsumerId(userId);
        order.setItemId(goods.getItemId());
        order.setCreationTime(String.valueOf(System.currentTimeMillis()));
        order.setPrice(goods.getPrice());
        orderMapper.insert(order);
        return order;
    }

    @Override
    @GlobalTransactional
    public boolean paymentOrder(Integer orderId, Integer userId) {
        Order order = orderMapper.getById(orderId);
        if(order==null||!order.getConsumerId().equals(userId)||!order.getState().equals(Order.State.NON_PAYMENT)){
            return false;
        }
        if(order.getGoodsType().equals(Goods.Type.NEWS)){
            newsTrade.payment(orderId,userId,this,paymentService,newsService);
        }else if(order.getGoodsType().equals(Goods.Type.BP)){
            browsePermissionTrade.setBrowsePermissionService(browsePermissionService);
            browsePermissionTrade.payment(orderId,userId,this,paymentService,null);
        }else if(order.getGoodsType().equals(Goods.Type.VOUCHER)){
            voucherTrade.payment(orderId,userId,this,paymentService,voucherService);
        }else{
            return false;
        }
        order.setState(Order.State.SUCCESS);
        order.setCreationTime(order.getCreationTime()+"+"+String.valueOf(System.currentTimeMillis()));
        modifyOrder(order);
        return true;
    }

    @Override
    @GlobalTransactional
    public boolean cancelOrder(Integer orderId, Integer userId) {
        Order order = orderMapper.getById(orderId);
        if(!order.getConsumerId().equals(userId)||!order.getState().equals(Order.State.NON_PAYMENT)){
            return false;
        }
        order.setState(Order.State.CANCEL);
        order.setCreationTime(order.getCreationTime()+"+"+String.valueOf(System.currentTimeMillis()));
        modifyOrder(order);
        Goods goods = new Goods();
        goods.setGoodsId(order.getGoodsId());
        goods.setInventory(1);
        goods.setSaleCount(-1);
        transactionService.modifyGoodsInfo(goods);
        return true;
    }

    @Override
    public List<Order> getOrders(Integer pageNo, Integer pageSize, Integer userId, Integer state) {
        if(pageNo<=0){
            return null;
        }
        return orderMapper.getList((pageNo-1)*pageSize,pageSize,state,userId);
    }

    @Override
    public Order getOrder(Integer orderId) {
        return orderMapper.getById(orderId);
    }

    @Override
    public Order modifyOrder(Order order) {
        orderMapper.update(order);
        return getOrder(order.getOrderId());
    }
}
