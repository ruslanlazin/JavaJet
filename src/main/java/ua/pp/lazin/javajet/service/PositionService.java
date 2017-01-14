package ua.pp.lazin.javajet.service;

import ua.pp.lazin.javajet.persistence.dao.PositionDao;
import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PositionService {
    private static final PositionDao positionDao = DaoFactoryCreator.getFactory().getPositionDao();
    private static final PositionService INSTANCE = new PositionService();

    private PositionService() {
    }

    public static PositionService getINSTANCE() {
        return INSTANCE;
    }

    public List<Position> findAll() {
        return positionDao.findAll();
    }

    public Position findByTitle(String title) {
        return positionDao.findByTitle(title);
    }

    public Long create(Position position) {
        return positionDao.create(position);
    }
}
