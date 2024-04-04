<script setup>
import {
  Edit,
  Delete
} from '@element-plus/icons-vue'
import {reactive, ref} from 'vue'
import {deleteCategoryService, getCategoryService, postCategoryService, putCategoryService} from "@/api/category.js";
import {ElMessage, ElMessageBox} from "element-plus";

/**
 * 分类列表
 * @type {Ref<UnwrapRef<*[]>>}
 */
const category = ref([])
/**
 * 控制添加分类弹窗
 * @type {Ref<UnwrapRef<boolean>>}
 */
const dialogVisible = ref(false)
const title = ref('')
/**
 * 获取数据列表
 * @returns {Promise<void>}
 */
const categoryList = async () => {
  const res = await getCategoryService()
  category.value = res.data
}

categoryList()

/**
 * 添加分类数据模型
 * @type {UnwrapNestedRefs<{categoryAlias: string, categoryName: string}>}
 */
const categoryModel = reactive({
  categoryName: '',
  categoryAlias: ''
})
/**
 * 添加分类表单校验
 * @type {{categoryAlias: [{trigger: string, message: string, required: boolean}], categoryName: [{trigger: string, message: string, required: boolean}]}}
 */
const rules = {
  categoryName: [
    {required: true, message: '请输入分类名称', trigger: 'blur'},
  ],
  categoryAlias: [
    {required: true, message: '请输入分类别名', trigger: 'blur'},
  ]
}
/**
 * 清空表单
 */
const categoryForm = () => {
  categoryModel.value = {
    categoryName: '',
    categoryAlias: ''
  }
}
/**
 * 公共复用
 * @param message
 * @returns {Promise<void>}
 */
const handleSuccess = async (message) => {
  ElMessage.success(message)
  dialogVisible.value = false
  categoryForm()
  await categoryList()
}
/**
 * 数据回显
 * @param row
 */
const showCategory = (row) => {
  title.value = '编辑分类'
  console.log(row, 'row')
  dialogVisible.value = true
  categoryModel.categoryName = row.categoryName
  categoryModel.categoryAlias = row.categoryAlias
  categoryModel.id = row.id
}
/**
 * 新增分类
 * @returns {Promise<void>}
 */
const insert = async () => {
  const res = await postCategoryService(categoryModel)
  await handleSuccess(res.message)
}
/**
 * 更新分类
 * @returns {Promise<void>}
 */
const update = async () => {
  const res = await putCategoryService(categoryModel)
  await handleSuccess(res.message)
}
/**
 * 分类点击事件
 * @returns {Promise<void>}
 */
const categoryClick = async () => {
  if (title.value === '添加分类') await insert()
  if (title.value === '编辑分类') await update()
}
/**
 * 删除分类
 * @param id
 * @returns {Promise<void>}
 */
const deleteCategory = async (id) => {
  ElMessageBox.confirm(
      '你确定要删除该文章分类吗?，该过程不可逆！',
      '警告',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(async () => {
        const res = await deleteCategoryService(id)
        ElMessage.success(res.message)
        await categoryList()
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
        <span>文章分类</span>
        <div class="extra">
          <el-button type="primary" @click="dialogVisible = true;title='添加分类'">添加分类</el-button>
        </div>
      </div>
    </template>
    <el-table :data="category" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"></el-table-column>
      <el-table-column label="分类名称" prop="categoryName"></el-table-column>
      <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="showCategory(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row.id)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据"/>
      </template>
    </el-table>
  </el-card>
  <!-- 添加分类弹窗 -->
  <el-dialog v-model="dialogVisible" :title="title" width="30%">
    <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
      <el-form-item label="分类名称" prop="categoryName">
        <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="分类别名" prop="categoryAlias">
        <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="categoryClick"> 确认 </el-button>
        </span>
    </template>
  </el-dialog>
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
</style>
