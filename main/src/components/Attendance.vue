<template>
  <el-card class="container">
    <el-card style="width: 98%;height: 98%;position: absolute;right: 1%;top: 1%">
      <h1>{{ formattedDate }} 考勤</h1>
      <el-date-picker v-model="selectedDate" type="date" placeholder="Pick a day" size="default"
                      style="margin-top: 10px" @change="handleDateChange"/>
      <div
          style="width: 100px; height: 80px;position: absolute; right: 300px; top: 20px; text-align: center;color: grey">
        <span style="font-size: 35px;color: cornflowerblue">{{ counterStore.countState(1) }}</span>
        <span>人</span>
        <br>
        <span>出勤</span>
      </div>
      <div
          style="width: 100px; height: 80px;position: absolute; right: 150px; top: 20px; text-align: center;color: grey">
        <span style="font-size: 35px;color: cornflowerblue">{{ counterStore.attendanceRate }}</span>
        <span>%</span>
        <br>
        <span>出勤率</span>
      </div>
      <div
          style="width: 100px; height: 80px; position: absolute; right: 10px; top: 0px; display: flex; align-items: center; justify-content: center; z-index: 2;">
        <el-icon :size="20" style="margin-right: 5px; color: #888;">
          <Setting/>
        </el-icon>
        <span style="color:grey;font-size: 18px;">管理</span>
      </div>
      <div
          style="width: 150px; height: 80px; position: absolute; right: 10px; top: 150px; display: flex; align-items: center; justify-content: center; z-index: 2;">
        <el-icon :size="20" style="margin-right: 5px; color: #888;">
          <Warning/>
        </el-icon>
        <el-badge value="1" class="item" style="font-size: 18px; color: #888;">
          可疑代签
        </el-badge>
      </div>
      <el-divider/>
      <el-menu :default-active="menu_index" mode="horizontal" @select="handleMenuSelect" style="z-index: 1">
        <el-menu-item index="0">全部({{ counterStore.list.length }})</el-menu-item>
        <el-menu-item index="1">正常({{ counterStore.countState(1) }})</el-menu-item>
        <el-menu-item index="2">迟到({{ counterStore.countState(2) }})</el-menu-item>
        <el-menu-item index="3">请假({{ counterStore.countState(3) }})</el-menu-item>
        <el-menu-item index="4">早退({{ counterStore.countState(4) }})</el-menu-item>
        <el-menu-item index="5">缺勤({{ counterStore.countState(0) }})</el-menu-item>
        <el-menu-item index="6">迟到/早退({{ counterStore.countState(6) }})</el-menu-item>
      </el-menu>
      <el-table :data="filteredTableData" height="650" style="width: 100%" table-layout="auto" stripe>
        <el-table-column prop="name" label="员工姓名" width="180" align="center">
          <template #default="scope">
            <div style="display: flex; align-items: center; justify-content: center;">
              <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
              <span style="margin-left: 10px;">{{ scope.row.name }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="employee_id" label="员工号" width="300" align="center"/>
        <el-table-column prop="clock_in_time" label="签到时间" width="250" align="center"/>
        <el-table-column prop="clock_out_time" label="签退时间" width="250" align="center"/>
        <el-table-column prop="state" label="状态" align="center">
          <template #default="scope">
            <el-tag v-if="scope.row.state === 1" type="success" effect="dark" size="large">正常</el-tag>
            <el-tag v-else-if="scope.row.state === 2" type="danger" effect="dark" size="large">迟到</el-tag>
            <el-tag v-else-if="scope.row.state === 3" type="info" effect="dark" size="large">请假</el-tag>
            <el-tag v-else-if="scope.row.state === 4" type="danger" effect="dark" size="large">早退</el-tag>
            <el-tag v-else-if="scope.row.state === 0" type="info" effect="dark" size="large">缺勤</el-tag>
            <el-tag v-else-if="scope.row.state === 6" type="info" effect="dark" size="large">迟到/早退</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </el-card>
</template>

<script setup>
import {useCounterStore} from "../stores/counter.js";
import {ref, onMounted, onUnmounted} from "vue";
import { ElNotification } from 'element-plus'

const counterStore = useCounterStore()
const selectedDate = ref('') // store the selected date
const formattedDate = ref('') // store the formatted date
const menu_index = ref('0');
const filteredTableData = ref([]);
let timer;

onMounted(() => {
  const today = new Date();
  selectedDate.value = today.toISOString().split('T')[0];
  formattedDate.value = formatDate(today);
  getList()
});

const open1 = () => {
  ElNotification({
    title: '成功',
    message: `获取考勤信息成功，选中日期为：${formattedDate.value}`,
    type: 'success',
    duration: 2000,
  });
};

onUnmounted(() => {
  clearInterval(timer); // 清除定时器
});

const handleMenuSelect = (index) => {
  menu_index.value = index;
  filterTableData(index);
};

const filterTableData = (index) => {
  let tableData = counterStore.list;
  if (index === '0') {
    filteredTableData.value = tableData;
  } else {
    filteredTableData.value = tableData.filter(item => item.state === parseInt(index));
  }
};

const handleDateChange = (newDate) => {
  formattedDate.value = formatDate(newDate);
  getList()
};

async function getList() {
  clearInterval(timer); // 清除之前的定时器
  timer = setInterval(async () => {
    await counterStore.getList(formattedDate.value);
    filterTableData(menu_index.value);
  }, 1000);
  open1()
}

function formatDate(date) {
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');
  return `${year}-${month}-${day}`;
}

</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;
}
</style>