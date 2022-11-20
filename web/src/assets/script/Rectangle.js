import { Cell } from "./Cell";
import { GimzoObject } from "./GimzoObject";

export class Rectangle extends GimzoObject{
    /**
     * 构造函数创建新的长方形障碍物
     * @param {游戏地图} gameMap 
     * @param {横坐标} c 
     * @param {纵坐标} r 
     */
    constructor(gameMap, c, r){
        super();
        this.gameMap = gameMap;
        this.r = r;
        this.c = c;

        this.size = 1;

        this.cells = [];
        this.cells.push(new Cell(r, c));
        
        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.rectangle_icon;
    }

    //初始化函数
    start(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], this);
        }
    }

    //更新函数
    update(){
        this.render();
    }


    magnify(){
        //TODO检查是否有格子占用并且占用
        this.size = 2;
    }

    shrink(){
        //释放格子
        console.log("shrink");
        this.size = 1;
    }


    on_destroy(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], undefined);
        }

        let socket = this.gameMap.store.state.layout.socket;
        socket.send("delete Rectangle " + this.id);
    }

    /**
     * 绘制函数
     */
    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image,this.c * L, this.r * L, L * this.size, L * this.size);
    }
}