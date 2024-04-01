package org.bigevent.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName category
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类别名
     */
    private String categoryAlias;

    /**
     * 创建人ID
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

}
