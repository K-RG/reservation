package egovframework.com.reservation.company.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import egovframework.com.reservation.common.Temporal;
import egovframework.com.reservation.common.utils.DateTimeUtil;
import egovframework.com.reservation.room.model.RoomVO;

@Entity
@Table(name = "COMPANY")
@DynamicUpdate
public class CompanyVO extends Temporal{

	/*
	 *  사업장 키
	 */
	@Id
	@GeneratedValue(generator = "COMPANY_UUID")
	@GenericGenerator(name = "COMPANY_UUID", strategy = "uuid")
	@Column(name = "COMPANY_UUID", updatable = false, nullable = false)
	private String companyUUID;
	
	/*
	 *  상호명
	 */
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	/*
	 *  대표자명
	 */
	@Column(name = "CEO_NAME")
	private String ceoName;
	
	/*
	 *  대표자 연락처 앞자리
	 */
	@Column(name = "CEO_CELL_PHONE_FST")
	private int ceoCellPhoneFst;
	
	/*
	 *  대표자 연락처  가운데 자리
	 */
	@Column(name = "CEO_CELL_PHONE_SND")
	private int ceoCellPhoneSnd;
	
	/*
	 *  대표자 연락처 끝자리
	 */
	@Column(name = "CEO_CELL_PHONE_TRD")
	private int ceoCellPhoneTrd;
	
	/*
	 *  사업자 등록번호 앞자리
	 */
	@Column(name = "COMPANY_REGISTRATION_NUMBER_FST")
	private int companyRegistrationNumberFst;
	
	/*
	 *  사업자 등록번호 가운데자리
	 */
	@Column(name = "COMPANY_REGISTRATION_NUMBER_SND")
	private int companyRegistrationNumberSnd;
	
	/*
	 *  사업자 등록번호 끝자리
	 */
	@Column(name = "COMPANY_REGISTRATION_NUMBER_TRD")
	private int companyRegistrationNumberTrd;
	
	/*
	 * 사업장 우편번호
	 */
	
	@Column(name = "COMPANY_POSTCODE")
	private String companyPostCode;
	
	/*
	 * 사업장 주소 
	 */
	@Column(name = "COMPANY_ADDRESS")
	private String companyAddress;
	
	/*
	 * 사업장 상세주소
	 */
	@Column(name = "COMPANY_DETAIL_ADDRESS")
	private String companyDetailAddress;
	
	/*
	 * 사업장 객실 층수
	 */
	@Column(name = "COMPANY_ROOM_FLOOR")
	private String companyRoomFloor;
	
	/*
	 * 사업장 전화번호 앞자리
	 */
	@Column(name = "COMPANY_CELL_PHONE_FST")
	private int companyCellPhoneFst;
	
	/*
	 * 사업장 전화번호 가운데자리
	 */
	@Column(name = "COMPANY_CELL_PHONE_SND")
	private int companyCellPhoneSnd;
	
	/*
	 * 사업장 전화번호 끝자리
	 */
	@Column(name = "COMPANY_CELL_PHONE_TRD")
	private int companyCellPhoneTrd;
	
	/*
	 * 사업장 은행이름
	 */
	@Column(name = "COMPANY_BANK_NAME")
	private int companyBankName;
	
	/*
	 * 사업장 계좌번호
	 */
	@Column(name = "COMPANY_BANK_ACCOUNT_NUMBER")
	private String companyBankAccountNumber;
	
	/*
	 * 사업장 계좌 예금주
	 */
	@Column(name = "COMPANY_BANK_ACCOUNT_HOLDER")
	private String companyBankAccountHolder;
	
	/**
	 * 등록일
	 */
	@Column(name="REGIST_DT", updatable = false, nullable = false)
	private Date registDt; 
	
	/**
	 * 등록ID
	 */
	@Column(name="REGIST_ID", updatable = false, nullable = false)
	private String registId;
	
	/**
	 * 수정일
	 */
	@Column(name="MODIFY_DT")
	private Date modifyDt; 
	
	/**
	 * 수정ID
	 */
	@Column(name="MODIFY_ID")
	private String modifyId;

