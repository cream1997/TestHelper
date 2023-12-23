<script setup lang="ts" name="Login">
import {ref} from "vue";

let username = ref("admin")
let password = ref()

function login() {
  alert(username.value + "@" + password.value)
}

function register() {
  alert(username.value + "@" + password.value)
}
</script>

<template>
  <div id="container">
    <h1>测试助手</h1>
    <div class="form-box">
      <div class="item">
        <i class="fa fa-github-alt" style="font-size:24px"></i>
        <input type="text" v-model="username" placeholder="请输入用户名" required><br>
      </div>
      <div class="item">
        <i class="fa fa-search" style="font-size:24px"></i>
        <input type="password" v-model="password" placeholder="请输入密码" required><br>
      </div>
    </div>
    <button @click="login">登录</button>
    <button @click="register">注册</button>
  </div>
</template>

<style scoped>
body {
  background: url('../image/2.jpg') no-repeat;
  background-size: 100% auto;
}

#container {
  width: 30%;
  height: auto;
  margin: 13% auto 0;
  text-align: center;
  background: #00000060;
  padding: 20px 50px;
}

#container-box h1 {
  color: #fff;
}

#container .form-box .item {
  margin-top: 15px;
}

#container .form-box .item i {
  font-size: 18px;
  color: #fff;
}

#container .form-box .item input {
  width: 180px;
  font-size: 18px;
  border: 0;
  border-bottom: 2px solid #fff;
  padding: 5px 10px;
  background: #ffffff00;
  color: #fff;
}

#container button {
  margin-top: 20px;
  width: 190px;
  height: 30px;
  font-size: 20px;
  font-weight: 700;
  color: #fff;
  background-image: linear-gradient(to right, #74ebd5 0%, #9face6 100%);
  border: 0;
  border-radius: 15px;
}
</style>