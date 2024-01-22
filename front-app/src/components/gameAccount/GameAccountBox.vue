<script setup lang="ts" name="AccountBox">
import {useAccountStore} from "@/store/account";
import type AccountInfo from "@/interface/AccountInfo";
import UserAccount from "@/components/gameAccount/UserAccount.vue";
import RoleAccount from "@/components/gameAccount/RoleAccount.vue";
import SelectRole from "@/components/gameAccount/SelectRole.vue";

const accountInfo: AccountInfo = useAccountStore();

function show(componentType: string) {
  if (componentType === "UserAccount") {
    return accountInfo.uid === 0;
  } else if (componentType === "RoleAccount") {
    return accountInfo.rid !== 0;
  } else if (componentType === "SelectRole") {
    return accountInfo.uid !== 0 && accountInfo.rid === 0;
  }
}

</script>

<template>
  <UserAccount v-if="show('UserAccount')"/>
  <RoleAccount v-if="show('RoleAccount')"/>
  <SelectRole v-if="show('SelectRole')"/>
</template>

<style scoped>

</style>