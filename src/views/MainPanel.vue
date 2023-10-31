<template>
    <div style="background-color: #f5f6fa;width: 100%; height: 100%;">
        <el-container>
            <el-header style=" overflow: hidden;background-color: #fff;width: 95%;height: 10%;margin: 0 auto; margin-top: 15px; display: flex;border-radius:10px ;">
                <el-menu @select="selectMenu"
                         text-color="#000"
                         mode="horizontal"
                >
                    <el-menu-item index="/shop">商店</el-menu-item>
                    <el-menu-item index="/order">订单</el-menu-item>
                    <el-menu-item index="/help">客服</el-menu-item>
<!--                    <el-menu-item index="/shop4">登录</el-menu-item>-->


                </el-menu>
            </el-header>
            <el-main style="width: 95%; height: 88vh;margin: 0 auto; margin-top: 15px;margin-bottom:15px;background-color: #fff;border-radius:10px ;">
                <router-view/>
            </el-main>
        </el-container>
    </div>
</template>

<script>

export default {
    name: "MainPlane",
    data() {
        return {
        }
    },



    created() {
        window.document.title = "商城主题面板"
        this.$router.push("/shop")
    },

    methods: {
        selectMenu(index) {
            if (index === '/help'){
                document.location.href = "https://t.me/lee7s_7s"
            }else{
                this.$router.push(index)
            }
        },


    }


}
</script>

<style scoped>

</style>