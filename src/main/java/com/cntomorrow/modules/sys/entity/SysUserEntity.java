

package com.cntomorrow.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户-业务系统拓展
 * @author speng
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@TableId
	private String id;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空")
	private String username;

	/**
	 * 用户真实姓名
	 */
//	@NotBlank(message="用户姓名不能为空")
	private String nickname;

	/**
	 * 密码
	 */
	@NotBlank(message="密码不能为空")
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	@NotBlank(message="邮箱不能为空")
	@Email(message="邮箱格式不正确")
	private String email;

	/**
	 * 用户部门
	 */
	private String orgId;

	/**
	 * 用户部门
	 */
	@TableField(exist = false)
	private String orgName;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;

	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;

	/**
	 * 创建者ID
	 */
	private String createUser;

	/**
	 * 创建时间
	 */
	private Date createTime;

}
