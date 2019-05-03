package com.edu.scnu.user.dto;

public abstract class UserDTO {

	public abstract String getId();

	public abstract void setId(String id);

	public abstract String getBzId();

	public abstract void setBzId(String bzId);

	public abstract String getUsername();

	public abstract void setUsername(String username);

	public abstract String getPassword();

	public abstract void setPassword(String password);

	public abstract String getNickname();

	public abstract void setNickname(String nickname);

	public abstract String getAvatar();

	public abstract void setAvatar(String avatar);

	public abstract Boolean getIsLock();

	public abstract void setIsLock(Boolean isLock);

}
