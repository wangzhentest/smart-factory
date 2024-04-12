<template>
  <div class="user-info-square">
    <div class="avatar-container">
      <img :src="avatarUrl" :alt="altText" />
    </div>
    <div class="info-container">
      <div class="employee-id">{{ employeeId }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    avatarUrl: String,
    username: String,
    employeeId: String,
    attendanceRecord: String,
    altText: {
      type: String,
      default: 'User Avatar'
    }
  }
}
</script>

<style scoped>
.user-info-square {
  width: 200px;
  height: 90%;
  background-color: rgba(0, 53, 76, 0.32);
  flex-direction: column;
  align-items: center;
  border-radius: 10px;
  margin: 10px;
  display: inline-block;
}

.avatar-container {
  width: 100%;
  height: 50%;
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: flex-end;
}

.avatar-container img {
  width: 80px; /* 根据需要调整头像的宽度 */
  height: 80px; /* 根据需要调整头像的高度 */
  border-radius: 50%; /* 将图片设置为圆形 */
}

.info-container {
  margin-top: 20px;
  text-align: center;
}

.username {
  font-weight: bold;
}

.employee-id,
.attendance-record {
  margin-top: 5px;
}
</style>
