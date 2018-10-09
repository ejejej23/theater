package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConn;

public class YemaeDAO {
	private Connection conn = DBConn.getConnection();

	public int insertYemae(YemaeDTO dto) {
		int result = 0;
		StringBuffer sb;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sb = new StringBuffer();
			sb.append("INSERT INTO yemae(yemaecode, adult, teenager, price, ticketingdate)");
			sb.append(" values(");
			sb.append("'" + 1111 + "'");// 오라클 할 때는 홑따옴표를 넘겨줘야함. 얘는 띄어쓰기하면 안 됨
			sb.append("," + dto.getAdult());
			sb.append("," + dto.getTeenager());
			sb.append("," + dto.getPrice());
			sb.append(", sysdate)");

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

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
		return result;

	}

	public List<PayDTO> listYemeahis(String memcode) {
		List<PayDTO> list = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 필요한거 : 예매내역에 보여줄 정보
		// 예매코드
		try {
			sb.append("SELECT Y.YEMAECODE myYemaeCode, TO_CHAR(TICKETINGDATE,'YYYYMMDD') TICKETINGDATE,  ");
			sb.append("TITLE mTitle, STARTTIME mstartTime,SCREENNAME,TO_CHAR(SCREENDATE,'YYYYMMDD') PLAYDATE, ");
			sb.append("NVL(MILEUSEJPRICE,0) USEM, ADULT+TEENAGER PERN,PRICE totalprice ");
			sb.append("FROM YEMAE Y ");
			sb.append(" LEFT OUTER JOIN YEMAESALE YS2 ON Y.YEMAECODE=YS2.YEMAECODE ");
			sb.append(" LEFT OUTER JOIN SCREENPLAY SP ON Y.SCREENPLAYCODE= SP.SCREENPLAYCODE ");
			sb.append(" LEFT OUTER JOIN SCREEN S ON SP.SCREENCODE=S.SCREENCODE ");
			sb.append(" LEFT OUTER JOIN MOVIE M ON M.MOVIECODE=SP.MOVIECODE ");
			sb.append("WHERE MEMCODE =?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memcode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PayDTO dto = new PayDTO();
				dto.setMyYemaeCode(rs.getString("myYemaeCode"));
				dto.setTicketingdate(rs.getString("ticketingdate"));
				dto.setmTitle(rs.getString("mTitle"));
				dto.setMstartTime(rs.getString("mstartTime"));
				dto.setScreenName(rs.getString("screenname"));
				dto.setPlaydate(rs.getString("playdate"));
				dto.setUseM(rs.getInt("useM"));
				dto.setPerN(rs.getInt("perN"));
				dto.setTotalPrice(rs.getInt("totalPrice"));

				list.add(dto);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
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

	
	public List<YemaeSeatDTO> listYemeaSeat(String screenplaycode) {
		List<YemaeSeatDTO> list = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sb.append("SELECT seatcode, seatinfo, yemaecode FROM yemaeseat WHERE screenplaycode = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, screenplaycode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				YemaeSeatDTO dto = new YemaeSeatDTO();
				dto.setSeatcode(rs.getString("seatcode"));
				dto.setSeatinfo(rs.getString("seatinfo"));
				dto.setYemaecode(rs.getString("seatinfo"));

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