	@OneToMany(mappedBy = "companyVO", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<RoomVO> room = new ArrayList<RoomVO>();
	
	public Collection<RoomVO> getRoom() {
		return room;
	}

	public void setRoom(Collection<RoomVO> room) {
		this.room = room;
	}

	public void addToRoom(RoomVO roomVO) {
		roomVO.setCompanyVO(this);
		this.room.add(roomVO);
	}
	public String getCompanyUUID() {
		return companyUUID;
	}

	public void setCompanyUUID(String companyUUID) {
		this.companyUUID = companyUUID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	public int getCeoCellPhoneFst() {
		return ceoCellPhoneFst;
	}

	public void setCeoCellPhoneFst(int ceoCellPhoneFst) {
		this.ceoCellPhoneFst = ceoCellPhoneFst;
	}

	public int getCeoCellPhoneSnd() {
		return ceoCellPhoneSnd;
	}

	public void setCeoCellPhoneSnd(int ceoCellPhoneSnd) {
		this.ceoCellPhoneSnd = ceoCellPhoneSnd;
	}

	public int getCeoCellPhoneTrd() {
		return ceoCellPhoneTrd;
	}

	public void setCeoCellPhoneTrd(int ceoCellPhoneTrd) {
		this.ceoCellPhoneTrd = ceoCellPhoneTrd;
	}

	public String getStrCeoCellPhoneNumber() {
		String strCeoCellPhoneNumber = 
				ceoCellPhoneFst+"-"+ceoCellPhoneSnd+"-"+ceoCellPhoneTrd;
		return strCeoCellPhoneNumber;
	}
	
	public int getCompanyRegistrationNumberFst() {
		return companyRegistrationNumberFst;
	}

	public void setCompanyRegistrationNumberFst(int companyRegistrationNumberFst) {
		this.companyRegistrationNumberFst = companyRegistrationNumberFst;
	}

	public int getCompanyRegistrationNumberSnd() {
		return companyRegistrationNumberSnd;
	}

	public void setCompanyRegistrationNumberSnd(int companyRegistrationNumberSnd) {
		this.companyRegistrationNumberSnd = companyRegistrationNumberSnd;
	}

	public int getCompanyRegistrationNumberTrd() {
		return companyRegistrationNumberTrd;
	}

	public void setCompanyRegistrationNumberTrd(int companyRegistrationNumberTrd) {
		this.companyRegistrationNumberTrd = companyRegistrationNumberTrd;
	}

	public String getStrCompanyRegistrationNumber() {
		String strCompanyRegistrationNumber = 
				companyRegistrationNumberFst+"-"+companyRegistrationNumberSnd+"-"+companyRegistrationNumberTrd;
		return strCompanyRegistrationNumber;
	}
	
	public String getCompanyPostCode() {
		return companyPostCode;
	}

	public void setCompanyPostCode(String companyPostCode) {
		this.companyPostCode = companyPostCode;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyDetailAddress() {
		return companyDetailAddress;
	}

	public String getStrCompanyAddress() {
		String strCompanyAddress = 
				"("+companyPostCode+")"+companyAddress+companyDetailAddress;
		
		return strCompanyAddress;
	}
	
	public void setCompanyDetailAddress(String companyDetailAddress) {
		this.companyDetailAddress = companyDetailAddress;
	}

	public String getCompanyRoomFloor() {
		return companyRoomFloor;
	}

	public void setCompanyRoomFloor(String companyRoomFloor) {
		this.companyRoomFloor = companyRoomFloor;
	}

	public int getCompanyCellPhoneFst() {
		return companyCellPhoneFst;
	}

	public void setCompanyCellPhoneFst(int companyCellPhoneFst) {
		this.companyCellPhoneFst = companyCellPhoneFst;
	}

	public int getCompanyCellPhoneSnd() {
		return companyCellPhoneSnd;
	}

	public void setCompanyCellPhoneSnd(int companyCellPhoneSnd) {
		this.companyCellPhoneSnd = companyCellPhoneSnd;
	}

	public int getCompanyCellPhoneTrd() {
		return companyCellPhoneTrd;
	}

	public void setCompanyCellPhoneTrd(int companyCellPhoneTrd) {
		this.companyCellPhoneTrd = companyCellPhoneTrd;
	}

	public String getStrCompanyCellPhone() {
		String strCompanyCellPhone = 
				companyCellPhoneFst+"-"+companyCellPhoneSnd+"-"+companyCellPhoneTrd;
		return strCompanyCellPhone;
	}

	public int getCompanyBankName() {
		return companyBankName;
	}

	public void setCompanyBankName(int companyBankName) {
		this.companyBankName = companyBankName;
	}

	public String getCompanyBankAccountNumber() {
		return companyBankAccountNumber;
	}

	public void setCompanyBankAccountNumber(String companyBankAccountNumber) {
		this.companyBankAccountNumber = companyBankAccountNumber;
	}

	public String getCompanyBankAccountHolder() {
		return companyBankAccountHolder;
	}

	public void setCompanyBankAccountHolder(String companyBankAccountHolder) {
		this.companyBankAccountHolder = companyBankAccountHolder;
	}
	
	public String getStrCompanyBankName() {
		String strCompanyBankName = "";
		
		switch(companyBankName) {
		case 1 : strCompanyBankName = "한국은행";
			break;
		case 2 : strCompanyBankName = "KB국민은행";
			break;
		case 3 : strCompanyBankName = "신한은행";
			break;
		case 4 : strCompanyBankName = "우리은행";
			break;
		case 5 : strCompanyBankName = "하나은행";
			break;
		case 6 : strCompanyBankName = "카카오뱅크";
			break;
		case 7 : strCompanyBankName = "KDB산업은행";
			break;
		case 8 : strCompanyBankName = "IBK기업은행";
			break;
		case 9 : strCompanyBankName = "NH농협은행";
			break;
		case 10 : strCompanyBankName = "수협은행";
			break;
		case 11 : strCompanyBankName = "대구은행";
			break;
		case 12 : strCompanyBankName = "부산은행";
			break;
		case 13 : strCompanyBankName = "경남은행";
			break;
		case 14 : strCompanyBankName = "광주은행";
			break;
		case 15 : strCompanyBankName = "전북은행";
			break;
		case 16 : strCompanyBankName = "제주은행";
			break;
		}
		return strCompanyBankName;
	}
	
	public String getStrCompanyBank() {
		String strCompanyBank = 
				getStrCompanyBankName()+"-"+companyBankAccountNumber+"-"+companyBankAccountHolder;
			
		return strCompanyBank;
	}

	public Date getRegistDt() {
		return registDt;
	}

	public void setRegistDt(Date registDt) {
		this.registDt = registDt;
	}

	public String getRegistDtByPattern(String format) {
		return DateTimeUtil.getDateTimeByPattern(registDt, format);
	}
	
	public String getRegistId() {
		return registId;
	}

	public void setRegistId(String registId) {
		this.registId = registId;
	}

	public Date getModifyDt() {
		return modifyDt;
	}

	public String getModifyDtByPattern(String format) {
		return DateTimeUtil.getDateTimeByPattern(modifyDt, format);
	}
	
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	
}






















