package operate;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Log {

	public static boolean Log_log(String �û���, String passId) throws Exception, IOException {
		
		Connection con=jdbc.Control.getcon();

		boolean result = false;
		String sql = "select * from log where �û���='" + �û��� + "' AND passId='" + passId + "'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		// ���ò���
		ResultSet resultSet = pstmt.executeQuery(sql);
		if (resultSet.next()) {
			result = true;
		}

		resultSet.close();
		pstmt.close();
		con.close();

		return result;
	}

	public static boolean register(String text, String text2) throws Exception {
		Connection con=jdbc.Control.getcon();

		//���ע��ʱ�û����Ƿ��ظ�
		boolean exist_user = false;
		String sql = "select * from log where �û���='" + text + "'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet resultSet = pstmt.executeQuery(sql);
		if (resultSet.next()) {
			exist_user = true;
		}
		if (exist_user == true) {
			resultSet.close();
			pstmt.close();
			con.close();
			return false;
		} else {
			sql = "insert into log(�û���,passId,���) VALUES (?,?,?)";
			pstmt = con.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, text);
			pstmt.setString(2, text2);
			pstmt.setString(3, "�û�");

			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			return true;
		}

	}

	public static boolean Log_Delete(String name) throws Exception {
		Connection con=jdbc.Control.getcon();

		int getid = -1;
		String sql1 = "select Id from log where �û���='" + name + "'";
		String sql2 = "DELETE FROM client WHERE Id=" + getid;
		String sql3 = "DELETE FROM log where �û���='" + name + "'";
		PreparedStatement pstmt;
		ResultSet resultSet;

		try {
			// ������������SQL���ϲ�Ϊһ������
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql1);
			resultSet = pstmt.executeQuery(sql1);
			while (resultSet.next()) {
				getid = resultSet.getInt("Id");
			}

			pstmt = con.prepareStatement(sql2);
			pstmt.executeUpdate();

			pstmt = con.prepareStatement(sql3);
			pstmt.executeUpdate();
			// �ύ����
			con.commit();
			resultSet.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}
		return true;
	}

	public static boolean Logmanage_log(String text, String text2) throws IOException, Exception {	
		Connection con=jdbc.Control.getcon();
		
		boolean result = false;
		String sql = "select * from log where �û���='" + text + "' AND passId='" + text2 + "' AND ���='����Ա'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet resultSet = pstmt.executeQuery(sql);
		if (resultSet.next()) {
			result = true;
		}

		resultSet.close();
		pstmt.close();
		con.close();

		return result;
	}
}
