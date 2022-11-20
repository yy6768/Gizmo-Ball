import { Cell } from "./Cell";
import { GimzoObject } from "./GimzoObject";

export class BendPipe extends GimzoObject{
    constructor(gameMap, c, r){
        super();
        this.gameMap = gameMap;
        this.c = c;
        this.r = r;
        
        this.cells = [];
        this.cells.push(new Cell(r, c));

        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.bend_pipe_icon;
    }

    start(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], this);
        }
    }

    update(){
        this.render();
    }

    //旋转
    rotate(){
        //旋转
    }

    on_destroy(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], undefined);
        }

        let socket = this.gameMap.store.state.layout.socket;
        socket.send("delete BendPipe " + this.id);
    }

    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image,this.c * L, this.r * L, L , L);
    }

}