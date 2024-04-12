<template>
  <div class="main">
    <div class="label">
      <el-button
          type="info"
          :icon="ArrowLeftBold"
          @click="switchLocation('left')"
          style="margin-right: 10px"
      />
      <h2 style="color: white">{{ locationName }}</h2>
      <el-button
          type="info"
          :icon="ArrowRightBold"
          @click="switchLocation('right')"
          style="margin-left: 10px"
      />
    </div>
    <div class="videoArea" ref="videoAreaRef">
      <div style="overflow: auto;width: 100%;height: 100%" @dblclick="toggleFullscreen">
        <div class="video-container" style="width: 46%;height: 45%;" v-for="(item,index) in cameras" :key="index">
          <div style="width: 100%;height: 30px;position: absolute">
            <span style="position: absolute;color: white;font-size: 20px"> {{item.equipment_id}}-{{item.regulated_area}}</span>
            <span style="position: absolute;right:0;color: white;font-size: 20px">温度:{{item.temperature}}℃ 湿度:{{item.humidity}}% </span>
          </div>

          <video ref="videoRef" autoplay loop muted style="height: 100%;width: 100%">
            <source :src="item.camera_source" type="video/mp4">
          </video>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import apiService from "../services/apiService.js";
import {ArrowLeftBold, ArrowRightBold} from '@element-plus/icons-vue'

const areaWidth = ref(0)
const areaHeight = ref(0)
const locationName = ref('车间A')
const cameras = ref(null)
const locations = ['车间A', '车间B', '仓库A']
onMounted(() => {
  const observer = new ResizeObserver((entries) => {
    entries.forEach((entry) => {
      areaWidth.value = entry.contentRect.width
      areaHeight.value = entry.contentRect.height
    });
  });
  getCameras("车间A")
});
function switchLocation(direction) {
  const currentIndex = locations.indexOf(this.locationName);
  let newIndex = direction === 'left' ? currentIndex - 1 : currentIndex + 1;
  if (newIndex < 0) {
    newIndex = locations.length - 1;
  } else if (newIndex >= locations.length) {
    newIndex = 0;
  }
  this.locationName = locations[newIndex];
  getCameras(locationName.value)
}

const videoAreaRef = ref(null);

async function getCameras(areaName) {
  locationName.value = areaName
  cameras.value = null
  cameras.value = await apiService.get_cameras(areaName);
}

const toggleFullscreen = () => {
  const videoArea = videoAreaRef.value;
  if (!document.fullscreenElement) {
    if (videoArea.requestFullscreen) {
      videoArea.requestFullscreen();
    } else if (videoArea.mozRequestFullScreen) {
      videoArea.mozRequestFullScreen();
    } else if (videoArea.webkitRequestFullscreen) {
      videoArea.webkitRequestFullscreen();
    } else if (videoArea.msRequestFullscreen) {
      videoArea.msRequestFullscreen();
    }
  } else {
    if (document.exitFullscreen) {
      document.exitFullscreen();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
    } else if (document.webkitExitFullscreen) {
      document.webkitExitFullscreen();
    } else if (document.msExitFullscreen) {
      document.msExitFullscreen();
    }
  }
};

</script>

<style scoped>
.main {
  height: 95vh;
}
.label{
  width: 100%;
  height: 50px;
  background-color: black;
  display: flex;
  justify-content: center;
  align-items: center;
}
.videoArea {
  display: flex;
  justify-content: center;
  align-items: center;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 10px;
  width: 100%;
  height: 90%;
  overflow: hidden;
}

.video-container {
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.video-container video {
  border-radius: inherit;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.video-container {
  width: 96%;
  height: 96%;
  position: relative;
  border: 2px white solid;
  background-color: #333333;
  float: left;
  margin-left: 2%;
  margin-top: 2%
}

.video-container video {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
}
.main {
  width: 100%;
  height: 100%;
}

.videoArea{
  width: 100%;
  height: 94%;
}

</style>