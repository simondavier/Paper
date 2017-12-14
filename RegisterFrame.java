
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;



import com.googlecode.javacv.FrameGrabber.Exception;


public class RegisterFrame {

	public static Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Label label_User = null;
	private Button button_Next = null;
	public static Text text_User = null;
	private Label label = null;
	private Label labelTip = null;
	public RegisterFrame(){
		createSShell();
	}
	
	public Shell getShell()
	{
		
		return sShell;
	}
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new Shell();
		sShell.setText("FaceRecognizer_Register");
		sShell.setSize(new Point(533, 317));
		sShell.setLayout(null);
		label_User = new Label(sShell, SWT.NONE);
		label_User.setText("输入用户名：");
		label_User.setFont(new Font(Display.getDefault(), "宋体", 10, SWT.NORMAL));
		label_User.setBounds(new Rectangle(122, 67, 85, 25));
		button_Next = new Button(sShell, SWT.NONE);
		button_Next.setBounds(new Rectangle(114, 140, 297, 42));
		button_Next.setText("下一步");
		
		labelTip = new Label(getShell(), SWT.NONE);
		labelTip.setBounds(new Rectangle(111, 210, 380, 80));
		labelTip.setFont(new Font(Display.getDefault(), "宋体", 10, SWT.NORMAL));
		labelTip.setText("删除已注册的用户：1.将data中的Fortraining.txt清空！" +
				"\n2.将data中的facedata.xml打开，留下第一行<?xml version=?>并清空保存");
		
		
		text_User = new Text(sShell, SWT.BORDER);
		text_User.setBounds(new Rectangle(239, 67, 168, 30));
		label = new Label(getShell(), SWT.NONE);
		label.setBounds(new Rectangle(111, 118, 350, 80));
		label.setFont(new Font(Display.getDefault(), "宋体", 12, SWT.NORMAL));
		label.setText("请勿输入已存在的用户名且用户名不要带空格!");
		button_Next.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					String userName=RegisterFrame.text_User.getText();
					if(userName.isEmpty()==false)
					{
						FaceRecognizer fr=new FaceRecognizer();
						try 
						{
							//System.out.println(userName);
							fr.register(userName);
							LoginFrame displayshell=new LoginFrame();
							Shell newShell= displayshell.getShell();
							Shell oldShell=RegisterFrame.sShell;
							RegisterFrame.sShell=newShell;
							RegisterFrame.sShell.open();
							oldShell.dispose();
						}
						catch (Exception e1) 
						{
							e1.printStackTrace();
						}
					}
			}
		});
	
	}

}
