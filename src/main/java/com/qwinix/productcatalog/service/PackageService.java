package com.qwinix.productcatalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qwinix.productcatalog.model.PackageBean;
import com.qwinix.productcatalog.respository.PackageRepo;


@Service
public class PackageService {
	@Autowired
	PackageRepo packageRepository;

	public PackageBean createPackage(PackageBean apackage) {
		packageRepository.createPackage(apackage);
		return apackage;
	}

	public java.util.List<PackageBean> getAllPackageBean() {
		List<PackageBean> packages = new ArrayList<>();
		packages = packageRepository.getAllPackages();
		return packages;
	}

	public PackageBean findById(int packageId) {
		return packageRepository.getPackageById(packageId);
	}

	public void updatePackage(PackageBean packageUpdate) {
		packageRepository.updatePackage(packageUpdate);

	}

	public void deleteById(int deletePackageId) {
		packageRepository.deletePackage(deletePackageId);

	}
}
