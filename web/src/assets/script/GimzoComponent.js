import { GimzoObject } from "./GimzoObject";
import { Cell } from "./Cell";

export class GimzoComponent extends GimzoObject{
    constructor(gameMap, c, r){
        super();
        this.gameMap = gameMap;
        this.c = c;
        this.r = r;

        this.cells = [];
        this.cells.push(new Cell(r ,c));
        this.x = this.r * this.gameMap.L;
        this.y = this.c * this.gameMap.L;
        this.eps = 0.1;
    }


    start(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], this);
        }
    }

    on_destroy(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], undefined);
        }

        let socket = this.gameMap.store.state.layout.socket;
        socket.send("delete " + this.id);
    }

    shrink(){
        //缩小
    }

    magnify(){
        //放大
    }

    rotate(){
        //旋转
    }

}