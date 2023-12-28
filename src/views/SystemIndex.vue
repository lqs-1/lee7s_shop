<template>
  <div>
    <h2 align="center" style="font-family: 楷体">订单总览</h2>
    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <div id="statisticalOne" style="width: 600px;height:400px; float: none; left: 30%; margin-top: 10%;"></div>
  </div>
</template>

<script>
export default {
  name: "SystemIndex",

  data() {
    return {
      orderStatisticalData:[],
    }
  },

  created() {
    window.document.title = "系统首页"


  },

  beforeMount() {

  },

  beforeCreate() {


  },
 async mounted() {

    await this.requestOrderStatisticsData()
    console.log(this.orderStatisticalData)
    this.statisticalOne();

  },

  methods:{

    async requestOrderStatisticsData(){
      await this.httpRequest.get("/back/statistics/orderStatistics").then(response => {
        console.log(response)
         this.orderStatisticalData = response.data.orderStatisticsData
      })
    },

    statisticalOne(){
      var statisticalOne = this.$echarts.init(document.getElementById('statisticalOne'));


      // 指定图表的配置项和数据
      var option = {
        title: {
          text: '整站订单数据',
          subtext: '统计',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '所有',
            type: 'pie',
            radius: '60%',
            data: this.orderStatisticalData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      // 使用刚指定的配置项和数据显示图表。
      statisticalOne.setOption(option);
    },

  }



}
</script>

<style scoped>

</style>