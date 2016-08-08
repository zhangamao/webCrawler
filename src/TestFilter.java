import java.net.*;

import org.htmlparser.*;
import org.htmlparser.filters.*;
import org.htmlparser.util.*;

/** 使用Filter过滤方式分析网页内容 */
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
	
	/** TagNameFilter类用法 */
	public static void testTagNameFilter(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //设置Parser对象的字符编码,一般与网页的字符编码保持一致
			parser.setEncoding("GB2312");
            //创建TagNameFilter实例
            NodeFilter filter = new TagNameFilter ("DIV");
            //筛选出所有DIV标签节点
            NodeList nodes = parser.extractAllNodesThatMatch(filter); 
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("当前DIV:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** HasChildFilter类用法 */
	public static void testHasChildFilter(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //设置Parser对象的字符编码,一般与网页的字符编码保持一致
			parser.setEncoding("GB2312");
            //创建TagNameFilter实例
            NodeFilter innerFilter = new TagNameFilter ("DIV");
            //创建HasChildFilter实例
	        NodeFilter filter = new HasChildFilter(innerFilter);
	        //筛选出所有拥有子节点的DIV标签节点
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("当前DIV:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** HasAttributeFilter类用法 */
	public static void testHasAttributeFilter(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //设置Parser对象的字符编码,一般与网页的字符编码保持一致
			parser.setEncoding("GB2312");
            //创建HasAttributeFilter实例
	        NodeFilter filter = new HasAttributeFilter("id","TopNav" );
	        //筛选出所有id属性值等于"TopNav"的节点
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("id属性值等于TopNav的节点:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	

	/** AndFilter类用法 */
	public static void testAndFilter(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //设置Parser对象的字符编码,一般与网页的字符编码保持一致
			parser.setEncoding("GB2312");
            //创建HasAttributeFilter实例
	        NodeFilter filter1 = new HasAttributeFilter("id");
	        //创建TagNameFilter实例
	        NodeFilter innerFilter = new TagNameFilter ("DIV");
	        //创建HasChildFilter实例
	        NodeFilter filter2 = new HasChildFilter(innerFilter);
	        //创建AndFilter实例
	        NodeFilter filter = new AndFilter(filter1, filter2);
	        //筛选出所有具有id属性且拥有子节点的所有DIV节点
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("当前DIV:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** StringFilter类用法 */
	public static void testStringFilter(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //设置Parser对象的字符编码,一般与网页的字符编码保持一致
			parser.setEncoding("GB2312");
			//创建StringFilter实例
	        NodeFilter filter = new StringFilter("陈水扁");
	        //筛选出所有包含"陈水扁"字符串的所有文本节点
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("包含\"陈水扁\"字符串的文本节点:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** LinkStringFilter类用法 */
	public static void testLinkStringFilter(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //设置Parser对象的字符编码,一般与网页的字符编码保持一致
			parser.setEncoding("GB2312");
            //创建LinkStringFilter实例
	        NodeFilter filter = new LinkStringFilter("http://tech.qq.com/");
	        //筛选出所有包含"http://tech.qq.com/"链接的所有LinkTag节点
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                for (int i = 0; i < nodes.size(); i++) {
                    Node textnode = (Node) nodes.elementAt(i);                    
                    System.out.println("包含\"http://tech.qq.com/\"链接的LinkTag节点:"+textnode.getText());
                }
            }       
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** RegexFilter类用法 */
	public static void getRegexText(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
	        //设置Parser对象的字符编码,一般与网页的字符编码保持一致
			parser.setEncoding("GB2312");
            //创建RegexFilter实例
	        RegexFilter filter = new RegexFilter("http://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))+(\\?\\S*)?");
	        //筛选出所有内容中包含http网址的文本节点
	        NodeIterator it = parser.extractAllNodesThatMatch(filter).elements();
            Node node = null;
	        while (it.hasMoreNodes()){
	        	node = it.nextNode();
	        	System.out.println("内容中包含http网址的文本节点:"+node.getText());
            }
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	
}
