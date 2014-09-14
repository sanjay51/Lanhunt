package searchPackage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class OldMyPanel extends javax.swing.JPanel {
	Button btnDownload=new Button("Download");
	Button btnOpenFolder=new Button("Open Folder..");
	TextField txtField=new TextField();
	
	Label label=new Label();
	OldMyPanel(){
		configure();
	}
	
	OldMyPanel(int x, int y){
		configure();
		this.setBounds(x, y, 618, 69);
	}
	
	OldMyPanel(int x,int y,String label, String textField){
		configure();
		this.setBounds(x, y,618,69);
		this.label.setText(label);
		this.txtField.setText(textField);
	}
	
	public void configure(){
		this.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setBounds(10, 123, 618, 70);
		this.setLayout(null);
		
		btnDownload.setBounds(414, 10, 70, 22);
		this.add(btnDownload);
		
		label.setText("File name here");
		label.setBounds(10, 10, 181, 22);
		this.add(label);
		
		btnOpenFolder.setBounds(502, 10, 106, 22);
		this.add(btnOpenFolder);
		
		txtField.setText("\\\\172.16.144.44\\users\\default.txt");
		txtField.setBounds(10, 38, 363, 21);
		this.add(txtField);
	}
	
	public void setData(String label, String textField) {
		this.label.setText(label);
		this.txtField.setText(textField);
	}
}
