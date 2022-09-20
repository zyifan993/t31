package com.zyf.t31.ticket.dao;

import com.zyf.t31.core.dao.ICrudDao;
import com.zyf.t31.ticket.po.TrainSku;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;

public interface TrainSkuDao extends ICrudDao<TrainSku> {

    @Insert("insert into `train_sku`(train_date,number_id,train_number,start_station,end_station,from_station,to_station,start_time,arrival_time,duration,hard_seat_stock,first_seat_stock,second_seat_stock,special_seat_stock,hard_sleeper_stock,soft_sleeper_stock)\n" +
            "SELECT \n" +
            "#{date},number_id,train_number, start_station, end_station, from_station, to_station, start_time, arrival_time, duration,\n" +
            "(SELECT SUM(b.count) FROM train_carriage AS b WHERE b.type='硬坐' AND b.number_id= a.number_id),\n" +
            "(select sum(b.count) from train_carriage AS b where b.type ='一等座' and b.number_id = a.number_id),\n" +
            "(select sum(b.count) from train_carriage AS b where b.type ='二等座' and b.number_id = a.number_id),\n" +
            "(select sum(b.count) from train_carriage AS b where b.type ='三等座' and b.number_id = a.number_id),\n" +
            "(select sum(b.count) from train_carriage AS b where b.type ='特等座' and b.number_id = a.number_id),\n" +
            "(select sum(b.count) from train_carriage AS b where b.type ='硬卧' and b.number_id = a.number_id),\n" +
            "(select sum(b.count) from train_carriage AS b where b.type ='软卧' and b.number_id = a.number_id)\n" +
            "from \n" +
            "train_timetable AS a")
    int pushSku(Date date);
}
