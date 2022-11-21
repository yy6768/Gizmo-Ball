
import { GimzoComponent } from "./GimzoComponent";


export class StraightPipe extends GimzoComponent{
    
    constructor(gameMap, c, r){
        super(gameMap, c, r);
        //d = 0 表示水平 1表示竖直
        this.d = 1;
        this.dx = [0, 1];
        this.dy = [1, 0];
        
        // this.image = new Image();
        // this.image.src = this.gameMap.store.state.icon.straight_pipe_icon;
    }


    update(){
        this.render();
    }

    rotate(){
        this.d = 1 - this.d;
        let socket = this.gameMap.store.state.layout.socket;
        socket.send("magnify " + this.id);
    }


    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        // ctx.drawImage(this.image,this.c * L, this.r * L, L , L);
        const x = this.c * L;
        const y = this.r * L;
        ctx.beginPath();
        ctx.moveTo(x, y);
        ctx.lineTo(x + this.dx[this.d] * L, y + this.dy[this.d] * L);
        ctx.stroke();
        ctx.moveTo(x + this.dx[1 - this.d] * L, y + this.dy[1 - this.d] * L);
        ctx.lineTo(x + L, y + L);
        ctx.stroke();
    }
}
