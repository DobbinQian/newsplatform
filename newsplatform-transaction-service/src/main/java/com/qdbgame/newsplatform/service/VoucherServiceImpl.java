package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.VoucherMapper;
import com.qdbgame.newsplatform.entities.Voucher;
import com.qdbgame.newsplatform.tools.exception.ResultException;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/27 13:57
 */

@DubboService
public class VoucherServiceImpl implements VoucherService {

    @Resource
    private VoucherMapper voucherMapper;

    @Override
    public boolean voucherCheck(Integer userId) {
        if(voucherMapper.getByUserId(userId)!=null){
            return true;
        }
        return false;
    }

    @Override
    public void modifyVoucherInfo(Voucher voucher) {
        voucherMapper.update(voucher);
    }

    @Override
    public Voucher modifyItemInfo(Integer itemId, Integer state) {
        Voucher voucher = new Voucher();
        voucher.setVoucherId(itemId);
        voucher.setState(state);
        modifyVoucherInfo(voucher);
        return voucher;
    }

    @Override
    public Object modifyItemInfo(Integer itemId, Integer userId, Integer state) {
        //TODO 判断是否是该用户的交易凭证
        Voucher voucher = new Voucher();
        voucher.setVoucherId(itemId);
        voucher.setState(state);
        modifyVoucherInfo(voucher);
        return voucher;
    }

    @Override
    public void createItem(Integer userId) {
        Voucher voucher = new Voucher();
        voucher.setUserId(userId);
        voucher.setCreationTime(System.currentTimeMillis());
        voucher.setState(Voucher.State.DEFAULT);
        voucherMapper.insert(voucher);
    }
}
