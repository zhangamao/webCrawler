import java.net.*;
import org.htmlparser.*;
import org.htmlparser.tags.*;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.visitors.*;

/** 使用Visitor方式分析网页内容 */
public class TestNodeVisitor {

	public static void main(String[] args) {
		testMyNodeVisitor("<html><head><title>这是标题</title></head><!-- 正文开始 --><body><div>这是测试内容</div></body></html>");
		testHtmlPage("http://www.qq.com");
		testLinkFindingVisitor("http://www.qq.com");
		testObjectFindingVisitor("http://www.qq.com");
		testStringFindingVisitor("http://www.qq.com");
		testTagFindingVisitor("http://www.qq.com");
		testTextExtractingVisitor("http://www.qq.com");
	}
	
	/** 自定义NodeVisitor子类的用法举例 */
	public static void testMyNodeVisitor(String htmlcode){
		try{
			//通过指定HTML内容创建Parser对象
			Parser parser = new Parser(htmlcode);
			//设置Parser对象的字符编码,一般与网页的字符编码保持一致
	        parser.setEncoding("GB2312");
	        //创建一个MyNodeVisitor实例
	        MyNodeVisitor visitor = new MyNodeVisitor(); 
	        //使用MyNodeVisitor实例访问parser中的内容
	        parser.visitAllNodesWith(visitor);
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	
	
	/** HtmlPage类的用法举例 */
	public static void testHtmlPage(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//设置Parser对象的字符编码,一般与网页的字符编码保持一致
	        parser.setEncoding("GB2312");
	        //创建HtmlPage对象
	        HtmlPage html = new HtmlPage(parser);
	        //通过HtmlPage对象遍历parser中的所有节点
	        parser.visitAllNodesWith(html);	        
    	    System.out.println("网页标题:"+html.getTitle());
    	    System.out.println("网页表格数:"+html.getTables().length);
	    	System.out.println("网页body中的节点数:"+html.getBody().size());
	    	//遍历body中的所有一级DIV节点
			for(NodeIterator it = html.getBody().elements(); it.hasMoreNodes();){
				Node node = it.nextNode();
				if(node instanceof Div){
					System.out.println("当前Div节点是:" + node.toHtml());
				}				
			} 	    	
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** LinkFindingVisitor类的用法举例 */
	public static void testLinkFindingVisitor(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//设置Parser对象的字符编码,一般与网页的字符编码保持一致
	        parser.setEncoding("GB2312");
	        //创建LinkFindingVisitor对象
	        LinkFindingVisitor lvisitor = new LinkFindingVisitor("http://news.qq.com/");
	        //查找http://www.qq.com的链接个数
	        parser.visitAllNodesWith(lvisitor);
	        System.out.println("网页中包含http://news.qq.com/的链接个数:"+lvisitor.getCount());
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** ObjectFindingVisitor类的用法举例 */
	public static void testObjectFindingVisitor(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//设置Parser对象的字符编码,一般与网页的字符编码保持一致
	        parser.setEncoding("GB2312");
	        //创建ObjectFindingVisitor对象
	        ObjectFindingVisitor visitor = new ObjectFindingVisitor(TableTag.class);
	        //找出网页中的所有表格标签节点
	        parser.visitAllNodesWith(visitor);
	        Node[] nodes = visitor.getTags();
	        System.out.println("网页中包含的表格个数:"+nodes.length);
	        TableTag tabletab = null;
	        for(int i=0;i<nodes.length;i++){
	        	tabletab = (TableTag)nodes[i];
	        	 System.out.println("第"+(i+1)+"个表格行数:"+tabletab.getRowCount());
	        }
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** StringFindingVisitor类的用法举例 */
	public static void testStringFindingVisitor(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//设置Parser对象的字符编码,一般与网页的字符编码保持一致
	        parser.setEncoding("GB2312");
	        //创建StringFindingVisitor对象
	        StringFindingVisitor visitor = new StringFindingVisitor("财经");
	        //统计网页中的"财经"字符串出现的次数
	        parser.visitAllNodesWith(visitor);
	        System.out.println("网页中的\"财经\"字符串出现的次数:"+visitor.stringFoundCount());
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** TagFindingVisitor类的用法举例 */
	public static void testTagFindingVisitor(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//设置Parser对象的字符编码,一般与网页的字符编码保持一致
	        parser.setEncoding("GB2312");
	        //创建TagFindingVisitor对象
	        String[] tags = new String[2];
	        tags[0]="img";
	        tags[1]="div";
	        TagFindingVisitor visitor = new TagFindingVisitor(tags);
	        //统计网页中的img标签与div标签分别出现的次数
	        parser.visitAllNodesWith(visitor);
	        System.out.println("网页中的img标签个数:"+visitor.getTagCount(0));
	        System.out.println("网页中的div标签个数:"+visitor.getTagCount(1));
	        //访问网页中的所有img标签
	        Node[] nodes =  visitor.getTags(0);
	        ImageTag img = null;
	        for(int i=0;i<nodes.length;i++){
	        	img = (ImageTag)nodes[i];
	        	 System.out.println("第"+(i+1)+"张图片为:"+img.getImageURL());
	        }	        
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** TextExtractingVisitor类的用法举例 */
	public static void testTextExtractingVisitor(String url){
		try{
			//通过指定URLConnection对象创建Parser对象
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//设置Parser对象的字符编码,一般与网页的字符编码保持一致
	        parser.setEncoding("GB2312");
	        //创建StringFindingVisitor对象
	        TextExtractingVisitor visitor = new TextExtractingVisitor();
	        //去除网页中的所有标签,提出纯文本内容
	        parser.visitAllNodesWith(visitor);
	        System.out.println("网页的纯文本内容为:"+visitor.getExtractedText());
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	

}
