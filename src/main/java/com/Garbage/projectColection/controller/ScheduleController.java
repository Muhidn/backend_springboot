package com.Garbage.projectColection.controller;

import com.Garbage.projectColection.model.ScheduleEntity;
import com.Garbage.projectColection.model.dto.ScheduleDto;
import com.Garbage.projectColection.services.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;
    private ModelMapper modelMapper;

    public ScheduleController(ScheduleService scheduleService, ModelMapper modelMapper){
        this.scheduleService = scheduleService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        ScheduleEntity scheduleEntity = modelMapper.map(scheduleDto, ScheduleEntity.class);
        ScheduleEntity savedSchedule = scheduleService.saveSchedule(scheduleEntity);
        return new ResponseEntity<>(modelMapper.map(savedSchedule, ScheduleDto.class), HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        List<ScheduleEntity> scheduleEntity = scheduleService.getAllSchedules();
        List<ScheduleDto> scheduleDto = scheduleEntity.stream()
                .map(schedule -> modelMapper.map(schedule, ScheduleDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(scheduleDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ScheduleDto> getScheduleByID(@PathVariable("id") Long scheduleID) {
        Optional<ScheduleEntity> scheduleEntity = scheduleService.getScheduleById(scheduleID);
        return scheduleEntity.map(entity -> new ResponseEntity<>(modelMapper.map(entity, ScheduleDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable("id") Long scheduleID, @RequestBody ScheduleDto scheduleDto) {
        if (!scheduleService.isExist(scheduleID)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            scheduleDto.setScheduleID(scheduleID); // Ensure ScheduleDto has a setId method
            ScheduleEntity scheduleEntity = modelMapper.map(scheduleDto, ScheduleEntity.class);
            ScheduleEntity savedSchedule = scheduleService.saveSchedule(scheduleEntity);
            return new ResponseEntity<>(modelMapper.map(savedSchedule, ScheduleDto.class), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteSchedule(@PathVariable("id") Long scheduleID) {
        scheduleService.deleteSchedule(scheduleID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    }
