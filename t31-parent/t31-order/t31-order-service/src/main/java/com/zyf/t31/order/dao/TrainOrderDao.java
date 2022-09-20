package com.zyf.t31.order.dao;

import com.zyf.t31.core.dao.ICrudDao;
import com.zyf.t31.order.po.TrainOrder;
import com.zyf.t31.order.vo.SeatType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TrainOrderDao extends ICrudDao<TrainOrder> {

    /**
     * 通过车次获取座位
     * @param numberId 车次号
     * @return
     */
    @Select("SELECT `type` AS type, AVG(price) AS price FROM train_carriage WHERE number_id = #{numberId} GROUP BY type")
    public List<SeatType> getSeatTypeByNumberId(Long numberId);

}
