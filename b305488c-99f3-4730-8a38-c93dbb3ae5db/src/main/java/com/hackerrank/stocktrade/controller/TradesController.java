package com.hackerrank.stocktrade.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;

@RestController
//@RequestMapping(value = "/trades")
public class TradesController {

	@Autowired
	private TradeService TradeService;
	
	@RequestMapping(value = "/trades", method = RequestMethod.POST)
	public ResponseEntity<Long> addNewTrade(@RequestBody Trade Trade, HttpServletRequest request) {

		if(TradeService.getTradeByTradeID(Trade.getId()).isPresent()){

			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
		Long TradeId = TradeService.addNewTrade(Trade);

		return new ResponseEntity<Long>(TradeId, HttpStatus.CREATED);
	}

	/**
	 * Returning all the Trades: The service should be able to return the JSON array
	 * of all the Trades by the GET request at /Trades. The HTTP response code
	 * should be 200. The JSON array should be sorted in ascending order by Trade
	 * ID.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Trade>> getAllTrades(HttpServletRequest request) {
		List<Trade> Trades = null;
		try {
			Trades = TradeService.getAllTrades();
		} catch (Exception e) {

		}
		if (null != Trades) {
			return new ResponseEntity<List<Trade>>(Trades, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Trade>>(Trades, HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Returning the Trade records filtered by the actor ID: The service should be
	 * able to return the JSON array of all the Trades which are performed by the
	 * actor ID by the GET request at /Trades/actors/{actorID}. If the requested
	 * actor does not exist then HTTP response code should be 404, otherwise, the
	 * response code should be 200. The JSON array should be sorted in ascending
	 * order by Trade ID. Updating the avatar URL of the actor: The service should
	 * be able to update the avatar URL of the actor by the PUT request at /actors.
	 * The actor JSON is sent in the request body. If the actor with the id does not
	 * exist then the response code should be 404, or if there are other fields
	 * being updated for the actor then the HTTP response code should be 400,
	 * otherwise, the response code should be 200.
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/Trades/{TradeID}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Trade> getTradeByTradeId(@PathVariable("TradeID") long TradeID, HttpServletRequest request) {
		Optional<Trade> Trades = null;
		try {
			Trades = TradeService.getTradeByTradeID(TradeID);
		} catch (Exception e) {
		}
		if (Trades.isPresent()) {
			return new ResponseEntity<Trade>(Trades.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Trade>(HttpStatus.NOT_FOUND);		}

	}

	@RequestMapping(value = "//stocks/{stockSymbol}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Trade>> getTradesByStockSymbol(@PathVariable("stockSymbol") String stockSymbol,
			HttpServletRequest request) {

		List<Trade> Trades = TradeService.getTradesByStockSymbol(stockSymbol);
		if (!Trades.isEmpty()) {
			return new ResponseEntity<List<Trade>>(Trades, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Trade>>(Trades, HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/Trades/actors/{actorID}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Trade>> getTradesByUserID(@PathVariable("userID") long userID,
			HttpServletRequest request) {

		List<Trade> Trades = TradeService.getTradesByUserID(userID);
		if (!Trades.isEmpty()) {
			return new ResponseEntity<List<Trade>>(Trades, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Trade>>(HttpStatus.NOT_FOUND);
		}
	}

	
	
	@RequestMapping(value = "/users/{userID}/stocks/{stockSymbol}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Trade>> findAllTradesByUserIdAndStockSymbol(@PathVariable long userID, @PathVariable String stockSymbol) {
		List<Trade> Trades = TradeService.findAllTradesByUserIdAndStockSymbol(userID,stockSymbol);
		if(!Trades.isEmpty()) { 
			return new ResponseEntity<List<Trade>>(Trades, HttpStatus.OK);
		} else { 
			return new ResponseEntity<List<Trade>>(HttpStatus.NOT_FOUND);
		}
	}
}
