package com.zyf.t31.ticket.service;

import com.zyf.t31.core.service.ICrudService;
import com.zyf.t31.ticket.po.TrainSku;

import java.util.Date;

/**
 * sku
 */
public interface ITrainSkuService extends ICrudService<TrainSku> {

    /**
     * 生成sku
     * @param date
     * @return
     */
    int pushSku(Date date);
}
