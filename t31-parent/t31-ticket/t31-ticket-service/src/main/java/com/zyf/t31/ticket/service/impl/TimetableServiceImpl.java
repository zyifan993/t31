package com.zyf.t31.ticket.service.impl;

import com.zyf.t31.core.service.impl.CrudServiceImpl;
import com.zyf.t31.ticket.dao.TimetableDao;
import com.zyf.t31.ticket.po.Timetable;
import com.zyf.t31.ticket.service.ITimetableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableServiceImpl extends CrudServiceImpl<Timetable> implements ITimetableService {
    @Override
    public List<String> getFromStation(String fromStation) {
        return ((TimetableDao)getBaseMapper()).getFromStations(fromStation);
    }

    @Override
    public List<String> getToStation(String toStation) {
        return ((TimetableDao)getBaseMapper()).getToStations(toStation);
    }
}
