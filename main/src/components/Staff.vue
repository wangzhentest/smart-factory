<template>
  <div class="main">
    <div class="descriptionsArea">
      <div style="width: 100%;height: 3%"></div>
      <el-descriptions size="large" border style="margin-left:1%;margin-right:1%;width: 98%;margin-bottom: 1%" v-for="(item,index) in EmployeeStore.employees" :key="index">
        <el-descriptions-item >
          <template #label>
            <el-icon :size="20">
              <UserFilled/>
            </el-icon>
            <span style="font-size: 18px">&nbsp;员工号</span>
          </template>
          <el-input style="width: 180px;" :readonly="true" :model-value="item.employee_id"/>
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <el-icon :size="20">
              <user/>
            </el-icon>
            <span style="font-size: 18px">&nbsp;姓名</span>
          </template>
          <el-input style="width: 180px;" :readonly="true" :model-value="item.name"/>
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <el-icon :size="20">
              <OfficeBuilding/>
            </el-icon>
            <span style="font-size: 18px">&nbsp;部门</span>
          </template>
          <el-tag size="large">&nbsp;{{item.department}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <el-icon :size="20">
              <Calendar/>
            </el-icon>
            <span style="font-size: 18px">&nbsp;出生日期</span>
          </template>
          <el-input style="width: 180px;" :readonly="true" :model-value="item.birth_date"/>
        </el-descriptions-item>

        <el-descriptions-item>
          <template #label>
            <el-icon :size="20">
              <Male/>
            </el-icon>
            <span style="font-size: 18px">&nbsp;性别</span>
          </template>
          <el-input style="width: 180px;" :readonly="true" :model-value="item.gender"/>
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <el-icon :size="20">
              <iphone/>
            </el-icon>
            <span style="font-size: 18px">&nbsp;联系方式</span>
          </template>
          <el-input style="width: 180px;" :readonly="true" :model-value="item.phone_number"/>
        </el-descriptions-item>
      </el-descriptions>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {useEmployeeStore} from "../stores/employee";

const EmployeeStore = useEmployeeStore()
const getEmployees = async () => {
  await EmployeeStore.getEmployees();
};

onMounted(() =>{
  getEmployees()
})

</script>

<style scoped>
.main {
  border-radius: 5%;
  width: 100%;
  height: 100%;
}
.descriptionsArea{
  background-color: white;
  margin-left: 1%;
  width: 100%;
  height: 97%;
  margin-top: 1%;
  overflow: auto;
}
</style>