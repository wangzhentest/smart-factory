<template>
  <div class="login-container">
    <h1 class="login-title">{{}}</h1>
    <form class="login-form">
      <el-input
          style="margin-top: 10%"
          class="login-input"
          v-model="account"
          placeholder="Account"
          clearable
      ></el-input>
      <el-input
          class="login-input"
          v-model="pwd"
          placeholder="Password"
          show-password
          clearable
      ></el-input>
      <el-button class="login-button" >{{}}</el-button>
    </form>
  </div>
</template>

<script setup>
import { ref} from "vue";
import apiService from "../services/apiService.js";
import { ElNotification } from "element-plus";
import { useRouter } from 'vue-router';
const account = ref("");
const pwd = ref("");
const router = useRouter();
const redirectToMainPage = (data) => {
  router.replace({
    path: 'MainPage',
    query: { data: JSON.stringify(data.value) }
  });
};


</script>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #222;
}

.login-title {
  font-size: 2.5rem;
  margin-bottom: 2rem;
  color: #ddd;
}

.login-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 30%;
  height: 30%;
  background-color: #333;
  padding: 2rem;
  border-radius: 5px;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.3);
}

.login-input {
  width: 80%;
  height: 20%;
  margin-bottom: 1rem;
  background-color: #444;
}

.login-button {
  font-size: 20px;
  color: white;
  width: 60%;
  height: 20%;
  border-radius: 20px;
  background-color: #555;
}
</style>