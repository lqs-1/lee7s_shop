<template>
  <div>

    <el-dialog
        title="订单详情"
        :visible="goodsVisible"
        width="85%"
        :before-close="addHandleClose">


      <el-alert :closable="false"
                v-for="item in this.orderForm.productDesc"
                :key="item"
                :title="item"
                type="success">
      </el-alert>
      <br>

<!--      <el-divider></el-divider>-->


      <el-alert :closable="false"
                v-for="item in this.orderForm.productWarn"
                :key="item"
                :title="item"
                type="error">
      </el-alert>
      <br>
<!--      <el-divider></el-divider>-->
      <span>
          <el-form ref="orderForm" :model="orderForm" label-width="80px" :rules="rules">
            <el-form-item label="产品名" prop="productName">
              <el-input :readonly="true" type="text" v-model="orderForm.productName"></el-input>
            </el-form-item>

            <el-form-item label="产品单价" prop="goodsPrice">
              <el-input :readonly="true" type="text" v-model="orderForm.goodsPrice"></el-input>
            </el-form-item>

            <el-form-item label="产品数量" prop="goodsNum">
              <el-input @input="goodsTotalPrice()" :placeholder="'库存' + orderForm.productStock" type="text"
                        v-model="orderForm.goodsNum"></el-input>
            </el-form-item>

            <el-form-item label="总价" prop="orderTotalPrice">
              <el-input :readonly="true" placeholder="输入数量自动生成" type="text"
                        v-model="orderForm.orderTotalPrice"></el-input>
            </el-form-item>

           <el-form-item label="收货邮箱" prop="email">
              <el-input placeholder="收货邮箱" type="email"
                        v-model="orderForm.email"></el-input>
            </el-form-item>

             <el-form-item label="支付方式" prop="payMethod">
<!--              <el-input type="text" >-->
                <el-select v-model="orderForm.payMethod" placeholder="请选择支付方式">
                 <el-option
                     v-for="item in payMethods"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
                  </el-option>
                </el-select>
               <!--              </el-input>-->
            </el-form-item>

          </el-form>
      </span>

      <span slot="footer" class="dialog-footer">
    <el-button @click="addHandleClose">取 消</el-button>
    <el-button type="primary" @click="orderCommit" :loading="subMitOrderLoading">提交订单</el-button>
  </span>
    </el-dialog>


    <el-collapse accordion>
      <el-collapse-item v-for="item in goodsInformationDataList" :key="item.productCategoryId" style="margin-top: 10px">
        <template slot="title">
          <img style="width: 20px; height: 20px;" :src="item.productCategoryLogo"> &nbsp;&nbsp; {{ item.productCategoryName }}<i
            class="header-icon el-icon-info"></i>
        </template>
        <el-table
            :data="item.shopProductItemVoList"
            style="width: 100%"
            align="center"
        >
          <el-table-column
              label="商品名"
              min-width="150"
              align="left"
              prop="goodsName">
            <template slot-scope="scope">
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium" type="info">{{ scope.row.productName }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column
              v-if="isMobile"
              label="价格"
              min-width="100"
              align="center"
              prop="price">
            <template slot-scope="scope">
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium" type="success">{{ scope.row.productPrice }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column
              v-if="isMobile"
              label="库存"
              min-width="100"
              align="center"
              prop="stock">
            <template slot-scope="scope">
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.productStock }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column
              v-if="isMobile"
              min-width="100"
              label="发货方式"
              align="center"
              prop="method">
            <template slot-scope="scope">
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium" type="danger">{{ scope.row.productMethod == 0 ? "自动" : "手动" }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column
              label="操作"
              min-width="100"
              align="center"
              prop="ops">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="danger"
                  @click="buying(scope.$index, scope.row)">购买
              </el-button>
            </template>
          </el-table-column>

        </el-table>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>


