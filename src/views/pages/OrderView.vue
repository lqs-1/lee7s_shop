<template>
    <div>
        <center>
            <div style="height: 5vh; width: 95%">
                <el-input placeholder="输入订单号" v-model="searchKey" clearable @clear="clearListAndKey">
                    <el-button :loading="isResponse" slot="append" icon="el-icon-search" @click="getOrder">查询
                    </el-button>
                </el-input>
            </div>
        </center>

        <center>
            <div style="height: 5vh; width: 95%; margin-top: 20px;">
                <el-alert :closable="false"
                          :title="'商品: ' + order.goodsName"
                          type="success">
                </el-alert>
                <el-alert :closable="false"
                          :title="'单价: ' + order.price"
                          type="warning">
                </el-alert>
                <el-alert :closable="false"
                          :title="'数量: ' + order.num"
                          type="success">
                </el-alert>
                <el-alert :closable="false"
                          :title="'状态: ' + order.orderStatus"
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
                        :data="order.items"
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
                                          :title="'商品: ' + scope.row.detail"
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
            searchKey: null,
            isResponse: false,

            order: {
                goodsName: "telegram成品号-美国",
                price: "34",
                num: "3",
                orderStatus: "已完成",
                email: "liqisong2002@gmail.com dddddd",
                createTime: "2023-10-7 12:23:21",
                updateTime: "2023-10-7 12:24:21",
                items: [
                     {
                        detail: "账号17298816234，密码2323423，密保：你妈妈是谁 - lee7s，你多大了 - 20岁"
                    },
                    {
                        detail: "账号17298816234，密码2323423，密保：你妈妈是谁 - lee7s，你多大了 - 20岁"
                    },
                    {
                        detail: "账号17298816234，密码2323423，密保：你妈妈是谁 - lee7s，你多大了 - 20岁"
                    },
                    {
                        detail: "账号17298816234，密码2323423，密保：你妈妈是谁 - lee7s，你多大了 - 20岁"
                    },
                ]
            }
        }
    },

    created() {
        window.document.title = "订单查询"
    },

    methods: {
        clearListAndKey() {
            this.order = {}
            this.searchKey = null
            this.isResponse = false
        },

        getOrder() {
            this.tvList = {}
            if (this.searchKey == null) {
                this.$message.info("请输入订单号")
            } else {
                this.isResponse = true
                this.httpRequest.get("/back/order/query?searchKey=" + this.searchKey)
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