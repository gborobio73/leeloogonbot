package com.leeloo.leeloogonbot.messageprocessors;

public class BotResponse {

	private String text;
	private String photo;
	private String video;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public boolean hasVideo() {
		return this.getVideo()!= null && this.getVideo().length()>0;
	}

	public boolean hasText() {
		return this.getText()!= null && this.getText().length()>0;
	}

	public boolean hasPhoto() {
		return this.getPhoto()!= null && this.getPhoto().length()>0;
	}
}
