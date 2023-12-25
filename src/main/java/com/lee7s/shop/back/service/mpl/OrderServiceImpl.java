package com.lee7s.shop.back.service.mpl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.entity.Order;
import com.lee7s.shop.back.mapper.OrderMapper;
import com.lee7s.shop.back.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee7s.shop.back.utils.MessageDigestHexUtil;
import com.lee7s.shop.back.vo.GoodsPayVo;
import com.lee7s.shop.back.vo.PayVo;
import com.lee7s.shop.back.vo.VPayVo;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lee7s
 * @since 2023-10-07 10:33:14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {



    @Override
    public PayVo constructOrderPayDataAliPay(GoodsPayVo goodsPayVo) {

        System.out.println(goodsPayVo);
        PayVo payVo = new PayVo();
        payVo.setOut_trade_no(IdWorker.get32UUID());
        payVo.setSubject(goodsPayVo.getGoodsName());
        payVo.setTotal_amount(goodsPayVo.getTotal().toString());
        payVo.setOrderDesc(goodsPayVo.getGoodsName());
        payVo.setTimeout_express("1m");
        return payVo;

    }

    /**
     * 构造订单 V免签支付
     * @param goodsPayVo
     * @return
     */
    @Override
    public VPayVo constructOrderPayDataVPay(GoodsPayVo goodsPayVo) {

        VPayVo vPayVo = new VPayVo();
        vPayVo.setOut_trade_no(IdWorker.get32UUID());
        vPayVo.setPayMethod(goodsPayVo.getPayMethod());
        vPayVo.setTotal_amount(String.valueOf(goodsPayVo.getTotal()));
        vPayVo.setParam(null);
        vPayVo.setSign(MessageDigestHexUtil.doDigest(
                        vPayVo.getOut_trade_no() +
                        vPayVo.getPayMethod() +
                        vPayVo.getTotal_amount() +
                        Constant.VKey
                ).getStrCode());
        vPayVo.setIsHtml("0");
        vPayVo.setNotifyUrl("http://nobibibi.top/back/order/notify");
        vPayVo.setReturnUrl("http://localhost:8080");

        return vPayVo;

    }



}
