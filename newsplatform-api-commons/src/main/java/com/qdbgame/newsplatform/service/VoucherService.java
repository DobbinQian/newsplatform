package com.qdbgame.newsplatform.service;

import com.qdbgame.newsplatform.entities.Voucher;
import com.qdbgame.newsplatform.tools.trade.TradeModifyItem;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/26 14:51
 */
public interface VoucherService extends TradeModifyItem {
    boolean voucherCheck(Integer userId);

    boolean modifyVoucherInfo(Voucher voucher);
}
