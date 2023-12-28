<template>
  <div class="product_category_list">
    <el-button type="primary" icon="el-icon-circle-plus" circle class="addProductCategory"
               @click="showProductCategoryAddForm"></el-button>
    <!--产品分类添加-->
    <el-dialog
        title="产品分类添加"
        :fullscreen="true"
        :visible="productCategoryAddVisible"
        width="30%"
        :before-close="addHandleClose">

      <span>
          <el-form ref="addForm" :model="productCategoryForm" label-width="80px" :rules="rules">
            <el-form-item label="分类名称" prop="productCategoryName">
              <el-input type="text" v-model="productCategoryForm.productCategoryName"></el-input>
            </el-form-item>

            <el-form-item label="logo" prop="productCategoryLogo">
<!--              <el-input type="text" v-model="productCategoryForm.productCategoryLogo"></el-input>-->
               <el-upload
                   class="upload-demo"
                   ref="uploadSingle"
                   :headers="header"
                   name="file"
                   :show-file-list="true"
                   :limit="1"
                   :action="singleUploadUrl"
                   :on-success="uploadSuccess">
                 <el-button size="small" type="primary">点击上传</el-button>
              </el-upload>
            </el-form-item>
            
          </el-form>
      </span>

      <span slot="footer" class="dialog-footer">
    <el-button @click="productCategoryAddVisible = false">取 消</el-button>
    <el-button type="primary" @click="addProductCategory">添加</el-button>
  </span>
    </el-dialog>

    <!--  产品分类修改-->
    <el-dialog
        :fullscreen="true"
        title="产品分类信息修改"
        :visible="productCategoryEditVisible"
        width="30%"
        :before-close="editHandleClose">

     <span>
          <el-form ref="addForm" :model="productCategoryForm" label-width="80px" :rules="rules">
            <el-form-item label="分类名称" prop="productCategoryName">
              <el-input type="text" v-model="productCategoryForm.productCategoryName"></el-input>
            </el-form-item>

            <el-form-item label="logo" prop="productCategoryLogo">
<!--              <el-input type="text" v-model="productCategoryForm.productCategoryLogo"></el-input>-->
               <el-upload
                   class="upload-demo"
                   ref="uploadSingle"
                   :headers="header"
                   name="file"
                   :limit="1"
                   :action="singleUploadUrl"
                   :on-success="uploadSuccess">
                 <el-button size="small" type="primary">点击上传</el-button>
              </el-upload>
            </el-form-item>

          </el-form>
      </span>

      <span slot="footer" class="dialog-footer">
    <el-button @click="productCategoryEditVisible = false">取消</el-button>
    <el-button type="primary" @click="editProductCategory">修改</el-button>
  </span>
    </el-dialog>


    <el-input v-model="productCategoryName" placeholder="请输入分类" @input="queryByName" class="queryProductCategory"></el-input>
    <el-divider></el-divider>

    <el-table
        :data="productCategoryList"
        height="700"
        style="width: 100%">
      <el-table-column
          label="产品分类id"
          width="200"
          align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.productCategoryId }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="产品分类名称"
          width="200"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.productCategoryName }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
          label="产品分类logo"
          width="200"
          align="center">
        <template slot-scope="scope">
          <!--          <div slot="reference" class="name-wrapper">-->
          <img :src="scope.row.productCategoryLogo" style="width: 30px; height: 30px">
          <!--          </div>-->
        </template>
      </el-table-column>


      <el-table-column
          label="创建时间"
          width="200"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.createTime }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="更新时间"
          width="200"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.updateTime }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="上架状态"
          width="200"
          align="center">
        <template slot-scope="scope">

          <el-switch
              :active-value="0"
              :inactive-value="1"
              @change="checkStatus(scope.row, $event)"
              v-model="scope.row.status"
              active-color="#13ce66"
              inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>


      <el-table-column
          label="操作"
          width="200"
          align="center">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">修改
          </el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <el-pagination
        @current-change="changeCurrentPageHandler"
        :current-page="pagination.currentPage"
        :page-size="pagination.pageSize"
        :page-count="pagination.totalPage"
        layout="prev, pager, next"
        :total="pagination.total">
    </el-pagination>
  </div>
</template>

<script>
import message from "element-ui/packages/message";

