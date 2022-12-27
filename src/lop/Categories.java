package lop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lop_interface.ICategories;

public class Categories implements ICategories,Serializable{
	private int catalogId;
	private String catalogName;
	private String descriptions;
	private boolean catalogStatus;
	private int parentId=0;
	
	
	//constructor mac dinh
	public Categories() {
		// TODO Auto-generated constructor stub
	}
	
	//constructor co tham so
	public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus, int parentId) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.descriptions = descriptions;
		this.catalogStatus = catalogStatus;
		this.parentId = parentId;
	}

	//-----------------begin getter and setter----------------------
	public int getCatalogId() {
		return catalogId;
	}


	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}


	public String getCatalogName() {
		return catalogName;
	}


	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}


	public String getDescriptions() {
		return descriptions;
	}


	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}


	public boolean isCatalogStatus() {
		return catalogStatus;
	}


	public void setCatalogStatus(boolean catalogStatus) {
		this.catalogStatus = catalogStatus;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	//-----------------------------end getter and setter-----------------------

	//Khoi tao phuong thuc inputData

	@Override
	public void inputData() {
		Scanner nhap = new Scanner (System.in);
		while(true) {
			System.out.println("Nhap ma danh muc: ");
			try {
				int id = Integer.parseInt(nhap.nextLine());
				boolean existed = false;
				for (Categories categories : Data.listCate) {
					if(categories.getCatalogId()==id) {
						existed = true;
						break;
					}
				} 
					if(existed) {
						System.out.println("Ma danh muc nay da ton tai , hay nhap ma khac: ");
					}else {
						setCatalogId(id);			
						break;
					}
			}catch(Exception e) {
				System.out.println("Ma danh muc phai la so nguyen! ");	
			}
		}
			while(true) {
			System.out.println("Nhap ten danh muc: ");
			String name = nhap.nextLine();
			if(name.length()<6 || name.length()>30) {
				System.out.println("Phai nhap tu 6 den 30 ki tu, hay nhap lai: ");
			}else {
				setCatalogName(name);
				break;
				}
			}
			
			while(true) {
				System.out.println("Nhap mo ta danh muc: ");
				String des = nhap.nextLine();
				if(des.length()<1) {
					System.out.println("Khong duoc de trong o danh muc, hay nhap lai!");
				}else {
					setDescriptions(des);
					break;
				}
			}
			
			
			System.out.println("Nhap trang thai danh muc: ");
			setCatalogStatus(Boolean.parseBoolean(nhap.nextLine()));
			
			while (true) {
				System.out.println("Nhap danh muc cha: ");
				try {
					int parent = Integer.parseInt(nhap.nextLine());
					boolean blExisted = false;
					for (Categories category : Data.listCate) {
						if (category.getCatalogId() == parent) {
							blExisted = true;
							break;
						}
					}

					if (blExisted || parent == 0) {
						setParentId(parent);
						break; 
					} else {
						System.out.println("Ma danh muc cha chua ton tai");
					}
				} catch (Exception e) {
					System.out.println("Ma danh muc cha phai la so nguyen");
				}
			}
			
	}
	//Khoi tao phuong thuc displayData
	@Override
	public void displayData() {
		
//		System.out.println("\tMa danh muc: "+catalogId);
//		System.out.println("\tTen danh muc: "+catalogName);
//		System.out.println("\tMo ta danh muc: "+descriptions);
//		System.out.println("\tTrang thai danh muc: "+(catalogStatus?"Hoat dong":"Khong hoat dong"));
//		System.out.println("\tMa danh muc cha: "+parentId);
	
		// TODO Auto-generated method stub
		
		System.out.printf("| %4d | %-20s | %-20s | %4d | ", this.catalogId, this.catalogName, this.descriptions,this.parentId);
//		System.out.println("Mã danh mục: " + this.catalogId);
//		System.out.println("Tên danh mục: " + this.catalogName);
//		System.out.println("Mô tả danh mục: " + this.descriptions);
		System.out.print((this.catalogStatus ? "Hoat dong" : "Khong hoat dong") + "\n");
//		System.out.println("Mã danh mục cha: " + this.parenId);

	}
	public static void main(String[] args) {
		Scanner nhap = new Scanner(System.in);
		//InputData, displayData cho Categories
		System.out.println("Nhap so phan tu cho danh muc: ");
		int n = Integer.parseInt(nhap.nextLine());
		for (int i = 0; i < n; i++) {
			System.out.println("Nhap thong tin danh muc thu "+(i+1));
			Categories ca = new Categories();
			ca.inputData();
			Data.listCate.add(ca);
	}
		Data.listCate.forEach(t->t.displayData());
	}
}
