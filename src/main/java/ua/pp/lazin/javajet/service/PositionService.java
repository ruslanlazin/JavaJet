package ua.pp.lazin.javajet.service;

import ua.pp.lazin.javajet.persistence.dao.PositionDao;
import ua.pp.lazin.javajet.persistence.entity.Position;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PositionService {
    private static final PositionDao POSITION_DAO = DaoFactoryCreator.getFactory().getPositionDao();
    private static final PositionService INSTANCE = new PositionService();

    private PositionService() {
    }

    public static PositionService getINSTANCE(){
        return INSTANCE;
    }
    public List<Position> findAll() {
        return POSITION_DAO.findAll();
    }

    public Position findByTitle(String title) {
        return POSITION_DAO.findByTitle(title);
    }
}
