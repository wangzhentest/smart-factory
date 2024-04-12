<template>
  <div class="container">
    <div class="box1">
      <div class="top">
        <BarChart :chartData="barChartData" />
      </div>
      <div class="bottom">
        <BarChart :chartData="barChartData2" />
      </div>
    </div>
    <div class="container2">
      <div class="container3">
        <div class="box2">
          <Statistic/>
          <Table/>
        </div>
        <div class="box3">
          <div class="box4">
            <div class="button">
              日
            </div>
            <div class="button">
              周
            </div>
            <div class="button">
              月
            </div>
          </div>
          <hr>
          <PieChart :PieChartData="PieChartData" />
        </div>
      </div>
      <div class="box5">
        <LineChart :chartData="lineChartData" />
      </div>
    </div>
  </div>
</template>

<script>
import PieChart from './DataVisualization/PieChart.vue';
import LineChart from './DataVisualization/LineChart.vue';
import BarChart from './DataVisualization/BarChart.vue';
import Statistic from './DataVisualization/Statistic.vue';
import Table from './DataVisualization/Table.vue';
export default {
  components: {
    PieChart,
    LineChart,
    BarChart,
    Statistic,
    Table
  },
  data() {
    return {
      PieChartData: {
        title: '事故汇总',
        seriesName: '数据',
        data: [
          { value: 1048, name: '人员受伤' },
          { value: 735, name: '火灾事故' },
          { value: 580, name: '设备损坏' },
          { value: 484, name: '危险告警' },
          { value: 300, name: '人员闯入' }
        ]
      },
      lineChartData: {
        title: '事故发生时间段汇总',
        subtext: '',
        xAxisData: [
          '00:00', '01:15', '02:30', '03:45', '05:00', '06:15', '07:30', '08:45', '10:00', '11:15',
          '12:30', '13:45', '15:00', '16:15', '17:30', '18:45', '20:00', '21:15', '22:30', '23:45'
        ],
        seriesName: 'Electricity',
        seriesData: [
          10, 20, 2, 8, 12, 30, 40, 50, 10, 12, 40, 60,
          10, 12, 13, 21, 11, 44, 11, 22
        ],
        visualMapPieces: [
          {lte: 6, color: 'green'},
          {gt: 6, lte: 8, color: 'red'},
          {gt: 8, lte: 14, color: 'green'},
          {gt: 14, lte: 17, color: 'red'},
          {gt: 17, color: 'green'}
        ],
        markAreaData: [
          [{name: 'Morning Peak', xAxis: '07:30'}, {xAxis: '10:00'}],
          [{name: 'Evening Peak', xAxis: '17:30'}, {xAxis: '21:15'}]
        ]
      },
      barChartData: {
        title: '事故汇总',
        xAxisData: ['受伤', '火灾', '设备', '告警', '闯入'],
        seriesData: [41, 9, 44, 33, 13],
        gradientStartColor: 'red',
        gradientEndColor: 'gray',
        minValue: 9, // 最小值
        maxValue: 44 // 最大值
      },
      barChartData2: {
        title: '安全提醒汇总',
        xAxisData: ['受伤', '火灾', '设备', '告警', '闯入'],
        seriesData: [20, 15, 24, 13, 35],
        gradientStartColor: 'red',
        gradientEndColor: 'gray',
        minValue: 9, // 最小值
        maxValue: 44 // 最大值
      }
    };
  },
};
</script>

<style scoped>
.container {
  display: flex;
  height: 100%;
  width: 100%;
}
.box1 {
  border-radius: 10px;
  width: 25%;
  display: flex;
  flex-direction: column;
  margin: 10px;
}
.top {
  flex: 1;
  height: 50%;
  background-color: rgba(0, 0, 0, 0.25);
}
.bottom {
  flex: 1;
  height: 50%;
  background-color: rgba(0, 0, 0, 0.25);
}
.container2 {
  width: 75%;
  height: 98%;
  margin: 10px;
}
.container3 {
  display: flex;
  width: 100%;
  height: 65%;
}
.box2 {
  width: 68%;
  height: 100%;
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0.25);
}
.box3 {
  width: 30%;
  height: 100%;
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0.25);
  margin-left: 2%;
}
.box4 {
  width: 100%;
  height: 15%;
  display: flex;
  align-items: center;
}
.box4 .button {
  width: 35%;
  height: 60%;
  background-color: gray;
  border-radius: 5px;
  margin: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20px; /* 设置字体大小 */
  color: white; /* 设置文字颜色 */
  font-weight: bold; /* 设置字体加粗 */
}
.box5 {
  width: 100%;
  height: 34%;
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0.25);
  margin-top: 1%;
}
</style>
