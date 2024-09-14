package com.itcorp.services.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itcorp.services.entity.ServiceEntity;
import com.itcorp.services.repository.ServiceRepository;

@Controller
public class ServiceController {

	private ServiceRepository repository = new ServiceRepository();

	@GetMapping("/")
	public String home(Model model) {

		model.addAttribute("ipaddress", getIPAddress());

		try {
			List<ServiceEntity> services = repository.listAll();
			model.addAttribute("services", services);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "services";		
	}
	
	public String getIPAddress() {

		try {
			String urlString = "http://checkip.amazonaws.com/";
			URL url = new URL(urlString);
			try (BufferedReader br = new BufferedReader(
				new InputStreamReader(url.openStream()))) {
			    return br.readLine();
			}			
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return "IP Address - Unknown";		
	}
	
}