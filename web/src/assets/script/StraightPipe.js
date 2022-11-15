import { BallObject } from "./BallObject";

export class StraightPipe extends BallObject{
    
    constructor(gameMap, c, r){
        super();
        this.gameMap = gameMap;
        this.c = c;
        this.r = r;
        //d = 1 表示水平 -1表示竖直
        this.d = 1;

        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.straight_pipe_icon;
    }

    start(){

    }

    update(){
        this.render();
    }

    on_destroy(){

    }

    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image,this.c * L, this.r * L, L , L);
    }
}
