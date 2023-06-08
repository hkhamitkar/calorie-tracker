package com.capcal.userservice.service;

import com.capcal.userservice.dto.FoodLog;
import com.capcal.userservice.dto.IAdminReport;
import com.capcal.userservice.dto.IUserDailyReport;
import com.capcal.userservice.repository.FoodLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FoodLogService {

    @Autowired
    FoodLogRepository foodLogRepository;

    public List<FoodLog> findByUserId(String username) {
        return foodLogRepository.findByUsername(username);
    }

    public FoodLog findById(Long id) {
        return foodLogRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public FoodLog save(FoodLog foodEntry) {
        return foodLogRepository.save(foodEntry);
    }

    public Page<FoodLog> findByMultipleCriteria(String userId,
                                                Date fromDate,
                                                Date toDate,
                                                Integer threshold,
                                                int offset,
                                                int page,
                                                String field) {

        if (null != userId && null==threshold && null == fromDate && null == toDate) {
            return foodLogRepository.findByUsername(userId, PageRequest.of(offset, page).withSort(Sort.by(field).descending()));
        } else if (null != fromDate && null != toDate && null == threshold && null!=userId) {
            return foodLogRepository.findByUsernameAndIntakeDateBetween(userId, fromDate,
                    toDate, PageRequest.of(offset, page).withSort(Sort.by(field).descending()));
        } else if (null != threshold && null == fromDate && null == toDate) {
            return foodLogRepository.findByUsernameAndCaloriesGreaterThan(userId,
                    threshold,
                    PageRequest.of(offset, page).withSort(Sort.by(field).descending()));
        }else if(null==userId && null != fromDate && null != toDate){
            return foodLogRepository.findByIntakeDateBetween(fromDate,
                    toDate, PageRequest.of(offset, page).withSort(Sort.by(field).descending()));
        }

        return foodLogRepository.findByUsernameAndIntakeDateBetweenAndCaloriesGreaterThan(
                userId,
                fromDate,
                toDate,
                threshold,
                PageRequest.of(offset, page).withSort(Sort.by(field).descending()));
    }


    public void deleteById(long id) {
        foodLogRepository.deleteById(id);
    }

    public Page<FoodLog> findAll(int offset, int pagesize, String fieldName) {
        return foodLogRepository.findAll(PageRequest.of(offset, pagesize)
                .withSort(Sort.by(fieldName).descending()));
    }

    public List<IAdminReport> findAdminData(){
        return foodLogRepository.findAdminData();
    }

    public List<IUserDailyReport> findUserData(String username,Date intakedate){
        SimpleDateFormat formatter=new SimpleDateFormat("YYYY-MM-dd");
        return foodLogRepository.findUserData(username,null!=intakedate?
                formatter.format(intakedate):null);
    }


}
