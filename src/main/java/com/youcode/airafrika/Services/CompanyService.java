package com.youcode.airafrika.Services;

import com.youcode.airafrika.Dao.CompanyDao;
import com.youcode.airafrika.Entities.Company;

import java.util.List;

public class CompanyService {

    private static final CompanyDao companyDao = new CompanyDao();

    public static List<Company> getAllCompanies() {
        return companyDao.getAll();
    }
}
