package egovframework.com.reservation.room.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import egovframework.com.reservation.common.Temporal;
import egovframework.com.reservation.common.utils.DateTimeUtil;

@Entity
@Table(name = "ROOM_PHOTO_FILE")
@DynamicUpdate
public class RoomPhotoFileVO extends Temporal{

	/*
	 * 방 사진파일 키
	 */
	@Id
	@GeneratedValue(generator = "ROOM_PHOTO_FILE_UUID")
	@GenericGenerator(name = "ROOM_PHOTO_FILE_UUID", strategy = "uuid")
	@Column(name = "ROOM_PHOTO_FILE_UUID", updatable = false, nullable = false)
	private String roomPhotoFileUUID;
	
	/*
	 * 방 키
	 */
	@Column(name = "ROOM_UUID")
	private String roomUUID;
	
	/*
	 * 사진 원본 이름
	 */
	@Column(name = "ROOM_PHOTO_FILE_NAME")
	private String roomPhotoFileName;
	
	/*
	 * 사진 저장 이름
	 */
	@Column(name = "ROOM_PHOTO_FILE_MASK")
	private String roomPhotoFileMask;
	
	/*
	 * 사진 파일 크기
	 */
	@Column(name = "ROOM_PHOTO_FILE_SIZE")
	private long roomPhotoFileSize;
	
	/*
	 * 사진 파일 확장자
	 */
	@Column(name = "ROOM_PHOTO_FILE_EXTENTION")
	private String roomPhotoFileExtention;
	
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROOM_UUID", insertable = false, updatable = false)
	private RoomVO roomVO;
	
	public RoomVO getRoomVO() {
		return roomVO;
	}

	public void setRoomVO(RoomVO roomVO) {
		this.roomVO = roomVO;
	}

	public String getRoomPhotoFileUUID() {
		return roomPhotoFileUUID;
	}

	public void setRoomPhotoFileUUID(String roomPhotoFileUUID) {
		this.roomPhotoFileUUID = roomPhotoFileUUID;
	}

	public String getRoomUUID() {
		return roomUUID;
	}

	public void setRoomUUID(String roomUUID) {
		this.roomUUID = roomUUID;
	}

	public String getRoomPhotoFileName() {
		return roomPhotoFileName;
	}

	public void setRoomPhotoFileName(String roomPhotoFileName) {
		this.roomPhotoFileName = roomPhotoFileName;
	}

	public String getRoomPhotoFileMask() {
		return roomPhotoFileMask;
	}

	public void setRoomPhotoFileMask(String roomPhotoFileMask) {
		this.roomPhotoFileMask = roomPhotoFileMask;
	}

	public long getRoomPhotoFileSize() {
		return roomPhotoFileSize;
	}

	public void setRoomPhotoFileSize(long roomPhotoFileSize) {
		this.roomPhotoFileSize = roomPhotoFileSize;
	}

	public String getRoomPhotoFileExtention() {
		return roomPhotoFileExtention;
	}

	public void setRoomPhotoFileExtention(String roomPhotoFileExtention) {
		this.roomPhotoFileExtention = roomPhotoFileExtention;
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