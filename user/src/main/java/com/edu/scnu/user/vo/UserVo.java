package com.edu.scnu.user.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserVo implements Serializable {
	
	private static final long serialVersionUID = 4348577661006240338L;

	private String id;
    
    private String bzId;

    private String username;
    
    @JsonIgnore
    private String password;

    private String nickname;

    private String avatar;

    private String loginIp;

    private Date loginTime;

    private Boolean isLock;
}
