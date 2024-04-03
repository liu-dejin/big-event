package org.bigevent.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bigevent.anno.State;
import org.hibernate.validator.constraints.URL;

/**
 * @TableName article
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    /**
     * ID
     */
    @NotNull(groups = update.class, message = "文章ID不能为空")
    private Integer id;

    /**
     * 文章标题
     */
    @NotEmpty(message = "文章标题不能为空")
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;

    /**
     * 文章内容
     */
    @NotEmpty(message = "文章内容不能为空")
    private String content;

    /**
     * 文章封面
     */
    @NotEmpty(message = "文章封面不能为空")
    @URL
    private String coverImg;

    /**
     * 文章状态: 只能是[已发布] 或者 [草稿]
     */
    @State(message = "文章状态只能是[已发布] 或者 [草稿]")
    private String state;

    /**
     * 文章分类ID
     */
    @NotNull(message = "文章分类不能为空")
    private Integer categoryId;

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

    private static final long serialVersionUID = 1L;

    public interface update extends Default {

    }

    public interface insert extends Default {

    }
}
