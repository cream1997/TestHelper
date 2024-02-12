<script lang="ts">
export default {
  name: "Login"
};
</script>
<script setup lang="ts">
import {onMounted, onUnmounted, reactive} from "vue";
import {useRouter} from "vue-router";
import {Tip, Notice} from "@/tools/CommonTool";
import {useAccountStore} from "@/stores/account";
import type Account from "@/interface/Account";
import config from "@/config.json";
import type LoginVO from "@/interface/vo/account/LoginVO";
import {reqLogin, reqRegister, reqCheckToken} from "@/api/account/AccountAPI";
import {clearCookie, getCookie, setCookie} from "@/tools/CookieTool";
import {getDayTime} from "@/tools/TimeTool";

const router = useRouter();
const AccountCookieKey = config.accountCookieKey;

const loginVO = reactive<LoginVO>({
  username: "admin",
  password: "admin"
});

function login() {
  reqLogin(loginVO).then((account: Account) => {
    Notice.success({
      title: `Hi, ${getDayTime()}好~~`,
      message: "欢迎回来"
    });
    setCookie(AccountCookieKey, account, 30);
    // 存入accountStore
    useAccountStore().accountId = account.id;
    router.push("/home");
  });
}

function register() {
  reqRegister(loginVO).then((res: any) => {
    Tip.success(res);
  });
}

function defaultLogin() {
  const accountCookie = getCookie(AccountCookieKey);
  if (accountCookie) {
    const account: Account = JSON.parse(accountCookie);
    reqCheckToken(account.token).then((tokenValid) => {
      if (tokenValid) {
        loginVO.username = account.accountName;
        loginVO.password = account.password;
        login();
      } else {
        clearCookie(AccountCookieKey);
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
      <input type="text" v-model="loginVO.username" class="inputItem" placeholder="请输入账号" />
      <input
        type="password"
        v-model="loginVO.password"
        class="inputItem"
        placeholder="请输入密码"
      />
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
