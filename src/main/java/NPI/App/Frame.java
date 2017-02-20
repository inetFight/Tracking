package NPI.App;

import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import NPI.Helpers.ChangeLog;
import NPI.Helpers.SmartScroller;
import NPI.Helpers.SwingUtils;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;

import org.json.simple.parser.ParseException;

import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Frame {
    private String info = "<html><p>1. Можно загружать только csv или txt файлы"
+"</p>"
+"<p>2. В файл необходимо записать номера первичных экспресс-накладных. Каждый номер с новой строки, без пробелов или пустых строк"
+"</p>"
+"<p>3. Результат сохраняется в csv файле в кодировке UTF-8 с разделителем точка с запятой"
+"</p>"
+"<p>4. Для использования результатов необходимо открыть Exel, вкладка &quot;Данные&quot;, вставить из текста. Далее выбрать кодировку - UTF-8, разделитель - точка с запятой"
+"</p>"
+"<p>5. Во время работы трекинга окно программы блокируется."
+"</p>"
+"<p>6. Программа рассчитана максимум на 4 этапа трекинга"
+"</p>"
+"<p>Удачи!"
+"</p>"
+"</html>";

	public static JFrame frame;
	public static JButton button = new JButton("<html>Выбрать файл<p>для трекинга</html>");;
    InputStream stream = null;
	static String file_path_open;
	static String file_path_save;
	public static JButton button_1 = new JButton("<html>Выбрать куда<p>сохранить файл</html>");;
	public JLabel label;
	public static JPanel panel = new JPanel();
    String userDir = System.getProperty("user.home");
    public static JTextArea textArea = new JTextArea();
    public static JScrollPane scroll;
    public static JButton btnNewButton = new JButton("<html>Найти смысл<p><b><center>жизни</center></html>");;
    public JLabel label_1;
    public static JLabel label_2 = new JLabel("Трекинг не запущен");
    private JLabel label_3;
    static public JLabel label_4 = new JLabel("Трекинг не запущен");
    private File file;
    URL url1 = getClass().getClassLoader().getResource("всё.jpg");
    final ImageIcon icon = new ImageIcon(url1);
    private final JLabel lblByDmitriy = new JLabel("@ by Dmitriy Litvinov specially for NovaPoshta International");
    private static boolean shortVersion = false;
    

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	 
	}

	
	public Frame() throws URISyntaxException, IOException  {
		initialize();
		
		
		
	}
	//Путь к файлу для трекинга
	public static String getFilePath(){
		return file_path_open;
		
	}
	//Путь к файлу для сохранения
	public static String getFilePathSave(){
		return file_path_save;
		
	}
	//Получаем статус чек бокса
	public static boolean getStatusShort(){
		return shortVersion;
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	private void initialize() throws URISyntaxException, IOException {
		frame = new JFrame("Трекинг v 1.2.0");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		panel.setToolTipText("");
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 434, 651);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		final JLabel lblNewLabel = new JLabel("Выберите файл");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(156, 11, 278, 43);
		panel.add(lblNewLabel);
		frame.setBounds(100, 100, 450, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);

		final FileFilter filter = new FileNameExtensionFilter("txt or csv files",new String[] {"txt", "csv"});
		final FileFilter filterSave = new FileNameExtensionFilter("csv file"," ");
		
		label = new JLabel("Выбрать куда сохранить");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label.setBounds(156, 65, 278, 43);
		panel.add(label);
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser(userDir + "/Desktop");
				fileopen.setAcceptAllFileFilterUsed(false);
				fileopen.addChoosableFileFilter(filter);
				AbstractButton button = SwingUtils.getDescendantOfType(AbstractButton.class,
						fileopen, "Icon", UIManager.getIcon("FileChooser.detailsViewIcon"));
		        	button.doClick();
                int ret = fileopen.showDialog(null, "Выбрать файл");                
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();
                    lblNewLabel.setText(file.getName());
                    
                    file_path_open = file.getAbsolutePath();
                }
			}
		});
		button.setBounds(10, 11, 138, 43);
		panel.add(button);
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(userDir + "/Desktop");
				fc.setSelectedFile(new File(userDir + "/Desktop/Result"));
		        fc.setFileFilter(filterSave);
		        fc.setAcceptAllFileFilterUsed(false);
		        AbstractButton button = SwingUtils.getDescendantOfType(AbstractButton.class,
		        		fc, "Icon", UIManager.getIcon("FileChooser.detailsViewIcon"));
		        	button.doClick();
		        if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
		        	file_path_save = fc.getSelectedFile() + ".csv";
		        	label.setText(fc.getSelectedFile().getName() + ".csv");
		            
		        }
			}
		});
		button_1.setBounds(10, 65, 138, 43);
		panel.add(button_1);
		
		textArea.setEditable(false);
		
		scroll = new JScrollPane(textArea);
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 297, 414, 318);
		
		panel.add(scroll);
		
		
		btnNewButton.setBounds(156, 119, 138, 52);
		panel.add(btnNewButton);
		
		label_1 = new JLabel("<html>Номеров в этом<br><center>этапе</center></html>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 182, 138, 43);
		panel.add(label_1);
		
		
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_2.setBounds(156, 182, 278, 43);
		panel.add(label_2);
		
		label_3 = new JLabel("<html><center>Этап трекинга</center></html>");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 236, 138, 43);
		panel.add(label_3);
		
		
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_4.setBounds(156, 236, 278, 43);
		panel.add(label_4);
		lblByDmitriy.setBounds(121, 626, 303, 14);
		panel.add(lblByDmitriy);
		
		lblByDmitriy.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblByDmitriy.setHorizontalAlignment(SwingConstants.CENTER);
		
		URL url = getClass().getClassLoader().getResource("info.png");
		
		Icon rightIcon = new ImageIcon(url);
		final JButton btnNewButton_1 = new JButton(rightIcon);
		btnNewButton_1.setIcon(rightIcon);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panel, info, "Справка", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnNewButton_1.setBorder(emptyBorder);
		btnNewButton_1.setBounds(304, 130, rightIcon.getIconWidth(), rightIcon.getIconHeight());
		
		panel.add(btnNewButton_1);
		
		JButton btnChangeLog = new JButton("Change log");
		btnChangeLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panel, ChangeLog.getChangeLog(), "История изменений", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnChangeLog.setBounds(10, 622, 116, 23);
		panel.add(btnChangeLog);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("+ короткая версия");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					shortVersion = true;
				}
			}
		});
		chckbxNewCheckBox.setBounds(10, 110, 138, 23);
		panel.add(chckbxNewCheckBox);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(file_path_open == null){
					JOptionPane.showMessageDialog(panel, "А что трекать? Файл то не выбран...", "Ошибочка", JOptionPane.ERROR_MESSAGE);
				}
				else if(file_path_save == null){
					JOptionPane.showMessageDialog(panel, "А сохранять куда? Надо бы выбрать...", "Ошибочка", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
						
						btnNewButton.setEnabled(false);
						button.setEnabled(false);
						button_1.setEnabled(false);
						textArea.setText(null);
					
						try {
							
							Start.track();
						} catch (URISyntaxException | IOException | ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						JTextPane jtp = new JTextPane();
//						jtp.setSize(new Dimension(480, 10));
//					    jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
//					    jtp.insertIcon(icon);
						
						BasicPlayer player = new BasicPlayer();
						try {
							
//							URL url2 = getClass().getClassLoader().getResource("eralash.mp3").getFile();							
							stream = getClass().getClassLoader().getResourceAsStream("eralash.mp3");
							 BufferedInputStream bis = new BufferedInputStream(stream);
							player.open(bis);
							
//							player.open(new File(getClass().getClassLoader().getResource("eralash.mp3").getFile()));
							
							player.play();
							Thread.sleep(1000);
							
							
						} catch (BasicPlayerException e1) {
							
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						JOptionPane.showMessageDialog(panel, icon, "Всё!", JOptionPane.CLOSED_OPTION);
						scroll.getVerticalScrollBar().setValue(0);
					}
			}
		});
		
	
		
		
		
		
		 
		
		
	}
}
