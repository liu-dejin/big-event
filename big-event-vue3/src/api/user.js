import request from '@/utils/request'

/**
 * 注册
 * @param registerData
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const registerService = (registerData) => {
    const params = new URLSearchParams()
    for (let key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post('/user/register', params)
}

/**
 * login
 * @param registerData
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const loginService = (registerData) => {
    const params = new URLSearchParams()
    for (let key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post('/user/login', params)
}
/**
 * logout
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const logoutService = () => {
    return request.post(`/user/logout`)
}
/**
 * userInfo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const userInfoService = () => {
    return request.get(`/user/userInfo`)
}
/**
 * updateUserInfo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const updateUserInfoService = (params) => {
    return request.put(`/user/update`, params)
}
/**
 * updateUserAvatar
 * @param avatarUrl
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const updateUserAvatarService = (avatarUrl) => {
    return request.patch(`/user/updateAvatar?avatarUrl=${avatarUrl}`)
}
/**
 * resetUserPassword
 * @param params
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const resetUserPasswordService = (params) => {
    return request.patch(`/user/updatePwd`, params)
}
