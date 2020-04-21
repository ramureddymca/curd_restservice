package com.hackerrank.stocktrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.stocktrade.model.Trade;
@Repository
public interface TradeRepository extends CrudRepository<Trade, Long>{

	public Trade save(Trade trade);
	
	@Query("SELECT t FROM Trade t  WHERE t.user.id =:userID AND t.symbol =:stockSymbol")
	List<Trade> findAllTradesByRepoIdAndActorId(@Param("userID") long userID, @Param("stockSymbol") String stockSymbol);

	@Transactional
	@Modifying
	@Query("DELETE FROM Trade")
	void deleteAll();

	public List<Trade> findByUserId(long userID);

	public List<Trade> findBySymbol(String symbol);
}
