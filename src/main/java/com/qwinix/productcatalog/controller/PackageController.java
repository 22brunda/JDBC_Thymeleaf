package com.qwinix.productcatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qwinix.productcatalog.model.PackageBean;
import com.qwinix.productcatalog.service.PackageService;

@RestController
@EnableAutoConfiguration
public class PackageController  {
	@Autowired
	PackageService packageService;

	@GetMapping("/packages")
	public List<PackageBean> getAllPackageBean() {
		return  packageService.getAllPackageBean();
	}

	@GetMapping("/package/{id}")
	public PackageBean getPackageById(@PathVariable(value = "id") int packageId) {
		return packageService.findById(packageId);
	}

	@PostMapping("/package")
	public void createPackage(@RequestBody PackageBean apackage) {
		packageService.createPackage(apackage);

	}

	@PutMapping("/package/{id}")
	public void updatePackage(@RequestBody PackageBean packageUpdate) {
		packageService.updatePackage(packageUpdate);
	}
	
	@DeleteMapping("/package/{id}")
	public void deletePackage(@PathVariable(value = "id") int deletePackageId) {
		packageService.deleteById(deletePackageId);
	}
}
