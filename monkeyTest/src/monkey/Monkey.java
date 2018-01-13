package monkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author 张大爷
 * @time 2018年1月13日 上午11:06:07 Just a joke
 */

public class Monkey {
	public static void runMonkey() {

		String errors[] = { "CRASH", "Exception", "ANR", "java.lang.NullPointerException",
				"java.lang.SecurityException", "java.lang.ClassNotFoundException", "java.lang.UnsatisfiedLinkError",
				"java.lang.IllegalArgumentException", "java.lang.AbstractMethodError", "java.lang.IllegalAccessError",
				"java.lang.IndexOutOfBoundsException", "flipjava.io.IOException" };
		BufferedReader bReader = null;
		String line = null;
		int count = 0;
		try {
			FileWriter monkeyLogwriter = new FileWriter(getConfigPro().getProperty("monkeyLogFile"));
			FileWriter analyzeLogWriter = new FileWriter(getConfigPro().getProperty("AnalyzeLogFile"));
			try {
				Process process = Runtime.getRuntime().exec(getConfigPro().getProperty("command"));// 运行cmd命令
				bReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				while ((line = bReader.readLine()) != null) {	
					System.out.println(line);
					monkeyLogwriter.write(line);// 一行行日志写入文件
					monkeyLogwriter.write("\r\n");// 写入文件一行后换行
					if (line.contains("Monkey finished")) {
						System.out.println("Monkey test is finished！！");
						System.out.println("Monkey test is finished！！");
						System.out.println("Monkey test is finished！！");
						System.out.println("Three important things to say !!!");
					}
					for (int i = 0; i < errors.length; i++) {
						// 拿error数组对比进行筛选，如果有就写入analyze.log
						if (line.contains(errors[i])) {
							String content = "有" + errors[i] + "在第" + count + "行";
							analyzeLogWriter.write(content);
							analyzeLogWriter.write("\r\n");
						}
					}

					count++;

				}
			} catch (Exception e) {
				System.out.println("日志写入文件失败!!");
				e.printStackTrace();
			}
			analyzeLogWriter.close();
			monkeyLogwriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("日志写入文件失败!!");
			e.printStackTrace();
		}
	}

	// 获取配置文件信息config.properties
	public static Properties getConfigPro() {
		Properties pro = new Properties();
		File file = new File("C:\\Users\\Administrator\\eclipse-workspace\\RunMonkey\\monkeyTest\\config.properties");
		try {
			if (file.exists()) {
				pro.load(new FileInputStream(file));
			} else {
				pro.setProperty("AnalyzeLogFile",
						"C:\\Users\\Administrator\\eclipse-workspace\\RunMonkey\\monkeyTest\\log\\analyze.log");
				pro.setProperty("monkeyLogFile",
						"C:\\Users\\Administrator\\eclipse-workspace\\RunMonkey\\monkeyTest\\log\\monkey.log");
				pro.setProperty("command",
						"adb shell monkey -p com.geometry --ignore-crashes --ignore-timeouts --ignore-security-exceptions --ignore-native-crashes --monitor-native-crashes --throttle 100 -s 5 -v -v -v 500");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pro;
	}

}
