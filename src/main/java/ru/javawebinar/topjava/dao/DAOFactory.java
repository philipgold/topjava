package ru.javawebinar.topjava.dao;

public abstract class DAOFactory {
    //DAO type list supported by the generator
    public static final int InMemory = 1;
    public static final int MySQL = 2;
    public static final int PostgreSQL = 3;

    public abstract MealDAO getMealDAO();
    //public abstract UserDAO getAccountDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case InMemory :
                return new InMemoryDAOFactory();
            case MySQL :
                //return new MySqlDAOFactory();
            case PostgreSQL :
                //return new PostgreSqlDAOFactory();
            default:
                return null;
        }
    }
}