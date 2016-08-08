import java.net.*;

import org.htmlparser.*;
import org.htmlparser.filters.*;
import org.htmlparser.util.*;

/** ʹ��Filter���˷�ʽ������ҳ���� */
public class TestFilter {

	public static void main(String[] args) {
		String url ="http://www.qq.com";
		testTagNameFilter(url);
		testHasChildFilter(url);
		testHasAttributeFilter(url);
		testAndFilter(url);
		testStringFilter(url);
		testLinkStringFilter(url);
		getRegexText(url);		
	}
	
	/** TagNameFilter���÷� */
	public static void testTagNameFilter(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
			parser.setEncoding("GB2312");
            //����TagNameFilterʵ��
            NodeFilter filter = new TagNameFilter ("DIV");
            //ɸѡ������DIV��ǩ�ڵ�
            NodeList nodes = parser.extractAllNodesThatMatch(filter); 
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("��ǰDIV:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** HasChildFilter���÷� */
	public static void testHasChildFilter(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
			parser.setEncoding("GB2312");
            //����TagNameFilterʵ��
            NodeFilter innerFilter = new TagNameFilter ("DIV");
            //����HasChildFilterʵ��
	        NodeFilter filter = new HasChildFilter(innerFilter);
	        //ɸѡ������ӵ���ӽڵ��DIV��ǩ�ڵ�
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("��ǰDIV:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** HasAttributeFilter���÷� */
	public static void testHasAttributeFilter(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
			parser.setEncoding("GB2312");
            //����HasAttributeFilterʵ��
	        NodeFilter filter = new HasAttributeFilter("id","TopNav" );
	        //ɸѡ������id����ֵ����"TopNav"�Ľڵ�
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("id����ֵ����TopNav�Ľڵ�:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	

	/** AndFilter���÷� */
	public static void testAndFilter(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
			parser.setEncoding("GB2312");
            //����HasAttributeFilterʵ��
	        NodeFilter filter1 = new HasAttributeFilter("id");
	        //����TagNameFilterʵ��
	        NodeFilter innerFilter = new TagNameFilter ("DIV");
	        //����HasChildFilterʵ��
	        NodeFilter filter2 = new HasChildFilter(innerFilter);
	        //����AndFilterʵ��
	        NodeFilter filter = new AndFilter(filter1, filter2);
	        //ɸѡ�����о���id������ӵ���ӽڵ������DIV�ڵ�
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("��ǰDIV:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** StringFilter���÷� */
	public static void testStringFilter(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
			parser.setEncoding("GB2312");
			//����StringFilterʵ��
	        NodeFilter filter = new StringFilter("��ˮ��");
	        //ɸѡ�����а���"��ˮ��"�ַ����������ı��ڵ�
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("����\"��ˮ��\"�ַ������ı��ڵ�:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** LinkStringFilter���÷� */
	public static void testLinkStringFilter(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
			parser.setEncoding("GB2312");
            //����LinkStringFilterʵ��
	        NodeFilter filter = new LinkStringFilter("http://tech.qq.com/");
	        //ɸѡ�����а���"http://tech.qq.com/"���ӵ�����LinkTag�ڵ�
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("����\"http://tech.qq.com/\"���ӵ�LinkTag�ڵ�:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** RegexFilter���÷� */
	public static void getRegexText(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
			parser.setEncoding("GB2312");
            //����RegexFilterʵ��
	        RegexFilter filter = new RegexFilter("http://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))+(\\?\\S*)?");
	        //ɸѡ�����������а���http��ַ���ı��ڵ�
	        NodeIterator it = parser.extractAllNodesThatMatch(filter).elements();
            Node node = null;
	        while (it.hasMoreNodes()){
	        	node = it.nextNode();
	        	System.out.println("�����а���http��ַ���ı��ڵ�:"+node.getText());
            }
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	
}
