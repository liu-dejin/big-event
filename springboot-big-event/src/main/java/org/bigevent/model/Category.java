package org.bigevent.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName category
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = {update.class})
    private Integer id;

    /**
     * 分类名称
     */
    @NotEmpty(message = "分类名称不能为空")
    @Pattern(regexp = "^\\S{1,10}$")
    private String categoryName;

    /**
     * 分类别名
     */
    @NotEmpty(message = "分类别名不能为空")
    private String categoryAlias;

    /**
     * 创建人ID
     */
    private Integer createUser;

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

    @Serial
    private static final long serialVersionUID = 1L;

    public interface insert extends Default {
    }

    public interface update extends Default {
    }
}
