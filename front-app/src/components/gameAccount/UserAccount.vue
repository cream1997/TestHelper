<script setup lang="ts" name="UserAccount">
import {onMounted, reactive, ref} from "vue";
import {post} from "@/axios/axios";
import {useAccountStore} from "@/store/account";
import {checkAccountNotNull} from "@/tools/CheckFormUtil";
import Cookies from "js-cookie";
import router from "@/router";
import type Server from "@/interface/Server";
import {Tip} from "@/tools/CommonTools";
import type UserVO from "@/interface/UserVO";

const allServer = reactive<Array<Server>>([])
const selectedServer = ref<Server>();
let username = ref<string>("")
let password = ref<string>("")
const accountStore = useAccountStore();
const accountId = accountStore.accountId;

function getUserVO(): UserVO {
  checkAccountNotNull(username.value, password.value)
  return {
    accountId,
    username: username.value,
    password: username.value
  }
}

function registerUser() {
  let userVO = getUserVO();
  post("/registerUser", userVO).then((okMsg: string) => {
    Tip.success(okMsg)
  })
}

function logoutAccount() {
  Cookies.set("accountInfo", "")
  router.push("/login")
}

onMounted(() => {
  post("/fetchServerList")
      .then((serverList: Array<Server>) => {
        allServer.push(...serverList);
        defaultSelectServer();
      })
})

function defaultSelectServer() {
  allServer.forEach(server => {
    if (server.name.includes("研发服")) {
      selectedServer.value = server;
    }
  })
}

</script>

<template>

  <form class="login-form" action="javascript:">
    <h4 class="form-title">游戏账号登录
      <button class="logout-account-button" @click="logoutAccount">退出</button>
    </h4>
    <label for="name" class="username-label">
      <input type="text" id="name" name="name" v-model="username" placeholder="用户名" autocomplete="off"
             list="user-list"/>
      <datalist id="user-list">
        <option></option>
      </datalist>
    </label>

    <label for="password" class="password-label">
      <input type="password" id="password" name="password" v-model="password" placeholder="密码"/>
    </label>
    <div class="server-selector">
      服务器：
      <select class="server-selector-option" v-model="selectedServer">
        <option v-for="(server,index) in allServer" :value="server" :key="index">
          {{ server.name }}
        </option>
      </select>
    </div>
    <div class="button-box">
      <button class="user-button">登录</button>
      <button class="user-button" @click="registerUser">注册</button>
    </div>
  </form>
</template>

<style scoped>
.login-form {
  display: flex;
  flex-wrap: wrap;
}

.form-title, .username-label, .password-label, .server-selector {
  width: 100%;
  text-align: center;
}

.form-title {
  margin: 0 0;
  height: 20px;
}

.button-box {
  width: 100%;
  text-align: center;
}

input {
  height: 20px;
}

.user-button {
  margin-top: 3px;
  font-size: 15px;
}

.server-selector {
  font-size: 18px;
}

.server-selector-option {
  font-size: inherit;
}
</style>