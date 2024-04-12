<template>
  <div class="main">
    <h3 style="color: rgba(255,255,255,0.34)">&nbsp;{{ PieChartData.title }}</h3>
    <div ref="chart" class="chart-container"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  props: {
    PieChartData: {
      type: Object,
      required: true
    }
  },
  mounted() {
    this.createChart();
    this.observeContainerResize();
  },
  methods: {
    createChart() {
      const chart = echarts.init(this.$refs.chart);

      const option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: this.PieChartData.seriesName,
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center',
              color: '#fff'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 20,
                fontWeight: 'bold',
                color: '#fff'
              }
            },
            labelLine: {
              show: false
            },
            data: this.PieChartData.data
          }
        ]
      };
      chart.setOption(option);
    },
    observeContainerResize() {
      const chartContainer = this.$refs.chart;
      const resizeObserver = new ResizeObserver(() => {
        this.resizeChart();
      });

      resizeObserver.observe(chartContainer);
    },
    resizeChart() {
      const chart = echarts.getInstanceByDom(this.$refs.chart);
      if (chart) {
        chart.resize();
      }
    }
  }
};
</script>

<style scoped>
.main {
  width: 100%;
  height: 100%;
  margin-top: 5%;
}

.chart-container {
  width: 100%;
  height: 80%;
}
</style>
