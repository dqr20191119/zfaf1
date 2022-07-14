package com.cesgroup.cds.service.videoclient.bean;

public class LayoutReq {
	private int oldLayout = 0;
	private int newLayout = 0;
	private int width = 1024;
	private int height = 768;
	private int x = 0;
	private int y = 0;

	public int getOldLayout() {
		return oldLayout;
	}
	public void setOldLayout(int oldLayout) {
		this.oldLayout = oldLayout;
	}
	public int getNewLayout() {
		return newLayout;
	}
	public void setNewLayout(int newLayout) {
		this.newLayout = newLayout;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
