<template>
    <div>

        <el-dialog
                title="商品详情"
                :visible="goodsVisible"
                width="80%"
                :before-close="addHandleClose">


            <el-alert :closable="false"
                      title="推特的风控严格，请遵守下方规则！不守规则乱搞的，不提供任何售后！"
                      type="success">
            </el-alert>
            <el-alert :closable="false"
                      title="单设备/单IP禁止登录多个号"
                      type="warning">
            </el-alert>
            <el-alert :closable="false"
                      title="不要频繁私信/大量关注别人"
                      type="warning">
            </el-alert>
            <el-alert :closable="false"
                      title="不要发送广告信息"
                      type="warning">
            </el-alert>

            <el-alert :closable="false"
                      title="不要做违反官方规则的事情"
                      type="warning">
            </el-alert>

            <el-divider></el-divider>


            <el-alert :closable="false"
                      title="购买后请保存订单号，本站只支持订单号查询，因本站在海外且无国内备案信息，无法接入支付宝和微信，顾目前只能通过口令红包方式，如介意，请另寻他处"
                      type="error">
            </el-alert>

            <el-divider></el-divider>
            <span>
          <el-form ref="goodsForm" :model="goodsForm" label-width="80px" :rules="rules">
            <el-form-item label="商品名" prop="goodsName">
              <el-input :readonly="true" type="text" v-model="goodsForm.goodsName"></el-input>
            </el-form-item>

            <el-form-item label="商品单价" prop="price">
              <el-input :readonly="true" type="text" v-model="goodsForm.price"></el-input>
            </el-form-item>

            <el-form-item label="商品数量" prop="num">
              <el-input @input="goodsTotalPrice()" :placeholder="'库存' + goodsForm.stock" type="text"
                        v-model="goodsForm.num"></el-input>
            </el-form-item>

            <el-form-item label="总价" prop="total">
              <el-input :readonly="true" placeholder="输入数量自动生成" type="text"
                        v-model="goodsForm.total"></el-input>
            </el-form-item>

           <el-form-item label="收货邮箱" prop="email">
              <el-input placeholder="收货邮箱" type="email"
                        v-model="goodsForm.email"></el-input>
            </el-form-item>

             <el-form-item label="支付方式" prop="payMethod">
<!--              <el-input type="text" >-->
                <el-select v-model="goodsForm.payMethod" placeholder="请选择支付方式">
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
    <el-button type="primary" @click="orderCommit">提交订单</el-button>
  </span>
        </el-dialog>


        <el-collapse accordion>
            <el-collapse-item v-for="item in catalogData" :key="item.id" style="margin-top: 10px">
                <template slot="title">
                    <img style="width: 20px; height: 20px;" :src="item.ico"> &nbsp;&nbsp; {{ item.catalog }}<i
                        class="header-icon el-icon-info"></i>
                </template>
                <el-table
                        :data="item.tableData"
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
                                <el-tag size="medium" type="info">{{ scope.row.goodsName }}</el-tag>
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
                                <el-tag size="medium" type="success">{{ scope.row.price }}</el-tag>
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
                                <el-tag size="medium">{{ scope.row.stock }}</el-tag>
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
                                <el-tag size="medium" type="danger">{{ scope.row.method }}</el-tag>
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
            isMobile: true,    //默认为pc端
            goodsForm: {},
            payMethods: [{
              value: '2',
              label: '支付宝'
            }],
            goodsVisible: false,
            rules: {
                num: [{required: true, message: '数量必须填写', trigger: "blur"}],
                email: [{required: true, message: '邮箱必须填写', trigger: "blur"}],
                moneyToken: [{required: true, message: '口令红包口令必须填写', trigger: "blur"}],
            },
            catalogData: [
                {
                    catalog: "twitter成品号",
                    ico: "https://pub-pce.oss-cn-chengdu.aliyuncs.com/public/2023-10-07/25edb49cbcd44262a2b47344a9273ca2.png",
                    tableData: [
                        {
                            id: 1,
                            goodsName: 'twitter成品',
                            stock: 23,
                            price: 1,
                            method: '自动发货'
                        },
                    ],
                },

                {
                    catalog: "telegram成品号",
                    ico: "https://pub-pce.oss-cn-chengdu.aliyuncs.com/public/2023-10-07/323182c981aa4cfe932180a6dcd14596.png",
                    tableData: [
                        {
                            id: 6,
                            goodsName: 'telegram成品号',
                            stock: 23,
                            price: 24,
                            method: '自动发货'
                        },
                        {
                            id: 6,
                            goodsName: 'telegram成品号',
                            stock: 23,
                            price: 24,
                            method: '自动发货'
                        },
                    ],
                },
                {
                    catalog: "twitter成品号",
                    ico: "https://pub-pce.oss-cn-chengdu.aliyuncs.com/public/2023-10-07/25edb49cbcd44262a2b47344a9273ca2.png",
                    tableData: [
                        {
                            id: 1,
                            goodsName: 'twitter成品',
                            stock: 23,
                            price: 24,
                            method: '自动发货'
                        },
                    ],
                },


            ],
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


    },

    methods: {
        // 购买详情页 商品页
        buying(index, row) {
            this.goodsForm.id = row.id
            this.goodsForm.goodsName = row.goodsName
            this.goodsForm.price = row.price
            this.goodsForm.stock = row.stock
            this.goodsVisible = true
            console.log(row)
            console.log(row.goodsName)

        },
        // 对话框关闭
        addHandleClose() {
            this.goodsForm = {}
            this.goodsVisible = false
        },
        // 总价计算
        goodsTotalPrice() {
            if (this.goodsForm.num === '-') {
                this.goodsForm.num = null
                this.goodsForm.total = null
                this.$message.error("数量必须大于0")
            } else {
                this.goodsForm.total = null
            }

            if (this.goodsForm.num) {
                if (Math.floor(Number(this.goodsForm.num)) < 1) {
                    this.goodsForm.num = null
                    this.goodsForm.total = null
                    this.$message.error("数量必须大于0")
                } else {
                    if (Math.floor(Number(this.goodsForm.num)) > this.goodsForm.stock) {
                        this.goodsForm.num = null
                        this.goodsForm.total = null
                        this.$message.error("数量不能多于库存")
                    } else {
                        this.goodsForm.num = Math.floor(Number(this.goodsForm.num))
                        this.goodsForm.total = this.goodsForm.num * this.goodsForm.price
                    }

                }
            } else {
                this.goodsForm.total = null
            }
        },

        // 提交订单
        orderCommit() {
            this.$refs.goodsForm.validate((valid) => {
                if (valid) {

                    this.httpRequest.post("/back/order/pay", this.goodsForm)
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