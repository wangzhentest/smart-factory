<template>
  <div class="main">
    <div class="search-bar">
      <el-select v-model="searchType" placeholder="请选择搜索列">
        <el-option label="事故类型" value="事故类型"></el-option>
        <el-option label="事故日期" value="事故日期"></el-option>
        <el-option label="发生地点" value="发生地点"></el-option>
        <el-option label="监控id" value="监控id"></el-option>
        <el-option label="处理人员" value="处理人员"></el-option>
      </el-select>
      <el-input v-model="search" placeholder="输入搜索关键字" style="margin-left: 2%"></el-input>
      <el-button type="info" @click="handleSearch" style="margin-left: 2%">搜索</el-button>
      <el-input placeholder="未处理事故页面" disabled style="width: 120px;margin-left: 450px"></el-input>
      <el-button style="position: absolute;right: -1%" @click="goToOther">切换</el-button>
    </div>

    <el-table :data="filteredData || incidentStore.UnfinishedList" style="width: 100%" table-layout="auto" stripe border class="incident_table" height="92%" size="large">
      <el-table-column label="事故id" prop="incident_id" align="center"/>
      <el-table-column label="事故类型" prop="incident_type" align="center"/>
      <el-table-column label="事故日期" prop="occur_date" align="center"/>
      <el-table-column label="发生地点" prop="location" align="center"/>
      <el-table-column label="详细地点" prop="regulated_area" align="center"/>
      <el-table-column label="监测设备id" prop="equipment_id" align="center"/>
      <el-table-column label="相关操作" align="center">
        <template #default="scope">
          <el-button @click="handleReplay(scope.$index)" size="large">查看监控</el-button>
          <el-button type="danger" @click="handleStaffs(scope.$index)" size="large">委派人员</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="videoBoxShow" title="现场监控" top="10vh">
      <div v-if="videoBoxShow" class="videoBox">
<!--        <img src="http://localhost:5000/video_feed" style="width: 100%"/>-->
        <VideoArea>
          <template v-slot:video>
            <video ref="videoRef" autoplay loop style="height: 100%;width: 100%">
              <source src="/videos/replayVideos/fall.mp4" type="video/mp4">
            </video>
          </template>
        </VideoArea>
      </div>
    </el-dialog>
    <el-dialog v-model="selectorShow" title="处理人员委派" top="10vh" width="30vw" >
      <div v-if="selectorShow" style="margin-bottom: 6%">
        <Selector :incidentId="incidentId"></Selector>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref, onMounted, onBeforeUnmount, h} from 'vue';
import {useRouter} from 'vue-router';
import VideoArea from '../commen/VideoArea.vue'
import Selector from '../commen/Selector.vue'
import {ElNotification} from "element-plus";
import {useIncidentStore} from "../../stores/incident";

const incidentStore = useIncidentStore()
const search = ref('');
const tableData = ref(null);
const searchType = ref(null);
const filteredData = ref(null);
const router = useRouter();
const videoBoxShow = ref(false)
const selectorShow = ref(false)
const videoSrc = ref('null')
const incidentId = ref(null)
let timer;
onMounted(() => {
  getIncidents();
  open1()
});

onBeforeUnmount(() => {
  clearInterval(timer);
});

const open1 = () => {
  ElNotification({
    title: '提示',
    message: h('i', { style: 'color: teal' }, '未处理事故页面'),
    type: 'warning',
    duration:1000,
    position:"top-left"
  })
}

function goToOther(){
  router.push('/TreatedIncidents')
}

const getIncidents = async () => {
  clearInterval(timer);
  timer = setInterval(async () => {
    await incidentStore.getUnfinishedIncident();
  }, 1000);
};

const handleReplay = (index) => {
  videoSrc.value = incidentStore.UnfinishedList[index].video
  videoBoxShow.value = true
};

const handleStaffs = async (index) => {
  selectorShow.value = true
  incidentId.value = incidentStore.UnfinishedList[index].incident_id
};

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
        return item.incident_date.includes(keyword);
      case '发生地点':
        return item.incident_position.includes(keyword);
      case '监控id':
        return item.camera_id.includes(keyword);
      case '处理人员':
        return item.handle_staff_id.includes(keyword);
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
  margin: 1%;
}
.search-bar {
  width: 100%;
  margin-left: 1%;
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  margin-top: 1%;
}
.videoBox{
  width: 100%;
  height: 80%;
}
</style>
