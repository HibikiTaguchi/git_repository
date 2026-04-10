package jp.co.sss.advanced.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.advanced.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
	List<Staff> findAllByDeleteFlagOrderByStaffIdAsc(Integer deleteFlag);
	Staff findByDeleteFlagAndAccessIdAndPassword(Integer deleteFlag, String accessId, String password);
}
