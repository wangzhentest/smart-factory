<template>
  <el-row :gutter="16">
    <el-col :span="8">
      <div class="statistic-card" style="margin-left: 10%">
        <el-statistic :value="2" :value-style="{ color: 'white'}">
          <template #title>
            <div style="display: inline-flex; align-items: center;color: white;font-size: 20px" >
              今日安全事故
              <el-tooltip effect="dark" content="每日事故数量的统计" placement="top">
                <el-icon style="margin-left: 4px" :size="20">
                  <Warning />
                </el-icon>
              </el-tooltip>
            </div>
          </template>
        </el-statistic>
        <div class="statistic-footer">
          <div class="footer-item" style="color: white">
            <span>相比昨日</span>
            <span class="red">
              12
              <el-icon>
                <Bottom />
              </el-icon>
            </span>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="statistic-card" style="margin-left: 5%">
        <el-statistic :value="10" :value-style="{ color: 'white' }">
          <template #title>
            <div style="display: inline-flex; align-items: center;color: white;font-size: 20px" >
              今日安全提醒
              <el-tooltip effect="dark" content="每日安全提醒数量的统计" placement="top">
                <el-icon style="margin-left: 4px" :size="20">
                  <Warning />
                </el-icon>
              </el-tooltip>
            </div>
          </template>
        </el-statistic>
        <div class="statistic-footer">
          <div class="footer-item" style="color: white">
            <span>相比昨日</span>
            <span class="red">
              4
              <el-icon>
                <Top />
              </el-icon>
            </span>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="statistic-card">
        <el-statistic value="77%" :value-style="{ color: 'white' }">
          <template #title>
            <div style="display: inline-flex; align-items: center;color: white;font-size: 20px" >
              事故解决率
              <el-tooltip effect="dark" content="每日解决事故的数量与每日数量总数的比值" placement="top">
                <el-icon style="margin-left: 4px" :size="20">
                  <Warning />
                </el-icon>
              </el-tooltip>
            </div>
          </template>
        </el-statistic>
        <div class="statistic-footer">
          <div class="footer-item" style="color: white">
            <span>相比昨日</span>
            <span class="red">
              12%
              <el-icon>
                <CaretBottom />
              </el-icon>
            </span>
          </div>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {
  ArrowRight,
  CaretBottom,
  CaretTop,
  Warning,
} from '@element-plus/icons-vue'
</script>

<style scoped>
.statistic-card {
  height: 100%;
  padding: 20px;
  border-radius: 4px;
  background-color: rgba(0, 0, 0, 0.25);
  width: 70%;
  margin-top: 5%;
}

.statistic-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  font-size: 12px;
  margin-top: 16px;
}

.statistic-footer .footer-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.statistic-footer .footer-item span:last-child {
  display: inline-flex;
  align-items: center;
  margin-left: 4px;
}

</style>
