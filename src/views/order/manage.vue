<template>
  <div class="product_category_list">
    <div style="width: 100%; height: 3vh">
      <el-select :clearable="true" v-model="orderStatus" @change="requestOrderByOrderStatus"
                 style="float: right; right: 40px" placeholder="请选择订单状态">
        <el-option
            v-for="item in states"
            :key="item.status"
            :label="item.text"
            :value="item.status">
        </el-option>
      </el-select>
    </div>

    <el-divider></el-divider>

    <el-table
        :data="orderList"
        height="700"
        style="width: 100%">
      <el-table-column
          label="订单编号"
          width="150"
          align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.orderSn }}</span>
        </template>
      </el-table-column>

      <el-table-column
          label="产品id"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.productId }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="产品名字"
          width="200"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.productName }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="商品数量"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.goodsNum }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="商品单价"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.goodsPrice }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="订单总价"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.orderTotalPrice }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="客户邮箱"
          width="200"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.email }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="商品清单"
          width="150"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="">{{ scope.row.goodsDetailIds }}</el-tag>
          </div>
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
          label="订单状态"
          width="80"
          align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="" type="warning">{{ scope.row.orderStatus == 0 ? "锁定" : scope.row.orderStatus == 1 ? "完成" : "取消" }}</el-tag>
          </div>
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
      orderStatus:"",
      states: [
        {
          text: "已锁定库存",
          status: 0
        },
        {
          text: "已完成",
          status: 1
        },
        {
          text: "已取消",
          status: 2
        },
      ],
      orderList: [],

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
    window.document.title = "订单总览"
    this.changeCurrentPageHandler(this.pagination.currentPage)
  },

  methods: {

    // 获取数据
    changeCurrentPageHandler(currentPage) {
      this.httpRequest.get("back/order/requestPageList?page=" + currentPage +
          "&limit=" + this.pagination.pageSize +
          "&status=" + this.orderStatus +
          "&orderFiled=update_time" +
          "&orderType=1")
          .then(response => {

            console.log(response)
            this.pagination.currentPage = response.data.orderPageList.currentPage
            this.pagination.pageSize = response.data.orderPageList.pageSize
            this.pagination.total = response.data.orderPageList.totalSize
            this.pagination.totalPage = response.data.orderPageList.totalPage
            this.orderList = response.data.orderPageList.resultList
          })
    },


    // 根据状态查询订单
    requestOrderByOrderStatus(value){
      this.orderStatus = value
      this.changeCurrentPageHandler(1)
    },

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