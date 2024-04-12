<template>
  <div class="center-box">
    <el-button class="down_btn" @click="downBtn">Download as PDF</el-button>
    <div class="content-box">
      <slot name="btn"></slot>
      <h1 style=" font-size: 24px;font-weight: bold;margin-bottom: 20px;">事故处理报表</h1>
      <table class="table">
        <tr v-if="reportObj">
          <td class="label">事故类型：</td>
          <td>{{ reportObj.incident_type }}</td>
        </tr>
        <tr v-if="reportObj">
          <td class="label">发生日期：</td>
          <td>{{ reportObj.occur_date }}</td>
        </tr>
        <tr v-if="reportObj">
          <td class="label">发生地点：</td>
          <td>{{ reportObj.location }}</td>
        </tr>
        <tr v-if="reportObj">
          <td class="label">事故原因：</td>
          <td>{{ reportObj.incident_reason }}</td>
        </tr>
        <tr v-if="reportObj">
          <td class="label">处理完毕时间：</td>
          <td>{{ reportObj.dispose_date }}</td>
        </tr>
        <tr v-if="reportObj">
          <td class="label">处理人员：</td>
          <td>{{ reportObj.dispose_staff_id }}</td>
        </tr>
      </table>
      <h2 style="font-size: 20px;font-weight: bold;margin-top: 30px;margin-bottom: 10px;">事故支出</h2>
      <ul style="list-style-type: disc; margin-top: 20px;margin-bottom: 20px;padding-left: 30px;">
        <li v-for="(item) in reportObj.expenditures.split(';')" v-if="reportObj">
          {{item}}
        </li>
      </ul>
      <h2 style="font-size: 20px;font-weight: bold;margin-top: 30px;margin-bottom: 10px;">潜在危险</h2>
      <ul style="list-style-type: disc; margin-top: 20px;margin-bottom: 20px;padding-left: 30px;">
        <li v-for="(item) in reportObj.potential_risk.split(';')" v-if="reportObj">
          {{item}}
        </li>
      </ul>
    </div>
    <el-dialog v-model="formShow" title="请确认身份" @close="clearPWD" width="50vh">
      <el-input v-model="PWD" placeholder="请输入密码"/><br><br>
      <el-button @click="confirmPWD" type="danger">确定</el-button>
      <el-button @click="formShow = false">取消</el-button>
    </el-dialog>
  </div>
</template>

<script setup>
import {defineProps, reactive, ref} from 'vue';
import html2pdf from 'html2pdf.js';
import {getCookie} from "../../utils/Cookie";
import {useRouter} from 'vue-router';
import apiService from "../../services/apiService";
import {ElMessage} from "element-plus";
const props = defineProps({
  reportObj: Object
});
const user = reactive(JSON.parse(getCookie("userInfo")))
const PWD = ref(null)
const error_number = ref(2)
const router = useRouter();
const formShow = ref(false)
function downBtn(){
  formShow.value = true
}

const downloadAsPDF = () => {
  const element = document.querySelector('.content-box');
  html2pdf().from(element).save('report.pdf');
};

function clearPWD(){
  PWD.value = null
}

async function confirmPWD() {
  let user_pwd = await apiService.get_pwd(user.user_id);
  user_pwd = user_pwd.toString()
  if (PWD.value === null){
    ElMessage.warning('密码不能为空！');
  }else {
    if (PWD.value === user_pwd){
      ElMessage.success('下载成功！');
      downloadAsPDF()
      formShow.value = false
    }else {
      ElMessage.warning('密码错误！');
      PWD.value = null
      error_number.value--
      if (error_number.value === 0){
        router.replace('/View403')
      }
    }
  }
}
</script>

<style scoped>
.center-box {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 98%;
}

.content-box {
  margin-top: 1%;
  width: 80%;
  height: 95%;
  border: 1px solid #000;
  padding: 20px;
  background-color: #fff;
  font-family: 'Times New Roman', Times, serif;
  font-size: 16px;
  line-height: 1.5;
}


.table {
  border-collapse: collapse;
  margin-top: 20px;
  margin-bottom: 20px;
  width: 100%;
}

.table td,
.table th {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.table th {
  background-color: #f2f2f2;
}

.label {
  font-weight: bold;
  width: 30%;
}

.footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 30px;
}

.down_btn {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  position: absolute;
  right: 12%;
  bottom: 20%;
}

</style>