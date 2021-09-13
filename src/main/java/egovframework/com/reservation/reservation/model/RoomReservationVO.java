package egovframework.com.reservation.reservation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import egovframework.com.reservation.common.Temporal;
import egovframework.com.reservation.common.utils.DateTimeUtil;
import egovframework.com.reservation.room.model.RoomVO;

@Entity
@Table(name = "ROOM_RESERVATION")
@DynamicUpdate
public class RoomReservationVO extends Temporal{

	/*
	 * 예약 정보키
	 */
	@Id
	@GeneratedValue(generator = "ROOM_RESERVATION_UUID")
	@GenericGenerator(name = "ROOM_RESERVATION_UUID", strategy = "uuid")
	@Column(name = "ROOM_RESERVATION_UUID", updatable = false, nullable = false)
	private String roomReservationUUID;
	
	/*
	 * 예약 인원
	 */
	@Column(name = "ROOM_RESERVATION_PEOPLE")
	private int roomReservationPeople;
	
	/*
	 * 예약자 이름
	 */
	@Column(name = "ROOM_RESERVATION_NAME")
	private String roomReservationName;
	
	/*
	 * 예약자 전화번호 앞자리
	 */
	@Column(name = "ROOM_RESERVATION_CELL_PHONE_FST")
	private int roomReservationCellPhoneFst;
	
	/*
	 * 예약자 전화번호 가운데 자리
	 */
	@Column(name = "ROOM_RESERVATION_CELL_PHONE_SND")
	private int roomReservationCellPhoneSnd;
	
	/*
	 * 예약자 전화번호 끝자리
	 */
	@Column(name = "ROOM_RESERVATION_CELL_PHONE_TRD")
	private int roomReservationCellPhoneTrd;
	
	/*
	 * 예약자 요청사함
	 */
	@Column(name = "ROOM_RESERVATION_REQUEST")
	@Lob
	private String roomReservationRequest;
	
	/*
	 *  결제 완료일
	 */
	@Column(name = "ROOM_RESERVATION_PAYMENT_COMPLETION_DAY")
	private Date roomReservationPaymentCompletionDay;
	
	/*
	 * 결제 타입
	 */
	@Column(name = "ROOM_RESERVATION_PAYMENT_TYPE")
	private int roomReservationPaymentType;
	
	/*
	 * 결제 상태
	 */
	@Column(name = "ROOM_RESERVATION_PAYMENT_STATUS")
	private int roomReservationPaymentStatus;
	
	/*
	 * 예약 시작일
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "START_DAY", nullable = false)
	private Date startDay;
	
	/*
	 * 예약 종료일
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "END_DAY", nullable = false)
	private Date endDay;

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
	@JoinColumn(name = "ROOM_UUID", updatable = false, nullable = false)
	private RoomVO roomVO;
	
	public RoomVO getRoomVO() {
		return roomVO;
	}

	public void setRoomVO(RoomVO roomVO) {
		this.roomVO = roomVO;
	}
	
	public String getRoomReservationUUID() {
		return roomReservationUUID;
	}

	public void setRoomReservationUUID(String roomReservationUUID) {
		this.roomReservationUUID = roomReservationUUID;
	}

	public int getRoomReservationPeople() {
		return roomReservationPeople;
	}

	public void setRoomReservationPeople(int roomReservationPeople) {
		this.roomReservationPeople = roomReservationPeople;
	}

	public String getRoomReservationName() {
		return roomReservationName;
	}

	public void setRoomReservationName(String roomReservationName) {
		this.roomReservationName = roomReservationName;
	}

	public int getRoomReservationCellPhoneFst() {
		return roomReservationCellPhoneFst;
	}

	public void setRoomReservationCellPhoneFst(int roomReservationCellPhoneFst) {
		this.roomReservationCellPhoneFst = roomReservationCellPhoneFst;
	}

	public int getRoomReservationCellPhoneSnd() {
		return roomReservationCellPhoneSnd;
	}

	public void setRoomReservationCellPhoneSnd(int roomReservationCellPhoneSnd) {
		this.roomReservationCellPhoneSnd = roomReservationCellPhoneSnd;
	}

	public int getRoomReservationCellPhoneTrd() {
		return roomReservationCellPhoneTrd;
	}

	public void setRoomReservationCellPhoneTrd(int roomReservationCellPhoneTrd) {
		this.roomReservationCellPhoneTrd = roomReservationCellPhoneTrd;
	}

	public String getStrRoomReservationCellPhone() {
		String strRoomReservationCellPhone = 
				roomReservationCellPhoneFst+"-"+roomReservationCellPhoneSnd+"-"+roomReservationCellPhoneTrd;
		return strRoomReservationCellPhone;
	}
	
	public String getRoomReservationRequest() {
		return roomReservationRequest;
	}

	public void setRoomReservationRequest(String roomReservationRequest) {
		this.roomReservationRequest = roomReservationRequest;
	}

	public Date getRoomReservationPaymentCompletionDay() {
		return roomReservationPaymentCompletionDay;
	}

	public void setRoomReservationPaymentCompletionDay(Date roomReservationPaymentCompletionDay) {
		this.roomReservationPaymentCompletionDay = roomReservationPaymentCompletionDay;
	}

	public int getRoomReservationPaymentType() {
		return roomReservationPaymentType;
	}

	public String getStrRoomReservationPaymentType() {
		String strRoomReservationPaymentType = "직접 결제";
		
		switch(roomReservationPaymentType) {
			case 1 : strRoomReservationPaymentType = "무통장 입금";
				break;
			case 2 : strRoomReservationPaymentType = "카드 결제";
				break;
		}
		
		return strRoomReservationPaymentType;
	}
	
	public void setRoomReservationPaymentType(int roomReservationPaymentType) {
		this.roomReservationPaymentType = roomReservationPaymentType;
	}

	public int getRoomReservationPaymentStatus() {
		return roomReservationPaymentStatus;
	}
	
	public String getStrRoomReservationPaymentStatus() {
		String strRoomReservationPaymentStatus = "결제대기중";
		
		switch(roomReservationPaymentStatus) {
			case 1 : strRoomReservationPaymentStatus = "결제완료";
				break;
			case 2 : strRoomReservationPaymentStatus = "완결";
				break;
		}
		
		return strRoomReservationPaymentStatus;
	}

	public void setRoomReservationPaymentStatus(int roomReservationPaymentStatus) {
		this.roomReservationPaymentStatus = roomReservationPaymentStatus;
	}

	public Date getStartDay() {
		return startDay;
	}
	
	public String getStrStartDay(String format) {
		return DateTimeUtil.getDateTimeByPattern(startDay, format);
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}
	
	public String getStrEndDay(String format) {
		return DateTimeUtil.getDateTimeByPattern(endDay, format);
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
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












