<template>
  <div class="switch-mode-bar">
    <div class="buttons-bar">
      <div class="bt-bar">
        <el-image :src="img.enlarge_icon" @click="enlarge"></el-image>
      </div>
      <div class="bt-bar">
        <el-image :src="img.shrink_icon" @click="shrink"></el-image>
      </div>
    </div>
    <div class="buttons-bar">
      <div class="bt-bar">
        <el-image :src="img.rotate_icon" @click="rotate"></el-image>
      </div>
      <div class="bt-bar">
        <el-image :src="img.remove_icon" @click="remove"></el-image>
      </div>
    </div>
  </div>
</template>

<script>
import icon from '../store/icon'
export default {
  data(){
    return{
      img:icon.state
    }
  },
  methods: {
    enlarge(){
      if(this.$store.state.status === 'game') return;
      console.log("enlarge")
    },
    shrink(){
      if(this.$store.state.status === 'game') return;
      console.log("shrink")
    },
    rotate(){
      if(this.$store.state.status === 'game') return;
      console.log("rotate")
    },
    remove(){
      if(this.$store.state.status === 'game') return;
      console.log("remove")
    }
  },
}
</script>

<style>
div.switch-mode-bar{
    height: 20vh;
    display: block;
}
div.buttons-bar{
  margin-left: 8vh;
  height: 10vh;
  display: flex;
}
div.bt-bar{
  margin-right: 8vh;
}
</style>