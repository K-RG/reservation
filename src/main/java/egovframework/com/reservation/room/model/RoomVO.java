package egovframework.com.reservation.room.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import egovframework.com.reservation.common.Temporal;
import egovframework.com.reservation.common.utils.DateTimeUtil;
import egovframework.com.reservation.company.model.CompanyVO;
import egovframework.com.reservation.reservation.model.RoomReservationVO;

@Entity
@Table(name = "ROOM")
@DynamicUpdate
public class RoomVO extends Temporal{

	/*
	 * 방 키
	 */
	@Id
	@GeneratedValue(generator = "ROOM_UUID")
	@GenericGenerator(name = "ROOM_UUID", strategy = "uuid")
	@Column(name = "ROOM_UUID", updatable = false, nullable = false)
	private String roomUUID;
	
	/*
	 * 방 층수
	 */
	@Column(name = "ROOM_FLOOR")
	private int roomFloor;
	
	/*
	 * 방이름
	 */
	@Column(name = "ROOM_NAME")
	private String roomName;
	
	/*
	 * 방 번호
	 */
	@Column(name = "ROOM_NUMBER")
	private String roomNumber;
	
	/*
	 * 방 체크인 시간
	 */
	@Column(name = "ROOM_CHECK_IN_TIME")
	private Time roomCheckInTime;
	
	/*
	 * 방 체크아웃 시간
	 */
	@Column(name = "ROOM_CHECK_OUT_TIME")
	private Time roomCheckOutTime;
	
	/*
	 * 방 소개
	 */
	@Column(name = "ROOM_INTRODUCE")
	@Lob
	private String roomIntroduce;
	
	/*
	 * 방 기준인원
	 */
	@Column(name = "ROOM_STANDARD_PEOPLE")
	private int roomStandardPeople;
	
	/*
	 * 방 최대인원
	 */
	@Column(name = "ROOM_MAX_PEOPLE")
	private int roomMaxPeople;
	
	/*
	 * 방 최대인원보다 초과시 명당 부과 요금
	 */
	@Column(name = "ROOM_PEOPLE_EXCESS_PRICE")
	private int roomPeopleExcessPrice;
	
	/*
	 * 기본 요금
	 */
	@Column(name = "ROOM_PRICE_STANDARD")
	private int roomPriceStandard;
	
	/*
	 * 방 비수기 요금
	 */
	@Column(name = "ROOM_PRICE_LOW_SEASON")
	private int roomPriceLowSeason;
	
	/*
	 * 방 성수기 요금
	 */
	@Column(name = "ROOM_PRICE_PEAK_SEASON_")
	private int roomPricePeakSeason;
	
	/**
	 * 사용여부
	 * 0 : 미사용
	 * 1 : 사용
	 */
	@Column(name="USE_AT")
	private int useAt;
	
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

	@ManyToOne
	@JoinColumn(name = "COMPANY_UUID", insertable = false, updatable = false)
	private CompanyVO companyVO;

	public CompanyVO getCompanyVO() {
		return companyVO;
	}

	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
	
	public String getRoomUUID() {
		return roomUUID;
	}

	public void setRoomUUID(String roomUUID) {
		this.roomUUID = roomUUID;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public String getStrRoomFloor() {
		String strRoomFloor = "";
		
		switch(roomFloor) {
		case 1 : strRoomFloor = "1층";
			break;
		case 2 : strRoomFloor = "2층";
			break;
		case 3 : strRoomFloor = "3층";
			break;
		case 4 : strRoomFloor = "4층";
			break;
		}
		return strRoomFloor;
	}
	public void setRoomFloor(int roomFloor) {
		this.roomFloor = roomFloor;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public Time getRoomCheckInTime() {
		return roomCheckInTime;
	}

	public void setRoomCheckInTime(Time roomCheckInTime) {
		this.roomCheckInTime = roomCheckInTime;
	}

	public Time getRoomCheckOutTime() {
		return roomCheckOutTime;
	}

	public void setRoomCheckOutTime(Time roomCheckOutTime) {
		this.roomCheckOutTime = roomCheckOutTime;
	}

	public String getRoomIntroduce() {
		return roomIntroduce;
	}

	public void setRoomIntroduce(String roomIntroduce) {
		this.roomIntroduce = roomIntroduce;
	}

	public int getRoomStandardPeople() {
		return roomStandardPeople;
	}

	public void setRoomStandardPeople(int roomStandardPeople) {
		this.roomStandardPeople = roomStandardPeople;
	}

	public int getRoomMaxPeople() {
		return roomMaxPeople;
	}

	public void setRoomMaxPeople(int roomMaxPeople) {
		this.roomMaxPeople = roomMaxPeople;
	}

	public int getRoomPeopleExcessPrice() {
		return roomPeopleExcessPrice;
	}

	public void setRoomPeopleExcessPrice(int roomPeopleExcessPrice) {
		this.roomPeopleExcessPrice = roomPeopleExcessPrice;
	}

	public int getRoomPriceStandard() {
		return roomPriceStandard;
	}

	public void setRoomPriceStandard(int roomPriceStandard) {
		this.roomPriceStandard = roomPriceStandard;
	}

	public int getRoomPriceLowSeason() {
		return roomPriceLowSeason;
	}

	public void setRoomPriceLowSeason(int roomPriceLowSeason) {
		this.roomPriceLowSeason = roomPriceLowSeason;
	}

	public int getRoomPricePeakSeason() {
		return roomPricePeakSeason;
	}

	public void setRoomPricePeakSeason(int roomPricePeakSeason) {
		this.roomPricePeakSeason = roomPricePeakSeason;
	}

	public int getUseAt() {
		return useAt;
	}

	public String getStrUseAt() {
		String strUseAt = "미사용";
		if(useAt == 1) {
			strUseAt = "사용";
		}
		return strUseAt;
	}
	
	public void setUseAt(int useAt) {
		this.useAt = useAt;
	}

	public Date getRegistDt() {
		return registDt;
	}

	public String getRegistDtByPattern(String format) {
		return DateTimeUtil.getDateTimeByPattern(registDt, format);
	}
	
	public void setRegistDt(Date registDt) {
		this.registDt = registDt;
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
