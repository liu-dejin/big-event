import request from "@/utils/request";

/**
 * 上传图片文件
 * @param file
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const uploadFile = (file) => {
    return request.post(`/upload`, file)
}
