package lop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Scanner;

import lop_interface.IProduct;

public class Product implements IProduct, Serializable {
	private String productId;
	private String productName;
	private String title;
	private float importPrice;
	private float exportPrice;
	private float profit;
	private String descriptions;
	private boolean productStatus;
	private Categories catalog;
	
	//Khoi tao constructor mac dinh
	public Product() {
		// TODO Auto-generated constructor stub
	}
	//Khoi tao constructor cho thuoc tinh
	public Product(String productId, String productName, String title, float importPrice, float exportPrice,
			float profit, String descriptions, boolean productStatus, Categories catalog) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.title = title;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.profit = profit;
		this.descriptions = descriptions;
		this.productStatus = productStatus;
		this.catalog = catalog;
	}
	
	//---------------begin getter and setter-----------------
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) throws Exception{
		
		if(productId.charAt(0) != 'C' ) {
			throw new Exception("Ma san pham phai bat dau bang ky tu c");
		}
		if( productId.length() != 4) {
			throw new Exception("Ma san pham co 4 ky tu");
		}
		for (Product pro : Data.listPro) {
			if(pro.getProductId().equals(productId)) {
				throw new Exception("Ma san pham da ton tai");
			}
		}
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getImportPrice() {
		return importPrice;
	}
	public void setImportPrice(float importPrice) {
		this.importPrice = importPrice;
	}
	public float getExportPrice() {
		return exportPrice;
	}
	public void setExportPrice(float exportPrice) {
		this.exportPrice = exportPrice;
	}
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public boolean isProductStatus() {
		return productStatus;
	}
	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}
	public Categories getCatalog() {
		return catalog;
	}
	public void setCatalog(Categories catalog) {
		this.catalog = catalog;
	}
//---------------end getter and setter------------------------
	//Khoi tao phuong thuc inputData()
	@Override
	public void inputData() {
		Scanner nhap = new Scanner (System.in);
		boolean existed = true;
	
		do {
			System.out.print("Nhap ma san pham: ");
			try { 
				this.setProductId(nhap.nextLine());
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				existed = false;
			}
		}while (!existed);

		while(true) {
			System.out.println("Nhap ten san pham: ");
			productName = nhap.nextLine();
			if(productName.length()<6 || productName.length()>50) {
				System.out.println("Ten san pham phai bao gom tu 6 den 50 ky tu, hay nhap lai! ");
			}else{
				for(Product prd: Data.listPro) {
					if(prd.productName.equals(productName)) {
						System.out.println("Ten san pham da ton tai ");
					}else {
						setProductName(productName);
						break;
					}
				}
				break;
			}
		}
		
		while(true) {
			System.out.println("Nhap tieu de san pham: ");
			title = nhap.nextLine();
			if(title.length()<6 || title.length()> 30) {
				System.out.println("Tieu de san pham phai bao gom 6 den 30 ky tu, hay nhap lai");
			}else {
				setTitle(title);
				break;
			}
		}
		
		while(true) {
			System.out.println("Nhap gia nhap san pham: ");
			importPrice = Float.parseFloat(nhap.nextLine());
			if(importPrice<1) {
				System.out.println("Gia nhap phai la so thuc lon hon 0, hay nhap lai!");
			}else {
				setImportPrice(importPrice);
				break;
			}
		}
		
		while(true) {
			System.out.println("Nhap gia ban san pham: ");
			exportPrice = Float.parseFloat(nhap.nextLine());
			if(exportPrice < (importPrice+(exportPrice*MIN_INTEREST_RATE))){
				System.out.println("Gia ban phai la so thuc lon hon gia nhap+lai, hay nhap lai!");
			}else {
				setExportPrice(exportPrice);
				break;
			}
		}
		
		while(true) {
			System.out.println("Nhap mo ta san pham: ");
			descriptions = nhap.nextLine();
			if(descriptions.length()<1) {
				System.out.println("Khong duoc de trong mo ta, hay nhap lai!");
			}else {
				setDescriptions(descriptions);
				break;
			}
		}
		
		System.out.print("Nhap trang thai san pham: ");
		productStatus = Boolean.parseBoolean(nhap.nextLine());
		while(true) {
			System.out.print("Nhap danh muc san pham: ");
			int keyCategory = Integer.parseInt(nhap.nextLine());
			Categories c = null;
			for(Categories ctg1 : Data.listCate) {
				if(ctg1.getCatalogId() == keyCategory) {
					c = ctg1;
				}
			}
			this.catalog = c;
			break;
		}
		
	}
	//Khoi tao phuong thuc displayData()
	@Override
	public void displayData() {	
		
		System.out.println("+------------------------+------------------------------+----------------------------------+----------------+");
		System.out.println("| Ma SP |  Ten SP  |    Title   | Gia Nhap  | Gia Ban | Loi Nhuan |    Mo ta   | Danh Muc SP | Trang Thai SP");
		System.out.printf("| %s  | %s  |  %s   | %8.1f  | %7.1f |  %8.1f |",this.productId,this.productName,this.title,this.importPrice,this.exportPrice,this.profit);
		System.out.printf("  %8s  |  %10s  | \n",this.descriptions,this.catalog.getCatalogName()+" | "+(this.isProductStatus()  ? "Hoat Dong" : "Khong hoat đong"));
		System.out.println("+-----------------------+------------------------------+-----------------------------+");
		
	}

	@Override
	public void calProfit() {
		profit = exportPrice - importPrice;
		System.out.println("\tLoi nhuan cua san pham "+productName+": " +profit);
		
	}
	public static void main(String[] args) {
		Scanner nhap = new Scanner (System.in);
		System.out.println("Nhap so phan tu cho san pham: ");
		int m = Integer.parseInt(nhap.nextLine());
		for (int i = 0; i < m; i++) {
			System.out.println("Nhap thong tin san pham thu "+(i+1));
			Product pro = new Product();
			pro.inputData();
			Data.listPro.add(pro);
		}
		Data.listPro.forEach(t->t.displayData());
	}

}
