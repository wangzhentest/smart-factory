<template>
  <div class="main">
    <h3 style="color: rgba(255,255,255,0.34)">&nbsp;{{ chartData.title }}</h3>
    <div ref="chartContainer" style="width: 100%; height: 45%;"></div>
  </div>
</template>

<script>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import * as echarts from 'echarts';

export default {
  props: {
    chartData: {
      type: Object,
      required: true
    },
  },
  name: 'BarChartComponent',
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
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: props.chartData.xAxisData,
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: 'Direct',
            type: 'bar',
            barWidth: '60%',
            data: props.chartData.seriesData,
            animation: true,
            itemStyle: {
              color: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  {
                    offset: 0,
                    color: props.chartData.gradientStartColor // 最低值的颜色
                  },
                  {
                    offset: 1,
                    color: props.chartData.gradientEndColor // 最高值的颜色
                  }
                ]
              }
            }
          }
        ],
        visualMap: {
          type: 'continuous',
          show: false,
          min: props.chartData.minValue,
          max: props.chartData.maxValue,
          inRange: {
            color: [props.chartData.gradientStartColor, props.chartData.gradientEndColor] // 渐变颜色范围
          }
        }
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

    return { chartContainer };
  }
};
</script>

<style scoped>
.main {
  width: 100%;
  height: 100vh;
}
</style>
