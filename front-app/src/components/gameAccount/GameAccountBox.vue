<script setup lang="ts" name="AccountBox">
import {useAccountStore} from "@/store/account";
import type AccountInfo from "@/interface/AccountInfo";
import UserAccount from "@/components/gameAccount/UserAccount.vue";
import RoleAccount from "@/components/gameAccount/RoleAccount.vue";
import SelectRole from "@/components/gameAccount/SelectRole.vue";
import UserState from "@/interface/UserState";

const accountInfo: AccountInfo = useAccountStore();

</script>

<template>
  <UserAccount v-if="accountInfo.userState===UserState.UnLoginUser"/>
  <RoleAccount v-if="accountInfo.userState===UserState.enterRole"/>
  <SelectRole v-if="accountInfo.userState===UserState.SelectRole"/>
</template>

<style scoped>

</style>