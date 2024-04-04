<script setup>
import {reactive, ref} from 'vue'
import {ElMessage} from "element-plus";
import {resetUserPasswordService} from "@/api/user.js";
import {useRouter} from "vue-router";

const router = useRouter();
const userInfo = reactive({
  old_pwd: '',
  new_pwd: '',
  re_pwd: ''
})
//自定义确认密码的校验函数
const rePasswordValid = (rule, value, callback) => {
  if (value == null || value === '') {
    return callback(new Error('请再次确认密码'))
  }
  if (userInfo.new_pwd !== value) {
    return callback(new Error('两次输入密码不一致'))
  }
}
const rules = {
  old_pwd: [
    {required: true, message: '请输入旧密码', trigger: 'blur'},
    {
      pattern: /^\S{2,10}$/,
      message: '旧密码必须是2-10位的非空字符串',
      trigger: 'blur'
    }
  ],
  new_pwd: [
    {required: true, message: '请输入新密码', trigger: 'blur'},
    {
      pattern: /^\S{2,10}$/,
      message: '新密码必须是2-10位的非空字符串',
      trigger: 'blur'
    }
  ],
  re_pwd: [
    {validator: rePasswordValid, trigger: 'blur'}
  ],
}
/**
 * userPassword
 * 修改用户密码
 * @returns {Promise<void>}
 */
const userPassword = async () => {
  const res = await resetUserPasswordService(userInfo)
  ElMessage.success(res.message)
  await router.push('/login')
}
</script>
<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>修改密码</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <el-form :model="userInfo" :rules="rules" label-width="100px" size="large">
          <el-form-item label="旧密码" prop="old_pwd">
            <el-input v-model="userInfo.old_pwd"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="new_pwd">
            <el-input v-model="userInfo.new_pwd"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="re_pwd">
            <el-input v-model="userInfo.re_pwd"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="userPassword">提交修改</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>
