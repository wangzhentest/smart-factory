<template>
  <div class="main">
    <div class="search-bar">
      <el-select v-model="searchType" placeholder="请选择搜索列">
        <el-option label="事故类型" value="事故类型"></el-option>
        <el-option label="事故日期" value="事故日期"></el-option>
        <el-option label="发生地点" value="发生地点"></el-option>
        <el-option label="监控id" value="监控id"></el-option>
        <el-option label="处理完毕日期" value="处理完毕日期"></el-option>
        <el-option label="处理人员" value="处理人员"></el-option>
      </el-select>
      <el-input v-model="search" placeholder="输入搜索关键字" style="margin-left: 2%"></el-input>
      <el-button type="info" @click="handleSearch" style="margin-left: 2%">搜索</el-button>
      <el-input placeholder="已处理事故页面" disabled style="width: 120px;margin-left: 450px"></el-input>
      <el-button style="position: absolute;right:0" @click="goToOther">切换</el-button>
    </div>
    <el-table :data="filteredData || incidentStore.FinishedList" style="width: 100%" table-layout="auto" stripe border class="incident_table" height="92%" size="large">
      <el-table-column label="事故id" prop="incident_id" align="center"/>
      <el-table-column label="事故类型" prop="incident_type" align="center"/>
      <el-table-column label="事故发生日期" prop="occur_date" align="center"/>
      <el-table-column label="事故地点" prop="location" align="center"/>
      <el-table-column label="处理完毕日期" prop="solve_date" align="center"/>
      <el-table-column label="监控id" prop="equipment_id" align="center"/>
      <el-table-column label="处理人员" prop="resolve_employee_id" align="center"/>
      <el-table-column label="相关操作" align="center">
        <template #default="scope">
          <el-button @click="handleReplay(scope.$index)" size="large">查看记录</el-button>
          <el-button type="danger" @click="handleGetReport(scope.$index)" size="large">查看报表</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="videoBoxShow" title="事故记录">
      <div v-if="videoBoxShow" class="videoBox">
        <VideoArea>
          <template v-slot:btn>
            <el-button class="invisible-button" type="text" @click="download_video" plain >
              <el-icon :size="18" >
                <Download/>
              </el-icon>
            </el-button>
          </template>
          <template v-slot:video>
            <img :src="videoSrc" style="width: 100%;height: 100%"/>
          </template>
        </VideoArea>
      </div>
    </el-dialog>
    <el-dialog v-model="formShow" title="请确认身份" @close="clearPWD" width="50vh">
      <el-input v-model="PWD" placeholder="请输入密码"/><br><br>
      <el-button @click="confirmPWD" type="danger">确定</el-button>
      <el-button @click="formShow = false">取消</el-button>
    </el-dialog>
    <el-dialog v-model="reportBoxShow" title="报表查看" top="5vh">
      <div class="reportBox" v-if="reportBoxShow">
        <Report :reportObj="reportObj"></Report>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref, onMounted, onBeforeUnmount, reactive} from 'vue';
import apiService from "../../services/apiService";
import {useRouter} from 'vue-router';
import VideoArea from '../commen/VideoArea.vue'
import Report from '../commen/Report.vue'
import {ElMessage,ElNotification } from "element-plus";
import { h } from 'vue'
import {useIncidentStore} from "../../stores/incident";

const incidentStore = useIncidentStore()
const search = ref('');
const tableData = ref(null);
const searchType = ref(null);
const filteredData = ref(null);
const router = useRouter();
const videoBoxShow = ref(false)
const reportBoxShow = ref(false)
const formShow = ref(false)
const videoSrc = ref('main/public/images/fire_2023.10.14_19.06.jpg')
const reportObj = ref(null)
const PWD = ref(null)
let timer;

onMounted(() => {
  getIncidents();
  open1()
});

const open1 = () => {
  ElNotification({
    title: '提示',
    message: h('i', { style: 'color: teal' }, '已处理事故页面'),
    type: 'warning',
    duration:1000,
    position:"top-left"
  })
}

onBeforeUnmount(() => {
  clearInterval(timer);
});

function goToOther(){
  router.push('/UntreatedIncidents')
}

const getIncidents = async () => {
  clearInterval(timer);
  timer = setInterval(async () => {
    await incidentStore.getFinishedIncident();
  }, 1000);

};

const get_summary_by_id = async (reportId) => {
  reportObj.value = await apiService.get_summary_by_id(reportId);
};

const handleReplay = (index) => {
  videoSrc.value = incidentStore.FinishedList[index].on_site_picture
  videoBoxShow.value = true
};

const handleGetReport= (index) => {
  reportBoxShow.value = true
  get_summary_by_id(incidentStore.FinishedList[index].incident_id)
};

function download_video(){
  formShow.value = true
}

function clearPWD(){
  PWD.value = null
}

const handleSearch = () => {
  if (search.value.trim() === '') {
    filteredData.value = null;
    return;
  }

  const type = searchType.value;
  const keyword = search.value.trim();

  filteredData.value = tableData.value.filter((item) => {
    switch (type) {
      case '事故类型':
        return item.incident_type.includes(keyword);
      case '事故日期':
        return item.date_time.includes(keyword);
      case '发生地点':
        return item.location.includes(keyword);
      case '监控id':
        return item.camera_id.includes(keyword);
      case '处理人员':
        return item.resolve_employee_id.includes(keyword);
      default:
        return false;
    }
  });
};
</script>

<style scoped>
.main {
  width: 100%;
  height: 100%;
}
.incident_table {
  top: 5%;
  margin: 1%;
  position: absolute;
  z-index: 1;
}
.search-bar {
  width: 100%;
  margin-left: 1%;
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  margin-top: 1%;
  position: absolute;
  z-index: 1;
}
.videoBox{
  width: 100%;
  height: 80%;
}

.reportBox{
  margin-bottom: 10%;
}

.invisible-button {
  position: absolute;
  right: 5%;top: 2.5%;
  background: none;
  border: none;
  padding: 0;
  cursor: pointer;
  outline: none;
}

</style>
