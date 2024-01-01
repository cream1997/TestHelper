<script setup lang="ts">
</script>

<template>
  <RouterView/>
</template>

<style>
/* 设置根元素的字体和背景颜色 */
html, body {
  font-family: sans-serif;
  font-size: 16px;
  background-color: #f5f5f5;
  margin: 0;
  padding: 0;
}

/* 重置所有元素的内外边距以及边框 */
#app {
  margin: 0;
  padding: 0;
}

*,
*::before,
*::after {
  box-sizing: border-box;
  outline: none;
  border: none;
}


</style>
