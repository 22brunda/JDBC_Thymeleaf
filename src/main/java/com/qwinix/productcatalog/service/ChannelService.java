package com.qwinix.productcatalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qwinix.productcatalog.model.Channel;
import com.qwinix.productcatalog.model.UserSignUp;
import com.qwinix.productcatalog.respository.ChannelRepo;
import com.qwinix.productcatalog.respository.IChannelRepo;
import com.qwinix.productcatalog.util.Validator;

@Service
public class ChannelService {
	@Autowired
	ChannelRepo channelRepository;

	public java.util.List<Channel> getAllChannel() {
		List<Channel> channels = new ArrayList<>();
		//channelRepository.findAll().forEach(channels::add);
		channels = channelRepository.getAllChannels();
		return channels;
	}

	public Channel findById(int channelId) {
		return channelRepository.getChannelById(channelId);
	}

	public Channel createChannel(Channel achannel) {
		if(Validator.validateText(String.valueOf(achannel.getName()), 2, 20))
			if(Validator.validateText(String.valueOf(achannel.getDescription()), 10, 255))		
				if(Validator.validateLength(String.valueOf(achannel.getPriority()), 4))
					if(Validator.validateText(String.valueOf(achannel.getTitle()), 5, 50))
					{
						channelRepository.createChannel(achannel);
						return achannel;
					}
		return null;
		// return addChannel;
	}

	public void channelUpdate(Channel channelUpdate) {
		channelRepository.updateChannel(channelUpdate);
	}

	public void deleteById(int deleteChannelId) {
		channelRepository.deleteChannel(deleteChannelId);
	}
}
