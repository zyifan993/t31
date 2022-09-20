package com.zyf.t31.ticket.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyf.t31.core.exception.T31SystemException;
import com.zyf.t31.core.exception.enums.ResponseEnum;
import com.zyf.t31.core.service.impl.CrudServiceImpl;
import com.zyf.t31.core.utils.RexUtil;
import com.zyf.t31.core.utils.StringUtil;
import com.zyf.t31.ticket.constant.SkuConstant;
import com.zyf.t31.ticket.dao.TrainSkuDao;
import com.zyf.t31.ticket.po.TrainSku;
import com.zyf.t31.ticket.service.ITrainSkuService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TrainSkuServiceImpl extends CrudServiceImpl<TrainSku> implements ITrainSkuService {

    @Override
    public int pushSku(Date date) {
        return ((TrainSkuDao) getBaseMapper()).pushSku(date);
    }

    @Override
    public PageInfo<TrainSku> listPage(TrainSku entity, int pageNum, int pageSize){

        if (entity != null && StringUtil.isNotBlank(entity.getTrainNumber())
                && !RexUtil.match(SkuConstant.SKU_TRAIN_NUMBER_REX,entity.getTrainNumber())){
            throw new T31SystemException(ResponseEnum.QUERY_PARAMETER_ERROR);
        }
        return super.listPage(entity,pageNum,pageSize);
    }
}
