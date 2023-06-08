package com.capcal.userservice.repository;

import com.capcal.userservice.dto.IAdminReport;
import com.capcal.userservice.dto.FoodLog;
import com.capcal.userservice.dto.IUserDailyReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FoodLogRepository extends JpaRepository<FoodLog, Long> {

    public List<FoodLog> findByUsername(String username);

    public Page<FoodLog> findByUsername(String userId, PageRequest pageRequest);
    public Page<FoodLog> findByUsernameAndIntakeDateBetween(String userId, Date fromDate,
                                                            Date toDateDate, PageRequest pageRequest);

    public Page<FoodLog> findByUsernameAndCaloriesGreaterThan(String userId, int threshold, PageRequest pageRequest);
    public Page<FoodLog> findByIntakeDateBetween(Date fromDate,
                                                 Date toDateDate, PageRequest pageRequest);

    public Page<FoodLog> findByUsernameAndIntakeDateBetweenAndCaloriesGreaterThan(String userId,
                                                                                  Date fromDate,
                                                                                  Date toDateDate,
                                                                                  int threshold,
                                                                                  PageRequest pageRequest);

    @Query(value = "select (select count(*) from foodlog\n" +
            "where food_date between date_sub(now(),INTERVAL 7 DAY) and date_sub(now(),INTERVAL -1 DAY)\n" +
            "AND user.username=foodlog.username) currentweek ,\n" +
            "(select count(*) from foodlog\n" +
            "where food_date between date_sub(now(),INTERVAL 14 DAY) and date_sub(now(),INTERVAL 8 DAY)\n" +
            "AND user.username=foodlog.username) previousweek ,\n" +
            "(select avg(calories) from foodlog\n" +
            "where food_date between date_sub(now(),INTERVAL 7 DAY) and date_sub(now(),INTERVAL -1 DAY)\n" +
            "AND user.username=foodlog.username\n" +
            ") averageinaweek,\n" +
            "user.username\n" +
            "from user \n" +
            "where user.role='ROLE_USER'", nativeQuery = true)
    public List<IAdminReport> findAdminData();

    @Query(value="SELECT sum(calories) calories,DATE(food_date) days,\n" +
            "if(sum(calories)>=(select callimit from user where user.username=foodlog.username),'RED','GREEN') calstatus FROM foodlog\n" +
            "where 1=1 and foodlog.username=:username\n" +
            "AND (:intakedate IS NULL OR DATE(food_date)=:intakedate)"+
            "group by DATE(food_date) order by days",
            nativeQuery = true)
    public List<IUserDailyReport> findUserData(@Param("username") String username,
                                               @Param("intakedate") String intakedate);

}
