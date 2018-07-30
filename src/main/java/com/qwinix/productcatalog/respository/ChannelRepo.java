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

@Component
public class ChannelRepo implements IChannelRepo {
	
	@Autowired	
	JdbcTemplate jdbcTemplate;

	private final String SQL_FIND_CHANNEL = "select * from channel where id = ?";
	private final String SQL_DELETE_CHANNEL = "delete from channel where id = ?";
	private final String SQL_UPDATE_CHANNEL = "update channel set id = ?, name = ?, description = ?, priority  = ?, title = ?, active = ?, where id = ?";
	private final String SQL_GET_ALL = "select * from channel";
	private final String SQL_INSERT_CHANNEL = "insert into channel(id, name, description, priority, title, active) values(?,?,?,?,?,?)";


	@Autowired
	public ChannelRepo(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Channel getChannelById(int id) {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_CHANNEL, new Object[] { id }, Channel.class);
		} catch(EmptyResultDataAccessException edae) {
			throw new ValidationException("No rows found for channel Id " + id);
		} catch(Exception e) {
			throw e;
		}
	} 

	public List<Channel> getAllChannels() {
		return jdbcTemplate.query(SQL_GET_ALL, new ChannelMapper());
	}

	public boolean deleteChannel(int channelId) {
		return jdbcTemplate.update(SQL_DELETE_CHANNEL, channelId) > 0;
	}

	public  boolean updateChannel(Channel channel) {
		return jdbcTemplate.update(SQL_UPDATE_CHANNEL, channel.getId(), channel.getName(), channel.getDescription(), channel.getPriority(), channel.getTitle(), channel.getActive()) > 0;

	}

	public boolean createChannel(Channel channel) {
		//Write code to select from seq_table.
		Integer id = jdbcTemplate.queryForObject("select id from seq_table where entity=?", 
				new Object[] {"channel"}, Integer.class);
		id += 1;
		channel.setId(id);

		int rowsAffected = jdbcTemplate.update(SQL_INSERT_CHANNEL, channel.getId(), 
				channel.getName(), channel.getDescription(), channel.getPriority(), 
				channel.getTitle(), channel.getActive());
		if(rowsAffected == 0)
			return false;

		rowsAffected = jdbcTemplate.update("update seq_table set id=? where entity=?",
				channel.getId(), "channel");	
		return true;
	}

}