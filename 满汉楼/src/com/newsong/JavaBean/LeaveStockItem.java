package com.newsong.JavaBean;

public class LeaveStockItem {
	private String leaveStockNo;
	private int matId;
	private int matQty;
	
	public LeaveStockItem() {
		// TODO Auto-generated constructor stub
	}

	
	
	public LeaveStockItem(String leaveStockNo, int matId, int matQty) {
		super();
		this.leaveStockNo = leaveStockNo;
		this.matId = matId;
		this.matQty = matQty;
	}



	public String getLeaveStockNo() {
		return leaveStockNo;
	}

	public void setLeaveStockNo(String leaveStockNo) {
		this.leaveStockNo = leaveStockNo;
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
	
	
}
