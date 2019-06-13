package release;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.DBManager;


public class ReleaseDAO {
	private static final Logger LOG = LoggerFactory.getLogger(ReleaseDAO.class);
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public ArrayList<ReleaseDTO> getAllReleaseLists() {
		ArrayList<ReleaseDTO> rList = new ArrayList<ReleaseDTO>();
		conn = DBManager.getConnection();
		String sql = "select * from p_release;";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReleaseDTO rDto = new ReleaseDTO();
				rDto.setrId(rs.getInt(1));
				rDto.setrInvoiceId(rs.getString(2));
				rDto.setrTransportId(rs.getInt(3));
				rDto.setrDate(rs.getString(4));
				rDto.setrState(rs.getString(5));
				rList.add(rDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("getAllReleaseLists(): Error Code : {}", e.getErrorCode());
			return null;
		} finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rList; 
	}
	
	public void addReleaseList(ReleaseDTO rDto) {
		LOG.trace("addReleaseList(): " + rDto.toString());
		conn = DBManager.getConnection();
		String sql = "insert into p_release(rInvoiceId, rTransportId, rDate)"
				+ " values(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rDto.getrInvoiceId());
			pstmt.setInt(2, rDto.getrTransportId());
			pstmt.setString(3, rDto.getrDate());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("addChargeList() Error Code : {}", e.getErrorCode());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ReleaseDTO getOneReleaseList(int rInvoiceId) { 
		ReleaseDTO rDto = new ReleaseDTO();
		conn = DBManager.getConnection();
		String sql = "select * from p_release where rInvoiceId=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rInvoiceId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rDto.setrId(rs.getInt(1));
				rDto.setrInvoiceId(rs.getString(2));
				rDto.setrTransportId(rs.getInt(3));
				rDto.setrDate(rs.getString(4));
				rDto.setrState(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("getOneReleaseList(): Error Code : {}", e.getErrorCode());
			return null;
		} finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rDto;
	}
	
	public void updateReleaseState(String rState, String rInvoiceId) {
		LOG.debug("");
		PreparedStatement pStmt = null;
		conn = DBManager.getConnection();
		String sql = "update p_release set rState=? where rInvoiceId=?;";
		pStmt = null;
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, rState);
			pStmt.setString(2, rInvoiceId);
			pStmt.executeUpdate();
			LOG.trace(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("updateReleaseState() Error Code : {}", e.getErrorCode());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
    public int getCount() {
    	conn = DBManager.getConnection();
		String sql = "select count(*) from p_release;";
		PreparedStatement pStmt = null;
		int count = 0;
		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			LOG.trace(sql);
			while (rs.next()) {				
				count = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("getCount(): Error Code : {}", e.getErrorCode());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
    
	public ArrayList<ReleaseDTO> selectJoinAll(int page, int rTransportId) {
		conn = DBManager.getConnection();
		int offset = 0;
		String sql = null;
		if (page == 0) {	// page가 0이면 모든 데이터를 보냄
			sql = "select rId, rTransportId, rShoppingId, rInvoiceId, rName, rTel, rAddress, rProductName, rQuantity, rDate, rPrice, rState"
					+ " from p_release "
					+ "where rTransportId=?"
					+ " order by id desc;"; 
		} else {			// page가 0이 아니면 해당 페이지 데이터만 보냄
			sql = "select rId, rTransportId, rShoppingId, rInvoiceId, rName, rTel, rAddress, rProductName, rQuantity, rDate, rPrice, rState"
					+ " from p_release where rTransportId=?"
					+ " order by id desc limit ?, 10;";  
			offset = (page - 1) * 10;
		}
		ArrayList<ReleaseDTO> rList = new ArrayList<ReleaseDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			LOG.trace(sql);
			if (page == 0) {
				pstmt.setInt(1, rTransportId);
			} else if(page != 0) {
				pstmt.setInt(1, rTransportId);
				pstmt.setInt(2, offset);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {	
				ReleaseDTO rDto = new ReleaseDTO();
				rDto.setrId(rs.getInt(1));
				rDto.setrInvoiceId(rs.getString(2));
				rDto.setrTransportId(rs.getInt(3));
				rDto.setrDate(rs.getString(4));
				rDto.setrState(rs.getString(5));
				rList.add(rDto);
				LOG.trace(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("selectJoinAll(): Error Code : {}", e.getErrorCode());
			return null;
		} finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rList;
	}
	
	public ArrayList<ReleaseDTO> selectMonthlyToRelease(int page, String date) {
		conn = DBManager.getConnection();
		int offset = 0;
		String sql = null;
		if (page == 0) {	// page가 0이면 모든 데이터를 보냄
			sql = 	"select r.rId, r.rInvoiceId, r.rTransportId, a.aName, r.rDate, r.rState "
					+ "from p_release as r "
					+ "inner join admins as a "
					+ "on r.rTransportId=a.aId "
					+ "where date_format(r.rDate, '%Y-%m')=? "
					+ "order by r.rDate desc;";
			
		} else {			// page가 0이 아니면 해당 페이지 데이터만 보냄
			sql = "select r.rId, r.rInvoiceId, r.rTransportId, a.aName, r.rDate, r.rState "
					+ "from p_release as r "
					+ "inner join admins as a "
					+ "on r.rTransportId=a.aId "
					+ "where date_format(r.rDate, '%Y-%m')=? "
					+ "order by r.rDate desc limit ?, 10;";
			offset = (page - 1) * 10;
		}
		ArrayList<ReleaseDTO> rList = new ArrayList<ReleaseDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			LOG.trace(sql);
			if (page == 0) {
				pstmt.setString(1, date);
			} else if(page != 0) {
				pstmt.setString(1, date);
				pstmt.setInt(2, offset);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {	
				ReleaseDTO rDto = new ReleaseDTO();
				rDto.setrId(rs.getInt(1));
				rDto.setrInvoiceId(rs.getString(2));
				rDto.setrTransportId(rs.getInt(3));
				rDto.setrTransportName(rs.getString(4));
				rDto.setrDate(rs.getString(5));
				rDto.setrState(rs.getString(6));
				rList.add(rDto);
				LOG.trace(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.info("selectdailyToRelease(): Error Code : {}", e.getErrorCode());
			return null;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rList;
	}
}
