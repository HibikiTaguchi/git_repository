package jp.co.sss.practice.p07.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.practice.p07.entity.Area;
import jp.co.sss.practice.p07.entity.FruitsArea;

public interface FruitsAreaRepository extends JpaRepository<FruitsArea,Integer> {
	List<FruitsArea> findByAreaOrderByFruitIdAsc(Area area);
}
