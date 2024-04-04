import request from "@/utils/request";

/**
 * 获取文章列表
 * @param params
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const getArticleService = (params) => {
    return request.get(`/article`, {params: params})
}

/**
 * 获取文章详情
 * @param id
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const getArticleDetailService = (id) => {
    return request.get(`/article/detail?id=${id}`)
}

/**
 * 修改文章
 * @param params
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const putArticleService = (params) => {
    return request.put(`/article`, params)
}

/**
 * 新增文章
 * @param params
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const postArticleService = (params) => {
    return request.post(`/article`, params)
}

/**
 * 删除文章
 * @param id
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const deleteArticleService = (id) => {
    return request.delete(`/article?id=${id}`)
}
