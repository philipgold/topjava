package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController extends AbstractMealController {

    @GetMapping("/meals")
    protected String doGet(Model model){
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @GetMapping("/meals/edit/{id}")
    protected String openExistingMeal(Model model, @PathVariable String id ){
        final Meal meal = super.get(getId(id));
        model.addAttribute("meal", meal);
        return  "mealForm";
    }

    @GetMapping({"/meals/new"})
    protected String openNewMeal(Model model){
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return  "mealForm";
    }

    @PostMapping
    protected String doPost(HttpServletRequest request) throws IOException {

        String sId = request.getParameter("id");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (sId.isEmpty()) {
            super.create(meal);
        } else {
            super.update(meal, getId(sId));
        }
        return "redirect:/meals";
    }

    @PostMapping ("/meals/filter")
    protected String getBetween(HttpServletRequest request){
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        request.setAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        request.setAttribute("startDate", startDate);
        request.setAttribute("startTime", startTime);
        request.setAttribute("endDate", endDate);
        request.setAttribute("endTime", endTime);
        return "redirect:/meals";
    }

    @GetMapping("/meals/delete/{id}")
    protected String doDelete(@PathVariable String id ){
        super.delete(getId(id));
        return "redirect:/meals";
    }
}
