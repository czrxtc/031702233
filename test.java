package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
	public static void main(String[] args) throws IOException {
		List<String> input = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		// 用来循环输入
		for (;;) {
			System.out.print("请输入下一个数据（直接回车结束输入）：");
			String s = sc.nextLine();
			if (s.equals("")) {
				System.out.println("已结束输入！");
				break;
			}
			input.add(s);
		}
		System.out.println("输出：");
		for (int i = 0; i < input.size(); i++) {
			// 例子都是开头数字加感叹号,下面这行是获取第一个字符,也就是难度级别
			Object type = input.get(i).subSequence(0, 1);
			if (type.equals("1") || type.equals("2") || type.equals("3")) {
				// 例子里面都是名字后面加逗号的,逗号后面是地址+电话,下面这行就是把原本的字符串按逗号分割,主要判断的对象还是后面地址+电话部分
				String[] arr = input.get(i).split(",");
				if (arr.length == 2) {
					// 直接获取感叹号后面逗号前面的字符串作为"姓名"
					String name = arr[0].substring(arr[0].indexOf("!") + 1);

					Pattern pattern = Pattern.compile("((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}");
					Matcher matcher = pattern.matcher(arr[1]);
					String phone = "";
					while (matcher.find()) {
						// 获取后半段字符串里面的电话,顺便把这个字符串里面电话那部分给替换成"",相当于删掉
						phone = matcher.group();
						arr[1] = arr[1].replace(phone, "");
					}

					String[] addressArr = getAddress(type, arr[1]);
					User user = new User();
					user.setName(name);
					user.setPhone(phone);
					user.setAddress(addressArr);
					System.out.print(user.toString());
				}
			}
		}
		sc.close();
	}

	/**
	 * 获取地址和上面获取电话的原理差不多，不过解析地址的正则表达式不够完善，需要更改
	 * 
	 */
	private static String[] getAddress(Object type, String string) {
		String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
		Matcher m = Pattern.compile(regex).matcher(string);
		String province = null;
		String city = null;
		String county = null;
		String town = null;
		String village = null;
		while (m.find()) {
			province = m.group("province");
			city = m.group("city");
			county = m.group("county");
			town = m.group("town");
			village = m.group("village");
		}
		String[] address = new String[5];
		address[0] = province;
		address[1] = city;
		address[2] = county;
		address[3] = town;
		address[4] = village;
		return address;
	}

}
