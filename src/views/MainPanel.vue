<template>
  <div style=" background-color: #f5f6fa;width: 100%; height: 100%;">
    <el-container>
      <el-header
          style=" overflow: hidden;background-color: #fff;width: 95%;height: 10%;margin: 0 auto; margin-top: 15px; display: flex;border-radius:10px ;">
        <el-menu @select="selectMenu"
                 text-color="#000"
                 mode="horizontal"
                 :default-active="activeIndex"
        >
          <el-menu-item index="/shop">商店</el-menu-item>
          <el-menu-item index="/order">订单</el-menu-item>
          <el-menu-item index="/help">客服</el-menu-item>
          <!--                    <el-menu-item index="/shop4">登录</el-menu-item>-->


        </el-menu>
      </el-header>
      <el-main
          style="width: 95%; height: 85vh;margin: 0 auto; margin-top: 15px;margin-bottom:15px;background-color: #fff;border-radius:10px ;">
        <router-view/>
      </el-main>
      <center>© 闲人小店.XianRen</center>
    </el-container>
  </div>
</template>

<script>


export default {
  name: "MainPlane",
  data() {
    return {
      sysDict:{},
      activeIndex: '/shop',
    }
  },


  created() {
    window.document.title = "商城主题面板"
    this.activeIndex = "/shop"
  },

  mounted() {
    this.requestAccountShopDict()
    this.activeIndex = this.$route.path
    if (this.activeIndex == '/'){
      this.activeIndex = "/shop"
      this.$router.replace(this.activeIndex)
    }
  },

  methods: {

    // 获取商城字典
    requestAccountShopDict(){
      this.httpRequest.get("/back/sysDict/requestDictByParent/account_shop_dict")
          .then((response) => {
            console.log(response)
            this.sysDict = response.data.parentDictAllSonDict
          }).catch((error) => {
        this.$message.error("未知错误")
      })
    },


    selectMenu(index) {
      if (index === '/help') {
        document.location.href = this.sysDict.huaxianren_telegram
      } else {
        this.$router.push(index)
      }
    },


  }


}
</script>

<style scoped>

</style>