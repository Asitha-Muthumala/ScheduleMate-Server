package com.example.ScheduleMate.service;

import com.example.ScheduleMate.dto.BusinessPackageResponse;
import com.example.ScheduleMate.dto.PackageDto;
import com.example.ScheduleMate.entity.Packages;

import java.util.List;

public interface PackageService {
    void createPackage(PackageDto packages);
    List<Packages> getBusinessPackages(Long id);
}
