import org.htmlparser.*;
import org.htmlparser.visitors.NodeVisitor;

/** 自定义NodeVisitor子类,并重载抽象类NodeVisitor中的相关方法 */
public class MyNodeVisitor extends NodeVisitor {
	
	/** 重载抽象类NodeVisitor的beginParsing方法,解析开始时调用此方法 */
	public void beginParsing(){
		System.out.println("开始解析HTML内容......");
	}	
	
	/** 重载抽象类NodeVisitor的finishedParsing方法,解析结束时调用此方法 */
	public void finishedParsing(){
		System.out.println("整个HTML内容解析完毕!");
	}		

	/** 重载抽象类NodeVisitor的visitTag方法,遇到开始标签时调用此方法 */
	public void visitTag(Tag tag){
		System.out.println("开始当前标签: "+tag.getText());
	}
	
	/** 重载抽象类NodeVisitor的visitEndTag方法,遇到结束标签时调用此方法 */
	public void visitEndTag(Tag tag){
		System.out.println("结束当前标签: "+tag.getText());
	}	
	
	/** 重载抽象类NodeVisitor的visitStringNode方法,遇到文本节点时调用此方法 */
	public void visitStringNode(Text string){
		System.out.println("当前文本节点: "+string);
	}
	
	/** 重载抽象类NodeVisitor的visitRemarkNode方法,遇到注释时调用此方法 */
	public void visitRemarkNode(Remark remark){
		System.out.println("当前注释: "+remark);
	}	
}