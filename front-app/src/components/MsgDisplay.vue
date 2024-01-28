<script setup lang="ts" name="MsgDisplay">

</script>

<template>
  <p class="title">
    <span class="title-text">消息控制台</span>
    <span class="num-display">999+</span>
    <button class="setup-btn">设置</button>
    <button class="clear-btn">清空</button>
  </p>
  <div class="middle">
    <ol class="msg-list-class">
      <li>消息1</li>
      <li>消息2</li>
      <li>消息3</li>
      <li>消息4</li>
    </ol>
  </div>
  <div class="footer">回到顶部</div>
</template>

<style scoped>
.title {
  margin: 1px auto;
  border-bottom: 1px black dotted;
  height: 25px;
}

.middle {
  height: calc(100% - 55px);
}

.footer {
  border-top: 1px black dotted;
  text-align: center;
  height: 30px;
}

.msg-list-class {
  padding: 0;
  margin: 0;
}

.title-text {
  width: 320px;
  font-size: small;
  font-weight: bold;
  display: inline-block;
}

.num-display {
  font-size: small;
  border: 1px solid black;
  border-radius: 6px;
  width: 34px;
  text-align: center;
  margin-right: 4px;
  display: inline-block;
}

.clear-btn, .setup-btn {
  text-align: right;
}
</style>