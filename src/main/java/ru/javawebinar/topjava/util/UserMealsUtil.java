package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        LocalDateTime date0 = mealList.get(0).getDateTime();
        String asBasicIsoDate0 = date0.format(DateTimeFormatter.BASIC_ISO_DATE);
        int cal=mealList.get(0).getCalories();

        List<UserMealWithExceed> mealWithExceedList = new ArrayList<>();
        Map<LocalDate, Integer> map = new HashMap<>();

        for (int i=1;i<mealList.size();i++)
        {
            LocalDateTime date = mealList.get(i).getDateTime();
            String asBasicIsoDate = date.format(DateTimeFormatter.BASIC_ISO_DATE);

            if (asBasicIsoDate.equals(asBasicIsoDate0))
            {
                cal += mealList.get(i).getCalories();
            }
            else
            {
                cal=mealList.get(i).getCalories();
                asBasicIsoDate0 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
            }
            map.put(date.toLocalDate(),cal);
        }
        for (int i=0;i<mealList.size();i++)
        {
            if (mealList.get(i).getDateTime().toLocalTime().toSecondOfDay()>startTime.toSecondOfDay()&& mealList.get(i).getDateTime().toLocalTime().toSecondOfDay()<endTime.toSecondOfDay())
            mealWithExceedList.add(new UserMealWithExceed(mealList.get(i).getDateTime(), mealList.get(i).getDescription(),mealList.get(i).getCalories(), map.get(mealList.get(i).getDateTime().toLocalDate())>caloriesPerDay)) ;
        }

        return mealWithExceedList;
    }
}
