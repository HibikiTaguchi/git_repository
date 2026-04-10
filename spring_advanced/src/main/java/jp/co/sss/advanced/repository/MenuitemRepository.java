package jp.co.sss.advanced.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.advanced.entity.Kind;
import jp.co.sss.advanced.entity.Menuitem;

public interface MenuitemRepository extends JpaRepository<Menuitem, Integer> {
	List<Menuitem> findAllByOrderByMenuitemIdAsc();
	List<Menuitem> findByKindOrderByMenuitemIdAsc(Kind kind);
	List<Menuitem> findByStatusOrderByMenuitemIdAsc(Integer status);
	List<Menuitem> findByMenuitemNameContainingAndStatusOrderByMenuitemIdAsc(String menuitemName, Integer status);
	List<Menuitem> findByKindAndStatusOrderByMenuitemIdAsc(Kind kind, Integer status);
	List<Menuitem> findByStatusOrderByPriceAscMenuitemIdAsc(Integer status);
	List<Menuitem> findByStatusOrderByPriceDescMenuitemIdAsc(Integer status);
	@Query("select AVG(m.price) from Menuitem m where m.kind = :kind")
	Integer findByKindIdAndCalcAvg(@Param("kind") Kind kind);
}