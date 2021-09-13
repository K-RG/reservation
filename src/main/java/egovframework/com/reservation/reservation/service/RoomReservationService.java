package egovframework.com.reservation.reservation.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.reservation.reservation.model.RoomReservationVO;
import egovframework.com.reservation.reservation.repository.RoomReservationRepository;

@Service
@Transactional
public class RoomReservationService {
	
	@Autowired
	private RoomReservationRepository roomReservationRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	public RoomReservationVO saveReservation(RoomReservationVO roomReservationVO) {
		return roomReservationRepository.save(roomReservationVO);
	}
	
	public RoomReservationVO findOneReservation(String roomReservationUUID) {
		return roomReservationRepository.findOne(roomReservationUUID);
	}
	
	public RoomReservationVO findbyRoomReservationUUID(String roomReservationUUID) {
		return roomReservationRepository.findbyRoomReservationUUID(roomReservationUUID);
	}
	/*
	public RoomReservationVO findbyRoomReservationUUID(String roomReservationUUID) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT v ");
		sb.append("		FROM  RoomReservationVO v");
		if(roomReservationUUID != null && !"".equals(roomReservationUUID)) {
		sb.append("	WHERE 1=1 ");
		sb.append(" 	AND roomReservationUUID =:roomReservationUUID");
		}
		Query query =  em.createNativeQuery(sb.toString());
		if(roomReservationUUID != null && !"".equals(roomReservationUUID)) {
			query.setParameter("roomReservationUUID", roomReservationUUID);
		}
		
		return query.getSingleResult();
		
	}
	*/
}
