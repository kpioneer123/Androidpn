package org.androidpn.server.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//建表操作
@Entity
@Table(name = "notification")
public class Notification implements Serializable{
	 private static final long serialVersionUID = 100000001L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    //apikey 不允许为空
    @Column(name ="api_key",length=64 )
    private String apiKey;
	//一个用户可以拥有多条离线消息
	@Column(name = "username",nullable = false, length = 64)
	private String username;
	@Column(name = "title",nullable = false, length = 64)
	private String title;
	@Column(name = "message",nullable = false, length = 1000)
	private String message;
	//允许为null
	@Column(name = "uri", length = 256)
	private String uri;
	
	@Column(name = "image_url", length = 256)
	private String imageUrl;
	

	@Column(name = "uuid",nullable = false, length = 64)
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
