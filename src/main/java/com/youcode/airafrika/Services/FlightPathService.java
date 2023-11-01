package com.youcode.airafrika.Services;

import com.youcode.airafrika.Dao.FlightPathDao;
import com.youcode.airafrika.Entities.FlightPath;

public class FlightPathService {
    private static final FlightPathDao flightPathDao = new FlightPathDao();

    public static FlightPath create(FlightPath flightPath) {
        return flightPathDao.store(flightPath).get();
    }
}
