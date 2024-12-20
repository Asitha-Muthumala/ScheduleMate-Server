package com.example.ScheduleMate.service;

import com.example.ScheduleMate.dto.ServiceDto;
import com.example.ScheduleMate.dto.ServiceListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import java.util.List;

public interface ServiceService {

    void createPackage(ServiceDto serviceDto);

    List<ServiceDto> getAllServices();

    ServiceDto getServiceById(Long id);

    void updateService(Long id, ServiceDto serviceDto);

    void deleteService(Long id);

    void createService(ServiceDto serviceDto);
    List<ServiceListDto> getServiceListView();

   ServiceDto getServiceById(Long id);

   List<ServiceDto> getServiceByBusinessId(Long id);

    Page<ServiceDto> getServiceListByBusinessId(Long id, Pageable pageable);

    List<ServiceDto>searchServicesByName(String name);

}
