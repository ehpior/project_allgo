package com.jhk.allgo.stock.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jhk.allgo.stock.model.entity.Stocks;
import com.jhk.allgo.stock.model.entity.id.DateCodePK;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, DateCodePK>{

	@Query(value=
			"SELECT d.* "+
			"FROM score a "+ 
			   "INNER JOIN (SELECT a.code "+
			       "FROM cheg a "+
			       "WHERE a.DATE >= IFNULL((SELECT DATE "+
			               "FROM business "+
			               "WHERE DATE < ?1 "+
			               "ORDER BY DATE desc "+
			               "LIMIT 19, 1), 0) "+
			           "AND a.date < ?1 "+
			       "GROUP BY a.code "+
			       "HAVING AVG(a.turn_over) >= 1.5) b ON b.code = a.code "+
			   "INNER JOIN stocks c ON c.code = a.code and c.market IN ('0', '10') "+
			   "INNER JOIN cheg d on d.code = a.code and d.date = d.date and d.capitalization >= 2000 "+
			"WHERE a.date = (SELECT MAX(DATE) FROM score WHERE DATE < ?1) "+
			   "AND a.type = 'A' "+
			   "AND a.score >= 0.7 "+
			"ORDER BY a.score desc",
		nativeQuery = true)
	List<Stocks> allgoAlphaPortfolioGenerate(Date date);
	
	@Query(value=
			"SELECT c.* "+
		    "FROM score a "+
		        "INNER JOIN (SELECT CODE, MAX(score) AS score "+
		            "FROM score "+
		            "WHERE DATE >= IFNULL((SELECT DISTINCT(DATE) "+
		                    "FROM score "+
		                    "WHERE DATE < ?1 "+
		                    "ORDER BY DATE desc "+
		                    "LIMIT 19, 1), 0) "+
		                "AND DATE < ?1 "+
		                "AND TYPE = 'B' "+
		            "GROUP BY CODE) b ON b.code = a.code AND b.score = a.score "+
		        "INNER JOIN stocks c ON c.code = a.code and c.market IN ('0', '10') "+
		        "INNER JOIN cheg d on d.code = a.code and d.date = a.date and d.capitalization >= 10000 "+
		    "WHERE a.date = (SELECT MAX(DATE) FROM score where DATE < ?1) "+
		        "AND a.type = 'B' "+
		    "ORDER BY a.score desc",
	    nativeQuery = true)
	List<Stocks> allgoBetaPortfolioGenerate(Date date);
	
}
