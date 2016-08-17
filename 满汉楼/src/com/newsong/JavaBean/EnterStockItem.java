package com.newsong.JavaBean;

public class EnterStockItem {
	private String enterStockNo;
	private int matId;
	private String matName;
	

	private int matQty;
	
	public EnterStockItem() {
		// TODO Auto-generated constructor stub
	}

	
	
	public EnterStockItem(String enterStockNo, int matId, int matQty) {
		super();
		this.enterStockNo = enterStockNo;
		this.matId = matId;
		this.matQty = matQty;
	}



	public String getEnterStockNo() {
		return enterStockNo;
	}

	public void setEnterStockNo(String enterStockNo) {
		this.enterStockNo = enterStockNo;
	}

	public int getMatId() {
		return matId;
	}

	public void setMatId(int matId) {
		this.matId = matId;
	}

	public int getMatQty() {
		return matQty;
	}

	public void setMatQty(int matQty) {
		this.matQty = matQty;
	}
	public String getMatName() {
		return matName;
	}



	public void setMatName(String matName) {
		this.matName = matName;
	}
	
}
