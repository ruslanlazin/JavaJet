package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.persistence.entity.Position;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface PositionDao {
    Long create(Position position);

    Position findByTitle(String title);

    List<Position> findAll();

    int update(Position position);

    int delete(Position position);
}
