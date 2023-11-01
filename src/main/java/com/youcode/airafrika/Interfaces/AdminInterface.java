package com.youcode.airafrika.Interfaces;

import com.youcode.airafrika.Entities.Admin;

import java.util.Optional;

public interface AdminInterface extends IDao<Admin, Long>{
    public Optional<Admin> findByEmail(Admin admin);
}
