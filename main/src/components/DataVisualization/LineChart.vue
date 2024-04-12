<template>
  <div class="main" ref="chartContainer"></div>
</template>

<script>
import {ref, onMounted, onBeforeUnmount} from 'vue';
import * as echarts from 'echarts';

export default {
  props: {
    chartData: {
      type: Object,
      required: true
    }
  },
  name: 'ChartComponent',
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
          subtext: props.chartData.subtext
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        toolbox: {
          show: true,
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: props.chartData.xAxisData
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value} 次'
          },
          axisPointer: {
            snap: true
          }
        },
        visualMap: {
          show: false,
          dimension: 0,
          pieces: props.chartData.visualMapPieces
        },
        series: [
          {
            name: props.chartData.seriesName,
            type: 'line',
            smooth: true,
            data: props.chartData.seriesData,
            markArea: {
              itemStyle: {
                color: 'rgba(255, 173, 177, 0.4)'
              },
              data: props.chartData.markAreaData
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

<style>
.main {
  width: 100%;
  height: 100%;
}
</style>
