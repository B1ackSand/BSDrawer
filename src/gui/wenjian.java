package main;

import java.awt.Graphics2D;
import java.awt.event.ItemListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import awaf.drawings;

public class Main extends JFrame{
    private ObjectInputStream input;
    private ObjectOutputStream output; 
    int index = 0; //ͼ������
    drawings[] itemList = new drawings[5000];//������Ż���ͼ�ε�����
    
    
    class drawings implements Serializable//���࣬����ͼ�ε�Ԫ���õ����л��ӿڣ�����ʱ����
    {
        int x1, y1, x2, y2; //������������
        int type;       //������������
        String s1;
        String s2;      //��������������
        void draw(Graphics2D g2d) {
        }
        //�����ͼ����
    }
    
    
	public void saveFile() {
		JFileChooser fileChooser= new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result =fileChooser.showSaveDialog(this);
		if(result==JFileChooser.CANCEL_OPTION) {
			return;
		}
		
		File fileName = fileChooser.getSelectedFile();
		fileName.canWrite();
		
		if(fileName==null||fileName.getName().equals("")) {
			JOptionPane.showMessageDialog(fileChooser, "��Ч���ļ���","��Ч���ļ���",JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				fileName.delete();
				FileOutputStream fos = new FileOutputStream(fileName);
				output=new ObjectOutputStream(fos);
				drawings record;
				
				output.writeInt(index);
				for(int i=0;i<index;i++) {
					drawings p =itemList[i];
					output.writeObject(p);
					output.flush();
				}
				output.close();
				fos.close();
			}catch (IOException ioe) {
				ioe.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	
	
	public void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(this);
		if(result==JFileChooser.CANCEL_OPTION) {
			return;
		}
		File fileName = fileChooser.getSelectedFile();
		fileName.canRead();
		if(fileName==null||fileName.getName().equals("")) {
			JOptionPane.showMessageDialog(fileChooser, "��Ч���ļ���","��Ч���ļ���",JOptionPane.ERROR_MESSAGE);;
			
		} else {
			try {
				FileInputStream fis = new FileInputStream(fileName);
				input = new ObjectInputStream(fis);
				drawings inputRecord;
				int countNumber =0;
				countNumber = input.readInt();
				for(index=0;index<countNumber;index++) {
					inputRecord = (drawings) input.readObject();
					itemList[index] = inputRecord;
				}
				createNewItem(); //�½���ͼ������Ԫ�ĳ����
				input.close();
				repaint();
			}catch (EOFException endofEofException) {
				JOptionPane.showMessageDialog(this, "��ȡ���",
						"���",JOptionPane.ERROR_MESSAGE);
				// TODO: handle exception
			}catch (ClassNotFoundException classNotFoundException) {
				JOptionPane.showMessageDialog(this, "�޷���������",
						"end of file",JOptionPane.ERROR_MESSAGE);
			}catch (IOException ioException) {
				JOptionPane.showMessageDialog(this, "��ȡʧ��",
						"Read Error",JOptionPane.ERROR_MESSAGE);
				// TODO: handle exception
			}
		}
	}
	
	public void newFile() {
		index =0 ;
		createNewItem();//�����µĻ���ͼ��λ
		repaint();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
