<!--
 * @Author: Lucky-Firstsnow firstsnow1119@163.com
 * @Date: 2024-04-04 11:01:18
 * @LastEditors: Lucky-Firstsnow firstsnow1119@163.com
 * @LastEditTime: 2024-04-04 11:05:30
 * @FilePath: \big-event-vue3\src\views\Index.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<script setup>
import {
  Management,
  Promotion,
  UserFilled,
  User,
  Crop,
  EditPen,
  SwitchButton,
  CaretBottom
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'
import {logoutService, userInfoService} from "@/api/user.js";
import userInfoStores from "@/stores/userInfo.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {useTokenStore} from "@/stores/token.js";
import {useRouter} from "vue-router";
import {deleteArticleService} from "@/api/article.js";

const infoStores = userInfoStores();
const tokenStore = useTokenStore();
const router = useRouter()
/**
 * 获取用户信息
 * @returns {Promise<void>}
 */
const getUserInfo = async () => {
  const res = await userInfoService();
  infoStores.setInfo(res.data);
}
getUserInfo()
/**
 * 登出
 * @param command
 */
const handleCommand = (command) => {
  if (command === 'logout') {
    ElMessageBox.confirm(
        '你确定要登出吗',
        '警告',
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning',
        }
    )
        .then(() => {
          const res = logoutService()
          ElMessage.success(res.message)
          tokenStore.removeToken()
          infoStores.removeInfo()
          router.push('/login')
        })
        .catch(() => {
          ElMessage({
            type: 'info',
            message: '已取消',
          })
        })
  } else {
    router.push(`/user/${command}`)
  }

}
</script>

<template>
  <el-container class="layout-container">
    <!-- 左侧菜单 -->
    <el-aside width="200px">
      <div class="el-aside__logo"></div>
      <el-menu active-text-color="#ffd04b"
               background-color="#232323"
               text-color="#fff"
               router
               default-active="/article/manage">
        <el-menu-item index="/article/category">
          <el-icon>
            <Management/>
          </el-icon>
          <span>文章分类</span>
        </el-menu-item>
        <el-menu-item index="/article/manage">
          <el-icon>
            <Promotion/>
          </el-icon>
          <span>文章管理</span>
        </el-menu-item>
        <el-sub-menu>
          <template #title>
            <el-icon>
              <UserFilled/>
            </el-icon>
            <span>个人中心</span>
          </template>
          <el-menu-item index="/user/info">
            <el-icon>
              <User/>
            </el-icon>
            <span>基本资料</span>
          </el-menu-item>
          <el-menu-item index="/user/avatar">
            <el-icon>
              <Crop/>
            </el-icon>
            <span>更换头像</span>
          </el-menu-item>
          <el-menu-item index="/user/password">
            <el-icon>
              <EditPen/>
            </el-icon>
            <span>重置密码</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <!-- 右侧主区域 -->
    <el-container>
      <!-- 头部区域 -->
      <el-header>
        <div>当前登录用户：<strong>{{ infoStores.info.nickname }}</strong></div>
        <!--        command事件触发会返回子类的command的值      -->
        <el-dropdown placement="bottom-end" @command="handleCommand">
                    <span class="el-dropdown__box">
                        <el-avatar :src="infoStores.info.userPic ? infoStores.info.userPic : avatar"/>
                        <el-icon>
                            <CaretBottom/>
                        </el-icon>
                    </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>
              <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>
              <el-dropdown-item command="password" :icon="EditPen">重置密码</el-dropdown-item>
              <el-dropdown-item command="logout" :icon="SwitchButton" @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>
      <!-- 中间区域 -->
      <el-main>
        <!--        <div style="width: 1290px; height: 570px;border: 1px solid red;">-->
        <!--          内容展示区-->
        <!--        </div>-->
        <RouterView/>
      </el-main>
      <!-- 底部区域 -->
      <el-footer>大事件 ©2023 Created by 黑马程序员</el-footer>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;

  .el-aside {
    background-color: #232323;

    &__logo {
      height: 120px;
      background: url('@/assets/logo.png') no-repeat center / 120px auto;
    }

    .el-menu {
      border-right: none;
    }
  }

  .el-header {
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;

    .el-dropdown__box {
      display: flex;
      align-items: center;

      .el-icon {
        color: #999;
        margin-left: 10px;
      }

      &:active,
      &:focus {
        outline: none;
      }
    }
  }

  .el-footer {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    color: #666;
  }
}
</style>
