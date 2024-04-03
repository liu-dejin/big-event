package org.bigevent.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import net.minidev.json.annotate.JsonIgnore;

/**
 * 用户表
 *
 * @TableName user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * ID
     */
    @NotNull(message = "ID不能为空")
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    @NotNull
    @Pattern(regexp = "^\\S{1,10}$", message = "昵称格式错误")
    private String nickname;

    /**
     * 邮箱
     */
    @NotNull
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * 头像
     */
    private String userPic;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

}
