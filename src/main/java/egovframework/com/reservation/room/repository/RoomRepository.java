package egovframework.com.reservation.room.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import egovframework.com.reservation.room.model.RoomVO;

public interface RoomRepository extends JpaRepository<RoomVO, String> {

	/*
	 * @Query(value =
	 * "SELECT r (SELECT c.companyName FROM CompanyVO c WHERE c.companyUUID = r.companyUUID)b FROM RoomVO r WHERE 1=1 AND r.roomFloor :roomFloor "
	 * )
	 */

	/*
	 * @Query(value = "SELECT r FROM RoomVO r JOIN fetch r.companyUUID")
	 * List<RoomVO> findByCompanyRoomList();
	 */
	/*
	 * @Query("SELECT distinct t FROM Team t join fetch t.members") public
	 * List<Team> findAllWithMemberUsingFetchJoin();
	 * 
	 * StringBuffer sb = new StringBuffer();
	 * sb.append("	SELECT r, (SELECT c.companyName ");
	 * sb.append("				FROM CompanyVO c  ");
	 * sb.append("				WHERE c.companyUUID = r.companyUUID ");
	 * sb.append("				)b"); sb.append("		FROM RoomVO r");
	 * sb.append("	WHERE 1=1"); sb.append(" 	AND r.roomFloor   =:roomFloor");
	 * if(companyUUID != null || "".equals(companyUUID)) {
	 * sb.append("		AND r.companyUUID =:companyUUID"); }
	 * 
	 * Query query = em.createQuery(sb.toString()) .setParameter("roomFloor",
	 * roomFloor); if(companyUUID != null || "".equals(companyUUID)) {
	 * query.setParameter("companyUUID", companyUUID);
	 * 
	 * }
	 * 
	 * 
	 * return query.getResultList(); }
	 */
}
