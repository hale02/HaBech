package lop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileReadWrite {
	
	public void writeCategoy() {
		File f = new File("E:\\baitap\\category.txt");
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Data.listCate);
			fos.close();
			oos.close();
			System.out.println("Da ghi danh muc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readCategory() {
		File f = new File("E:\\baitap\\category.txt");
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Data.listCate = (List<Categories>) ois.readObject();
			Data.listCate.forEach(x->x.displayData());
			fis.close();
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Write, read java file PRoduct
	 */
	public void writeProduct() {
		File f = new File("E:\\baitap\\product1.txt");
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Data.listPro);
			System.out.println("Da ghi san pham");
			fos.close();
			oos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		public void readProduct() {
			File f = new File("E:\\baitap\\product1.txt");
			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Data.listPro = (List<Product>) ois.readObject();
				Data.listPro.forEach(x -> x.displayData());
				fis.close();
				ois.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

