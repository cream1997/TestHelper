<script setup lang="ts" name="MsgDisplay">

</script>

<template>
  <p class="title">消息控制台
    <span>统计</span>
    <button>设置</button>
    <button>清空</button>
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
  height: 20px;
}

.middle {
  height: calc(100% - 50px);
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
</style>