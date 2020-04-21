package com.hackerrank.stocktrade.service;

import java.util.List;
import java.util.Optional;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeService {

	public void deleteAll();

	public Optional<Trade> getTradeByTradeID(Long id);

	public Long addNewTrade(Trade trade);

	public List<Trade> getAllTrades();

	public List<Trade> getTradesByUserID(long userID);

	public List<Trade> getTradesByStockSymbol(String stockSymbol);

	public List<Trade> findAllTradesByUserIdAndStockSymbol(long userID, String stockSymbol);

}
