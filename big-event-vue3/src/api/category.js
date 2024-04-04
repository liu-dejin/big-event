import request from "@/utils/request.js";

/**
 * 获取文章分类列表
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const getCategoryService = () => {
    return request.get(`/category`)
}

/**
 * 获取文章分类详情
 * @param id
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const getCategoryDetailService = (id) => {
    return request.get(`/category/detail?id=${id}`)
}

/**
 * 修改文章分类
 * @param params
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const putCategoryService = (params) => {
    return request.put(`/category`, params)
}

/**
 * 新增文章分类
 * @param params
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const postCategoryService = (params) => {
    return request.post(`/category`, params)
}

/**
 * 删除文章分类
 * @param id
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const deleteCategoryService = (id) => {
    return request.delete(`/category?id=${id}`)
}
