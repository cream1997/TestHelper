<script setup lang="ts" name="Login">
import {onMounted, onUnmounted, ref} from "vue";
import {useRouter} from "vue-router";
import {post} from "@/axios/axios";
import {ElMessage as Tip} from "element-plus";
import {useAccountStore} from "@/store/account";
import {checkAccountNotNull} from "@/tools/CheckFormUtil";
import type Account from "@/interface/Account";

const router = useRouter();

let username = ref("admin");
let password = ref("admin");

const account = defaultAccount();
if (account) {
  // todo 默认账号直接登录
}

function login() {
  checkAccountNotNull(username.value, password.value);
  post("/login", {
    username: username.value,
    password: password.value
  }).then((account: Account) => {
    Tip.success("登录成功");
    // 存入accountStore
    const accountStore = useAccountStore();
    accountStore.accountId = account.id;
    router.push({
      path: "/home",
      query: {accountId: account.id}
    })
  })
}

function register() {
  checkAccountNotNull(username.value, password.value);
  post("/register",
      {
        username: username.value,
        password: password.value
      }
  ).then((res: any) => {
    Tip.success(res)
  })
}

function defaultAccount() {
  // todo 后续需要修改（可能根据本地存储）
  return undefined;
}

function setLoginPageStyle() {
  onMounted(() => {
    document.querySelector("body")
        ?.setAttribute("style",
            `background: linear-gradient(to right, #65CBF7, #B3A5FC);
                   width: 100vw;
                   height: 100vh;
                   margin: 0;`)
  })
  onUnmounted(() => {
    document.querySelector("body")
        ?.removeAttribute("style")
  })
}

setLoginPageStyle();
</script>

<template>
  <div class="box">
    <div class="left">
      <img src="../assets/flower.jpg" alt="loading">
    </div>
    <div class="right">
      <h1>测试助手</h1>
      <input type="text" v-model="username" class="inputItem" placeholder="请输入账号">
      <input type="password" v-model="password" class="inputItem" placeholder="请输入密码">
      <a href="#" class="forgetPassword">忘记密码?</a>
      <button class="btn" @click="login">登录</button>
      <button class="btn" @click="register">注册</button>
    </div>
  </div>
</template>

<style scoped>
body {
  background: linear-gradient(to right, #65CBF7, #B3A5FC);
  width: 100vw;
  height: 100vh;
  margin: 0;
}

.box {
  width: 60%;
  height: 450px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, .8);
  display: flex;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.left {
  width: 65%;
}

.left > img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.right {
  width: 35%;
  height: 100%;
  background-color: #fff;
  padding: 0 20px;
}

h1 {
  text-align: center;
  padding-top: 45px;
  margin-top: 0;
}

.inputItem {
  height: 44px;
  padding: 0;
  padding-left: 5px;
  border: none;
  background: none;
  outline: none;
  border-bottom: 3px solid #B3A5FC;
  width: 100%;
  font-size: 18px;
  margin-top: 20px;
}

.forgetPassword {
  margin-top: 25px;
  color: #9C3493;
  text-align: right;
  display: block;
}

.btn {
  background: linear-gradient(to right, #65CBF7, #B3A5FC);
  color: #9C3493;
  font-weight: bold;
  border: none;
  border-radius: 30px;
  height: 46px;
  width: 80%;
  font-size: 18px;
  display: block;
  margin: auto;
  margin-top: 10px;
  cursor: pointer;
}

.btn:hover {
  background: linear-gradient(to right, #B3A5FC, #65CBF7);
}

.btn:active {
  background: linear-gradient(to right, #51458d, #65CBF7);
}

@media screen and(min-width: 960px) {
  .box {
    max-width: 950px;
    min-width: 750px;
  }
}
</style>