import org.htmlparser.*;
import org.htmlparser.visitors.NodeVisitor;

/** �Զ���NodeVisitor����,�����س�����NodeVisitor�е���ط��� */
public class MyNodeVisitor extends NodeVisitor {
	
	/** ���س�����NodeVisitor��beginParsing����,������ʼʱ���ô˷��� */
	public void beginParsing(){
		System.out.println("��ʼ����HTML����......");
	}	
	
	/** ���س�����NodeVisitor��finishedParsing����,��������ʱ���ô˷��� */
	public void finishedParsing(){
		System.out.println("����HTML���ݽ������!");
	}		

	/** ���س�����NodeVisitor��visitTag����,������ʼ��ǩʱ���ô˷��� */
	public void visitTag(Tag tag){
		System.out.println("��ʼ��ǰ��ǩ: "+tag.getText());
	}
	
	/** ���س�����NodeVisitor��visitEndTag����,����������ǩʱ���ô˷��� */
	public void visitEndTag(Tag tag){
		System.out.println("������ǰ��ǩ: "+tag.getText());
	}	
	
	/** ���س�����NodeVisitor��visitStringNode����,�����ı��ڵ�ʱ���ô˷��� */
	public void visitStringNode(Text string){
		System.out.println("��ǰ�ı��ڵ�: "+string);
	}
	
	/** ���س�����NodeVisitor��visitRemarkNode����,����ע��ʱ���ô˷��� */
	public void visitRemarkNode(Remark remark){
		System.out.println("��ǰע��: "+remark);
	}	
}