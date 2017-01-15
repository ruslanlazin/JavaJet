package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Position;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface PositionDao {
    Long create(Position position);

    int update(Position position);

    int deleteCascade(Position position);

    int delete(Position position);

    Position findByTitle(String title);

    List<Position> findAll();
}
