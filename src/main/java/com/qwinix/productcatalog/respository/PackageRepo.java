package com.qwinix.productcatalog.respository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.qwinix.productcatalog.ValidationException;
import com.qwinix.productcatalog.model.Channel;
import com.qwinix.productcatalog.model.ChannelMapper;
import com.qwinix.productcatalog.model.PackageBean;
import com.qwinix.productcatalog.model.PackageMapper;

@Component
public class PackageRepo implements IPackageRepo{
	@Autowired	
	JdbcTemplate jdbcTemplate;

	private final String SQL_FIND_PACKAGE = "select * from package where id = ?";
	//	private final String SQL_DELETE_PACKAGE = "delete from package where id = ?";
	private final String SQL_UPDATE_PACKAGE = "update package set name = ?, description = ?, amount = ?, priority = ?, enabled = ?  where id = ?";
	private final String SQL_GET_ALL = "select * from package";
	private final String SQL_INSERT_PACKAGE = "insert into package(id, name, description, amount, priority, enabled) values(?,?,?,?,?,?)";
	private final String SQL_SELECT_PACKAGE_CHANNELS = "select id, name, description, priority, title, active "
													+ "from channel c "
													+ "inner join package_channels p "
													+ "on c.id = p.channels_id"
													+ " and p.package_bean_id=?"; 

	private final String SQL_DELETE_PACKAGE_CHANNELS = 	"delete from package_channels where package_bean_id = ?";

	@Autowired
	public PackageRepo(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//Get Channels for PackageId
	private List<Channel> getChannelsForPackageId(int packageId) {
		return jdbcTemplate.query(SQL_SELECT_PACKAGE_CHANNELS, 
				new Object[] { packageId }, new ChannelMapper());
	}

	//Get Package by ID
	public PackageBean getPackageById(int id) {
		try {
			List<PackageBean> packages = jdbcTemplate.query(SQL_FIND_PACKAGE,new Object[] {id}, new PackageMapper());
			PackageBean p = packages.get(0);
			//Get channels from join query
			List<Channel> channels = getChannelsForPackageId(p.getPackageId());
			p.setChannels(channels);
			return p;

		} catch(EmptyResultDataAccessException edae) {
			throw new ValidationException("No rows found for package Id " + id);
		} catch(Exception e) {
			throw e;
		}
	}

	//Get all Packages
	public List<PackageBean> getAllPackages() {
		List<PackageBean> packages =  jdbcTemplate.query(SQL_GET_ALL, new PackageMapper());

		for(PackageBean p : packages) {
			List<Channel> channels = getChannelsForPackageId(p.getPackageId());
			p.setChannels(channels);
		}

		return packages;
	}

	//Delete Package
	public boolean deletePackage(int packageId) {
		return jdbcTemplate.update(SQL_DELETE_PACKAGE_CHANNELS, packageId) > 0;
	}

	//Update Package
	public boolean updatePackage(PackageBean packageUpdate) {
		int rowsAffected =	jdbcTemplate.update(SQL_UPDATE_PACKAGE, packageUpdate.getName(), packageUpdate.getDescription(), packageUpdate.getAmount(), packageUpdate.getPriority(), packageUpdate.getEnabled(), packageUpdate.getPackageId());
		if(rowsAffected == 0)
			return false;
		addChannels(packageUpdate.getChannels(), packageUpdate.getPackageId());
		return true;
	}

	//Create Package
	public boolean createPackage(PackageBean packageCreate) {
		//Write code to select from seq_table.
		Integer id = jdbcTemplate.queryForObject("select id from seq_table where entity=?", 
				new Object[] {"package"}, Integer.class);
		id += 1;
		packageCreate.setPackageId(id);

		int rowsAffected = jdbcTemplate.update(SQL_INSERT_PACKAGE, packageCreate.getPackageId(), 
				packageCreate.getName(), packageCreate.getDescription(), packageCreate.getPriority(), 
				packageCreate.getAmount(), packageCreate.getEnabled());
		if(rowsAffected == 0)
			return false;

		rowsAffected = jdbcTemplate.update("update seq_table set id=? where entity=?",
				packageCreate.getPackageId(), "package");
		if(rowsAffected == 0)
			return false;
		addChannels(packageCreate.getChannels(), packageCreate.getPackageId());
		return true;
	}

	//Get Channels into Package
	private final String SQL_INSERT_PACKAGE_CHANNELS = "insert into package_channels(package_bean_id, channels_id) VALUES(?,?)";
	private boolean addChannels(List<Channel> channels, int packageId) {
		int rowsAffected = 0;
		for(Channel channel : channels) {
			rowsAffected = jdbcTemplate.update(SQL_INSERT_PACKAGE_CHANNELS,
					packageId, channel.getId());
		}
		return true;
	}
}
