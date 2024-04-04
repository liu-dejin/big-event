import {defineStore} from 'pinia'
import {ref} from "vue";

const userInfoStores = defineStore(
    'userInfo',
    () => {
        const info = ref({})

        const setInfo = (newInfo) => {
            info.value = newInfo
        }

        const removeInfo = () => {
            info.value = {}
        }

        return {
            info,
            setInfo,
            removeInfo
        }
    },
    {
        // 开启数据持久化
        persist: true
    }
)
export default userInfoStores
