import java.util.regex.*;

public class TestRegex {
	public static void main(String[] args) {
		//ʹ��������ʽ��֤IP��ַ
		String ip1 ="192.168.1.1";
		String ip2 ="192.168.a.1"; 
		boolean b = Pattern.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}",ip1);
		System.out.println(ip1+(b?"����":"����")+"Ч��IP��ַ!");
		 b = Pattern.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}",ip2);
		System.out.println(ip2+(b?"����":"����")+"Ч��IP��ַ!");
		//ʹ��������ʽ��֤http��ַ
		String http1 ="http://www.javares.cn?from=index&id=9";
		String http2 ="http://javares?from=index"; 
		b = Pattern.matches("http://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))+(\\?\\S*)?",http1);
		System.out.println(http1+(b?"����":"����")+"Ч��http��ַ!");
		b = Pattern.matches("http://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))+(\\?\\S*)?",http2);
		System.out.println(http2+(b?"����":"����")+"Ч��http��ַ!");	
		//ʹ��������ʽ��ȡָ������
		String html = "<body id='mybody'>����:<span>������</span></body>";
		//Pattern.MULTILINE��ʾ���ö���ģʽ
		//Pattern.DOTALL��ʾ����dotallģʽ,��dotallģʽ�£����ʽ.ƥ���κ��ַ�����������ֹ����
		Pattern p = Pattern.compile("<span(.*)>(.*)</span(.*)>",Pattern.MULTILINE|Pattern.DOTALL);
		Matcher m = p.matcher(html);
		System.out.print(html+ "��ȡspan��ǩ������:");
		String text="";
		if (m.find()){//����find()��������������Matcher�����ƥ�����
			text=m.group();//����group()ȡ������ƥ�䵽�Ľ��
		}
		System.out.print(text);		
	}
}
	