package com.boot.dasboot.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dasboot.model.Shipwreck;
import com.boot.dasboot.repository.ShipwreckRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ShipwreckController {

	@Autowired
	ShipwreckRepository shipwreckRepository;

	@RequestMapping(method = RequestMethod.GET, value = "shipwrecks")
	public List<Shipwreck> list() {
		return shipwreckRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, value = "shipwrecks")
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		return shipwreckRepository.saveAndFlush(shipwreck);
	}

	@RequestMapping(method = RequestMethod.GET, value = "shipwrecks/{id}")
	public Shipwreck get(@PathVariable Long id) {
		return shipwreckRepository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "shipwrecks/{id}")
	public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
		Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
		BeanUtils.copyProperties(shipwreck, existingShipwreck);
		return shipwreckRepository.saveAndFlush(existingShipwreck);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "shipwrecks/{id}")
	public Shipwreck delete(@PathVariable Long id) {
		Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
		shipwreckRepository.delete(existingShipwreck);
		return existingShipwreck;
	}

}
