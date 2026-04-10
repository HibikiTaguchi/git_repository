package jp.co.sss.advanced.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.advanced.entity.Kind;

public interface KindRepository extends JpaRepository<Kind, Integer> {
	List<Kind> findAllByOrderByKindIdAsc();
}
