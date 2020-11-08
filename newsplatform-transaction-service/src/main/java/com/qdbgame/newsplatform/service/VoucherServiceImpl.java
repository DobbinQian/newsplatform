package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.dao.VoucherMapper;
import com.qdbgame.newsplatform.entities.Voucher;
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
    public boolean modifyVoucherInfo(Voucher voucher) {
        return voucherMapper.update(voucher);
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
        return null;
    }

    @Override
    public boolean createItem(Integer userId) {
        Voucher voucher = new Voucher();
        voucher.setUserId(userId);
        voucher.setCreationTime(System.currentTimeMillis());
        voucher.setState(Voucher.State.DEFAULT);
        return voucherMapper.insert(voucher);
    }
}
