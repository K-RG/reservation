package egovframework.com.reservation.reservation.service;

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
	
	public RoomReservationVO saveReservation(RoomReservationVO roomReservationVO) {
		return roomReservationRepository.save(roomReservationVO);
	}
	
	public RoomReservationVO findOneReservation(String roomReservationUUID) {
		return roomReservationRepository.findOne(roomReservationUUID);
	}
	
}
