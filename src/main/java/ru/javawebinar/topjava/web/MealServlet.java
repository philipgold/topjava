package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.DAOFactory;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";
    private MealDAO dao;

   @Override
    public void init() throws ServletException {
        super.init();
        DAOFactory inMemoryFactory = DAOFactory.getDAOFactory(DAOFactory.InMemory);
        dao = inMemoryFactory.getMealDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward= LIST_MEAL;
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            LOG.debug("delete meal: " + Integer.parseInt(request.getParameter("id")));
            dao.delete(Integer.parseInt(request.getParameter("id")));
        }else if (action.equalsIgnoreCase("edit")){
            LOG.debug("get meal for editing: " + Integer.parseInt(request.getParameter("id")));
            forward = INSERT_OR_EDIT;
        }else if (action.equalsIgnoreCase("list")){
            LOG.debug("get meal list");
        }else {
            forward = INSERT_OR_EDIT;
        }
        setRequestAttributes(request);

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    private void setRequestAttributes (HttpServletRequest request){
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete") ||
                action.equalsIgnoreCase("list")){

            List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(
                    (List<Meal>) dao.getList(),
                    LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

            request.setAttribute("meals", meals);
        }else if (action.equalsIgnoreCase("edit") ){
            int mealId = Integer.parseInt(request.getParameter("id"));

            request.setAttribute("meal", dao.getById(mealId));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String description = request.getParameter("inputDescription");
        int calories = Integer.parseInt(request.getParameter("inputCalories"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("inputDateTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String sMealId = request.getParameter("inputMealId");

        if(sMealId == null || sMealId.isEmpty()){
            dao.insert(new Meal(-1, dateTime, description, calories));
        }else{
            dao.update(new Meal(Integer.parseInt(sMealId), dateTime, description, calories));
        }

        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded((List<Meal>) dao.getList(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        request.setAttribute("meals", meals);
        view.forward(request, response);
    }
}
