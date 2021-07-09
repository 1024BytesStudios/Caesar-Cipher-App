import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame {
	
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JTextField txtKey;
	private JSlider slider;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	public String encode(String message, int keyVal) {
		String output = "";
		char key = (char) keyVal;
		
		for(int x = 0; x < message.length(); x++) {
			char input = message.charAt(x);
			if(input >= 'A' && input <= 'Z') {
				input += key;
				if(input > 'Z')
					input -= 26;
				if(input < 'A')
					input += 26;
			}
			else if(input >= 'a' && input <= 'z') {
				input += key;
				if(input > 'z')
					input -= 26;
				if(input < 'a')
					input += 26;
			}
			else if(input >= '0' && input <= '9') {
				input += (keyVal % 10);
				if(input > '9')
					input -= 10;
				if(input < '0')
					input += 10;
			}
			output += input;
		}
		return output;
	}
	
	public Main() {
		setTitle("Ceaser Cipher App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 140);
		getContentPane().add(scrollPane);
		
		txtIn = new JTextArea();
		txtIn.setFont(new Font("Arial", Font.BOLD, 16));
		scrollPane.setViewportView(txtIn);
		txtIn.setWrapStyleWord(true);
		txtIn.setLineWrap(true);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 210, 564, 140);
		getContentPane().add(scrollPane_1);
		
		txtOut = new JTextArea();
		txtOut.setFont(new Font("Arial", Font.BOLD, 16));
		scrollPane_1.setViewportView(txtOut);
		txtOut.setWrapStyleWord(true);
		txtOut.setLineWrap(true);
		
		JLabel lblNewLabel = new JLabel("Key: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel.setBounds(308, 166, 47, 15);
		getContentPane().add(lblNewLabel);
		
		txtKey = new JTextField();
		txtKey.setFont(new Font("Arial", Font.PLAIN, 16));
		txtKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String key = txtKey.getText();
					slider.setValue(Integer.parseInt(key));
				} catch(Exception ex) {
					
				}
			}
		});
		txtKey.setBounds(347, 164, 54, 21);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JButton btnEncode = new JButton("Encode/Decode");
		btnEncode.setFont(new Font("Arial", Font.BOLD, 13));
		btnEncode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				try {
					String message = txtIn.getText();
					int key = Integer.parseInt(txtKey.getText());
					String output = encode(message, key);
					txtOut.setText(output);	
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Enter a whole number");
					txtKey.requestFocus();
					txtKey.selectAll();
				}
			}
		});
		btnEncode.setBounds(430, 163, 144, 23);
		getContentPane().add(btnEncode);
		
		slider = new JSlider();
		slider.setFont(new Font("Arial", Font.PLAIN, 12));
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtKey.setText("" + slider.getValue());
				String message = txtIn.getText();
				int key = slider.getValue();
				String output = encode(message, key);
				txtOut.setText(output);
			}
		});
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(13);
		slider.setMinimum(-26);
		slider.setMinorTickSpacing(1);
		slider.setMaximum(26);
		slider.setBounds(42, 156, 200, 44);
		getContentPane().add(slider);
	}

	public static void main(String[] args) {
		Main app = new Main();
		app.setSize(new java.awt.Dimension(600, 400));
		app.setVisible(true);

	}
}
