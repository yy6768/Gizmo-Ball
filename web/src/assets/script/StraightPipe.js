import { Cell } from "./Cell";
import { GimzoObject } from "./GimzoObject";

export class StraightPipe extends GimzoObject{
    
    constructor(gameMap, c, r){
        super();
        this.gameMap = gameMap;
        this.c = c;
        this.r = r;
        //d = 1 表示水平 -1表示竖直
        this.d = 1;

        this.cells = [];
        this.cells.push(new Cell(r, c));

        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.straight_pipe_icon;
    }

    start(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], this);
        }
    }

    update(){
        this.render();
    }

    rotate(){
        //旋转
    }

    on_destroy(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], undefined);
        }

        let socket = this.gameMap.store.state.layout.socket;
        socket.send("delete StraightPipe " + this.id);
    }

    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image,this.c * L, this.r * L, L , L);
    }
}
