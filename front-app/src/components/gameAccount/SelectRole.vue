<script setup lang="ts">
import type AccountInfo from "@/interface/AccountInfo";
import {useAccountStore} from "@/store/account";
import {MsgBox} from "@/tools/CommonTools";

const accountInfo: AccountInfo = useAccountStore();
let roleItems = accountInfo.roleItems;
console.log(roleItems)


function toCreateRolePage() {

}

MsgBox.confirm("确定删除角色？").then(() => {
})

</script>

<template>
  <div class="title">选择/创建/删除角色</div>
  <div class="role-item-parent-box">
    <span class="role-item-box" v-for="roleItem in roleItems" :key="roleItem.rid">
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