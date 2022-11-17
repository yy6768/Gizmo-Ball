<template>
  <div class="toolbar">
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="click" :disabled="mode==='game'">
          <el-image :src="img.click_icon" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="Ball" :disabled="mode==='game'">
          <el-image :src="img.ball_icon" fit="contain"></el-image>
        </el-radio>
      </div>
      
    </div>
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="Blackhole" :disabled="mode==='game'">
          <el-image :src="img.black_hole_icon" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="Triangle" :disabled="mode==='game'">
          <el-image :src="img.triangle_icon" fit="contain"></el-image>
        </el-radio>
      </div>
    </div>
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="Circle" :disabled="mode==='game'">
          <el-image :src="img.circle_icon" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="Rectangle" :disabled="mode==='game'">
          <el-image :src="img.rectangle_icon" fit="contain"></el-image>
        </el-radio>
      </div>
    </div>
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="StraightPipe" :disabled="mode==='game'">
          <el-image :src="img.straight_pipe_icon" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="BendPipe" :disabled="mode==='game'">
          <el-image :src="img.bend_pipe_icon" fit="contain"></el-image>
        </el-radio>
      </div>
    </div>
    <div class="colBar">
      <div class="rowBar">
        <el-radio v-model="selected" label="LeftBaffle" :disabled="mode==='game'">
          <el-image :src="img.baffle_icon" fit="contain"></el-image>
        </el-radio>
      </div>
      <div class="rowBar">
        <el-radio v-model="selected" label="RightBaffle" :disabled="mode==='game'">
          <el-image :src="img.baffle_icon" fit="contain"></el-image>
        </el-radio>
      </div>
    </div>
  </div>
</template>

<script>
import icon from '../store/icon'
export default {
  data(){
    return{
      selected:null,
      img:icon.state,
    }
  },
  watch:{
    selected:{
      handler(newValue){
        this.$store.commit('updateGameObject', newValue);
      }
    },
    
  },
  computed:{
    mode(){
      return this.$store.state.layout.status
    }
  },
  methods:{

  },
}
</script>

<style scoped>
div.toolbar{
    height: 60vh;
}
div.colBar{
  margin-left: 7vh;
  margin-top: 3vh;
  display: flex;
}
div.rowBar{
  margin-right: 12vh;
  margin-bottom: 2.5vh;
}
</style>