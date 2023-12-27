<template>
  <div class="product_category_list">
    <el-button type="primary" icon="el-icon-circle-plus" circle class="addGoods"
               @click="showGoodsAddForm"></el-button>
    <!--产品添加-->
    <el-dialog
        title="商品添加"
        :visible="goodsAddVisible"
        width="60%"
        :before-close="addHandleClose">

      <span>
          <el-form ref="addForm" :model="goodsForm" label-width="140px" :rules="rules">
            <el-form-item label="所属产品分类" prop="productCategoryId">
              <el-select  v-model="goodsForm.productCategoryId" @change="requestProductIdAndNameList" placeholder="请选择所属产品分类">
                <el-option
                    v-for="item in productCategoryIdAndNameOptions"
                    :key="item.productCategoryId"
                    :label="item.productCategoryName"
                    :value="item.productCategoryId">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="所属产品" prop="productId">
              <el-select ref="selectProduct" :clearable="true" v-model="goodsForm.productId" placeholder="请选择所属产品">
                <el-option
                    v-for="item in productIdAndNameOptions"
                    :key="item.productId"
                    :label="item.productName"
                    :value="item.productId">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="商品详情" prop="goodsDetail">
              <el-input type="textarea" v-model="goodsForm.goodsDetail"></el-input>
            </el-form-item>
          </el-form>
      </span>

      <span slot="footer" class="dialog-footer">
    <el-button @click="goodsAddVisible = false">取 消</el-button>
    <el-button type="primary" @click="addGoods">添加</el-button>
  </span>
    </el-dialog>

    <!--  产品修改-->
    <el-dialog
        title="产品信息修改"
        :visible="goodsEditVisible"
        width="60%"
        :before-close="editHandleClose">

      <span>
          <el-form ref="addForm" :model="goodsForm" label-width="140px" :rules="rules">
            <el-form-item label="所属产品分类" prop="productCategoryId">
              <el-select  v-model="goodsForm.productCategoryId" @change="requestProductIdAndNameList" placeholder="请选择所属产品分类">
                <el-option
                    v-for="item in productCategoryIdAndNameOptions"
                    :key="item.productCategoryId"
                    :label="item.productCategoryName"
                    :value="item.productCategoryId">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="所属产品" prop="productId">
              <el-select ref="selectProduct" :clearable="true" v-model="goodsForm.productId" placeholder="请选择所属产品">
                <el-option
                    v-for="item in productIdAndNameOptions"
                    :key="item.productId"
                    :label="item.productName"
                    :value="item.productId">
                </el-option>
              </el-select>
            </el-form-item>
"
"            <el-form-item label="商品详情" prop="goodsDetail">
              <el-input type="textarea" v-model="goodsForm.goodsDetail"></el-input>
            </el-form-item>
