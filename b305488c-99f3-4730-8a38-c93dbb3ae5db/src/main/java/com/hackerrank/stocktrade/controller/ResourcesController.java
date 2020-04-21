package com.hackerrank.stocktrade.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.service.TradeService;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
    
	@Autowired
	private TradeService tradeService;
	
	@RequestMapping(value = "/erase", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllEvents(HttpServletRequest request) {

		tradeService.deleteAll();
	}
}
