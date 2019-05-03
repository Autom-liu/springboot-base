package com.edu.scnu.test.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends com.edu.scnu.user.dto.UserDTO {
	
    private String id;

    private String bzId;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private Boolean isLock;

}
