package com.example.ScheduleMate.controller;

import com.example.ScheduleMate.dto.BusinessPackageResponse;
import com.example.ScheduleMate.dto.PackageDto;
import com.example.ScheduleMate.dto.ServiceDto;
import com.example.ScheduleMate.endpoints.APIResponse;
import com.example.ScheduleMate.entity.Packages;
import com.example.ScheduleMate.service.PackageService;
import com.example.ScheduleMate.utils.ResponseCode;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PackageController {
    private final PackageService packageService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse<?>> createEvent(@RequestBody PackageDto packages) {
        packageService.createPackage(packages);

        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, packages));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<APIResponse< List<PackageDto> >> getPackages(@PathVariable Long id) {

        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, packageService.getBusinessPackages(id)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<APIResponse<List<PackageDto>>> getAllPackages() {
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, packageService.getAllPackages()));
    }

    @GetMapping("getpackages/{id}")
    public ResponseEntity<APIResponse<Page<PackageDto>>> getServicesByBusiness
            (@PathVariable Long id,
             @RequestParam(value = "page", defaultValue = "0") int page,
             @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, packageService.getPackageListByBusinessId(id,pageable)));
    }
}
