<script setup lang="ts" name="CreateRole">
import type AccountInfo from "@/interface/AccountInfo";
import {useAccountStore} from "@/stores/account";
import {reactive} from "vue";
import {Tip} from "@/tools/CommonTools";
import UserState from "@/interface/UserState";
import {post} from "@/axios/axios";
import type RoleItemVO from "@/interface/vo/role/RoleItemVO";

const accountInfo: AccountInfo = useAccountStore();
const accountId = accountInfo.accountId;

const createRoleVO = reactive<RoleItemVO>(
    {
      uid: accountInfo.uid,
      roleName: "",
      career: "1"
    })

function createRole() {
  if (!createRoleVO.roleName) {
    Tip.error("请输入角色名")
    return
  }
  if (createRoleVO.career === "0") {
    Tip.error("请选择职业")
    return
  }

  post("/createRole", createRoleVO)
      .then((createRole: RoleItemVO) => {
        accountInfo.roleItems.unshift(createRole);
        accountInfo.userState = UserState.SelectRole;
      })
}

function returnSelectRole() {
  accountInfo.userState = UserState.SelectRole;
}
</script>

<template>
  <div class="title">创建角色</div>
  <input type="text" placeholder="请输入角色名" v-model="createRoleVO.roleName">
  <br>
  选择职业：<select v-model="createRoleVO.career">
  <option value="1">职业1</option>
  <option value="2">职业2</option>
</select>
  <br>
  <button @click="createRole">创建角色</button>
  <button @click="returnSelectRole">返回</button>
</template>

<style scoped>
.title {
  text-align: center;
}

</style>