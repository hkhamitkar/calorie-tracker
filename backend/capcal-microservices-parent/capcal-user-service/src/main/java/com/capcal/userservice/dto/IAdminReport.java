package com.capcal.userservice.dto;

public interface IAdminReport {
     Long getCurrentweek();
     Long getPreviousweek();
     Double getAverageinaweek();

     String getUsername();
}
