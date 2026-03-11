package jp.co.sss.practice.p06.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.practice.p06.entity.FruitsSeason;

public interface FruitsSeasonRepository extends JpaRepository<FruitsSeason, Integer> {
	List<FruitsSeason> findAllByOrderBySeasonMonthAsc();
	
	List<FruitsSeason> findAllByOrderByFruitIdAsc();
	
	List<FruitsSeason> findBySeasonMonthOrderByFruitIdAsc(Integer seasonMonth);
}
