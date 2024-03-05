<template>
    <div>
        <center>
            <div style="height: 5vh; width: 95%">
                <el-input placeholder="输入订单号" v-model="searchKey" clearable @clear="clearListAndKey">
                    <el-button :loading="isResponse" slot="append" icon="el-icon-search" @click="getOrder">查询</el-button>
                </el-input>
            </div>
        </center>

        <center>
            <div style="height: 5vh; width: 95%; margin-top: 20px;" v-if="order">
                <el-alert :closable="false"
                          :title="'商品: ' + order.productName"
                          type="success">
                </el-alert>
                <el-alert :closable="false"
                          :title="'单价: ' + order.goodsPrice"
                          type="warning">
                </el-alert>
                <el-alert :closable="false"
                          :title="'数量: ' + order.goodsNum"
                          type="success">
                </el-alert>
                <el-alert :closable="false"
                          :title="'状态: ' + order.orderStatus == 0? '锁定': order.orderStatus == 1? '已完成' : '已取消'"
                          type="warning">
                </el-alert>
                <el-alert :closable="false"
                          :title="'邮箱: ' + order.email"
                          type="success">
                </el-alert>
                <el-alert :closable="false"
                          :title="'订单创建时间: ' + order.createTime"
                          type="warning">
                </el-alert>
                <el-alert :closable="false"
                          :title="'订单修改时间: ' + order.updateTime"
                          type="success">
                </el-alert>

                <el-divider></el-divider>

                <el-table
                        :data="order.goodsDetailList"
                        style="width: 100%; margin-top: 10px"
                        align="center">
                    <el-table-column
                            label="具体商品"
                            min-width="100"
                            align="left"
                            prop="detail">
                        <template slot-scope="scope">
                            <div slot="reference" class="name-wrapper">
                                <el-alert :closable="false"
                                          :title="'商品: ' + scope.row"
                                          type="error">
                                </el-alert>
                            </div>
                        </template>
                    </el-table-column>

                </el-table>
            </div>
        </center>

        <center>

        </center>
    </div>
</template>

<script>
export default {
    name: "OrderView",
    data() {
        return {
          isMobile: true,
            searchKey: null,
            isResponse: false,

            order: false
        }
    },

    created() {
      console.log(this.order)
        window.document.title = "闲人小店.XianRen"
        let flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
        if (flag) {
          //如果flag不为空，说明可以匹配到，是手机端
          this.isMobile = false
        }
    },

    methods: {
        clearListAndKey() {
            this.order = false
            this.searchKey = null
            this.isResponse = false
        },

        getOrder() {
            let orderSn = this.searchKey.trim()
            if (orderSn == null || orderSn == "") {
                this.$message.info("请输入订单号")
                this.searchKey = ""
            } else {
                this.isResponse = true
                this.httpRequest.get("/back/order/requestOrderByOrderSn/" + this.searchKey)
                    .then(resp => {
                        this.order = resp.data.order
                        this.isResponse = false
                    })
            }


        },
    }
}
</script>

<style scoped>

</style>