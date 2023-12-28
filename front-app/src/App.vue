<script setup lang="ts">
import {RouterView, useRouter} from "vue-router";
import {onMounted} from "vue";
// 跳转到登录页
onMounted(() => {
  useRouter().push("/login")
})
</script>

<template>
  <RouterView/>
</template>
