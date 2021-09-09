package egovframework.com.reservation.company.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.com.reservation.company.model.CompanyVO;

@Transactional
public interface CompanyRepository extends JpaRepository<CompanyVO, String>{
}
