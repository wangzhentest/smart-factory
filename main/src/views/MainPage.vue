<template>
  <div class="container">
    <div class="left-box">
      <Clock/>
      <hr>
      <div class="person_message">
        <h1>管理员</h1>
      </div>
      <hr>
      <ul>
        <li class="item" @click="other_router(0)">
          <el-icon :size="25">
            <House/>
          </el-icon>&nbsp;数字工厂
        </li>
        <li class="item" @click="other_router(1)">
          <el-icon :size="25">
            <Van/>
          </el-icon>&nbsp;巡逻小车
        </li>
        <li class="item" @click="other_router(2)">
          <el-icon :size="25">
            <Warning/>
          </el-icon>
          <el-badge :value="untreated_incidents_length" class="item">
            &nbsp;事故列表
          </el-badge>
        </li>
        <li class="item" @click="other_router(3)">
          <el-icon :size="25">
            <Odometer />
          </el-icon>&nbsp;数据分析
        </li>
        <li class="item" @click="other_router(4)">
          <el-icon :size="25">
            <Monitor/>
          </el-icon>&nbsp;实时监控
        </li>
        <li class="item" @click="other_router(5)">
          <el-icon :size="25">
            <Tickets/>
          </el-icon>&nbsp;智慧考勤
        </li>
        <li class="item" @click="other_router(6)">
          <el-icon :size="25">
            <User/>
          </el-icon>&nbsp;人员信息
        </li>
        <li class="item" @click="other_router(7)">
          <el-icon :size="25">
            <Upload/>
          </el-icon>&nbsp;异常上报
        </li>
        <br>
        <hr>
        <li class="item" @click="other_router(8)">
          <el-icon :size="25">
            <Phone/>
          </el-icon>&nbsp;联系我们
        </li>
        <li class="item" @click="other_router(9)">
          <el-icon :size="25">
            <Back/>
          </el-icon>&nbsp;退出
        </li>
      </ul>
      <div class="user-info">
        <img src="../assets/images/factory_logo.png" alt="">
        <span>SafeGuard</span>
        <i class="icon icon-gear"></i>
      </div>
    </div>
    <el-dialog v-model="dialogFormVisible" title="异常上报" destroy-on-close width="30vw">
      <el-form :model="form" v-if="dialogFormVisible">
        <el-form-item label="反馈类型" label-width="140px">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option label="系统故障" value="系统故障"/>
            <el-option label="校园异常事件" value="校园异常事件"/>
          </el-select>
        </el-form-item>
        <el-form-item label="反馈原因" label-width="140px">
          <el-input v-model="form.reason" autocomplete="off" placeholder="请输入原因"/>
        </el-form-item>
        <el-form-item label="补充内容" label-width="140px">
          <el-input v-model="form.other" autocomplete="off" placeholder="无补充可不填写"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancel_form">取消</el-button>
        <el-button type="danger" @click="input_form">提交</el-button>
      </span>
      </template>
    </el-dialog>
    <el-dialog v-model="centerDialogVisible" title="联系我们" width="50vh" center>
      <span style="text-align: center">
        {{ '如果遇到无法解决的问题，可以通过下面的方式联系我们'}}<br>
        {{ '电话: 123456' }}<br>
        {{ '地址：浙江省杭州市'}}
      </span>
    </el-dialog>
    <div class="right-box">
      <div class="handler" :class="{close: isClose}" @click="toggleHandler"></div>
      <div class="router_area" >
        <transition name="el-fade-in">
          <router-view></router-view>
        </transition>
      </div>
    </div>
  </div>
</template>
<script setup>
import Clock from "../components/Clock.vue";
import {onMounted, reactive, ref, onBeforeUnmount} from 'vue';
import {useRouter} from 'vue-router';
import apiService from "../services/apiService";
import {ElMessage, ElNotification, ElMessageBox} from 'element-plus'
import {useMessageStore} from "../stores/message";
import {useIncidentStore} from "../stores/incident";

const MessageStore = useMessageStore();
const IncidentStore = useIncidentStore();
let untreated_incidents_length = ref(null);
let isClose = ref(false)
const router = useRouter();
const dialogFormVisible = ref(false)
const centerDialogVisible = ref(false)
const not_reminded_messages = ref(null)
const form = reactive({
  reason: '',
  type: '',
  other: ''
})
onMounted(() => {
  startTimer()
});

onBeforeUnmount(() => {
  stopTimer()
});

function other_router(id) {
  if (id === 0) {
    router.push('/DigitalFactory')
  } else if (id === 1) {
    router.push('/PatrolCar')
  }else if (id === 2) {
    router.push('/Incidents')
  } else if (id === 3) {
    router.push('/DataAnalysis')
  } else if (id === 4) {
    router.push('/Monitoring')
  } else if (id === 5) {
    router.push('/Attendance')
  } else if (id === 6) {
    router.push('/Staff')
  } else if (id === 7) {
    dialogFormVisible.value = true
  } else if (id === 8) {
    centerDialogVisible.value = true
  } else if (id === 9) {
    ElMessageBox.confirm(
        '您确定要退出吗？',
        'Warning',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
    ).then(() => {
      router.replace('/')
      ElNotification({
        title: '退出成功',
        message: '感谢您的使用！',
        type: "success",
      });
      stopTimer()
    }).catch(() => {
      ElMessage({
        type: 'info',
        message: '退出已取消',
      })
    })
  }
}

