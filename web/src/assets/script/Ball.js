import { Cell } from "./Cell";
import { GimzoObject } from "./GimzoObject";


export class Ball extends GimzoObject{
    /**
     * 构造函数
     * @param {游戏地图 creator&信息专家} gameMap 
     * @param {横坐标} c 
     * @param {纵坐标} r 
     */
    constructor(gameMap,c, r){
        super();
        this.gameMap = gameMap;
        this.c = c;
        this.r = r;
        this.size = 1;
        //速度在x方向和y方向的分量
        this.velocity = [0,0];

        this.image = new Image();
        
        this.cells = [];
        this.cells.push(new Cell(r,c));

        this.image.src = this.gameMap.store.state.icon.ball_icon;
    }

    start(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], this);
        }
    }

    /**
     * 更新函数
     * 1、当处于布局模式是只需要重新渲染
     * 2、当处于游戏模式的时候位置发生变化
     */
    update(){
        this.render()
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
        socket.send("delete Ball " + this.id);
    }

    /**
     * 绘制函数
     */
    render(){
        // console.log(this.image);
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image, this.c * L, this.r * L, L * this.size, L * this.size);
    }

    
}