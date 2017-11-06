package ru.javawebinar.topjava.dao;

public class InMemoryDAOFactory extends DAOFactory {

    @Override
    public MealDAO getMealDAO() {
        return new InMemoryMealDAO();
    }


//    @Override
//    public UserDAO getUserDAO() {
//        return new InMemoryUserDAO();
//    }

}