"
"          </el-form>
      </span>

      <span slot="footer" class="dialog-footer">
    <el-button @click="goodsEditVisible = false">取消</el-button>
    <el-button type="primary" @click="editProduct">修改</el-button>
  </span>
    </el-dialog>

    <el-select v-model="goodsForm.productCategoryId" placeholder="请选择产品分类" style="float: right" @change="requestProductIdAndNameList">
      <el-option
          v-for="item in productCategoryIdAndNameOptions"
          :key="item.productCategoryId"
          :label="item.productCategoryName"
          :value="item.productCategoryId">
      </el-option>
    </el-select>

    <el-select :clearable="true" v-model="goodsForm.productId" @change="requestGoodsByProductId" style="float: right; right: 20px" placeholder="请选择所属产品">
      <el-option
          v-for="item in productIdAndNameOptions"
          :key="item.productId"
          :label="item.productName"
          :value="item.productId">
      </el-option>
    </el-select>

    <el-select :clearable="true" v-model="goodsForm.status" @change="requestGoodsByStatus" style="float: right; right: 40px" placeholder="请选择上架状态">
      <el-option
          v-for="item in states"
          :key="item.status"
          :label="item.text"
          :value="item.status">
      </el-option>
    </el-select>
    <el-input v-model="goodsDetail" @input="queryByGoodsDetail" placeholder="请输入商品详情" class="queryGoods"></el-input>
    <el-divider></el-divider>

    <el-table
        :data="goodsList"
        height="700"
        style="width: 100%">
      <el-table-column
          label="商品id"
          width="80"
          align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.goodsId }}</span>
        </template>
      </el-table-column>

      <el-table-column
          label="所属餐品"
          width="200"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.productName }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="商品详情"
          width="200"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">

            <!--            <el-tag size="">{{ scope.row.productDesc }}</el-tag>-->
            <el-button type="text" v-clipboard:copy="scope.row.goodsDetail" v-clipboard:success="onCopy">点击复制</el-button>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="创建时间"
          width="250"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.createTime }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="更新时间"
          width="250"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.updateTime }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="上架状态"
          width="150"
          align="center">
        <template slot-scope="scope">
          <el-switch
              :active-value=0
              :inactive-value=1
              @change="checkStatus(scope.row, $event)"
              v-model="scope.row.status"
              active-color="#13ce66"
              inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>


      <el-table-column
          label="商品状态"
          width="150"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="" type="success">{{ scope.row.goodsStatus == 0 ? "可用": scope.row.goodsStatus == 1 ? "已售" : "锁定" }}</el-tag>
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
      states:[
        {
          text: "已上架",
          status: 0
        },
        {
          text: "已下架",
          status: 1
        },
      ],
      queryByProductId:"",
      queryStatus:"",
      productIdAndNameOptions:[],
      productCategoryIdAndNameOptions:[],
      goodsEditVisible: false,
      goodsList: [],

      goodsDetail: "",

      goodsAddVisible: false,
      goodsForm: {},
      rules: {
        productCategoryId: [{required: true, message: '产品分类不能为空', trigger: "blur"}],
        productId: [{required: true, message: '产品不能为空', trigger: "blur"}],
        goodsDetail: [{required: true, message: '商品详情不能为空', trigger: "blur"}],
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
    window.document.title = "商品管理"
    this.changeCurrentPageHandler(this.pagination.currentPage)
    this.requestProductCategoryIdAndNameList()
  },

  methods: {

    // 获取数据
    changeCurrentPageHandler(currentPage) {
      this.httpRequest.get("back/goods/requestPageList?page=" + currentPage +
          "&limit=" + this.pagination.pageSize +
          "&goodsDetail=" + this.goodsDetail +
          "&productId=" + this.queryByProductId +
          "&status=" + this.queryStatus +
          "&orderFiled=goods_id" +
          "&orderType=1")
          .then(response => {

            console.log(response)
            this.pagination.currentPage = response.data.goodsPageList.currentPage
            this.pagination.pageSize = response.data.goodsPageList.pageSize
            this.pagination.total = response.data.goodsPageList.totalSize
            this.pagination.totalPage = response.data.goodsPageList.totalPage
            this.goodsList = response.data.goodsPageList.resultList
          })
    },

    // 复制消息提示
    onCopy(){
      this.$message.success("Copy Success")
    },

    // 获取数据 根据分类
    requestGoodsByProductId(value){
      this.queryByProductId = value
      this.changeCurrentPageHandler(1)
    },

    // 获取数据 根据状态
    requestGoodsByStatus(value){
      this.queryStatus = value
      this.changeCurrentPageHandler(1)
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
    queryByGoodsDetail() {
      this.changeCurrentPageHandler(1)
    },

    // 关闭添加产品分类对话框
    addHandleClose() {
      this.goodsAddVisible = false
      this.goodsForm = {}
    },

    // 打开添加产品分类对话框
    showGoodsAddForm() {
      this.goodsForm = {}
      this.goodsAddVisible = true
    },

    // 获取可用产品分类id和名字
    requestProductCategoryIdAndNameList(){
      this.httpRequest.get("/back/productCategory/requestProductCategoryIdAndNameList")
          .then(response => {

            console.log(response)
            this.productCategoryIdAndNameOptions = response.data.productCategoryIdAndNameList

          })
    },

    // 根据产品分类id获取可用商品id和名字
    requestProductIdAndNameList(){
      // this.goodsForm.productId = ""
      // this.$refs.selectProduct.value = ""
      // this.$forceUpdate()
      this.httpRequest.get("/back/product/requestProductIdAndNameListByCategoryId/" + this.goodsForm.productCategoryId)
          .then(response => {
            console.log(response)
            this.productIdAndNameOptions = response.data.productIdAndNameList


          })

    },


    // 添加产品分类
    addGoods() {

      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.goodsForm.isDelete = 0
          this.goodsForm.goodsStatus = 0
          this.goodsForm.status = 1


          // console.log(this.goodsForm.status)

          this.httpRequest.post("/back/goods/appendGoods", this.goodsForm).then(response => {
            this.changeCurrentPageHandler(1)
            this.goodsAddVisible = false
            this.productIdAndNameOptions = []
            this.goodsForm = {}
          })
        } else {
          message.error("请完善数据")
        }
      })
    },


    // 打开编辑产品分类对话框
    handleEdit(index, data) {
      // console.log(data)
      this.goodsForm = data
      this.requestProductIdAndNameList()
      this.goodsEditVisible = true

      this.requestProductCategoryIdAndNameList()

    },

    // 关闭产品分类编辑对话框
    editHandleClose() {
      this.goodsEditVisible = false
    },


    // 编辑产品分类
    editProduct() {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.goodsForm.isDelete = 0
          // this.goodsForm.status = this.goodsForm.status ? 0 : 1
          // console.log(this.goodsForm)
          this.httpRequest.put("/back/goods/alterGoods", this.goodsForm).then(response => {
            this.changeCurrentPageHandler(this.pagination.currentPage)
            this.goodsEditVisible = false
            this.goodsForm = {}
          })
        } else {
          message.error("请完善数据")
        }
      })
    },


    // 删除产品分类
    handleDelete(index, data) {

      this.$confirm("确定要删除该商品吗?", {
        type: "warning",
        beforeClose: async (action, instance, done) => {
          if (action === 'confirm') {
            // console.log("群定了")
            this.httpRequest.delete("back/goods/removeGoods/" + data.goodsId).then(response => {
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

    // 修改商品分类上架状态
    checkStatus(goods, value) {

      this.httpRequest.put("back/goods/alterGoodsStatus", {
        "goodsId": goods.goodsId,
        "status": value
      })

    }


  }


}
</script>

<style scoped>
.addGoods {
  margin-left: 20px;
}

.queryGoods {
  width: 15%;
  position: absolute;
  right: 750px;
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