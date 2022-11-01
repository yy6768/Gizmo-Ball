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
    console.log(this.parent)
    console.log(this.canvas)
    let cxt = this.canvas.getContext("2d");
    this.$store.commit("updateGameObject",new GameMap(cxt,this.parent,this.$store))
  }
}
</script>

<style>
div.gamemap{
    height: 95vh;
    width:100%;
    background-color:green;
    display: flex;
    justify-content: center;
}
</style>