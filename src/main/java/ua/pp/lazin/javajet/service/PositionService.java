package ua.pp.lazin.javajet.service;

import ua.pp.lazin.javajet.persistence.dao.PositionDao;
import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * The type Position service.
 *
 * @author Ruslan Lazin
 */
public class PositionService {
    private static final PositionDao positionDao = DaoFactoryCreator.getFactory().getPositionDao();
    private static final PositionService INSTANCE = new PositionService();

    private PositionService() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static PositionService getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Create.
     *
     * @param position the position
     * @return the long - Position id
     */
    public Long create(Position position) {
        return positionDao.create(position);
    }

    /**
     * Find position  by title
     *
     * @param title the title
     * @return the Position
     */
    public Position findByTitle(String title) {
        return positionDao.findByTitle(title);
    }

    /**
     * Find all.
     *
     * @return the list of Position
     */
    public List<Position> findAll() {
        return positionDao.findAll();
    }
}
