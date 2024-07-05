package com.Garbage.projectColection.services.Impl;

import com.Garbage.projectColection.Repository.ScheduleRepository;
import com.Garbage.projectColection.model.ScheduleEntity;
import com.Garbage.projectColection.services.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleEntity saveSchedule(ScheduleEntity schedule){
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleEntity> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<ScheduleEntity> getScheduleById(Long scheduleID){
        return scheduleRepository.findById(scheduleID);
    }

    @Override
    public void deleteSchedule(Long scheduleID){
        scheduleRepository.deleteById(scheduleID);
    }

    public boolean isExist(Long scheduleID){
        return scheduleRepository.existsById(scheduleID);
    }
}
