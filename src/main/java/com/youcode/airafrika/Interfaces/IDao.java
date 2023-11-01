package com.youcode.airafrika.Interfaces;

import java.util.List;
import java.util.Optional;

public interface IDao<Entity, Identifier> {
    public Optional<Entity> store(Entity entity);
    public Optional<Entity> update(Entity entity);
    public Optional<Entity> findById(Identifier id);
    public List<Entity> getAll();
    public boolean delete();
}