const toggleHandler = () => {
  isClose.value = !isClose.value
  const leftBox = document.querySelector('.left-box')
  if (isClose.value) {
    leftBox.style.width = 0
  } else {
    leftBox.style.width = '250px'
  }
}

function cancel_form() {
  dialogFormVisible.value = false
  ElMessage({
    type: 'info',
    message: '取消反馈',
  })
}

const timer = ref(null);

function startTimer() {
  if (timer.value === null) {
    timer.value = setInterval(() => {
      get_not_reminded_messages()
      get_untreated_incidents()
    }, 2000);
  }
}

function stopTimer() {
  clearInterval(timer.value);
  timer.value = null;
}

const get_untreated_incidents = async () => {
  const untreated_incidents = await apiService.get_untreated_incidents();
  untreated_incidents_length.value = untreated_incidents.length
};

const get_not_reminded_messages = async () => {
  stopTimer()
  not_reminded_messages.value = await apiService.get_not_reminded_messages();
  if (not_reminded_messages.value.length > 0){
    for (let i = 0; i < not_reminded_messages.value.length; i++) {
      await remind_message(not_reminded_messages.value[i].id)
      setTimeout(showMessage(not_reminded_messages.value[i]), 1000);
    }
  }
  startTimer()
};

function showMessage(newMessage){
  const message = `监控:${newMessage.camera_id}发出警告${newMessage.occur_date}时,在${newMessage.occur_place}发生${newMessage.alarm_reason}`
  remind_message(newMessage.id)
  ElNotification({
    title: "警告",
    message: message,
    type: "warning",
    duration: 0,
  });
}

async function remind_message(id) {
  await apiService.remind_message(id);
}

</script>
<style>
.router_area {
  width: 96%;
  height: 100%;
  position: absolute;
  right: 2%;
  background-color: #333;
  z-index: 2
}

* {
  margin: 0;
  padding: 0;
}

.item {
  font-size: 20px;
}

.person_message {
  width: 100%;
  height: 4%;
  text-indent: 30px;
}

.container {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  background-size: cover;
  display: flex;
}

.icon {
  color: #fff;
  font-size: 30px;
}

.left-box {
  width: 250px;
  height: 100%;
  background-color: #333;
  backdrop-filter: blur(30px);
  position: relative;
  color: #fff;
  font-size: 14px;
  display: flex;
  flex-direction: column;
  transition: 0.5s ease;
  white-space: nowrap;
}

.left-box ul {
  list-style: none;
  width: 90%;
  margin: 25px auto;
  flex: 1;
}

.left-box li {
  height: 46px;
  display: flex;
  align-items: center;
  padding-left: 12px;
  border-radius: 10px;
  cursor: pointer;
  transition: 0.3s;
}

.left-box li:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.left-box hr {
  width: 90%;
  margin: 18px auto;
  border: none;
  border-top: 1px solid rgba(255, 255, 255, 0.2);
}

.left-box .icon {
  margin-right: 16px;
}

.user-info {
  border-top: 1px solid rgba(255, 255, 255, 0.2);
  display: flex;
  align-items: center;
  padding: 24px;
}

.user-info img {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 14px;
}

.user-info span {
  flex: 1;
}

.user-info .icon {
  font-size: 20px;
  margin-right: 0;
}

.right-box {
  background-color: #0f0f11;
  flex: 1;
  position: relative;
  display: flex;
  flex-direction: column;
}


.right-box .handler {
  width: 10px;
  height: 70px;
  position: absolute;
  left: 10px;
  top: 50%;
  transform: translateY(-50%);
  z-index: 1;
  cursor: pointer;
  opacity: 0;
  transition: opacity 0.3s;
}

.right-box .handler::before,
.right-box .handler::after {
  content: "";
  background-color: rgba(255, 255, 255, 0.2);
  position: absolute;
  left: 0;
  width: 100%;
  height: 50%;
  border-radius: 10px 10px 0 0;
  transition: 0.2s;
}

.right-box .handler::after {
  bottom: 0;
  border-radius: 0 0 10px 10px;
}

.right-box:hover .handler {
  opacity: 1;
}

.right-box .handler:hover::before {
  transform: skewX(-15deg);
}

.right-box .handler:hover::after {
  transform: skewX(15deg);
}

.right-box .handler.close:hover::before {
  transform: skewX(15deg);
}

.right-box .handler.close:hover::after {
  transform: skewX(-15deg);
}


.dialog-footer button:first-child {
  margin-right: 10px;
}

</style>
