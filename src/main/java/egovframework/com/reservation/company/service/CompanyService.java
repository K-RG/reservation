package egovframework.com.reservation.company.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.reservation.company.model.CompanyVO;
import egovframework.com.reservation.company.repository.CompanyRepository;
import egovframework.com.reservation.room.model.RoomVO;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	public void saveCompany(CompanyVO companyVO) {
		companyRepository.save(companyVO);
	}
	
	public List<CompanyVO> findAllCompany() {
		return companyRepository.findAll();
	}
	
	public CompanyVO findOneCompany(String companyUUID) {
		return companyRepository.findOne(companyUUID);
	}
	
	public void deleteCompany(String companyUUID) {
		companyRepository.delete(companyUUID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyVO> findCompanyAndRoomList() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT c, 	(									");
		sb.append("				SELECT count(r.companyUUID)			");
		sb.append("					FROM RoomVO r					");
		sb.append("				WHERE r.companyUUID = c.companyUUID	");
		sb.append("				)								");
		sb.append("		FROM CompanyVO c							");
		
		Query query = em.createQuery(sb.toString());
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyVO> gegegegegegegegegege() {
		StringBuffer sb = new StringBuffer();
		sb.append("				SELECT r");
		sb.append("		FROM CompanyVO r");
		Query query = em.createQuery(sb.toString());
		
		return query.getResultList();
	}
	
	/*
	@SuppressWarnings("unchecked")
	public List<CompanyVO> findByCompany() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("	SELECT c");
		sb.append("		FROM CompanyVO c");
		
		Query query = em.createQuery(sb.toString());
		List<CompanyVO> companyList = query.getResultList();
		List<CompanyVO> companyList2 = new ArrayList<CompanyVO>();
		for(CompanyVO c : companyList) {
			CompanyVO vo = new CompanyVO();
			vo.setCompanyUUID(c.getCompanyUUID());
			vo.setCompanyName(c.getCompanyName());
			vo.setCeoName(c.getCeoName());
			vo.setCeoCellPhoneFst(c.getCeoCellPhoneFst());
			vo.setCeoCellPhoneSnd(c.getCeoCellPhoneSnd());
			vo.setCeoCellPhoneTrd(c.getCeoCellPhoneTrd());
			vo.setCompanyRegistrationNumberFst(c.getCompanyRegistrationNumberFst());
			vo.setCompanyRegistrationNumberSnd(c.getCompanyRegistrationNumberSnd());
			vo.setCompanyRegistrationNumberTrd(c.getCompanyRegistrationNumberTrd());
			vo.setCompanyPostCode(c.getCompanyPostCode());
			vo.setCompanyAddress(c.getCompanyAddress());
			vo.setCompanyDetailAddress(c.getCompanyDetailAddress());
			vo.setCompanyRoomFloor(c.getCompanyRoomFloor());
			vo.setCompanyCellPhoneFst(c.getCompanyCellPhoneFst());
			vo.setCompanyCellPhoneSnd(c.getCompanyCellPhoneSnd());
			vo.setCompanyCellPhoneTrd(c.getCompanyCellPhoneTrd());
			vo.setCompanyBankName(c.getCompanyBankName());
			vo.setCompanyBankAccountNumber(c.getCompanyBankAccountNumber());
			vo.setCompanyBankAccountHolder(c.getCompanyBankAccountHolder());
			vo.setRegistDt(c.getRegistDt());
			vo.setRegistId(c.getRegistId());
			vo.setModifyDt(c.getModifyDt());
			vo.setModifyId(c.getModifyId());
			
			
			companyList2.add(vo);
		}
		
		return query.getResultList();
	}
	*/
}