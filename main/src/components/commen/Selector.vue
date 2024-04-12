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
            v-for="item in guards"
            :key="item.guard_id"
            :label="item.guard_name"
            :value="item.guard_name"/>
      </el-select>
    </div>
    <div class="selectors">
      <p>医护人员</p>
      <el-select
          v-model="mediaStaffsResult"
          multiple
          placeholder="Select"
          style="width: 200px">
        <el-option
            v-for="item in mediaStaffs"
            :key="item.medical_staff_id"
            :label="item.medical_staff_name"
            :value="item.medical_staff_name"/>
      </el-select>
    </div>
    <div class="selectors">
      <p>维修人员</p>
      <el-select
          v-model="repairMenResult"
          multiple
          placeholder="Select"
          style="width: 200px">
        <el-option
            v-for="item in repairMen"
            :key="item.repairman_id"
            :label="item.repairman_name"
            :value="item.repairman_name"/>
      </el-select>
    </div>
    <el-button type="danger" style="position: absolute;right: 6%;margin-top: 2%" @click="handleStaffs">委派</el-button>
  </div>
</template>

<script setup>
import {defineProps, onMounted, ref} from "vue";
import apiService from "../../services/apiService";
import { ElMessage } from 'element-plus'
const guards = ref([])
const mediaStaffs = ref([])
const repairMen = ref([])
const guardsResult = ref([])
const mediaStaffsResult = ref([])
const repairMenResult = ref([])
const ifShow = ref(true)
onMounted(()=>{
  get_guards()
  get_medical_staffs()
  get_repairmen()
})

const props = defineProps({
  incidentId: Number
});

const get_guards = async () => {
  guards.value = await apiService.get_guards();
};

const get_medical_staffs = async () => {
  mediaStaffs.value = await apiService.get_medical_staffs();
};

const get_repairmen = async () => {
  repairMen.value = await apiService.get_repairmen();
};

async function handleStaffs() {
  const combinedArray = guardsResult.value.concat(mediaStaffsResult.value, repairMenResult.value);
  if (combinedArray.length === 0) {
    ElMessage.warning('委派人员不能为空！');
  } else {
    const staffs_list = combinedArray.join(';');
    await apiService.appoint_staffs(props.incidentId,staffs_list);
    ElMessage.success('委派成功！');
  }
}

</script>

<style scoped>

</style>