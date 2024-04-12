<template>
  <div class="main">
    <div class="router_area">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import {useRouter} from 'vue-router';
import {onMounted} from "vue";
export default {
  setup(){
    const router = useRouter();
    onMounted(() =>{
      router.push('/UntreatedIncidents')
    })
  }
}
</script>

<style scoped>
.main{
  width: 100%;
  height: 100%;
}
.router_area {
  width: 98%;
  height: 100%;
  position: absolute;
  right: 2%;
  background-color: #333;
  z-index: 2
}
</style>