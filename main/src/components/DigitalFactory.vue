<template>
  <div class="main" ref="MainArea">
    <Factory :areaWidth="areaWidth" :areaHeight="areaHeight"/>
  </div>
</template>

<script setup>
import Factory from "./Model/Factory.vue";
import { onMounted, ref } from "vue";

const MainArea = ref(null);
const areaWidth = ref(0);
const areaHeight = ref(0);

onMounted(() => {
  const observer = new ResizeObserver((entries) => {
    entries.forEach((entry) => {
      areaWidth.value = entry.contentRect.width;
      areaHeight.value = entry.contentRect.height;
    });
  });
  observer.observe(MainArea.value);
});
</script>

<style scoped>
.main {
  width: 100%;
  height: 100%;
}
</style>
