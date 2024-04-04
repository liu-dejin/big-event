<script setup>
import {
  Edit,
  Delete,
  Plus
} from '@element-plus/icons-vue'
import {reactive, ref} from 'vue'
import {deleteArticleService, getArticleService, postArticleService, putArticleService} from "@/api/article.js";
import {getCategoryService} from "@/api/category.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {useTokenStore} from "@/stores/token.js";

//文章分类数据模型
const category = ref([])
const total = ref(null)
const articles = ref([])
const query = reactive({
  pageNum: 1,
  pageSize: 3,
  categoryId: null,
  state: null
})
/**
 * 文章分类数据模型 List
 * @returns {Promise<void>}
 */
const categoryList = async () => {
  const categoryService = await getCategoryService();
  category.value = categoryService.data;
}
categoryList()
/**
 * 文章列表数据模型
 * @returns {Promise<void>}
 */
const articlesList = async () => {
  const res = await getArticleService(query);
  total.value = res.data.total
  articles.value = res.data.items;
}
articlesList()
/**
 * 当每页条数发生了变化，调用此函数
 * @param size
 * @returns {Promise<void>}
 */
const onSizeChange = async (size) => {
  query.pageSize = size
  await articlesList()
}
/**
 * 当前页码发生变化，调用此函数
 * @param num
 * @returns {Promise<void>}
 */
const onCurrentChange = async (num) => {
  query.pageNum = num
  await articlesList()
}
/**
 * 搜索文章
 * @returns {Promise<void>}
 */
const search = async () => {
  await articlesList()
}
/**
 * 重置查询条件
 * @returns {Promise<void>}
 */
const resetFrom = async () => {
  query.categoryId = null
  query.state = null
  await articlesList()
}
//控制抽屉是否显示
const visibleDrawer = ref(false)
//添加表单数据模型
let articleModel = reactive({
  title: '',
  categoryId: '',
  coverImg: '',
  content: '',
  state: ''
})
/**
 * 清空表单
 */
const articleForm = () => {
  articleModel.value = {
    title: '',
    categoryId: '',
    coverImg: '',
    content: '',
    state: ''
  }
}
const title = ref('')
/**
 * 公共复用
 * @param message
 * @returns {Promise<void>}
 */
const handleSuccess = async (message) => {
  ElMessage.success(message)
  visibleDrawer.value = false
  articleForm()
  await articlesList()
}
const tokenStore = useTokenStore();
const onUploadSuccess = async (res) => {
  console.log(res.message, 'img')
  articleModel.coverImg = res.message
}
/**
 * 添加文章 打开抽屉
 * @returns {Promise<void>}
 */
const insert = async () => {
  visibleDrawer.value = true
  title.value = '添加文章'
}
/**
 * 添加或修改文章
 * @param state
 * @returns {Promise<void>}
 */
const articlesBtn = async (state) => {
  if (title.value === '添加文章') {
    articleModel.state = state
    const res = await postArticleService(articleModel)
    await handleSuccess(res.message)
  }
  if (title.value === '修改文章') {
    articleModel.state = state
    const res = await putArticleService(articleModel);
    await handleSuccess(res.message)
  }
}
/**
 * 修改文章 打开抽屉
 * @returns {Promise<void>}
 */
const update = async (row) => {
  visibleDrawer.value = true
  title.value = '修改文章'
  articleModel = row
}
/**
 * 删除文章
 * @param id
 */
const deleteArticle = (id) => {
  ElMessageBox.confirm(
      '你确定要删除该文章分类吗?，该过程不可逆！',
      '警告',
      {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning',
      }
  )
      .then(async () => {
        const res = await deleteArticleService(id);
        ElMessage.success(res.message)
        await articlesList()
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '已取消',
        })
      })
}
</script>
<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>文章管理</span>
        <div class="extra">
          <el-button type="primary" @click="insert">添加文章</el-button>
        </div>
      </div>
    </template>
    <!-- 搜索表单 -->
    <el-form inline>
      <el-form-item label="文章分类：">
        <el-select placeholder="请选择" v-model="query.categoryId" style="width: 200px">
          <el-option
              v-for="c in category"
              :key="c.id"
              :label="c.categoryName"
              :value="c.id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="发布状态：">
        <el-select placeholder="请选择" v-model="query.state" style="width: 200px">
          <el-option label="已发布" value="已发布"></el-option>
          <el-option label="草稿" value="草稿"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">搜索</el-button>
        <el-button @click="resetFrom">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 文章列表 -->
    <el-table :data="articles" style="width: 100%">
      <el-table-column label="文章标题" width="400" prop="title"></el-table-column>
      <el-table-column label="分类" prop="categoryId"></el-table-column>
      <el-table-column label="发表时间" prop="createTime"></el-table-column>
      <el-table-column label="状态" prop="state"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="update(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="deleteArticle(row.id)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据"/>
      </template>
    </el-table>
    <!-- 分页条 -->
    <el-pagination
        v-model:current-page="query.pageNum"
        v-model:page-size="query.pageSize"
        :page-sizes="[3, 5 ,10, 15]"
        layout="jumper, total, sizes, prev, pager, next" background :total="total"
        @size-change="onSizeChange"
        @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end"/>
    <!-- 抽屉 -->
    <el-drawer v-model="visibleDrawer" :title="title" direction="rtl" size="50%">
      <!-- 添加文章表单 -->
      <el-form :model="articleModel" label-width="100px">
        <el-form-item label="文章标题">
          <el-input v-model="articleModel.title" placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item label="文章分类">
          <el-select placeholder="请选择" v-model="articleModel.categoryId">
            <el-option v-for="c in category" :key="c.id" :label="c.categoryName" :value="c.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="文章封面">
          <el-upload
              class="avatar-uploader"
              :auto-upload="true"
              :show-file-list="false"
              action="/api/upload"
              name="file"
              :headers="{'Authorization':tokenStore.token}"
              :on-success="onUploadSuccess"
          >
            <img v-if="articleModel.coverImg" :src="articleModel.coverImg" class="avatar"/>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="文章内容">
          <div class="editor">
            <quill-editor
                theme="snow"
                v-model:content="articleModel.content"
                contentType="html"
            >
            </quill-editor>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="articlesBtn('已发布')">发布</el-button>
          <el-button type="info" @click="articlesBtn('草稿')">草稿</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </el-card>
</template>
<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}

/* 抽屉样式 */
.avatar-uploader {
  :deep() {
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }

    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }

    .el-upload:hover {
      border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      text-align: center;
    }
  }
}

.editor {
  width: 100%;

  :deep(.ql-editor) {
    min-height: 200px;
  }
}
</style>
