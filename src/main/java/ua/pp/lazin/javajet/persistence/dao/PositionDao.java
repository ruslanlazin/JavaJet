package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Position;

import java.util.List;

/**
 * The interface Position dao.
 *
 * @author Ruslan Lazin
 */
public interface PositionDao {
    /**
     * Create
     *
     * @param position the position
     * @return the long - position id
     */
    Long create(Position position);

    /**
     * Update int.
     *
     * @param position the position
     * @return the int number of updated rows
     */
    int update(Position position);

    /**
     * Delete int.
     *
     * @param position the position
     * @return the int number of deleted rows
     */
    int delete(Position position);

    /**
     * Delete cascade int.
     * Warn! All Users with that Position will be deleted as well!
     *
     * @param position the position
     * @return the int  number of deleted rows
     */
    int deleteCascade(Position position);

    /**
     * Find by title position.
     *
     * @param title the title
     * @return the position
     */
    Position findByTitle(String title);

    /**
     * Find all list.
     *
     * @return the list of Position
     */
    List<Position> findAll();
}
