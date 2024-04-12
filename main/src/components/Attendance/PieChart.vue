<template>
  <div class="main">
    <div ref="chartContainer" style="width: 100%; height: 100%;"></div>
  </div>
</template>

<script>
import {ref, onMounted, onBeforeUnmount} from 'vue';
import * as echarts from 'echarts';

export default {
  props: {
    chartData: {
      type: Object,
      required: true
    },
  },
  name: 'PieChartComponent',
  setup(props) {
    const chartContainer = ref(null);
    const chartInstance = ref(null);
    let resizeObserver = null;

    onMounted(() => {
      createChart();
      observeContainerResize();
    });

    function createChart() {
      const chart = echarts.init(chartContainer.value);
      const options = {
        title: {
          text: props.chartData.title,
          subtext: props.chartData.subtext,
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
            name: props.chartData.seriesName,
            type: 'pie',
            radius: '50%',
            data: props.chartData.seriesData,
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

      chart.setOption(options);
      chartInstance.value = chart;
    }

    function observeContainerResize() {
      resizeObserver = new ResizeObserver(() => {
        resizeChart();
      });

      resizeObserver.observe(chartContainer.value);
    }

    function resizeChart() {
      if (chartInstance.value) {
        chartInstance.value.resize();
      }
    }

    onBeforeUnmount(() => {
      if (resizeObserver) {
        resizeObserver.disconnect();
      }
    });

    return {chartContainer};
  }
};
</script>

<style scoped>
.main {
  width: 100%;
  height: 68%;
  background-color: rgba(0, 0, 0, 0.50);
  border-radius: 10px;
  margin-top: 5%;
}
</style>
