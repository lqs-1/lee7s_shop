<template>
  <div class="product_category_list">
    <el-button type="primary" icon="el-icon-circle-plus" circle class="addProduct"
               @click="showProductAddForm"></el-button>
    <!--产品添加-->
    <el-dialog
        title="产品添加"
        :visible="productAddVisible"
        width="60%"
        :before-close="addHandleClose">

      <span>
          <el-form ref="addForm" :model="productForm" label-width="100px" :rules="rules">
            <el-form-item label="产品名称" prop="productName">
              <el-input type="text" v-model="productForm.productName"></el-input>
            </el-form-item>

            <el-form-item label="产品单价" prop="productPrice">
              <el-input type="text" v-model="productForm.productPrice"></el-input>
            </el-form-item>

             <el-form-item label="上架状态" prop="status">
              <el-switch
                  v-model="productForm.status"
                  :active-value="0"
                  :inactive-value="1"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
              </el-switch>
            </el-form-item>

            <el-form-item label="logo" prop="productLogo">
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

            <el-form-item label="发货方法" prop="productMethod">
              <el-select v-model="productForm.productMethod" placeholder="请选择发货方式">
                <el-option
                    v-for="item in productMethods"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="注意事项:分割" prop="productDesc">
              <el-input type="text" v-model="productForm.productDesc"></el-input>
            </el-form-item>


            <el-form-item label="警告事项:分割" prop="productWarn">
              <el-input type="text" v-model="productForm.productWarn"></el-input>
            </el-form-item>

            <el-form-item label="产品分类" prop="productCategoryId">
              <el-select v-model="productForm.productCategoryId" placeholder="请选择发货方式">
                <el-option
                    v-for="item in productCategoryIdAndNameOptions"
                    :key="item.productCategoryId"
                    :label="item.productCategoryName"
                    :value="item.productCategoryId">
                </el-option>
              </el-select>
            </el-form-item>

          </el-form>
      </span>

      <span slot="footer" class="dialog-footer">
    <el-button @click="productAddVisible = false">取 消</el-button>
    <el-button type="primary" @click="addProduct">添加</el-button>
  </span>
    </el-dialog>

    <!--  产品修改-->
    <el-dialog
        title="产品信息修改"
        :visible="productEditVisible"
        width="30%"
        :before-close="editHandleClose">

      <span>
          <el-form ref="addForm" :model="productForm" label-width="100px" :rules="rules">
            <el-form-item label="产品名称" prop="productName">
              <el-input type="text" v-model="productForm.productName"></el-input>
            </el-form-item>

            <el-form-item label="产品单价" prop="productPrice">
              <el-input type="text" v-model="productForm.productPrice"></el-input>
            </el-form-item>

            <el-form-item label="logo" prop="productLogo">
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

            <el-form-item label="发货方法" prop="productMethod">
              <el-select v-model="productForm.productMethod" placeholder="请选择发货方式">
                <el-option
                    v-for="item in productMethods"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="注意事项:分割" prop="productDesc">
              <el-input type="text" v-model="productForm.productDesc"></el-input>
            </el-form-item>


            <el-form-item label="警告事项:分割" prop="productWarn">
              <el-input type="text" v-model="productForm.productWarn"></el-input>
            </el-form-item>

            <el-form-item label="产品分类" prop="productCategoryId">
              <el-select v-model="productForm.productCategoryId" placeholder="请选择发货方式">
                <el-option
                    v-for="item in productCategoryIdAndNameOptions"
                    :key="item.productCategoryId"
                    :label="item.productCategoryName"
                    :value="item.productCategoryId">
                </el-option>
              </el-select>
            </el-form-item>

          </el-form>
      </span>

      <span slot="footer" class="dialog-footer">
    <el-button @click="productEditVisible = false">取消</el-button>
    <el-button type="primary" @click="editProduct">修改</el-button>
  </span>
    </el-dialog>


    <el-button type="success" class="queryButton" @click="queryByName">产品查询</el-button>
    <el-input v-model="productName" placeholder="请输入产品名" class="queryProduct"></el-input>
    <el-divider></el-divider>

    <el-table
        :data="productList"
        height="700"
        style="width: 100%">
      <el-table-column
          label="产品id"
          width="80"
          align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.productId }}</span>
        </template>
      </el-table-column>

      <el-table-column
          label="产品名称"
          width="160"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.productName }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="所属分类"
          width="150"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.productCategoryName }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="产品价格"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.productPrice }}</el-tag>
          </div>
        </template>
      </el-table-column>


      <el-table-column
          label="产品logo"
          width="100"
          align="center">
        <template slot-scope="scope">
          <!--          <div slot="reference" class="name-wrapper">-->
          <img :src="scope.row.productLogo" style="width: 30px; height: 30px">
          <!--          </div>-->
        </template>
      </el-table-column>

      <el-table-column
          label="发货方式"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.productMethod === 1 ? "手动" : "自动" }}</el-tag>
          </div>
        </template>
      </el-table-column>


      <el-table-column
          label="创建时间"
          width="160"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.createTime }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="更新时间"
          width="160"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.updateTime }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="上架状态"
          width="80"
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
          label="注意事项"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <!--            <el-tag size="">{{ scope.row.productDesc }}</el-tag>-->
            <el-button type="text" @click="showDesc(scope.row.productDesc)">点击查看</el-button>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="警告事项"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <!--            <el-tag size="">{{ scope.row.productWarn }}</el-tag>-->
            <el-button type="text" @click="showDesc(scope.row.productWarn)">点击查看</el-button>

          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="库存量"
          width="70"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.productStock }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="锁定库存"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.productLockStock }}</el-tag>
          </div>
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
      productCategoryIdAndNameOptions:[],
      productMethods:[{
        value: 0,
        label: '自动'
      }, {
        value: 1,
        label: '手动'
      }],
      productEditVisible: false,
      productList: [],

      productName: "",

      singleUploadUrl: "http://localhost:8888/back/web-file-generate/single",

      header: {
        token: localStorage.getItem("token")
      },
      productAddVisible: false,
      productForm: {},
      rules: {
        productName: [{required: true, message: '产品分类名不能为空', trigger: "blur"}],
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
    window.document.title = "产品管理"
    this.changeCurrentPageHandler(this.pagination.currentPage)
  },

  methods: {

    // 获取数据
    changeCurrentPageHandler(currentPage) {
      this.httpRequest.get("back/product/requestPageList?page=" + currentPage +
          "&limit=" + this.pagination.pageSize +
          "&productName=" + this.productName +
          "&orderFiled=product_id" +
          "&orderType=1")
          .then(response => {

            console.log(response)
            this.pagination.currentPage = response.data.productPageList.currentPage
            this.pagination.pageSize = response.data.productPageList.pageSize
            this.pagination.total = response.data.productPageList.totalSize
            this.pagination.totalPage = response.data.productPageList.totalPage
            this.productList = response.data.productPageList.resultList
          })
    },

    // 点击查看文本
    showDesc(text){
      this.$alert(text, '事项详情', {
        confirmButtonText: '关闭',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    },

    // 查询数据
    queryByName() {
      this.changeCurrentPageHandler(1)
    },

    // 关闭添加产品分类对话框
    addHandleClose() {
      this.productAddVisible = false
    },

    // 打开添加产品分类对话框
    showProductAddForm() {
      this.productForm = {}
      this.productAddVisible = true
      this.requestProductCategoryIdAndNameList()
    },

    // 获取可用产品分类id和名字
    requestProductCategoryIdAndNameList(){
      this.httpRequest.get("/back/productCategory/requestProductCategoryIdAndNameList")
          .then(response => {

            console.log(response)
            this.productCategoryIdAndNameOptions = response.data.productCategoryIdAndNameList

          })
    },


    // 添加产品分类
    addProduct() {

      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.productForm.isDelete = 0
          this.productForm.productStock = 0
          this.productForm.productLockStock = 0
          // this.productForm.status = this.productForm.status ? 0 : 1
          // console.log(this.productForm)

          this.httpRequest.post("/back/product/appendProduct", this.productForm).then(response => {
            this.changeCurrentPageHandler(1)
            this.productAddVisible = false
            this.productForm = {}
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
      this.productForm = data
      this.productEditVisible = true

      this.requestProductCategoryIdAndNameList()

    },

    // 关闭产品分类编辑对话框
    editHandleClose() {
      this.productEditVisible = false
    },


    // 编辑产品分类
    editProduct() {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.productForm.isDelete = 0
          // this.productForm.status = this.productForm.status ? 0 : 1
          // console.log(this.productForm)
          this.httpRequest.put("/back/product/alterProduct", this.productForm).then(response => {
            this.changeCurrentPageHandler(this.pagination.currentPage)
            this.productEditVisible = false
            this.productForm = {}
            this.$refs.uploadSingle.clearFiles()
          })
        } else {
          message.error("请完善数据")
        }
      })
    },


    // 删除产品分类
    handleDelete(index, data) {

      this.$confirm("确定要删除该产品吗?", {
        type: "warning",
        beforeClose: async (action, instance, done) => {
          if (action === 'confirm') {
            // console.log("群定了")
            this.httpRequest.delete("back/product/removeProduct/" + data.productId).then(response => {
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
        this.productForm.productLogo = response.url
        this.$message.success(response.msg)
      } else {
        this.$message.error(response.msg)
      }
    },

    // 修改产品分类上架状态
    checkStatus(product, value) {
      // value 参数表示开关的最新状态，true 表示开启，false 表示关闭
      // console.log('开关状态变化:', productCategory, "数据", value);


      this.httpRequest.put("back/product/alterProductStatus", {
        "productId": product.productId,
        "status": value
      })
    }


  }


}
</script>

<style scoped>
.addProduct {
  margin-left: 20px;
}

.queryProduct {
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