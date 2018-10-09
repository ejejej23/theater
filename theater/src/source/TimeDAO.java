package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConn;

public class TimeDAO {
	private Connection conn = DBConn.getConnection();

	// db에 있는 시간표 가져오기
	//쥬라기월드
	public List<TimeDTO> listTime1(String title,String screenDate) {
		List<TimeDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("SELECT sp.screenplayCode,m.title,s.screenName,s.seatNumber,TO_CHAR(SP.screenDate,'dd') screenDate,sp.startTime from screen s ");
			sb.append("LEFT OUTER JOIN screenPlay sp ON s.screenCode=sp.screenCode ");
			sb.append("LEFT OUTER JOIN movie m ON m.movieCode=sp.movieCode ");
			sb.append("WHERE m.title=? AND sp.screenDate=?");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1,title);
			pstmt.setString(2, screenDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TimeDTO dto = new TimeDTO();
				dto.setScreenplayCode(rs.getString("screenplayCode"));
				dto.setTitle(rs.getString("title"));
				dto.setScreenDate(rs.getString("screenDate"));
				dto.setScreenName(rs.getString("screenName"));
				dto.setSeatNumber(rs.getString("seatNumber"));
				dto.setStartTime(rs.getString("startTime"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return list;

	}
	
	public int leftSeat(String screenplayCode) {
		int left=20;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql="select (20-count(*)) left from yemaeseat where SCREENPLAYCODE=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, screenplayCode);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				left=rs.getInt("left");
			}
			//System.out.println(screenplayCode+" : "+ left+" test");
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		
		return left;
	}
	
}
