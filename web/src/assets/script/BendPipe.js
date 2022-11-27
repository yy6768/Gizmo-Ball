import { GimzoComponent } from "./GimzoComponent";

export class BendPipe extends GimzoComponent{
    constructor(gameMap, c, r, angle){
        super(gameMap, c , r);
        // this.image = new Image();
        // this.image.src = this.gameMap.store.state.icon.bend_pipe_icon;
        this.angle = (angle + 3) % 4;
        this.dx = [0, 1, 1, 0];
        this.dy = [0, 0, 1, 1];
    }

    update(){
        this.render();
    }

    //旋转
    rotate(){
        this.angle = (this.angle + 1) % 4;
        let socket = this.gameMap.store.state.layout.socket;
        socket.send("rotate " + this.id);
    }

    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.strokeStyle = "black";
        // ctx.drawImage(this.image,this.c * L, this.r * L, L , L);
        ctx.beginPath();
        const x = (this.c + this.dx[this.angle]) * L;
        const y = (this.r + this.dy[this.angle]) * L;
        const ba = this.angle * Math.PI / 2;
        const ea = (this.angle + 1) * Math.PI / 2;
        ctx.arc(x, y, L, ba, ea);
        ctx.stroke();
    }

}