<script setup lang="ts">
import {RouterView, useRouter} from "vue-router";
// 跳转到登录页
useRouter().push("/login")
</script>

<template>
  <RouterView/>
</template>
