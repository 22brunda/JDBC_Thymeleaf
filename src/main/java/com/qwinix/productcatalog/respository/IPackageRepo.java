package com.qwinix.productcatalog.respository;

import java.util.List;
import com.qwinix.productcatalog.model.PackageBean;

public interface IPackageRepo {
	PackageBean getPackageById(int id);

	List<PackageBean> getAllPackages();

	boolean deletePackage(int packageId);

	boolean updatePackage(PackageBean packageUpdate);

	boolean createPackage(PackageBean packageCreate);
}
