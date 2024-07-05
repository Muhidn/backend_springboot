package com.Garbage.projectColection.services;


import com.Garbage.projectColection.model.ScheduleEntity;
import com.Garbage.projectColection.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    ScheduleEntity saveSchedule(ScheduleEntity schedule);
    List<ScheduleEntity> getAllSchedules();
    Optional<ScheduleEntity> getScheduleById(Long scheduleID);
    void deleteSchedule(Long scheduleID);
    boolean isExist(Long scheduleID);
}
