package org.bigevent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页对象
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {

    private Long total;

    private List<T> items;
}
