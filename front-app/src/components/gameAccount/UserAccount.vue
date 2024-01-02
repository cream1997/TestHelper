<script setup lang="ts" name="UserAccount">
import {ref} from "vue";

let username = ref()
let password = ref()
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
      服务器：<select>
      <option>1</option>
      <option>2</option>
      <option>3</option>
    </select>
    </div>
    <div class="button-box">
      <button>登录</button>
      <button>注册</button>
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

.username-label, .password-label {
  line-height: 30px;
}

.button-box {
  width: 100%;
  text-align: center;
}
</style>