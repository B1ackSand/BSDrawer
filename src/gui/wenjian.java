package gui;

import java.awt.Graphics2D;
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

public class wenjian extends JFrame{
	private ObjectInputStream input;
	private ObjectOutputStream output;
	int index = 0;
	//图形数量

	drawings[] itemList = new drawings[5000];
	//用来存放基本图形的数组


	class drawings implements Serializable//父类，基本图形单元，用到串行化接口，保存时所用
	{
		int x1, y1, x2, y2; //定义坐标属性
		int type;       //定义字体属性
		String s1;
		String s2;      //定义字体风格属性
		void draw(Graphics2D g2d) {
		}
		//定义绘图函数
	}


	public void saveFile() {
		JFileChooser fileChooser= new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result =fileChooser.showSaveDialog(null);
		if(result==JFileChooser.CANCEL_OPTION) {
			return;
		}

		File fileName = fileChooser.getSelectedFile();
		fileName.canWrite();

		if(fileName==null||fileName.getName().equals("")) {
			JOptionPane.showMessageDialog(fileChooser, "无效的文件名","无效的文件名",JOptionPane.ERROR_MESSAGE);
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
		int result = fileChooser.showOpenDialog(null);
		if(result==JFileChooser.CANCEL_OPTION) {
			return;
		}
		File fileName = fileChooser.getSelectedFile();
		fileName.canRead();
		if(fileName==null||fileName.getName().equals("")) {
			JOptionPane.showMessageDialog(fileChooser, "无效的文件名","无效的文件名",JOptionPane.ERROR_MESSAGE);;

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
				//createNewItem(); //新建画图基本单元的程序段
				input.close();
				repaint();
			}catch (EOFException endofEofException) {
				JOptionPane.showMessageDialog(this, "读取完毕",
						"完毕",JOptionPane.ERROR_MESSAGE);
				// TODO: handle exception
			}catch (ClassNotFoundException classNotFoundException) {
				JOptionPane.showMessageDialog(this, "无法创建对象",
						"end of file",JOptionPane.ERROR_MESSAGE);
			}catch (IOException ioException) {
				JOptionPane.showMessageDialog(this, "读取失败",
						"Read Error",JOptionPane.ERROR_MESSAGE);
				// TODO: handle exception
			}
		}
	}

	public void newFile() {
		index =0 ;
		//createNewItem();//创建新的基本图像单位
		repaint();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
