<script setup lang="ts">
import type AccountInfo from "@/interface/AccountInfo";
import {useAccountStore} from "@/store/account";
import type RoleItemVO from "@/interface/vo/RoleItemVO";
import {MsgBox, Tip} from "@/tools/CommonTools";
import type {Action} from "element-plus";
import {post} from "@/axios/axios";
import UserState from "@/interface/UserState";
import type RoleEnterVO from "@/interface/vo/RoleEnterVO";

const accountInfo: AccountInfo = useAccountStore();
let roleItems = accountInfo.roleItems;

function toCreateRolePage() {
  if (roleItems.length >= 4) {
    Tip.error("最多只能创建4个角色");
    return;
  }
  // 切换页面
  accountInfo.userState = UserState.CreateRole;
}

function loginRole(roleItem: RoleItemVO) {
  post("/enterRole", roleItem)
      .then((roleEnterVO: RoleEnterVO) => {
        accountInfo.role = roleEnterVO.roleItem;
        accountInfo.serverTime = roleEnterVO.serverTime;
        accountInfo.position = roleEnterVO.position;
        accountInfo.userState = UserState.enterRole;
      })
}

function operateRole(roleItem: RoleItemVO) {
  MsgBox.confirm(
      '登入或删除角色？',
      '请确认你的操作~~',
      {
        distinguishCancelAndClose: true,
        confirmButtonText: '登入角色',
        cancelButtonText: '删除角色',
      }
  )
      .then(() => {
        Tip.success('登录角色')
        loginRole(roleItem)
      })
      .catch((action: Action) => {
        if (action == "cancel") {
          post("deleteRole", roleItem)
              .then((deletedRole: RoleItemVO) => {
                roleItems.splice(roleItems.indexOf(deletedRole), 1);
                Tip.success("删除成功")
              })
        }
      })
}

</script>

<template>
  <div class="title">登录/创建/删除角色</div>
  <div class="role-item-parent-box">
    <span class="role-item-box" v-for="roleItem in roleItems" :key="roleItem.rid" @click="operateRole(roleItem)">
      <span class="role-desc">名称：{{ roleItem.roleName }}</span>
      <span class="role-desc">职业：{{ roleItem.career }}</span>
      <span class="role-desc">等级：{{ roleItem.level }}</span>
    </span>
    <span @click="toCreateRolePage" class="role-item-box add-role" v-if="roleItems.length<=3">+</span>
  </div>
</template>

<style scoped>
.title {
  height: 22px;
  text-align: center;
  box-sizing: border-box;
  border-bottom: 1px solid black;
}

.role-item-parent-box {
  height: calc(100% - 22px);
  width: 100%;
  background-color: #51458d;
}

.role-item-box {
  display: inline-block;
  width: 25%;
  height: 100%;
  box-sizing: border-box;
  border-right: 1px solid black;
  vertical-align: bottom;
}

.role-item-box:hover {
  background-color: #9488d3;
}

.role-item-box:active {
  background-color: rgba(126, 109, 227, 0.69);
}


.role-desc {
  display: inline-block;
  margin: 0;
}

.add-role {
  padding-top: 10px;
  text-align: center;
  font-size: 50px;
  font-weight: bold;
}
</style>