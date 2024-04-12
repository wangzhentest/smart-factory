<template>
  <div class="accident-container">
    <table class="accident-table">
      <thead>
      <tr>
        <th>事故编号</th>
        <th>发生时间</th>
        <th>发生区域</th>
        <th>具体位置</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="accident in messages" :key="accident.id">
        <td>{{ accident.id }}</td>
        <td>{{ accident.occur_date }}</td>
        <td>{{ accident.occur_area }}</td>
        <td>{{ accident.occur_place }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import apiService from "../../services/apiService";
const messages = ref(null)
onMounted(() => {
  get_messages()
});

const get_messages = async () => {
  messages.value = await apiService.get_messages();
};
</script>

<style scoped>
.accident-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  margin-top: 8%;
  border-radius: 5px;
}


.accident-table {
  width: 100%;
  border-collapse: collapse;
  font-family: 'Roboto', sans-serif;
  background-color: rgba(0, 0, 0, 0.25);
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  overflow: hidden;
  height: 370px;
  overflow-y: auto;
}

.accident-table th,
.accident-table td {
  padding: 12px 15px;
  text-align: left;
}

.accident-table th {
  background-color: rgba(0, 0, 0, 0.75);
  color: #fff;
  font-weight: 500;
  text-transform: uppercase;
  position: sticky;
  top: 0;
}
.accident-table td {
  height: 30px; /* 设置 td 的高度为 50px */
  white-space: nowrap; /* 防止文本换行 */
  overflow: hidden; /* 隐藏溢出的内容 */
  text-overflow: ellipsis; /* 当文本溢出时显示省略号 */
  color: white;
}

.accident-table tbody {
  display: block; /* 将 tbody 设置为块级元素 */
  max-height: 330px; /* 设置最大高度为 200px */
  overflow-y: auto; /* 当内容超过最大高度时显示垂直滚动条 */
}

.accident-table thead,
.accident-table tbody tr {
  display: table; /* 将 thead 和 tr 设置为表格行 */
  width: 100%; /* 确保宽度为 100% */
  table-layout: fixed; /* 固定表格布局，防止单元格内容过长时影响其他单元格 */
}
.accident-table tr:nth-child(even) {
  background-color: rgba(0, 0, 0, 0.31);
}

.accident-table tr:hover {
  background-color: rgba(230, 230, 230, 0.27);
}
</style>