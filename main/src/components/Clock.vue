<template>
  <div class="clock">
    <h1 class="time">{{ currentTime }}</h1>
    <h2 class="day">{{ formattedDate }}</h2>
    <h2 class="day">{{ month }}&nbsp;&nbsp;{{ day }}</h2>
  </div>
</template>

<script>
import { ref, watch, onMounted, onUnmounted } from 'vue';

export default {
  name: 'TT',
  setup() {
    const formattedDate = ref('');
    const currentTime = ref('');
    const month = ref('');
    const day = ref('');

    const updateFormattedDate = () => {
      const date = new Date();
      const options = { weekday: 'long' };
      const options2 = { month: 'long' };
      const options3 = { day: 'numeric' };
      formattedDate.value = date.toLocaleString('en-US', options);
      month.value = date.toLocaleString('en-US', options2);
      day.value = date.toLocaleString('en-US', options3);
    };

    // 在组件挂载时更新日期
    onMounted(() => {
      updateFormattedDate();
    });

    // 在组件卸载时停止定时器
    onUnmounted(() => {
      clearInterval(timerId);
    });

    // 使用定时器每秒更新时间
    const timerId = setInterval(() => {
      currentTime.value = new Date().toLocaleTimeString('en-US', {
        hour12: false,
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric',
      });
    }, 1000);

    // 监听 formattedDate 的变化，并在变化时更新日期
    watch(formattedDate, () => {
      updateFormattedDate();
    });

    return {
      formattedDate,
      currentTime,
      month,
      day,
    };
  },
};
</script>

<style scoped>
.clock {
  width: 100%;
  height: 14%;
  align-items: center;
  background-color: #333;
  padding: 20px;
}

.time {
  color: white;
  font-size: 3em;
  font-weight: bold;
  margin-bottom: 10px;
  margin-left: 10px;
}

.day {
  color: white;
  font-size: 2em;
  font-weight: bold;
  margin-bottom: 10px;
  margin-left: 10px;
}
</style>
