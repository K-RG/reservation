package egovframework.com.reservation.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.com.reservation.reservation.model.RoomReservationVO;

public interface RoomReservationRepository extends JpaRepository<RoomReservationVO, String>{

}
