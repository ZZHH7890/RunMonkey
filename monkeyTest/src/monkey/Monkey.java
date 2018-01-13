package monkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * @author �Ŵ�ү
 * @time 2018��1��13�� ����11:06:07 Just a joke
 */

public class Monkey {
	public static void runMonkey() {

		String errors[] = { "CRASH", "Exception", "ANR", "java.lang.NullPointerException",
				"java.lang.SecurityException", "java.lang.ClassNotFoundException", "java.lang.UnsatisfiedLinkError",
				"java.lang.IllegalArgumentException", "java.lang.AbstractMethodError", "java.lang.IllegalAccessError",
				"java.lang.IndexOutOfBoundsException", "flipjava.io.IOException" };
		String command = "adb shell monkey -p com.geometry --ignore-crashes --ignore-timeouts --ignore-security-exceptions --ignore-native-crashes --monitor-native-crashes --throttle 200 -s 5 -v -v -v 1000";
		String monkeyLogFile = "C:\\Users\\Administrator\\monkeylog\\monkey.log";
		String AnalyzeLogFile = "C:\\Users\\Administrator\\monkeylog\\analyze.log";
		BufferedReader bReader = null;
		String line = null;
		int count = 0;
		try {
			FileWriter monkeyLogwriter = new FileWriter(monkeyLogFile);
			FileWriter analyzeLogWriter = new FileWriter(AnalyzeLogFile);
			try {
				Process process = Runtime.getRuntime().exec(command);// ����cmd����
				bReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				while ((line = bReader.readLine()) != null) {
					if (line.contains("Monkey finished")) {
						System.out.println("Monkey test is finished����");
					}
					System.out.println(line);
					monkeyLogwriter.write(line);// һ������־д���ļ�
					monkeyLogwriter.write("\r\n");// д���ļ�һ�к���
					for (int i = 0; i < errors.length; i++) {
						// ��error����ԱȽ���ɸѡ������о�д��analyze.log
						if (line.contains(errors[i])) {
							String content = "��" + errors[i] + "�ڵ�" + count + "��";
							analyzeLogWriter.write(content);
							analyzeLogWriter.write("\r\n");
						}
					}

					count++;

				}
			} catch (Exception e) {
				System.out.println("��־д���ļ�ʧ��!!");
				e.printStackTrace();
			}
			analyzeLogWriter.close();
			monkeyLogwriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("��־д���ļ�ʧ��!!");
			e.printStackTrace();
		}
	}

	// ��ȡ���������ļ���Ϣenv.properties
	public static Properties getEnvPro() {
		Properties pro = new Properties();
		File file = new File("");
		try {
			if (file.exists()) {
				pro.load(new FileInputStream(file));
			} else {
				pro.setProperty("host", "http://release.thy360.com");
				pro.setProperty("region", "813395");
				pro.setProperty("phone", "13714672776");
				pro.setProperty("code", "1234");
				pro.setProperty("introducerCode", "13714672770");
				pro.store(new FileOutputStream(file), "ENV CLASS");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pro;
	}

}
