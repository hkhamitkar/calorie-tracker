package com.capcal.userservice.controller;


import com.capcal.userservice.dto.FoodLog;
import com.capcal.userservice.service.FoodLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/foodlog")
@Slf4j
@CrossOrigin(origins = "*")
public class FoodLogRestController {

    @Autowired
    FoodLogService foodLogService;

    @PostMapping("/add")
    @PreAuthorize("#foodlog.username == authentication.principal.username OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@RequestBody FoodLog foodlog) {
        FoodLog updatedLog = foodlog;
        try {
            updatedLog = foodLogService.save(foodlog);
        } catch (JpaSystemException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to add food log, please check server logs", e);
        }
        return new ResponseEntity(updatedLog, HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateFoodLog(@RequestBody FoodLog foodLog) {
        try {
            foodLog=foodLogService.save(foodLog);
        } catch (JpaSystemException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to add food log, please check server logs", e);
        }
        return new ResponseEntity(foodLog,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteFoodLog(@PathVariable Long id) {
        try {
            foodLogService.deleteById(id);
        } catch (JpaSystemException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to add food log, please check server logs", e);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getFoodLog(@PathVariable Long id) {
        FoodLog foodlog=null;
        try {
             foodlog=foodLogService.findById(id);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No data found", e);
        }
        return new ResponseEntity(foodlog,HttpStatus.OK);
    }

    @GetMapping("/admin/{offset}/{pagesize}/{field}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<FoodLog>> getAllFoodLogs(@PathVariable int offset,
                                                        @PathVariable int pagesize,
                                                        @PathVariable String field) {
        Page<FoodLog> foodlogsByUser = foodLogService.findAll(offset, pagesize, field);
        if (foodlogsByUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(foodlogsByUser, HttpStatus.OK);
    }

    @GetMapping("/find/{offset}/{pagesize}/{field}")
    @PreAuthorize("#username == authentication.principal.username OR hasRole('ROLE_ADMIN')")
    public Page<FoodLog> findByUserIdAndAdditionalCriteria(@PathVariable int offset,
                                                           @PathVariable int pagesize,
                                                           @PathVariable String field,
                                                           @RequestParam(required = false) String username,
                                                           @RequestParam(value = "fromDate", required = false)
                                                           @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                           Date fromDate,
                                                           @RequestParam(value = "toDate", required = false)
                                                           @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
                                                           @RequestParam(value = "threshold", required = false) Integer threshold
    ) {

        return foodLogService.findByMultipleCriteria(username,
                fromDate, toDate, threshold,
                offset, pagesize, field);

    }

    @GetMapping("/{username}/report")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<?> getUserReport(@PathVariable String username,
                                           @RequestParam(value = "fromDate", required = false)
                                           @DateTimeFormat(pattern = "yyyy-MM-dd")
                                           Date intakeDate
                                           ){
        return new ResponseEntity(foodLogService.findUserData(username,intakeDate),HttpStatus.OK);
    }

    @GetMapping("/report")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAdminReport(){
        return new ResponseEntity(foodLogService.findAdminData(),HttpStatus.OK);
    }
}
