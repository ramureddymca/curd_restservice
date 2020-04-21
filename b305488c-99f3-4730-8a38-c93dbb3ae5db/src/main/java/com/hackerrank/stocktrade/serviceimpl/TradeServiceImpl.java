package com.hackerrank.stocktrade.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepository TradeRepository;

	@Override
	public void deleteAll() {
		TradeRepository.deleteAll();
	}

	@Override
	public Long addNewTrade(Trade Trade) {
		Trade newTrade = TradeRepository.save(Trade);
		System.out.println(newTrade.toString());
		return newTrade.getId();
	}

	@Override
	public List<Trade> getAllTrades() {

		return toList(TradeRepository.findAll());
	}



	private List<Trade> toList(Iterable<Trade> findAll) {
		return null;
	}

	@Override
	public Optional<Trade> getTradeByTradeID(Long tradeID) {
		return TradeRepository.findById(tradeID);
	}

	@Override
	public List<Trade> getTradesByUserID(long userID) {
		return TradeRepository.findByUserId(userID);
	}

	@Override
	public List<Trade> getTradesByStockSymbol(String symbol) {
		return TradeRepository.findBySymbol(symbol);
	}

	@Override
	public List<Trade> findAllTradesByUserIdAndStockSymbol(long userID, String stockSymbol) {
		return TradeRepository.findAllTradesByRepoIdAndActorId(userID, stockSymbol);
	}
}
