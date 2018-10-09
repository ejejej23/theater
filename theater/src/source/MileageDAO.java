package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConn;

public class MileageDAO {
	private Connection conn = DBConn.getConnection();

	// 마일리지 내역 가져오기
	public List<MileageDTO> listMileage(String memcode) {
		List<MileageDTO> list = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sb.append("SELECT MILECODE, MEMCODE,to_char(HISTORYDATE,'YYYYMMDD') HISTORYDATE, NOWMILE, NEAYEONG FROM MILEAGE WHERE MEMCODE=?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memcode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MileageDTO dto = new MileageDTO();
				
				dto.setMilecode(rs.getString("milecode"));
				dto.setMemcode(rs.getString("memcode"));
				dto.setHistorydate(rs.getString("historydate"));
				dto.setNowmile(rs.getInt("nowmile"));
				dto.setNeayeong(rs.getString("neayeong"));
				
				list.add(dto);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {

			}

			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {

			}
		}
		return list;
	}
}
