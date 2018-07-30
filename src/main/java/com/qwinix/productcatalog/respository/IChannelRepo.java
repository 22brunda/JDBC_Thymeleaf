package com.qwinix.productcatalog.respository;

import java.util.List;

import com.qwinix.productcatalog.model.Channel;

public interface IChannelRepo {
	Channel getChannelById(int id);

	List<Channel> getAllChannels();

	boolean deleteChannel(int channelId);

	boolean updateChannel(Channel channel);

	boolean createChannel(Channel channel);
}

