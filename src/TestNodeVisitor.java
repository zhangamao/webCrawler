import java.net.*;
import org.htmlparser.*;
import org.htmlparser.tags.*;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.visitors.*;

/** ʹ��Visitor��ʽ������ҳ���� */
public class TestNodeVisitor {

	public static void main(String[] args) {
		testMyNodeVisitor("<html><head><title>���Ǳ���</title></head><!-- ���Ŀ�ʼ --><body><div>���ǲ�������</div></body></html>");
		testHtmlPage("http://www.qq.com");
		testLinkFindingVisitor("http://www.qq.com");
		testObjectFindingVisitor("http://www.qq.com");
		testStringFindingVisitor("http://www.qq.com");
		testTagFindingVisitor("http://www.qq.com");
		testTextExtractingVisitor("http://www.qq.com");
	}
	
	/** �Զ���NodeVisitor������÷����� */
	public static void testMyNodeVisitor(String htmlcode){
		try{
			//ͨ��ָ��HTML���ݴ���Parser����
			Parser parser = new Parser(htmlcode);
			//����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
	        parser.setEncoding("GB2312");
	        //����һ��MyNodeVisitorʵ��
	        MyNodeVisitor visitor = new MyNodeVisitor(); 
	        //ʹ��MyNodeVisitorʵ������parser�е�����
	        parser.visitAllNodesWith(visitor);
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	
	
	/** HtmlPage����÷����� */
	public static void testHtmlPage(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
	        parser.setEncoding("GB2312");
	        //����HtmlPage����
	        HtmlPage html = new HtmlPage(parser);
	        //ͨ��HtmlPage�������parser�е����нڵ�
	        parser.visitAllNodesWith(html);	        
    	    System.out.println("��ҳ����:"+html.getTitle());
    	    System.out.println("��ҳ�����:"+html.getTables().length);
	    	System.out.println("��ҳbody�еĽڵ���:"+html.getBody().size());
	    	//����body�е�����һ��DIV�ڵ�
			for(NodeIterator it = html.getBody().elements(); it.hasMoreNodes();){
				Node node = it.nextNode();
				if(node instanceof Div){
					System.out.println("��ǰDiv�ڵ���:" + node.toHtml());
				}				
			} 	    	
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** LinkFindingVisitor����÷����� */
	public static void testLinkFindingVisitor(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
	        parser.setEncoding("GB2312");
	        //����LinkFindingVisitor����
	        LinkFindingVisitor lvisitor = new LinkFindingVisitor("http://news.qq.com/");
	        //����http://www.qq.com�����Ӹ���
	        parser.visitAllNodesWith(lvisitor);
	        System.out.println("��ҳ�а���http://news.qq.com/�����Ӹ���:"+lvisitor.getCount());
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** ObjectFindingVisitor����÷����� */
	public static void testObjectFindingVisitor(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
	        parser.setEncoding("GB2312");
	        //����ObjectFindingVisitor����
	        ObjectFindingVisitor visitor = new ObjectFindingVisitor(TableTag.class);
	        //�ҳ���ҳ�е����б���ǩ�ڵ�
	        parser.visitAllNodesWith(visitor);
	        Node[] nodes = visitor.getTags();
	        System.out.println("��ҳ�а����ı�����:"+nodes.length);
	        TableTag tabletab = null;
	        for(int i=0;i<nodes.length;i++){
	        	tabletab = (TableTag)nodes[i];
	        	 System.out.println("��"+(i+1)+"���������:"+tabletab.getRowCount());
	        }
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** StringFindingVisitor����÷����� */
	public static void testStringFindingVisitor(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
	        parser.setEncoding("GB2312");
	        //����StringFindingVisitor����
	        StringFindingVisitor visitor = new StringFindingVisitor("�ƾ�");
	        //ͳ����ҳ�е�"�ƾ�"�ַ������ֵĴ���
	        parser.visitAllNodesWith(visitor);
	        System.out.println("��ҳ�е�\"�ƾ�\"�ַ������ֵĴ���:"+visitor.stringFoundCount());
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** TagFindingVisitor����÷����� */
	public static void testTagFindingVisitor(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
	        parser.setEncoding("GB2312");
	        //����TagFindingVisitor����
	        String[] tags = new String[2];
	        tags[0]="img";
	        tags[1]="div";
	        TagFindingVisitor visitor = new TagFindingVisitor(tags);
	        //ͳ����ҳ�е�img��ǩ��div��ǩ�ֱ���ֵĴ���
	        parser.visitAllNodesWith(visitor);
	        System.out.println("��ҳ�е�img��ǩ����:"+visitor.getTagCount(0));
	        System.out.println("��ҳ�е�div��ǩ����:"+visitor.getTagCount(1));
	        //������ҳ�е�����img��ǩ
	        Node[] nodes =  visitor.getTags(0);
	        ImageTag img = null;
	        for(int i=0;i<nodes.length;i++){
	        	img = (ImageTag)nodes[i];
	        	 System.out.println("��"+(i+1)+"��ͼƬΪ:"+img.getImageURL());
	        }	        
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	/** TextExtractingVisitor����÷����� */
	public static void testTextExtractingVisitor(String url){
		try{
			//ͨ��ָ��URLConnection���󴴽�Parser����
			Parser parser = new Parser((HttpURLConnection)(new URL(url)).openConnection());
			//����Parser������ַ�����,һ������ҳ���ַ����뱣��һ��
	        parser.setEncoding("GB2312");
	        //����StringFindingVisitor����
	        TextExtractingVisitor visitor = new TextExtractingVisitor();
	        //ȥ����ҳ�е����б�ǩ,������ı�����
	        parser.visitAllNodesWith(visitor);
	        System.out.println("��ҳ�Ĵ��ı�����Ϊ:"+visitor.getExtractedText());
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	

}
