<script setup lang="ts" name="UserAccount">
import {ref} from "vue";
import {axios} from "@/axios/axios";
import {useAccountStore} from "@/store/account";
import {checkAccountNotNull} from "@/tools/CheckFormUtil";

let username = ref()
let password = ref()

function registerUser() {
  checkAccountNotNull(username.value, password.value)
  const accountStore = useAccountStore();
  axios.post("/registerUser", {
    accountId: accountStore.accountId,
    username: username.value,
    password: username.value
  }).then(res => {
    alert(res)
  })

}

</script>

<template>

  <form class="login-form" action="javascript:">
    <h4 class="form-title">游戏账号登录</h4>
    <label for="name" class="username-label">
      <input type="text" id="name" name="name" v-model="username" placeholder="用户名"/>
    </label>

    <label for="password" class="password-label">
      <input type="password" id="password" name="password" v-model="password" placeholder="密码"/>
    </label>
    <div class="server-selector">
      服务器：<select class="server-selector-option">
      <option>1</option>
      <option>2</option>
      <option>3</option>
    </select>
    </div>
    <div class="button-box">
      <button>登录</button>
      <button @click="registerUser">注册</button>
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

button {
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