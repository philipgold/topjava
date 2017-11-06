package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.DAOFactory;
import ru.javawebinar.topjava.dao.MealDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";
    private MealDAO dao;

    public MealServlet() {
        super();
        DAOFactory inMemoryFactory = DAOFactory.getDAOFactory(DAOFactory.InMemory);

        dao = inMemoryFactory.getMealDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealid"));
            LOG.debug("delete meal: " + mealId);
            dao.deleteMeal(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getMealsWithExceeded());
        }else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealid"));
            LOG.debug("get meal for editing: " + mealId);
            request.setAttribute("meal", dao.getMealId(mealId));

        }else if (action.equalsIgnoreCase("list")){
            forward = LIST_MEAL;
            LOG.debug("get meal list");
            request.setAttribute("meals", dao.getMealsWithExceeded());
        }else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String description = request.getParameter("inputDescription");
        int calories = Integer.parseInt(request.getParameter("inputCalories"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("inputDateTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String sMealId = request.getParameter("inputMealId");

        if(sMealId == null || sMealId.isEmpty()){
            dao.insertMeal(dateTime, description, calories);
        }else{
            dao.updateMeal(Integer.parseInt(sMealId), dateTime, description, calories);
        }

        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("meals", dao.getMealsWithExceeded());
        view.forward(request, response);
    }
}
