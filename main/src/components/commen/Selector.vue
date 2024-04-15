<template >
  <div>
    <div class="selectors">
      <p>安保人员</p>
      <el-select
          v-model="guardsResult"
          multiple
          placeholder="Select"
          style="width: 200px">
        <el-option
            v-for="item in EmployeeStore.safeEmployees"
            :key="item.employee_id"
            :label="item.name"
            :value="item.name"/>
      </el-select>
    </div>

    <el-button type="danger" style="position: absolute;right: 6%;margin-top: 2%" @click="handleStaffs">委派</el-button>
  </div>
</template>

<script setup>
import {defineProps, h, onMounted, ref} from "vue";
import {ElMessage, ElNotification} from 'element-plus'
import {useEmployeeStore} from "../../stores/employee";

const EmployeeStore = useEmployeeStore()
const guardsResult = ref([])
const mediaStaffsResult = ref([])
const repairMenResult = ref([])


const props = defineProps({
  incidentId: Number
});

const get_guards = async () => {
  await EmployeeStore.getSafeEmployees();
};

const open1 = (message) => {
  ElNotification({
    title: '提示',
    message: message,
    type: 'warning',
    duration:1000,
    position:"top-right"
  })
}

async function handleStaffs() {
  const combinedArray = guardsResult.value.concat(mediaStaffsResult.value, repairMenResult.value);
  if (combinedArray.length === 0) {
    ElMessage.warning('委派人员不能为空！');
  } else {
    const staffs_list = combinedArray.join(';');
    open1('事故：'+props.incidentId+'委派人员成功：'+staffs_list)
  }
}

onMounted(()=>{
  get_guards()
})

</script>

<style scoped>

</style>