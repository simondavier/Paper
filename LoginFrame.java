import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;


import com.googlecode.javacv.FrameGrabber.Exception;



public class LoginFrame {

	public static Shell sShell = null; 
	private Button button_FaceLogin = null;
	private Button button_Register = null;
	private Label label = null;
	public static  Button checkBox_video=null;
	private Button button_RecongizeFromImage = null;
	public static boolean video_flag=false;
	int count=1;

	
	public Shell getShell()
	{
		
		return sShell;
	}
	
	public LoginFrame(){
		createSShell();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		LoginFrame thisClass = new LoginFrame();
		thisClass.createSShell();
		thisClass.sShell.open();
		
		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	
	private void createSShell() {
		sShell = new Shell(SWT.APPLICATION_MODAL | SWT.SHELL_TRIM | SWT.BORDER);
		sShell.setText("FaceRecoginzer");
		sShell.setSize(new Point(476, 333));
		sShell.setLayout(null);
		button_FaceLogin = new Button(sShell, SWT.NONE);
		button_FaceLogin.setBounds(new Rectangle(35, 70, 170, 112));
		button_FaceLogin.setFont(new Font(Display.getDefault(), "����", 14, SWT.NORMAL));
		button_FaceLogin.setText("����ʶ��");
		
		button_Register = new Button(sShell, SWT.NONE);
		button_Register.setBounds(new Rectangle(249, 70, 186, 111));
		button_Register.setFont(new Font(Display.getDefault(), "����", 14, SWT.NORMAL));
		button_Register.setText("����ѵ��");
		label = new Label(getShell(), SWT.NONE);
		label.setBounds(new Rectangle(172, 280, 150, 30));
		label.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		//label.setText("��һ��ʹ������ѵ������");

		button_RecongizeFromImage = new Button(getShell(), SWT.NONE);
		button_RecongizeFromImage.setBounds(new Rectangle(42, 190, 381, 72));
		button_RecongizeFromImage.setFont(new Font(Display.getDefault(), "΢���ź�", 14, SWT.NORMAL));
		button_RecongizeFromImage.setText("ʶ��ͼƬ");
		
		checkBox_video = new Button(getShell(), SWT.CHECK);
		//checkBox_video.setBounds(new Rectangle(80, 0,20, 20));
		/*checkBox_video.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				count++;
				if(count%2==0)
					video_flag=true;
				else
					video_flag=false;
			}
		});
		*/
		label = new Label(getShell(), SWT.NONE);
		label.setBounds(new Rectangle(100, 0, 350, 60));
		label.setFont(new Font(Display.getDefault(), "΢���ź�", 10, SWT.NORMAL));
		//label.setText("���.   ����Ƶ�ļ�����ʶ���ע��.\nע�⣺ֻ֧��avi,wmv.mp4��ʽ����Ƶ\n��·�������ļ������ܰ������ģ�");
		//label.setText("��ѡʹ���ļ���ʶ��");
		label.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		
		button_RecongizeFromImage
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						FaceRecognizer fr=new FaceRecognizer();
						FileDialog fd=new FileDialog(LoginFrame.sShell,SWT.OPEN);  
						fd.setFilterExtensions(new String[]{"*.jpg","*.bmp","*.png","*.*"});
						fd.setFilterNames(new String[]{".jpeg",".bmp",".png"});
						String filename=fd.open();
						fr.recongizeFormImage(filename);
					}
				});

		button_FaceLogin
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				FaceRecognizer fr=new FaceRecognizer();
				boolean flag=true;
				while(flag)
				{
					try 
					{
						flag=fr.recognizeFromCam();
					}
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
				} 
			}
			
		});
		
		
		button_Register
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				RegisterFrame displayshell=new RegisterFrame();
				Shell newShell= displayshell.getShell();
				Shell oldShell=LoginFrame.sShell;
				LoginFrame.sShell=newShell;
				LoginFrame.sShell.open();
				oldShell.dispose();
			}
		});
	}

}
