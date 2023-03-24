package druid;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;
import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidDemo {

	public static void main(String[] args) throws Exception {
		// 1.����jar��
		// 2.���������ļ�
		// 3.���������ļ�
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/druid.properties"));

		// 4.��ȡ���ӳض���
		DataSource dataSource;
		dataSource = DruidDataSourceFactory.createDataSource(prop);
		// 5.��ȡ���Ӷ���
		@SuppressWarnings("unused")
		Connection con = dataSource.getConnection();

	}
}
