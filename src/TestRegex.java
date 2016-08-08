import java.util.regex.*;

public class TestRegex {
	public static void main(String[] args) {
		//使用正则表达式验证IP地址
		String ip1 ="192.168.1.1";
		String ip2 ="192.168.a.1"; 
		boolean b = Pattern.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}",ip1);
		System.out.println(ip1+(b?"是有":"是无")+"效的IP地址!");
		 b = Pattern.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}",ip2);
		System.out.println(ip2+(b?"是有":"是无")+"效的IP地址!");
		//使用正则表达式验证http地址
		String http1 ="http://www.javares.cn?from=index&id=9";
		String http2 ="http://javares?from=index"; 
		b = Pattern.matches("http://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))+(\\?\\S*)?",http1);
		System.out.println(http1+(b?"是有":"是无")+"效的http地址!");
		b = Pattern.matches("http://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))+(\\?\\S*)?",http2);
		System.out.println(http2+(b?"是有":"是无")+"效的http地址!");	
		//使用正则表达式提取指定内容
		String html = "<body id='mybody'>姓名:<span>必填项</span></body>";
		//Pattern.MULTILINE表示启用多行模式
		//Pattern.DOTALL表示启用dotall模式,在dotall模式下，表达式.匹配任何字符，包括行终止符。
		Pattern p = Pattern.compile("<span(.*)>(.*)</span(.*)>",Pattern.MULTILINE|Pattern.DOTALL);
		Matcher m = p.matcher(html);
		System.out.print(html+ "提取span标签的内容:");
		String text="";
		if (m.find()){//调用find()方法才真正启动Matcher对象的匹配过程
			text=m.group();//调用group()取得所有匹配到的结果
		}
		System.out.print(text);		
	}
}
	