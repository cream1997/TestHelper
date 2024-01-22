<script setup lang="ts" name="UserAccount">
import {onMounted, reactive, ref} from "vue";
import {post} from "@/axios/axios";
import {useAccountStore} from "@/store/account";
import {checkAccountNotNull} from "@/tools/CheckFormUtil";
import Cookies from "js-cookie";
import router from "@/router";
import type Server from "@/interface/Server";
import {MsgBox, Tip} from "@/tools/CommonTools";
import type UserVO from "@/interface/UserVO";
import type LoginUserInfoVO from "@/interface/LoginUserInfoVO";

const accountStore = useAccountStore();
const accountId = accountStore.accountId;
const allServer = reactive<Array<Server>>([])
const selectedServer = ref<Server>();
const userAccounts = reactive<Array<UserVO>>([])


let username = ref<string>("")
let password = ref<string>("")


function getUserVO(): UserVO {
  checkAccountNotNull(username.value, password.value)
  return {
    accountId,
    username: username.value,
    password: username.value
  }
}

function registerUser() {
  const userVO = getUserVO();
  post("/registerUser", userVO).then((okMsg: string) => {
    Tip.success(okMsg)
    userAccounts.unshift(userVO)
  })
}

function loginUser() {
  const userVO = getUserVO();
  post("/loginUser", userVO)
      .then((loginUserInfo: LoginUserInfoVO) => {
        accountStore.uid = loginUserInfo.uid;
        accountStore.roleItems.push(...loginUserInfo.roleItems)
      })
}

function logoutAccount() {
  Cookies.set("accountInfo", "")
  router.push("/login")
}

function fetchServerList() {
  post("/fetchServerList")
      .then((serverList: Array<Server>) => {
        allServer.push(...serverList);
        defaultSelectServer();
      })
}

function fetchUserAccounts() {
  post("/fetchUserAccounts", accountId)
      .then((res: Array<UserVO>) => {
        userAccounts.push(...res);
      })
}

onMounted(() => {
  if (accountId == 0) {
    router.push("/login");
    return
  }
  fetchServerList();
  fetchUserAccounts();
})

function defaultSelectServer() {
  allServer.forEach(server => {
    if (server.name.includes("研发服")) {
      selectedServer.value = server;
    }
  })
}

const matchAccount = ref(false);

function selectAccount(event: Event) {
  const target = event.target as HTMLInputElement | HTMLSelectElement
  const username = target.value;
  const matchingUser = userAccounts.find(user => user.username === username); // 使用find以提高效率
  if (matchingUser) {
    password.value = matchingUser.password; // 设置密码输入框的值
    matchAccount.value = true;
  } else {
    password.value = "";
    matchAccount.value = false;
  }
}

function removeAccount() {
  MsgBox.confirm(
      '确认操作？',
      '取消关联该账号',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        post("/unBindUser", username.value)
            .then((okMsg) => {
              Tip.success(okMsg)
              userAccounts.splice(userAccounts.findIndex(user => user.username === username.value), 1);
              username.value = "";
              password.value = "";
              matchAccount.value = false;
            })

      })
      .catch(() => {
        Tip.info("取消删除")
      })
}


</script>

<template>

  <form class="login-form" action="javascript:">
    <h4 class="form-title">游戏账号登录
      <button class="logout-account-button" @click="logoutAccount">退出</button>
    </h4>
    <label for="name" class="username-label">
      <input type="text" id="name" name="name" v-model="username" @change="selectAccount($event)" placeholder="用户名"
             autocomplete="off"
             list="user-list"/>
      <button v-if="matchAccount" @click="removeAccount" class="remove-account-btn">删除</button>
      <datalist id="user-list">
        <option v-for="(user,index) in userAccounts" :value="user.username" :key="index"/>
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
      <button class="user-button" @click="loginUser">登录</button>
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

.remove-account-btn {
  background-color: #e08080;
  width: 45px;
  margin-right: -45px;
  cursor: pointer;
}

.remove-account-btn:hover {
  background-color: #e07070;
}

.remove-account-btn:active {
  background-color: rgba(224, 112, 112, 0.4);
}

</style>