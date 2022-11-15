<template>
  <div ref="parent" class="gamemap"> 
    <canvas ref="canvas" tabindex="0" id="canvas"></canvas>
  </div>
</template>

<script>
import { GameMap } from '@/assets/script/GameMap'

export default {
  data(){
    return{
      parent:null,
      canvas:null,
    }
  },
  mounted(){
    this.parent = document.getElementsByClassName("gamemap")[0]
    this.canvas = document.getElementById("canvas");
    let cxt = this.canvas.getContext("2d");
    this.$store.commit("updateMap",new GameMap(cxt,this.parent,this.$store));
    this.$store.commit("updateGameObject","Ball");
  }
}
</script>

<style>
div.gamemap{
    height: 95vh;
    width:100%;
    
    display: flex;
    justify-content: center;
}
</style>