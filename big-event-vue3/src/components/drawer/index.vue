<!--<script setup>-->
<!--import {Plus} from '@element-plus/icons-vue'-->
<!--import {reactive, ref} from "vue";-->
<!--//控制抽屉是否显示-->
<!--// const visibleDrawer = ref(false)-->
<!--//添加表单数据模型-->
<!--const articleModel = reactive({-->
<!--  title: '',-->
<!--  categoryId: '',-->
<!--  coverImg: '',-->
<!--  content: '',-->
<!--  state: ''-->
<!--})-->
<!--defineProps({-->
<!--  category: {-->
<!--    type: Array,-->
<!--    default: () => []-->
<!--  },-->
<!--  visibleDrawer: {-->
<!--    type: Boolean,-->
<!--    default: false-->
<!--  }-->
<!--})-->
<!--</script>-->

<!--<template>-->
<!--  &lt;!&ndash; 抽屉 &ndash;&gt;-->
<!--  <el-drawer v-model="visibleDrawer" title="添加文章" direction="rtl" size="50%">-->
<!--    &lt;!&ndash; 添加文章表单 &ndash;&gt;-->
<!--    <el-form :model="articleModel" label-width="100px">-->
<!--      <el-form-item label="文章标题">-->
<!--        <el-input v-model="articleModel.title" placeholder="请输入标题"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="文章分类">-->
<!--        <el-select placeholder="请选择" v-model="articleModel.categoryId">-->
<!--          <el-option v-for="c in category" :key="c.id" :label="c.categoryName" :value="c.id">-->
<!--          </el-option>-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="文章封面">-->

<!--        <el-upload class="avatar-uploader" :auto-upload="false" :show-file-list="false">-->
<!--          <img v-if="articleModel.coverImg" :src="articleModel.coverImg" class="avatar"/>-->
<!--          <el-icon v-else class="avatar-uploader-icon">-->
<!--            <Plus/>-->
<!--          </el-icon>-->
<!--        </el-upload>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="文章内容">-->
<!--        <div class="editor">富文本编辑器</div>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary">发布</el-button>-->
<!--        <el-button type="info">草稿</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
<!--  </el-drawer>-->
<!--</template>-->

<!--<style scoped lang="scss">-->
<!--/* 抽屉样式 */-->
<!--.avatar-uploader {-->
<!--  :deep() {-->
<!--    .avatar {-->
<!--      width: 178px;-->
<!--      height: 178px;-->
<!--      display: block;-->
<!--    }-->

<!--    .el-upload {-->
<!--      border: 1px dashed var(&#45;&#45;el-border-color);-->
<!--      border-radius: 6px;-->
<!--      cursor: pointer;-->
<!--      position: relative;-->
<!--      overflow: hidden;-->
<!--      transition: var(&#45;&#45;el-transition-duration-fast);-->
<!--    }-->

<!--    .el-upload:hover {-->
<!--      border-color: var(&#45;&#45;el-color-primary);-->
<!--    }-->

<!--    .el-icon.avatar-uploader-icon {-->
<!--      font-size: 28px;-->
<!--      color: #8c939d;-->
<!--      width: 178px;-->
<!--      height: 178px;-->
<!--      text-align: center;-->
<!--    }-->
<!--  }-->
<!--}-->

<!--.editor {-->
<!--  width: 100%;-->

<!--  :deep(.ql-editor) {-->
<!--    min-height: 200px;-->
<!--  }-->
<!--}-->
<!--</style>-->
