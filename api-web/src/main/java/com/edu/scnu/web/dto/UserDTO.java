package com.edu.scnu.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 之所以在这里新建两个DTO的目的，是为了最大化分离web和service之间的关系。
 * web和service都需要使用DTO相同字段，但它们却有不同的处理业务，如web需要做参数校验。
 * 将service作为抽象类接口，在web中具体实现
 * 但这也会造成扩展性变差。
 * 
 * 一个可以提升扩展性的办法是：
 * 在service层和web层一样定义DTO接口，可以不用写抽象方法，仍然可以使用lombok
 * 但是service必须是抽象类，web继承它。
 * web的DTO可以做自己处理逻辑，不影响service的DTO。
 * 当需求要求新增字段时，两个DTO都新增一个字段即可（甚至service中的DTO忘了改也没关系，但最好还是改哦）
 * @author Autom
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends com.edu.scnu.user.dto.UserDTO {
	
	@Null(message = "无需id", groups = {addUserAPI.class})
    private String id;
	
	@NotBlank(message = "用户编号不能为空", groups = {addUserAPI.class})
    private String bzId;

	@NotBlank(message = "用户名不能为空", groups = {addUserAPI.class})
    private String username;

	@NotBlank(message = "密码不能为空", groups = {addUserAPI.class})
    private String password;

	@NotBlank(message = "用户昵称不能为空", groups = {addUserAPI.class})
    private String nickname;
	
	private MultipartFile avatarFile;

    private String avatar;
    
    private Integer age;
    
    private Double price;

    private Boolean isLock;

    public static interface addUserAPI {}

}
