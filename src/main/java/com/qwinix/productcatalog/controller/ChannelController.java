package com.qwinix.productcatalog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.qwinix.productcatalog.model.Channel;
import com.qwinix.productcatalog.service.ChannelService;

@Controller
public class ChannelController {
	@Autowired
	ChannelService channelService;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/channel", method=RequestMethod.GET)
	public String userForm(Model model) {
		model.addAttribute("channel", new Channel());
		model.addAttribute("errors", "");
		return "channel";
	}

	@RequestMapping(value="/channel", method=RequestMethod.POST)
	public String newChannel(@ModelAttribute (name="channelNew") Channel channel, Model model) {

		List<Channel> channels = channelService.getAllChannel();	
		try { 
			channel = channelService.createChannel(channel);  
		} catch(Exception e) {
			model.addAttribute("errors","Error = " + e.getMessage());
			return "channel";
		}
		String userInfo = String.format("active = %b, description = %s, name = %s, priority = %d, title= %s",
				channel.getActive(), channel.getDescription(), channel.getName(), channel.getPriority(), channel.getTitle());
		log.info(userInfo);

		Map<String, Object> params = new HashMap<>();
		params.put("channel", channels);

		return "channellist";
	}

	//	@GetMapping("/channels")
	//	public List<Channel> getAllChannel() {
	//		return  channelService.getAllChannel();
	//	}
	//
	//	@GetMapping("/channel/{id}")
	//	public Channel getChannelById(@PathVariable(value = "id") int channelId) {
	//		return channelService.findById(channelId);
	//	}
	//
	//	@PostMapping("/channel")
	//	public void createChannel(@RequestBody Channel achannel) {
	//		channelService.createChannel(achannel);
	//	}
	//
	//	@PutMapping("/channel/{id}")
	//	public void updateChannel(@RequestBody Channel channelUpdate) {
	//		channelService.channelUpdate(channelUpdate);
	//	}
	//
	//	@DeleteMapping("/channel/{id}")
	//	public void deleteChannelById(@PathVariable(value = "id") int deleteChannelId) {
	//		channelService.deleteById(deleteChannelId);
	//	}
}


