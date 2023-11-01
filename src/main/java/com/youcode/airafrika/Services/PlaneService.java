package com.youcode.airafrika.Services;

import com.youcode.airafrika.Dao.PlaneDao;
import com.youcode.airafrika.Entities.Plane;

import java.util.List;

public class PlaneService {
    private static final PlaneDao planeDao = new PlaneDao();
    public static List<Plane> getAllPlanes() {
        return planeDao.getAll();
    }

    public static Plane findOne(Long id) throws Exception {
        if (!planeDao.findById(id).isPresent())
            throw new Exception("Plane not found");
        return planeDao.findById(id).get();
    }
}
