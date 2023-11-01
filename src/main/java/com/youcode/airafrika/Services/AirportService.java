package com.youcode.airafrika.Services;

import com.youcode.airafrika.Dao.AirportDao;
import com.youcode.airafrika.Entities.Airport;

import java.util.List;

public class AirportService {
    private static final AirportDao airportDao = new AirportDao();

    public static List<Airport> getAllAirports() {
        return airportDao.getAll();
    }

    public static Airport findOne(Long id) throws Exception {
        if (!airportDao.findById(id).isPresent())
            throw new Exception("Airport not found");
        return airportDao.findById(id).get();
    }
}
