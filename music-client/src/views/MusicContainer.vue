<template>
  <el-container>
    <el-header>
      <music-header></music-header>
    </el-header>
    <el-main>
      <router-view />
      <music-current-play></music-current-play>
      <music-play-bar></music-play-bar>
      <music-scroll-top></music-scroll-top>
      <music-audio></music-audio>
    </el-main>
    <el-footer>
      <music-footer></music-footer>
    </el-footer>
  </el-container>
</template>

<script lang="ts" setup>
import { getCurrentInstance } from "vue";
import MusicHeader from "@/components/layouts/MusicHeader.vue";
import MusicCurrentPlay from "@/components/layouts/MusicCurrentPlay.vue";
import MusicPlayBar from "@/components/layouts/MusicPlayBar.vue";
import MusicScrollTop from "@/components/layouts/MusicScrollTop.vue";
import MusicFooter from "@/components/layouts/MusicFooter.vue";
import MusicAudio from "@/components/layouts/MusicAudio.vue";

const { proxy } = getCurrentInstance();

if (sessionStorage.getItem("dataStore")) {
  proxy.$store.replaceState(Object.assign({}, proxy.$store.state, JSON.parse(sessionStorage.getItem("dataStore"))));
}

window.addEventListener("beforeunload", () => {
  sessionStorage.setItem("dataStore", JSON.stringify(proxy.$store.state));
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

.el-container {
  min-height: calc(100% - 60px);
}
.el-header {
  padding: 0;
}
.el-main {
  padding-left: 0;
  padding-right: 0;
}
</style>
