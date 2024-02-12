<script setup lang="ts" name="Login">
import {onMounted, onUnmounted, ref} from "vue";

import {useRouter} from "vue-router";
import {post} from "@/axios/axios";
import {Tip} from "@/tools/CommonTools";
import {useAccountStore} from "@/stores/account";
import {checkAccountNotNull} from "@/tools/CheckFormUtil";
import type Account from "@/interface/Account";
import Cookies from "js-cookie";
import config from "@/config.json";

const router = useRouter();
const AccountCookieKey = config.accountCookieKey;
let accountName = ref("admin");
let password = ref("admin");

function login() {
  checkAccountNotNull(accountName.value, password.value);
  post("/login", {
    username: accountName.value,
    password: password.value
  }).then((account: Account) => {
    Tip.success("登录成功");
    Cookies.set(AccountCookieKey, JSON.stringify(account), {
      expires: 30
    });
    // 存入accountStore
    const accountStore = useAccountStore();
    accountStore.accountId = account.id;
    router.push("/home");
  });
}

function register() {
  checkAccountNotNull(accountName.value, password.value);
  post("/register", {
    username: accountName.value,
    password: password.value
  }).then((res: any) => {
    Tip.success(res);
  });
}

function defaultLogin() {
  let accountCookie = Cookies.get(AccountCookieKey);
  if (accountCookie) {
    const account: Account = JSON.parse(accountCookie);
    // 检查token合法性
    post("/checkToken", {
      token: account.token
    }).then((tokenValid) => {
      if (tokenValid) {
        accountName.value = account.accountName;
        password.value = account.password;
        login();
      } else {
        Cookies.set(AccountCookieKey, "");
      }
    });
  }
}

function setLoginPageStyle() {
  onMounted(() => {
    document.querySelector("body")?.setAttribute(
      "style",
      `background: linear-gradient(to right, #65CBF7, #B3A5FC);
                   width: 100vw;
                   height: 100vh;
                   margin: 0;`
    );
  });
  onUnmounted(() => {
    document.querySelector("body")?.removeAttribute("style");
  });
  onMounted(() => {
    defaultLogin();
  });
}

setLoginPageStyle();

function forgetPassword() {
  Tip.info("请联系管理员！");
}
</script>

<template>
  <div class="box">
    <div class="left">
      <img src="../assets/flower.jpg" alt="loading" />
    </div>
    <div class="right">
      <h1>测试助手</h1>
      <input type="text" v-model="accountName" class="inputItem" placeholder="请输入账号" />
      <input type="password" v-model="password" class="inputItem" placeholder="请输入密码" />
      <a href="#" class="forgetPassword" @click="forgetPassword">忘记密码?</a>
      <button class="btn" @click="login">登录</button>
      <button class="btn" @click="register">注册</button>
    </div>
  </div>
</template>

<style scoped>
body {
  background: linear-gradient(to right, #65cbf7, #b3a5fc);
  width: 100vw;
  height: 100vh;
  margin: 0;
}

.box {
  width: 60%;
  height: 450px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.8);
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
  padding: 0 0 0 5px;
  border: none;
  background: none;
  outline: none;
  border-bottom: 3px solid #b3a5fc;
  width: 100%;
  font-size: 18px;
  margin-top: 20px;
}

.forgetPassword {
  margin-top: 25px;
  color: #9c3493;
  text-align: right;
  display: block;
}

.forgetPassword:hover {
  color: #51458d;
}

.forgetPassword:active {
  color: #b3a5fc;
}

.btn {
  background: linear-gradient(to right, #65cbf7, #b3a5fc);
  color: #9c3493;
  font-weight: bold;
  border: none;
  border-radius: 30px;
  height: 46px;
  width: 80%;
  font-size: 18px;
  display: block;
  margin: 10px auto auto;
  cursor: pointer;
}

.btn:hover {
  background: linear-gradient(to right, #b3a5fc, #65cbf7);
}

.btn:active {
  background: linear-gradient(to right, #51458d, #65cbf7);
}

@media screen and(min-width: 960px) {
  .box {
    max-width: 950px;
    min-width: 750px;
  }
}
</style>