export default {
  name: "ShopView",
  data() {
    return {
      subMitOrderLoading: false,
      isMobile: true,    //默认为pc端
      orderForm: {},
      payMethods: [{
        value: '2',
        label: '支付宝'
      }],
      goodsVisible: false,
      rules: {
        num: [{required: true, message: '数量必须填写', trigger: "blur"}],
        email: [{required: true, message: '邮箱必须填写', trigger: "blur"}],
        payMethod: [{required: true, message: '支付方式必须填写', trigger: "blur"}],
        goodsNum: [{required: true, message: '产品数量必须填写', trigger: "blur"}],
      },
      goodsInformationDataList: [],
    }
  },

  mounted() {
  },

  created() {
    window.document.title = "商品页"
    let flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
    if (flag) {
      //如果flag不为空，说明可以匹配到，是手机端
      this.isMobile = false
    }

    this.requestShopGoodsInformation()


  },

  methods: {

    // 获取商品列表
    requestShopGoodsInformation(){
      this.httpRequest.get("/shop/goods/requestShopGoodsInformation")
          .then((response) => {
            console.log(response)
            this.goodsInformationDataList = response.data.shopGoodsInformationList
          }).catch((error) => {
        this.$message.error("未知错误")
      })
    },




    // 购买详情页
    buying(index, row) {
      this.orderForm.productId = row.productId
      this.orderForm.productName = row.productName
      this.orderForm.goodsPrice = row.productPrice


      this.orderForm.productStock = row.productStock
      this.orderForm.productDesc = row.productDesc.toString().split(":")
      this.orderForm.productWarn = row.productWarn.toString().split(":")


      this.goodsVisible = true
      console.log(row)
      console.log(row.goodsName)

    },
    // 对话框关闭
    addHandleClose() {
      this.orderForm = {}
      this.goodsVisible = false
    },
    // 总价计算
    goodsTotalPrice() {

      if (isNaN(parseInt(this.orderForm.goodsNum))){
        this.orderForm.goodsNum = null
        this.orderForm.orderTotalPrice = null
        this.$message.error("数量必须数字")
      }

      if (this.orderForm.goodsNum === '-') {
        this.orderForm.goodsNum = null
        this.orderForm.orderTotalPrice = null
        this.$message.error("数量必须大于0")
      } else {
        this.orderForm.orderTotalPrice = null
      }

      if (this.orderForm.goodsNum) {
        if (Math.floor(Number(this.orderForm.goodsNum)) < 1) {
          this.orderForm.goodsNum = null
          this.orderForm.orderTotalPrice = null
          this.$message.error("数量必须大于0")
        } else {
          if (Math.floor(Number(this.orderForm.goodsNum)) > this.orderForm.productStock) {
            this.orderForm.goodsNum = null
            this.orderForm.orderTotalPrice = null
            this.$message.error("数量不能多于库存")
          } else {
            this.orderForm.goodsNum = Math.floor(Number(this.orderForm.goodsNum))
            this.orderForm.orderTotalPrice = this.orderForm.goodsNum * this.orderForm.goodsPrice
          }

        }
      } else {
        this.orderForm.total = null
      }
    },

    // 提交订单
    orderCommit() {
      this.$refs.orderForm.validate((valid) => {
        if (valid) {
          this.subMitOrderLoading = true

          this.httpRequest.post("/back/order/pay", this.orderForm)
              .then((response) => {
                // if (response.data.code >= 10000 && response.data.code < 20000) {
                //     this.$message.success("等待邮件")
                // }
                console.log(response)
                //跳转支付页面
                // document.location.href = response

                // document.querySelector("body").innerHTML = response.data;
                // document.forms[0].submit();

                // 假设后端返回的链接是存储在变量 responseLink 中
                let payLink = response.data

                // 使用 window.location.href 实现页面跳转
                window.location.href = payLink;

              }).catch((error) => {
            this.$message.error("未知错误")
          })
        } else {
          this.$message.error("请完善订单表单")
        }
      })
    }
  }
}
</script>

<style scoped>


el-aside::-webkit-scrollbar {
  width: 0;
}
</style>