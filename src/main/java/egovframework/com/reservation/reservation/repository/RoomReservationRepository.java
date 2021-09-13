package egovframework.com.reservation.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import egovframework.com.reservation.reservation.model.RoomReservationVO;

public interface RoomReservationRepository extends JpaRepository<RoomReservationVO, String>{

	@Query(value = "SELECT v FROM RoomReservationVO v WHERE v.roomReservationUUID =:roomReservationUUID")
	RoomReservationVO findbyRoomReservationUUID(@Param(value = "roomReservationUUID") String roomReservationUUID);
	
}
