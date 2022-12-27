package lop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.sun.tools.javac.Main;

public class ShopManagement {
	public static FileReadWrite fileReadWrite = new FileReadWrite();
	public static void main(String[] args) {
		ShopManagement shopManagement = new ShopManagement();
		
		try {
			System.out.println("+------+----------------------+----------------------+------+----------------------+");
			System.out.println("|Ma DM |     Ten Danh Muc     |   Mo Ta Danh Muc     |DM Cha|    Trang Thai DM     |");
			System.out.println("+------+----------------------+----------------------+------+----------------------+");
			fileReadWrite.readCategory();
			fileReadWrite.readProduct();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner nhap = new Scanner(System.in);
		
	
		while(true) {
			System.out.println("********************MENU******************");
			System.out.println("1.Quan ly danh muc");
			System.out.println("2.Quan ly san pham");
			System.out.println("3.Thoat");
			int choice = 0;
			System.out.println("Nhap lua chon cua ban: ");
			try {
				 choice = Integer.parseInt(nhap.nextLine());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch(choice) {
			case 1 : 
				shopManagement.quanLyDanhMuc();
				break;
			case 2: 
				shopManagement.quanLySanPham();
				break;
			case 3:
				System.exit(0);
			default: 
				System.out.println("Phai nhap lua chon tu 1 den 3,hay nhap lai!");
				
			}
		}
	}
	private  void quanLyDanhMuc() {
		Scanner nhap = new Scanner (System.in);
		while (true) {
			System.out.println("************QUAN LY DANH MUC*************");
			System.out.println("1.Danh sach danh muc");
			System.out.println("2.Them danh muc");
			System.out.println("3.Xoa danh muc");
			System.out.println("4.Tim kiem danh muc");
			System.out.println("5.Quay lai");
			int choice = 0;
			System.out.println("Nhap lua chon cua ban: ");
			try {
				 choice = Integer.parseInt(nhap.nextLine());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch(choice) {
			case 1: 
				danhSachDanhMuc();
				break;
			case 2:
				// them danh muc
				System.out.println("Nhap so phan tu cho danh muc: ");
				int n = Integer.parseInt(nhap.nextLine());
				for (int i = 0; i < n; i++) {
					System.out.println("Nhap thong tin danh muc thu "+(i+1));
					Categories ca = new Categories();
					ca.inputData();
					Data.listCate.add(ca);
					
				}
				Data.listCate.forEach(t->t.displayData());
				
				break;
			case 3: 
				// xoa danh muc
				System.out.println("Nhap ma danh muc can xoa: ");
				try {
					int key1 = Integer.parseInt(nhap.nextLine());
					for (Categories categories : Data.listCate) {
						if(categories.getCatalogId() == key1) {
							Data.listCate.remove(categories);
							System.out.println("Da xoa danh muc thanh cong!");
						}else {
							System.out.println("Ma danh muc can xoa khong ton tai!");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				Data.listCate.forEach(t->t.displayData());
				break;
			case 4: 
				//tim kiem danh muc
				System.out.println("Nhap ten danh muc can tim kiem: ");
				String key = nhap.nextLine();
				for (Categories categories : Data.listCate) {
					if(categories.getCatalogName().toLowerCase().equals(key.toLowerCase())) {
						categories.displayData();
					}else {
						System.out.println("Ten danh muc khong ton tai");
					}
				}
				break;
			case 5:
				fileReadWrite.writeCategoy();
				return;
				
			}
		}
	}
	private  void quanLySanPham() {
		Scanner nhap = new Scanner (System.in);
		boolean bl = true;
		
		while(bl) {
			System.out.println("**********QUAN LY SAN PHAM************");
			System.out.println("1.Them san pham moi ");
			System.out.println("2.Tinh loi nhuan san pham ");
			System.out.println("3.Hien thi thong tin san pham ");
			System.out.println("4.Sap xep san pham ");
			System.out.println("5.Cap nhat thong tin cho san pham ");
			System.out.println("6.Cap nhat trang thai san pham ");
			System.out.println("7.Quay lai ");
			int choice = 0;
			System.out.println("Nhap lua chon cua ban: ");
			try {
				 choice = Integer.parseInt(nhap.nextLine());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch (choice) {
			case 1:
				//them san pham moi
				System.out.println("Nhap so san pham ban muon them: ");
				int n = Integer.parseInt(nhap.nextLine());
				for (int i = 0; i < n; i++) {
					System.out.println("Nhap thong tin san pham thu "+(i+1));
					Product pro = new Product();
					pro.inputData();
					Data.listPro.add(pro);
					
				}
				fileReadWrite.writeProduct();
				break;
			case 2:
				//loi nhuan
				System.out.println("Loi nhuan tat ca san pham dang quan ly: ");
				for (Product product : Data.listPro) {
					product.calProfit();
				}		
				break;
			case 3:
				thongTinSanPham();
				break;
			case 4:
				sapXepSanPham();
				break;
			case 5:
				//cap nhat san pham theo ma
				System.out.println("Nhap ma san pham ban muon cap nhat: ");
				String maSP = nhap.nextLine();
				for (Product product : Data.listPro) {
					if(product.getProductId().equals(maSP)) {
						System.out.println("Nhap ten san pham: ");
						product.setProductName(nhap.nextLine());
						System.out.println("Nhap tieu de san pham: ");
						product.setTitle(nhap.nextLine());
						System.out.println("Nhap gia nhap san pham: ");
						product.setImportPrice(Float.parseFloat(nhap.nextLine()));
						System.out.println("Nhap gia ban san pham: ");
						product.setExportPrice(Float.parseFloat(nhap.nextLine()));
						System.out.println("Nhap mo ta san pham: ");
						product.setDescriptions(nhap.nextLine());
						System.out.println("\nCap nhat thong tin san pham thanh cong!");
					}else {
						System.out.println("Ma san pham vua nhap khong ton tai!");
					}
				}
				break;
			case 6:
				System.out.println("Nhap ma san pham can cap nhat trang thai: ");
				String key2 = nhap.nextLine();
				for (Product product : Data.listPro) {
					if(product.getProductId().toLowerCase().equals(key2.toLowerCase())) {
						product.setProductStatus(!product.isProductStatus());
						System.out.println("Cap nhat trang thai san pham thanh cong!");
					}else {
						System.out.println("Ma san pham vua nhap khong ton tai!");
					}
				}
				break;
			case 7:
				bl = false;
				fileReadWrite.writeProduct();
				break;

			default:
				System.out.println("Nhap lua chon sai, hay nhap lai lua chon tu 1-7");
				break;
			}
		}
	}
	private void danhSachDanhMuc() {
		Scanner nhap = new Scanner (System.in);
		boolean bl = true;
		while(bl) {
			System.out.println("***********DANH SACH DANH MUC*********");
			System.out.println("1.Danh sach cay danh muc");
			System.out.println("2.Thong tin chi tiet danh muc");
			System.out.println("3.Quay lai");
			int choice = 0;
			System.out.println("Nhap lua chon cua ban: ");
			try {
				 choice = Integer.parseInt(nhap.nextLine());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch(choice) {
			case 1:
				System.out.println("\nCay danh muc: ");
				treeCategory(0);
				break;
			case 2:
				System.out.println("Nhap ten danh muc can hien thi thong tin chi tiet: ");
				String key = nhap.nextLine();
				System.out.println("+------+----------------------+----------------------+------+----------------------+");
				System.out.println("|Ma DM |     Ten Danh Muc     |   Mo Ta Danh Muc     |DM Cha|    Trang Thai DM     |");
				System.out.println("+------+----------------------+----------------------+------+----------------------+");
				List<Categories> lstCtg = Data.listCate.stream().filter(x->x.getCatalogName().contains(key)).toList();
				if(lstCtg.isEmpty()) {
					System.out.println("Ten danh muc "+key+" khong ton tai");
				}else {
					lstCtg.forEach(x->x.displayData());
					break;
				}
				break;
			case 3:
				bl = false;
				break;
			}
		}
	}
	
	private void treeCategory(int parenId) {
		
		List<Categories> lstLV1 = Data.listCate.stream().filter(c -> c.getParentId() == 0)
				.collect(Collectors.toList());
		for (int i = 1; i <= lstLV1.size(); i++) {

			Categories ctCategories = lstLV1.get(i - 1);
			System.out.println(i + ". " + ctCategories.getCatalogName());
			List<Categories> lstLv2 = Data.listCate.stream()
					.filter(lv2 -> lv2.getParentId() == ctCategories.getCatalogId()).collect(Collectors.toList());

			for (int j = 1; j <= lstLv2.size(); j++) {

				Categories ctLv2 = lstLv2.get(j - 1);
				System.out.println("\t" + i + "." + j + "." + ctLv2.getCatalogName());
				List<Categories> lstLv3 = Data.listCate.stream()
						.filter(lv3 -> lv3.getParentId() == ctLv2.getCatalogId()).collect(Collectors.toList());

				for (int k = 1; k <= lstLv3.size(); k++) {
					Categories ctlv3 = lstLv3.get(k - 1);
					System.out.println("\t \t" + i + "." + j + "." + k + "." + ctlv3.getCatalogName());
				}
			}
		}
	}
	private void thongTinSanPham() {
		Scanner nhap = new Scanner (System.in);
		boolean bl = true;
		while(bl) {
			System.out.println("***********THONG TIN SAN PHAM ***********");
			System.out.println("1.Hien thi san pham theo danh muc ");
			System.out.println("2.Hien thi chi tiet san pham ");
			System.out.println("3.Quay lai");
			int choice = 0;
			System.out.println("Nhap lua chon cua ban: ");
			try {
				 choice = Integer.parseInt(nhap.nextLine());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch (choice) {
			case 1:
				System.out.print("Nhap ma danh muc san pham: ");
				int keyCategory = Integer.parseInt(nhap.nextLine());
				for(Product categories : Data.listPro) {
					if(categories.getCatalog().getCatalogId() == keyCategory) {
						categories.displayData();
						
						break;
					}else {
						System.out.println("Danh muc khong ton tai");
					}
				}
				
				break;
			case 2:
				System.out.println("Nhap ten san pham ban muon xem: ");
				String key = nhap.nextLine();
				for (Product product : Data.listPro) {
					
					
					if(product.getProductName().contains(key)) {
						product.displayData();
						break;
					}
						
					else {
						System.out.println("San pham vua nhap khong ton tai: "+key);
					}
					break;
				}		
				break;
			case 3:
				bl = false;
				break;
			default:
				System.out.println("Nhap lua chon sai, hay nhap lai lua chon tu 1 -3: ");
				break;
			}
		}
	}
	 void sapXepSanPham() {
		boolean bl = true;
		Scanner nhap = new Scanner(System.in);
		while(bl) {
			System.out.println("**************SAP XEP SAN PHAM*************");
			System.out.println("1.Sap xep san pham theo gia ban tang dan ");
			System.out.println("2.Sap xep san pham theo loi nhuan giam dan");
			System.out.println("3.Quay lai ");
			int choice = 0;
			System.out.println("Nhap lua chon cua ban: ");
			try {
				 choice = Integer.parseInt(nhap.nextLine());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch (choice) {
			case 1:
				//colection
				for(int i = 0 ; i< Data.listPro.size()- 1; i++) {
					for(int j = i +1; j< Data.listPro.size(); j++) {
						
							if(Data.listPro.get(i).getExportPrice() > Data.listPro.get(j).getExportPrice() ) {
								Product temp = Data.listPro.get(i);
								// doi vi  tri j vao i
								Data.listPro.set(i ,Data.listPro.get(j));
								//luu vi tri j vao i
								Data.listPro.set(j,temp );
							}
						
						}

				}
				Data.listPro.forEach(x -> x.displayData());
				break;
			case 2: 
				Data.listPro.sort((x,y)-> Float.compare(y.getProfit(), x.getProfit()));
				Data.listPro.forEach(x -> x.displayData());
				break;
			case 3:
				bl = false;
				break;
			default:
				System.out.println("Nhap lua chon sai, hay nhap lai lua chon tu 1 -3: ");
				break;
			}
		}
	}
	
	
	}