<template>
  <div class="row">
    <div class="col-8">
       <content-field>
         <game-map />
       </content-field>
    </div>
    <div class="col-3">
      <div class="row">
        <content-field>
          <tool-bar></tool-bar>    
        </content-field>
      </div>
      <div class="row">
        <content-field>
          <switch-mode-bar></switch-mode-bar>
        </content-field>
      </div>
      <div class="row">
        <content-field>
          <game-design-bar></game-design-bar>
        </content-field>
      </div>
    </div>
  </div>
</template>

<script>
import GameMap from '@/components/GameMap.vue';
import ContentField from '@/components/ContentField.vue'
import ToolBar from '@/components/ToolBar.vue';
import SwitchModeBar from '@/components/SwitchModeBar.vue';
import GameDesignBar from '@/components/GameDesignBar.vue';

export default {
  components:{ GameMap ,ContentField, ToolBar, SwitchModeBar, GameDesignBar},
  data(){
    return{
      socket:null,
    }
  },


  mounted(){
    const socketUrl = `ws://localhost:3000/websocket/`;
    this.socket = new WebSocket(socketUrl);
    this.socket.onopen = () => {
      console.log("connected");
      this.$store.commit("updateSocket", this.socket);
    };
    this.socket.onmessage = msg =>{
      const store = this.$store;
      if(store.state.layout.status ==='layout'){
        //文件导入
      } else {
        const msgs = msg.data.split("#");
        const type = msgs[0];
        const x = msgs[2];
        const y = msgs[3];
        if (type === "Ball") {
          store.state.layout.gameMap.ball.set_position(x, y);
        } else if (type === "Baffle") {
          if(msgs[4] === 'true'){
            store.state.layout.gameMap.leftBaffle.set_position(x, y);
          } else {
            store.state.layout.gameMap.rightBaffle.set_position(x, y);
          }
        }        
      }
    };
    this.socket.onclose = () => {
      console.log("disconnected!");
    };
  },

  unmounted() { 
    this.socket.close();
  }

}
</script>

<style>
.game-text{
  display: table;
  height: 10vh;
}
.game-text-ceil{
    display: table-cell;
    vertical-align: middle;
    text-align: center;
    font-weight: 300;
    font-size: xx-large;
}
</style>