export default {
  name: "manage",
  data() {
    return {
      productCategoryEditVisible: false,
      productCategoryList: [],

      productCategoryName: "",

      singleUploadUrl: "https://nobibibi.top/back/web-file-generate/single",

      header: {
        token: localStorage.getItem("token")
      },
      productCategoryAddVisible: false,
      productCategoryForm: {},
      rules: {
        productCategoryName: [{required: true, message: '产品分类名不能为空', trigger: "blur"}],
        productCategoryLogo: [{required: true, message: '产品分类logo不能为空', trigger: "blur"}],
      },


      pagination: {
        // 每页多找个
        pageSize: 10,
        // 总页数
        totalPage: 0,
        // 获取页码
        activePage: 0,
        // 总条数
        total: 0,
        // 当前页
        currentPage: 1,
      },
    }
  },

  created() {
    window.document.title = "产品分类管理"
    this.changeCurrentPageHandler(this.pagination.currentPage)
  },

  methods: {

    // 获取数据
    changeCurrentPageHandler(currentPage) {
      this.httpRequest.get("back/productCategory/requestPageList?page=" + currentPage +
          "&limit=" + this.pagination.pageSize +
          "&productCategoryName=" + this.productCategoryName +
          "&orderFiled=product_category_id" +
          "&orderType=1")
          .then(response => {

            // console.log(response)
            this.pagination.currentPage = response.data.productCategoryPageList.currentPage
            this.pagination.pageSize = response.data.productCategoryPageList.pageSize
            this.pagination.total = response.data.productCategoryPageList.totalSize
            this.pagination.totalPage = response.data.productCategoryPageList.totalPage
            this.productCategoryList = response.data.productCategoryPageList.resultList
          })
    },

    // 查询数据
    queryByName() {
      this.changeCurrentPageHandler(1)
    },

    // 关闭添加产品分类对话框
    addHandleClose() {
      this.productCategoryAddVisible = false
    },

    // 打开添加产品分类对话框
    showProductCategoryAddForm() {
      this.productCategoryForm = {}
      this.productCategoryAddVisible = true
    },



    // 添加产品分类
    addProductCategory() {

      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.productCategoryForm.isDelete = 0
          this.productCategoryForm.status = 1
          // this.productCategoryForm.status = this.productCategoryForm.status ? 0 : 1
          // console.log(this.productCategoryForm)
          this.httpRequest.post("/back/productCategory/appendProductCategory", this.productCategoryForm).then(response => {
            this.changeCurrentPageHandler(1)
            this.productCategoryAddVisible = false
            this.productCategoryForm = {}
            this.$refs.uploadSingle.clearFiles()
          })
        } else {
          message.error("请完善数据")
        }
      })
    },



    // 打开编辑产品分类对话框
    handleEdit(index, data) {
      // console.log(data)
      this.productCategoryForm = data
      this.productCategoryEditVisible = true

    },

    // 关闭产品分类编辑对话框
    editHandleClose() {
      this.productCategoryEditVisible = false
    },


    // 编辑产品分类
    editProductCategory() {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.productCategoryForm.isDelete = 0
          // this.productCategoryForm.status = this.productCategoryForm.status ? 0 : 1
          // console.log(this.productCategoryForm)
          this.httpRequest.put("/back/productCategory/alterProductCategory", this.productCategoryForm).then(response => {
            this.changeCurrentPageHandler(this.pagination.currentPage)
            this.productCategoryEditVisible = false
            this.productCategoryForm = {}
            this.$refs.uploadSingle.clearFiles()
          })
        } else {
          message.error("请完善数据")
        }
      })
    },


    // 删除产品分类
    handleDelete(index, data) {

      this.$confirm("确定要删除该产品分类吗?", {
        type: "warning",
        beforeClose: async (action, instance, done) => {
          if (action === 'confirm') {
            // console.log("群定了")
            this.httpRequest.delete("back/productCategory/removeProductCategory/" + data.productCategoryId).then(response => {
              this.changeCurrentPageHandler(1)
            })
            done()
          } else {
            // console.log("取消了")
            done()
          }
        }
      })

    },

    // 文件上传成功钩子
    uploadSuccess(response) {
      if (response.code >= 10000 && response.code < 20000) {
        // console.log(response)
        this.productCategoryForm.productCategoryLogo = response.url
        this.$message.success(response.msg)
      } else {
        this.$message.error(response.msg)
      }
    },

    // 修改产品分类上架状态
    checkStatus(productCategory, value) {

      this.httpRequest.put("back/productCategory/alterProductCategoryStatus", {
        "productCategoryId": productCategory.productCategoryId,
        "status": value
      })

    }


  }


}
</script>

<style scoped>
.addProductCategory {
  margin-left: 20px;
}

.queryProductCategory {
  width: 15%;
  position: absolute;
  right: 50px;
}

.queryButton {
  position: absolute;
  right: 400px;
}

.datatable {
  position: relative;
  box-sizing: border-box;
  -webkit-box-flex: 1;
  width: 100%;
  max-width: 100%;
  font-size: 14px;
  color: rgb(96, 98, 102);
  overflow: hidden;
  flex: 1 1 0%;
}

.datatable td, .datatable th {
  padding: 12px 0;
  min-width: 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  text-overflow: ellipsis;
  vertical-align: middle;
  position: relative;
  text-align: left;
}


</style>