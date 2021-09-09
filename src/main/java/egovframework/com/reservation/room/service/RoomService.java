package egovframework.com.reservation.room.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.reservation.room.model.RoomVO;
import egovframework.com.reservation.room.repository.RoomRepository;

@Service
@Transactional
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	public RoomVO saveRoom(RoomVO roomVO) {
		return roomRepository.save(roomVO);
	}
	
	public RoomVO findOneRoom(String roomUUID) {
		return roomRepository.findOne(roomUUID);
	}
	
	public void deleteRoom(String roomUUID) {
		roomRepository.delete(roomUUID);
	}
	/*
	public List<RoomVO> findByCompanyRoomList(){
		return roomRepository.findByCompanyRoomList();
	}
	
	 * companyUUID = 사업자
	 * roomfloor = 층수 (gubun 값으로 받아옴)
	 */
	@SuppressWarnings("unchecked")
	public List<RoomVO> findRoomListByCompanyUUID(int roomFloor){
		//SELECT r.* from RoomVO r <if
		StringBuffer sb = new StringBuffer();
		sb.append("	SELECT r");
		sb.append("		FROM RoomVO r");
		sb.append("	WHERE 1=1");
		sb.append(" 	AND r.roomFloor   =:roomFloor");
		
		Query query = em.createQuery(sb.toString())
						.setParameter("roomFloor", roomFloor);
		
		return query.getResultList();
	}
	
	
}
// 검색 쿼리
//SELECT 
//r.ROOM_UUID, 
//r.COMPANY_UUID, 
//r.MODIFY_DT, 
//r.MODIFY_ID, 
//r.REGIST_DT, 
//r.REGIST_ID, 
//r.ROOM_CHECK_IN_TIME, 
//r.ROOM_CHECK_OUT_TIME, 
//r.ROOM_FLOOR, 
//ROOM_INTRODUCE, 
//r.ROOM_MAX_PEOPLE, 
//r.ROOM_NAME, 
//r.ROOM_NUMBER, 
//r.ROOM_PEOPLE_EXCESS_PRICE, 
//r.ROOM_PRICE_LOW_SEASON, 
//r.ROOM_PRICE_PEAK_SEASON_, 
//r.ROOM_PRICE_STANDARD, 
//r.ROOM_STANDARD_PEOPLE, 
//r.USE_AT
//FROM room r 
//WHERE 1=1 
//	AND	r.company_UUID = '4028808b7ba1c84f017ba1e9c0ef0001'
//    AND r.ROOM_NAME like concat('%','1','%');

//검색 조인 쿼리
//SELECT 
//	r.*,
//    c.*
//FROM room r inner join company c on r.company_UUID = c.company_UUID
//	WHERE 1=1 
//		AND	r.company_UUID = '4028808b7ba1c84f017ba1e9c0ef0001'
//        AND r.ROOM_NAME like concat('%','1','%');