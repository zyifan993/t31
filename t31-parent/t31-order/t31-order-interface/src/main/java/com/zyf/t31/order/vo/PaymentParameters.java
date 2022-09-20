package com.zyf.t31.order.vo;

import lombok.Data;

@Data
public class PaymentParameters {

    /*
     * 支付参数，三方平台要求是现下划线的
     */

    /**
     * 订单号，商户网站订单系统中唯一订单号
     */
    private String out_trade_no;

    /**
     * 付款金额
     */
    private String total_amount;

    /**
     * 订单名称
     */
    private String subject;

    /**
     * 商品描述
     */
    private String body;
}
